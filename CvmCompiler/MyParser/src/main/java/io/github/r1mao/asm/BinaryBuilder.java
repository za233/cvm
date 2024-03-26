package io.github.r1mao.asm;

import io.github.r1mao.ir.Module;

import java.util.ArrayList;

public class BinaryBuilder
{

    private ArrayList<BinaryObject> binaryObjects;
    private int entryPoint;
    private Module module;
    public BinaryBuilder(Module module)
    {
        this.module=module;
    }
    public void assemble()
    {
        Assembler assembler=new Assembler(this.module);
        this.binaryObjects=assembler.assemble(0x10);
        this.entryPoint=assembler.getEntryPoint();
    }
    public ArrayList<BinaryObject> getObjects()
    {
        return this.binaryObjects;
    }
    public byte[] build()
    {

        DataObject header=new DataObject(-1,false);
        header.writeInt(0xdeadbeef);
        header.writeInt(this.binaryObjects.size());
        int maxSpace=0,fileSize=0;
        for(BinaryObject binaryObject:this.binaryObjects)
        {
            maxSpace=Math.max(binaryObject.getLoadBase()+binaryObject.getMemorySize(),maxSpace);
            fileSize+=binaryObject.getFileSize();
        }
        header.writeInt(maxSpace);
        header.writeInt(fileSize);
        header.writeInt(this.entryPoint);
        ArrayList<Byte> sectionData=new ArrayList<>(),result=new ArrayList<>();
        int offset=4*(5+this.binaryObjects.size()*5);

        for(BinaryObject binaryObject:this.binaryObjects)
        {
            ArrayList<Byte> e=binaryObject.encode();
            sectionData.addAll(e);

            header.writeInt(offset);
            offset+=e.size();
            header.writeInt(binaryObject.getLoadBase());
            int attribute=0;
            if(binaryObject instanceof CodeObject)
                attribute|=1;
            else if(binaryObject instanceof DataObject)
            {
                DataObject dataObject= (DataObject) binaryObject;
                if(dataObject.isDynamic())
                    attribute|=2;
                if(dataObject.isStack())
                    attribute|=4;
            }
            else if(binaryObject instanceof ImportObject)
            {
                attribute|=8;
            }
            header.writeInt(attribute);
            header.writeInt(binaryObject.getMemorySize());
            header.writeInt(e.size());
        }
        result.addAll(header.encode());
        result.addAll(sectionData);
        byte data[]=new byte[result.size()];
        int idx=0;
        for(Byte d:result)
            data[idx++]=d;
        return data;
    }

}
