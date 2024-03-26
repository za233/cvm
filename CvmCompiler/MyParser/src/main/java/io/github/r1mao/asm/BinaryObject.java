package io.github.r1mao.asm;

import io.github.r1mao.ir.Dumpable;

import java.util.ArrayList;

public abstract class BinaryObject implements Dumpable
{
    private int loadBase;
    public abstract int getMemorySize();
    public abstract int getFileSize();
    protected boolean isDynamic;
    protected boolean shouldLoad;
    public BinaryObject(int loadBase,boolean isDynamic, boolean shouldLoad)
    {
        this.loadBase=loadBase;
        this.isDynamic=isDynamic;
        this.shouldLoad=shouldLoad;
    }
    public boolean isDynamic()
    {
        return this.isDynamic;
    }
    public abstract ArrayList<Byte> encode();
    public int getLoadBase()
    {
        return this.loadBase;
    }
    public void setLoadBase(int loadBase)
    {
        this.loadBase=loadBase;
    }
}
