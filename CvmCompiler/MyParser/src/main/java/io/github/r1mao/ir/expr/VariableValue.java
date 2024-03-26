package io.github.r1mao.ir.expr;

import io.github.r1mao.ir.Variable;

import java.util.ArrayList;
import java.util.List;

public class VariableValue extends Expression
{
    private Variable variable;
    public VariableValue(Variable variable)
    {
        super(variable.getType());
        this.variable=variable;

    }

    public Variable getVariable()
    {
        return this.variable;
    }
    @Override
    public List<Expression> getSubExpressions()
    {
        return new ArrayList<>();
    }

    @Override
    public String dump()
    {
        return variable.getName();
    }
}
