package io.github.r1mao.asm;

import io.github.r1mao.ir.GlobalVariable;
import io.github.r1mao.ir.Variable;
import io.github.r1mao.ir.expr.ConstantValue;
import io.github.r1mao.ir.expr.Expression;
import io.github.r1mao.symbol.BasicType;
import io.github.r1mao.symbol.Type;

import java.util.ArrayList;
import java.util.HashMap;

public class DataObject extends BinaryObject
{
    private ArrayList<Byte> rawData=new ArrayList<>();
    private HashMap<Variable,Integer> offsetMap=new HashMap<>();
    private int allocator=0;
    private boolean isStack=false;
    public DataObject(int loadBase,boolean isDynamic)
    {
        super(loadBase,isDynamic,true);

    }
    public boolean isStack()
    {
        return this.isStack;
    }
    public void setStack()
    {
        assert this.isDynamic : "only dynamic space can be used as stack";
        this.isStack=true;
    }
    public void setData(int index,ArrayList<Byte> toWrite)
    {
        if(!this.isDynamic)
        {
            assert index + toWrite.size() <= this.allocator : "out of bound";
            for(int i=0;i<toWrite.size();i++)
                this.rawData.set(index+i,toWrite.get(i));
        }

    }
    @Override
    public ArrayList<Byte> encode()
    {

        return this.rawData;
    }

    public HashMap<Variable,Integer> getView(int base)
    {
        HashMap<Variable,Integer> view=new HashMap<>();
        for(Variable v:offsetMap.keySet())
            view.put(v,this.offsetMap.get(v)+base);
        return view;
    }
    public int writeByte(byte data)
    {
        if(!this.isDynamic)
            this.rawData.add(data);
        this.allocator+=1;
        return this.allocator-1;
    }
    public int writeShort(short data)
    {
        if(!this.isDynamic)
        {
            for(int i=0;i<2;i++)
                this.rawData.add((byte) ((byte)(data>>(i*8))&0xff));
        }
        this.allocator+=2;
        return this.allocator-2;
    }
    public int writeInt(int data)
    {
        if(!this.isDynamic)
        {
            for(int i=0;i<4;i++)
                this.rawData.add((byte) ((byte)(data>>(i*8))&0xff));
        }
        this.allocator+=4;
        return this.allocator-4;
    }
    public int writeLong(long data)
    {
        if(!this.isDynamic)
        {
            for(int i=0;i<8;i++)
                this.rawData.add((byte) ((byte)(data>>(i*8))&0xff));
        }

        this.allocator+=8;
        return this.allocator-8;
    }
    public void writeZero(int size)
    {
        if(!this.isDynamic)
        {
            for(int i=0;i<size;i++)
                this.writeByte((byte)0);
        }
        else
            this.allocator+=size;

    }
    public long evalConst(Expression expr)
    {
        if(expr instanceof ConstantValue)
            return ((ConstantValue) expr).getValue();
        else
            assert false : "unsupported initializer";
        return 0;
    }
    public void write(BasicType basicType,long value)
    {

        if(basicType.getBitSize()==8)
            this.writeByte((byte)value);
        else if(basicType.getBitSize()==16)
            this.writeShort((short)value);
        else if(basicType.getBitSize()==32)
            this.writeInt((int)value);
        else
            this.writeLong(value);
    }
    public void addVariable(GlobalVariable variable)
    {
        Type type=variable.getType();
        this.offsetMap.put(variable,this.allocator);
        if(type.isArray())
        {
            int length=type.getArrayLength();
            for(int i=0;i<length;i++)
            {
                long value=0;
                if(i<variable.getInitializer().size())
                {
                    Expression expr=variable.getInitializer().get(i);
                    value=this.evalConst(expr);
                }

                this.write(type.getBase(),value);
            }

        }
        else
        {
            long value=0;
            if(variable.getInitializer().size()!=0)
                value=this.evalConst(variable.getInitializer().get(0));
            this.write(type.getBase(),value);
        }

    }
    public String dump()
    {
        String result="section.data(base = "+this.getLoadBase()+", size = "+this.getMemorySize()+") {\n";
        if(!this.isStack)
        {
            for(Variable v:this.offsetMap.keySet())
            {
                result+="\nglobal variable: \n\t.name = "+v.getName()+"\n\t.type = "+v.getType().toString()+"\n\t.addr = 0x"+String.format("%08X",this.getLoadBase()+this.offsetMap.get(v))+"\n";
                if(this.isDynamic)
                    continue;
                result+="\t[";
                for(int i=0;i<v.getType().getByteSize();i++)
                {
                    if(i%16==15)
                        result+="\n\t";
                    result+=String.format("%02X, ",this.rawData.get(this.offsetMap.get(v)+i));

                }
                result+="],\n";
            }
        }
        else
        {
            result+="\tused as a stack memory";
        }
        return result+"\n}";
    }

    @Override
    public int getMemorySize()
    {
        return this.allocator;
    }
    @Override
    public int getFileSize()
    {
        return this.rawData.size();
    }
}
