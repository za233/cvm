package io.github.r1mao.ir;

import io.github.r1mao.symbol.Type;

public class Argument implements Variable
{

    private String name;
    private Type type;
    private Function parent;
    public Argument(String name, Type type, Function parent)
    {
        this.name=name;
        this.type=type;
        this.parent=parent;
    }
    public Function getFunction()
    {
        return this.parent;
    }
    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public UsedAs getUseType()
    {
        return UsedAs.FUNCTION_ARGUMENT;
    }

    @Override
    public Type getType()
    {
        return type;
    }
}