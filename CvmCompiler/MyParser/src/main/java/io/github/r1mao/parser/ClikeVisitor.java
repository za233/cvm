// Generated from C:/Users/rimao/Desktop/MyParser/src/main/java\Clike.g4 by ANTLR 4.9.1
package io.github.r1mao.parser;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ClikeParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ClikeVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ClikeParser#module}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModule(ClikeParser.ModuleContext ctx);
	/**
	 * Visit a parse tree produced by {@link ClikeParser#moduleDefine}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModuleDefine(ClikeParser.ModuleDefineContext ctx);
	/**
	 * Visit a parse tree produced by {@link ClikeParser#defineTypes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefineTypes(ClikeParser.DefineTypesContext ctx);
	/**
	 * Visit a parse tree produced by {@link ClikeParser#returnTypes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnTypes(ClikeParser.ReturnTypesContext ctx);
	/**
	 * Visit a parse tree produced by {@link ClikeParser#varDefine}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDefine(ClikeParser.VarDefineContext ctx);
	/**
	 * Visit a parse tree produced by {@link ClikeParser#arrDefine}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrDefine(ClikeParser.ArrDefineContext ctx);
	/**
	 * Visit a parse tree produced by {@link ClikeParser#atomDefine}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtomDefine(ClikeParser.AtomDefineContext ctx);
	/**
	 * Visit a parse tree produced by {@link ClikeParser#varInitializer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarInitializer(ClikeParser.VarInitializerContext ctx);
	/**
	 * Visit a parse tree produced by {@link ClikeParser#arrInitializer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrInitializer(ClikeParser.ArrInitializerContext ctx);
	/**
	 * Visit a parse tree produced by {@link ClikeParser#strInitializer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStrInitializer(ClikeParser.StrInitializerContext ctx);
	/**
	 * Visit a parse tree produced by {@link ClikeParser#functionDefine}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionDefine(ClikeParser.FunctionDefineContext ctx);
	/**
	 * Visit a parse tree produced by {@link ClikeParser#globalDefine}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGlobalDefine(ClikeParser.GlobalDefineContext ctx);
	/**
	 * Visit a parse tree produced by {@link ClikeParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmt(ClikeParser.StmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link ClikeParser#stmtBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtBlock(ClikeParser.StmtBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link ClikeParser#defineStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefineStmt(ClikeParser.DefineStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link ClikeParser#assignStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignStmt(ClikeParser.AssignStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link ClikeParser#whileStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileStmt(ClikeParser.WhileStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link ClikeParser#forStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForStmt(ClikeParser.ForStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link ClikeParser#forInit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForInit(ClikeParser.ForInitContext ctx);
	/**
	 * Visit a parse tree produced by {@link ClikeParser#forUpdate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForUpdate(ClikeParser.ForUpdateContext ctx);
	/**
	 * Visit a parse tree produced by {@link ClikeParser#callStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallStmt(ClikeParser.CallStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link ClikeParser#ifStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStmt(ClikeParser.IfStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link ClikeParser#continueStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContinueStmt(ClikeParser.ContinueStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link ClikeParser#breakStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBreakStmt(ClikeParser.BreakStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link ClikeParser#returnStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnStmt(ClikeParser.ReturnStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link ClikeParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondition(ClikeParser.ConditionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ClikeParser#atomCondition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtomCondition(ClikeParser.AtomConditionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ClikeParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(ClikeParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link ClikeParser#callExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallExpr(ClikeParser.CallExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link ClikeParser#callArgs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallArgs(ClikeParser.CallArgsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ClikeParser#varValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarValue(ClikeParser.VarValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link ClikeParser#constValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstValue(ClikeParser.ConstValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link ClikeParser#arrValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrValue(ClikeParser.ArrValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link ClikeParser#symbolValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSymbolValue(ClikeParser.SymbolValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link ClikeParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue(ClikeParser.ValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link ClikeParser#function}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction(ClikeParser.FunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ClikeParser#vm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVm(ClikeParser.VmContext ctx);
	/**
	 * Visit a parse tree produced by {@link ClikeParser#vmCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVmCall(ClikeParser.VmCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link ClikeParser#var}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVar(ClikeParser.VarContext ctx);
	/**
	 * Visit a parse tree produced by {@link ClikeParser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(ClikeParser.NumberContext ctx);
	/**
	 * Visit a parse tree produced by {@link ClikeParser#string}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitString(ClikeParser.StringContext ctx);
}