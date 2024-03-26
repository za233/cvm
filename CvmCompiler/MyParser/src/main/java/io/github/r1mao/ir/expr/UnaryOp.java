package io.github.r1mao.ir.expr;

public class UnaryOp extends Operation
{
    public UnaryOp(String op, Expression operand)
    {
        super(operand.getType(), op, operand);
        assert !operand.getType().isArray() : "the operand of the UnaryOp cannot be array";
    }


    public Expression operand()
    {
        return this.operand(0);
    }

    @Override
    public String dump()
    {
        return "UnOp("+op+", "+operand().dump()+")";
    }
}
