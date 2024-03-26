package io.github.r1mao.ir.stmt;

import io.github.r1mao.ir.BasicBlock;
import io.github.r1mao.ir.UsedAs;
import io.github.r1mao.ir.Variable;
import io.github.r1mao.ir.expr.Expression;
import io.github.r1mao.symbol.Type;

import java.util.List;

public class Alloc extends Statement implements Variable
{
    private String name;
    private Type type;
    public Alloc(BasicBlock bb,Type type, String name)
    {
        super(bb);
        this.type=type;
        this.name=name;
    }
    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public UsedAs getUseType()
    {
        return UsedAs.LOCAL_VARIABLE;
    }


    public Type getType()
    {
        return this.type;
    }

    @Override
    public List<Expression> getExpressions()
    {
        return null;
    }

    @Override
    public String dump()
    {
        return name+" = alloc("+type.toString()+");";
    }
}
