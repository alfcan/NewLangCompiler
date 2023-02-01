package esercitazione5.syntax.expr;

import esercitazione5.symboltable.SymbolTable;
import esercitazione5.visitor.Visitable;

public abstract class ExprOP implements Visitable {

    private SymbolTable scopeSymbolTable;
    private int line;

    public SymbolTable getScopeSymbolTable() {
        return scopeSymbolTable;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public void setScopeSymbolTable(SymbolTable scopeSymbolTable) {
        this.scopeSymbolTable = scopeSymbolTable;
    }
}
