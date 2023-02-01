package esercitazione5.syntax.statement;

import esercitazione5.visitor.Visitor;
import esercitazione5.syntax.expr.ExprOP;

import java.util.ArrayList;

public class WriteStatOP extends Statement {
    private ArrayList<ExprOP> exprList;
    private String mode;

    public WriteStatOP(ArrayList<ExprOP> exprList, String mode) {
        this.exprList = exprList;
        this.mode = mode;
    }

    public ArrayList<ExprOP> getExprList() {
        return exprList;
    }

    public String getMode() {
        return mode;
    }

    @Override
    public Object accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
