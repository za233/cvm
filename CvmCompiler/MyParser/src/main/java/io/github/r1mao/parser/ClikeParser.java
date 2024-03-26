// Generated from C:/Users/rimao/Desktop/MyParser/src/main/java\Clike.g4 by ANTLR 4.9.1
package io.github.r1mao.parser;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ClikeParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, T__37=38, 
		T__38=39, T__39=40, T__40=41, T__41=42, T__42=43, T__43=44, T__44=45, 
		T__45=46, T__46=47, T__47=48, T__48=49, T__49=50, T__50=51, T__51=52, 
		T__52=53, ESC=54, ID=55, NUM=56, WS=57;
	public static final int
		RULE_module = 0, RULE_moduleDefine = 1, RULE_defineTypes = 2, RULE_returnTypes = 3, 
		RULE_varDefine = 4, RULE_arrDefine = 5, RULE_atomDefine = 6, RULE_varInitializer = 7, 
		RULE_arrInitializer = 8, RULE_strInitializer = 9, RULE_functionDefine = 10, 
		RULE_globalDefine = 11, RULE_stmt = 12, RULE_stmtBlock = 13, RULE_defineStmt = 14, 
		RULE_assignStmt = 15, RULE_whileStmt = 16, RULE_forStmt = 17, RULE_forInit = 18, 
		RULE_forUpdate = 19, RULE_callStmt = 20, RULE_ifStmt = 21, RULE_continueStmt = 22, 
		RULE_breakStmt = 23, RULE_returnStmt = 24, RULE_condition = 25, RULE_atomCondition = 26, 
		RULE_expr = 27, RULE_callExpr = 28, RULE_callArgs = 29, RULE_varValue = 30, 
		RULE_constValue = 31, RULE_arrValue = 32, RULE_symbolValue = 33, RULE_value = 34, 
		RULE_function = 35, RULE_vm = 36, RULE_vmCall = 37, RULE_var = 38, RULE_number = 39, 
		RULE_string = 40;
	private static String[] makeRuleNames() {
		return new String[] {
			"module", "moduleDefine", "defineTypes", "returnTypes", "varDefine", 
			"arrDefine", "atomDefine", "varInitializer", "arrInitializer", "strInitializer", 
			"functionDefine", "globalDefine", "stmt", "stmtBlock", "defineStmt", 
			"assignStmt", "whileStmt", "forStmt", "forInit", "forUpdate", "callStmt", 
			"ifStmt", "continueStmt", "breakStmt", "returnStmt", "condition", "atomCondition", 
			"expr", "callExpr", "callArgs", "varValue", "constValue", "arrValue", 
			"symbolValue", "value", "function", "vm", "vmCall", "var", "number", 
			"string"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'unsigned'", "'char'", "'short'", "'int'", "'long'", "'void'", 
			"'['", "']'", "'='", "'{'", "','", "'}'", "'\"'", "'('", "')'", "';'", 
			"'+='", "'-='", "'*='", "'/='", "'&='", "'|='", "'^='", "'<<='", "'>>='", 
			"'while'", "'for'", "'if'", "'else'", "'continue'", "'break'", "'return'", 
			"'&&'", "'||'", "'!'", "'=='", "'!='", "'>='", "'<='", "'>'", "'<'", 
			"'-'", "'~'", "'*'", "'/'", "'%'", "'+'", "'|'", "'&'", "'^'", "'>>'", 
			"'<<'", "'.'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, "ESC", "ID", "NUM", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Clike.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ClikeParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ModuleContext extends ParserRuleContext {
		public List<ModuleDefineContext> moduleDefine() {
			return getRuleContexts(ModuleDefineContext.class);
		}
		public ModuleDefineContext moduleDefine(int i) {
			return getRuleContext(ModuleDefineContext.class,i);
		}
		public ModuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_module; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClikeListener ) ((ClikeListener)listener).enterModule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClikeListener ) ((ClikeListener)listener).exitModule(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ClikeVisitor ) return ((ClikeVisitor<? extends T>)visitor).visitModule(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ModuleContext module() throws RecognitionException {
		ModuleContext _localctx = new ModuleContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_module);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(85);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5))) != 0)) {
				{
				{
				setState(82);
				moduleDefine();
				}
				}
				setState(87);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ModuleDefineContext extends ParserRuleContext {
		public GlobalDefineContext globalDefine() {
			return getRuleContext(GlobalDefineContext.class,0);
		}
		public FunctionDefineContext functionDefine() {
			return getRuleContext(FunctionDefineContext.class,0);
		}
		public ModuleDefineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_moduleDefine; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClikeListener ) ((ClikeListener)listener).enterModuleDefine(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClikeListener ) ((ClikeListener)listener).exitModuleDefine(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ClikeVisitor ) return ((ClikeVisitor<? extends T>)visitor).visitModuleDefine(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ModuleDefineContext moduleDefine() throws RecognitionException {
		ModuleDefineContext _localctx = new ModuleDefineContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_moduleDefine);
		try {
			setState(90);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(88);
				globalDefine();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(89);
				functionDefine();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DefineTypesContext extends ParserRuleContext {
		public DefineTypesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_defineTypes; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClikeListener ) ((ClikeListener)listener).enterDefineTypes(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClikeListener ) ((ClikeListener)listener).exitDefineTypes(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ClikeVisitor ) return ((ClikeVisitor<? extends T>)visitor).visitDefineTypes(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DefineTypesContext defineTypes() throws RecognitionException {
		DefineTypesContext _localctx = new DefineTypesContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_defineTypes);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(94);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
				{
				setState(92);
				match(T__0);
				}
				break;
			case T__1:
			case T__2:
			case T__3:
			case T__4:
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(96);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__2) | (1L << T__3) | (1L << T__4))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ReturnTypesContext extends ParserRuleContext {
		public DefineTypesContext defineTypes() {
			return getRuleContext(DefineTypesContext.class,0);
		}
		public ReturnTypesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnTypes; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClikeListener ) ((ClikeListener)listener).enterReturnTypes(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClikeListener ) ((ClikeListener)listener).exitReturnTypes(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ClikeVisitor ) return ((ClikeVisitor<? extends T>)visitor).visitReturnTypes(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReturnTypesContext returnTypes() throws RecognitionException {
		ReturnTypesContext _localctx = new ReturnTypesContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_returnTypes);
		try {
			setState(100);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
			case T__1:
			case T__2:
			case T__3:
			case T__4:
				enterOuterAlt(_localctx, 1);
				{
				setState(98);
				defineTypes();
				}
				break;
			case T__5:
				enterOuterAlt(_localctx, 2);
				{
				setState(99);
				match(T__5);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarDefineContext extends ParserRuleContext {
		public VarContext var() {
			return getRuleContext(VarContext.class,0);
		}
		public VarInitializerContext varInitializer() {
			return getRuleContext(VarInitializerContext.class,0);
		}
		public VarDefineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varDefine; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClikeListener ) ((ClikeListener)listener).enterVarDefine(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClikeListener ) ((ClikeListener)listener).exitVarDefine(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ClikeVisitor ) return ((ClikeVisitor<? extends T>)visitor).visitVarDefine(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarDefineContext varDefine() throws RecognitionException {
		VarDefineContext _localctx = new VarDefineContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_varDefine);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(102);
			var();
			setState(105);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__8:
				{
				setState(103);
				varInitializer();
				}
				break;
			case T__10:
			case T__14:
			case T__15:
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArrDefineContext extends ParserRuleContext {
		public VarContext var() {
			return getRuleContext(VarContext.class,0);
		}
		public ConstValueContext constValue() {
			return getRuleContext(ConstValueContext.class,0);
		}
		public ArrInitializerContext arrInitializer() {
			return getRuleContext(ArrInitializerContext.class,0);
		}
		public ArrDefineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrDefine; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClikeListener ) ((ClikeListener)listener).enterArrDefine(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClikeListener ) ((ClikeListener)listener).exitArrDefine(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ClikeVisitor ) return ((ClikeVisitor<? extends T>)visitor).visitArrDefine(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrDefineContext arrDefine() throws RecognitionException {
		ArrDefineContext _localctx = new ArrDefineContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_arrDefine);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(107);
			var();
			setState(108);
			match(T__6);
			setState(111);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NUM:
				{
				setState(109);
				constValue();
				}
				break;
			case T__7:
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(113);
			match(T__7);
			setState(116);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__8:
				{
				setState(114);
				arrInitializer();
				}
				break;
			case T__10:
			case T__14:
			case T__15:
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AtomDefineContext extends ParserRuleContext {
		public ArrDefineContext arrDefine() {
			return getRuleContext(ArrDefineContext.class,0);
		}
		public VarDefineContext varDefine() {
			return getRuleContext(VarDefineContext.class,0);
		}
		public AtomDefineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atomDefine; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClikeListener ) ((ClikeListener)listener).enterAtomDefine(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClikeListener ) ((ClikeListener)listener).exitAtomDefine(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ClikeVisitor ) return ((ClikeVisitor<? extends T>)visitor).visitAtomDefine(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AtomDefineContext atomDefine() throws RecognitionException {
		AtomDefineContext _localctx = new AtomDefineContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_atomDefine);
		try {
			setState(120);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(118);
				arrDefine();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(119);
				varDefine();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarInitializerContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public VarInitializerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varInitializer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClikeListener ) ((ClikeListener)listener).enterVarInitializer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClikeListener ) ((ClikeListener)listener).exitVarInitializer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ClikeVisitor ) return ((ClikeVisitor<? extends T>)visitor).visitVarInitializer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarInitializerContext varInitializer() throws RecognitionException {
		VarInitializerContext _localctx = new VarInitializerContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_varInitializer);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(122);
			match(T__8);
			setState(123);
			expr(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArrInitializerContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public ArrInitializerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrInitializer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClikeListener ) ((ClikeListener)listener).enterArrInitializer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClikeListener ) ((ClikeListener)listener).exitArrInitializer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ClikeVisitor ) return ((ClikeVisitor<? extends T>)visitor).visitArrInitializer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrInitializerContext arrInitializer() throws RecognitionException {
		ArrInitializerContext _localctx = new ArrInitializerContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_arrInitializer);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(125);
			match(T__8);
			setState(126);
			match(T__9);
			setState(132);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(127);
					expr(0);
					setState(128);
					match(T__10);
					}
					} 
				}
				setState(134);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			}
			setState(135);
			expr(0);
			setState(136);
			match(T__11);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StrInitializerContext extends ParserRuleContext {
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public StrInitializerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_strInitializer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClikeListener ) ((ClikeListener)listener).enterStrInitializer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClikeListener ) ((ClikeListener)listener).exitStrInitializer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ClikeVisitor ) return ((ClikeVisitor<? extends T>)visitor).visitStrInitializer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StrInitializerContext strInitializer() throws RecognitionException {
		StrInitializerContext _localctx = new StrInitializerContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_strInitializer);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(138);
			match(T__8);
			setState(139);
			match(T__12);
			setState(140);
			string();
			setState(141);
			match(T__12);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionDefineContext extends ParserRuleContext {
		public ReturnTypesContext returnTypes() {
			return getRuleContext(ReturnTypesContext.class,0);
		}
		public FunctionContext function() {
			return getRuleContext(FunctionContext.class,0);
		}
		public StmtBlockContext stmtBlock() {
			return getRuleContext(StmtBlockContext.class,0);
		}
		public List<DefineTypesContext> defineTypes() {
			return getRuleContexts(DefineTypesContext.class);
		}
		public DefineTypesContext defineTypes(int i) {
			return getRuleContext(DefineTypesContext.class,i);
		}
		public List<AtomDefineContext> atomDefine() {
			return getRuleContexts(AtomDefineContext.class);
		}
		public AtomDefineContext atomDefine(int i) {
			return getRuleContext(AtomDefineContext.class,i);
		}
		public FunctionDefineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionDefine; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClikeListener ) ((ClikeListener)listener).enterFunctionDefine(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClikeListener ) ((ClikeListener)listener).exitFunctionDefine(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ClikeVisitor ) return ((ClikeVisitor<? extends T>)visitor).visitFunctionDefine(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionDefineContext functionDefine() throws RecognitionException {
		FunctionDefineContext _localctx = new FunctionDefineContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_functionDefine);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(143);
			returnTypes();
			setState(144);
			function();
			setState(145);
			match(T__13);
			setState(159);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
			case T__1:
			case T__2:
			case T__3:
			case T__4:
				{
				{
				setState(152);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(146);
						defineTypes();
						setState(147);
						atomDefine();
						setState(148);
						match(T__10);
						}
						} 
					}
					setState(154);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
				}
				setState(155);
				defineTypes();
				setState(156);
				atomDefine();
				}
				}
				break;
			case T__14:
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(161);
			match(T__14);
			setState(162);
			stmtBlock();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GlobalDefineContext extends ParserRuleContext {
		public DefineTypesContext defineTypes() {
			return getRuleContext(DefineTypesContext.class,0);
		}
		public List<AtomDefineContext> atomDefine() {
			return getRuleContexts(AtomDefineContext.class);
		}
		public AtomDefineContext atomDefine(int i) {
			return getRuleContext(AtomDefineContext.class,i);
		}
		public GlobalDefineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_globalDefine; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClikeListener ) ((ClikeListener)listener).enterGlobalDefine(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClikeListener ) ((ClikeListener)listener).exitGlobalDefine(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ClikeVisitor ) return ((ClikeVisitor<? extends T>)visitor).visitGlobalDefine(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GlobalDefineContext globalDefine() throws RecognitionException {
		GlobalDefineContext _localctx = new GlobalDefineContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_globalDefine);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(164);
			defineTypes();
			setState(170);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(165);
					atomDefine();
					setState(166);
					match(T__10);
					}
					} 
				}
				setState(172);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			}
			setState(173);
			atomDefine();
			setState(174);
			match(T__15);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StmtContext extends ParserRuleContext {
		public DefineStmtContext defineStmt() {
			return getRuleContext(DefineStmtContext.class,0);
		}
		public AssignStmtContext assignStmt() {
			return getRuleContext(AssignStmtContext.class,0);
		}
		public WhileStmtContext whileStmt() {
			return getRuleContext(WhileStmtContext.class,0);
		}
		public ForStmtContext forStmt() {
			return getRuleContext(ForStmtContext.class,0);
		}
		public CallStmtContext callStmt() {
			return getRuleContext(CallStmtContext.class,0);
		}
		public IfStmtContext ifStmt() {
			return getRuleContext(IfStmtContext.class,0);
		}
		public ContinueStmtContext continueStmt() {
			return getRuleContext(ContinueStmtContext.class,0);
		}
		public BreakStmtContext breakStmt() {
			return getRuleContext(BreakStmtContext.class,0);
		}
		public ReturnStmtContext returnStmt() {
			return getRuleContext(ReturnStmtContext.class,0);
		}
		public StmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClikeListener ) ((ClikeListener)listener).enterStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClikeListener ) ((ClikeListener)listener).exitStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ClikeVisitor ) return ((ClikeVisitor<? extends T>)visitor).visitStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StmtContext stmt() throws RecognitionException {
		StmtContext _localctx = new StmtContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_stmt);
		try {
			setState(197);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(176);
				defineStmt();
				setState(177);
				match(T__15);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(179);
				assignStmt();
				setState(180);
				match(T__15);
				}
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(182);
				whileStmt();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(183);
				forStmt();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				{
				setState(184);
				callStmt();
				setState(185);
				match(T__15);
				}
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(187);
				ifStmt();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				{
				setState(188);
				continueStmt();
				setState(189);
				match(T__15);
				}
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				{
				setState(191);
				breakStmt();
				setState(192);
				match(T__15);
				}
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				{
				setState(194);
				returnStmt();
				setState(195);
				match(T__15);
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StmtBlockContext extends ParserRuleContext {
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public StmtBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmtBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClikeListener ) ((ClikeListener)listener).enterStmtBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClikeListener ) ((ClikeListener)listener).exitStmtBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ClikeVisitor ) return ((ClikeVisitor<? extends T>)visitor).visitStmtBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StmtBlockContext stmtBlock() throws RecognitionException {
		StmtBlockContext _localctx = new StmtBlockContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_stmtBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(199);
			match(T__9);
			setState(203);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__25) | (1L << T__26) | (1L << T__27) | (1L << T__29) | (1L << T__30) | (1L << T__31) | (1L << ID))) != 0)) {
				{
				{
				setState(200);
				stmt();
				}
				}
				setState(205);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(206);
			match(T__11);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DefineStmtContext extends ParserRuleContext {
		public DefineTypesContext defineTypes() {
			return getRuleContext(DefineTypesContext.class,0);
		}
		public List<AtomDefineContext> atomDefine() {
			return getRuleContexts(AtomDefineContext.class);
		}
		public AtomDefineContext atomDefine(int i) {
			return getRuleContext(AtomDefineContext.class,i);
		}
		public DefineStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_defineStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClikeListener ) ((ClikeListener)listener).enterDefineStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClikeListener ) ((ClikeListener)listener).exitDefineStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ClikeVisitor ) return ((ClikeVisitor<? extends T>)visitor).visitDefineStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DefineStmtContext defineStmt() throws RecognitionException {
		DefineStmtContext _localctx = new DefineStmtContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_defineStmt);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(208);
			defineTypes();
			setState(214);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(209);
					atomDefine();
					setState(210);
					match(T__10);
					}
					} 
				}
				setState(216);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			}
			setState(217);
			atomDefine();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AssignStmtContext extends ParserRuleContext {
		public Token op;
		public SymbolValueContext symbolValue() {
			return getRuleContext(SymbolValueContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public AssignStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClikeListener ) ((ClikeListener)listener).enterAssignStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClikeListener ) ((ClikeListener)listener).exitAssignStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ClikeVisitor ) return ((ClikeVisitor<? extends T>)visitor).visitAssignStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignStmtContext assignStmt() throws RecognitionException {
		AssignStmtContext _localctx = new AssignStmtContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_assignStmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(219);
			symbolValue();
			setState(220);
			((AssignStmtContext)_localctx).op = _input.LT(1);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__8) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24))) != 0)) ) {
				((AssignStmtContext)_localctx).op = (Token)_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(221);
			expr(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class WhileStmtContext extends ParserRuleContext {
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public StmtBlockContext stmtBlock() {
			return getRuleContext(StmtBlockContext.class,0);
		}
		public WhileStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whileStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClikeListener ) ((ClikeListener)listener).enterWhileStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClikeListener ) ((ClikeListener)listener).exitWhileStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ClikeVisitor ) return ((ClikeVisitor<? extends T>)visitor).visitWhileStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WhileStmtContext whileStmt() throws RecognitionException {
		WhileStmtContext _localctx = new WhileStmtContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_whileStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(223);
			match(T__25);
			setState(224);
			match(T__13);
			setState(225);
			condition(0);
			setState(226);
			match(T__14);
			setState(227);
			stmtBlock();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ForStmtContext extends ParserRuleContext {
		public StmtBlockContext stmtBlock() {
			return getRuleContext(StmtBlockContext.class,0);
		}
		public ForInitContext forInit() {
			return getRuleContext(ForInitContext.class,0);
		}
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public ForUpdateContext forUpdate() {
			return getRuleContext(ForUpdateContext.class,0);
		}
		public ForStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClikeListener ) ((ClikeListener)listener).enterForStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClikeListener ) ((ClikeListener)listener).exitForStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ClikeVisitor ) return ((ClikeVisitor<? extends T>)visitor).visitForStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForStmtContext forStmt() throws RecognitionException {
		ForStmtContext _localctx = new ForStmtContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_forStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(229);
			match(T__26);
			setState(230);
			match(T__13);
			setState(233);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				{
				setState(231);
				forInit();
				}
				break;
			case T__15:
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(235);
			match(T__15);
			setState(238);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__13:
			case T__34:
			case T__41:
			case T__42:
			case ID:
			case NUM:
				{
				setState(236);
				condition(0);
				}
				break;
			case T__15:
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(240);
			match(T__15);
			setState(243);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				{
				setState(241);
				forUpdate();
				}
				break;
			case T__14:
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(245);
			match(T__14);
			setState(246);
			stmtBlock();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ForInitContext extends ParserRuleContext {
		public AssignStmtContext assignStmt() {
			return getRuleContext(AssignStmtContext.class,0);
		}
		public ForInitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forInit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClikeListener ) ((ClikeListener)listener).enterForInit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClikeListener ) ((ClikeListener)listener).exitForInit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ClikeVisitor ) return ((ClikeVisitor<? extends T>)visitor).visitForInit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForInitContext forInit() throws RecognitionException {
		ForInitContext _localctx = new ForInitContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_forInit);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(248);
			assignStmt();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ForUpdateContext extends ParserRuleContext {
		public AssignStmtContext assignStmt() {
			return getRuleContext(AssignStmtContext.class,0);
		}
		public ForUpdateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forUpdate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClikeListener ) ((ClikeListener)listener).enterForUpdate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClikeListener ) ((ClikeListener)listener).exitForUpdate(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ClikeVisitor ) return ((ClikeVisitor<? extends T>)visitor).visitForUpdate(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForUpdateContext forUpdate() throws RecognitionException {
		ForUpdateContext _localctx = new ForUpdateContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_forUpdate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(250);
			assignStmt();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CallStmtContext extends ParserRuleContext {
		public CallExprContext callExpr() {
			return getRuleContext(CallExprContext.class,0);
		}
		public CallStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_callStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClikeListener ) ((ClikeListener)listener).enterCallStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClikeListener ) ((ClikeListener)listener).exitCallStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ClikeVisitor ) return ((ClikeVisitor<? extends T>)visitor).visitCallStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CallStmtContext callStmt() throws RecognitionException {
		CallStmtContext _localctx = new CallStmtContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_callStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(252);
			callExpr();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IfStmtContext extends ParserRuleContext {
		public List<ConditionContext> condition() {
			return getRuleContexts(ConditionContext.class);
		}
		public ConditionContext condition(int i) {
			return getRuleContext(ConditionContext.class,i);
		}
		public List<StmtBlockContext> stmtBlock() {
			return getRuleContexts(StmtBlockContext.class);
		}
		public StmtBlockContext stmtBlock(int i) {
			return getRuleContext(StmtBlockContext.class,i);
		}
		public IfStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClikeListener ) ((ClikeListener)listener).enterIfStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClikeListener ) ((ClikeListener)listener).exitIfStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ClikeVisitor ) return ((ClikeVisitor<? extends T>)visitor).visitIfStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfStmtContext ifStmt() throws RecognitionException {
		IfStmtContext _localctx = new IfStmtContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_ifStmt);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(254);
			match(T__27);
			setState(255);
			match(T__13);
			setState(256);
			condition(0);
			setState(257);
			match(T__14);
			setState(258);
			stmtBlock();
			setState(268);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(259);
					match(T__28);
					setState(260);
					match(T__27);
					setState(261);
					match(T__13);
					setState(262);
					condition(0);
					setState(263);
					match(T__14);
					setState(264);
					stmtBlock();
					}
					} 
				}
				setState(270);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			}
			setState(274);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__28:
				{
				{
				setState(271);
				match(T__28);
				setState(272);
				stmtBlock();
				}
				}
				break;
			case T__0:
			case T__1:
			case T__2:
			case T__3:
			case T__4:
			case T__11:
			case T__25:
			case T__26:
			case T__27:
			case T__29:
			case T__30:
			case T__31:
			case ID:
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ContinueStmtContext extends ParserRuleContext {
		public ContinueStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_continueStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClikeListener ) ((ClikeListener)listener).enterContinueStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClikeListener ) ((ClikeListener)listener).exitContinueStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ClikeVisitor ) return ((ClikeVisitor<? extends T>)visitor).visitContinueStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ContinueStmtContext continueStmt() throws RecognitionException {
		ContinueStmtContext _localctx = new ContinueStmtContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_continueStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(276);
			match(T__29);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BreakStmtContext extends ParserRuleContext {
		public BreakStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_breakStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClikeListener ) ((ClikeListener)listener).enterBreakStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClikeListener ) ((ClikeListener)listener).exitBreakStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ClikeVisitor ) return ((ClikeVisitor<? extends T>)visitor).visitBreakStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BreakStmtContext breakStmt() throws RecognitionException {
		BreakStmtContext _localctx = new BreakStmtContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_breakStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(278);
			match(T__30);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ReturnStmtContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ReturnStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClikeListener ) ((ClikeListener)listener).enterReturnStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClikeListener ) ((ClikeListener)listener).exitReturnStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ClikeVisitor ) return ((ClikeVisitor<? extends T>)visitor).visitReturnStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReturnStmtContext returnStmt() throws RecognitionException {
		ReturnStmtContext _localctx = new ReturnStmtContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_returnStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(280);
			match(T__31);
			setState(283);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__13:
			case T__41:
			case T__42:
			case ID:
			case NUM:
				{
				setState(281);
				expr(0);
				}
				break;
			case T__15:
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConditionContext extends ParserRuleContext {
		public Token logic;
		public List<ConditionContext> condition() {
			return getRuleContexts(ConditionContext.class);
		}
		public ConditionContext condition(int i) {
			return getRuleContext(ConditionContext.class,i);
		}
		public AtomConditionContext atomCondition() {
			return getRuleContext(AtomConditionContext.class,0);
		}
		public ConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClikeListener ) ((ClikeListener)listener).enterCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClikeListener ) ((ClikeListener)listener).exitCondition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ClikeVisitor ) return ((ClikeVisitor<? extends T>)visitor).visitCondition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConditionContext condition() throws RecognitionException {
		return condition(0);
	}

	private ConditionContext condition(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ConditionContext _localctx = new ConditionContext(_ctx, _parentState);
		ConditionContext _prevctx = _localctx;
		int _startState = 50;
		enterRecursionRule(_localctx, 50, RULE_condition, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(293);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				{
				setState(286);
				match(T__13);
				setState(287);
				condition(0);
				setState(288);
				match(T__14);
				}
				break;
			case 2:
				{
				setState(290);
				((ConditionContext)_localctx).logic = match(T__34);
				setState(291);
				condition(2);
				}
				break;
			case 3:
				{
				setState(292);
				atomCondition();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(300);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ConditionContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_condition);
					setState(295);
					if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
					setState(296);
					((ConditionContext)_localctx).logic = _input.LT(1);
					_la = _input.LA(1);
					if ( !(_la==T__32 || _la==T__33) ) {
						((ConditionContext)_localctx).logic = (Token)_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(297);
					condition(5);
					}
					} 
				}
				setState(302);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class AtomConditionContext extends ParserRuleContext {
		public Token cmp;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public AtomConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atomCondition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClikeListener ) ((ClikeListener)listener).enterAtomCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClikeListener ) ((ClikeListener)listener).exitAtomCondition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ClikeVisitor ) return ((ClikeVisitor<? extends T>)visitor).visitAtomCondition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AtomConditionContext atomCondition() throws RecognitionException {
		AtomConditionContext _localctx = new AtomConditionContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_atomCondition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(303);
			expr(0);
			setState(304);
			((AtomConditionContext)_localctx).cmp = _input.LT(1);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__35) | (1L << T__36) | (1L << T__37) | (1L << T__38) | (1L << T__39) | (1L << T__40))) != 0)) ) {
				((AtomConditionContext)_localctx).cmp = (Token)_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(305);
			expr(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public CallExprContext callExpr() {
			return getRuleContext(CallExprContext.class,0);
		}
		public DefineTypesContext defineTypes() {
			return getRuleContext(DefineTypesContext.class,0);
		}
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClikeListener ) ((ClikeListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClikeListener ) ((ClikeListener)listener).exitExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ClikeVisitor ) return ((ClikeVisitor<? extends T>)visitor).visitExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 54;
		enterRecursionRule(_localctx, 54, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(321);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				{
				setState(308);
				((ExprContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==T__41 || _la==T__42) ) {
					((ExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(309);
				expr(8);
				}
				break;
			case 2:
				{
				setState(310);
				callExpr();
				}
				break;
			case 3:
				{
				setState(311);
				match(T__13);
				setState(312);
				expr(0);
				setState(313);
				match(T__14);
				}
				break;
			case 4:
				{
				setState(315);
				match(T__13);
				setState(316);
				defineTypes();
				setState(317);
				match(T__14);
				setState(318);
				expr(2);
				}
				break;
			case 5:
				{
				setState(320);
				value();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(334);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(332);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
					case 1:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(323);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(324);
						((ExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__43) | (1L << T__44) | (1L << T__45))) != 0)) ) {
							((ExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(325);
						expr(7);
						}
						break;
					case 2:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(326);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(327);
						((ExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__41 || _la==T__46) ) {
							((ExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(328);
						expr(6);
						}
						break;
					case 3:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(329);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(330);
						((ExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__47) | (1L << T__48) | (1L << T__49) | (1L << T__50) | (1L << T__51))) != 0)) ) {
							((ExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(331);
						expr(5);
						}
						break;
					}
					} 
				}
				setState(336);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class CallExprContext extends ParserRuleContext {
		public CallArgsContext callArgs() {
			return getRuleContext(CallArgsContext.class,0);
		}
		public VmContext vm() {
			return getRuleContext(VmContext.class,0);
		}
		public VmCallContext vmCall() {
			return getRuleContext(VmCallContext.class,0);
		}
		public FunctionContext function() {
			return getRuleContext(FunctionContext.class,0);
		}
		public CallExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_callExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClikeListener ) ((ClikeListener)listener).enterCallExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClikeListener ) ((ClikeListener)listener).exitCallExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ClikeVisitor ) return ((ClikeVisitor<? extends T>)visitor).visitCallExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CallExprContext callExpr() throws RecognitionException {
		CallExprContext _localctx = new CallExprContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_callExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(342);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
			case 1:
				{
				setState(337);
				vm();
				setState(338);
				match(T__52);
				setState(339);
				vmCall();
				}
				break;
			case 2:
				{
				setState(341);
				function();
				}
				break;
			}
			setState(344);
			match(T__13);
			setState(345);
			callArgs();
			setState(346);
			match(T__14);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CallArgsContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public CallArgsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_callArgs; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClikeListener ) ((ClikeListener)listener).enterCallArgs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClikeListener ) ((ClikeListener)listener).exitCallArgs(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ClikeVisitor ) return ((ClikeVisitor<? extends T>)visitor).visitCallArgs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CallArgsContext callArgs() throws RecognitionException {
		CallArgsContext _localctx = new CallArgsContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_callArgs);
		try {
			int _alt;
			setState(358);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__13:
			case T__41:
			case T__42:
			case ID:
			case NUM:
				enterOuterAlt(_localctx, 1);
				{
				setState(353);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(348);
						expr(0);
						setState(349);
						match(T__10);
						}
						} 
					}
					setState(355);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
				}
				setState(356);
				expr(0);
				}
				break;
			case T__14:
				enterOuterAlt(_localctx, 2);
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarValueContext extends ParserRuleContext {
		public VarContext var() {
			return getRuleContext(VarContext.class,0);
		}
		public VarValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClikeListener ) ((ClikeListener)listener).enterVarValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClikeListener ) ((ClikeListener)listener).exitVarValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ClikeVisitor ) return ((ClikeVisitor<? extends T>)visitor).visitVarValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarValueContext varValue() throws RecognitionException {
		VarValueContext _localctx = new VarValueContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_varValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(360);
			var();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstValueContext extends ParserRuleContext {
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public ConstValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClikeListener ) ((ClikeListener)listener).enterConstValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClikeListener ) ((ClikeListener)listener).exitConstValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ClikeVisitor ) return ((ClikeVisitor<? extends T>)visitor).visitConstValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstValueContext constValue() throws RecognitionException {
		ConstValueContext _localctx = new ConstValueContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_constValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(362);
			number();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArrValueContext extends ParserRuleContext {
		public VarContext var() {
			return getRuleContext(VarContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ArrValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClikeListener ) ((ClikeListener)listener).enterArrValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClikeListener ) ((ClikeListener)listener).exitArrValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ClikeVisitor ) return ((ClikeVisitor<? extends T>)visitor).visitArrValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrValueContext arrValue() throws RecognitionException {
		ArrValueContext _localctx = new ArrValueContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_arrValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(364);
			var();
			setState(365);
			match(T__6);
			setState(366);
			expr(0);
			setState(367);
			match(T__7);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SymbolValueContext extends ParserRuleContext {
		public VarValueContext varValue() {
			return getRuleContext(VarValueContext.class,0);
		}
		public ArrValueContext arrValue() {
			return getRuleContext(ArrValueContext.class,0);
		}
		public SymbolValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_symbolValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClikeListener ) ((ClikeListener)listener).enterSymbolValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClikeListener ) ((ClikeListener)listener).exitSymbolValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ClikeVisitor ) return ((ClikeVisitor<? extends T>)visitor).visitSymbolValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SymbolValueContext symbolValue() throws RecognitionException {
		SymbolValueContext _localctx = new SymbolValueContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_symbolValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(371);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
			case 1:
				{
				setState(369);
				varValue();
				}
				break;
			case 2:
				{
				setState(370);
				arrValue();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ValueContext extends ParserRuleContext {
		public SymbolValueContext symbolValue() {
			return getRuleContext(SymbolValueContext.class,0);
		}
		public ConstValueContext constValue() {
			return getRuleContext(ConstValueContext.class,0);
		}
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClikeListener ) ((ClikeListener)listener).enterValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClikeListener ) ((ClikeListener)listener).exitValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ClikeVisitor ) return ((ClikeVisitor<? extends T>)visitor).visitValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_value);
		try {
			setState(375);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(373);
				symbolValue();
				}
				break;
			case NUM:
				enterOuterAlt(_localctx, 2);
				{
				setState(374);
				constValue();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(ClikeParser.ID, 0); }
		public FunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClikeListener ) ((ClikeListener)listener).enterFunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClikeListener ) ((ClikeListener)listener).exitFunction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ClikeVisitor ) return ((ClikeVisitor<? extends T>)visitor).visitFunction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionContext function() throws RecognitionException {
		FunctionContext _localctx = new FunctionContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_function);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(377);
			match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VmContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(ClikeParser.ID, 0); }
		public VmContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_vm; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClikeListener ) ((ClikeListener)listener).enterVm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClikeListener ) ((ClikeListener)listener).exitVm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ClikeVisitor ) return ((ClikeVisitor<? extends T>)visitor).visitVm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VmContext vm() throws RecognitionException {
		VmContext _localctx = new VmContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_vm);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(379);
			match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VmCallContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(ClikeParser.ID, 0); }
		public VmCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_vmCall; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClikeListener ) ((ClikeListener)listener).enterVmCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClikeListener ) ((ClikeListener)listener).exitVmCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ClikeVisitor ) return ((ClikeVisitor<? extends T>)visitor).visitVmCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VmCallContext vmCall() throws RecognitionException {
		VmCallContext _localctx = new VmCallContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_vmCall);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(381);
			match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(ClikeParser.ID, 0); }
		public VarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_var; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClikeListener ) ((ClikeListener)listener).enterVar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClikeListener ) ((ClikeListener)listener).exitVar(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ClikeVisitor ) return ((ClikeVisitor<? extends T>)visitor).visitVar(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarContext var() throws RecognitionException {
		VarContext _localctx = new VarContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_var);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(383);
			match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NumberContext extends ParserRuleContext {
		public TerminalNode NUM() { return getToken(ClikeParser.NUM, 0); }
		public NumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_number; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClikeListener ) ((ClikeListener)listener).enterNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClikeListener ) ((ClikeListener)listener).exitNumber(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ClikeVisitor ) return ((ClikeVisitor<? extends T>)visitor).visitNumber(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumberContext number() throws RecognitionException {
		NumberContext _localctx = new NumberContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_number);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(385);
			match(NUM);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StringContext extends ParserRuleContext {
		public List<TerminalNode> ESC() { return getTokens(ClikeParser.ESC); }
		public TerminalNode ESC(int i) {
			return getToken(ClikeParser.ESC, i);
		}
		public StringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_string; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ClikeListener ) ((ClikeListener)listener).enterString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ClikeListener ) ((ClikeListener)listener).exitString(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ClikeVisitor ) return ((ClikeVisitor<? extends T>)visitor).visitString(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StringContext string() throws RecognitionException {
		StringContext _localctx = new StringContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_string);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(387);
			match(T__12);
			setState(392);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,32,_ctx);
			while ( _alt!=1 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1+1 ) {
					{
					setState(390);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
					case 1:
						{
						setState(388);
						match(ESC);
						}
						break;
					case 2:
						{
						setState(389);
						matchWildcard();
						}
						break;
					}
					} 
				}
				setState(394);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,32,_ctx);
			}
			setState(395);
			match(T__12);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 25:
			return condition_sempred((ConditionContext)_localctx, predIndex);
		case 27:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean condition_sempred(ConditionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 4);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 6);
		case 2:
			return precpred(_ctx, 5);
		case 3:
			return precpred(_ctx, 4);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3;\u0190\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\3\2\7\2"+
		"V\n\2\f\2\16\2Y\13\2\3\3\3\3\5\3]\n\3\3\4\3\4\5\4a\n\4\3\4\3\4\3\5\3\5"+
		"\5\5g\n\5\3\6\3\6\3\6\5\6l\n\6\3\7\3\7\3\7\3\7\5\7r\n\7\3\7\3\7\3\7\5"+
		"\7w\n\7\3\b\3\b\5\b{\n\b\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\7\n\u0085\n\n"+
		"\f\n\16\n\u0088\13\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\3\f\7\f\u0099\n\f\f\f\16\f\u009c\13\f\3\f\3\f\3\f\3\f\5\f"+
		"\u00a2\n\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\7\r\u00ab\n\r\f\r\16\r\u00ae\13"+
		"\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\5\16\u00c8\n\16\3\17"+
		"\3\17\7\17\u00cc\n\17\f\17\16\17\u00cf\13\17\3\17\3\17\3\20\3\20\3\20"+
		"\3\20\7\20\u00d7\n\20\f\20\16\20\u00da\13\20\3\20\3\20\3\21\3\21\3\21"+
		"\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\5\23\u00ec\n\23"+
		"\3\23\3\23\3\23\5\23\u00f1\n\23\3\23\3\23\3\23\5\23\u00f6\n\23\3\23\3"+
		"\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3"+
		"\27\3\27\3\27\3\27\3\27\3\27\7\27\u010d\n\27\f\27\16\27\u0110\13\27\3"+
		"\27\3\27\3\27\5\27\u0115\n\27\3\30\3\30\3\31\3\31\3\32\3\32\3\32\5\32"+
		"\u011e\n\32\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\5\33\u0128\n\33\3"+
		"\33\3\33\3\33\7\33\u012d\n\33\f\33\16\33\u0130\13\33\3\34\3\34\3\34\3"+
		"\34\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3"+
		"\35\5\35\u0144\n\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\7\35"+
		"\u014f\n\35\f\35\16\35\u0152\13\35\3\36\3\36\3\36\3\36\3\36\5\36\u0159"+
		"\n\36\3\36\3\36\3\36\3\36\3\37\3\37\3\37\7\37\u0162\n\37\f\37\16\37\u0165"+
		"\13\37\3\37\3\37\5\37\u0169\n\37\3 \3 \3!\3!\3\"\3\"\3\"\3\"\3\"\3#\3"+
		"#\5#\u0176\n#\3$\3$\5$\u017a\n$\3%\3%\3&\3&\3\'\3\'\3(\3(\3)\3)\3*\3*"+
		"\3*\7*\u0189\n*\f*\16*\u018c\13*\3*\3*\3*\3\u018a\4\648+\2\4\6\b\n\f\16"+
		"\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@BDFHJLNPR\2\n\3\2\4"+
		"\7\4\2\13\13\23\33\3\2#$\3\2&+\3\2,-\3\2.\60\4\2,,\61\61\3\2\62\66\2\u0193"+
		"\2W\3\2\2\2\4\\\3\2\2\2\6`\3\2\2\2\bf\3\2\2\2\nh\3\2\2\2\fm\3\2\2\2\16"+
		"z\3\2\2\2\20|\3\2\2\2\22\177\3\2\2\2\24\u008c\3\2\2\2\26\u0091\3\2\2\2"+
		"\30\u00a6\3\2\2\2\32\u00c7\3\2\2\2\34\u00c9\3\2\2\2\36\u00d2\3\2\2\2 "+
		"\u00dd\3\2\2\2\"\u00e1\3\2\2\2$\u00e7\3\2\2\2&\u00fa\3\2\2\2(\u00fc\3"+
		"\2\2\2*\u00fe\3\2\2\2,\u0100\3\2\2\2.\u0116\3\2\2\2\60\u0118\3\2\2\2\62"+
		"\u011a\3\2\2\2\64\u0127\3\2\2\2\66\u0131\3\2\2\28\u0143\3\2\2\2:\u0158"+
		"\3\2\2\2<\u0168\3\2\2\2>\u016a\3\2\2\2@\u016c\3\2\2\2B\u016e\3\2\2\2D"+
		"\u0175\3\2\2\2F\u0179\3\2\2\2H\u017b\3\2\2\2J\u017d\3\2\2\2L\u017f\3\2"+
		"\2\2N\u0181\3\2\2\2P\u0183\3\2\2\2R\u0185\3\2\2\2TV\5\4\3\2UT\3\2\2\2"+
		"VY\3\2\2\2WU\3\2\2\2WX\3\2\2\2X\3\3\2\2\2YW\3\2\2\2Z]\5\30\r\2[]\5\26"+
		"\f\2\\Z\3\2\2\2\\[\3\2\2\2]\5\3\2\2\2^a\7\3\2\2_a\3\2\2\2`^\3\2\2\2`_"+
		"\3\2\2\2ab\3\2\2\2bc\t\2\2\2c\7\3\2\2\2dg\5\6\4\2eg\7\b\2\2fd\3\2\2\2"+
		"fe\3\2\2\2g\t\3\2\2\2hk\5N(\2il\5\20\t\2jl\3\2\2\2ki\3\2\2\2kj\3\2\2\2"+
		"l\13\3\2\2\2mn\5N(\2nq\7\t\2\2or\5@!\2pr\3\2\2\2qo\3\2\2\2qp\3\2\2\2r"+
		"s\3\2\2\2sv\7\n\2\2tw\5\22\n\2uw\3\2\2\2vt\3\2\2\2vu\3\2\2\2w\r\3\2\2"+
		"\2x{\5\f\7\2y{\5\n\6\2zx\3\2\2\2zy\3\2\2\2{\17\3\2\2\2|}\7\13\2\2}~\5"+
		"8\35\2~\21\3\2\2\2\177\u0080\7\13\2\2\u0080\u0086\7\f\2\2\u0081\u0082"+
		"\58\35\2\u0082\u0083\7\r\2\2\u0083\u0085\3\2\2\2\u0084\u0081\3\2\2\2\u0085"+
		"\u0088\3\2\2\2\u0086\u0084\3\2\2\2\u0086\u0087\3\2\2\2\u0087\u0089\3\2"+
		"\2\2\u0088\u0086\3\2\2\2\u0089\u008a\58\35\2\u008a\u008b\7\16\2\2\u008b"+
		"\23\3\2\2\2\u008c\u008d\7\13\2\2\u008d\u008e\7\17\2\2\u008e\u008f\5R*"+
		"\2\u008f\u0090\7\17\2\2\u0090\25\3\2\2\2\u0091\u0092\5\b\5\2\u0092\u0093"+
		"\5H%\2\u0093\u00a1\7\20\2\2\u0094\u0095\5\6\4\2\u0095\u0096\5\16\b\2\u0096"+
		"\u0097\7\r\2\2\u0097\u0099\3\2\2\2\u0098\u0094\3\2\2\2\u0099\u009c\3\2"+
		"\2\2\u009a\u0098\3\2\2\2\u009a\u009b\3\2\2\2\u009b\u009d\3\2\2\2\u009c"+
		"\u009a\3\2\2\2\u009d\u009e\5\6\4\2\u009e\u009f\5\16\b\2\u009f\u00a2\3"+
		"\2\2\2\u00a0\u00a2\3\2\2\2\u00a1\u009a\3\2\2\2\u00a1\u00a0\3\2\2\2\u00a2"+
		"\u00a3\3\2\2\2\u00a3\u00a4\7\21\2\2\u00a4\u00a5\5\34\17\2\u00a5\27\3\2"+
		"\2\2\u00a6\u00ac\5\6\4\2\u00a7\u00a8\5\16\b\2\u00a8\u00a9\7\r\2\2\u00a9"+
		"\u00ab\3\2\2\2\u00aa\u00a7\3\2\2\2\u00ab\u00ae\3\2\2\2\u00ac\u00aa\3\2"+
		"\2\2\u00ac\u00ad\3\2\2\2\u00ad\u00af\3\2\2\2\u00ae\u00ac\3\2\2\2\u00af"+
		"\u00b0\5\16\b\2\u00b0\u00b1\7\22\2\2\u00b1\31\3\2\2\2\u00b2\u00b3\5\36"+
		"\20\2\u00b3\u00b4\7\22\2\2\u00b4\u00c8\3\2\2\2\u00b5\u00b6\5 \21\2\u00b6"+
		"\u00b7\7\22\2\2\u00b7\u00c8\3\2\2\2\u00b8\u00c8\5\"\22\2\u00b9\u00c8\5"+
		"$\23\2\u00ba\u00bb\5*\26\2\u00bb\u00bc\7\22\2\2\u00bc\u00c8\3\2\2\2\u00bd"+
		"\u00c8\5,\27\2\u00be\u00bf\5.\30\2\u00bf\u00c0\7\22\2\2\u00c0\u00c8\3"+
		"\2\2\2\u00c1\u00c2\5\60\31\2\u00c2\u00c3\7\22\2\2\u00c3\u00c8\3\2\2\2"+
		"\u00c4\u00c5\5\62\32\2\u00c5\u00c6\7\22\2\2\u00c6\u00c8\3\2\2\2\u00c7"+
		"\u00b2\3\2\2\2\u00c7\u00b5\3\2\2\2\u00c7\u00b8\3\2\2\2\u00c7\u00b9\3\2"+
		"\2\2\u00c7\u00ba\3\2\2\2\u00c7\u00bd\3\2\2\2\u00c7\u00be\3\2\2\2\u00c7"+
		"\u00c1\3\2\2\2\u00c7\u00c4\3\2\2\2\u00c8\33\3\2\2\2\u00c9\u00cd\7\f\2"+
		"\2\u00ca\u00cc\5\32\16\2\u00cb\u00ca\3\2\2\2\u00cc\u00cf\3\2\2\2\u00cd"+
		"\u00cb\3\2\2\2\u00cd\u00ce\3\2\2\2\u00ce\u00d0\3\2\2\2\u00cf\u00cd\3\2"+
		"\2\2\u00d0\u00d1\7\16\2\2\u00d1\35\3\2\2\2\u00d2\u00d8\5\6\4\2\u00d3\u00d4"+
		"\5\16\b\2\u00d4\u00d5\7\r\2\2\u00d5\u00d7\3\2\2\2\u00d6\u00d3\3\2\2\2"+
		"\u00d7\u00da\3\2\2\2\u00d8\u00d6\3\2\2\2\u00d8\u00d9\3\2\2\2\u00d9\u00db"+
		"\3\2\2\2\u00da\u00d8\3\2\2\2\u00db\u00dc\5\16\b\2\u00dc\37\3\2\2\2\u00dd"+
		"\u00de\5D#\2\u00de\u00df\t\3\2\2\u00df\u00e0\58\35\2\u00e0!\3\2\2\2\u00e1"+
		"\u00e2\7\34\2\2\u00e2\u00e3\7\20\2\2\u00e3\u00e4\5\64\33\2\u00e4\u00e5"+
		"\7\21\2\2\u00e5\u00e6\5\34\17\2\u00e6#\3\2\2\2\u00e7\u00e8\7\35\2\2\u00e8"+
		"\u00eb\7\20\2\2\u00e9\u00ec\5&\24\2\u00ea\u00ec\3\2\2\2\u00eb\u00e9\3"+
		"\2\2\2\u00eb\u00ea\3\2\2\2\u00ec\u00ed\3\2\2\2\u00ed\u00f0\7\22\2\2\u00ee"+
		"\u00f1\5\64\33\2\u00ef\u00f1\3\2\2\2\u00f0\u00ee\3\2\2\2\u00f0\u00ef\3"+
		"\2\2\2\u00f1\u00f2\3\2\2\2\u00f2\u00f5\7\22\2\2\u00f3\u00f6\5(\25\2\u00f4"+
		"\u00f6\3\2\2\2\u00f5\u00f3\3\2\2\2\u00f5\u00f4\3\2\2\2\u00f6\u00f7\3\2"+
		"\2\2\u00f7\u00f8\7\21\2\2\u00f8\u00f9\5\34\17\2\u00f9%\3\2\2\2\u00fa\u00fb"+
		"\5 \21\2\u00fb\'\3\2\2\2\u00fc\u00fd\5 \21\2\u00fd)\3\2\2\2\u00fe\u00ff"+
		"\5:\36\2\u00ff+\3\2\2\2\u0100\u0101\7\36\2\2\u0101\u0102\7\20\2\2\u0102"+
		"\u0103\5\64\33\2\u0103\u0104\7\21\2\2\u0104\u010e\5\34\17\2\u0105\u0106"+
		"\7\37\2\2\u0106\u0107\7\36\2\2\u0107\u0108\7\20\2\2\u0108\u0109\5\64\33"+
		"\2\u0109\u010a\7\21\2\2\u010a\u010b\5\34\17\2\u010b\u010d\3\2\2\2\u010c"+
		"\u0105\3\2\2\2\u010d\u0110\3\2\2\2\u010e\u010c\3\2\2\2\u010e\u010f\3\2"+
		"\2\2\u010f\u0114\3\2\2\2\u0110\u010e\3\2\2\2\u0111\u0112\7\37\2\2\u0112"+
		"\u0115\5\34\17\2\u0113\u0115\3\2\2\2\u0114\u0111\3\2\2\2\u0114\u0113\3"+
		"\2\2\2\u0115-\3\2\2\2\u0116\u0117\7 \2\2\u0117/\3\2\2\2\u0118\u0119\7"+
		"!\2\2\u0119\61\3\2\2\2\u011a\u011d\7\"\2\2\u011b\u011e\58\35\2\u011c\u011e"+
		"\3\2\2\2\u011d\u011b\3\2\2\2\u011d\u011c\3\2\2\2\u011e\63\3\2\2\2\u011f"+
		"\u0120\b\33\1\2\u0120\u0121\7\20\2\2\u0121\u0122\5\64\33\2\u0122\u0123"+
		"\7\21\2\2\u0123\u0128\3\2\2\2\u0124\u0125\7%\2\2\u0125\u0128\5\64\33\4"+
		"\u0126\u0128\5\66\34\2\u0127\u011f\3\2\2\2\u0127\u0124\3\2\2\2\u0127\u0126"+
		"\3\2\2\2\u0128\u012e\3\2\2\2\u0129\u012a\f\6\2\2\u012a\u012b\t\4\2\2\u012b"+
		"\u012d\5\64\33\7\u012c\u0129\3\2\2\2\u012d\u0130\3\2\2\2\u012e\u012c\3"+
		"\2\2\2\u012e\u012f\3\2\2\2\u012f\65\3\2\2\2\u0130\u012e\3\2\2\2\u0131"+
		"\u0132\58\35\2\u0132\u0133\t\5\2\2\u0133\u0134\58\35\2\u0134\67\3\2\2"+
		"\2\u0135\u0136\b\35\1\2\u0136\u0137\t\6\2\2\u0137\u0144\58\35\n\u0138"+
		"\u0144\5:\36\2\u0139\u013a\7\20\2\2\u013a\u013b\58\35\2\u013b\u013c\7"+
		"\21\2\2\u013c\u0144\3\2\2\2\u013d\u013e\7\20\2\2\u013e\u013f\5\6\4\2\u013f"+
		"\u0140\7\21\2\2\u0140\u0141\58\35\4\u0141\u0144\3\2\2\2\u0142\u0144\5"+
		"F$\2\u0143\u0135\3\2\2\2\u0143\u0138\3\2\2\2\u0143\u0139\3\2\2\2\u0143"+
		"\u013d\3\2\2\2\u0143\u0142\3\2\2\2\u0144\u0150\3\2\2\2\u0145\u0146\f\b"+
		"\2\2\u0146\u0147\t\7\2\2\u0147\u014f\58\35\t\u0148\u0149\f\7\2\2\u0149"+
		"\u014a\t\b\2\2\u014a\u014f\58\35\b\u014b\u014c\f\6\2\2\u014c\u014d\t\t"+
		"\2\2\u014d\u014f\58\35\7\u014e\u0145\3\2\2\2\u014e\u0148\3\2\2\2\u014e"+
		"\u014b\3\2\2\2\u014f\u0152\3\2\2\2\u0150\u014e\3\2\2\2\u0150\u0151\3\2"+
		"\2\2\u01519\3\2\2\2\u0152\u0150\3\2\2\2\u0153\u0154\5J&\2\u0154\u0155"+
		"\7\67\2\2\u0155\u0156\5L\'\2\u0156\u0159\3\2\2\2\u0157\u0159\5H%\2\u0158"+
		"\u0153\3\2\2\2\u0158\u0157\3\2\2\2\u0159\u015a\3\2\2\2\u015a\u015b\7\20"+
		"\2\2\u015b\u015c\5<\37\2\u015c\u015d\7\21\2\2\u015d;\3\2\2\2\u015e\u015f"+
		"\58\35\2\u015f\u0160\7\r\2\2\u0160\u0162\3\2\2\2\u0161\u015e\3\2\2\2\u0162"+
		"\u0165\3\2\2\2\u0163\u0161\3\2\2\2\u0163\u0164\3\2\2\2\u0164\u0166\3\2"+
		"\2\2\u0165\u0163\3\2\2\2\u0166\u0169\58\35\2\u0167\u0169\3\2\2\2\u0168"+
		"\u0163\3\2\2\2\u0168\u0167\3\2\2\2\u0169=\3\2\2\2\u016a\u016b\5N(\2\u016b"+
		"?\3\2\2\2\u016c\u016d\5P)\2\u016dA\3\2\2\2\u016e\u016f\5N(\2\u016f\u0170"+
		"\7\t\2\2\u0170\u0171\58\35\2\u0171\u0172\7\n\2\2\u0172C\3\2\2\2\u0173"+
		"\u0176\5> \2\u0174\u0176\5B\"\2\u0175\u0173\3\2\2\2\u0175\u0174\3\2\2"+
		"\2\u0176E\3\2\2\2\u0177\u017a\5D#\2\u0178\u017a\5@!\2\u0179\u0177\3\2"+
		"\2\2\u0179\u0178\3\2\2\2\u017aG\3\2\2\2\u017b\u017c\79\2\2\u017cI\3\2"+
		"\2\2\u017d\u017e\79\2\2\u017eK\3\2\2\2\u017f\u0180\79\2\2\u0180M\3\2\2"+
		"\2\u0181\u0182\79\2\2\u0182O\3\2\2\2\u0183\u0184\7:\2\2\u0184Q\3\2\2\2"+
		"\u0185\u018a\7\17\2\2\u0186\u0189\78\2\2\u0187\u0189\13\2\2\2\u0188\u0186"+
		"\3\2\2\2\u0188\u0187\3\2\2\2\u0189\u018c\3\2\2\2\u018a\u018b\3\2\2\2\u018a"+
		"\u0188\3\2\2\2\u018b\u018d\3\2\2\2\u018c\u018a\3\2\2\2\u018d\u018e\7\17"+
		"\2\2\u018eS\3\2\2\2#W\\`fkqvz\u0086\u009a\u00a1\u00ac\u00c7\u00cd\u00d8"+
		"\u00eb\u00f0\u00f5\u010e\u0114\u011d\u0127\u012e\u0143\u014e\u0150\u0158"+
		"\u0163\u0168\u0175\u0179\u0188\u018a";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}