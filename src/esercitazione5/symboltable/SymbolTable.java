package esercitazione5.symboltable;

import esercitazione5.symboltable.exception.IdAlreadyDefinedException;
import esercitazione5.symboltable.exception.IdNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class SymbolTable {

    public static final String PREFIX = "__nl__";
    private final HashMap<String, Record> table;
    private static Stack<HashMap<String, Record>> STACK_SYMBOLTABLE = new Stack<>();

    public SymbolTable() {
        this.table = new HashMap<>();
    }

    public static void enterScope(SymbolTable symbolTable) {
        STACK_SYMBOLTABLE.push(symbolTable.getTable());
    }

    public static void exitScope(){
        STACK_SYMBOLTABLE.pop();
    }

    public static void clearScope() {
        STACK_SYMBOLTABLE.clear();
    }

    public static Record lookup(String id){
        Stack<HashMap<String, Record>> _tempStack = (Stack<HashMap<String, Record>>) STACK_SYMBOLTABLE.clone();

        while (_tempStack.size() > 0){
            HashMap<String, Record> table = _tempStack.pop();
            if(table.containsKey(id))
                return table.get(id);
        }

        throw new IdNotFoundException(id);
    }

    public void addId(String lexeme, String type, int line) throws IdAlreadyDefinedException {
        VariableRecord record = new VariableRecord(lexeme, type);
        if(this.table.containsKey(lexeme)){
            throw new IdAlreadyDefinedException(lexeme, line);
        }
        this.table.put(lexeme, record);
    }

    public void addId(String lexeme, String type, boolean out, int line) throws IdAlreadyDefinedException {
        ParamRecord record = new ParamRecord(lexeme, type, out);
        if(this.table.containsKey(lexeme)){
            throw new IdAlreadyDefinedException(lexeme, line);
        }
        this.table.put(lexeme, record);
    }

    public void addId(String lexeme, ArrayList<Boolean> paramsIsOut, ArrayList<String> paramsTypes,
                      String returnType, int line) throws IdAlreadyDefinedException {
        FunctionRecord record = new FunctionRecord(lexeme, paramsIsOut, paramsTypes, returnType);
        if(this.table.containsKey(lexeme)){
            throw new IdAlreadyDefinedException(lexeme, line);
        }
        this.table.put(lexeme, record);
    }

    public HashMap<String, Record> getTable(){
        return this.table;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("----------------------------\n");
        for (Map.Entry<String, Record> entry : this.table.entrySet()) {
            if (entry.getValue() instanceof VariableRecord variableRecord) {
                sb.append("var " + variableRecord.getLexeme() + " " + variableRecord.getType() + "\n");
            }

            if (entry.getValue() instanceof ParamRecord paramRecord) {
                sb.append("var " + paramRecord.getLexeme() + (paramRecord.isOut() ? " out " : " ") + paramRecord.getType() + "\n");
            }

            if (entry.getValue() instanceof FunctionRecord functionRecord) {
                sb.append("fun " + entry.getValue().getLexeme() + " type (");
                for (int i = 0; i < functionRecord.getParamsTypes().size(); i++){
                    String term = i == functionRecord.getParamsTypes().size() - 1 ? "" : ",";
                    sb.append(functionRecord.getParamsIsOut().get(i) ? " out " : "");
                    sb.append(functionRecord.getParamsTypes().get(i) + term);
                }
                sb.append(") return " + functionRecord.getReturnType() + "\n");
            }
        }
        return sb.toString();
    }

}