package io.github.r1mao.model;

public class Edge<M,T>
{
    private T data;
    private M from,to;
    protected Edge(M from,M to,T data)
    {
        this.from=from;
        this.to=to;
        this.data=data;
    }
    public M getFrom()
    {
        return this.from;
    }
    public M getTo()
    {
        return this.to;
    }

    public T getData()
    {
        return this.data;
    }
}
