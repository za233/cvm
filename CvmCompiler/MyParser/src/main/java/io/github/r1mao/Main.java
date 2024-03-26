package io.github.r1mao;


import io.github.r1mao.asm.Assembler;
import io.github.r1mao.asm.BinaryBuilder;
import io.github.r1mao.asm.BinaryObject;
import io.github.r1mao.asm.Instruction;
import io.github.r1mao.ir.Function;
import io.github.r1mao.ir.Module;
import org.antlr.v4.runtime.CharStreams;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        FileInputStream fileInputStream=new FileInputStream(args[0]);
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer=new byte[10240];
        int length;
        while ((length=fileInputStream.read(buffer))!=-1)
            result.write(buffer,0,length);

        String code=result.toString("UTF-8");
        System.out.println("[+] reading code string:\n");
        System.out.println(code);
        System.out.println("[+] compile to ir....\n");
        Compiler compiler=new Compiler(args[0],code);
        Module module=compiler.compileToIr();
        System.out.println(module.dump());
        System.out.println("[!] Done!");
        System.out.println("[+] compile to binary:\n");
        BinaryBuilder builder=new BinaryBuilder(module);
        builder.assemble();
        for(BinaryObject binaryObject:builder.getObjects())
            System.out.println(binaryObject.dump());
        System.out.println("[!] Done!");
        FileOutputStream fileOutputStream=new FileOutputStream(args[1]);
        System.out.println("[+] print into file:\n");
        byte[] data=builder.build();
        fileOutputStream.write(data);
        fileOutputStream.close();
        System.out.println("[!] Done!");
    }
}
