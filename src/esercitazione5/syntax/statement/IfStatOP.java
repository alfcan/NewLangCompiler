package esercitazione5.syntax.statement;

import esercitazione5.syntax.BodyOP;
import esercitazione5.visitor.Visitor;
import esercitazione5.syntax.expr.ExprOP;

public class IfStatOP extends Statement {
    private ExprOP expr;
    private BodyOP body;
    private ElseOP elseOP;

    public IfStatOP(ExprOP expr, BodyOP body, ElseOP elseOP) {
        this.expr = expr;
        this.body = body;
        this.elseOP = elseOP;
    }

    public ExprOP getExpr() {
        return expr;
    }

    public BodyOP getBody() {
        return body;
    }

    public ElseOP getElseOP() {
        return elseOP;
    }

    @Override
    public Object accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
