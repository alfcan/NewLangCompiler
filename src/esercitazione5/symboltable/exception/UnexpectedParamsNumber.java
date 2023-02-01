package esercitazione5.symboltable.exception;

import esercitazione5.symboltable.SymbolTable;

public class UnexpectedParamsNumber extends RuntimeException{
    public UnexpectedParamsNumber(String functionName, int expected, int found, int line){
        super("Error line at " + (line+1) + ": function call " + functionName.substring(SymbolTable.PREFIX.length()) + " Expected " + expected + " arguments but found " + found);
    }
}
