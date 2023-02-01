package esercitazione5.syntax.const_;

import esercitazione5.visitor.Visitable;
import esercitazione5.syntax.expr.ExprOP;

public abstract class AbstractConst<T> extends ExprOP implements Visitable {

    private T value;

    public AbstractConst (T value){
        this.value = value;
    }

    public T getValue(){
        return this.value;
    }

    public void setValue(T value){
        this.value = value;
    }

}
