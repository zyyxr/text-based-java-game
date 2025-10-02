package commands;

import gameobjects.*;

/**
 * Represents the help command, providing the player with instructions or information
 * about various topics related to the game.
 *
 * <p>
 * The help command displays information on how to play the game, including details about 
 * available commands, their syntax, and their purpose.
 * </p>
 */
public class Help extends Command {
    String topic;
    CommandType type = CommandType.HELP;

    public Help(String topic) {
        if (!(topic == null)) {
            this.topic = topic.toLowerCase();
        } else {
            this.topic = "null";
        }
        this.commandType = CommandType.HELP;

    }

    @Override
    public String toString() {
        if (topic != null) {
            return "Command: " + this.commandType.toString() + " " + topic;
        } else {
            return "Command: HELP null";
        }
    }

    @Override
    public String execute(GameState gameState) {
        return switch (topic) {
            case "null" ->
                    "Welcome to the game!\nAvailable Commands:\n- MOVE\n- USE\n- LOOK\n- GET\n- STATUS\n- DROP\n- TALK\n- QUIT\n- HELP\n- COMBINE\n- MISSIONS\nYou can use \"help <command>\" to get further information on a specific command.";
            case "move" ->
                    "MOVE Command:\nFormat: 'move <exit name>'\nUse the 'move' command to move in a certain direction, to a different location as defined by an exit's name (e.g., 'move north'). ";
            case "use" ->
                    "USE Command:\nFormat: 'use <equipment name> (on|with <feature|item>)'\nUse the 'use' command to use an item in your inventory on its own, or on a feature of item (e.g., 'use lamp' or 'use key on chest'). ";
            case "look" ->
                    "LOOK Command:\nFormat: 'look <room|exist|features>|<item name>|<equipment name>|<feature name>'\nUse the 'look' command to look around the current room, at an exit, at a feature, or, at a specific item, equipment, or feature, and get further information on it. ";
            case "get" ->
                    "GET Command:\nFormat: 'get <item name|equipment name>'\nUse the 'get' command to pick up an item or equipment from the current room (e.g., 'get key'). ";
            case "status" ->
                    "STATUS Command:\nFormat: 'status <inventory|player|item name|equipment name|map|score>'\nUse the 'status' command to check your current status, or inventory; or get more information about a specific item or equipment in your inventory (e.g., 'status map', 'status inventory', 'status key'. ";
            case "drop" ->
                    "DROP Command:\nFormat: 'drop <item name|equipment name>\nUse the 'drop' command to drop an item or equipment from your inventory (e.g., 'drop key'). ";
            case "quit" -> "QUIT Command:\nFormat: 'quit'\nUse the 'quit' command to exit the game. ";
            case "combine" ->
                    "COMBINE Command:\nFormat: 'combine <item1> and <item2'>\nCombine two items into a new item or equipment (e.g., 'combine stick and rock'). ";
            case "help" ->
                    "HELP Command:\nFormat: 'help <topic>'\nDisplays help information about all available commands in a help message if no argument is given, otherwise information is displayed on a specified topic (e.g., 'help move'). ";
            case "missions" ->
                    "MISSIONS Command:\nFormat: 'missions'\nDisplays a list of missions that have/have not been completed. ";
            case "talk" ->
                    "TALK Command:\nFormat: 'talk to <person name>'\nIf the person is present in the current room, they will exchange dialogue with you. ";
            default -> "No help available for the topic: " + topic;
        };
    }
}
