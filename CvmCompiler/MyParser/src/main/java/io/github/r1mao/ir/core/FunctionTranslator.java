package io.github.r1mao.ir.core;

import io.github.r1mao.ir.BasicBlock;
import io.github.r1mao.ir.Function;
import io.github.r1mao.ir.Pair;
import io.github.r1mao.ir.Variable;
import io.github.r1mao.ir.expr.*;
import io.github.r1mao.ir.stmt.*;
import io.github.r1mao.parser.ClikeParser;
import io.github.r1mao.symbol.*;

import java.nio.charset.StandardCharsets;
import java.util.*;


public class FunctionTranslator extends FunctionProcessor
{
    private final ClikeParser.FunctionDefineContext ctx;
    private final SymbolWatcher<Variable> symbolWatcher;
    private final ExpressionTranslator translator;
    static class GotoStmtWatcher
    {
        private final HashMap<String, Stack<ArrayList<BasicBlock>>> stack=new HashMap<>();
        public GotoStmtWatcher()
        {
            stack.put("continue", new Stack<>());
            stack.put("break", new Stack<>());
        }
        public void push(String type)
        {
            stack.get(type).push(new ArrayList<>());
        }
        public ArrayList<BasicBlock> top(String type)
        {
            return stack.get(type).lastElement();
        }
        public void add(String type, BasicBlock bb)
        {
            this.top(type).add(bb);
        }

        public void pop(String type)
        {
            stack.get(type).pop();
        }
    }
    private final GotoStmtWatcher gotoStmtWatcher= new GotoStmtWatcher();
    public FunctionTranslator(Function function, ClikeParser.FunctionDefineContext ctx,ExpressionTranslator translator)
    {
        super(function);
        assert this.function.isEmpty() : "the function has been generated";
        this.ctx=ctx;
        this.translator=translator;
        this.symbolWatcher=translator.getSymbolWatcher();
    }
    public void process()
    {
        this.symbolWatcher.push();
        for(Variable v:this.function.getArgs())
            this.symbolWatcher.addVariable(v.getType(),v.getName(),v);
        this.translateStmtBlock(this.ctx.stmtBlock(),this.function.newBasicBlock());
        this.symbolWatcher.pop();
    }


