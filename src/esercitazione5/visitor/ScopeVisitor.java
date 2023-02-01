package esercitazione5.visitor;

import esercitazione5.symboltable.SymbolTable;
import esercitazione5.syntax.BodyOP;
import esercitazione5.syntax.Identifier;
import esercitazione5.syntax.ProgramOP;
import esercitazione5.syntax.const_.*;
import esercitazione5.syntax.decl.*;
import esercitazione5.syntax.expr.*;
import esercitazione5.syntax.idinit.AbstractIdInit;
import esercitazione5.syntax.idinit.IdInitOP;
import esercitazione5.syntax.idinit.IdInitObblOP;
import esercitazione5.syntax.statement.*;
import esercitazione5.syntax.type.TypeOP;

import java.util.ArrayList;

public class ScopeVisitor implements Visitor{

    private ArrayList<String> stringTable;

    public ScopeVisitor(ArrayList<String> stringTable){
        this.stringTable = stringTable;
    }

    @Override
    public Object visit(BooleanConst booleanConst) {
        return null;
    }

    @Override
    public Object visit(IntegerConst integerConst) {
        return null;
    }

    @Override
    public Object visit(FloatConst floatConst) {
        return null;
    }

    @Override
    public Object visit(StringConst stringConst) {
        return null;
    }

    @Override
    public Object visit(CharConst charConst) {
        return null;
    }

    @Override
    public Object visit(FunCallStatOP funCallStatOP) {
        return null;
    }

    @Override
    public Object visit(PlusOP plusOP) {
        return null;
    }

    @Override
    public Object visit(MinusOP minusOP) {
        return null;
    }

    @Override
    public Object visit(TimesOP timesOP) {
        return null;
    }

    @Override
    public Object visit(DivOP divOP) {
        return null;
    }

    @Override
    public Object visit(OrOP orOP) {
        return null;
    }

    @Override
    public Object visit(AndOP andOP) {
        return null;
    }

    @Override
    public Object visit(StringConcatOP stringConcatOP) {
        return null;
    }

    @Override
    public Object visit(PowOP powOP) {
        return null;
    }

    @Override
    public Object visit(GTOP gtOp) {
        return null;
    }

    @Override
    public Object visit(GEOP geOp) {
        return null;
    }

    @Override
    public Object visit(UminusOP uminusOP) {
        return null;
    }

    @Override
    public Object visit(NotOP notOP) {
        return null;
    }

    @Override
    public Object visit(LTOP ltop) {
        return null;
    }

    @Override
    public Object visit(LEOP leop) {
        return null;
    }

    @Override
    public Object visit(EQOP eqop) {
        return null;
    }

    @Override
    public Object visit(NEOP neop) {
        return null;
    }

    @Override
    public Object visit(AssignStatOP assignStatOP) {
        return null;
    }

    @Override
    public Object visit(WriteStatOP writeStatOP) {
        return null;
    }

    @Override
    public Object visit(Identifier identifier) {
        return null;
    }

    @Override
    public Object visit(ReadStatOP readStatOP) {
        return null;
    }

    @Override
    public Object visit(VarDeclOP varDeclOP) {
        return null;
    }

    @Override
    public Object visit(ForStatOP forStatOP) {
        SymbolTable scopeSymbolTable = new SymbolTable();

        // identifier del for -> autodichiarazione
        Identifier idFor = forStatOP.getId();
        scopeSymbolTable.addId(stringTable.get(idFor.getReferenceId()),"integer", idFor.getLine());

        for (AbstractDecl decl : forStatOP.getBody().getVarDeclList()){
            if (decl instanceof VarDeclOP varDeclOP)
                generateVarDeclsSymbols(scopeSymbolTable, varDeclOP);
        }
        forStatOP.setScopeSymbolTable(scopeSymbolTable);
        forStatOP.getBody().accept(this);

        return forStatOP;
    }

    @Override
    public Object visit(WhileStatOP whileStatOP) {
        SymbolTable scopeSymbolTable = new SymbolTable();
        for (AbstractDecl decl : whileStatOP.getBody().getVarDeclList()){
            if (decl instanceof VarDeclOP varDeclOP)
                generateVarDeclsSymbols(scopeSymbolTable, varDeclOP);
        }
        whileStatOP.setScopeSymbolTable(scopeSymbolTable);
        whileStatOP.getBody().accept(this);

        return whileStatOP;
    }

    @Override
    public Object visit(ElseOP elseOP) {
        SymbolTable scopeSymbolTable = new SymbolTable();
        for (AbstractDecl decl : elseOP.getBody().getVarDeclList()){
            if (decl instanceof VarDeclOP varDeclOP)
                generateVarDeclsSymbols(scopeSymbolTable, varDeclOP);
        }
        elseOP.setScopeSymbolTable(scopeSymbolTable);
        elseOP.getBody().accept(this);

        return elseOP;
    }

    @Override
    public Object visit(IfStatOP ifStatOP) {
        SymbolTable scopeSymbolTable = new SymbolTable();
        for (AbstractDecl decl : ifStatOP.getBody().getVarDeclList()){
            if (decl instanceof VarDeclOP varDeclOP)
                generateVarDeclsSymbols(scopeSymbolTable, varDeclOP);
        }
        ifStatOP.setScopeSymbolTable(scopeSymbolTable);
        ifStatOP.getBody().accept(this);
        if (ifStatOP.getElseOP() != null)
            ifStatOP.getElseOP().accept(this);

        return ifStatOP;
    }

    @Override
    public Object visit(ReturnOP returnOP) {
        return null;
    }

    @Override
    public Object visit(TypeOP typeOP) {
        return null;
    }

