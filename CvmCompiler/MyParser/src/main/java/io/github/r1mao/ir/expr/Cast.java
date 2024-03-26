package io.github.r1mao.ir.expr;

import io.github.r1mao.symbol.Type;

import java.util.List;

public class Cast extends Expression
{
    private Expression src;
    private Type castTo;

    public Cast(Type castTo, Expression src)
    {
        super(castTo);
        this.castTo=castTo;
        this.src=src;
        assert !castTo.isArray() && !src.getType().isArray() : "CastOp can't cast the expression into an array";
    }
    public Expression getSrc()
    {
        return this.src;
    }
    public Type getCastTo()
    {
        return this.castTo;
    }
    @Override
    public List<Expression> getSubExpressions()
    {
        return List.of(this.src);
    }

    @Override
    public String dump()
    {
        return "("+castTo.toString()+") "+src.dump();
    }
}
