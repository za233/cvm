package io.github.r1mao.ir.core;

import io.github.r1mao.ir.Function;

public abstract class FunctionProcessor
{
    protected Function function;
    public FunctionProcessor(Function function)
    {
        this.function=function;
    }
    public abstract void process();
}
