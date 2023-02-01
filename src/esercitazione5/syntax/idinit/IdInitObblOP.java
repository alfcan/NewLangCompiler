package esercitazione5.syntax.idinit;

import esercitazione5.syntax.Identifier;
import esercitazione5.visitor.Visitor;
import esercitazione5.syntax.const_.AbstractConst;

public class IdInitObblOP extends AbstractIdInit{

    private AbstractConst constant;

    public IdInitObblOP(Identifier id, AbstractConst constant) {
        super(id);
        this.constant = constant;
    }

    public AbstractConst getConstant() {
        return constant;
    }

    @Override
    public Object accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
