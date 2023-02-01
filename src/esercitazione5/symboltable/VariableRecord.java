package esercitazione5.symboltable;

public class VariableRecord extends Record{

    private String type;

    public VariableRecord(String lexeme, String type) {
        super(lexeme);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
