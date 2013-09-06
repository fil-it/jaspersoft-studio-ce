
/*
* generated by Xtext
*/
lexer grammar InternalSqlLexer;


@header {
package com.jaspersoft.studio.data.ui.contentassist.antlr.lexer;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.Lexer;
}




KEYWORD_62 : ('R'|'r')('I'|'i')('G'|'g')('H'|'h')('T'|'t')' '('O'|'o')('U'|'u')('T'|'t')('E'|'e')('R'|'r')' '('J'|'j')('O'|'o')('I'|'i')('N'|'n');

KEYWORD_60 : ('F'|'f')('U'|'u')('L'|'l')('L'|'l')' '('O'|'o')('U'|'u')('T'|'t')('E'|'e')('R'|'r')' '('J'|'j')('O'|'o')('I'|'i')('N'|'n');

KEYWORD_61 : ('L'|'l')('E'|'e')('F'|'f')('T'|'t')' '('O'|'o')('U'|'u')('T'|'t')('E'|'e')('R'|'r')' '('J'|'j')('O'|'o')('I'|'i')('N'|'n');

KEYWORD_58 : ('I'|'i')('S'|'s')' '('N'|'n')('O'|'o')('T'|'t')' '('N'|'n')('U'|'u')('L'|'l')('L'|'l');

KEYWORD_59 : ('N'|'n')('O'|'o')('T'|'t')' '('B'|'b')('E'|'e')('T'|'t')('W'|'w')('E'|'e')('E'|'e')('N'|'n');

KEYWORD_56 : ('C'|'c')('R'|'r')('O'|'o')('S'|'s')('S'|'s')' '('J'|'j')('O'|'o')('I'|'i')('N'|'n');

KEYWORD_57 : ('I'|'i')('N'|'n')('N'|'n')('E'|'e')('R'|'r')' '('J'|'j')('O'|'o')('I'|'i')('N'|'n');

KEYWORD_54 : ('I'|'i')('N'|'n')('T'|'t')('E'|'e')('R'|'r')('S'|'s')('E'|'e')('C'|'c')('T'|'t');

KEYWORD_55 : '['('B'|'b')('E'|'e')('T'|'t')('W'|'w')('E'|'e')('E'|'e')('N'|'n')']';

KEYWORD_46 : ('B'|'b')('E'|'e')('T'|'t')('W'|'w')('E'|'e')('E'|'e')('N'|'n')']';

KEYWORD_47 : ('D'|'d')('I'|'i')('S'|'s')('T'|'t')('I'|'i')('N'|'n')('C'|'c')('T'|'t');

KEYWORD_48 : ('G'|'g')('R'|'r')('O'|'o')('U'|'u')('P'|'p')' '('B'|'b')('Y'|'y');

KEYWORD_49 : ('N'|'n')('O'|'o')('T'|'t')' '('L'|'l')('I'|'i')('K'|'k')('E'|'e');

KEYWORD_50 : ('N'|'n')('O'|'o')('T'|'t')('E'|'e')('Q'|'q')('U'|'u')('A'|'a')('L'|'l');

KEYWORD_51 : ('O'|'o')('R'|'r')('D'|'d')('E'|'e')('R'|'r')' '('B'|'b')('Y'|'y');

KEYWORD_52 : '['('B'|'b')('E'|'e')('T'|'t')('W'|'w')('E'|'e')('E'|'e')('N'|'n');

KEYWORD_53 : '['('G'|'g')('R'|'r')('E'|'e')('A'|'a')('T'|'t')('E'|'e')('R'|'r');

KEYWORD_43 : ('B'|'b')('E'|'e')('T'|'t')('W'|'w')('E'|'e')('E'|'e')('N'|'n');

KEYWORD_44 : ('G'|'g')('R'|'r')('E'|'e')('A'|'a')('T'|'t')('E'|'e')('R'|'r');

KEYWORD_45 : ('I'|'i')('S'|'s')' '('N'|'n')('U'|'u')('L'|'l')('L'|'l');

KEYWORD_39 : ('E'|'e')('X'|'x')('C'|'c')('E'|'e')('P'|'p')('T'|'t');

KEYWORD_40 : ('H'|'h')('A'|'a')('V'|'v')('I'|'i')('N'|'n')('G'|'g');

KEYWORD_41 : ('N'|'n')('O'|'o')('T'|'t')' '('I'|'i')('N'|'n');

