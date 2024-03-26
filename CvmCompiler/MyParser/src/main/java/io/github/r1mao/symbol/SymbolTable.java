package io.github.r1mao.symbol;

import java.util.*;

public class SymbolTable<T>
{
    private final HashMap<String,Symbol<T>> symbols=new HashMap<>();
    public void defineVariable(String name, Type type, T data)
    {
        assert this.check(name);
        SymbolVariable variable=new SymbolVariable(name,type,data);
        this.symbols.put(name, variable);
    }

    public void defineVariable(String name, Type type)
    {
        assert this.check(name);
        SymbolVariable variable=new SymbolVariable(name,type);
        this.symbols.put(name, variable);
    }
    @Override
    public String toString()
    {
        String s="Table{\n";
        for(Symbol sym:this.symbols.values())
            s+="\t"+sym.toString()+"\n";
        s+="}";
        return s;
    }
    public void defineFunction(String name, Type returnType, ArrayList<Type> argumentType, T data)
    {
        assert this.check(name);
        SymbolFunction function=new SymbolFunction(name,returnType,argumentType, data);
        this.symbols.put(name, function);
    }
    public void defineFunction(String name, Type returnType, ArrayList<Type> argumentType)
    {
        assert this.check(name);
        SymbolFunction function=new SymbolFunction(name,returnType,argumentType);
        this.symbols.put(name, function);
    }
    public boolean check(String name)
    {
        return !this.getNames().contains(name);
    }
    public Symbol<T> getSymbol(String name)
    {
        return this.symbols.get(name);
    }
    public Collection<Symbol<T>> getSymbols()
    {
        return this.symbols.values();
    }
    public Set<String> getNames()
    {
        return this.symbols.keySet();
    }
}