    public BasicBlock processDefineStmt(ClikeParser.DefineStmtContext defineStmtContext, BasicBlock fa)
    {
        ClikeParser.DefineTypesContext defineTypesContext=defineStmtContext.defineTypes();
        BasicType baseType=BasicType.fromString(defineTypesContext.getText());
        BasicBlock body=this.function.newBasicBlock();
        for(ClikeParser.AtomDefineContext atom:defineStmtContext.atomDefine())
        {
            Type type;
            String varName;
            if(atom.varDefine()!=null)
            {
                ClikeParser.VarDefineContext varDefineContext=atom.varDefine();
                type=Type.getPrimitiveType(baseType);
                varName=varDefineContext.var().getText();
                Expression initVal=null;
                if(varDefineContext.varInitializer()!=null)
                    initVal=this.translator.getExpression(varDefineContext.varInitializer().expr());
                Alloc alloc=new Alloc(body, type, varName);
                this.symbolWatcher.addVariable(alloc.getType(), varName, alloc);
                body.addStatement(alloc);
                if(initVal!=null)
                {

                    if(!initVal.getType().isSame(type))
                        initVal=new Cast(type,initVal);
                    body.addStatement(new Assign(body,new VariableValue(alloc),initVal));
                }

            }
            else if(atom.arrDefine()!=null)
            {
                ClikeParser.ArrDefineContext arrDefineContext=atom.arrDefine();
                Type elementType=Type.getPrimitiveType(baseType);
                type=Type.getArrayType(baseType, Integer.parseInt(arrDefineContext.constValue().getText()));
                varName=arrDefineContext.var().getText();
                ArrayList<Expression> initVal=new ArrayList<>();
                if(arrDefineContext.arrInitializer()!=null)
                {
                    ClikeParser.ArrInitializerContext arrInitializerContext=arrDefineContext.arrInitializer();
                    for(ClikeParser.ExprContext exprContext:arrInitializerContext.expr())
                        initVal.add(this.translator.getExpression(exprContext));
                }
                /*else if(arrDefineContext.strInitializer()!=null)
                {
                    ClikeParser.StrInitializerContext strInitializerContext=arrDefineContext.strInitializer();
                    String data=strInitializerContext.string().getText();
                    int size=elementType.getByteSize(),idx=0;
                    long value=0;
                    for(Byte b:data.getBytes(StandardCharsets.US_ASCII))
                    {
                        value=value|(((long)b)<<8*idx);
                        idx++;
                        if(idx==size)
                        {
                            idx=0;
                            initVal.add(new ConstantValue(value,false));
                            value=0;
                        }

                    }
                }*/
                assert initVal.size()<=type.getArrayLength();
                Alloc alloc=new Alloc(body, type, varName);
                this.symbolWatcher.addVariable(alloc.getType(), varName, alloc);
                body.addStatement(alloc);
                long idx=0;
                for(Expression expr:initVal)
                {
                    Expression src=expr;
                    if(!expr.getType().isSame(elementType))
                        src=new Cast(elementType,expr);
                    body.addStatement(new Assign(body,new ArrayGet(alloc,new ConstantValue(idx,false)),src));
                    idx++;
                }
            }
            else
            {
                assert false : "something went wrong..";
            }

        }
        fa.addEdge(body);
        return body;
    }
    public BasicBlock processAssignStmt(ClikeParser.AssignStmtContext assignStmtContext, BasicBlock fa)
    {
        BasicBlock body=this.function.newBasicBlock();
        Expression dst=this.translator.getSymbolValueExpression(assignStmtContext.symbolValue());
        Expression src;
        if(assignStmtContext.op.getText().equals("="))
            src=this.translator.getExpression(assignStmtContext.expr());
        else
        {
            String op=assignStmtContext.op.getText().replaceAll("=","");
            Expression var=this.translator.getSymbolValueExpression(assignStmtContext.symbolValue());
            Expression delta=this.translator.getExpression(assignStmtContext.expr());
            if(!delta.getType().isSame(var.getType()))
                delta=new Cast(var.getType(),delta);
            src=new BinaryOp(op, var, delta);
        }
        if(!src.getType().isSame(dst.getType()))
            src=new Cast(dst.getType(), src);
        body.addStatement(new Assign(body, dst, src));
        fa.addEdge(body);
        return body;
    }
    public BasicBlock processContinueStmt(BasicBlock fa)
    {
        BasicBlock body=this.function.newBasicBlock();
        fa.addEdge(body);
        this.gotoStmtWatcher.add("continue",body);
        return body;
    }
    public BasicBlock processBreakStmt(BasicBlock fa)
    {
        BasicBlock body=this.function.newBasicBlock();
        fa.addEdge(body);
        this.gotoStmtWatcher.add("break",body);
        return body;
    }
    public BasicBlock processWhileStmt(ClikeParser.WhileStmtContext whileStmtContext, BasicBlock fa)
    {
        BasicBlock entry=this.function.newBasicBlock("whileEntry");
        fa.addEdge(entry);

        ClikeParser.ConditionContext conditionContext=whileStmtContext.condition();
        Expression condition=this.translator.getCondition(conditionContext);

        this.gotoStmtWatcher.push("continue");
        this.gotoStmtWatcher.push("break");
        BasicBlock bodyStart=this.function.newBasicBlock("whileBody");
        BasicBlock body=translateStmtBlock(whileStmtContext.stmtBlock(),bodyStart);
        BasicBlock end=this.function.newBasicBlock("whileEnd");

        entry.addEdge(bodyStart,condition,1);
        entry.addEdge(end,condition,0);
        body.addEdge(entry);

        for(BasicBlock bb:this.gotoStmtWatcher.top("continue"))
        {
            bb.clearEdges();
            bb.addEdge(entry);
        }


        for(BasicBlock bb:this.gotoStmtWatcher.top("break"))
        {
            bb.clearEdges();
            bb.addEdge(end);
        }

        //fa.addStatement(new Branch(entry));
        this.gotoStmtWatcher.pop("continue");
        this.gotoStmtWatcher.pop("break");
        return end;
    }
    public BasicBlock processIfStmt(ClikeParser.IfStmtContext ifStmtContext, BasicBlock fa)
    {
        ArrayList<Pair<Expression, ClikeParser.StmtBlockContext>> subStmtBlocks=new ArrayList<>();
        ClikeParser.StmtBlockContext elseStmtBlock=null;
        int ptr=0;
        while(ptr<ifStmtContext.condition().size())
        {
            subStmtBlocks.add(new Pair<>(this.translator.getCondition(ifStmtContext.condition(ptr)), ifStmtContext.stmtBlock(ptr)));
            ptr++;
        }
        if(ptr<ifStmtContext.stmtBlock().size())
            elseStmtBlock=ifStmtContext.stmtBlock(ptr);

        BasicBlock entry=this.function.newBasicBlock("ifEntry");
        fa.addEdge(entry);
        BasicBlock last=entry;
        ArrayList<BasicBlock> jumpToEnd=new ArrayList<>();
        int idx=0;
        for(Pair<Expression, ClikeParser.StmtBlockContext> pair:subStmtBlocks)
        {
            Expression cond=pair.getKey();
            ClikeParser.StmtBlockContext block=pair.getValue();

            BasicBlock condBlock=this.function.newBasicBlock("ifCond"+idx);
            last.addEdge(condBlock);
            BasicBlock stmtStart=this.function.newBasicBlock("ifStmtStart"+idx);
            BasicBlock stmtBlock=this.translateStmtBlock(block, stmtStart);
            condBlock.addEdge(stmtStart,cond,1);
            BasicBlock nextEntry=this.function.newBasicBlock("next"+idx);
            condBlock.addEdge(nextEntry,cond,0);
            jumpToEnd.add(stmtBlock);
            last=nextEntry;
            idx+=1;
        }

        if(elseStmtBlock!=null)
        {
            BasicBlock elseBlock=this.translateStmtBlock(elseStmtBlock,last);
            jumpToEnd.add(elseBlock);
        }
        else
        {
            jumpToEnd.add(last);
        }
        BasicBlock end=this.function.newBasicBlock("ifEnd");
        for(BasicBlock bb:jumpToEnd)
            bb.addEdge(end);
        return end;
    }
    public BasicBlock processForStmt(ClikeParser.ForStmtContext forStmtContext, BasicBlock fa)
    {
        this.gotoStmtWatcher.push("continue");
        this.gotoStmtWatcher.push("break");
        BasicBlock init;
        if(forStmtContext.forInit()!=null)
            init=this.processAssignStmt(forStmtContext.forInit().assignStmt(),fa);
        else
        {
            init=this.function.newBasicBlock("forInit");
            fa.addEdge(init);
        }

        BasicBlock cmp=this.function.newBasicBlock("forCmp");
        BasicBlock bodyStart=this.function.newBasicBlock("forBody");
        BasicBlock body=this.translateStmtBlock(forStmtContext.stmtBlock(), bodyStart);
        BasicBlock updateStart=this.function.newBasicBlock("forUpdate");
        BasicBlock update;



        if(forStmtContext.forUpdate()!=null)
            update=this.processAssignStmt(forStmtContext.forUpdate().assignStmt(),updateStart);
        else
        {
            update=this.function.newBasicBlock();
            updateStart.addEdge(update);
        }
        BasicBlock exit=this.function.newBasicBlock("forExit");
        init.addEdge(cmp);
        Expression cond;
        if(forStmtContext.condition()!=null)
            cond=this.translator.getCondition(forStmtContext.condition());
        else
            cond=new ConstantValue(1,true);
        cmp.addEdge(exit,cond,0);
        cmp.addEdge(bodyStart,cond,1);

        body.addEdge(updateStart);
        update.addEdge(cmp);

        for(BasicBlock bb:this.gotoStmtWatcher.top("continue"))
        {
            bb.clearEdges();
            bb.addEdge(updateStart);
        }


        for(BasicBlock bb:this.gotoStmtWatcher.top("break"))
        {
            bb.clearEdges();
            bb.addEdge(exit);
        }


        this.gotoStmtWatcher.pop("continue");
        this.gotoStmtWatcher.pop("break");
        return exit;
    }
    public BasicBlock processReturnStmt(ClikeParser.ReturnStmtContext returnStmtContext, BasicBlock fa)
    {
        BasicBlock bb=this.function.newBasicBlock();
        if(returnStmtContext.expr()==null)
            bb.addStatement(new Return(bb,null));
        else
        {
            Expression expr=this.translator.getExpression(returnStmtContext.expr());
            Type returnType=this.function.getSymbol().getReturnType();

            if(!expr.getType().isSame(returnType))
                expr=new Cast(returnType,expr);
            bb.addStatement(new Return(bb,expr));
        }

        fa.addEdge(bb);
        return bb;
    }
    public BasicBlock processCallStmt(ClikeParser.CallStmtContext callStmtContext, BasicBlock fa)
    {
        Expression callExpr=this.translator.getCallExpression(callStmtContext.callExpr());
        BasicBlock bb=this.function.newBasicBlock();
        if(callExpr instanceof CallExpr)
            bb.addStatement(new Call(bb,(CallExpr) callExpr));
        else if(callExpr instanceof VMCallExpr)
            bb.addStatement(new VMCall(bb,(VMCallExpr) callExpr));
        fa.addEdge(bb);
        return bb;
    }
    public BasicBlock translateStmt(ClikeParser.StmtContext stmt, BasicBlock fa)
    {
        if(stmt.defineStmt()!=null)
        {
            ClikeParser.DefineStmtContext defineStmtContext=stmt.defineStmt();
            return this.processDefineStmt(defineStmtContext,fa);
        }
        else if(stmt.assignStmt()!=null)
        {
            ClikeParser.AssignStmtContext assignStmtContext=stmt.assignStmt();
            return this.processAssignStmt(assignStmtContext,fa);
        }
        else if(stmt.continueStmt()!=null)
        {
            return this.processContinueStmt(fa);
        }
        else if(stmt.breakStmt()!=null)
        {
            return this.processBreakStmt(fa);
        }
        else if(stmt.whileStmt()!=null)
        {
            ClikeParser.WhileStmtContext whileStmtContext=stmt.whileStmt();
            return this.processWhileStmt(whileStmtContext,fa);

        }
        else if(stmt.ifStmt()!=null)
        {
            ClikeParser.IfStmtContext ifStmtContext=stmt.ifStmt();
            return this.processIfStmt(ifStmtContext,fa);
        }
        else if(stmt.forStmt()!=null)
        {
            ClikeParser.ForStmtContext forStmtContext=stmt.forStmt();
            return this.processForStmt(forStmtContext,fa);
        }
        else if(stmt.returnStmt()!=null)
        {
            ClikeParser.ReturnStmtContext returnStmtContext=stmt.returnStmt();
            return this.processReturnStmt(returnStmtContext,fa);
        }
        else if(stmt.callStmt()!=null)
        {
            ClikeParser.CallStmtContext callStmtContext=stmt.callStmt();
            return this.processCallStmt(callStmtContext,fa);
        }
        return null;
    }




    public BasicBlock translateStmtBlock(ClikeParser.StmtBlockContext ctx, BasicBlock fa)
    {
        this.symbolWatcher.push();
        BasicBlock prev=fa;
        for(ClikeParser.StmtContext stmt:ctx.stmt())
            prev=translateStmt(stmt, prev);
        this.symbolWatcher.pop();
        return prev;
    }
}
