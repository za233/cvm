// Generated from C:/Users/rimao/Desktop/MyParser/src/main/java\Clike.g4 by ANTLR 4.9.1
package io.github.r1mao.parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ClikeParser}.
 */
public interface ClikeListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ClikeParser#module}.
	 * @param ctx the parse tree
	 */
	void enterModule(ClikeParser.ModuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClikeParser#module}.
	 * @param ctx the parse tree
	 */
	void exitModule(ClikeParser.ModuleContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClikeParser#moduleDefine}.
	 * @param ctx the parse tree
	 */
	void enterModuleDefine(ClikeParser.ModuleDefineContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClikeParser#moduleDefine}.
	 * @param ctx the parse tree
	 */
	void exitModuleDefine(ClikeParser.ModuleDefineContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClikeParser#defineTypes}.
	 * @param ctx the parse tree
	 */
	void enterDefineTypes(ClikeParser.DefineTypesContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClikeParser#defineTypes}.
	 * @param ctx the parse tree
	 */
	void exitDefineTypes(ClikeParser.DefineTypesContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClikeParser#returnTypes}.
	 * @param ctx the parse tree
	 */
	void enterReturnTypes(ClikeParser.ReturnTypesContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClikeParser#returnTypes}.
	 * @param ctx the parse tree
	 */
	void exitReturnTypes(ClikeParser.ReturnTypesContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClikeParser#varDefine}.
	 * @param ctx the parse tree
	 */
	void enterVarDefine(ClikeParser.VarDefineContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClikeParser#varDefine}.
	 * @param ctx the parse tree
	 */
	void exitVarDefine(ClikeParser.VarDefineContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClikeParser#arrDefine}.
	 * @param ctx the parse tree
	 */
	void enterArrDefine(ClikeParser.ArrDefineContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClikeParser#arrDefine}.
	 * @param ctx the parse tree
	 */
	void exitArrDefine(ClikeParser.ArrDefineContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClikeParser#atomDefine}.
	 * @param ctx the parse tree
	 */
	void enterAtomDefine(ClikeParser.AtomDefineContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClikeParser#atomDefine}.
	 * @param ctx the parse tree
	 */
	void exitAtomDefine(ClikeParser.AtomDefineContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClikeParser#varInitializer}.
	 * @param ctx the parse tree
	 */
	void enterVarInitializer(ClikeParser.VarInitializerContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClikeParser#varInitializer}.
	 * @param ctx the parse tree
	 */
	void exitVarInitializer(ClikeParser.VarInitializerContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClikeParser#arrInitializer}.
	 * @param ctx the parse tree
	 */
	void enterArrInitializer(ClikeParser.ArrInitializerContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClikeParser#arrInitializer}.
	 * @param ctx the parse tree
	 */
	void exitArrInitializer(ClikeParser.ArrInitializerContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClikeParser#strInitializer}.
	 * @param ctx the parse tree
	 */
	void enterStrInitializer(ClikeParser.StrInitializerContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClikeParser#strInitializer}.
	 * @param ctx the parse tree
	 */
	void exitStrInitializer(ClikeParser.StrInitializerContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClikeParser#functionDefine}.
	 * @param ctx the parse tree
	 */
	void enterFunctionDefine(ClikeParser.FunctionDefineContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClikeParser#functionDefine}.
	 * @param ctx the parse tree
	 */
	void exitFunctionDefine(ClikeParser.FunctionDefineContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClikeParser#globalDefine}.
	 * @param ctx the parse tree
	 */
	void enterGlobalDefine(ClikeParser.GlobalDefineContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClikeParser#globalDefine}.
	 * @param ctx the parse tree
	 */
	void exitGlobalDefine(ClikeParser.GlobalDefineContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClikeParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterStmt(ClikeParser.StmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClikeParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitStmt(ClikeParser.StmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClikeParser#stmtBlock}.
	 * @param ctx the parse tree
	 */
	void enterStmtBlock(ClikeParser.StmtBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClikeParser#stmtBlock}.
	 * @param ctx the parse tree
	 */
	void exitStmtBlock(ClikeParser.StmtBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClikeParser#defineStmt}.
	 * @param ctx the parse tree
	 */
	void enterDefineStmt(ClikeParser.DefineStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClikeParser#defineStmt}.
	 * @param ctx the parse tree
	 */
	void exitDefineStmt(ClikeParser.DefineStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClikeParser#assignStmt}.
	 * @param ctx the parse tree
	 */
	void enterAssignStmt(ClikeParser.AssignStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClikeParser#assignStmt}.
	 * @param ctx the parse tree
	 */
	void exitAssignStmt(ClikeParser.AssignStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClikeParser#whileStmt}.
	 * @param ctx the parse tree
	 */
	void enterWhileStmt(ClikeParser.WhileStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClikeParser#whileStmt}.
	 * @param ctx the parse tree
	 */
	void exitWhileStmt(ClikeParser.WhileStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClikeParser#forStmt}.
	 * @param ctx the parse tree
	 */
	void enterForStmt(ClikeParser.ForStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClikeParser#forStmt}.
	 * @param ctx the parse tree
	 */
	void exitForStmt(ClikeParser.ForStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClikeParser#forInit}.
	 * @param ctx the parse tree
	 */
	void enterForInit(ClikeParser.ForInitContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClikeParser#forInit}.
	 * @param ctx the parse tree
	 */
	void exitForInit(ClikeParser.ForInitContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClikeParser#forUpdate}.
	 * @param ctx the parse tree
	 */
	void enterForUpdate(ClikeParser.ForUpdateContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClikeParser#forUpdate}.
	 * @param ctx the parse tree
	 */
	void exitForUpdate(ClikeParser.ForUpdateContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClikeParser#callStmt}.
	 * @param ctx the parse tree
	 */
	void enterCallStmt(ClikeParser.CallStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClikeParser#callStmt}.
	 * @param ctx the parse tree
	 */
	void exitCallStmt(ClikeParser.CallStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClikeParser#ifStmt}.
	 * @param ctx the parse tree
	 */
	void enterIfStmt(ClikeParser.IfStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClikeParser#ifStmt}.
	 * @param ctx the parse tree
	 */
	void exitIfStmt(ClikeParser.IfStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClikeParser#continueStmt}.
	 * @param ctx the parse tree
	 */
	void enterContinueStmt(ClikeParser.ContinueStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClikeParser#continueStmt}.
	 * @param ctx the parse tree
	 */
	void exitContinueStmt(ClikeParser.ContinueStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClikeParser#breakStmt}.
	 * @param ctx the parse tree
	 */
	void enterBreakStmt(ClikeParser.BreakStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClikeParser#breakStmt}.
	 * @param ctx the parse tree
	 */
	void exitBreakStmt(ClikeParser.BreakStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClikeParser#returnStmt}.
	 * @param ctx the parse tree
	 */
	void enterReturnStmt(ClikeParser.ReturnStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClikeParser#returnStmt}.
	 * @param ctx the parse tree
	 */
	void exitReturnStmt(ClikeParser.ReturnStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClikeParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterCondition(ClikeParser.ConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClikeParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitCondition(ClikeParser.ConditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClikeParser#atomCondition}.
	 * @param ctx the parse tree
	 */
	void enterAtomCondition(ClikeParser.AtomConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClikeParser#atomCondition}.
	 * @param ctx the parse tree
	 */
	void exitAtomCondition(ClikeParser.AtomConditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClikeParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(ClikeParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClikeParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(ClikeParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClikeParser#callExpr}.
	 * @param ctx the parse tree
	 */
	void enterCallExpr(ClikeParser.CallExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClikeParser#callExpr}.
	 * @param ctx the parse tree
	 */
	void exitCallExpr(ClikeParser.CallExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClikeParser#callArgs}.
	 * @param ctx the parse tree
	 */
	void enterCallArgs(ClikeParser.CallArgsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClikeParser#callArgs}.
	 * @param ctx the parse tree
	 */
	void exitCallArgs(ClikeParser.CallArgsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClikeParser#varValue}.
	 * @param ctx the parse tree
	 */
	void enterVarValue(ClikeParser.VarValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClikeParser#varValue}.
	 * @param ctx the parse tree
	 */
	void exitVarValue(ClikeParser.VarValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClikeParser#constValue}.
	 * @param ctx the parse tree
	 */
	void enterConstValue(ClikeParser.ConstValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClikeParser#constValue}.
	 * @param ctx the parse tree
	 */
	void exitConstValue(ClikeParser.ConstValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClikeParser#arrValue}.
	 * @param ctx the parse tree
	 */
	void enterArrValue(ClikeParser.ArrValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClikeParser#arrValue}.
	 * @param ctx the parse tree
	 */
	void exitArrValue(ClikeParser.ArrValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClikeParser#symbolValue}.
	 * @param ctx the parse tree
	 */
	void enterSymbolValue(ClikeParser.SymbolValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClikeParser#symbolValue}.
	 * @param ctx the parse tree
	 */
	void exitSymbolValue(ClikeParser.SymbolValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClikeParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(ClikeParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClikeParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(ClikeParser.ValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClikeParser#function}.
	 * @param ctx the parse tree
	 */
	void enterFunction(ClikeParser.FunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClikeParser#function}.
	 * @param ctx the parse tree
	 */
	void exitFunction(ClikeParser.FunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClikeParser#vm}.
	 * @param ctx the parse tree
	 */
	void enterVm(ClikeParser.VmContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClikeParser#vm}.
	 * @param ctx the parse tree
	 */
	void exitVm(ClikeParser.VmContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClikeParser#vmCall}.
	 * @param ctx the parse tree
	 */
	void enterVmCall(ClikeParser.VmCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClikeParser#vmCall}.
	 * @param ctx the parse tree
	 */
	void exitVmCall(ClikeParser.VmCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClikeParser#var}.
	 * @param ctx the parse tree
	 */
	void enterVar(ClikeParser.VarContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClikeParser#var}.
	 * @param ctx the parse tree
	 */
	void exitVar(ClikeParser.VarContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClikeParser#number}.
	 * @param ctx the parse tree
	 */
	void enterNumber(ClikeParser.NumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClikeParser#number}.
	 * @param ctx the parse tree
	 */
	void exitNumber(ClikeParser.NumberContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClikeParser#string}.
	 * @param ctx the parse tree
	 */
	void enterString(ClikeParser.StringContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClikeParser#string}.
	 * @param ctx the parse tree
	 */
	void exitString(ClikeParser.StringContext ctx);
}