package esercitazione5.visitor;

import esercitazione5.syntax.const_.*;
import esercitazione5.syntax.decl.FunDeclOP;
import esercitazione5.syntax.decl.MainFunDeclOP;
import esercitazione5.syntax.decl.ParDeclOP;
import esercitazione5.syntax.decl.VarDeclOP;
import esercitazione5.syntax.expr.*;
import esercitazione5.syntax.statement.*;
import org.w3c.dom.*;
import esercitazione5.syntax.BodyOP;
import esercitazione5.syntax.Identifier;
import esercitazione5.syntax.ProgramOP;
import esercitazione5.syntax.idinit.IdInitOP;
import esercitazione5.syntax.idinit.IdInitObblOP;
import esercitazione5.syntax.type.TypeOP;

public class XMLVisitor implements Visitor{
    private Document document;

    public XMLVisitor(Document document) {
        this.document = document;
    }

    @Override
    public Object visit(ProgramOP programOP) {
        Element element = document.createElement("ProgramOP");
        element.appendChild((Element) programOP.getMainFunDecl().accept(this));
        programOP.getDeclList().forEach((decl) -> element.appendChild((Element) decl.accept(this)));
        return element;
    }

    @Override
    public Object visit(MainFunDeclOP mainFunDeclOP) {
        Element element = document.createElement("MainFunDeclOP");
        element.appendChild((Element) mainFunDeclOP.getFunDeclOP().accept(this));
        return element;
    }

    @Override
    public Object visit(VarDeclOP varDeclOP) {
        Element element = document.createElement("VarDeclOP");
        element.appendChild((Element) varDeclOP.getType().accept(this));
        varDeclOP.getIdList().forEach((id) -> element.appendChild((Element) id.accept(this)));
        return element;
    }

    @Override
    public Object visit(TypeOP typeOP) {
        Element element = document.createElement("TypeOP");
        element.setAttribute("type", typeOP.getTypeName());
        return element;
    }

    @Override
    public Object visit(BooleanConst booleanConst) {
        Element element = document.createElement("BooleanConst");
        element.setAttribute("value", String.valueOf(booleanConst.getValue()));
        return element;
    }

    @Override
    public Object visit(IntegerConst integerConst) {
        Element element = document.createElement("IntegerConst");
        element.setAttribute("value", String.valueOf(integerConst.getValue()));
        return element;
    }

    @Override
    public Object visit(FloatConst floatConst) {
        Element element = document.createElement("FloatConst");
        element.setAttribute("value", String.valueOf(floatConst.getValue()));
        return element;
    }

    @Override
    public Object visit(StringConst stringConst) {
        Element element = document.createElement("StringConst");
        element.setAttribute("value", stringConst.getValue());
        return element;
    }

    @Override
    public Object visit(CharConst charConst) {
        Element element = document.createElement("CharConst");
        element.setAttribute("value", String.valueOf(charConst.getValue()));
        return element;
    }

    @Override
    public Object visit(FunDeclOP funDeclOP) {
        Element element = document.createElement("FunDeclOP");
        element.appendChild((Element) funDeclOP.getId().accept(this));
        funDeclOP.getParamDeclList().forEach((param) -> element.appendChild((Element) param.accept(this)));
        element.appendChild((Element) funDeclOP.getType().accept(this));
        element.appendChild((Element) funDeclOP.getBody().accept(this));
        return element;
    }

    @Override
    public Object visit(BodyOP bodyOP) {
        Element element = document.createElement("Body");
        if (bodyOP.getVarDeclList() != null)
            bodyOP.getVarDeclList().forEach((decl) -> element.appendChild((Element) decl.accept(this)));
        if (bodyOP.getStatList() != null)
            bodyOP.getStatList().forEach((stat) -> element.appendChild((Element) stat.accept(this)));
        return element;
    }

    @Override
    public Object visit(ParDeclOP parDeclOP) {
        Element element = document.createElement("ParDeclOP");
        element.setAttribute("out", String.valueOf(parDeclOP.isOut()));
        element.appendChild((Element) parDeclOP.getType().accept(this));
        parDeclOP.getIdList().forEach((id) -> element.appendChild((Element) id.accept(this)));
        return element;
    }

