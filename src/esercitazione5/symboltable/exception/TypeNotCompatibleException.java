package esercitazione5.symboltable.exception;

public class TypeNotCompatibleException extends RuntimeException {
    public TypeNotCompatibleException(String type, int line) {
        super("Error at line " + (line + 1) + ": " + type + " is not a compatible type");
    }

    public TypeNotCompatibleException(String type1, String type2, int line) {
        super("Error at line " + (line + 1) + ": " + type1 + " is not compatible with " + type2);
    }
}
