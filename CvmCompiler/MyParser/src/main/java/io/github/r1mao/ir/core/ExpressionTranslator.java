package io.github.r1mao.ir.core;

import io.github.r1mao.ir.Module;
import io.github.r1mao.ir.Variable;
import io.github.r1mao.ir.expr.*;
import io.github.r1mao.parser.ClikeParser;
import io.github.r1mao.symbol.BasicType;
import io.github.r1mao.symbol.SymbolWatcher;
import io.github.r1mao.symbol.Type;

import java.util.List;

public class ExpressionTranslator
{
    private final SymbolWatcher<Variable> symbolWatcher;
    private final Module module;
    public Variable getVariable(String varName)
    {
        return this.symbolWatcher.getSymbol(varName).getData();
    }
    public ExpressionTranslator(SymbolWatcher<Variable> symbolWatcher, Module context)
    {
        this.symbolWatcher=symbolWatcher;
        this.module=context;
    }
    public SymbolWatcher<Variable> getSymbolWatcher()
    {
        return this.symbolWatcher;
    }
    public Expression getSymbolValueExpression(ClikeParser.SymbolValueContext symbolValueContext)
    {
        if(symbolValueContext.varValue()!=null)
        {
            ClikeParser.VarValueContext valueContext=symbolValueContext.varValue();
            Variable v=this.getVariable(valueContext.var().getText());
            return new VariableValue(v);

        }
        else if(symbolValueContext.arrValue()!=null)
        {
            ClikeParser.ArrValueContext arrValueContext=symbolValueContext.arrValue();
            Variable v=this.getVariable(arrValueContext.var().getText());
            return new ArrayGet(v, this.getExpression(arrValueContext.expr()));

        }
        else
        {
            System.out.println("undefined variable");
            return null;
        }
    }
    public Expression getConstValueExpression(ClikeParser.ConstValueContext constValue)
    {
        String text=constValue.getText();
        if(text.startsWith("0x"))
        {
            text=text.replaceAll("0x","");
            return new ConstantValue(Long.parseUnsignedLong(text,16), true);
        }
        else
        {
            boolean unsigned=text.endsWith("U");
            text=text.replaceAll("U","");
            if(unsigned)
                return new ConstantValue(Long.parseUnsignedLong(text), true);
            else
                return new ConstantValue(Long.parseLong(text), false);
        }

    }
    public Expression getCallExpression(ClikeParser.CallExprContext callExprContext)
    {
        if(callExprContext.function()!=null)
        {
            String functionName=callExprContext.function().getText();
            CallExpr callIr=new CallExpr(this.module.getFunction(functionName));
            for(ClikeParser.ExprContext args:callExprContext.callArgs().expr())
                callIr.addArgument(this.getExpression(args));

            return callIr;
        }
        else
        {
            String prefix=callExprContext.vm().getText();
            String name=callExprContext.vmCall().getText();
            VMCallExpr callIr=new VMCallExpr(prefix,name);
            for(ClikeParser.ExprContext args:callExprContext.callArgs().expr())
                callIr.addArgument(this.getExpression(args));
            return callIr;
        }

    }

