package io.github.r1mao.ir.internal;


import io.github.r1mao.symbol.BasicType;
import io.github.r1mao.symbol.SymbolFunction;
import io.github.r1mao.symbol.SymbolVariable;
import io.github.r1mao.symbol.Type;

import java.util.HashMap;

public class VMFunction
{
    private final String prefix;
    private final String callName;
    private SymbolFunction functionSymbol;
    private static HashMap<String,HashMap<String,VMFunction>> builtin=new HashMap<>();
    static
    {
        HashMap<String,VMFunction> vm=new HashMap<>();
        builtin.put("vm", vm);
        update("vm","ref",new SymbolFunction("vm.ref",Type.getPrimitiveType(BasicType.UINT8)));
        update("vm","store",new SymbolFunction("vm.store",Type.getPrimitiveType(BasicType.VOID)));
        update("vm","load",new SymbolFunction("vm.load",Type.getPrimitiveType(BasicType.UINT8)));


    }
    public static void update(String prefix,String name,SymbolFunction functionSymbol)
    {
        builtin.get(prefix).put(name,new VMFunction(prefix,name,functionSymbol));
    }
    public VMFunction(String prefix,String name,SymbolFunction function)
    {
        this.prefix=prefix;
        this.callName=name;
        this.functionSymbol=function;
    }
    public String getPrefix()
    {
        return this.prefix;
    }
    public String getCallName()
    {
        return this.callName;
    }
    public static Type getType(String prefix, String callName)
    {
        VMFunction vmFunction=getFunction(prefix, callName);
        if(vmFunction!=null)
            return vmFunction.getSymbol().getReturnType();
        return Type.getPrimitiveType(BasicType.INT8);
    }
    public static VMFunction getFunction(String prefix, String callName)
    {
        if(builtin.containsKey(prefix))
        {
            if(builtin.get(prefix).containsKey(callName))
                return builtin.get(prefix).get(callName);
        }
        return null;
    }
    public String getName()
    {
        return this.functionSymbol.getName();
    }
    public SymbolFunction getSymbol()
    {
        return this.functionSymbol;
    }
}
