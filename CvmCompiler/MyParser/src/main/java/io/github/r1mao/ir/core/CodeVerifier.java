package io.github.r1mao.ir.core;

import io.github.r1mao.error.ErrorHandler;
import io.github.r1mao.error.ErrorRecord;
import io.github.r1mao.parser.ClikeBaseListener;
import io.github.r1mao.parser.ClikeParser;
import io.github.r1mao.symbol.*;

import java.util.ArrayList;

public class CodeVerifier extends ClikeBaseListener
{
    private SymbolWatcher sym=new SymbolWatcher();
    private ErrorHandler err;
    private String currentFunc;
    public CodeVerifier(ErrorHandler err)
    {
        this.err=err;
    }
    public ErrorHandler getErrorHandler()
    {
        return this.err;
    }
    @Override
    public void enterFunctionDefine(ClikeParser.FunctionDefineContext ctx)
    {


        this.sym.push();
        Type returnType= Type.getPrimitiveType(BasicType.fromString(ctx.returnTypes().getText()));
        ArrayList<Type> argsType=new ArrayList<>();
        String funcName=ctx.function().getText();
        assert ctx.atomDefine().size()==ctx.defineTypes().size();
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
            }
            else
            {
                ClikeParser.VarDefineContext varDefineContext=atomDefineContext.varDefine();
                varName=varDefineContext.var().getText();
                type= Type.getPrimitiveType(baseType);
            }
            if(this.sym.check(varName))
                this.sym.addVariable(type,varName);
            else
                err.addError(ErrorRecord.RedefineError("variable: "+varName));
            argsType.add(type);
        }
        if(sym.check(funcName))
        {
            this.sym.addFunction(returnType,funcName,argsType);
            this.currentFunc=ctx.function().getText();
        }
        else
            err.addError(ErrorRecord.RedefineError("function: "+funcName));
    }
    @Override
    public void exitFunctionDefine(ClikeParser.FunctionDefineContext ctx)
    {
        this.sym.pop();
    }

    @Override
    public void enterGlobalDefine(ClikeParser.GlobalDefineContext ctx)
    {
        BasicType baseType=BasicType.fromString(ctx.defineTypes().getText());
        for(ClikeParser.AtomDefineContext atomDefineContext:ctx.atomDefine())
        {
            Type type;
            String varName;
            if(atomDefineContext.arrDefine()!=null)
            {
                ClikeParser.ArrDefineContext arrDefineContext=atomDefineContext.arrDefine();
                varName=arrDefineContext.var().getText();
                int value=Integer.valueOf(arrDefineContext.constValue().getText());
                type= Type.getArrayType(baseType,value);
            }
            else
            {
                ClikeParser.VarDefineContext varDefineContext=atomDefineContext.varDefine();
                varName=varDefineContext.var().getText();
                type= Type.getPrimitiveType(baseType);
            }
            if(this.sym.check(varName))
                this.sym.addVariable(type,varName);
            else
            {
                err.addError(ErrorRecord.RedefineError("variable: "+varName));
            }

        }
    }
    @Override
    public void enterStmtBlock(ClikeParser.StmtBlockContext ctx)
    {
        this.sym.push();
    }
    @Override
    public void exitStmtBlock(ClikeParser.StmtBlockContext ctx)
    {
        this.sym.pop();
    }
    @Override
    public void enterDefineStmt(ClikeParser.DefineStmtContext ctx)
    {
        BasicType baseType=BasicType.fromString(ctx.defineTypes().getText());
        for(ClikeParser.AtomDefineContext atomDefineContext:ctx.atomDefine())
        {
            Type type;
            String varName;

            if(atomDefineContext.arrDefine()!=null)
            {
                ClikeParser.ArrDefineContext arrDefineContext=atomDefineContext.arrDefine();
                varName=arrDefineContext.var().getText();
                int value=Integer.valueOf(arrDefineContext.constValue().getText());
                type= Type.getArrayType(baseType,value);
            }
            else
            {
                ClikeParser.VarDefineContext varDefineContext=atomDefineContext.varDefine();
                varName=varDefineContext.var().getText();
                type= Type.getPrimitiveType(baseType);
            }
            if(this.sym.check(varName))
                this.sym.addVariable(type,varName);
            else
                err.addError(ErrorRecord.RedefineError("variable: "+varName));
        }

    }
    @Override
    public void enterReturnStmt(ClikeParser.ReturnStmtContext ctx)
    {
        if(this.currentFunc!=null)
        {
            Symbol symbol=this.sym.getSymbol(this.currentFunc);
            if(symbol instanceof SymbolFunction)
            {
                SymbolFunction f=(SymbolFunction) symbol;
                assert f.getReturnType().isArray()==false;
                if(f.getReturnType().isVoid())
                {
                    if(ctx.expr()!=null)
                        this.err.addError(ErrorRecord.ReturnTypeError("void type function should not have a return value"));
                }
                else
                {
                    if(ctx.expr()==null)
                        this.err.addError(ErrorRecord.ReturnTypeError("return statement missing a return value"));
                }

            }
        }
    }

    @Override
    public void enterSymbolValue(ClikeParser.SymbolValueContext ctx)
    {

        if(ctx.varValue()!=null)
        {
            String varName=ctx.varValue().getText();
            if(this.sym.check(varName))
                err.addError(ErrorRecord.UndefineError("variable: "+varName));
            else
            {
                Symbol symbol=this.sym.getSymbol(varName);
                if(symbol instanceof SymbolFunction)
                    err.addError(ErrorRecord.SymbolTypeError(varName+" should be a variable,not a function"));
                /*else
                {
                    SymbolVariable var= (SymbolVariable) symbol;
                    if(var.getType().isArray())
                        err.addError(ErrorRecord.SymbolTypeError(varName+" should be a common variable,not a array variable"));
                }*/
            }
        }
        else
        {
            String varName=ctx.arrValue().var().getText();
            if(this.sym.check(varName))
                err.addError(ErrorRecord.UndefineError("variable: "+varName));
            else
            {
                Symbol symbol=this.sym.getSymbol(varName);
                if(symbol instanceof SymbolFunction)
                    err.addError(ErrorRecord.SymbolTypeError(varName+" should be a variable,not a function"));
                else
                {
                    SymbolVariable var= (SymbolVariable) symbol;
                    if(!var.getType().isArray())
                        err.addError(ErrorRecord.SymbolTypeError(varName+" should be a array variable,not a common variable"));
                }
            }

        }
    }


}
