package esercitazione5.symboltable.exception;

import esercitazione5.symboltable.SymbolTable;

public class IdAlreadyDefinedException extends RuntimeException{
    public IdAlreadyDefinedException(String id, int line) {
        super("Error at line " + (line + 1) + ": " + id.substring(SymbolTable.PREFIX.length()) + " is already defined");
    }
}
