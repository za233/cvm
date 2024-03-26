package io.github.r1mao.ir;

import io.github.r1mao.ir.expr.Expression;
import io.github.r1mao.ir.stmt.Branch;
import io.github.r1mao.ir.stmt.Statement;
import io.github.r1mao.symbol.SymbolVariable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class BasicBlock implements Iterable<Statement>, Dumpable
{
    private ArrayList<Statement> statements=new ArrayList<>();
    private final Function parent;
    private String debugName="";
    protected  BasicBlock(Function parent,String debugName)
    {
        this.parent=parent;
        this.debugName=debugName;
    }
    protected BasicBlock(Function parent)
    {
        this.parent=parent;
    }
    public void addStatement(Statement stmt)
    {
        this.statements.add(stmt);
    }
    public int statementCount()
    {
        return this.statements.size();
    }
    public ArrayList<BasicBlock> getSuccessors()
    {
        return this.parent.successors(this);
    }
    public ArrayList<BasicBlock> getPredecessors()
    {
        return this.parent.predecessors(this);
    }
    public void addEdge(BasicBlock to)
    {
        this.parent.addEdge(this, to, null);
    }
    public void addEdge(BasicBlock to, Expression condition, Integer condValue)
    {
        this.parent.addEdge(this, to, new Pair(condition, condValue));
    }
    public void clearEdges()
    {
        this.parent.removeEdges(this);
    }
    public String id()
    {
        String name="bb_"+this.parent.indexOf(this);
        if(this.debugName.length()!=0)
            name+="_"+this.debugName;
        return name;
    }
    public Statement getTerminator()
    {
        Statement terminator=this.statements.get(this.statements.size()-1);
        return terminator;
    }
    @Override
    public Iterator<Statement> iterator()
    {
        return this.statements.iterator();

    }
    public Statement get(int index)
    {
        return this.statements.get(index);
    }

    public void insertBeforeTerminator(Statement stmt)
    {
        Statement t=this.getTerminator();
        this.statements.set(this.statements.size()-1, stmt);
        this.statements.add(t);
    }

    @Override
    public void forEach(Consumer<? super Statement> action)
    {
        this.statements.forEach(action);
    }

    @Override
    public Spliterator<Statement> spliterator()
    {
        return this.statements.spliterator();
    }

    @Override
    public String dump()
    {
        String str=this.id()+":\n";
        for(Statement stmt:this)
            str+="\t"+stmt.dump()+"\n";
        return str;
    }
}
