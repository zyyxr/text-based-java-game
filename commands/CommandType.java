package commands;

/**
 * Represents the various types of commands that can be executed in the game.
 * 
 * <p>
 * Each command type corresponds to a specific player action or game functionality.
 * </p>
 */
public enum CommandType {
    MOVE, // Represents a command to move the player to a different location.
    USE, // Represents a command to use an item or interact with a game object.
    GET, // Represents a command to pick up an item.
    DROP, //  Represents a command to drop an item from the player's inventory.
    LOOK, // Represents a command to look around the current location or inspect an object.
    STATUS, // Represents a command to check the player's current status.
    HELP, // Represents a command to display help information.
    QUIT, // Represents a command to quit the game.
    COMBINE, // Represents a command to combine two items.
    MISSIONS, // Represents missions command.
    TALK;


}
