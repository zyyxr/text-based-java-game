
import java.util.ArrayList;
import java.util.Scanner;

import commands.*;
import gameobjects.*;
import parser.*;
import utils.*;

/**
 * Main class for the game application. Handles game setup, input parsing, and game execution.
 * 
 * <p>
 * This class initializes the game state, reads user input, processes commands, and maintains the game loop.
 * </p>
 */
public class Game {
    static GameState gameState; // initialising gamestate, parser, tokeniser and scanner
    static Scanner input;
    static Tokeniser tokeniser;
    static Parser parser;

    public static void setup() {
        input = new Scanner(System.in);
        tokeniser = new Tokeniser();
        parser = new Parser();
        System.out.println("Loading game... ");
        gameState = GameStateFileParser.parse("game.txt"); // parses the gamestate file to load content into our game
        }

    public static void start() {
        if (gameState == null) {
            System.out.println("Game could not be loaded. "); // if our game couldn't be parsed, it will output an error message
        } else {
            System.out.println("Game loaded successfully. ");
            while (true) { // starting and maintaining the game loop
                System.out.print(">> ");
                String userInput = input.nextLine(); // taking in user input for commands
                tokeniser.tokenise(userInput); // will sanitise and tokenise the user input into a token list so it can be processed
                try {
                    Command userCommand = parser.parse(tokeniser.getTokens()); // the tokenlist is processed into a command which we can execute.
                    if (userCommand.getCommandType() == CommandType.QUIT) { // if our command is quit, we execute the command, and break the loop to exit the game
                        turn(userCommand);
                        break;
                    } else {
                        turn(userCommand); // Executes the command
                        gameState.getPlayer().checkMission(); // Checks if any mission has been completed - if so, an appropriate message is returned.
                    }
                } catch (CommandErrorException e) { // if an invalid command is returned, we print an appropriate error message, thrown by line 41
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public static void turn(Command command) {
        System.out.println(command.execute(gameState)); // prints out the result of the command, based on the user input
    }

    public static void main(String[] args) {
        setup(); // initialises gamestate with content from game.txt
        start(); // starts the while loop to allow the game to continue playing until the user quits


    }

}
