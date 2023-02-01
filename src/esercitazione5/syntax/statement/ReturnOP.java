package esercitazione5.syntax.statement;

import esercitazione5.visitor.Visitor;
import esercitazione5.syntax.expr.ExprOP;

public class ReturnOP extends Statement{

    private ExprOP expr;

    public ReturnOP (ExprOP expr){
        this.expr = expr;
    }

    public ReturnOP (){
        this.expr = null;
    }

    public ExprOP getExpr() {
        return expr;
    }

    @Override
    public Object accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
