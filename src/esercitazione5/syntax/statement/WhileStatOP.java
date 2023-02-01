package esercitazione5.syntax.statement;

import esercitazione5.syntax.BodyOP;
import esercitazione5.visitor.Visitor;
import esercitazione5.syntax.expr.ExprOP;

public class WhileStatOP extends Statement {
    private ExprOP expr;
    private BodyOP body;

    public WhileStatOP(ExprOP expr, BodyOP body) {
        this.expr = expr;
        this.body = body;
    }

    public ExprOP getExpr() {
        return expr;
    }

    public BodyOP getBody() {
        return body;
    }

    @Override
    public Object accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
