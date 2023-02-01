package esercitazione5.symboltable.exception;

import esercitazione5.symboltable.SymbolTable;

public class MethodCallException extends RuntimeException{
    public MethodCallException(String functionName, int line) {
        super("Error at line " + (line + 1) + ": function " + functionName.substring(SymbolTable.PREFIX.length()) + " has an invalid call method");
    }
}
