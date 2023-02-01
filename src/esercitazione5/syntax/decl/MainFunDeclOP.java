package esercitazione5.syntax.decl;

import esercitazione5.visitor.Visitor;

public class MainFunDeclOP extends AbstractDecl{

    private FunDeclOP funDeclOP;

    public MainFunDeclOP(FunDeclOP funDeclOP){
        this.funDeclOP = funDeclOP;
    }

    public FunDeclOP getFunDeclOP(){
        return funDeclOP;
    }

    @Override
    public Object accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
