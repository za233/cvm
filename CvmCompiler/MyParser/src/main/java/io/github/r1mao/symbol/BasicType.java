package io.github.r1mao.symbol;

public enum BasicType
{
    INT1(8,true,"char"),
    INT2(16,true,"short"),
    INT4(32,true,"int"),
    INT8(64,true,"long"),
    UINT1(8,false,"unsigned char"),
    UINT2(16,false,"unsigned short"),
    UINT4(32,false,"unsigned int"),
    UINT8(64,false,"unsigned long"),
    VOID(0,false,"void");
    private int bit;
    private boolean isSigned;
    private String dumpString;
    BasicType(int bit, boolean isSigned,String dumpString)
    {
        this.bit=bit;
        this.isSigned=isSigned;
        this.dumpString=dumpString;
    }

    public String toString()
    {
        return this.dumpString;
    }
    public static BasicType fromString(String text)
    {
        boolean signed=true;
        if(text.contains("unsigned"))
            signed=false;
        if(text.contains("char"))
        {
            if(signed)
                return INT1;
            else
                return UINT1;
        }
        else if(text.contains("short"))
        {
            if(signed)
                return INT2;
            else
                return UINT2;
        }
        else if(text.contains("int"))
        {
            if(signed)
                return INT4;
            else
                return UINT4;
        }
        else if(text.contains("long"))
        {
            if(signed)
                return INT8;
            else
                return UINT8;
        }
        else if(text.contains("void"))
            return VOID;
        else
        {
            throw new NullPointerException();
        }

    }
    public static BasicType fromBitSize(int bit, boolean isSigned)
    {
        if(bit==8 && isSigned)
            return INT1;
        if(bit==16 && isSigned)
            return INT2;
        if(bit==32 && isSigned)
            return INT4;
        if(bit==64 && isSigned)
            return INT8;
        if(bit==8 && !isSigned)
            return UINT1;
        if(bit==16 && !isSigned)
            return UINT2;
        if(bit==32 && !isSigned)
            return UINT4;
        if(bit==64 && !isSigned)
            return UINT8;
        throw new NullPointerException();
    }
    public int getBitSize()
    {
        return this.bit;
    }
    public boolean isSigned()
    {
        return this.isSigned;
    }
    public boolean isVoidType()
    {
        return this.bit==0;
    }
}
