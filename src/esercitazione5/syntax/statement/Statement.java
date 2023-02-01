package esercitazione5.syntax.statement;

import esercitazione5.symboltable.SymbolTable;
import esercitazione5.visitor.Visitable;

public abstract class Statement implements Visitable {
    private SymbolTable scopeSymbolTable;
    private int line;

    public SymbolTable getScopeSymbolTable() {
        return scopeSymbolTable;
    }

    public void setScopeSymbolTable(SymbolTable scopeSymbolTable) {
        this.scopeSymbolTable = scopeSymbolTable;
    }

    public int getLine(){
        return this.line;
    }

    public void setLine(int line){
        this.line = line;
    }

}