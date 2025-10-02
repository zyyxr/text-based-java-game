package parser;

import java.util.ArrayList;

/**
 * The {@code Tokeniser} class is responsible for converting a string input into a list of tokens
 * that can be parsed into commands by the game.
 * 
 * <p>
 * The tokeniser identifies keywords, variables, and special symbols, assigning each a {@code TokenType}.
 * </p>
 */
public class Tokeniser {
        ArrayList<Token> tokens = new ArrayList<>();

    public ArrayList<Token> getTokens() {
        return tokens;
    }

    public String sanitise(String s) {
        String string = s.toLowerCase();
        int startIndex;
        int endIndex;
        int i;
        for (i = 0; i < string.length(); i++) {
            if (!(string.charAt(i) == ' ')) {
                startIndex = i;
                break;
            }
        }
        int j;
        for (j = string.length() - 1; j >= 0; j--) {
            if (!(string.charAt(j) == ' ')) {
                endIndex = j;
                break;
            }
        }
        return string.substring(i,j + 1);
    }

    public void tokenise(String s) {
        tokens.clear();
        String sanitisedInput = sanitise(s);
        String[] stringTokens = sanitisedInput.split(" ");
        for (String str: stringTokens) {
            switch (str) {
                case "": break;
                case "move": tokens.add(new Token(TokenType.MOVE, "move")); break;
                case "drop": tokens.add(new Token(TokenType.DROP, "drop")); break;
                case "get": tokens.add(new Token(TokenType.GET, "get")); break;
                case "help": tokens.add(new Token(TokenType.HELP, "help")); break;
                case "look": tokens.add(new Token(TokenType.LOOK, "look")); break;
                case "quit": tokens.add(new Token(TokenType.QUIT, "quit")); break;
                case "status": tokens.add(new Token(TokenType.STATUS, "status")); break;
                case "use": tokens.add(new Token(TokenType.USE, "use")); break;
                case "on": tokens.add(new Token(TokenType.PREPOSITION, "on")); break;
                case "with": tokens.add(new Token(TokenType.PREPOSITION, "with")); break;
                case "using": tokens.add(new Token(TokenType.PREPOSITION, "using")); break;
                case "to": tokens.add(new Token(TokenType.ADDRESS, "to")); break;
                case "talk": tokens.add(new Token(TokenType.TALK,"talk")); break;
                case "and": tokens.add(new Token(TokenType.CONJUNCTION, "and")); break;
                case "combine": tokens.add(new Token(TokenType.COMBINE, "combine")); break;
                case "missions": tokens.add(new Token(TokenType.MISSIONS,"missions")); break;
                default: tokens.add(new Token(TokenType.VAR, str)); break;
            }
        }
        tokens.add(new Token(TokenType.EOL));
    }


   
}
