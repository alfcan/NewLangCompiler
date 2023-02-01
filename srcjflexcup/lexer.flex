package esercitazione5;
import java_cup.runtime.Symbol; //This is how we pass tokens to the parser
import java.util.ArrayList;
import java.lang.Integer;
import java.lang.Float;
import esercitazione5.symboltable.SymbolTable;

%%
%class Lexer
%unicode
%cup
%line
%column
%public

%{
    private static ArrayList<String> stringTable;
    private String stringConst;
%}

%init{
	stringTable = new ArrayList<>();
%init}

%eofval{
	return new Symbol(ParserSym.EOF);
%eofval}

%{
    private int installID(String lexeme){
        if (!stringTable.contains(lexeme))
            stringTable.add(lexeme);
        return stringTable.indexOf(lexeme);
    }

    public String getElementOfStringTable(int pos){
        return stringTable.get(pos);
    }
    public int getSizeStringTable(){
        return stringTable.size();
    }
    public ArrayList<String> getStringTable(){
      return stringTable;
    }
%}


LineTerminator = \r|\n|\r\n
WhiteSpace     = {LineTerminator} | [ \t\f]

identifier = [$_A-Za-z][$_A-Za-z0-9]*
integer_const = [0-9]+
float_const = [0-9]+\.[0-9]+
char_const = \'.\'

%state STRING
%state COMMENT
%state LINECOMMENT

%%
<YYINITIAL> {
    /* whitespace */
    {WhiteSpace} { /* ignore */ }

    /* Keywords */
    "def" { return new Symbol(ParserSym.DEF, yyline, yycolumn); }
    "out" { return new Symbol(ParserSym.OUT, yyline, yycolumn); }
    "for" { return new Symbol(ParserSym.FOR, yyline, yycolumn); }
    "if" { return new Symbol(ParserSym.IF, yyline, yycolumn); }
    "else" { return new Symbol(ParserSym.ELSE, yyline, yycolumn); }
    "then" { return new Symbol(ParserSym.THEN, yyline, yycolumn); }
    "while" { return new Symbol(ParserSym.WHILE, yyline, yycolumn); }
    "to" { return new Symbol(ParserSym.TO, yyline, yycolumn); }
    "loop" { return new Symbol(ParserSym.LOOP, yyline, yycolumn); }
    "var" { return new Symbol(ParserSym.VAR, yyline, yycolumn); }
    "integer" { return new Symbol(ParserSym.INT, yyline, yycolumn); }
    "float" { return new Symbol(ParserSym.FLOAT, yyline, yycolumn); }
    "string" { return new Symbol(ParserSym.STRING, yyline, yycolumn); }
    "boolean" { return new Symbol(ParserSym.BOOL, yyline, yycolumn); }
    "char" { return new Symbol(ParserSym.CHAR, yyline, yycolumn); }
    "void" { return new Symbol(ParserSym.VOID, yyline, yycolumn); }
    "return" { return new Symbol(ParserSym.RETURN, yyline, yycolumn); }
    "and" { return new Symbol(ParserSym.AND, yyline, yycolumn); }
    "or" { return new Symbol(ParserSym.OR, yyline, yycolumn); }
    "not" { return new Symbol(ParserSym.NOT, yyline, yycolumn); }
    "true" { return new Symbol(ParserSym.TRUE, yyline, yycolumn); }
    "false" { return new Symbol(ParserSym.FALSE, yyline, yycolumn); }

    /* Separators */
    "start:" { return new Symbol(ParserSym.MAIN, yyline, yycolumn); }
    ";" { return new Symbol(ParserSym.SEMI, yyline, yycolumn); }
    "," { return new Symbol(ParserSym.COMMA, yyline, yycolumn); }
    "|" { return new Symbol(ParserSym.PIPE, yyline, yycolumn); }
    "<--" { return new Symbol(ParserSym.READ, yyline, yycolumn); }
    "-->" { return new Symbol(ParserSym.WRITE, yyline, yycolumn); }
    "-->!" { return new Symbol(ParserSym.WRITELN, yyline, yycolumn); }
    "(" { return new Symbol(ParserSym.LPAR, yyline, yycolumn); }
    ")" { return new Symbol(ParserSym.RPAR, yyline, yycolumn); }
    "{" { return new Symbol(ParserSym.LBRAC, yyline, yycolumn); }
    "}" { return new Symbol(ParserSym.RBRAC, yyline, yycolumn); }
    ":" { return new Symbol(ParserSym.COLON, yyline, yycolumn); }
    "<<" { return new Symbol(ParserSym.ASSIGN, yyline, yycolumn); }


    /* Operators */
    "+" { return new Symbol(ParserSym.PLUS, yyline, yycolumn); }
    "-" { return new Symbol(ParserSym.MINUS, yyline, yycolumn); }
    "*" { return new Symbol(ParserSym.TIMES, yyline, yycolumn); }
    "/" { return new Symbol(ParserSym.DIV, yyline, yycolumn); }
    "^" { return new Symbol(ParserSym.POW, yyline, yycolumn); }
    "&" { return new Symbol(ParserSym.STR_CONCAT, yyline, yycolumn); }
    "=" { return new Symbol(ParserSym.EQ, yyline, yycolumn); }
    "<>" { return new Symbol(ParserSym.NE, yyline, yycolumn); }
    "!=" { return new Symbol(ParserSym.NE, yyline, yycolumn); }
    "<" { return new Symbol(ParserSym.LT, yyline, yycolumn); }
    "<=" { return new Symbol(ParserSym.LE, yyline, yycolumn); }
    ">" { return new Symbol(ParserSym.GT, yyline, yycolumn); }
    ">=" { return new Symbol(ParserSym.GE, yyline, yycolumn); }

    /* identifier */
    {identifier} { return new Symbol(ParserSym.ID, yyline, yycolumn, installID(SymbolTable.PREFIX + yytext())); }

    /* constant */
    {integer_const} { return new Symbol(ParserSym.INTEGER_CONST, yyline, yycolumn, Integer.parseInt(yytext())); }
    {float_const}    { return new Symbol(ParserSym.FLOAT_CONST, yyline, yycolumn, Float.parseFloat(yytext())); }
    {char_const}    { String charLexeme = yytext(); return new Symbol(ParserSym.CHAR_CONST, yyline, yycolumn, charLexeme.charAt(1)); }

    \" { stringConst = ""; yybegin(STRING); }

    /* comments */
    \|\* { yybegin(COMMENT); }
    \|\| { yybegin(LINECOMMENT); }

    /* error fallback */
    [^] { throw new Error("Carattere non accetatto: <"+yytext()+">"); }

}

<STRING>{
    \"      { yybegin(YYINITIAL); return new Symbol(ParserSym.STRING_CONST, yyline, yycolumn, stringConst); }
    .       { stringConst += yytext(); }
    \\\"    { stringConst += "\""; }
    \\n     { stringConst += "\n"; }
    \\t     { stringConst += "\t"; }
    <<EOF>> { throw new Error("Valore stringa non valido");}
}

<COMMENT>{
    \|    { yybegin(YYINITIAL); }
    .	    { }
    \n      { }
    \r      { }
    \t      { }
    <<EOF>>	{ throw new Error("Commento non valido"); }
}

<LINECOMMENT>{
    . { }
    \n	{ yybegin(YYINITIAL); }
    <<EOF>>	{ yybegin(YYINITIAL); }
}