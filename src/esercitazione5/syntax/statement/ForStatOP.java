package esercitazione5.syntax.statement;

import esercitazione5.syntax.BodyOP;
import esercitazione5.syntax.Identifier;
import esercitazione5.syntax.expr.ExprOP;
import esercitazione5.visitor.Visitor;
import esercitazione5.syntax.const_.IntegerConst;

public class ForStatOP extends Statement {

    private Identifier id;
    private ExprOP expr1;
    private ExprOP expr2;
    private BodyOP body;

    public ForStatOP(Identifier id, ExprOP expr1, ExprOP expr2, BodyOP body) {
        this.id = id;
        this.expr1 = expr1;
        this.expr2 = expr2;
        this.body = body;
    }

    public Identifier getId() {
        return id;
    }

    public ExprOP getExpr1() {
        return expr1;
    }

    public ExprOP getExpr2() {
        return expr2;
    }

    public BodyOP getBody() {
        return body;
    }

    @Override
    public Object accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
