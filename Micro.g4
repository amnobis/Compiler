grammar Micro;

options {
    language = Java;
}
@rulecatch{
	catch (RecognitionException e) 
	{
		throw e;
	}
}

@members{
	public SymbolTableStack STACK = new SymbolTableStack();
	public SymbolTable TABLE = new SymbolTable("GLOBAL");
}

/* Program */
program: 'PROGRAM' id 'BEGIN' pgm_body 'END';

id: IDENTIFIER;
pgm_body: decl func_declarations;
decl: decl_list*;
decl_list: string_decl_list | var_decl_list ;

/* Global String Declaration */
string_decl: 'STRING' id ':=' str ';'
{
	TABLE.AddGlobalString($id.text, $str.text);
};

string_decl_list: string_decl+;
str: STRINGLITERAL;

/* Variable Declaration */

var_decl_list: var_decl+;

var_decl: var_type id_list ';'
{
	TABLE.Add($var_type.text, $id_list.vars);
};

var_type: 'FLOAT' | 'INT';
any_type: var_type | 'VOID'; 
id_list returns [ArrayList<String> vars]: {ArrayList<String> vars = new ArrayList<String>();} id { vars.add($id.text); } (',' id {vars.add($id.text);})*;


/* Function Paramater List */
param_decl_list: param_decl param_decl_tail | ;
param_decl: var_type id;
param_decl_tail: ',' param_decl param_decl_tail | ;


/* Function Declarations */
func_declarations: func_decl*;
func_decl: 'FUNCTION' any_type id 
{ 
	STACK.AddTable(TABLE); 
	TABLE = new SymbolTable($id.text);
}
'(' param_decl_list ')' 'BEGIN' func_body 'END';

func_body: decl stmt_list ;

/* Statement List */
stmt_list: stmt stmt_list | ;
stmt: base_stmt | if_stmt | for_stmt;
base_stmt: assign_stmt | read_stmt | write_stmt | return_stmt;

/* Basic Statements */
assign_stmt: assign_expr ';';
assign_expr: id ':=' expr;
read_stmt: 'READ' '(' id_list ')' ';';
write_stmt: 'WRITE' '(' id_list ')' ';';
return_stmt: 'RETURN' expr ';';

/* Expressions */
expr: expr_prefix factor;
expr_prefix: expr_prefix factor addop | ;
factor: factor_prefix postfix_expr;
factor_prefix: factor_prefix postfix_expr mulop | ;
postfix_expr: primary | call_expr;
call_expr :id '(' expr_list ')';
expr_list: expr expr_list_tail | ;
expr_list_tail: ',' expr expr_list_tail | ;
primary: '(' expr ')' | id | INTLITERAL | FLOATLITERAL;
addop: '+' | '-';
mulop: '*' | '/';

/* Complex Statements and Condition */ 
if_stmt: 'IF' 
{
	STACK.AddTable(TABLE);
	TABLE = new SymbolTable();
}
'(' cond ')' decl stmt_list else_part 'FI';

else_part: ('ELSE' decl stmt_list )? 
{
	STACK.AddTable(TABLE);
	TABLE = new SymbolTable();
};
cond: expr compop expr;
compop: '<' | '>' | '=' | '!=' | '<=' | '>=';

init_stmt: assign_expr | ;
incr_stmt: assign_expr | ;

/* ECE 468 Student version of While_stmt */
while_stmt: 'WHILE' '(' cond ')' decl stmt_list 'ENDWHILE';
for_stmt: 'FOR' 
{
	STACK.AddTable(TABLE);
	TABLE = new SymbolTable();
} 
'(' init_stmt ';' cond ';' incr_stmt ')' decl stmt_list 'ROF'
;

KEYWORD : 'PROGRAM' | 'BEGIN' | 'END' | 'FUNCTION' | 'READ' | 'WRITE' | 'IF' | 'ELSE' | 'FI' | 'FOR' | 'ROF' | 'CONTINUE' | 'BREAK' | 'RETURN' | 'INT' | 'VOID' | 'STRING' | 'FLOAT' ;

OPERATOR: (':=' | '+' | '-' | '*' | '/' | '=' | '!=' | '<' | '>' | '(' | ')' | ';' | ',' | '<=' | '>=');

COMMENT: '--' ~[\r\n]* -> skip;

WHITESPACE:  (' ' | '\n' | '\t' | '\f' | '\r')+ -> skip; 

IDENTIFIER: [A-z_][A-z0-9_]*;

INTLITERAL: [0-9]+;

FLOATLITERAL: (INTLITERAL)*('.')(INTLITERAL)*;

STRINGLITERAL: '"'(~[\r\n])*?'"';


