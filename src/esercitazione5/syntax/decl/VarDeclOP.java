package esercitazione5.syntax.decl;

import esercitazione5.syntax.idinit.AbstractIdInit;
import esercitazione5.syntax.type.TypeOP;
import esercitazione5.visitor.Visitor;

import java.util.ArrayList;

public class VarDeclOP extends AbstractDecl {
    private TypeOP type;
    private ArrayList<AbstractIdInit> idList;

    public VarDeclOP(TypeOP type, ArrayList<AbstractIdInit> idList){
        this.type = type;
        this.idList = idList;
    }


    public TypeOP getType() {
        return type;
    }

    public void setType(TypeOP type){
        this.type = type;
    }

    public ArrayList<AbstractIdInit> getIdList() {
        return idList;
    }

    @Override
    public Object accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