    @Override
    public Object visit(IfStatOP ifStatOP) {
        Element element = document.createElement("IfStatOP");
        element.appendChild((Element) ifStatOP.getExpr().accept(this));
        element.appendChild((Element) ifStatOP.getBody().accept(this));
        ElseOP elseOP = ifStatOP.getElseOP();
        if (elseOP != null)
            element.appendChild((Element) elseOP.accept(this));
        return element;
    }

    @Override
    public Object visit(ElseOP elseOP) {
        Element element = document.createElement("ElseOP");
        element.appendChild((Element) elseOP.getBody().accept(this));
        return element;
    }

    @Override
    public Object visit(WhileStatOP whileStatOP) {
        Element element = document.createElement("WhileStatOP");
        element.appendChild((Element) whileStatOP.getExpr().accept(this));
        element.appendChild((Element) whileStatOP.getBody().accept(this));
        return element;
    }


    @Override
    public Object visit(ForStatOP forStatOP) {
        Element element = document.createElement("ForStatOP");
        element.setAttribute("Id_For", forStatOP.getId().toString());
        element.appendChild((Element) forStatOP.getId().accept(this));
        element.setAttribute("from", forStatOP.getExpr1().toString());
        element.appendChild((Element) forStatOP.getExpr1().accept(this));
        element.setAttribute("to", forStatOP.getExpr2().toString());
        element.appendChild((Element) forStatOP.getExpr2().accept(this));
        element.appendChild((Element) forStatOP.getBody().accept(this));
        return element;
    }

    @Override
    public Object visit(ReadStatOP readStatOP) {
        Element element = document.createElement("ReadStatOP");
        readStatOP.getIdList().forEach((id) -> element.appendChild((Element) id.accept(this)));
        StringConst stringConst = readStatOP.getStringConst();
        if (stringConst != null)
            element.appendChild((Element) stringConst.accept(this));
        return element;
    }

    @Override
    public Object visit(WriteStatOP writeStatOP) {
        Element element = document.createElement("WriteStatOP");
        writeStatOP.getExprList().forEach((expr) -> element.appendChild((Element) expr.accept(this)));
        element.setAttribute("mode", writeStatOP.getMode());
        return element;
    }

    @Override
    public Object visit(AssignStatOP assignStatOP) {
        Element element = document.createElement("AssignStatOP");
        assignStatOP.getIdList().forEach((id) -> element.appendChild((Element) id.accept(this)));
        assignStatOP.getExprList().forEach((expr) -> element.appendChild((Element) expr.accept(this)));
        return element;
    }

    @Override
    public Object visit(FunCallStatOP funCallStatOP) {
        Element element = document.createElement("FunCallStatOP");
        element.appendChild((Element) funCallStatOP.getId().accept(this));
        funCallStatOP.getExprList().forEach((expr) -> element.appendChild((Element) expr.accept(this)));
        return element;
    }

    @Override
    public Object visit(FunCallExprOP funCallExprOP) {
        Element element = document.createElement("FunCallExprOP");
        element.appendChild((Element) funCallExprOP.getId().accept(this));
        funCallExprOP.getExprList().forEach((expr) -> element.appendChild((Element) expr.accept(this)));
        return element;
    }

    @Override
    public Object visit(PlusOP plusOP) {
        Element element = document.createElement("PlusOP");
        element.appendChild((Element) plusOP.getExpr1().accept(this));
        element.appendChild((Element) plusOP.getExpr2().accept(this));
        return element;
    }

    @Override
    public Object visit(MinusOP minusOP) {
        Element element = document.createElement("MinusOP");
        element.appendChild((Element) minusOP.getExpr1().accept(this));
        element.appendChild((Element) minusOP.getExpr2().accept(this));
        return element;
    }

    @Override
    public Object visit(TimesOP timesOP) {
        Element element = document.createElement("TimesOP");
        element.appendChild((Element) timesOP.getExpr1().accept(this));
        element.appendChild((Element) timesOP.getExpr2().accept(this));
        return element;
    }

