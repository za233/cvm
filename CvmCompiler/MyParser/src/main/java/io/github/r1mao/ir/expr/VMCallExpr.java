package io.github.r1mao.ir.expr;

import io.github.r1mao.ir.Function;
import io.github.r1mao.ir.internal.VMFunction;
import io.github.r1mao.symbol.SymbolVariable;

import java.util.ArrayList;
import java.util.List;

public class VMCallExpr extends Expression
{
    private VMFunction function;
    private ArrayList<Expression> arguments=new ArrayList<>();
    private boolean isBuiltin;
    private String importName;
    public VMCallExpr(String prefix, String callName)
    {
        super(VMFunction.getType(prefix,callName));
        this.function=VMFunction.getFunction(prefix,callName);
        this.isBuiltin=false;
        if(this.function==null)
        {
            this.isBuiltin=true;
            this.importName=prefix+":"+callName;
        }
    }
    public boolean isBuiltin()
    {
        return this.isBuiltin;
    }
    public String getImportName()
    {
        return this.importName;
    }
    public VMFunction getFunction()
    {
        return this.function;
    }
    public ArrayList<Expression> getArguments()
    {
        return this.arguments;
    }
    public void addArgument(Expression expr)
    {
        this.arguments.add(expr);
    }
    @Override
    public String dump()
    {
        String str;
        if(this.function!=null)
            str=function.getSymbol().getName()+"(";
        else
            str=this.importName+"(";
        for(Expression args:arguments)
        {
            str+=args.dump();
            str+=", ";
        }
        str+="...)";
        return str;
    }

    @Override
    public List<Expression> getSubExpressions()
    {
        return null;
    }
}
