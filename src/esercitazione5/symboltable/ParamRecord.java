package esercitazione5.symboltable;

public class ParamRecord extends Record{
    private String type;
    private boolean out;

    public ParamRecord(String lexeme, String type, boolean out) {
        super(lexeme);
        this.type = type;
        this.out = out;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isOut() {
        return out;
    }

    public void setOut(boolean out) {
        this.out = out;
    }
}
