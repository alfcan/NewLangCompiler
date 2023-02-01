package esercitazione5.syntax.expr;

import esercitazione5.visitor.Visitor;

public class NotOP extends ExprOP{

    private ExprOP expr;

    public NotOP(ExprOP expr){
        this.expr = expr;
    }

    public ExprOP getExpr() {
        return expr;
    }

    @Override
    public Object accept (Visitor visitor){
        return visitor.visit(this);
    }
}
