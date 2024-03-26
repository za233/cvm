package io.github.r1mao.ir.expr;

import io.github.r1mao.symbol.Type;

import java.util.ArrayList;
import java.util.List;

public abstract class Operation extends Expression
{
    protected String op;
    protected ArrayList<Expression> operands=new ArrayList<>();
    protected Operation(Type type, String op, Expression ...operands)
    {
        super(type);
        this.op=op;
        for(int i=0;i< operands.length;i++)
            this.operands.add(operands[i]);
    }
    public String getOp()
    {
        return this.op;
    }
    protected Expression operand(int index)
    {
        return this.operands.get(index);
    }
    @Override
    public List<Expression> getSubExpressions()
    {
        return this.operands;
    }
}
