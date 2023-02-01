package esercitazione5.syntax.expr;

import esercitazione5.syntax.Identifier;
import esercitazione5.visitor.Visitor;

import java.util.ArrayList;

public class FunCallExprOP extends ExprOP {

    private Identifier id;
    private ArrayList<ExprOP> exprList;

    public FunCallExprOP(Identifier id, ArrayList<ExprOP> exprList){
        this.id = id;
        this.exprList = exprList;
    }

    public FunCallExprOP(Identifier id){
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
