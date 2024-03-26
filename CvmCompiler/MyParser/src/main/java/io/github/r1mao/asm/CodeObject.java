package io.github.r1mao.asm;

import io.github.r1mao.ir.Function;

import java.util.ArrayList;
import java.util.HashMap;

public class CodeObject extends BinaryObject
{
    private ArrayList<Instruction> instructions=new ArrayList<>();
    private HashMap<Function, Instruction> entryMap= new HashMap<>();
    private HashMap<Instruction,Function> revMap=new HashMap<>();
    public CodeObject(int loadBase)
    {
        super(loadBase,false, true);
    }
    public ArrayList<Instruction> getInstructions()
    {
        return this.instructions;
    }
    public HashMap<Function,Integer> getView(int base)
    {
        HashMap<Function,Integer> view=new HashMap<>();
        int offset=0;
        for(Instruction i:this.instructions)
        {
            view.put(revMap.get(i),offset+base);
            offset+=i.getSize();

        }
        return view;
    }
    public HashMap<Instruction, Integer> getInstructionView(int base)
    {
        HashMap<Instruction,Integer> view=new HashMap<>();
        int offset=0;
        for(Instruction instruction:this.instructions)
        {
            view.put(instruction,offset+base);
            offset+=instruction.getSize();
        }
        return view;
    }

    public void addFunction(Function function, ArrayList<Instruction> code)
    {
        instructions.addAll(code);
        this.entryMap.put(function, code.get(0));
        this.revMap.put(code.get(0), function);
    }
    public String dump()
    {
        String result="section.code(offset = "+this.getLoadBase()+", size = "+this.getMemorySize()+") {\n";
        int offset=0;
        for(Instruction instruction:this.getInstructions())
        {
            if(this.revMap.containsKey(instruction))
            {
                result+="\n\n";
                Function function=this.revMap.get(instruction);
                result+=function.getSymbol().toString()+"\n";
            }
            result+=String.format("0x%08X: %d ", offset+this.getLoadBase(), instruction.getSize());
            int remain=27;
            for(Byte data:instruction.encode())
            {
                result+=String.format("%02X ",data);
                remain-=2;
            }
            while(remain>0)
            {
                result+=" ";
                remain--;
            }
            result+="\t"+instruction.dump()+"\n";
            offset+=instruction.getSize();
        }
        return result+"}";
    }
    @Override
    public int getMemorySize()
    {
        int size=0;
        for(Instruction inst:this.instructions)
            size+=inst.getSize();
        return size;
    }

    @Override
    public int getFileSize()
    {
        return this.getMemorySize();
    }

    @Override
    public ArrayList<Byte> encode()
    {
        ArrayList<Byte> data=new ArrayList<>();
        for(Instruction instruction:this.instructions)
            data.addAll(instruction.encode());
        return data;
    }
}
