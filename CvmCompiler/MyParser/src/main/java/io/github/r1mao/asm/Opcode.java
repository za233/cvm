package io.github.r1mao.asm;

import io.github.r1mao.ir.Dumpable;

public enum Opcode
{

    // operation stack
    LOAD("load",0,(byte)1),
    STORE("store",-2,(byte)2),
    PUSH_IMM1("push_imm1",1,(byte)3),
    PUSH_IMM2("push_imm2",1,(byte)4),
    PUSH_IMM4("push_imm4",1,(byte)5),
    PUSH_IMM8("push_imm8",1,(byte)6),
    PUSH_VAR("push_var",1,(byte)7),
    PUSH_BP("push_bp",1,(byte)8),
    POP("pop",1,(byte)9),

    // binary operator
    // arithmetic
    ADD("add",-1,(byte)10),
    SUB("sub",-1,(byte)11),
    S_MUL("smul",-1,(byte)12),
    U_MUL("umul",-1,(byte)13),
    S_DIV("sdiv",-1,(byte)14),
    U_DIV("udiv",-1,(byte)15),
    S_MOD("smod",-1,(byte)16),
    U_MOD("umod",-1,(byte)17),
    // bitwise
    AND("and",-1,(byte)18),
    OR("or",-1,(byte)19),
    XOR("xor",-1,(byte)20),
    SHL("shl",-1,(byte)21),
    L_SHR("lshr",-1,(byte)22),
    A_SHR("ashr",-1,(byte)23),
    // unary operator
    NEG("neg",0,(byte)24),
    NOT("not",0,(byte)25),
    S_EXT("sext",0,(byte)26),
    // call
    STORE_ARG("st_arg",0,(byte)27),
    POP_ARG("pop_arg",0,(byte)28),
    CALL("call",1,(byte)29),
    RETURN("ret",-1,(byte)30),
    // br
    BRANCH("br",0,(byte)31),
    GOTO("jmp",0,(byte)32),
    CMP("cmp",-1,(byte)33),

    ALLOC("alloc",0,(byte)34),
    ENTER("enter", 0, (byte) 35),
    LEAVE("leave", 0, (byte) 36),
    VMCALL("vmcall", 1, (byte) 37);
    private final byte opcode;
    private final String displayName;
    private final int stackDelta;
    public int getStackDelta()
    {
        return this.stackDelta;
    }
    public String getName()
    {
        return this.displayName;
    }
    public byte getByte()
    {
        return this.opcode;
    }
    Opcode(String displayName,int stackDelta,byte opcode)
    {
        this.displayName=displayName;
        this.stackDelta=stackDelta;
        this.opcode=opcode;
    }
}
