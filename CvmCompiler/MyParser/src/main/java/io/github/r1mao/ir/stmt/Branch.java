package io.github.r1mao.ir.stmt;

import io.github.r1mao.ir.BasicBlock;
import io.github.r1mao.ir.expr.Expression;

import java.util.ArrayList;
import java.util.List;

public class Branch extends Statement
{
    private Expression condition=null;
    private BasicBlock trueTarget, falseTarget;
    public Branch(BasicBlock bb,BasicBlock target)
    {
        super(bb);
        this.trueTarget=target;
    }
    public Branch(BasicBlock bb,Expression condition, BasicBlock ifTrue, BasicBlock ifFalse)
    {
        super(bb);
        this.condition=condition;
        this.trueTarget=ifTrue;
        this.falseTarget=ifFalse;
    }
    public BasicBlock getTrueBlock()
    {
        return this.trueTarget;
    }
    public BasicBlock getFalseBlock()
    {
        return this.falseTarget;
    }
    public boolean isConditional()
    {
        return this.condition!=null;
    }

    public Expression getCondition()
    {
        return this.condition;
    }
    @Override
    public List<Expression> getExpressions()
    {
        if(condition!=null)
            return List.of(condition);
        return new ArrayList<>();
    }

    @Override
    public String dump()
    {
        if(this.isConditional())
            return "if("+condition.dump()+") { goto "+trueTarget.id()+"; } else { goto "+falseTarget.id()+"; }";
        return "goto "+trueTarget.id()+";";
    }
}
