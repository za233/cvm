package io.github.r1mao.asm;


import io.github.r1mao.ir.UsedAs;
import io.github.r1mao.ir.Variable;
import io.github.r1mao.ir.expr.*;
import io.github.r1mao.ir.internal.VMFunction;
import io.github.r1mao.ir.stmt.*;
import io.github.r1mao.symbol.BasicType;
import io.github.r1mao.symbol.Type;

import java.util.ArrayList;

public class AssembleCore
{
    private ArrayList<Instruction> result=new ArrayList<>();
    private void add(Instruction instruction)
    {
        this.result.add(instruction);
    }
    private void pushOffset(Variable variable)
    {
        if(variable.getUseType().equals(UsedAs.GLOBAL_VARIABLE))
        {
            this.add(Instruction.PushVar(variable));
        }
        else if(variable.getUseType().equals(UsedAs.LOCAL_VARIABLE))
        {
            this.add(Instruction.PushBp());
            this.add(Instruction.PushVar(variable));
            this.add(Instruction.Add(8));
        }
        else if(variable.getUseType().equals(UsedAs.FUNCTION_ARGUMENT))
        {

            this.add(Instruction.PushBp());
            this.add(Instruction.PushVar(variable));
            this.add(Instruction.Add(8));
            if(variable.getType().isArray())
                this.add(Instruction.Load(8));

        }
        else
            assert false : "no such variable type";

    }
    public void pushOffset(VariableValue arrayGet)
    {
        this.pushOffset(arrayGet.getVariable());
    }
    public void pushOffset(ArrayGet arrayGet)
    {
        Variable array=arrayGet.getArray();
        Expression index=arrayGet.getIndex();
        byte elementSize=(byte)arrayGet.getType().getByteSize();
        this.pushOffset(array);
        this.add(arrayGet.getIndex());
        BasicType offsetType=index.getType().getBase();
        if(offsetType.isSigned() && offsetType.getBitSize()!=64)
            this.add(Instruction.SExt(index.getType().getByteSize(),8));
        if(elementSize!=1)
        {
            this.add(Instruction.PushImm(elementSize));
            this.add(Instruction.Mul(8,true));
        }
        this.add(Instruction.Add(8));
    }
    private void add(ArrayGet arrayGet)
    {
        this.pushOffset(arrayGet);
        this.add(Instruction.Load(arrayGet.getType().getByteSize()));
    }
    private void add(BinaryOp binaryOp)
    {
        Expression left=binaryOp.operand1(),right=binaryOp.operand2();
        this.add(left);
        this.add(right);
        String op=binaryOp.getOp();
        int size=binaryOp.getType().getByteSize();
        BasicType type=left.getType().getBase();

        if(op.equals("+"))
            this.add(Instruction.Add(size));
        else if(op.equals("-"))
            this.add(Instruction.Sub(size));
        else if(op.equals("*"))
            this.add(Instruction.Mul(size,type.isSigned()));
        else if(op.equals("/"))
            this.add(Instruction.Div(size,type.isSigned()));
        else if(op.equals("%"))
            this.add(Instruction.Mod(size,type.isSigned()));
        else if(op.equals("&") || op.equals("&&"))
            this.add(Instruction.And(size));
        else if(op.equals("|") || op.equals("||"))
            this.add(Instruction.Or(size));
        else if(op.equals("^"))
            this.add(Instruction.Xor(size));
        else if(op.equals("<<"))
            this.add(Instruction.Shl(size));
        else if(op.equals(">>"))
            this.add(Instruction.Shr(size,type.isSigned()));
        else
            assert false : "unknown operation.";
    }
    private void add(UnaryOp unaryOp)
    {
        Expression operand=unaryOp.operand();
        this.add(operand);
        String op=unaryOp.getOp();
        int size=unaryOp.getType().getByteSize();
        if(op.equals("-"))
            this.add(Instruction.Neg(size));
        else if(op.equals("~"))
            this.add(Instruction.Not(size));
        else
            assert false : "unknown operation.";
    }
    private void add(CallExpr callExpr)
    {
        assert callExpr.getArguments().size()==callExpr.getFunction().getArgs().size() : "invalid arguments.";
        for(Expression arg:callExpr.getArguments())
        {
            if(arg instanceof VariableValue && ((VariableValue) arg).getVariable().getType().isArray())
                this.pushOffset((VariableValue) arg);
            else
                this.add(arg);
            this.add(Instruction.StoreArg());
        }
        this.add(Instruction.Call(callExpr.getFunction()));
        assert callExpr.getArguments().size() < 256 : "too many arguments.";
        if(callExpr.getArguments().size()!=0)
            this.add(Instruction.PopArg(callExpr.getArguments().size()));
    }
    private void add(VMCallExpr vmCall)
    {


        if(vmCall.isBuiltin())
        {
            //assert vmCall.getArguments().size()==vmCall.getFunction().getSymbol().getArguments().size() : "invalid arguments.";
            for(Expression arg:vmCall.getArguments())
            {
                if(arg instanceof VariableValue && ((VariableValue) arg).getVariable().getType().isArray())
                    this.pushOffset((VariableValue) arg);
                else
                    this.add(arg);
                this.add(Instruction.StoreArg());
            }
            this.add(Instruction.VMCall(vmCall.getImportName()));
            assert vmCall.getArguments().size() < 256 : "too many arguments.";
            if(vmCall.getArguments().size()!=0)
                this.add(Instruction.PopArg(vmCall.getArguments().size()));
        }
        else
        {
            VMFunction vmFunction=vmCall.getFunction();
            if(vmFunction.getPrefix().equals("vm"))
            {

                if(vmFunction.getCallName().equals("ref"))
                {
                    assert vmCall.getArguments().size()==1 : "invalid arguments for vm.ref";
                    Expression argument=vmCall.getArguments().get(0);
                    assert argument instanceof VariableValue || argument instanceof ArrayGet : "invalid arguments for vm.ref";
                    if(argument instanceof VariableValue)
                        this.pushOffset(((VariableValue) argument));
                    else if(argument instanceof ArrayGet)
                        this.pushOffset((ArrayGet) argument);
                    else
                        this.add(Instruction.PushImm((byte)0));
                }
                else if(vmFunction.getCallName().equals("load"))
                {
                    Expression address=vmCall.getArguments().get(0), bit=vmCall.getArguments().get(1);
                    assert bit instanceof ConstantValue : "invalid argument for vm.load";
                    ConstantValue val= (ConstantValue) bit;
                    this.add(address);
                    this.add(Instruction.Load((int) val.getValue()));
                }
                else if(vmFunction.getCallName().equals("store"))
                {
                    Expression address=vmCall.getArguments().get(0), bit=vmCall.getArguments().get(1), data=vmCall.getArguments().get(2);
                    assert bit instanceof ConstantValue : "invalid argument for vm.store";
                    ConstantValue val= (ConstantValue) bit;
                    this.add(address);
                    this.add(data);
                    this.add(Instruction.Store((int) val.getValue()));
                    this.add(Instruction.PushImm((byte)0));
                }

            }
        }


    }
    private void add(VariableValue variableValue)
    {
        assert !variableValue.getVariable().getType().isArray() : "variable cannot be an array";
        this.pushOffset(variableValue);
        this.add(Instruction.Load(variableValue.getType().getByteSize()));
    }

