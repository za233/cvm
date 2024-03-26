package io.github.r1mao.ir.stmt;

public class Free
{
    public Alloc target;
    public Free(Alloc target)
    {
        this.target=target;
    }
}
