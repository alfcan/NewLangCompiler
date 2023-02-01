package esercitazione5.syntax.const_;

import esercitazione5.visitor.Visitor;

public class IntegerConst extends AbstractConst<Integer>{

    public IntegerConst (Integer value){
        super(value);
    }

    @Override
    public Object accept(Visitor visitor){
        return visitor.visit(this);
    }
}
