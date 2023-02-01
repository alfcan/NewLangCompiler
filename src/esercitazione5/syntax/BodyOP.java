package esercitazione5.syntax;

import esercitazione5.symboltable.SymbolTable;
import esercitazione5.syntax.decl.VarDeclOP;
import esercitazione5.visitor.Visitable;
import esercitazione5.visitor.Visitor;
import esercitazione5.syntax.statement.Statement;
import java_cup.runtime.Symbol;

import java.util.ArrayList;

public class BodyOP implements Visitable {

    private ArrayList<VarDeclOP> varDeclList;
    private ArrayList<Statement> statList;
    private SymbolTable scopeSymbolTable;

    public BodyOP(ArrayList<VarDeclOP> varDeclList, ArrayList<Statement> statList) {
        this.varDeclList = varDeclList;
        this.statList = statList;
    }

    public BodyOP(ArrayList<VarDeclOP> varDeclList) {
        this.varDeclList = varDeclList;
        this.statList = null;
    }

    public ArrayList<VarDeclOP> getVarDeclList() {
        return varDeclList;
    }

    public ArrayList<Statement> getStatList() {
        return statList;
    }

    public SymbolTable getScopeSymbolTable() {
        return scopeSymbolTable;
    }

    public void setScopeSymbolTable(SymbolTable scopeSymbolTable) {
        this.scopeSymbolTable = scopeSymbolTable;
    }

    @Override
    public Object accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
