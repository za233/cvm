package io.github.r1mao.symbol;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class SymbolWatcher<T>
{
    private final SymbolTable<T> globals=new SymbolTable();
    private final Stack<SymbolTable<T>> stack=new Stack<>();
    public void push()
    {
        this.stack.push(new SymbolTable());
    }
    public SymbolTable<T> pop()
    {
        return this.stack.pop();
    }
    public ArrayList<Symbol> getAllLocalSymbols()
    {
        ArrayList<Symbol> result=new ArrayList<>();
        for(SymbolTable st:this.getTempSymbols())
            result.addAll(st.getSymbols());
        return result;
    }
    public boolean check(String name)
    {

        if(!this.globals.check(name))
            return false;
        for(SymbolTable symbolTable:this.stack)
        {
            if(!symbolTable.check(name))
                return false;
        }
        return true;
    }
    private SymbolTable<T> getCurrent()
    {
        if(this.stack.size()==0)
            return this.globals;
        return this.stack.lastElement();
    }
    public void addVariable(Type type, String varName)
    {
        assert this.check(varName);
        this.getCurrent().defineVariable(varName,type);
    }
    public void addVariable(Type type, String varName, T data)
    {
        assert this.check(varName);
        this.getCurrent().defineVariable(varName,type, data);
    }
    public void addFunction(Type returnType, String funcName, ArrayList<Type> argumentTypes)
    {
        assert this.check(funcName);
        this.getCurrent().defineFunction(funcName,returnType,argumentTypes);
    }
    public void addFunction(Type returnType, String funcName, ArrayList<Type> argumentTypes, T data)
    {
        assert this.check(funcName);
        this.getCurrent().defineFunction(funcName,returnType,argumentTypes, data);
    }
    public Symbol<T> getSymbol(String name)
    {
        Symbol<T> target=this.getGlobalSymbols().getSymbol(name);
        if(target!=null)
            return target;
        for(SymbolTable st:this.getTempSymbols())
        {
            target=st.getSymbol(name);
            if(target!=null)
                return target;
        }
        return null;
    }
    public SymbolTable<T> getGlobalSymbols()
    {
        return this.globals;
    }
    public List<SymbolTable<T>> getTempSymbols()
    {
        return List.of(this.stack.toArray(new SymbolTable[0]));
    }
    public String toString()
    {
        String s="global:\n"+this.getGlobalSymbols().toString()+"\n";
        for(SymbolTable t:this.getTempSymbols())
            s+=t.toString()+"\n";

        return s;
    }

}
