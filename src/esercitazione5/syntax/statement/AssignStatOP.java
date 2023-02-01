package esercitazione5.syntax.statement;

import esercitazione5.syntax.Identifier;
import esercitazione5.visitor.Visitor;
import esercitazione5.syntax.expr.ExprOP;

import java.util.ArrayList;
public class AssignStatOP extends Statement {
    private ArrayList<Identifier> idList;
    private ArrayList<ExprOP> exprList;

    public AssignStatOP(ArrayList<Identifier> idList, ArrayList<ExprOP> exprList) {
        this.idList = idList;
        this.exprList = exprList;
    }

    public ArrayList<ExprOP> getExprList() {
        return exprList;
    }

    public ArrayList<Identifier> getIdList() {
        return idList;
    }

    @Override
    public Object accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
