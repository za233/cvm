package io.github.r1mao.ir.stmt;

import io.github.r1mao.ir.BasicBlock;
import io.github.r1mao.ir.expr.CallExpr;
import io.github.r1mao.ir.expr.Expression;

import java.util.List;

public class Call extends Statement
{
    public CallExpr call;
    public Call(BasicBlock bb, CallExpr call)
    {
        super(bb);
        this.call=call;
    }

    @Override
    public String dump()
    {
        return call.dump();
    }
    public CallExpr getCall()
    {
        return this.call;
    }
    @Override
    public List<Expression> getExpressions()
    {
        return List.of(call);
    }
}
