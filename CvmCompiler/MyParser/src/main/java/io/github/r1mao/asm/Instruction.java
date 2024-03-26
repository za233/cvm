package io.github.r1mao.asm;

import io.github.r1mao.ir.*;

import java.util.ArrayList;

public class Instruction
{
    private final Opcode opcode;
    private final ArrayList<Operand> operands=new ArrayList<>();
    Instruction(Opcode opcode)
    {
        this.opcode=opcode;
    }
    public Instruction addOperand(Object operand)
    {
        this.operands.add(Operand.wrap(operand));
        return this;
    }
    public Opcode getOpcode()
    {
        return this.opcode;
    }
    public String dump()
    {
        String result=this.opcode.getName()+" ";
        for(Operand o:this.operands)
            result+=o.dump()+" ";
        return result;
    }

    public ArrayList<Operand> getOperands()
    {
        return this.operands;
    }
    public static Instruction PushVar(Variable variable)
    {
        return new Instruction(Opcode.PUSH_VAR).addOperand(variable);
    }
    public static Instruction PushImm(Object imm)
    {
        Operand op=Operand.wrap(imm);
        if(op.getType().equals(Operand.OperandType.IMMEDIATE1))
            return new Instruction(Opcode.PUSH_IMM1).addOperand(imm);
        else if(op.getType().equals(Operand.OperandType.IMMEDIATE2))
            return new Instruction(Opcode.PUSH_IMM2).addOperand(imm);
        else if(op.getType().equals(Operand.OperandType.IMMEDIATE4))
            return new Instruction(Opcode.PUSH_IMM4).addOperand(imm);
        else if(op.getType().equals(Operand.OperandType.IMMEDIATE8))
            return new Instruction(Opcode.PUSH_IMM8).addOperand(imm);
        else
            assert false : "no such opcode";
        return null;
    }
    public static Instruction Load(int byteSize)
    {
        return new Instruction(Opcode.LOAD).addOperand(Byte.valueOf((byte)byteSize));
    }
    public static Instruction Store(int byteSize)
    {
        return new Instruction(Opcode.STORE).addOperand(Byte.valueOf((byte)byteSize));
    }
    public static Instruction PushBp()
    {
        return new Instruction(Opcode.PUSH_BP);
    }
    public static Instruction StoreArg()
    {
        return new Instruction(Opcode.STORE_ARG);
    }
    public static Instruction PopArg(int argCount)
    {
        return new Instruction(Opcode.POP_ARG).addOperand(Byte.valueOf((byte)argCount));
    }
    public static Instruction Add(int byteSize)
    {
        return new Instruction(Opcode.ADD).addOperand(Byte.valueOf((byte)byteSize));
    }
    public static Instruction Sub(int byteSize)
    {
        return new Instruction(Opcode.SUB).addOperand(Byte.valueOf((byte)byteSize));
    }
    public static Instruction Mul(int byteSize,boolean signed)
    {
        Opcode op;
        if(signed)
            op=Opcode.S_MUL;
        else
            op=Opcode.U_MUL;
        return new Instruction(op).addOperand(Byte.valueOf((byte)byteSize));
    }
    public static Instruction Div(int byteSize,boolean signed)
    {
        Opcode op;
        if(signed)
            op=Opcode.S_DIV;
        else
            op=Opcode.U_DIV;
        return new Instruction(op).addOperand(Byte.valueOf((byte)byteSize));
    }
    public static Instruction Mod(int byteSize,boolean signed)
    {
        Opcode op;
        if(signed)
            op=Opcode.S_MOD;
        else
            op=Opcode.U_MOD;
        return new Instruction(op).addOperand(Byte.valueOf((byte)byteSize));
    }

    public static Instruction And(int byteSize)
    {
        return new Instruction(Opcode.AND).addOperand(Byte.valueOf((byte)byteSize));
    }
    public static Instruction Or(int byteSize)
    {
        return new Instruction(Opcode.OR).addOperand(Byte.valueOf((byte)byteSize));
    }
    public static Instruction Xor(int byteSize)
    {
        return new Instruction(Opcode.XOR).addOperand(Byte.valueOf((byte)byteSize));
    }
    public static Instruction Shl(int byteSize)
    {
        return new Instruction(Opcode.SHL).addOperand(Byte.valueOf((byte)byteSize));
    }
    public static Instruction Shr(int byteSize,boolean signed)
    {
        Opcode op;
        if(signed)
            op=Opcode.A_SHR;
        else
            op=Opcode.L_SHR;
        return new Instruction(op).addOperand(Byte.valueOf((byte)byteSize));
    }
    public static Instruction Neg(int byteSize)
    {
        return new Instruction(Opcode.NEG).addOperand(Byte.valueOf((byte)byteSize));
    }
    public static Instruction Not(int byteSize)
    {
        return new Instruction(Opcode.NOT).addOperand(Byte.valueOf((byte)byteSize));
    }
    public static Instruction SExt(int byteSize,int targetSize)
    {
        return new Instruction(Opcode.S_EXT).addOperand(Byte.valueOf((byte)byteSize)).addOperand(Byte.valueOf((byte)targetSize));
    }
    public static Instruction Call(Function function)
    {
        return new Instruction(Opcode.CALL).addOperand(function);
    }
    public static Instruction Return()
    {
        return new Instruction(Opcode.RETURN);
    }
    public static Instruction Branch(BasicBlock ifTrue)
    {
        return new Instruction(Opcode.BRANCH).addOperand(ifTrue);
    }
    public static Instruction Goto(BasicBlock target)
    {
        return new Instruction(Opcode.GOTO).addOperand(target);
    }
    public static Instruction Cmp(int size, int type)
    {
        return new Instruction(Opcode.CMP).addOperand(Byte.valueOf((byte) size)).addOperand(Byte.valueOf((byte) type));
    }
    public static Instruction Alloc(int size)
    {
        return new Instruction(Opcode.ALLOC).addOperand(size);
    }
    public static Instruction Pop(int popCount)
    {
        return new Instruction(Opcode.POP).addOperand(Byte.valueOf((byte) popCount));
    }
    public static Instruction Enter()
    {
        return new Instruction(Opcode.ENTER);
    }
    public static Instruction Leave()
    {
        return new Instruction(Opcode.LEAVE);
    }
    public static Instruction VMCall(String callStr)
    {
        return new Instruction(Opcode.VMCALL).addOperand(callStr);
    }
    public int getSize()
    {
        int size=1;
        for(Operand o:this.operands)
            size+=o.getType().getEncodeSize();
        return size;
    }
    public ArrayList<Byte> encode()
    {
        ArrayList<Byte> data=new ArrayList<>();
        data.add(this.opcode.getByte());
        for(Operand op:this.operands)
            data.addAll(op.encode());
        return data;
    }
}
