package esercitazione5.visitor;

import esercitazione5.symboltable.*;
import esercitazione5.symboltable.Record;
import esercitazione5.symboltable.exception.*;
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

public class TypeCheckVisitor implements Visitor{

    private ArrayList<String> stringTable;

    public TypeCheckVisitor(ArrayList<String> stringTable){
        this.stringTable = stringTable;
    }

    @Override
    public Object visit(BooleanConst booleanConst) {
        TypeOP typeOP = new TypeOP("bool");
        return typeOP;
    }

    @Override
    public Object visit(IntegerConst integerConst) {
        TypeOP typeOP = new TypeOP("integer");
        return typeOP;
    }

    @Override
    public Object visit(FloatConst floatConst) {
        TypeOP typeOP = new TypeOP("float");
        return typeOP;
    }

    @Override
    public Object visit(StringConst stringConst) {
        TypeOP typeOP = new TypeOP("string");
        return typeOP;
    }

    @Override
    public Object visit(CharConst charConst) {
        TypeOP typeOP = new TypeOP("char");
        return typeOP;
    }

    @Override
    public Object visit(FunCallStatOP funCallStatOP) {
        Record record = SymbolTable.lookup(stringTable.get(funCallStatOP.getId().getReferenceId()));

        if (record instanceof FunctionRecord functionRecord){
            if (functionRecord.getParamsTypes().size() == funCallStatOP.getExprList().size()){
                for (int i = 0; i < functionRecord.getParamsTypes().size(); i++){
                    ExprOP expr = funCallStatOP.getExprList().get(i);

                    if (functionRecord.getParamsIsOut().get(i)){
                        if (expr instanceof Identifier id) {
                            if (!(SymbolTable.lookup(stringTable.get(id.getReferenceId())) instanceof VariableRecord)){
                                throw new NotFoundVariableInOutParam(functionRecord.getLexeme(), id.getLine());
                            }
                        } else {
                            throw new NotFoundVariableInOutParam(functionRecord.getLexeme(), expr.getLine());
                        }
                    }

                    TypeOP typeExpr = (TypeOP) expr.accept(this);

                    if (!functionRecord.getParamsTypes().get(i).equals(typeExpr.getTypeName()))
                        throw new TypeNotCompatibleException(functionRecord.getParamsTypes().get(i), typeExpr.getTypeName(), funCallStatOP.getLine());
                }
            } else {
                throw new UnexpectedParamsNumber(stringTable.get(funCallStatOP.getId().getReferenceId()), functionRecord.getParamsTypes().size(), funCallStatOP.getExprList().size(), funCallStatOP.getLine());
            }
        } else {
            throw new MethodCallException(stringTable.get(funCallStatOP.getId().getReferenceId()), funCallStatOP.getLine());
        }

        return new TypeOP(functionRecord.getReturnType());
    }

    @Override
    public Object visit(PlusOP plusOP) {
        TypeOP typeArg1 = (TypeOP) plusOP.getExpr1().accept(this);
        TypeOP typeArg2 = (TypeOP) plusOP.getExpr2().accept(this);
        return optype2(typeArg1, typeArg2, "plus", plusOP.getLine());
    }

    @Override
    public Object visit(MinusOP minusOP) {
        TypeOP typeArg1 = (TypeOP) minusOP.getExpr1().accept(this);
        TypeOP typeArg2 = (TypeOP) minusOP.getExpr2().accept(this);
        return optype2(typeArg1, typeArg2, "minus", minusOP.getLine());
    }

    @Override
    public Object visit(TimesOP timesOP) {
        TypeOP typeArg1 = (TypeOP) timesOP.getExpr1().accept(this);
        TypeOP typeArg2 = (TypeOP) timesOP.getExpr2().accept(this);
        return optype2(typeArg1, typeArg2, "times", timesOP.getLine());
    }

    @Override
    public Object visit(DivOP divOP) {
        TypeOP typeArg1 = (TypeOP) divOP.getExpr1().accept(this);
        TypeOP typeArg2 = (TypeOP) divOP.getExpr2().accept(this);
        return optype2(typeArg1, typeArg2, "div", divOP.getLine());
    }

