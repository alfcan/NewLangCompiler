package esercitazione5.symboltable;

import java.util.ArrayList;

public class FunctionRecord extends Record{

    private ArrayList<Boolean> paramsIsOut;
    private ArrayList<String> paramsTypes;
    private String returnType;


    public FunctionRecord(String lexeme, ArrayList<Boolean> paramsIsOut, ArrayList<String> paramsTypes, String returnType) {
        super(lexeme);
        this.paramsIsOut = paramsIsOut;
        this.paramsTypes = paramsTypes;
        this.returnType = returnType;
    }

    public ArrayList<Boolean> getParamsIsOut() {
        return paramsIsOut;
    }

    public void setParamsModes(ArrayList<Boolean> paramsIsOut) {
        this.paramsIsOut = paramsIsOut;
    }

    public ArrayList<String> getParamsTypes() {
        return paramsTypes;
    }

    public void setParamsTypes(ArrayList<String> paramsTypes) {
        this.paramsTypes = paramsTypes;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }
}
