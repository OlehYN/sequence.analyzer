package ukma.tprk.core.deprecated.lexer;

public class Token {

    public String name;
    public String data;

    public Token(String name, String data) {
        this.name = name;
        this.data = data;
    }

    @Override
    public String toString() {
        return String.format("%s: %s", name, data);
    }
}
