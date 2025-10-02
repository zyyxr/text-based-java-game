package parser;

import java.util.ArrayList;

import commands.*;

/**
 * The {@code Parser} class processes a list of tokens and converts them into {@code Command} objects
 * that can be executed by the game.
 * 
 * <p>
 * The parser identifies the type of command from the tokens and creates the appropriate command object.
 * If the command is invalid or incomplete, a {@code CommandErrorException} is thrown.
 * </p>
 */
public class Parser {
    CommandErrorException exception1 = new CommandErrorException("Error: Invalid format of arguments provided to the command. Use 'help <command>' for more info. ");
    CommandErrorException exception2 = new CommandErrorException("Error: Too many arguments provided to the command. Use 'help <command>' for more info. ");
    CommandErrorException exception3 = new CommandErrorException("Error: Not enough arguments provided to the command. Use 'help <command>' for more info. ");
    CommandErrorException exception4 = new CommandErrorException("Error: Invalid command. Use 'help' for more info. ");

    public Parser() {};

    public Command parse(ArrayList<Token> tokens) throws CommandErrorException {
        if (tokens.get(tokens.size() - 1).getTokenType() != TokenType.EOL) {
            tokens.add(new Token(TokenType.EOL));
        }
        for (int i = 0; i < tokens.size();i++ ) {
            if ((tokens.get(i).getTokenType() == TokenType.VAR) && (tokens.get(i+1).getTokenType() == TokenType.VAR)) {
                tokens.get(i).setTokenType(new Token(TokenType.VAR, tokens.get(i).getValue() + " " + tokens.get(i+1).getValue()));
                for (int j = i+1; j < tokens.size()-2; j++) {
                    tokens.get(j).setTokenType(tokens.get(j+1));
                }
                tokens.remove(tokens.size()-1);
                break;
            }
        }
        if (tokens.get(0).getTokenType() == TokenType.MOVE) { // MOVE COMMAND
            if (tokens.size() > 3) {
                throw exception2;
            } else if (tokens.size() < 3) {
                throw exception3;
            } else if (tokens.get(1).getTokenType() != TokenType.VAR) {
                throw exception1;
            }
            return new Move(tokens.get(1).getValue());
        } else if (tokens.get(0).getTokenType() == TokenType.GET) { // GET COMMAND
            if (tokens.size() > 3) {
                throw exception2;
            } else if (tokens.size() < 3) {
                throw exception3;
            } else if (tokens.get(1).getTokenType() != TokenType.VAR) {
                throw exception1;
            }
            return new Get(tokens.get(1).getValue());
        } else if (tokens.get(0).getTokenType() == TokenType.DROP) { // DROP COMMAND
            if (tokens.size() > 3) {
                throw exception2;
            } else if (tokens.size() < 3) {
                throw exception3;
            } else if (tokens.get(1).getTokenType() != TokenType.VAR) {
                throw exception1;
            }
            return new Drop(tokens.get(1).getValue());
        } else if (tokens.get(0).getTokenType() == TokenType.HELP) { // HELP COMMAND
            if (tokens.size() > 3) {
                throw exception2;
            } else if (tokens.size() == 2) {
                return new Help("null");
            } else if (tokens.get(1).getTokenType() == TokenType.VAR) {
                throw exception1;
            } else {
                return new Help(tokens.get(1).getValue());
            }
        } else if (tokens.get(0).getTokenType() == TokenType.LOOK) { // LOOK COMMAND
            if (tokens.size() > 3) {
                throw exception2;
            } else if (tokens.size() < 3) {
                throw exception3;
            } else if (tokens.get(1).getTokenType() != TokenType.VAR) {
                throw exception1;
            }
            return new Look(tokens.get(1).getValue());
        } else if (tokens.get(0).getTokenType() == TokenType.QUIT) { // QUIT COMMAND
            return new Quit();
        } else if (tokens.get(0).getTokenType() == TokenType.STATUS) { // STATUS COMMAND
            if (tokens.size() > 3) {
                throw exception2;
            } else if (tokens.size() < 3) {
                throw exception3;
            } else if (tokens.get(1).getTokenType() != TokenType.VAR) {
                throw exception1;
            }
            return new Status(tokens.get(1).getValue());
        } else if (tokens.get(0).getTokenType() == TokenType.USE) { // USE COMMAND
            if (tokens.size() > 5) {
                throw exception2;
            } else if (tokens.size() < 5) {
                throw exception3;
            } else if (!((tokens.get(1).getTokenType() == TokenType.VAR) && (tokens.get(2).getTokenType() == TokenType.PREPOSITION) && (tokens.get(3).getTokenType() == TokenType.VAR))) {
                throw exception1;
            }
            Use useCommand = new Use(tokens.get(1).getValue(), tokens.get(3).getValue());
            useCommand.setPreposition(tokens.get(2).getValue());
            return useCommand;
        } else if (tokens.get(0).getTokenType() == TokenType.COMBINE) { // COMBINE COMMAND
            if (tokens.size() > 6) {
                throw exception2;
            } else if (tokens.size() < 4) {
                throw exception3;
            } else if (!((tokens.get(1).getTokenType() == TokenType.VAR) && (tokens.get(2).getTokenType() == TokenType.CONJUNCTION) && (tokens.get(3).getTokenType() == TokenType.VAR))) {
                throw exception1;
            }
            return new Combine(tokens.get(1).getValue(), tokens.get(3).getValue());
        } else if (tokens.get(0).getTokenType() == TokenType.MISSIONS) { // MISSIONS COMMAND
            return new Missions();
        } else if (tokens.get(0).getTokenType() == TokenType.TALK) { // TALK COMMAND
            if (tokens.size() > 4) {
                throw exception2;
            } else if (tokens.size() < 4) {
                throw exception3;
            } else if (!((tokens.get(1).getTokenType() == TokenType.ADDRESS) && (tokens.get(2).getTokenType() == TokenType.VAR))) {
                throw exception1;
            }
            return new Talk(tokens.get(2).getValue());
        } else {
            throw exception4;
        }
    }

 
}
