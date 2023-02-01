package esercitazione5.syntax.type;

import esercitazione5.visitor.Visitable;
import esercitazione5.visitor.Visitor;

public class TypeOP implements Visitable {

    private String type;

    public TypeOP(String type){
        this.type = type;
    }

    public String getTypeName() {
        return type;
    }

    @Override
    public Object accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
