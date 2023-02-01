package esercitazione5.visitor;

public interface Visitable {
    Object accept(Visitor visitor);
}
