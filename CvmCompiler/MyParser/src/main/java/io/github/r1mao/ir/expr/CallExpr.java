package io.github.r1mao.ir.expr;

import io.github.r1mao.ir.Function;
import io.github.r1mao.symbol.SymbolVariable;


import java.util.ArrayList;
import java.util.List;

public class CallExpr extends Expression
{
    private Function function;
    private ArrayList<Expression> arguments=new ArrayList<>();
    public CallExpr(Function function)
    {
        super(function.getSymbol().getReturnType());
        this.function=function;

    }
    public Function getFunction()
    {
        return this.function;
    }
    public ArrayList<Expression> getArguments()
    {
        return this.arguments;
    }
    public void addArgument(Expression expr)
    {
        int ptr=this.arguments.size();
        SymbolVariable variable=this.function.getSymbol().getArgument(ptr);
        if(!variable.getType().isSame(expr.getType()))
            this.arguments.add(new Cast(variable.getType(), expr));
        else
            this.arguments.add(expr);
    }
    @Override
    public List<Expression> getSubExpressions()
    {
        return this.arguments;
    }

    @Override
    public String dump()
    {

        String str=function.getSymbol().getName()+"(";
        for(Expression args:arguments)
        {
            str+=args.dump();
            str+=", ";
        }
        str+="...)";
        return str;
    }
}