    @Override
    public Object visit(OrOP orOP) {
        TypeOP typeArg1 = (TypeOP) orOP.getExpr1().accept(this);
        TypeOP typeArg2 = (TypeOP) orOP.getExpr2().accept(this);
        return optype2(typeArg1, typeArg2, "or", orOP.getLine());
    }

    @Override
    public Object visit(AndOP andOP) {
        TypeOP typeArg1 = (TypeOP) andOP.getExpr1().accept(this);
        TypeOP typeArg2 = (TypeOP) andOP.getExpr2().accept(this);
        return optype2(typeArg1, typeArg2, "and", andOP.getLine());
    }

    @Override
    public Object visit(StringConcatOP stringConcatOP) {
        TypeOP typeArg1 = (TypeOP) stringConcatOP.getExpr1().accept(this);
        TypeOP typeArg2 = (TypeOP) stringConcatOP.getExpr2().accept(this);
        return optype2(typeArg1, typeArg2, "stringConcat", stringConcatOP.getLine());
    }

    @Override
    public Object visit(PowOP powOP) {
        TypeOP typeArg1 = (TypeOP) powOP.getExpr1().accept(this);
        TypeOP typeArg2 = (TypeOP) powOP.getExpr2().accept(this);
        return optype2(typeArg1, typeArg2, "pow", powOP.getLine());
    }

    @Override
    public Object visit(GTOP gtOp) {
        TypeOP typeArg1 = (TypeOP) gtOp.getExpr1().accept(this);
        TypeOP typeArg2 = (TypeOP) gtOp.getExpr2().accept(this);
        return optype2(typeArg1, typeArg2, "gt", gtOp.getLine());
    }

    @Override
    public Object visit(GEOP geOp) {
        TypeOP typeArg1 = (TypeOP) geOp.getExpr1().accept(this);
        TypeOP typeArg2 = (TypeOP) geOp.getExpr2().accept(this);
        return optype2(typeArg1, typeArg2, "ge", geOp.getLine());
    }

    @Override
    public Object visit(UminusOP uminusOP) {
        TypeOP typeArg = (TypeOP) uminusOP.getExpr().accept(this);
        return optype1(typeArg, "umins", uminusOP.getLine());
    }

    @Override
    public Object visit(NotOP notOP) {
        TypeOP typeArg = (TypeOP) notOP.getExpr().accept(this);
        return optype1(typeArg, "not", notOP.getLine());
    }

    @Override
    public Object visit(LTOP ltop) {
        TypeOP typeArg1 = (TypeOP) ltop.getExpr1().accept(this);
        TypeOP typeArg2 = (TypeOP) ltop.getExpr2().accept(this);
        return optype2(typeArg1, typeArg2, "lt", ltop.getLine());
    }

    @Override
    public Object visit(LEOP leop) {
        TypeOP typeArg1 = (TypeOP) leop.getExpr1().accept(this);
        TypeOP typeArg2 = (TypeOP) leop.getExpr2().accept(this);
        return optype2(typeArg1, typeArg2, "le", leop.getLine());
    }

    @Override
    public Object visit(EQOP eqop) {
        TypeOP typeArg1 = (TypeOP) eqop.getExpr1().accept(this);
        TypeOP typeArg2 = (TypeOP) eqop.getExpr2().accept(this);
        return optype2(typeArg1, typeArg2, "eq", eqop.getLine());
    }

    @Override
    public Object visit(NEOP neop) {
        TypeOP typeArg1 = (TypeOP) neop.getExpr1().accept(this);
        TypeOP typeArg2 = (TypeOP) neop.getExpr2().accept(this);
        return optype2(typeArg1, typeArg2, "ne", neop.getLine());
    }