    @Override
    public Object visit(BodyOP bodyOP) {
        if (bodyOP.getStatList() != null && bodyOP.getStatList().size() > 0)
            for (Statement statement : bodyOP.getStatList()) {
                statement.accept(this);
            }
        return bodyOP;
    }

    @Override
    public Object visit(ParDeclOP parDeclOP) {
        return null;
    }

    @Override
    public Object visit(FunDeclOP funDeclOP) {
        SymbolTable scopeSymbolTable = new SymbolTable();
        if (funDeclOP.getParamDeclList() != null && funDeclOP.getParamDeclList().size() > 0) {
            for (ParDeclOP parDeclOP : funDeclOP.getParamDeclList()) {
                for (Identifier id : parDeclOP.getIdList()) {
                    scopeSymbolTable.addId(stringTable.get(id.getReferenceId()), parDeclOP.getType().getTypeName(),
                            parDeclOP.isOut(), id.getLine());
                }
            }
        }
        for (VarDeclOP varDecl : funDeclOP.getBody().getVarDeclList()) {
            generateVarDeclsSymbols(scopeSymbolTable, varDecl);
        }
        funDeclOP.setScopeSymbolTable(scopeSymbolTable);
        funDeclOP.getBody().accept(this);

        return funDeclOP;
    }

    private void generateVarDeclsSymbols(SymbolTable scopeSymbolTable, VarDeclOP varDecl) {
        for (AbstractIdInit idInit : varDecl.getIdList()){
            if (varDecl.getType().getTypeName().equals("var")){
                IdInitObblOP idInitObbl = (IdInitObblOP) idInit;
                if (idInitObbl.getConstant() instanceof BooleanConst)
                    scopeSymbolTable.addId(stringTable.get(idInit.getId().getReferenceId()), "bool", idInitObbl.getId().getLine());
                else if (idInitObbl.getConstant() instanceof CharConst)
                    scopeSymbolTable.addId(stringTable.get(idInit.getId().getReferenceId()), "char", idInitObbl.getId().getLine());
                else if (idInitObbl.getConstant() instanceof FloatConst)
                    scopeSymbolTable.addId(stringTable.get(idInit.getId().getReferenceId()), "float", idInitObbl.getId().getLine());
                else if (idInitObbl.getConstant() instanceof IntegerConst)
                    scopeSymbolTable.addId(stringTable.get(idInit.getId().getReferenceId()), "integer", idInitObbl.getId().getLine());
                else if (idInitObbl.getConstant() instanceof StringConst)
                    scopeSymbolTable.addId(stringTable.get(idInit.getId().getReferenceId()), "string", idInitObbl.getId().getLine());
            } else
                scopeSymbolTable.addId(stringTable.get(idInit.getId().getReferenceId()), varDecl.getType().getTypeName(), idInit.getId().getLine());
        }
    }

    @Override
    public Object visit(MainFunDeclOP mainFunDeclOP) {
        mainFunDeclOP.getFunDeclOP().accept(this);
        return mainFunDeclOP;
    }

    @Override
    public Object visit(IdInitOP idInitOP) {
        return null;
    }

    @Override
    public Object visit(ProgramOP programOP) {
        SymbolTable scopeSymbolTable = new SymbolTable();
        if (programOP.getDeclList() != null && programOP.getDeclList().size() > 0){
            for(AbstractDecl decl : programOP.getDeclList()){
                if (decl instanceof VarDeclOP){
                    VarDeclOP varDecl = (VarDeclOP) decl;
                    generateVarDeclsSymbols(scopeSymbolTable, varDecl);
                } else if (decl instanceof FunDeclOP) {
                    FunDeclOP funDecl = (FunDeclOP) decl;
                    ArrayList<String> types = new ArrayList<>();
                    ArrayList<Boolean> modes = new ArrayList<>();

                    if (funDecl.getParamDeclList() != null && funDecl.getParamDeclList().size() > 0){
                        for(ParDeclOP parDeclOP : funDecl.getParamDeclList()){
                            for(Identifier id : parDeclOP.getIdList()){
                                modes.add(parDeclOP.isOut());
                                types.add(parDeclOP.getType().getTypeName());
                            }
                        }
                    }
                    scopeSymbolTable.addId(stringTable.get(funDecl.getId().getReferenceId()), modes, types, funDecl.getType().getTypeName(), funDecl.getId().getLine());
                }
            }
        }

        MainFunDeclOP main = programOP.getMainFunDecl();
        ArrayList<String> types = new ArrayList<>();
        ArrayList<Boolean> modes = new ArrayList<>();
        if (main.getFunDeclOP().getParamDeclList() != null && main.getFunDeclOP().getParamDeclList().size() > 0){
            for(ParDeclOP parDeclOP : main.getFunDeclOP().getParamDeclList()){
                for(Identifier id : parDeclOP.getIdList()){
                    modes.add(parDeclOP.isOut());
                    types.add(parDeclOP.getType().getTypeName());
                }
            }
        }
        scopeSymbolTable.addId(stringTable.get(main.getFunDeclOP().getId().getReferenceId()), modes, types,
                main.getFunDeclOP().getType().getTypeName(), main.getFunDeclOP().getId().getLine());

        programOP.setScopeSymbolTable(scopeSymbolTable);

        for(AbstractDecl decl : programOP.getDeclList()){
            if (decl instanceof FunDeclOP)
                decl.accept(this);
        }

        programOP.getMainFunDecl().accept(this);

        return programOP;
    }

    @Override
    public Object visit(IdInitObblOP idInitObblOP) {
        return null;
    }

    @Override
    public Object visit(FunCallExprOP funCallExprOP) {
        return null;
    }
}
