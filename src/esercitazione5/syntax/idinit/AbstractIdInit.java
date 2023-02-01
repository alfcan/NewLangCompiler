package esercitazione5.syntax.idinit;

import esercitazione5.symboltable.SymbolTable;
import esercitazione5.syntax.Identifier;
import esercitazione5.visitor.Visitable;

public abstract class AbstractIdInit implements Visitable {

    private Identifier id;
    private SymbolTable scopeSymbolTable;
    private int line;

    public AbstractIdInit(Identifier id){
        this.id = id;
    }

    public void setId(Identifier id){
        this.id = id;
    }

    public Identifier getId() {
        return id;
    }

    public SymbolTable getScopeSymbolTable() {
        return scopeSymbolTable;
    }

    public void setScopeSymbolTable(SymbolTable scopeSymbolTable) {
        this.scopeSymbolTable = scopeSymbolTable;
    }
}
