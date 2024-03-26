package io.github.r1mao.ir.expr;

import io.github.r1mao.ir.Dumpable;
import io.github.r1mao.symbol.Type;

import java.util.List;

public abstract class Expression implements Dumpable
{
    public Type type;
    public Type getType()
    {
        return this.type;
    }
    public Expression(Type type)
    {
        this.type=type;
        if(type==null)
            System.out.println(this.dump());
    }
    public abstract List<Expression> getSubExpressions();

}
