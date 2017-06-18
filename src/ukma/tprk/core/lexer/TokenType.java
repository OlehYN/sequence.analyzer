package ukma.tprk.core.lexer;

public enum TokenType {

    FLOAT("-?[0-9]+[\\.][0-9]+"), INTEGER("-?[0-9]+"),
    OPEN("[\\(]"), CLOSE("[\\)]"), WHITESPACE("[ \t\f\r\n]+"),
    SEPARATOR("[\\,]"), ITERATION("\\{i\\}");

    public final String pattern;

    private TokenType(String pattern) {
        this.pattern = pattern;
    }
}
