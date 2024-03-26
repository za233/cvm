package io.github.r1mao.ir;

import io.github.r1mao.symbol.Type;

public interface Variable
{
    String getName();
    UsedAs getUseType();
    Type getType();
}
