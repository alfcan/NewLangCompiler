package esercitazione5.syntax.const_;

import esercitazione5.visitor.Visitor;

public class CharConst extends AbstractConst<Character>{

    public CharConst (Character value){
        super(value);
    }

    @Override
    public Object accept(Visitor visitor){
        return visitor.visit(this);
    }
}
