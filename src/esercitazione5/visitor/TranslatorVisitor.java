package esercitazione5.visitor;

import esercitazione5.symboltable.*;
import esercitazione5.symboltable.Record;
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

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TranslatorVisitor implements Visitor{

    private static final int SIZE_STRING = 200;
    private FileWriter file;
    private BufferedWriter bufferedWriter;
    private StringBuilder code;
    private ArrayList<String> stringTable;

    public TranslatorVisitor(String namePath, ArrayList<String> stringTable) throws IOException {
        this.file = new FileWriter(namePath);
        this.stringTable = stringTable;
        this.bufferedWriter = new BufferedWriter(this.file);
        this.code = new StringBuilder();
    }

    @Override
    public Object visit(BooleanConst booleanConst) {
        if (booleanConst.getValue())
            this.code.append("1");
        else
            this.code.append("0");

        return null;
    }

    @Override
    public Object visit(IntegerConst integerConst) {
        code.append(integerConst.getValue().toString());
        return null;
    }

    @Override
    public Object visit(FloatConst floatConst) {
        code.append(floatConst.getValue().toString());
        return null;
    }

    @Override
    public Object visit(StringConst stringConst) {
        String value = stringConst.getValue().replace("\n", "\\n");;
        this.code.append("\"" + value + "\"");
        return null;
    }

    @Override
    public Object visit(CharConst charConst) {
        code.append("\'" + charConst.getValue() + "\'");
        return null;
    }

    @Override
    public Object visit(FunCallStatOP funCallStatOP) {
        return getFunctionCall(funCallStatOP.getId(), funCallStatOP.getExprList(), true);
    }

    @Override
    public Object visit(PlusOP plusOP) {

        if (plusOP.getExpr1() instanceof Identifier id) {
            Record record = SymbolTable.lookup(stringTable.get(id.getReferenceId()));
            if (record instanceof ParamRecord paramRecord && paramRecord.isOut()){
                this.code.append("*");
            }
        }
        plusOP.getExpr1().accept(this);
        this.code.append(" + ");
        if (plusOP.getExpr2() instanceof Identifier id) {
            Record record = SymbolTable.lookup(stringTable.get(id.getReferenceId()));
            if (record instanceof ParamRecord paramRecord && paramRecord.isOut()){
                this.code.append("*");
            }
        }
        plusOP.getExpr2().accept(this);

        return null;
    }

    @Override
    public Object visit(MinusOP minusOP) {
        if (minusOP.getExpr1() instanceof Identifier id) {
            Record record = SymbolTable.lookup(stringTable.get(id.getReferenceId()));
            if (record instanceof ParamRecord paramRecord && paramRecord.isOut()){
                this.code.append("*");
            }
        }
        minusOP.getExpr1().accept(this);
        this.code.append(" - ");
        if (minusOP.getExpr2() instanceof Identifier id) {
            Record record = SymbolTable.lookup(stringTable.get(id.getReferenceId()));
            if (record instanceof ParamRecord paramRecord && paramRecord.isOut()){
                this.code.append("*");
            }
        }
        minusOP.getExpr2().accept(this);

        return null;
    }

    @Override
    public Object visit(TimesOP timesOP) {
        if (timesOP.getExpr1() instanceof Identifier id) {
            Record record = SymbolTable.lookup(stringTable.get(id.getReferenceId()));
            if (record instanceof ParamRecord paramRecord && paramRecord.isOut()){
                this.code.append("*");
            }
        }
        timesOP.getExpr1().accept(this);
        this.code.append(" * ");
        if (timesOP.getExpr2() instanceof Identifier id) {
            Record record = SymbolTable.lookup(stringTable.get(id.getReferenceId()));
            if (record instanceof ParamRecord paramRecord && paramRecord.isOut()){
                this.code.append("*");
            }
        }
        timesOP.getExpr2().accept(this);
        return null;
    }

    @Override
    public Object visit(DivOP divOP) {
        if (divOP.getExpr1() instanceof Identifier id) {
            Record record = SymbolTable.lookup(stringTable.get(id.getReferenceId()));
            if (record instanceof ParamRecord paramRecord && paramRecord.isOut()){
                this.code.append("*");
            }
        }
        divOP.getExpr1().accept(this);
        this.code.append(" / ");
        if (divOP.getExpr2() instanceof Identifier id) {
            Record record = SymbolTable.lookup(stringTable.get(id.getReferenceId()));
            if (record instanceof ParamRecord paramRecord && paramRecord.isOut()){
                this.code.append("*");
            }
        }
        divOP.getExpr2().accept(this);

        return null;
    }

    @Override
    public Object visit(OrOP orOP) {
        if (orOP.getExpr1() instanceof Identifier id) {
            Record record = SymbolTable.lookup(stringTable.get(id.getReferenceId()));
            if (record instanceof ParamRecord paramRecord && paramRecord.isOut()){
                this.code.append("*");
            }
        }
        orOP.getExpr1().accept(this);
        this.code.append(" || ");
        if (orOP.getExpr2() instanceof Identifier id) {
            Record record = SymbolTable.lookup(stringTable.get(id.getReferenceId()));
            if (record instanceof ParamRecord paramRecord && paramRecord.isOut()){
                this.code.append("*");
            }
        }
        orOP.getExpr2().accept(this);

        return null;
    }

    @Override
    public Object visit(AndOP andOP) {
        if (andOP.getExpr1() instanceof Identifier id) {
            Record record = SymbolTable.lookup(stringTable.get(id.getReferenceId()));
            if (record instanceof ParamRecord paramRecord && paramRecord.isOut()){
                this.code.append("*");
            }
        }
        andOP.getExpr1().accept(this);
        this.code.append(" && ");
        if (andOP.getExpr2() instanceof Identifier id) {
            Record record = SymbolTable.lookup(stringTable.get(id.getReferenceId()));
            if (record instanceof ParamRecord paramRecord && paramRecord.isOut()){
                this.code.append("*");
            }
        }
        andOP.getExpr2().accept(this);

        return null;
    }

    @Override
    public Object visit(StringConcatOP stringConcatOP) {
        this.code.append("str_concat(");
        if (stringConcatOP.getExpr1() instanceof FunCallExprOP funCallExprOP)
            getFunctionCall(funCallExprOP.getId(), funCallExprOP.getExprList(), false);
        else
            stringConcatOP.getExpr1().accept(this);
        this.code.append(", ");
        if (stringConcatOP.getExpr2() instanceof FunCallExprOP funCallExprOP)
            getFunctionCall(funCallExprOP.getId(), funCallExprOP.getExprList(), false);
        else
            stringConcatOP.getExpr2().accept(this);
        this.code.append(")");
        return null;
    }

    @Override
    public Object visit(PowOP powOP) {
        this.code.append("pow(");
        if (powOP.getExpr1() instanceof Identifier id) {
            Record record = SymbolTable.lookup(stringTable.get(id.getReferenceId()));
            if (record instanceof ParamRecord paramRecord && paramRecord.isOut()){
                this.code.append("*");
            }
        }
        powOP.getExpr1().accept(this);
        this.code.append(", ");
        if (powOP.getExpr2() instanceof Identifier id) {
            Record record = SymbolTable.lookup(stringTable.get(id.getReferenceId()));
            if (record instanceof ParamRecord paramRecord && paramRecord.isOut()){
                this.code.append("*");
            }
        }
        powOP.getExpr2().accept(this);
        this.code.append(")");
        return null;
    }

    @Override
    public Object visit(GTOP gtOp) {
        if (gtOp.getExpr1() instanceof Identifier id) {
            Record record = SymbolTable.lookup(stringTable.get(id.getReferenceId()));
            if (record instanceof ParamRecord paramRecord && paramRecord.isOut()){
                this.code.append("*");
            }
        }
        gtOp.getExpr1().accept(this);
        this.code.append(" > ");
        if (gtOp.getExpr2() instanceof Identifier id) {
            Record record = SymbolTable.lookup(stringTable.get(id.getReferenceId()));
            if (record instanceof ParamRecord paramRecord && paramRecord.isOut()){
                this.code.append("*");
            }
        }
        gtOp.getExpr2().accept(this);
        return null;
    }

    @Override
    public Object visit(GEOP geOp) {
        if (geOp.getExpr1() instanceof Identifier id) {
            Record record = SymbolTable.lookup(stringTable.get(id.getReferenceId()));
            if (record instanceof ParamRecord paramRecord && paramRecord.isOut()){
                this.code.append("*");
            }
        }
        geOp.getExpr1().accept(this);
        this.code.append(" >= ");
        if (geOp.getExpr2() instanceof Identifier id) {
            Record record = SymbolTable.lookup(stringTable.get(id.getReferenceId()));
            if (record instanceof ParamRecord paramRecord && paramRecord.isOut()){
                this.code.append("*");
            }
        }
        geOp.getExpr2().accept(this);
        return null;
    }

    @Override
    public Object visit(UminusOP uminusOP) {
        this.code.append("-");
        if (uminusOP.getExpr() instanceof Identifier id) {
            Record record = SymbolTable.lookup(stringTable.get(id.getReferenceId()));
            if (record instanceof ParamRecord paramRecord && paramRecord.isOut()){
                this.code.append("*");
            }
        }
        uminusOP.getExpr().accept(this);
        return null;
    }

    @Override
    public Object visit(NotOP notOP) {
        this.code.append("!");
        if (notOP.getExpr() instanceof Identifier id) {
            Record record = SymbolTable.lookup(stringTable.get(id.getReferenceId()));
            if (record instanceof ParamRecord paramRecord && paramRecord.isOut()){
                this.code.append("*");
            }
        }
        notOP.getExpr().accept(this);
        return null;
    }

    @Override
    public Object visit(LTOP ltop) {
        if (ltop.getExpr1() instanceof Identifier id) {
            Record record = SymbolTable.lookup(stringTable.get(id.getReferenceId()));
            if (record instanceof ParamRecord paramRecord && paramRecord.isOut()){
                this.code.append("*");
            }
        }
        ltop.getExpr1().accept(this);
        this.code.append(" < ");
        if (ltop.getExpr2() instanceof Identifier id) {
            Record record = SymbolTable.lookup(stringTable.get(id.getReferenceId()));
            if (record instanceof ParamRecord paramRecord && paramRecord.isOut()){
                this.code.append("*");
            }
        }
        ltop.getExpr2().accept(this);

        return null;
    }

    @Override
    public Object visit(LEOP leop) {
        if (leop.getExpr1() instanceof Identifier id) {
            Record record = SymbolTable.lookup(stringTable.get(id.getReferenceId()));
            if (record instanceof ParamRecord paramRecord && paramRecord.isOut()){
                this.code.append("*");
            }
        }
        leop.getExpr1().accept(this);
        this.code.append(" <= ");
        if (leop.getExpr2() instanceof Identifier id) {
            Record record = SymbolTable.lookup(stringTable.get(id.getReferenceId()));
            if (record instanceof ParamRecord paramRecord && paramRecord.isOut()){
                this.code.append("*");
            }
        }
        leop.getExpr2().accept(this);

        return null;
    }

    @Override
    public Object visit(EQOP eqop) {
        TypeCheckVisitor typeCheck = new TypeCheckVisitor(stringTable);
        TypeOP type1 = (TypeOP) eqop.getExpr1().accept(typeCheck);
        if (type1.getTypeName().equals("string")){
            this.code.append("strcmp(");
            eqop.getExpr1().accept(this);
            this.code.append(", ");
            eqop.getExpr2().accept(this);
            this.code.append(") == 0");
        } else {
            if (eqop.getExpr1() instanceof Identifier id) {
                Record record = SymbolTable.lookup(stringTable.get(id.getReferenceId()));
                if (record instanceof ParamRecord paramRecord && paramRecord.isOut()){
                    this.code.append("*");
                }
            }
            eqop.getExpr1().accept(this);
            this.code.append(" == ");
            if (eqop.getExpr2() instanceof Identifier id) {
                Record record = SymbolTable.lookup(stringTable.get(id.getReferenceId()));
                if (record instanceof ParamRecord paramRecord && paramRecord.isOut()){
                    this.code.append("*");
                }
            }
            eqop.getExpr2().accept(this);
        }
        return null;
    }

    @Override
    public Object visit(NEOP neop) {
        TypeCheckVisitor typeCheck = new TypeCheckVisitor(stringTable);
        TypeOP type1 = (TypeOP) neop.getExpr1().accept(typeCheck);
        if (type1.getTypeName().equals("string")){
            this.code.append("strcmp(");
            neop.getExpr1().accept(this);
            this.code.append(", ");
            neop.getExpr2().accept(this);
            this.code.append(") != 0");
        } else {
            if (neop.getExpr1() instanceof Identifier id) {
                Record record = SymbolTable.lookup(stringTable.get(id.getReferenceId()));
                if (record instanceof ParamRecord paramRecord && paramRecord.isOut()){
                    this.code.append("*");
                }
            }
            neop.getExpr1().accept(this);
            this.code.append(" != ");
            if (neop.getExpr2() instanceof Identifier id) {
                Record record = SymbolTable.lookup(stringTable.get(id.getReferenceId()));
                if (record instanceof ParamRecord paramRecord && paramRecord.isOut()){
                    this.code.append("*");
                }
            }
            neop.getExpr2().accept(this);
        }

        return null;
    }

    @Override
    public Object visit(AssignStatOP assignStatOP) {
        for (int i = 0; i < assignStatOP.getIdList().size(); i++){
            Identifier id = assignStatOP.getIdList().get(i);
            Record record = SymbolTable.lookup(stringTable.get(id.getReferenceId()));

            if (record instanceof VariableRecord variableRecord){
                if (variableRecord.getType().equals("string")){
                    this.code.append("strcpy(");
                    id.accept(this);
                    this.code.append(", ");
                    if (assignStatOP.getExprList().get(i) instanceof FunCallExprOP funCallExprOP) {
                        getFunctionCall(funCallExprOP.getId(), funCallExprOP.getExprList(), false);
                        this.code.append(");\n");
                    } else {
                        assignStatOP.getExprList().get(i).accept(this);
                        this.code.append(");\n");
                    }
                } else {
                    id.accept(this);
                    this.code.append(" = ");
                    if (assignStatOP.getExprList().get(i) instanceof FunCallExprOP) {
                        assignStatOP.getExprList().get(i).accept(this);
                    } else {
                        assignStatOP.getExprList().get(i).accept(this);
                        this.code.append(";\n");
                    }
                }
            } else if (record instanceof ParamRecord paramRecord) {
                if (paramRecord.getType().equals("string")){
                    this.code.append("strcpy(");
                    id.accept(this);
                    this.code.append(", ");
                    if (assignStatOP.getExprList().get(i) instanceof FunCallExprOP funCallExprOP) {
                        getFunctionCall(funCallExprOP.getId(), funCallExprOP.getExprList(), false);
                    } else {
                        assignStatOP.getExprList().get(i).accept(this);
                        this.code.append(");\n");
                    }
                } else if (paramRecord.isOut()) {
                    this.code.append("*");
                    id.accept(this);
                    this.code.append(" = ");
                    if (assignStatOP.getExprList().get(i) instanceof FunCallExprOP) {
                        assignStatOP.getExprList().get(i).accept(this);
                    } else {
                        assignStatOP.getExprList().get(i).accept(this);
                        this.code.append(";\n");
                    }
                } else {
                    id.accept(this);
                    this.code.append(" = ");
                    if (assignStatOP.getExprList().get(i) instanceof FunCallExprOP) {
                        assignStatOP.getExprList().get(i).accept(this);
                    } else {
                        assignStatOP.getExprList().get(i).accept(this);
                        this.code.append(";\n");
                    }
                }
            }
        }

        return null;
    }

    @Override
    public Object visit(WriteStatOP writeStatOP) {
        TypeCheckVisitor typeCheck = new TypeCheckVisitor(stringTable);

        this.code.append("printf(\"");
        for (ExprOP expr : writeStatOP.getExprList()){
            if (expr instanceof AbstractConst<?> abstractConst){
                if (abstractConst instanceof StringConst stringConst) {
                    stringConst.setValue(stringConst.getValue().replace("\n", "\\n"));
                }
                this.code.append(((AbstractConst<?>) expr).getValue().toString());
            } else if (expr instanceof FunCallExprOP funCallExprOP){
                String lexeme = stringTable.get(funCallExprOP.getId().getReferenceId());
                FunctionRecord record = (FunctionRecord) SymbolTable.lookup(lexeme);
                this.code.append(getFormat(record.getReturnType()));
            } else if (expr instanceof PlusOP plusOP) {
                TypeOP type = (TypeOP) typeCheck.visit(plusOP);
                this.code.append(getFormat(type.getTypeName()));
            } else if (expr instanceof MinusOP minusOP) {
                TypeOP type = (TypeOP) typeCheck.visit(minusOP);
                this.code.append(getFormat(type.getTypeName()));
            } else if (expr instanceof TimesOP timesOP) {
                TypeOP type = (TypeOP) typeCheck.visit(timesOP);
                this.code.append(getFormat(type.getTypeName()));
            } else if (expr instanceof DivOP divOP) {
                TypeOP type = (TypeOP) typeCheck.visit(divOP);
                this.code.append(getFormat(type.getTypeName()));
            } else if (expr instanceof AndOP andOP) {
                TypeOP type = (TypeOP) typeCheck.visit(andOP);
                this.code.append(getFormat(type.getTypeName()));
            } else if (expr instanceof OrOP orOP) {
                TypeOP type = (TypeOP) typeCheck.visit(orOP);
                this.code.append(getFormat(type.getTypeName()));
            } else if (expr instanceof PowOP powOP) {
                TypeOP type = (TypeOP) typeCheck.visit(powOP);
                this.code.append(getFormat(type.getTypeName()));
            } else if (expr instanceof GTOP gtop) {
                TypeOP type = (TypeOP) typeCheck.visit(gtop);
                this.code.append(getFormat(type.getTypeName()));
            } else if (expr instanceof GEOP geop) {
                TypeOP type = (TypeOP) typeCheck.visit(geop);
                this.code.append(getFormat(type.getTypeName()));
            } else if (expr instanceof LTOP ltop) {
                TypeOP type = (TypeOP) typeCheck.visit(ltop);
                this.code.append(getFormat(type.getTypeName()));
            } else if (expr instanceof LEOP leop) {
                TypeOP type = (TypeOP) typeCheck.visit(leop);
                this.code.append(getFormat(type.getTypeName()));
            } else if (expr instanceof EQOP eqop) {
                TypeOP type = (TypeOP) typeCheck.visit(eqop);
                this.code.append(getFormat(type.getTypeName()));
            } else if (expr instanceof NEOP neop) {
                TypeOP type = (TypeOP) typeCheck.visit(neop);
                this.code.append(getFormat(type.getTypeName()));
            } else if (expr instanceof UminusOP uminusOP) {
                TypeOP type = (TypeOP) typeCheck.visit(uminusOP);
                this.code.append(getFormat(type.getTypeName()));
            } else if (expr instanceof NotOP notOP) {
                TypeOP type = (TypeOP) typeCheck.visit(notOP);
                this.code.append(getFormat(type.getTypeName()));
            } else if (expr instanceof StringConcatOP) {
                this.code.append("%s");
            } else if (expr instanceof Identifier id) {
                String lexeme = stringTable.get(id.getReferenceId());
                Record abstrRecord = SymbolTable.lookup(lexeme);
                if (abstrRecord instanceof VariableRecord variableRecord)
                    this.code.append(getFormat(variableRecord.getType()));
                else if (abstrRecord instanceof ParamRecord paramRecord)
                    this.code.append(getFormat(paramRecord.getType()));
            }
        }

        if (writeStatOP.getMode().equals("writeln")){
            this.code.append("\\n\"");
        } else {
            this.code.append("\"");
        }


        int i = 0;
        boolean firstComma = true;
        for (ExprOP expr : writeStatOP.getExprList()) {
            if (!(expr instanceof AbstractConst<?>)) {
                if (i++ <= (writeStatOP.getExprList().size() - 1) || firstComma) {
                    this.code.append(", ");
                    firstComma = false;
                }
                if(expr instanceof Identifier id){
                    Record record = SymbolTable.lookup(stringTable.get(id.getReferenceId()));
                    if (record instanceof ParamRecord paramRecord && paramRecord.isOut() && !(paramRecord.getType().equals("string"))){
                        this.code.append("*");
                    }
                }
                if (expr instanceof FunCallExprOP funCallExprOP) {
                    getFunctionCall(funCallExprOP.getId(), funCallExprOP.getExprList(), false);
                } else {
                    expr.accept(this);
                }
            }
        }
        this.code.append(");\n");
        return null;
    }

    @Override
    public Object visit(Identifier identifier) {
        this.code.append(stringTable.get(identifier.getReferenceId()));
        return null;
    }

    @Override
    public Object visit(ReadStatOP readStatOP) {
        if (readStatOP.getStringConst() != null) {
            this.code.append("printf(");
            readStatOP.getStringConst().accept(this);
            this.code.append(");\n");
        }

        for (Identifier id : readStatOP.getIdList()) {
            String lexeme = stringTable.get(id.getReferenceId());
            Record record = SymbolTable.lookup(lexeme);
            if (record instanceof VariableRecord variableRecord){
                if (variableRecord.getType().equals("string")){
                    this.code.append("fgets(").append(variableRecord.getLexeme()).append(", ").append(SIZE_STRING)
                            .append(", stdin);\n");
                    this.code.append(variableRecord.getLexeme()).append("[strcspn(").append(variableRecord.getLexeme()).append(", \"\\n\\r\"").append(")] = 0;\n");
                } else {
                    this.code.append("scanf(\"").append(getFormat(variableRecord.getType())).append("\" , ")
                            .append("&").append(lexeme).append(");\n");
                    this.code.append("nl_flush();\n\t");
                }
            } else if (record instanceof ParamRecord paramRecord){
                if (paramRecord.getType().equals("string")){
                    this.code.append("fgets(").append(paramRecord.getLexeme()).append(", ").append(SIZE_STRING)
                            .append(", stdin);\n");
                    this.code.append(paramRecord.getLexeme()).append("[strcspn(").append(paramRecord.getLexeme()).append(", \"\\n\\r\"").append(")] = 0;\n");
                } else {
                    if (paramRecord.isOut()) {
                        this.code.append("scanf(\"").append(getFormat(paramRecord.getType())).append("\" , ")
                                .append(lexeme).append(");\n");
                        this.code.append("nl_flush();\n\t");
                    } else {
                        this.code.append("scanf(\"").append(getFormat(paramRecord.getType())).append("\" , ")
                                .append("&").append(lexeme).append(");\n");
                        this.code.append("nl_flush();\n\t");
                    }
                }
            }
        }

        return null;
    }

    @Override
    public Object visit(VarDeclOP varDeclOP) {
        for (AbstractIdInit id : varDeclOP.getIdList()){
            Record record = SymbolTable.lookup(stringTable.get(id.getId().getReferenceId()));
            if (record instanceof VariableRecord variableRecord){
                TypeOP type = new TypeOP(variableRecord.getType());
                type.accept(this);
                if (variableRecord.getType().equals("string")) {
                    this.code.append(variableRecord.getLexeme()).append("[").append(SIZE_STRING).append("];\n");
                } else{
                    this.code.append(variableRecord.getLexeme());
                }
                if (id instanceof IdInitOP idInitOP && idInitOP.getExpr() != null) {
                    if (variableRecord.getType().equals("string")) {
                        this.code.append("strcpy(").append(variableRecord.getLexeme()).append(", ");
                        if (idInitOP.getExpr() instanceof FunCallExprOP funCallExprOP) {
                            getFunctionCall(funCallExprOP.getId(), funCallExprOP.getExprList(), false);
                        } else {
                            idInitOP.getExpr().accept(this);
                            this.code.append(");\n");
                        }
                    } else{
                        this.code.append(" = ");
                        ExprOP exprOP = idInitOP.getExpr();
                        if (exprOP instanceof FunCallExprOP funCallExprOP)
                            funCallExprOP.accept(this);
                        else {
                            exprOP.accept(this);
                            this.code.append(";\n");
                        }
                    }
                } else if (id instanceof IdInitObblOP idInitObblOP && idInitObblOP.getConstant() != null) {
                    if (variableRecord.getType().equals("string")) {
                        this.code.append("strcpy(").append(variableRecord.getLexeme()).append(", ");
                        idInitObblOP.getConstant().accept(this);
                        this.code.append(");\n");
                    } else {
                        this.code.append(" = ");
                        idInitObblOP.getConstant().accept(this);
                        this.code.append(";\n");
                    }
                } else if (!variableRecord.getType().equals("string")){
                    this.code.append(";\n");
                }
            }
        }

        return null;
    }

    @Override
    public Object visit(ForStatOP forStatOP) {
        SymbolTable.enterScope(forStatOP.getScopeSymbolTable());

        String lexemeId = stringTable.get(forStatOP.getId().getReferenceId());

        this.code.append("for (int ").append(lexemeId).append(" = ");
        if (forStatOP.getExpr1() instanceof FunCallExprOP funCallExprOP)
            getFunctionCall(funCallExprOP.getId(), funCallExprOP.getExprList(), false);
        else
            forStatOP.getExpr1().accept(this);
        this.code.append("; ").append(lexemeId).append(" <= ");
        if (forStatOP.getExpr2() instanceof FunCallExprOP funCallExprOP)
            getFunctionCall(funCallExprOP.getId(), funCallExprOP.getExprList(), false);
        else
            forStatOP.getExpr2().accept(this);
        this.code.append("; ").append(lexemeId).append("++) {\n");
        forStatOP.getBody().accept(this);
        this.code.append("}\n");

        SymbolTable.exitScope();

        return null;
    }

    @Override
    public Object visit(WhileStatOP whileStatOP) {
        SymbolTable.enterScope(whileStatOP.getScopeSymbolTable());

        this.code.append("while (");
        whileStatOP.getExpr().accept(this);
        this.code.append(") {\n");
        whileStatOP.getBody().accept(this);
        this.code.append("}\n");

        SymbolTable.exitScope();

        return null;
    }

    @Override
    public Object visit(ElseOP elseOP) {
        SymbolTable.enterScope(elseOP.getScopeSymbolTable());

        this.code.append(" else {\n");
        elseOP.getBody().accept(this);
        this.code.append("}\n");

        SymbolTable.exitScope();

        return null;
    }

    @Override
    public Object visit(IfStatOP ifStatOP) {
        SymbolTable.enterScope(ifStatOP.getScopeSymbolTable());

        this.code.append("if (");
        ifStatOP.getExpr().accept(this);
        this.code.append(") {\n");
        ifStatOP.getBody().accept(this);
        this.code.append("}");

        SymbolTable.exitScope();

        if (ifStatOP.getElseOP() != null)
            ifStatOP.getElseOP().accept(this);
        else
            this.code.append("\n");

        return null;
    }

    @Override
    public Object visit(ReturnOP returnOP) {
        this.code.append("return ");

        ExprOP exprOP = returnOP.getExpr();
        if (exprOP != null) {
            if (exprOP instanceof Identifier id) {
                Record record = SymbolTable.lookup(stringTable.get(id.getReferenceId()));
                if (record instanceof ParamRecord paramRecord && paramRecord.isOut() && !(paramRecord.getType().equals("string"))) {
                    this.code.append("*");
                }

            }
            exprOP.accept(this);
        }
        this.code.append(";\n");

        return null;
    }

    @Override
    public Object visit(TypeOP typeOP) {
        switch (typeOP.getTypeName()){
            case "integer", "bool":
                this.code.append("int ");
                break;
            case "float":
                this.code.append("float ");
                break;
            case "string", "char":
                this.code.append("char ");
                break;
            case "void":
                this.code.append("void");
        }
        return null;
    }

    @Override
    public Object visit(BodyOP bodyOP) {
        if(bodyOP.getVarDeclList() != null)
            for (VarDeclOP varDeclOP : bodyOP.getVarDeclList())
                varDeclOP.accept(this);

        if(bodyOP.getStatList() != null)
            for (Statement statement : bodyOP.getStatList())
                statement.accept(this);

        return null;
    }

    @Override
    public Object visit(ParDeclOP parDeclOP) {
        return null;
    }

    @Override
    public Object visit(FunDeclOP funDeclOP) {
        SymbolTable.enterScope(funDeclOP.getScopeSymbolTable());

        getSignature(funDeclOP);
        this.code.append(" {\n");
        funDeclOP.getBody().accept(this);
        this.code.append("}\n\n");

        SymbolTable.exitScope();

        return null;
    }

    @Override
    public Object visit(MainFunDeclOP mainFunDeclOP) {
        mainFunDeclOP.getFunDeclOP().accept(this);
        ArrayList<ExprOP> exprList = new ArrayList<>();

        this.code.append("int main() {\n").append("\t");

        if (mainFunDeclOP.getFunDeclOP().getParamDeclList() != null) {
            for (int i = 0; i < mainFunDeclOP.getFunDeclOP().getParamDeclList().size(); i++) {
                ParDeclOP parDecl = mainFunDeclOP.getFunDeclOP().getParamDeclList().get(i);

                for (int j = 0; j < parDecl.getIdList().size(); j++) {
                    stringTable.add("param" + i + j);
                    int index = stringTable.indexOf("param" + i + j);
                    Identifier id = new Identifier(index);
                    exprList.add(id);

                    parDecl.getType().accept(this);
                    if (parDecl.getType().getTypeName().equals("string"))
                        this.code.append("param" + i + j).append("[").append(SIZE_STRING).append("];\n\t");
                    else
                        this.code.append("param" + i + j).append(";\n\t");

                    this.code.append("printf(\"Inserisci il parametro ").append(i + 1).append(" di tipo ")
                            .append(parDecl.getType().getTypeName()).append("\\n\");\n\t");
                    if (parDecl.getType().getTypeName().equals("string")) {
                        this.code.append("fgets(").append("param" + i + j).append(", ").append(SIZE_STRING)
                                .append(", stdin);\n\t");
                        this.code.append("param" + i + j).append("[strcspn(").append("param" + i + j).append(", \"\\n\\r\"").append(")] = 0;\n");
                    }else if (parDecl.isOut()) {
                        this.code.append("scanf(\"").append(getFormat(parDecl.getType().getTypeName())).append("\" , ")
                                .append("param" + i + j).append(");\n\t");
                        this.code.append("nl_flush();\n\t");
                    } else {
                        this.code.append("scanf(\"").append(getFormat(parDecl.getType().getTypeName())).append("\" , ")
                                .append("&").append("param" + i + j).append(");\n\t");
                        this.code.append("nl_flush();\n\t");
                    }
                }
            }
        }

        this.code.append("\n\t");

        FunDeclOP funMain = mainFunDeclOP.getFunDeclOP();
        if (funMain.getType().getTypeName().equals("void"))
            getFunctionCall(mainFunDeclOP.getFunDeclOP().getId(), exprList, true);
        else{
            funMain.getType().accept(this);
            if (funMain.getType().getTypeName().equals("string")) {
                this.code.append("_nl_result").append("[").append(SIZE_STRING).append("];\n\t");
                this.code.append("strcpy(_nl_result, ");
                getFunctionCall(mainFunDeclOP.getFunDeclOP().getId(), exprList, false);
                this.code.append(");");
            } else {
                this.code.append("_nl_result").append(" = ");
                getFunctionCall(mainFunDeclOP.getFunDeclOP().getId(), exprList, true);
            }

            this.code.append("\n\tprintf(\"Il risultato è ").append(getFormat(funMain.getType().getTypeName())).append("\\n\", ")
                    .append("_nl_result);\n");
        }

        this.code.append("}");

        return null;
    }

    @Override
    public Object visit(IdInitOP idInitOP) {
        return null;
    }

    @Override
    public Object visit(ProgramOP programOP){
        SymbolTable.enterScope(programOP.getScopeSymbolTable());

        this.code.append("#include <stdlib.h>\n");
        this.code.append("#include <stdio.h>\n");
        this.code.append("#include <string.h>\n");
        this.code.append("#include <math.h>\n");
        this.code.append("#include <unistd.h>\n");
        this.code.append("#define SIZE_STRING ").append(SIZE_STRING).append("\n\n");

        this.code.append("void nl_flush() {\n");
        this.code.append("\tchar c;\n");
        this.code.append("\twhile ((c = getchar()) != '\\n' && c != EOF) { }\n");
        this.code.append("}\n\n");

        this.code.append("char *str_concat(char *s1, char *s2) {\n");
        this.code.append("\tchar *buf = malloc(sizeof(char) * SIZE_STRING);\n");
        this.code.append("\tstrcat(buf,s1);\n");
        this.code.append("\tstrcat(buf,s2);\n");
        this.code.append("\treturn buf;\n");
        this.code.append("}\n\n");

        for (AbstractDecl decl : programOP.getDeclList()) {
            if (decl instanceof VarDeclOP varDecl)
                varDecl.accept(this);
            else if(decl instanceof FunDeclOP funDeclOP){
                this.code.append("\n");
                getSignature(funDeclOP);
                this.code.append(";\n");
            }
        }

        this.code.append("\n");
        getSignature(programOP.getMainFunDecl().getFunDeclOP());
        this.code.append(";\n");

        this.code.append("\n\n");

        for (AbstractDecl decl : programOP.getDeclList())
            if (decl instanceof FunDeclOP funDeclOP)
                funDeclOP.accept(this);

        this.code.append("\n");
        programOP.getMainFunDecl().accept(this);

        try {
            this.bufferedWriter.write(String.valueOf(code));
            this.bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        SymbolTable.exitScope();

        return programOP;
    }

    @Override
    public Object visit(IdInitObblOP idInitObblOP) {
        return null;
    }

    @Override
    public Object visit(FunCallExprOP funCallExprOP) {
        return getFunctionCall(funCallExprOP.getId(), funCallExprOP.getExprList(), true);
    }

    private Object getFunctionCall(Identifier id, ArrayList<ExprOP> exprList, boolean comma) {
        FunctionRecord functionRecord = (FunctionRecord) SymbolTable.lookup(stringTable.get(id.getReferenceId()));

        this.code.append(functionRecord.getLexeme()).append("(");

        int i = 0;

        if (exprList != null) {
            for (ExprOP exprOP : exprList) {
                if (functionRecord.getParamsIsOut().get(i) && !(functionRecord.getParamsTypes().get(i).equals("string"))) {
                    this.code.append("&");
                }
                exprOP.accept(this);

                if (i < exprList.size() - 1) {
                    this.code.append(", ");
                }

                i++;
            }
        }

        this.code.append(")");
        if (comma){
            this.code.append(";\n");
        }

        return null;
    }

    private void getSignature(FunDeclOP funDeclOP){
        switch (funDeclOP.getType().getTypeName()){
            case "integer", "bool":
                this.code.append("int ");
                break;
            case "float":
                this.code.append("float ");
                break;
            case "string":
                this.code.append("char* ");
                break;
            case "char":
                this.code.append("char ");
                break;
            case "void":
                this.code.append("void");
        }

        this.code.append(" ").append(stringTable.get(funDeclOP.getId().getReferenceId())).append("(");
        if (funDeclOP.getParamDeclList() != null && funDeclOP.getParamDeclList().size() > 0) {
            int j = 1;
            for (ParDeclOP param : funDeclOP.getParamDeclList()) {
                int i = 1;
                for (Identifier id : param.getIdList()) {
                    param.getType().accept(this);
                    if (param.getType().getTypeName().equals("string"))
                        this.code.append("*");
                    else if (param.isOut())
                        this.code.append("*");
                    this.code.append(stringTable.get(id.getReferenceId()));
                    if (i++ <= (param.getIdList().size() - 1))
                        this.code.append(", ");
                }
                if (j++ <= (funDeclOP.getParamDeclList().size() - 1))
                    this.code.append(", ");
            }
        }
        this.code.append(")");
    }

    private String getFormat(String type){
        switch (type){
            case "integer", "bool":
                return "%d";
            case "float":
                return "%f";
            case "char":
                return "%c";
            case "string":
                return "%s";
        }
        return null;
    }
}