    private void add(Cast cast)
    {
        this.add(cast.getSrc());
        Type fromType=cast.getSrc().getType();
        Type toType=cast.getCastTo();
        int origSize=fromType.getByteSize(),newSize=toType.getByteSize();
        if(origSize > newSize)
        {
            if(newSize==1)
                this.add(Instruction.PushImm(Byte.valueOf((byte) 0xff)));
            else if(newSize==2)
                this.add(Instruction.PushImm(Short.valueOf((short) 0xffff)));
            else if(newSize==4)
                this.add(Instruction.PushImm(Integer.valueOf((int) 0xffffffff)));
            else if(newSize==8)
                this.add(Instruction.PushImm(Long.valueOf((long) 0xffffffffffffffffL)));
            else
                assert false : "no such type";
            this.add(Instruction.And(origSize));
        }
        else if(origSize < newSize)
        {
            if(toType.getBase().isSigned())
                this.add(Instruction.SExt(origSize,newSize));
        }
    }
    private void add(ConstantValue constantValue)
    {
        long value=constantValue.getValue();

        if(value>=-128L && value<=255L)
        {
            this.add(Instruction.PushImm(Byte.valueOf((byte) value)));
            if((value&0x80)!=0)
                this.add(Instruction.SExt(1,8));
        }
        else if(value>=-32768L && value<=65535L)
        {
            this.add(Instruction.PushImm(Short.valueOf((short) value)));
            if((value&0x8000)!=0)
                this.add(Instruction.SExt(2,8));
        }
        else if(value>=-2147483648L && value<=4294967295L)
        {
            this.add(Instruction.PushImm(Integer.valueOf((int) value)));
            if((value&0x80000000)!=0)
            this.add(Instruction.SExt(4,8));
        }
        else
            this.add(Instruction.PushImm(Long.valueOf(value)));

    }
    private void add(Compare compare)
    {
        this.add(compare.getLeft());
        this.add(compare.getRight());
        this.add(Instruction.Cmp(compare.getLeft().getType().getByteSize(), Operand.encodeCmp(compare)));
    }
    private void add(Expression expression)
    {
        if(expression instanceof ArrayGet)
        {
            ArrayGet arrayGet= (ArrayGet) expression;
            this.add(arrayGet);
        }
        else if(expression instanceof BinaryOp)
        {
            BinaryOp binaryOp= (BinaryOp) expression;
            this.add(binaryOp);
        }
        else if(expression instanceof UnaryOp)
        {
            UnaryOp unaryOp= (UnaryOp) expression;
            this.add(unaryOp);
        }
        else if(expression instanceof CallExpr)
        {
            CallExpr callExpr= (CallExpr) expression;
            this.add(callExpr);
        }
        else if(expression instanceof VariableValue)
        {
            VariableValue variableValue= (VariableValue) expression;
            this.add(variableValue);
        }
        else if(expression instanceof Cast)
        {
            Cast cast= (Cast) expression;
            this.add(cast);
        }
        else if(expression instanceof ConstantValue)
        {
            ConstantValue constantValue= (ConstantValue) expression;
            this.add(constantValue);
        }
        else if(expression instanceof Compare)
        {
            Compare compare= (Compare) expression;
            this.add(compare);
        }
        else if(expression instanceof VMCallExpr)
        {
            VMCallExpr vmCall=(VMCallExpr) expression;
            this.add(vmCall);
        }
    }
    public void assemble(Statement statement)
    {
        if(statement instanceof Assign)
        {
            Assign assign= (Assign) statement;
            Expression dst=assign.getDst();
            if(dst instanceof ArrayGet)
            {
                ArrayGet arrayGet= (ArrayGet) dst;
                this.pushOffset(arrayGet);

            }
            else if(dst instanceof VariableValue)
            {
                VariableValue variableValue= (VariableValue) dst;
                this.pushOffset(variableValue);
            }
            else
                assert false : "unsupported expression store.";
            this.add(assign.getSrc());
            this.add(Instruction.Store(dst.getType().getByteSize()));
        }
        else if(statement instanceof Branch)
        {
            Branch br= (Branch) statement;
            if(!br.isConditional())
                this.add(Instruction.Goto(br.getTrueBlock()));
            else
            {
                Expression cond=br.getCondition();
                this.add(cond);
                this.add(Instruction.Branch(br.getTrueBlock()));
                this.add(Instruction.Goto(br.getFalseBlock()));
            }
        }
        else if(statement instanceof Call)
        {
            Call call= (Call) statement;
            this.add(call.getCall());
            this.add(Instruction.Pop(1));
        }
        else if(statement instanceof Return)
        {
            Return ret= (Return) statement;
            if(ret.getReturnValue()!=null)
                this.add(ret.getReturnValue());
            else
                this.add(Instruction.PushImm((byte)0));
            this.add(Instruction.Leave());
            this.add(Instruction.Return());
        }
        else if(statement instanceof VMCall)
        {
            VMCall call=(VMCall) statement;
            this.add(call.getCall());
            this.add(Instruction.Pop(1));
        }
        else if(statement instanceof Alloc)
            return;
        else
            assert false : "unknown statement type";

    }
    public void clear()
    {
        this.result.clear();
    }
    public ArrayList<Instruction> getResult()
    {
        return this.result;
    }
}
