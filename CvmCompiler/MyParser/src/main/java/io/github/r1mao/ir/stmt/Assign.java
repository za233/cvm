package io.github.r1mao.ir.stmt;

import io.github.r1mao.ir.BasicBlock;
import io.github.r1mao.ir.expr.Expression;

import java.util.ArrayList;
import java.util.List;

public class Assign extends Statement
{
    private Expression src,dst;
    public Assign(BasicBlock bb,Expression dst, Expression src)
    {
        super(bb);
        this.src=src;
        this.dst=dst;
        assert src.getType().isStrictSame(dst.getType()) && !src.getType().isArray() : "src and dst must be the same type(not array)";
    }
    public Expression getSrc()
    {
        return this.src;
    }
    public Expression getDst()
    {
        return this.dst;
    }
    @Override
    public List<Expression> getExpressions()
    {
        return List.of(src,dst);
    }

    @Override
    public String dump()
    {
        return dst.dump()+" = "+src.dump()+";";
    }
}
