package io.github.r1mao.asm;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class ImportObject extends BinaryObject
{
    private ArrayList<String> strings=new ArrayList<>();
    public ImportObject()
    {
        super(-1, true, false);
    }
    public int addString(String str)
    {
        if(strings.contains(str))
            return this.indexOf(str);
        int idx=this.strings.size();
        this.strings.add(str);
        assert idx < 65536;
        return idx;
    }
    public int indexOf(String str)
    {
        return this.strings.indexOf(str);
    }
    @Override
    public int getMemorySize()
    {
        return 0;
    }

    @Override
    public int getFileSize()
    {
        return this.encode().size();
    }

    @Override
    public ArrayList<Byte> encode()
    {
        ArrayList<Byte> result=new ArrayList<>();
        for(String str:this.strings)
        {
            ArrayList<Byte> arrayList=new ArrayList<>();
            for(Byte b:str.getBytes(StandardCharsets.US_ASCII))
                arrayList.add(b);
            assert arrayList.size() < 256;
            result.add((byte)arrayList.size());
            result.addAll(arrayList);
        }
        return result;
    }

    @Override
    public String dump()
    {
        String result="section.strings(base = "+this.getLoadBase()+", size = "+this.getMemorySize()+") {\n";
        for(String str:this.strings)
            result+="\t\""+str+"\",\n";
        result+="}\n";
        return result;
    }
}
