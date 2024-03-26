package io.github.r1mao.ir.stmt;

import io.github.r1mao.ir.BasicBlock;
import io.github.r1mao.ir.expr.Expression;

import java.util.List;

public class Return extends Statement
{
    private Expression returnValue;

    public Return(BasicBlock bb, Expression returnValue)
    {
        super(bb);
        this.returnValue=returnValue;
    }

    @Override
    public List<Expression> getExpressions()
    {
        return List.of(this.returnValue);
    }
    public Expression getReturnValue()
    {
        return this.returnValue;
    }
    @Override
    public String dump()
    {
        if(returnValue!=null)
            return "return "+returnValue.dump()+";";
        else
            return "return;";
    }
}
