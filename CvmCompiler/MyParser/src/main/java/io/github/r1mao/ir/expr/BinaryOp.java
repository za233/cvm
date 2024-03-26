package io.github.r1mao.ir.expr;

public class BinaryOp extends Operation
{
    public BinaryOp(String op,Expression operand1,Expression operand2)
    {
        super(operand1.getType(), op,operand1,operand2);
        assert operand1.getType().isStrictSame(operand2.getType()) && !operand1.getType().isArray() : "the operands of BinaryOp must be the same type";
    }
    public Expression operand1()
    {
        return this.operand(0);
    }
    public Expression operand2()
    {
        return this.operand(1);
    }

    @Override
    public String dump()
    {
        if(this.getType().getBase().isSigned())
            return "BinOp("+operand1().dump()+", "+op+", "+operand2().dump()+", s)";
        else
            return "BinOp("+operand1().dump()+", "+op+", "+operand2().dump()+", u)";
    }
}
