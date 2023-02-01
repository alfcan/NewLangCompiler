package esercitazione5.syntax.const_;

import esercitazione5.visitor.Visitor;

public class StringConst extends AbstractConst<String>{

    public StringConst (String value){
        super(value);
    }

    @Override
    public Object accept(Visitor visitor){
        return visitor.visit(this);
    }
}
