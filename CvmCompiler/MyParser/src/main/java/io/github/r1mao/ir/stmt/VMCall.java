package io.github.r1mao.ir.stmt;

import io.github.r1mao.ir.BasicBlock;
import io.github.r1mao.ir.expr.CallExpr;
import io.github.r1mao.ir.expr.Expression;
import io.github.r1mao.ir.expr.VMCallExpr;

import java.util.List;

public class VMCall extends Statement
{
    public VMCallExpr call;
    public VMCall(BasicBlock bb, VMCallExpr call)
    {
        super(bb);
        this.call=call;
    }

    @Override
    public String dump()
    {
        return call.dump();
    }
    public VMCallExpr getCall()
    {
        return this.call;
    }
    @Override
    public List<Expression> getExpressions()
    {
        return List.of(call);
    }
}
