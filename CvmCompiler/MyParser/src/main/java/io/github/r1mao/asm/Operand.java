package io.github.r1mao.asm;


import io.github.r1mao.ir.BasicBlock;
import io.github.r1mao.ir.Function;
import io.github.r1mao.ir.Variable;
import io.github.r1mao.ir.expr.Compare;

import java.util.ArrayList;
import java.util.HashMap;

public class Operand
{
    private final Object operand;
    private final OperandType type;
    private static HashMap<Object,Operand> cached=new HashMap<>();
    public static int EQ=0,NEQ=1,LE=2,LT=3,GE=4,GT=5,S_LE=6,S_LT=7,S_GE=8,S_GT=9;
    enum OperandType
    {
        VARIABLE(4),
        BASIC_BLOCK(4),
        FUNCTION(4),
        IMMEDIATE1(1),
        IMMEDIATE2(2),
        IMMEDIATE4(4),
        IMMEDIATE8(8),
        STRING(2);
        private int encodeSize;
        OperandType(int size)
        {
            this.encodeSize=size;
        }
        public int getEncodeSize()
        {
            return this.encodeSize;
        }
    }
    public Object get()
    {
        return this.operand;
    }
    public static void reset()
    {
        cached.clear();
    }
    public OperandType getType()
    {
        return this.type;
    }

    public String dump()
    {
        if(this.getType().equals(OperandType.VARIABLE))
            return ((Variable) this.operand).getName();
        else if(this.getType().equals(OperandType.BASIC_BLOCK))
            return ((BasicBlock) this.operand).id();
        else if(this.getType().equals(OperandType.FUNCTION))
            return ((Function) this.operand).getName();
        else
            return String.valueOf(this.operand);
    }
    public ArrayList<Byte> encode()
    {
        ArrayList<Byte> data=new ArrayList<>();
        if(this.getType().equals(OperandType.IMMEDIATE1))
            data.add((byte)this.operand);
        else if(this.getType().equals(OperandType.IMMEDIATE2))
        {
            short value=(short) this.operand;
            for(int i=0;i<2;i++)
                data.add((byte) ((byte)(value>>(i*8))&0xff));
        }
        else if(this.getType().equals(OperandType.IMMEDIATE4))
        {
            int value=(int) this.operand;
            for(int i=0;i<4;i++)
                data.add((byte) ((byte)(value>>(i*8))&0xff));
        }
        else if(this.getType().equals(OperandType.IMMEDIATE8))
        {
            long value=(long) this.operand;
            for(int i=0;i<8;i++)
                data.add((byte) ((byte)(value>>(i*8))&0xff));
        }
        else
            assert false : "wrong time to encode";
        return data;
    }

    private Operand(Object operand, OperandType type)
    {
        this.operand=operand;
        this.type=type;
    }
    public static int encodeCmp(Compare cmp)
    {
        if(cmp.getCmp().equals("=="))
            return EQ;
        else if(cmp.getCmp().equals("!="))
            return NEQ;
        else if(cmp.getCmp().equals(">"))
        {
            if(!cmp.isSigned())
                return GT;
            else
                return S_GT;
        }
        else if(cmp.getCmp().equals(">="))
        {
            if(!cmp.isSigned())
                return GE;
            else
                return S_GE;
        }
        else if(cmp.getCmp().equals("<"))
        {
            if(!cmp.isSigned())
                return LT;
            else
                return S_LT;
        }
        else if(cmp.getCmp().equals("<="))
        {
            if(!cmp.isSigned())
                return LE;
            else
                return S_LE;
        }
        else
            assert false : "no such compare operator";
        return -1;
    }
    private static Operand get(Object obj, OperandType type)
    {
        if(cached.containsKey(obj))
            return cached.get(obj);
        Operand alloc=new Operand(obj,type);
        cached.put(obj,alloc);
        return alloc;
    }
    public static Operand wrap(Object obj)
    {
        if(obj instanceof Variable)
            return V((Variable) obj);
        else if(obj instanceof BasicBlock)
            return B((BasicBlock) obj);
        else if(obj instanceof Function)
            return F((Function) obj);
        else if(obj instanceof Byte)
            return I1((Byte) obj);
        else if(obj instanceof Short)
            return I2((Short) obj);
        else if(obj instanceof Integer)
            return I4((Integer) obj);
        else if(obj instanceof Long)
            return I8((Long) obj);
        else if(obj instanceof String)
            return S((String) obj);
        else
            assert false : "unknown operand type";
            return null;
    }
    public static Operand S(String ref)
    {
        return get(ref, OperandType.STRING);
    }
    public static Operand V(Variable v)
    {
        return get(v,OperandType.VARIABLE);
    }
    public static Operand B(BasicBlock b)
    {
        return get(b,OperandType.BASIC_BLOCK);
    }
    public static Operand F(Function func)
    {
        return get(func,OperandType.FUNCTION);
    }
    public static Operand I1(Byte i)
    {
        return get(i,OperandType.IMMEDIATE1);
    }
    public static Operand I2(Short i)
    {
        return get(i,OperandType.IMMEDIATE2);
    }
    public static Operand I4(Integer i)
    {
        return get(i,OperandType.IMMEDIATE4);
    }
    public static Operand I8(Long i)
    {
        return get(i,OperandType.IMMEDIATE8);
    }


}
