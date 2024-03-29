package esercitazione5.visitor;

import esercitazione5.syntax.Identifier;
import esercitazione5.syntax.ProgramOP;
import esercitazione5.syntax.const_.*;
import esercitazione5.syntax.decl.FunDeclOP;
import esercitazione5.syntax.decl.MainFunDeclOP;
import esercitazione5.syntax.decl.ParDeclOP;
import esercitazione5.syntax.decl.VarDeclOP;
import esercitazione5.syntax.expr.*;
import esercitazione5.syntax.BodyOP;
import esercitazione5.syntax.idinit.IdInitOP;
import esercitazione5.syntax.idinit.IdInitObblOP;
import esercitazione5.syntax.statement.*;
import esercitazione5.syntax.type.TypeOP;

public interface Visitor {
    Object visit(BooleanConst booleanConst);
    Object visit(IntegerConst integerConst);
    Object visit(FloatConst floatConst);
    Object visit(StringConst stringConst);
    Object visit(CharConst charConst);
    Object visit(FunCallStatOP funCallStatOP);
    Object visit(PlusOP plusOP);
    Object visit(MinusOP minusOP);
    Object visit(TimesOP timesOP);
    Object visit(DivOP divOP);
    Object visit(OrOP orOP);
    Object visit(AndOP andOP);
    Object visit(StringConcatOP stringConcatOP);
    Object visit(PowOP powOP);
    Object visit(GTOP gtOp);
    Object visit(GEOP geOp);
    Object visit(UminusOP uminusOP);
    Object visit(NotOP notOP);
    Object visit(LTOP ltop);
    Object visit(LEOP leop);
    Object visit(EQOP eqop);
    Object visit(NEOP neop);
    Object visit(AssignStatOP assignStatOP);
    Object visit(WriteStatOP writeStatOP);
    Object visit(Identifier identifier);
    Object visit(ReadStatOP readStatOP);
    Object visit(VarDeclOP varDeclOP);
    Object visit(ForStatOP forStatOP);
    Object visit(WhileStatOP whileStatOP);
    Object visit(ElseOP elseOP);
    Object visit(IfStatOP ifStatOP);
    Object visit(ReturnOP returnOP);
    Object visit(TypeOP typeOP);
    Object visit(BodyOP bodyOP);
    Object visit(ParDeclOP parDeclOP);
    Object visit(FunDeclOP funDeclOP);
    Object visit(MainFunDeclOP mainFunDeclOP);
    Object visit(IdInitOP idInitOP);
    Object visit(ProgramOP programOP);
    Object visit(IdInitObblOP idInitObblOP);
    Object visit(FunCallExprOP funCallExprOP);
}
