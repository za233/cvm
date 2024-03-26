package io.github.r1mao.ir.expr;

import io.github.r1mao.ir.Variable;
import io.github.r1mao.symbol.Type;

import java.util.List;

public class ArrayGet extends Expression
{
    private Variable array;
    private Expression index;
    public ArrayGet(Variable array, Expression index)
    {
        super(Type.getPrimitiveType(array.getType().getBase()));
        this.array=array;
        assert this.array.getType().isArray() : "the operand of ArrayGet must be an array";

        if(index instanceof VariableValue)
            assert !((VariableValue)index).getVariable().getType().isArray() : "invalid usage for ArrayGet";

        this.index=index;
    }
    public Expression getIndex()
    {
        return this.index;
    }
    public Variable getArray()
    {
        return this.array;
    }
    @Override
    public List<Expression> getSubExpressions()
    {
        return List.of(index);
    }

    @Override
    public String dump()
    {
        return array.getName()+"["+index.dump()+"]";
    }
}
