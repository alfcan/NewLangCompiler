package esercitazione5.syntax.decl;

import esercitazione5.symboltable.SymbolTable;
import esercitazione5.visitor.Visitable;

public abstract class AbstractDecl implements Visitable {

    private SymbolTable scopeSymbolTable;

    public SymbolTable getScopeSymbolTable() {
        return scopeSymbolTable;
    }

    public void setScopeSymbolTable(SymbolTable scopeSymbolTable) {
        this.scopeSymbolTable = scopeSymbolTable;
    }
}