    @Override
    public Object visit(AssignStatOP assignStatOP) {
        if (assignStatOP.getIdList().size() != assignStatOP.getExprList().size())
            throw new InvalidAssignException(assignStatOP.getLine());

        for (int i = 0; i<assignStatOP.getIdList().size(); i++){
            Identifier id = assignStatOP.getIdList().get(i);
            ExprOP expr = assignStatOP.getExprList().get(i);
            Record record = SymbolTable.lookup(stringTable.get(id.getReferenceId()));

            TypeOP typeExpr = (TypeOP) expr.accept(this);
            if (record instanceof VariableRecord variableRecord){
                if (!variableRecord.getType().equals(typeExpr.getTypeName()))
                    throw new TypeNotCompatibleException(variableRecord.getType(), id.getLine());
            } else if (record instanceof ParamRecord paramRecord) {
                if (!paramRecord.getType().equals(typeExpr.getTypeName()))
                    throw new TypeNotCompatibleException(paramRecord.getType(), id.getLine());
            } else if (record instanceof FunctionRecord functionRecord){
                if (!functionRecord.getReturnType().equals(typeExpr.getTypeName()))
                    throw new TypeNotCompatibleException(functionRecord.getReturnType(), id.getLine());
            }
        }
        return null;
    }

    @Override
    public Object visit(WriteStatOP writeStatOP) {
        for (ExprOP expr : writeStatOP.getExprList()) {
            expr.accept(this);
        }
        return null;
    }

    @Override
    public Object visit(Identifier identifier) {
        Record record = SymbolTable.lookup(stringTable.get(identifier.getReferenceId()));
        if (record instanceof VariableRecord variableRecord)
            return new TypeOP(variableRecord.getType());
        else if (record instanceof ParamRecord paramRecord)
            return new TypeOP(paramRecord.getType());
        else if (record instanceof FunctionRecord functionRecord) {
            return functionRecord;
        }
        return null;
    }

    @Override
    public Object visit(ReadStatOP readStatOP) {
        for (Identifier identifier : readStatOP.getIdList()){
            Object id = identifier.accept(this);
            if (id instanceof FunctionRecord functionRecord)
                throw new VariableException(readStatOP.getLine(), functionRecord.getLexeme());
        }
        return null;
    }

    @Override
    public Object visit(VarDeclOP varDeclOP) {
        if(varDeclOP.getType() == null){
            return varDeclOP;
        }

        for(AbstractIdInit id : varDeclOP.getIdList() ){
            if (id instanceof IdInitOP idInitOP) {
                if (idInitOP.getExpr() != null) {
                    TypeOP varType = (TypeOP) idInitOP.getExpr().accept(this);
                    Record record = SymbolTable.lookup(stringTable.get(id.getId().getReferenceId()));
                    VariableRecord variable = (VariableRecord) record;
                    if (!variable.getType().equals(varType.getTypeName())) {
                        throw new TypeNotCompatibleException(varType.getTypeName(), id.getId().getLine());
                    }
                }
            }
        }
        return null;
    }

    @Override
    public Object visit(ForStatOP forStatOP) {
        SymbolTable.enterScope(forStatOP.getScopeSymbolTable());

        Identifier idFor = forStatOP.getId();
        Record record = SymbolTable.lookup(stringTable.get(idFor.getReferenceId()));

        if (record instanceof VariableRecord variableRecord){
            if (!variableRecord.getType().equals("integer"))
                throw new TypeNotCompatibleException(variableRecord.getType(), idFor.getLine());
        }

        if (forStatOP.getExpr1().accept(this) instanceof TypeOP type1 &&
                forStatOP.getExpr2().accept(this) instanceof TypeOP type2) {
            if (!type1.getTypeName().equals("integer")) {
                throw new TypeNotCompatibleException(type1.getTypeName(), forStatOP.getLine());
            }
            if (!type2.getTypeName().equals("integer")) {
                throw new TypeNotCompatibleException(type2.getTypeName(), forStatOP.getLine());
            }
        } else {
            throw new SemanticException(forStatOP.getLine());
        }

        forStatOP.getBody().accept(this);
        SymbolTable.exitScope();

        return forStatOP;
    }

    @Override
    public Object visit(WhileStatOP whileStatOP) {
        SymbolTable.enterScope(whileStatOP.getScopeSymbolTable());
        TypeOP typeExpr = (TypeOP) whileStatOP.getExpr().accept(this);
        if (!typeExpr.getTypeName().equals("bool")){
            throw new InvalidBooleanException(whileStatOP.getExpr().getLine());
        }
        whileStatOP.getBody().accept(this);
        SymbolTable.exitScope();
        return whileStatOP;
    }

