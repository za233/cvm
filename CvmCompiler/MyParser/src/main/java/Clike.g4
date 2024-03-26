grammar Clike;

module : moduleDefine*;
moduleDefine : globalDefine | functionDefine;

defineTypes : ('unsigned' | ) ('char' | 'short' | 'int' | 'long');
returnTypes : defineTypes | 'void';

varDefine : var (varInitializer | );
arrDefine : var '[' (constValue | ) ']' (arrInitializer | );
atomDefine : arrDefine | varDefine;

varInitializer : '=' expr;
arrInitializer : '=' '{' (expr ',')* expr '}';
functionDefine : returnTypes function '(' (((defineTypes atomDefine ',')* defineTypes atomDefine) | ) ')' stmtBlock;
globalDefine : defineTypes (atomDefine ',')* atomDefine ';';

stmt : (defineStmt ';') | (assignStmt ';') | whileStmt | forStmt | (callStmt ';') | ifStmt | (continueStmt ';') | (breakStmt ';') | (returnStmt ';');
stmtBlock : '{' stmt* '}';


defineStmt : defineTypes (atomDefine ',')* atomDefine;
assignStmt : symbolValue op=('+=' | '-=' | '*=' | '/=' | '&=' | '|=' | '^=' | '<<=' | '>>=' | '=') expr;
whileStmt : 'while' '(' condition ')' stmtBlock;
forStmt : 'for' '(' (forInit | ) ';'  (condition | ) ';' (forUpdate | ) ')' stmtBlock;
forInit : assignStmt;
forUpdate : assignStmt ;
callStmt : callExpr;
ifStmt : 'if' '(' condition ')' stmtBlock  ('else' 'if' '(' condition ')' stmtBlock)* (('else' stmtBlock) | );
continueStmt : 'continue';
breakStmt : 'break';
returnStmt : 'return' (expr | );

condition : condition logic=('&&' | '||') condition |
            '(' condition ')' |
            logic='!' condition |
            atomCondition;
atomCondition : expr cmp=('==' | '!=' | '>=' | '<=' | '>' | '<') expr;

expr : op=('-' | '~') expr |
       callExpr |
       expr op=('*' | '/' | '%') expr |
       expr op=('+' | '-') expr |
       expr op=('|' | '&' | '^' | '>>' | '<<') expr |
       '(' expr ')' |
       '(' defineTypes ')' expr |
       value;
callExpr : (vm '.' vmCall | function) '(' callArgs ')';
callArgs : (expr ',')* expr | ;

varValue : var;
constValue : number;
arrValue : var '[' expr ']';
symbolValue : (varValue | arrValue);
value : symbolValue | constValue;

function : ID;
vm : ID;
vmCall : ID;
var : ID;
number : NUM;

ESC : '\\"' | '\\\\';
ID : [a-zA-Z_] ([a-zA-Z0-9_]+ | );
NUM : ('0x' [0-9a-fA-F]+) | (('-' |) [0-9]+ ('U' |));
WS : [ \t\n\r]+ -> skip ;   // skip spaces, tabs, newlines, \r (Windows)