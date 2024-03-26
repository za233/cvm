package io.github.r1mao.symbol;

public class SymbolVariable<T> extends Symbol<T>
{
    private Type type;
    private String name;

    public SymbolVariable(String name, Type type)
    {
        this.name=name;
        this.type=type;
    }
    public SymbolVariable(String name, Type type, T data)
    {
        this.name=name;
        this.type=type;
        this.setData(data);
    }
    public String getName()
    {
        return this.name;
    }
    public Type getType()
    {
        return this.type;
    }
    public String toString()
    {
        return this.type.toString()+" "+this.name;
    }


}
