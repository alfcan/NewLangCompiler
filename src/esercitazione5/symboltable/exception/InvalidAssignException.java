package esercitazione5.symboltable.exception;

public class InvalidAssignException extends RuntimeException{
    public InvalidAssignException(int line) {
        super("Error at line " + (line + 1) + " assignment is not valid");
    }
}
