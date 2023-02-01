package esercitazione5.syntax.const_;

import esercitazione5.visitor.Visitor;

public class FloatConst extends AbstractConst<Float> {

    public FloatConst(Float value){
        super(value);
    }

    @Override
    public Object accept(Visitor visitor){
        return visitor.visit(this);
    }

}