    @Override
    public Object visit(DivOP divOP) {
        Element element = document.createElement("DivOP");
        element.appendChild((Element) divOP.getExpr1().accept(this));
        element.appendChild((Element) divOP.getExpr2().accept(this));
        return element;
    }

    @Override
    public Object visit(OrOP orOP) {
        Element element = document.createElement("OrOP");
        element.appendChild((Element) orOP.getExpr1().accept(this));
        element.appendChild((Element) orOP.getExpr2().accept(this));
        return element;
    }

    @Override
    public Object visit(AndOP andOP) {
        Element element = document.createElement("AndOP");
        element.appendChild((Element) andOP.getExpr1().accept(this));
        element.appendChild((Element) andOP.getExpr2().accept(this));
        return element;
    }

    @Override
    public Object visit(StringConcatOP stringConcatOP) {
        Element element = document.createElement("StringConcatOP");
        element.appendChild((Element) stringConcatOP.getExpr1().accept(this));
        element.appendChild((Element) stringConcatOP.getExpr2().accept(this));
        return element;
    }

    @Override
    public Object visit(PowOP powOP) {
        Element element = document.createElement("PowOP");
        element.appendChild((Element) powOP.getExpr1().accept(this));
        element.appendChild((Element) powOP.getExpr2().accept(this));
        return element;
    }

    @Override
    public Object visit(GTOP gtOp) {
        Element element = document.createElement("GTOP");
        element.appendChild((Element) gtOp.getExpr1().accept(this));
        element.appendChild((Element) gtOp.getExpr2().accept(this));
        return element;
    }

    @Override
    public Object visit(GEOP geOp) {
        Element element = document.createElement("GEOP");
        element.appendChild((Element) geOp.getExpr1().accept(this));
        element.appendChild((Element) geOp.getExpr2().accept(this));
        return element;
    }

    @Override
    public Object visit(LTOP ltop) {
        Element element = document.createElement("LTOP");
        element.appendChild((Element) ltop.getExpr1().accept(this));
        element.appendChild((Element) ltop.getExpr2().accept(this));
        return element;
    }

    @Override
    public Object visit(LEOP leop) {
        Element element = document.createElement("LEOP");
        element.appendChild((Element) leop.getExpr1().accept(this));
        element.appendChild((Element) leop.getExpr2().accept(this));
        return element;
    }

    @Override
    public Object visit(EQOP eqop) {
        Element element = document.createElement("EQOP");
        element.appendChild((Element) eqop.getExpr1().accept(this));
        element.appendChild((Element) eqop.getExpr2().accept(this));
        return element;
    }

    @Override
    public Object visit(NEOP neop) {
        Element element = document.createElement("NEOP");
        element.appendChild((Element) neop.getExpr1().accept(this));
        element.appendChild((Element) neop.getExpr2().accept(this));
        return element;
    }

    @Override
    public Object visit(UminusOP uminusOP) {
        Element element = document.createElement("UminusOP");
        element.appendChild((Element) uminusOP.getExpr().accept(this));
        return element;
    }

    @Override
    public Object visit(NotOP notOP) {
        Element element = document.createElement("NotOP");
        element.appendChild((Element) notOP.getExpr().accept(this));
        return element;
    }

    @Override
    public Object visit(Identifier identifier) {
        Element element = document.createElement("Identifier");
        element.setAttribute("referenceId", String.valueOf(identifier.getReferenceId()));
        return element;
    }

    @Override
    public Object visit(ReturnOP returnOP) {
        Element element = document.createElement("ReturnOP");
        element.appendChild((Element) returnOP.getExpr().accept(this));
        return element;
    }

    @Override
    public Object visit(IdInitOP idInitOP) {
        Element element = document.createElement("IdInitOP");
        element.appendChild((Element) idInitOP.getId().accept(this));
        ExprOP exprOP = idInitOP.getExpr();
        if (exprOP != null)
            element.appendChild((Element) exprOP.accept(this));
        return element;
    }

    @Override
    public Object visit(IdInitObblOP idInitObblOP) {
        Element element = document.createElement("IdInitOP");
        element.appendChild((Element) idInitObblOP.getId().accept(this));
        element.appendChild((Element) idInitObblOP.getConstant().accept(this));
        return element;
    }
}