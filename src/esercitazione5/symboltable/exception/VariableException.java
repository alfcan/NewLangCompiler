package esercitazione5.symboltable.exception;

import esercitazione5.symboltable.SymbolTable;

public class VariableException extends RuntimeException{
    public VariableException(String variable, int line){
        super("Error at line " + (line + 1) + ": " + variable.substring(SymbolTable.PREFIX.length()) + " is not a variable");
    }

    public VariableException(int line, String variable){
        super("Error at line " + (line + 1) + ": " + variable.substring(SymbolTable.PREFIX.length()) + " is not a variable but is a function");
    }
}
