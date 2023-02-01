package esercitazione5.syntax.const_;

import esercitazione5.visitor.Visitor;

public class BooleanConst extends AbstractConst<Boolean> {

    public BooleanConst(Boolean value){
        super(value);
    }

    @Override
    public Object accept(Visitor visitor){
        return visitor.visit(this);
    }
}
