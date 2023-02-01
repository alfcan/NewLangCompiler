package esercitazione5.syntax.decl;

import esercitazione5.syntax.Identifier;
import esercitazione5.syntax.type.TypeOP;
import esercitazione5.visitor.Visitor;

import java.util.ArrayList;

public class ParDeclOP extends AbstractDecl {

    private boolean out;
    private TypeOP type;
    private ArrayList<Identifier> idList;

    public ParDeclOP(boolean out, TypeOP type, ArrayList<Identifier> idList) {
        this.out = out;
        this.type = type;
        this.idList = idList;
    }

    public ParDeclOP(TypeOP type, ArrayList<Identifier> idList) {
        this.out = false;
        this.type = type;
        this.idList = idList;
    }

    public boolean isOut() {
        return out;
    }

    public TypeOP getType() {
        return type;
    }

    public ArrayList<Identifier> getIdList() {
        return idList;
    }

    @Override
    public Object accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