    @Override
    public Object visit(ElseOP elseOP) {
        SymbolTable.enterScope(elseOP.getScopeSymbolTable());
        elseOP.getBody().accept(this);
        SymbolTable.exitScope();
        return elseOP;
    }

    @Override
    public Object visit(IfStatOP ifStatOP) {
        SymbolTable.enterScope(ifStatOP.getScopeSymbolTable());

        TypeOP typeExpr = (TypeOP) ifStatOP.getExpr().accept(this);
        if (!typeExpr.getTypeName().equals("bool"))
            throw new InvalidBooleanException(ifStatOP.getExpr().getLine());
        ifStatOP.getBody().accept(this);

        SymbolTable.exitScope();

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
        if (bodyOP.getVarDeclList() != null && bodyOP.getVarDeclList().size() > 0)
            for (VarDeclOP varDecl : bodyOP.getVarDeclList())
                varDecl.accept(this);
        if (bodyOP.getStatList() != null && bodyOP.getStatList().size() > 0)
            for (Statement stat : bodyOP.getStatList())
                stat.accept(this);
        return null;
    }

    @Override
    public Object visit(ParDeclOP parDeclOP) {
        return null;
    }

    @Override
    public Object visit(FunDeclOP funDeclOP) {
        SymbolTable.enterScope(funDeclOP.getScopeSymbolTable());
        funDeclOP.getBody().accept(this);

        boolean hasReturn = false;
        if(funDeclOP.getBody().getStatList() != null) {
            for (Statement statement : funDeclOP.getBody().getStatList()) {
                if (!funDeclOP.getType().getTypeName().equals("void")) {
                    if (statement instanceof ReturnOP returnOP) {
                        hasReturn = true;
                        if (returnOP.getExpr() == null)
                            throw new InvalidReturnException(stringTable.get(funDeclOP.getId().getReferenceId()), funDeclOP.getType().getTypeName(), funDeclOP.getId().getLine(), 0);
                        TypeOP typeOP = (TypeOP) returnOP.getExpr().accept(this);
                        if (!typeOP.getTypeName().equals(funDeclOP.getType().getTypeName()))
                            throw new InvalidReturnException(stringTable.get(funDeclOP.getId().getReferenceId()), funDeclOP.getType().getTypeName(), typeOP.getTypeName(), returnOP.getLine());
                    }
                } else {
                    if (statement instanceof ReturnOP returnOP) {
                        if (returnOP.getExpr() != null)
                            throw new VoidReturnValueException(stringTable.get(funDeclOP.getId().getReferenceId()), returnOP.getLine());
                    }
                }
            }
        } else {
            if (!funDeclOP.getType().getTypeName().equals("void"))
                throw new InvalidReturnException(stringTable.get(funDeclOP.getId().getReferenceId()), funDeclOP.getType().getTypeName() , funDeclOP.getId().getLine());
        }

        if (!funDeclOP.getType().getTypeName().equals("void") && !hasReturn)
            throw new InvalidReturnException(stringTable.get(funDeclOP.getId().getReferenceId()), funDeclOP.getType().getTypeName() , funDeclOP.getId().getLine());

        SymbolTable.exitScope();
        return funDeclOP;
    }

    @Override
    public Object visit(MainFunDeclOP mainFunDeclOP) {
        mainFunDeclOP.getFunDeclOP().accept(this);
        return null;
    }

    @Override
    public Object visit(IdInitOP idInitOP) {
        return null;
    }

    @Override
    public Object visit(ProgramOP programOP) {
        SymbolTable.enterScope(programOP.getScopeSymbolTable());

        for (AbstractDecl decl : programOP.getDeclList()) {
            if (decl instanceof VarDeclOP varDeclOP)
                varDeclOP.accept(this);
            if (decl instanceof FunDeclOP funDeclOP)
                funDeclOP.accept(this);
        }

        programOP.getMainFunDecl().accept(this);
        SymbolTable.exitScope();

        return programOP;
    }

    @Override
    public Object visit(IdInitObblOP idInitObblOP) {
        return null;
    }

