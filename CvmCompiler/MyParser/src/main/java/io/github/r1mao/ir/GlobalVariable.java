package io.github.r1mao.ir;

import io.github.r1mao.ir.expr.Expression;
import io.github.r1mao.symbol.Type;

import java.util.ArrayList;

public class GlobalVariable implements Variable,Dumpable
{
    private String name;
    private Type type;
    private Module parent;
    private ArrayList<Expression> initializer;
    public GlobalVariable(String name,Type type,Module module,ArrayList<Expression> initializer)
    {
        this.name=name;
        this.type=type;
        this.parent=module;
        this.initializer=initializer;
    }
    public ArrayList<Expression> getInitializer()
    {
        return this.initializer;
    }
    @Override
    public String getName()
    {
        return this.name;
    }


    @Override
    public UsedAs getUseType()
    {
        return UsedAs.GLOBAL_VARIABLE;
    }


    public Type getType()
    {
        return this.type;
    }
    public Module getModule()
    {
        return this.parent;
    }

    @Override
    public String dump()
    {
        return this.type.toString()+" "+this.getName()+";\n";
    }
}
