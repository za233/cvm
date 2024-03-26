package io.github.r1mao.symbol;

import java.util.HashMap;


public class Type
{
    private BasicType base;
    private boolean isArray=false;
    private int arrayLength=-1;
    private static HashMap<BasicType, Type> pool=new HashMap<>();
    private Type(BasicType base)
    {
        this.base=base;
    }
    private Type(BasicType base, int arrayLength)
    {
        this.base=base;
        this.isArray=true;
        this.arrayLength=arrayLength;
    }
    public static Type getPrimitiveType(BasicType type)
    {
        assert type!=null;
        if(pool.containsKey(type))
            return pool.get(type);
        Type wrapper=new Type(type);
        pool.put(type,wrapper);
        return wrapper;
    }
    public boolean isSame(Type other)
    {
        if(this.isArray==other.isArray && this.base.equals(other.base))
            return true;
        else
            return false;
    }
    public boolean isStrictSame(Type other)
    {
        if(this.isArray==other.isArray && this.arrayLength==other.arrayLength && this.base.equals(other.base))
            return true;
        else
            return false;
    }
    public Type getSigned()
    {
        return Type.getPrimitiveType(BasicType.fromBitSize(this.base.getBitSize(), true));
    }
    public Type getUnsigned()
    {
        return Type.getPrimitiveType(BasicType.fromBitSize(this.base.getBitSize(), false));
    }
    public static Type getArrayType(BasicType type, int arrayLength)
    {
        assert type!=null;
        return new Type(type,arrayLength);
    }
    public BasicType getBase()
    {
        return this.base;
    }
    public int getByteSize()
    {
        if(this.isArray)
            return base.getBitSize()/8*arrayLength;
        else
            return this.base.getBitSize()/8;
    }
    public int getSize()
    {
        return this.getByteSize()*8;
    }
    public boolean isArray()
    {
        return this.isArray;
    }
    public boolean isVoid()
    {
        if(!this.isArray && this.base==BasicType.VOID)
            return true;
        return false;
    }
    public int getArrayLength()
    {
        return this.arrayLength;
    }

    public String toString()
    {

        String s=this.base.toString();
        if(this.isArray)
            s+="["+this.arrayLength+"]";
        return s;
    }

}
