package esercitazione5.symboltable.exception;

public class InvalidBooleanException extends RuntimeException {
    public InvalidBooleanException(int line) {
            super("Error at line " + (line + 1) + ": statement condition does not return a boolean value");
        }
}
