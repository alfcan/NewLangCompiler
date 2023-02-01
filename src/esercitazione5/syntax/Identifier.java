package esercitazione5.syntax;

import esercitazione5.syntax.expr.ExprOP;
import esercitazione5.visitor.Visitor;

public class Identifier extends ExprOP {
    private int referenceId;
    private int line;

    public Identifier (int referenceId) {
        this.referenceId = referenceId;
    }

    public int getReferenceId() {
        return referenceId;
    }

    public int getLine(){
        return line;
    }

    public void setLine(int line){
        this.line = line;
    }

    @Override
    public Object accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
