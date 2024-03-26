package io.github.r1mao;

import io.github.r1mao.ir.core.CodeVerifier;
import io.github.r1mao.error.ErrorHandler;
import io.github.r1mao.error.ErrorRecord;
import io.github.r1mao.ir.Module;
import io.github.r1mao.parser.ClikeLexer;
import io.github.r1mao.parser.ClikeParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.util.ArrayList;

public class Compiler
{
    private final String moduleName;
    private final ParseTree ast;
    public Compiler(String moduleName, String text)
    {
        this.moduleName=moduleName;
        ClikeLexer lexer=new ClikeLexer(CharStreams.fromString(text));
        CommonTokenStream tokens=new CommonTokenStream(lexer);
        ClikeParser parser=new ClikeParser(tokens);
        this.ast=parser.module();

    }
    public Module compileToIr()
    {
        ParseTreeWalker walker=new ParseTreeWalker();
        ErrorHandler err=new ErrorHandler();
        CodeVerifier verifier=new CodeVerifier(err);
        walker.walk(verifier,this.ast);
        if(err.getErrors().size()!=0)
        {
            for(ErrorRecord e:err.getErrors())
                System.out.println(e.getText());
            assert false : "symbol check failed";
        }
        else
        {
            ModuleVisitor moduleVisitor=new ModuleVisitor(this.moduleName);
            walker.walk(moduleVisitor,this.ast);
            return moduleVisitor.getModule();
        }
        return null;
    }
}
