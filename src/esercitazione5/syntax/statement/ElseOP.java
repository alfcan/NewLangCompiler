package esercitazione5.syntax.statement;

import esercitazione5.syntax.BodyOP;
import esercitazione5.visitor.Visitor;

public class ElseOP extends Statement {
    private BodyOP body;

    public ElseOP(BodyOP body) {
        this.body = body;
    }

    public BodyOP getBody() {
        return body;
    }

    @Override
    public Object accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
