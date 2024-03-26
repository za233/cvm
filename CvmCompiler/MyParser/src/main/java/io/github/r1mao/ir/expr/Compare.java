package io.github.r1mao.ir.expr;

import io.github.r1mao.symbol.BasicType;
import io.github.r1mao.symbol.Type;

import java.util.List;

public class Compare extends Expression
{
    private String cmp;
    private boolean isSigned;
    private Expression left, right;
    public Compare(String cmp, Expression left, Expression right)
    {
        super(Type.getPrimitiveType(BasicType.UINT1));
        assert !left.getType().isArray() && !right.getType().isArray() : "the operands of the CmpOp cannot be arrays";
        assert left.getType().isStrictSame(right.getType()) : "the operands of BinaryOp must be the same type";
        this.cmp=cmp;
        this.left=left;
        this.right=right;
        this.isSigned=this.left.getType().getBase().isSigned() && this.right.getType().getBase().isSigned();
    }
    public Expression getLeft()
    {
        return this.left;
    }
    public Expression getRight()
    {
        return this.right;
    }
    public String getCmp()
    {
        return this.cmp;
    }
    public boolean isSigned()
    {
        return this.isSigned;
    }
    @Override
    public List<Expression> getSubExpressions()
    {

        return List.of(this.left, this.right);
    }

    @Override
    public String dump()
    {
        if(this.isSigned)
            return "Cmp("+left.dump()+", "+cmp+", "+right.dump()+", s)";
        else
            return "Cmp("+left.dump()+", "+cmp+", "+right.dump()+", u)";
    }
}
