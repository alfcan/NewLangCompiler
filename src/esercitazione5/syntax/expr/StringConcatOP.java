package esercitazione5.syntax.expr;

import esercitazione5.visitor.Visitor;

public class StringConcatOP extends ExprOP{

    private ExprOP expr1;
    private ExprOP expr2;

    public StringConcatOP(ExprOP expr1, ExprOP expr2){
        this.expr1 = expr1;
        this.expr2 = expr2;
    }

    public ExprOP getExpr1() {
        return expr1;
    }

    public ExprOP getExpr2() {
        return expr2;
    }

    @Override
    public Object accept (Visitor visitor){
        return visitor.visit(this);
    }
}
