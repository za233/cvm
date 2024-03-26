package io.github.r1mao.ir;

import io.github.r1mao.ir.expr.Expression;
import io.github.r1mao.ir.stmt.Alloc;
import io.github.r1mao.ir.stmt.Statement;
import io.github.r1mao.model.Edge;
import io.github.r1mao.model.Graph;
import io.github.r1mao.parser.ClikeParser;
import io.github.r1mao.symbol.SymbolFunction;
import io.github.r1mao.symbol.SymbolVariable;

import java.util.ArrayList;
import java.util.List;


public class Function extends Graph<BasicBlock, Pair<Expression,Integer>> implements Dumpable
{
    private Module parent;
    private SymbolFunction functionSymbol;
    private ArrayList<Argument> args=new ArrayList<>();

    public ArrayList<Argument> getArgs()
    {
        return this.args;
    }
    public String getName()
    {
        return this.functionSymbol.getName();
    }
    public Function(Module parent, ClikeParser.FunctionDefineContext functionDefineContext)
    {
        this.parent=parent;
        this.functionSymbol=SymbolFunction.getSymbolByAst(functionDefineContext);
        List<SymbolVariable> list=this.functionSymbol.getArguments();
        for(SymbolVariable var:list)
        {
            Argument argument=new Argument(var.getName(), var.getType(), this);
            this.args.add(argument);
        }

    }
    public ArrayList<Alloc> getAlloc()
    {
        ArrayList<Alloc> vars=new ArrayList<>();
        for(BasicBlock bb:this)
        {
            for(Statement stmt:bb)
            {
                if(stmt instanceof Alloc)
                    vars.add((Alloc) stmt);
            }
        }
        return vars;

    }
    public BasicBlock newBasicBlock()
    {
        BasicBlock bb=new BasicBlock(this);
        this.addNode(bb);
        return bb;
    }

    public BasicBlock newBasicBlock(String debugName)
    {
        BasicBlock bb=new BasicBlock(this,debugName);
        this.addNode(bb);
        return bb;
    }
    public Module getParent()
    {
        return this.parent;
    }
    public SymbolFunction getSymbol()
    {
        return this.functionSymbol;
    }
    protected void addEdge(BasicBlock from, BasicBlock to, Pair<Expression,Integer> data)
    {
        super.addEdge(from, to, data);
    }
    public ArrayList<Edge<BasicBlock, Pair<Expression, Integer>>> getEdges(BasicBlock bb)
    {
        return super.getEdges(bb);
    }
    public ArrayList<Edge<BasicBlock, Pair<Expression, Integer>>> getReverseEdges(BasicBlock bb)
    {
        return super.getReverseEdges(bb);
    }
    public void removeNode(BasicBlock node)
    {
        super.removeNode(node);
    }
    public void removeEdges(BasicBlock b)
    {
        super.removeEdges(b);
    }
    public ArrayList<BasicBlock> successors(BasicBlock bb)
    {
        return super.successors(bb);
    }
    public ArrayList<BasicBlock> predecessors(BasicBlock bb)
    {
        return super.predecessors(bb);
    }
    public int indexOf(BasicBlock bb)
    {
        return super.indexOf(bb);
    }

    @Override
    public String dump()
    {
        String str=this.functionSymbol.toString()+" {\n";
        for(BasicBlock bb:this)
            str+=bb.dump();
        str+="}\n";
        return str;
    }
}
