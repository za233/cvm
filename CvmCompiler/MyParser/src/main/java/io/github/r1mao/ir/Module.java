package io.github.r1mao.ir;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class Module implements Dumpable
{
    private final HashMap<String,GlobalVariable> globalVariables=new HashMap<>();
    private final HashMap<String,Function> functions=new HashMap<>();
    private final String moduleName;
    private final ArrayList<Module> importModule=new ArrayList<>();
    public Module(String moduleName)
    {
        this.moduleName=moduleName;

    }

    public Collection<GlobalVariable> getAllGlobalVariable()
    {
        return this.globalVariables.values();
    }
    public GlobalVariable getGlobalVariable(String name)
    {
        return this.globalVariables.get(name);
    }

    public Function getFunction(String functionName)
    {
        return this.functions.get(functionName);
    }

    public void addFunction(Function func)
    {
        this.functions.put(func.getName(),func);
    }
    public void addGlobalVariable(GlobalVariable var)
    {
        this.globalVariables.put(var.getName(),var);
    }
    public Collection<Function> getAllFunctions()
    {
        return this.functions.values();
    }
    @Override
    public String dump()
    {
        String str=this.moduleName+":\n";
        for(String varName:this.globalVariables.keySet())
        {
            GlobalVariable globalVariable=this.globalVariables.get(varName);
            str+=globalVariable.dump()+"\n";
        }
        for(String funcName:this.functions.keySet())
        {
            Function function=this.functions.get(funcName);

            str+=function.dump()+"\n";
        }
        return str;
    }
}
