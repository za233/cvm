package io.github.r1mao.ir.core;

import io.github.r1mao.ir.*;
import io.github.r1mao.ir.Module;
import io.github.r1mao.ir.expr.Expression;
import io.github.r1mao.parser.ClikeParser;
import io.github.r1mao.symbol.BasicType;
import io.github.r1mao.symbol.SymbolWatcher;
import io.github.r1mao.symbol.Type;

import java.util.ArrayList;
import java.util.HashMap;

public class ModuleBuilder
{
    private final Module module;
    private final ClikeParser.ModuleContext ctx;
    private final SymbolWatcher<Variable> symbolWatcher;
    private final ExpressionTranslator translator;
    public ModuleBuilder(Module module, ClikeParser.ModuleContext ctx)
    {
        this.module=module;
        this.ctx=ctx;
        this.symbolWatcher=new SymbolWatcher<>();
        this.translator=new ExpressionTranslator(this.symbolWatcher, module);
    }
    public void process()
    {
        HashMap<Function,ClikeParser.FunctionDefineContext> bodyMap=new HashMap<>();
        for(ClikeParser.ModuleDefineContext moduleDefineContext:this.ctx.moduleDefine())
        {
            if(moduleDefineContext.functionDefine()!=null)
            {
                assert this.module.getFunction(moduleDefineContext.functionDefine().function().getText())==null : "function redefinition";
                Function function=new Function(this.module,moduleDefineContext.functionDefine());
                bodyMap.put(function, moduleDefineContext.functionDefine());
                this.module.addFunction(function);

            }
            else if(moduleDefineContext.globalDefine()!=null)
            {
                ClikeParser.GlobalDefineContext globalDefineContext=moduleDefineContext.globalDefine();
                ClikeParser.DefineTypesContext defineTypesContext=globalDefineContext.defineTypes();
                BasicType baseType=BasicType.fromString(defineTypesContext.getText());
                for(ClikeParser.AtomDefineContext atom:globalDefineContext.atomDefine())
                {
                    Type type;
                    String varName;
                    ArrayList<Expression> initializer=new ArrayList<>();
                    if(atom.varDefine()!=null)
                    {
                        ClikeParser.VarDefineContext varDefineContext=atom.varDefine();
                        type=Type.getPrimitiveType(baseType);
                        varName=varDefineContext.var().getText();
                        if(varDefineContext.varInitializer()!=null)
                            initializer.add(this.translator.getExpression(varDefineContext.varInitializer().expr()));
                    }
                    else if(atom.arrDefine()!=null)
                    {
                        ClikeParser.ArrDefineContext arrDefineContext=atom.arrDefine();
                        type=Type.getArrayType(baseType, Integer.parseInt(atom.arrDefine().constValue().getText()));
                        varName=atom.arrDefine().var().getText();
                        if(arrDefineContext.arrInitializer()!=null)
                        {
                            ClikeParser.ArrInitializerContext arrInitializerContext=arrDefineContext.arrInitializer();
                            for(ClikeParser.ExprContext exprContext:arrInitializerContext.expr())
                                initializer.add(this.translator.getExpression(exprContext));
                        }
                        assert initializer.size()<=type.getArrayLength();
                    }
                    else
                    {
                        //oops
                        type=null;
                        varName=null;
                    }
                    assert type!=null && varName!=null;
                    GlobalVariable globalVariable=new GlobalVariable(varName,type,this.module,initializer);
                    this.module.addGlobalVariable(globalVariable);
                    symbolWatcher.addVariable(type, varName, globalVariable);
                }

            }

            assert moduleDefineContext.globalDefine()!=null || moduleDefineContext.functionDefine()!=null;
        }
        for(Function function:this.module.getAllFunctions())
        {
            FunctionTranslator functionTranslator=new FunctionTranslator(function,bodyMap.get(function),this.translator);
            functionTranslator.process();
            FunctionCFGBuilder functionCFGBuilder=new FunctionCFGBuilder(function);
            functionCFGBuilder.process();
        }
    }

}
