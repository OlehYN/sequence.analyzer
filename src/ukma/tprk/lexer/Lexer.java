package ukma.tprk.lexer;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import ukma.tprk.operation.Operation;
import ukma.tprk.operation.OperationManager;

import java.util.regex.Matcher;

public class Lexer {

    public static List<Token> lex(String input) {

        List<Token> tokens = new ArrayList<Token>();

        StringBuilder tokenPatternsBuilder = new StringBuilder();
        for (TokenType tokenType : TokenType.values()) {
            tokenPatternsBuilder.append(String.format("|(?<%s>%s)", tokenType.name(), tokenType.pattern));
        }

        for (Operation operation : OperationManager.operations) {
            tokenPatternsBuilder.append(String.format("|(?<%s>%s)", operation.getName(), operation.getPattern()));
        }

        Pattern tokenPatterns = Pattern.compile(tokenPatternsBuilder.substring(1));

        Matcher matcher = tokenPatterns.matcher(input);
        boolean value = true;
        while (matcher.find()) {
            if (matcher.group(TokenType.FLOAT.name()) != null) {
                if (!value) {
                    tokens.add(new Token("-", "-"));
                    tokens.add(new Token(TokenType.FLOAT.name(), matcher.group(TokenType.FLOAT.name()).substring(1)));
                } else {
                    tokens.add(new Token(TokenType.FLOAT.name(), matcher.group(TokenType.FLOAT.name())));
                }

                value = false;
                continue;
            } else if (matcher.group(TokenType.INTEGER.name()) != null) {
                if (!value) {
                    tokens.add(new Token("-", "-"));
                    tokens.add(
                            new Token(TokenType.INTEGER.name(), matcher.group(TokenType.INTEGER.name()).substring(1)));
                } else {
                    tokens.add(new Token(TokenType.INTEGER.name(), matcher.group(TokenType.INTEGER.name())));
                }
                value = false;
                continue;
            } else if (matcher.group(TokenType.SEPARATOR.name()) != null) {
                tokens.add(new Token(TokenType.SEPARATOR.name(), matcher.group(TokenType.SEPARATOR.name())));
                value = true;
                continue;
            } else if (matcher.group(TokenType.OPEN.name()) != null) {
                tokens.add(new Token(TokenType.OPEN.name(), matcher.group(TokenType.OPEN.name())));
                value = true;
                continue;
            } else if (matcher.group(TokenType.CLOSE.name()) != null) {
                value = false;
                tokens.add(new Token(TokenType.CLOSE.name(), matcher.group(TokenType.CLOSE.name())));
                continue;
            } else if (matcher.group(TokenType.WHITESPACE.name()) != null) {
                value = true;
                tokens.add(new Token(TokenType.WHITESPACE.name(), matcher.group(TokenType.WHITESPACE.name())));
                continue;
            } else if (matcher.group(TokenType.ITERATION.name()) != null) {
                value = false;
                tokens.add(new Token(TokenType.ITERATION.name(), matcher.group(TokenType.ITERATION.name())));
                continue;
            }

            for (Operation operation : OperationManager.operations) {
                if (matcher.group(operation.getName()) != null) {
                    value = true;
                    tokens.add(new Token(operation.getName(), matcher.group(operation.getName())));
                }
            }
        }

        return tokens;
    }

}
