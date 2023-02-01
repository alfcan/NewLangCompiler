package esercitazione5.symboltable.exception;

public class NotFoundVariableInOutParam extends RuntimeException {

    public NotFoundVariableInOutParam(String nameFunction, int line) {
        super("Error at line "+ (line+1) +": a parameter in function " + nameFunction + " not a variable");
    }
}
