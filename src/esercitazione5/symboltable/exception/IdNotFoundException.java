package esercitazione5.symboltable.exception;

import esercitazione5.symboltable.SymbolTable;

public class IdNotFoundException extends RuntimeException {
    public IdNotFoundException(String id) {
        super("Identifier " + id.substring(SymbolTable.PREFIX.length()) + " not found");
    }
}
