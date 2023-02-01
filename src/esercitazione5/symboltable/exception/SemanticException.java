package esercitazione5.symboltable.exception;

public class SemanticException extends RuntimeException{
    public SemanticException(int line) {
        super("Semantic Error in for expressions at line" + (line + 1));
    }
}
