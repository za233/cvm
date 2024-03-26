package io.github.r1mao.ir.expr;

import io.github.r1mao.symbol.BasicType;
import io.github.r1mao.symbol.Type;

import java.util.ArrayList;
import java.util.List;

public class ConstantValue extends Expression
{
    private long value;
    private boolean isUnsigned;
    public ConstantValue(long value,boolean isUnsigned)
    {
        super(ConstantValue.getType(isUnsigned));
        this.value=value;
        this.isUnsigned=isUnsigned;
    }
    public static Type getType(boolean flag)
    {
        if(!flag)
            return Type.getPrimitiveType(BasicType.INT8);
        else
            return Type.getPrimitiveType(BasicType.UINT8);

    }
    public long getValue()
    {
        return this.value;
    }
    @Override
    public List<Expression> getSubExpressions()
    {
        return new ArrayList<>();
    }

    @Override
    public String dump()
    {
        if(isUnsigned)
            return value +"U";
        else
            return String.valueOf(value);
    }
}
