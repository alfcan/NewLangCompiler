package esercitazione5.syntax;

import esercitazione5.symboltable.SymbolTable;
import esercitazione5.syntax.decl.AbstractDecl;
import esercitazione5.syntax.decl.MainFunDeclOP;
import esercitazione5.visitor.Visitable;
import esercitazione5.visitor.Visitor;

import java.util.ArrayList;

public class ProgramOP implements Visitable {
    private ArrayList<AbstractDecl> declList;
    private MainFunDeclOP mainFunDecl;
    private SymbolTable scopeSymbolTable;

    public ProgramOP(ArrayList<AbstractDecl> declList, MainFunDeclOP mainFunDecl) {
        this.declList = declList;
        this.mainFunDecl = mainFunDecl;
    }

    public ArrayList<AbstractDecl> getDeclList() {
        return declList;
    }

    public MainFunDeclOP getMainFunDecl() {
        return mainFunDecl;
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
