package io.github.r1mao.ir.core;

import io.github.r1mao.ir.BasicBlock;
import io.github.r1mao.ir.Function;
import io.github.r1mao.ir.Pair;
import io.github.r1mao.ir.expr.Expression;
import io.github.r1mao.ir.stmt.Branch;
import io.github.r1mao.ir.stmt.Return;
import io.github.r1mao.ir.stmt.Statement;
import io.github.r1mao.model.Edge;
import io.github.r1mao.symbol.BasicType;

import java.util.ArrayList;


public class FunctionCFGBuilder extends FunctionProcessor
{
    public FunctionCFGBuilder(Function function)
    {
        super(function);
    }

    @Override
    public void process()
    {
        this.removeEmptyBlock();
        this.combineBlocks();
        this.insertBranch();
        this.insertReturn();
        this.removeUnreachable();
        assert this.verify() : "invalid control flow graph";

    }
    public void removeUnreachable()
    {
        ArrayList<BasicBlock> toRemove=new ArrayList<>();
        for(BasicBlock bb:this.function)
        {
            if(bb.getPredecessors().size()==0 && bb.getSuccessors().size()==0 && bb.statementCount()==0)
                toRemove.add(bb);
        }
        for(BasicBlock bb:toRemove)
            this.function.removeNode(bb);
    }
    public void insertReturn()
    {
        if(this.function.getSymbol().getReturnType().getBase().equals(BasicType.VOID))
        {
            for(BasicBlock bb:this.function)
            {
                if(bb.getSuccessors().size()==0)
                {
                    if(bb.statementCount()==0 || !(bb.get(bb.statementCount()-1) instanceof Return))
                        bb.addStatement(new Return(bb,null));
                }
            }
        }
    }
    public void combineBlocks()
    {
        while(true)
        {
            boolean flag=false;
            BasicBlock from=null,to=null;
            for(BasicBlock bb:this.function)
            {
                if(bb.getSuccessors().size()==1)
                {
                    from=bb;
                    to=bb.getSuccessors().get(0);
                    if(to.getPredecessors().size()==1)
                    {
                        flag=true;
                        break;
                    }

                }
            }
            if(!flag)
                break;
            for(Statement stmt:to)
                from.addStatement(stmt);
            for(Edge<BasicBlock, Pair<Expression,Integer>> e:this.function.getEdges(to))
            {
                BasicBlock tmp=e.getTo();
                if(e.getData()!=null)
                    from.addEdge(tmp,e.getData().getKey(),e.getData().getValue());
                else
                    from.addEdge(tmp);
            }
            this.function.removeNode(to);
        }

    }
    public boolean verify()
    {

        for(BasicBlock bb:this.function)
        {
            if(bb.getSuccessors().size()>2)
                return false;
            Statement stmt=bb.getTerminator();
            if(stmt instanceof Branch)
            {
                Branch br=(Branch) stmt;
                if(br.isConditional())
                {
                    if(bb.getSuccessors().size()!=2 || br.getTrueBlock()==null || br.getFalseBlock()==null)
                        return false;
                    for(Edge<BasicBlock,Pair<Expression,Integer>> e:this.function.getEdges(bb))
                    {
                        if(e.getData().getKey()!=br.getCondition())
                            return false;
                        if(e.getData().getValue()==1)
                        {
                            if(e.getTo()!=br.getTrueBlock())
                                return false;
                        }
                        else if(e.getData().getValue()==0)
                        {
                            if(e.getTo()!=br.getFalseBlock())
                                return false;
                        }
                        else
                            return false;
                    }
                }
                else
                {
                    if(bb.getSuccessors().size()!=1)
                        return false;
                    if(bb.getSuccessors().get(0)!=br.getTrueBlock())
                        return false;
                }
            }
            else if(stmt instanceof Return)
            {
                if(bb.getSuccessors().size()!=0)
                    return false;
            }
            else
                return false;

        }
        System.out.println(this.function.dump());
        return true;
    }
    public void removeEmptyBlock()
    {
        while(true)
        {
            BasicBlock emptyBlock=null;
            for(BasicBlock bb:this.function)
            {
                if(bb.statementCount()==0 && bb.getSuccessors().size()==1)
                {
                    emptyBlock=bb;
                    break;
                }
            }
            if(emptyBlock==null)
                break;
            BasicBlock newTarget=emptyBlock.getSuccessors().get(0);
            for(Edge<BasicBlock, Pair<Expression,Integer>> e:this.function.getReverseEdges(emptyBlock))
            {
                BasicBlock from=e.getFrom();
                if(e.getData()!=null)
                    from.addEdge(newTarget,e.getData().getKey(),e.getData().getValue());
                else
                    from.addEdge(newTarget);
            }
            this.function.removeNode(emptyBlock);
        }
    }
    public void insertBranch()
    {
        for(BasicBlock bb:this.function)
        {
            if(bb.statementCount()==1 && bb.getTerminator() instanceof Return)
            {
                bb.clearEdges();
                continue;
            }
            int size=this.function.getEdges(bb).size();
            if(size==1)
            {
                BasicBlock t=this.function.getEdges(bb).get(0).getTo();
                bb.addStatement(new Branch(bb,t));
            }
            else if(size==2)
            {
                Edge<BasicBlock,Pair<Expression,Integer>> t0=this.function.getEdges(bb).get(0);
                Edge<BasicBlock,Pair<Expression,Integer>> t1=this.function.getEdges(bb).get(1);
                Expression cond=t0.getData().getKey();
                BasicBlock t=null,f=null;
                int v0=t0.getData().getValue(),v1=t1.getData().getValue();

                if(v0==v1)
                {
                    throw new NullPointerException();
                }
                else
                {
                    if(v1==1)
                    {
                        t=t1.getTo();
                        f=t0.getTo();
                    }
                    if(v0==1)
                    {
                        t=t0.getTo();
                        f=t1.getTo();
                    }

                    bb.addStatement(new Branch(bb,cond,t,f));
                }

            }

        }
    }

}
