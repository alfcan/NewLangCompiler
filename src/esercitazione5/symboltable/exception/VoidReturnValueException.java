package esercitazione5.symboltable.exception;

import esercitazione5.symboltable.SymbolTable;

public class VoidReturnValueException extends RuntimeException{
    public VoidReturnValueException(String functionName, int line){
        super("Error at line " + (line + 1) + ": function " + functionName.substring(SymbolTable.PREFIX.length()) + " has a void return type, should not have a return expression");
    }
}
