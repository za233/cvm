package io.github.r1mao.asm;

import io.github.r1mao.ir.*;
import io.github.r1mao.ir.Module;

import io.github.r1mao.ir.stmt.Alloc;
import io.github.r1mao.ir.stmt.Statement;


import java.util.ArrayList;
import java.util.HashMap;

public class Assembler
{
    private Module module;
    private Function main;
    private int entryPoint;
    private HashMap<Integer,BasicBlock> bMap=new HashMap<>();
    public Assembler(Module module)
    {
        this.module=module;
        for(Function func:this.module.getAllFunctions())
        {
            if(func.getName().equals("main") && func.getSymbol().getArguments().size()==0)
                this.main=func;
        }
        assert this.main!=null : "main function not found.";
    }

    public int getEntryPoint()
    {
        return this.entryPoint;
    }
    private int doAlignment(int data,int align)
    {
        if(data%align==0)
            return data;
        return (data/align+1)*align;
    }
    public ArrayList<BinaryObject> assemble(int align)
    {
        ArrayList<BinaryObject> sections=new ArrayList<>();

        ArrayList<GlobalVariable> rdata=new ArrayList<>();
        ArrayList<GlobalVariable> data=new ArrayList<>();
        for(GlobalVariable gv:this.module.getAllGlobalVariable())
        {
            if(gv.getInitializer().size()>0)
                rdata.add(gv);
            else
                data.add(gv);
        }
        int allocator=0;

        if(rdata.size()!=0)
        {
            DataObject dataObject=new DataObject(allocator,false);
            for(GlobalVariable gv:rdata)
                dataObject.addVariable(gv);
            sections.add(dataObject);
            allocator+=this.doAlignment(dataObject.getMemorySize(),align);
        }
        if(data.size()!=0)
        {
            DataObject dataObject=new DataObject(allocator,true);
            for(GlobalVariable gv:data)
                dataObject.addVariable(gv);
            sections.add(dataObject);
            allocator+=this.doAlignment(dataObject.getMemorySize(),align);
        }
        CodeObject codeObject=new CodeObject(allocator);
        sections.add(codeObject);
        for(Function func:this.module.getAllFunctions())
        {
            bMap.clear();
            ArrayList<ArrayList<Instruction>> code=this.assemble(func);
            for(int i=0;i<code.size();i++)
            {
                for(BinaryObject section:sections)
                {
                    if(section instanceof DataObject)
                    {
                        DataObject dataObject= (DataObject) section;
                        code.set(i,this.fixVarOperand(code.get(i),dataObject.getView(dataObject.getLoadBase())));
                    }
                }
            }


            this.fixBranch(code);
            ArrayList<Instruction> finalCode=new ArrayList<>();
            for(ArrayList<Instruction> bbCode : code)
                finalCode.addAll(bbCode);
            codeObject.addFunction(func,finalCode);
        }
        HashMap<Function,Integer> funcView=codeObject.getView(codeObject.getLoadBase());
        this.entryPoint=funcView.get(this.main);
        System.out.println("entry point: "+this.entryPoint);
        ImportObject importObject =new ImportObject();
        sections.add(importObject);
        HashMap<Instruction,Integer> instView=codeObject.getInstructionView(codeObject.getLoadBase());
        for(Instruction inst:codeObject.getInstructions())
        {
            if(inst.getOpcode().equals(Opcode.CALL))
            {
                Function func= (Function) inst.getOperands().get(0).get();
                int funcAddress=funcView.get(func);
                int instAddress=instView.get(inst);
                inst.getOperands().set(0,Operand.I4(funcAddress-instAddress-5));

            }
            else if(inst.getOpcode().equals(Opcode.VMCALL))
            {
                String name= (String) inst.getOperands().get(0).get();
                int index= importObject.addString(name);
                inst.getOperands().set(0,Operand.I2((short) index));
            }

        }

        allocator+=this.doAlignment(codeObject.getMemorySize(),align);
        DataObject stackSection=new DataObject(allocator, true);
        stackSection.setStack();
        stackSection.writeZero(0x4000);
        sections.add(stackSection);
        return sections;
    }
    public ArrayList<Instruction> fixVarOperand(ArrayList<Instruction> bbInst,HashMap<Variable,Integer> view)
    {

        ArrayList<Instruction> temp=new ArrayList<>();

        for(Instruction inst:bbInst)
        {
            if(inst.getOpcode().equals(Opcode.PUSH_VAR))
            {
                Variable v= (Variable) inst.getOperands().get(0).get();
                if(view.containsKey(v))
                {
                    int offset=view.get(v);
                    boolean isSigned=offset<0;
                    if((offset & 0xffff0000)!=0)
                    {
                        temp.add(Instruction.PushImm(offset));
                        if(isSigned && (offset & 0x80000000)!=0)
                            temp.add(Instruction.SExt(4,8));
                    }

                    else if((offset & 0xff00)!=0)
                    {
                        temp.add(Instruction.PushImm(Short.valueOf((short) offset)));
                        if(isSigned && (offset & 0x8000)!=0)
                            temp.add(Instruction.SExt(2,8));
                    }
                    else
                    {
                        temp.add(Instruction.PushImm(Byte.valueOf((byte) offset)));
                        if(isSigned && (offset & 0x80)!=0)
                            temp.add(Instruction.SExt(1,8));
                    }
                }
                else
                    temp.add(inst);
            }
            else
                temp.add(inst);
        }
        return temp;
    }
    public void fixBranch(ArrayList<ArrayList<Instruction>> body)
    {
        HashMap<BasicBlock,Integer> offsetMap=new HashMap<>();
        HashMap<Instruction,Integer> instMap=new HashMap<>();
        int offset=0, idx=0;
        System.out.println(bMap.keySet());
        for(ArrayList<Instruction> bbCode:body)
        {
            if(bMap.containsKey(idx))
            {
                offsetMap.put(bMap.get(idx), offset);
                System.out.println(bMap.get(idx).dump());
            }
            for(Instruction inst:bbCode)
            {
                instMap.put(inst,offset);
                offset+=inst.getSize();
            }
            idx+=1;
        }
        for(ArrayList<Instruction> bbCode:body)
        {

            for(Instruction inst:bbCode)
            {

                if(inst.getOpcode().equals(Opcode.BRANCH) || inst.getOpcode().equals(Opcode.GOTO))
                {
                    for(Operand op:inst.getOperands())
                    {
                        BasicBlock bb= (BasicBlock) op.get();
                        int delta=offsetMap.get(bb)-instMap.get(inst)-5;
                        inst.getOperands().set(0,Operand.I4(delta));
                    }

                }
            }

        }
    }
    public ArrayList<ArrayList<Instruction>> assemble(Function function)
    {

        ArrayList<ArrayList<Instruction>> body=new ArrayList<>();
        HashMap<Variable, Integer> view= new HashMap<>();
        int argCount=function.getArgs().size(),idx=0;
        for(Argument argument:function.getArgs())
        {
            view.put(argument,8+(argCount-idx)*8);
            idx++;
        }
        int allocated=0;
        for(Alloc alloc:function.getAlloc())
        {
            allocated+=alloc.getType().getByteSize();
            view.put(alloc, -allocated);

        }
        idx=0;
        ArrayList<Instruction> prolog=new ArrayList<>();
        prolog.add(Instruction.Enter());

        if(allocated!=0)
            prolog.add(Instruction.Alloc(allocated));
        body.add(prolog);
        idx+=1;

        AssembleCore assembleCore =new AssembleCore();

        for(BasicBlock bb:function)
        {
            ArrayList<Instruction> bbBody=new ArrayList<>();
            for(Statement stmt:bb)
            {
                System.out.println(stmt.dump());
                int old=assembleCore.getResult().size();
                assembleCore.assemble(stmt);
            }

            ArrayList<Instruction> bbInst= assembleCore.getResult();
            ArrayList<Instruction> temp=this.fixVarOperand(bbInst,view);
            assembleCore.clear();
            bbBody.addAll(temp);
            body.add(bbBody);
            bMap.put(idx,bb);
            idx+=1;
        }


        return body;
    }




}
