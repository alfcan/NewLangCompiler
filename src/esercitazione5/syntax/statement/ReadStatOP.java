package esercitazione5.syntax.statement;

import esercitazione5.syntax.Identifier;
import esercitazione5.visitor.Visitor;
import esercitazione5.syntax.const_.StringConst;

import java.util.ArrayList;

public class ReadStatOP extends Statement {

    private ArrayList<Identifier> idList;
    private StringConst stringConst;

    public ReadStatOP(ArrayList<Identifier> idList, StringConst stringConst) {
        this.idList = idList;
        this.stringConst = stringConst;
    }

    public ReadStatOP(ArrayList<Identifier> idList) {
        this.idList = idList;
        this.stringConst = null;
    }

    public ArrayList<Identifier> getIdList() {
        return idList;
    }

    public StringConst getStringConst() {
        return stringConst;
    }

    @Override
    public Object accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
