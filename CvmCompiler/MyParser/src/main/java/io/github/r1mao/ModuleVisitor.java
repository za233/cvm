package io.github.r1mao;

import io.github.r1mao.ir.core.ModuleBuilder;
import io.github.r1mao.ir.Module;
import io.github.r1mao.parser.ClikeBaseListener;
import io.github.r1mao.parser.ClikeParser;

public class ModuleVisitor extends ClikeBaseListener
{
    public String moduleName;
    public Module module;
    private boolean visited=false;
    public ModuleVisitor(String moduleName)
    {
        this.moduleName=moduleName;
        this.module=new Module(this.moduleName);
    }
    public Module getModule()
    {
        assert this.visited;
        return this.module;
    }
    @Override
    public void enterModule(ClikeParser.ModuleContext ctx)
    {
        this.visited=true;
        ModuleBuilder builder=new ModuleBuilder(module,ctx);
        builder.process();
    }



}
