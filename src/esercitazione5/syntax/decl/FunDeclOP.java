package esercitazione5.syntax.decl;

import esercitazione5.syntax.BodyOP;
import esercitazione5.syntax.Identifier;
import esercitazione5.syntax.type.TypeOP;
import esercitazione5.visitor.Visitor;

import java.util.ArrayList;

public class FunDeclOP extends AbstractDecl {

    private Identifier id;
    private ArrayList<ParDeclOP> paramDeclList;
    private TypeOP type;
    private BodyOP body;

    public FunDeclOP(Identifier id, ArrayList<ParDeclOP> paramDeclList, TypeOP type, BodyOP body) {
        this.id = id;
        this.paramDeclList = paramDeclList;
        this.type = type;
        this.body = body;
    }

    public Identifier getId() {
        return id;
    }

    public ArrayList<ParDeclOP> getParamDeclList() {
        return paramDeclList;
    }

    public TypeOP getType() {
        return type;
    }

    public BodyOP getBody() {
        return body;
    }

    @Override
    public Object accept(Visitor visitor) {
        return visitor.visit(this);
    }
}