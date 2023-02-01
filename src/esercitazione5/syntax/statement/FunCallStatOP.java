package esercitazione5.syntax.statement;

import esercitazione5.syntax.Identifier;
import esercitazione5.visitor.Visitor;
import esercitazione5.syntax.expr.ExprOP;

import java.util.ArrayList;

public class FunCallStatOP extends Statement {

    private Identifier id;
    private ArrayList<ExprOP> exprList;

    public FunCallStatOP(Identifier id, ArrayList<ExprOP> exprList){
        this.id = id;
        this.exprList = exprList;
    }

    public FunCallStatOP(Identifier id){
        this.id = id;
        this.exprList = new ArrayList<ExprOP>();
    }

    public Identifier getId() {
        return id;
    }

    public ArrayList<ExprOP> getExprList() {
        return exprList;
    }


    @Override
    public Object accept(Visitor visitor){
        return visitor.visit(this);
    }

}
