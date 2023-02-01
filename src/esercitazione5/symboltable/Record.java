package esercitazione5.symboltable;

public abstract class Record {

    private String lexeme;

    public Record(String lexeme) {
        this.lexeme = lexeme;
    }

    public String getLexeme() {
        return lexeme;
    }

    public void setLexeme(String lexeme) {
        this.lexeme = lexeme;
    }

    @Override
    public String toString() {
        return "Record{" +
                "lexeme='" + lexeme + '\'' +
                '}';
    }
}