    @Override
    public Object visit(FunCallExprOP funCallExprOP) {
        Record record = SymbolTable.lookup(stringTable.get(funCallExprOP.getId().getReferenceId()));

        if (record instanceof FunctionRecord functionRecord){
            if (functionRecord.getParamsTypes().size() == funCallExprOP.getExprList().size()){
                for (int i = 0; i < functionRecord.getParamsTypes().size(); i++){
                    ExprOP expr = funCallExprOP.getExprList().get(i);

                    if (functionRecord.getParamsIsOut().get(i)){
                        if (expr instanceof Identifier id) {
                            if (!(SymbolTable.lookup(stringTable.get(id.getReferenceId())) instanceof VariableRecord)){
                                throw new NotFoundVariableInOutParam(functionRecord.getLexeme(), id.getLine());
                            }
                        } else {
                            throw new NotFoundVariableInOutParam(functionRecord.getLexeme(), expr.getLine());
                        }
                    }

                    TypeOP typeExpr = (TypeOP) expr.accept(this);

                    if (!functionRecord.getParamsTypes().get(i).equals(typeExpr.getTypeName()))
                        throw new TypeNotCompatibleException(functionRecord.getParamsTypes().get(i), typeExpr.getTypeName(), funCallExprOP.getLine());
                }
            } else {
                throw new UnexpectedParamsNumber(stringTable.get(funCallExprOP.getId().getReferenceId()), functionRecord.getParamsTypes().size(), funCallExprOP.getExprList().size(), funCallExprOP.getLine());
            }
        } else {
            throw new MethodCallException(stringTable.get(funCallExprOP.getId().getReferenceId()), funCallExprOP.getLine());
        }

        return new TypeOP(functionRecord.getReturnType());
    }

    private TypeOP optype1(TypeOP type, String op, int line){
        switch (op){
            case "umins":
                if (type.getTypeName().equals("integer"))
                    return new TypeOP("integer");
                else if (type.getTypeName().equals("float"))
                    return new TypeOP("float");
                else
                    throw new TypeNotCompatibleException(type.getTypeName(), line);

            case "not":
                if (type.getTypeName().equals("bool"))
                    return new TypeOP("bool");
                else
                    throw new TypeNotCompatibleException(type.getTypeName(), line);
        }
        return null;
    }

    private TypeOP optype2(TypeOP type1, TypeOP type2, String op, int line){
        switch (op){
            case "plus", "times", "div", "minus", "pow":
                if (type1.getTypeName().equals("integer") && type2.getTypeName().equals("integer"))
                    return new TypeOP("integer");
                else if (type1.getTypeName().equals("integer") && type2.getTypeName().equals("float"))
                    return new TypeOP("float");
                else if (type1.getTypeName().equals("float") && type2.getTypeName().equals("integer"))
                    return new TypeOP("float");
                else if (type1.getTypeName().equals("float") && type2.getTypeName().equals("float"))
                    return new TypeOP("float");
                else
                    throw new TypeNotCompatibleException(type1.getTypeName(), type2.getTypeName(), line);

            case "or", "and":
                if(type1.getTypeName().equals("bool") && type2.getTypeName().equals("bool"))
                    return new TypeOP("bool");
                else
                    throw new TypeNotCompatibleException(type1.getTypeName(), type2.getTypeName(), line);

            case "stringConcat":
                if(type1.getTypeName().equals("string") && type2.getTypeName().equals("string"))
                    return new TypeOP("string");
                else
                    throw new TypeNotCompatibleException(type1.getTypeName(), type2.getTypeName(), line);

            case "gt", "ge", "lt", "le":
                if(type1.getTypeName().equals("integer") && type2.getTypeName().equals("integer"))
                    return new TypeOP("bool");
                else if(type1.getTypeName().equals("float") && type2.getTypeName().equals("integer"))
                    return new TypeOP("bool");
                else if(type1.getTypeName().equals("integer") && type2.getTypeName().equals("float"))
                    return new TypeOP("bool");
                else if(type1.getTypeName().equals("float") && type2.getTypeName().equals("float"))
                    return new TypeOP("bool");
                else
                    throw new TypeNotCompatibleException(type1.getTypeName(), type2.getTypeName(), line);

            case "eq", "ne":
                if(type1.getTypeName().equals(type2.getTypeName()))
                    return new TypeOP("bool");
                else
                    throw new TypeNotCompatibleException(type1.getTypeName(), type2.getTypeName(), line);
        }
        return null;
    }
}
