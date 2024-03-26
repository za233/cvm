package io.github.r1mao.ir.stmt;

import io.github.r1mao.ir.BasicBlock;
import io.github.r1mao.ir.Dumpable;
import io.github.r1mao.ir.expr.Expression;

import java.util.List;

public abstract class Statement implements Dumpable
{
    private BasicBlock parent;
    public abstract List<Expression> getExpressions();
    public boolean isTerminator()
    {
        return this instanceof Branch || this instanceof Return;
    }
    public Statement(BasicBlock bb)
    {
        this.parent=bb;
    }
}