KEYWORD_42 : ('S'|'s')('E'|'e')('L'|'l')('E'|'e')('C'|'c')('T'|'t');

KEYWORD_33 : ('E'|'e')('Q'|'q')('U'|'u')('A'|'a')('L'|'l');

KEYWORD_34 : ('L'|'l')('E'|'e')('S'|'s')('S'|'s')']';

KEYWORD_35 : ('M'|'m')('I'|'i')('N'|'n')('U'|'u')('S'|'s');

KEYWORD_36 : ('N'|'n')('O'|'o')('T'|'t')('I'|'i')('N'|'n');

KEYWORD_37 : ('U'|'u')('N'|'n')('I'|'i')('O'|'o')('N'|'n');

KEYWORD_38 : ('W'|'w')('H'|'h')('E'|'e')('R'|'r')('E'|'e');

KEYWORD_28 : ('D'|'d')('E'|'e')('S'|'s')('C'|'c');

KEYWORD_29 : ('F'|'f')('R'|'r')('O'|'o')('M'|'m');

KEYWORD_30 : ('L'|'l')('E'|'e')('S'|'s')('S'|'s');

KEYWORD_31 : ('L'|'l')('I'|'i')('K'|'k')('E'|'e');

KEYWORD_32 : ('S'|'s')('O'|'o')('M'|'m')('E'|'e');

KEYWORD_23 : '$'('P'|'p')'!';

KEYWORD_24 : ('A'|'a')('L'|'l')('L'|'l');

KEYWORD_25 : ('A'|'a')('N'|'n')('D'|'d');

KEYWORD_26 : ('A'|'a')('N'|'n')('Y'|'y');

KEYWORD_27 : ('A'|'a')('S'|'s')('C'|'c');

KEYWORD_13 : '$'('P'|'p');

KEYWORD_14 : '$'('X'|'x');

KEYWORD_15 : '<''=';

KEYWORD_16 : '<''>';

KEYWORD_17 : '>''=';

KEYWORD_18 : ('A'|'a')('S'|'s');

KEYWORD_19 : ('I'|'i')('N'|'n');

KEYWORD_20 : ('O'|'o')('N'|'n');

KEYWORD_21 : ('O'|'o')('R'|'r');

KEYWORD_22 : '|''|';

KEYWORD_1 : '(';

KEYWORD_2 : ')';

KEYWORD_3 : '+';

KEYWORD_4 : ',';

KEYWORD_5 : '-';

KEYWORD_6 : '.';

KEYWORD_7 : '/';

KEYWORD_8 : '<';

KEYWORD_9 : '=';

KEYWORD_10 : '>';

KEYWORD_11 : '{';

KEYWORD_12 : '}';



RULE_FNAME : RULE_STRINGID '(';

RULE_DBID : (RULE_STRINGID|'\'' RULE_STRINGID '\''|'"' RULE_STRINGID '"'|'`' RULE_STRINGID '`'|'[' RULE_STRINGID ']');

fragment RULE_STRINGID : ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

RULE_STAR : '*';

RULE_INT : '-'? ('0'..'9')+;

RULE_TIMESTAMP : RULE_DATE ' ' RULE_TIME;

RULE_DATE : '\'' '0'..'9' '0'..'9' '0'..'9' '0'..'9' '-' '0'..'1' '0'..'9' '-' '0'..'3' '0'..'9' '\'';

RULE_TIME : '\'' '0'..'9' '0'..'9' ':' '0'..'9' '0'..'9' ':' '0'..'1' '0'..'9' '.' '0'..'9' '0'..'9' '0'..'9' '\'';

RULE_SIGNED_DOUBLE : '-'? ('0'..'9')+ ('.' ('0'..'9')+)?;

RULE_BRACEDPRM : '{' RULE_PRMNAME '}';

fragment RULE_PRMNAME : ~(('\r'|'\n'|'}'|','|'{'))+;

RULE_SL_COMMENT : ('--'|'#'|'//') ~(('\n'|'\r'))* ('\r'? '\n')?;

RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

RULE_STRING : ('"' ('\\' ('b'|'t'|'n'|'f'|'r'|'u'|'"'|'\''|'\\')|~(('\\'|'"')))* '"'|'\'' ('\\' ('b'|'t'|'n'|'f'|'r'|'u'|'"'|'\''|'\\')|~(('\\'|'\'')))* '\'');

RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

RULE_WS : (' '|'\t'|'\r'|'\n')+;

RULE_ANY_OTHER : .;



