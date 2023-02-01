package esercitazione5.symboltable.exception;

import esercitazione5.symboltable.SymbolTable;

public class InvalidReturnException extends RuntimeException {
    public InvalidReturnException(String functionName, String functionType, String returnType, int line) {
        super("Error at line " + (line + 1) + ": function " + functionName.substring(SymbolTable.PREFIX.length()) + " should return a " + functionType +" value, but return value is a "+ returnType);
    }

    public InvalidReturnException(String functionName, String functionType, int line) {
        super("Error at line " + (line + 1) + ": function " + functionName.substring(SymbolTable.PREFIX.length()) + " should return a " + functionType +" value, but there is not a return value");
    }

    public InvalidReturnException(String functionName, String functionType, int line, int error) {
        super("Error at line " + (line + 1) + ": function " + functionName.substring(SymbolTable.PREFIX.length()) + " should return a " + functionType +" value, but return value is not an expression");
    }
}