    public Expression getExpression(ClikeParser.ExprContext exprContext)
    {

        if(exprContext.value()!=null)
        {
            ClikeParser.ValueContext valueContext=exprContext.value();
            if(valueContext.symbolValue()!=null)
                return this.getSymbolValueExpression(valueContext.symbolValue());

            else if(valueContext.constValue()!=null)
                return this.getConstValueExpression(valueContext.constValue());
            else
            {
                System.out.println("invalid expression: "+exprContext.getText());
                return null;
            }

        }
        else if(exprContext.op!=null)
        {
            List<ClikeParser.ExprContext> subExpression=exprContext.expr();
            if(subExpression.size()==1)
            {
                ClikeParser.ExprContext expr1=subExpression.get(0);
                return new UnaryOp(exprContext.op.getText(), this.getExpression(expr1));
            }
            else if(subExpression.size()==2)
            {
                ClikeParser.ExprContext expr1=subExpression.get(0);
                ClikeParser.ExprContext expr2=subExpression.get(1);
                Expression subExpression1=this.getExpression(expr1);
                Expression subExpression2=this.getExpression(expr2);
                String op=exprContext.op.getText();
                assert !subExpression1.getType().isArray() && !subExpression2.getType().isArray() : "the operands of the BinOp cannot be arrays";
                if(!subExpression1.getType().isSame(subExpression2.getType()))
                {
                    Type type1=subExpression1.getType();
                    Type type2=subExpression2.getType();

                    int bit=Math.max(type1.getSize(), type2.getSize());
                    boolean sign=type1.getBase().isSigned() && type2.getBase().isSigned();
                    Type castTo=Type.getPrimitiveType(BasicType.fromBitSize(bit,sign));
                    if(!subExpression1.getType().isSame(castTo))
                        subExpression1=new Cast(castTo,subExpression1);
                    if(!subExpression2.getType().isSame(castTo))
                        subExpression2=new Cast(castTo,subExpression2);
                }
                return new BinaryOp(op, subExpression1, subExpression2);
            }

            else
            {
                assert false : "invalid expression: "+exprContext.getText();
                return null;
            }


        }
        else if(exprContext.callExpr()!=null)
        {
            return this.getCallExpression(exprContext.callExpr());
        }
        else if(exprContext.defineTypes()!=null)
        {
            ClikeParser.DefineTypesContext defineTypesContext=exprContext.defineTypes();
            return new Cast(Type.getPrimitiveType(BasicType.fromString(defineTypesContext.getText())), this.getExpression(exprContext.expr(0)));
        }
        else if(exprContext.expr().size()==1)
        {
            return this.getExpression(exprContext.expr(0));
        }
        else
        {
            assert false : "invalid expression: "+exprContext.getText();
            return null;
        }
    }
    public Expression getCondition(ClikeParser.ConditionContext conditionsContext)
    {
        if(conditionsContext.atomCondition()!=null)
        {
            ClikeParser.AtomConditionContext atomConditionContext=conditionsContext.atomCondition();
            Expression subExpression1=this.getExpression(atomConditionContext.expr(0));
            Expression subExpression2=this.getExpression(atomConditionContext.expr(1));
            assert !subExpression1.getType().isArray() && !subExpression2.getType().isArray() : "the operands of the BinOp cannot be arrays";
            if(!subExpression1.getType().isSame(subExpression2.getType()))
            {
                Type type1=subExpression1.getType();
                Type type2=subExpression2.getType();

                int bit=Math.max(type1.getSize(), type2.getSize());
                boolean sign=type1.getBase().isSigned() && type2.getBase().isSigned();
                Type castTo=Type.getPrimitiveType(BasicType.fromBitSize(bit,sign));
                if(!subExpression1.getType().isSame(castTo))
                    subExpression1=new Cast(castTo,subExpression1);
                if(!subExpression2.getType().isSame(castTo))
                    subExpression2=new Cast(castTo,subExpression2);
            }
            return new Compare(atomConditionContext.cmp.getText(), subExpression1, subExpression2);
        }
        else if(conditionsContext.logic!=null)
        {
            if(conditionsContext.condition().size()==1)
            {
                return new UnaryOp("!", this.getCondition(conditionsContext.condition(0)));
            }
            else if(conditionsContext.condition().size()==2)
            {
                return new BinaryOp(conditionsContext.logic.getText(), this.getCondition(conditionsContext.condition(0)), this.getCondition(conditionsContext.condition(1)));
            }
            else
                return null;
        }
        else if(conditionsContext.condition().size()==1)
        {
            return this.getCondition(conditionsContext.condition(0));
        }
        else
            return null;
    }
}
