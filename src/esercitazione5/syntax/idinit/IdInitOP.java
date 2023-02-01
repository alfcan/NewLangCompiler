package esercitazione5.syntax.idinit;

import esercitazione5.syntax.Identifier;
import esercitazione5.visitor.Visitor;
import esercitazione5.syntax.expr.ExprOP;

public class IdInitOP extends AbstractIdInit {
    private ExprOP expr;

    public IdInitOP(Identifier id, ExprOP expr) {
        super(id);
        this.expr = expr;
    }

    public IdInitOP(Identifier id) {
        super(id);
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
