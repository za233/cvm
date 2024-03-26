package io.github.r1mao.symbol;

import io.github.r1mao.error.ErrorRecord;
import io.github.r1mao.ir.Function;
import io.github.r1mao.ir.Variable;
import io.github.r1mao.parser.ClikeParser;

import java.util.ArrayList;
import java.util.List;

public class SymbolFunction<T> extends Symbol<T>
{
    private String name;
    private Type returnType;



    private ArrayList<SymbolVariable> arguments;
    private ClikeParser.FunctionDefineContext functionAst;

    public SymbolFunction(String name, Type returnType, ArrayList<SymbolVariable> argumentsType, T data)
    {
        this.returnType=returnType;
        this.name=name;
        this.arguments=argumentsType;
        this.setData(data);
    }
    public SymbolFunction(String name, Type returnType,T data)
    {
        this.returnType=returnType;
        this.name=name;
        this.setData(data);
        this.arguments=new ArrayList<>();
    }
    public SymbolFunction(String name, Type returnType, ArrayList<SymbolVariable> argumentsType)
    {
        this.returnType=returnType;
        this.name=name;
        this.arguments=argumentsType;
    }
    public SymbolFunction(String name, Type returnType)
    {
        this.returnType=returnType;
        this.name=name;
        this.arguments=new ArrayList<>();
    }

    public ArrayList<SymbolVariable> getArguments()
    {
        return arguments;
    }

    public static SymbolFunction getSymbolByAst(ClikeParser.FunctionDefineContext ctx)
    {
        ArrayList<SymbolVariable> args=new ArrayList<>();
        for(int i=0;i<ctx.atomDefine().size();i++)
        {
            Type type;
            String varName;
            ClikeParser.DefineTypesContext defineTypesContext=ctx.defineTypes(i);
            BasicType baseType=BasicType.fromString(defineTypesContext.getText());
            ClikeParser.AtomDefineContext atomDefineContext=ctx.atomDefine(i);
            if(atomDefineContext.arrDefine()!=null)
            {
                ClikeParser.ArrDefineContext arrDefineContext=atomDefineContext.arrDefine();
                int value=0;
                if(arrDefineContext.constValue()!=null)
                    value=Integer.valueOf(arrDefineContext.constValue().getText());
                varName=arrDefineContext.var().getText();
                type= Type.getArrayType(baseType,value);
                assert arrDefineContext.arrInitializer()==null : "arguments cannot have initializers";
            }
            else
            {
                ClikeParser.VarDefineContext varDefineContext=atomDefineContext.varDefine();
                varName=varDefineContext.var().getText();
                type= Type.getPrimitiveType(baseType);
                assert varDefineContext.varInitializer()==null : "arguments cannot have initializers";
            }
            args.add(new SymbolVariable(varName,type));
        }
        return new SymbolFunction(ctx.function().getText(), Type.getPrimitiveType(BasicType.fromString(ctx.returnTypes().getText())), args);
    }
    public Type getReturnType()
    {
        return this.returnType;
    }
    public String toString()
    {
        String s=this.returnType.toString()+" "+this.name+"(";
        for(SymbolVariable t:this.arguments)
            s+=t.toString()+" ";
        s+=")";
        return s;

    }
    public String getName()
    {
        return this.name;
    }
    public SymbolFunction<T> addArgument(SymbolVariable arg)
    {
        this.arguments.add(arg);
        return this;
    }
    public SymbolVariable getArgument(int ptr)
    {
        return this.getArguments().get(ptr);
    }
}
