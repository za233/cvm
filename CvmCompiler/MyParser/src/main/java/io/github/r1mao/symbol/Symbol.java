package io.github.r1mao.symbol;

public abstract class Symbol<T>
{
    private T data;
    public T getData()
    {
        return this.data;
    }
    public void setData(T data)
    {
        this.data=data;
    }
    public String getName()
    {
        return null;
    }
}
