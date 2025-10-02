package commands;

import gameobjects.*;

/**
 * Represents the move command, allowing the player to navigate to a different room in the game world.
 * 
 * <p>
 * The move command checks if the specified direction corresponds to an available exit in the current room.
 * If a matching exit is found, the player's location is updated to the connected room.
 * </p>
 */
public class Move extends Command {
    String direction;


    public Move(String direction) {
        this.direction = direction;
        this.commandType = CommandType.MOVE;
    }
    @Override
    public String toString() {
        return "Command: " + this.commandType.toString() + " " + direction;
    }

    @Override
    public String execute(GameState gameState) {
        Player player = gameState.getPlayer();
        Map map = gameState.getMap();
        Room currentRoom = gameState.getMap().getCurrentRoom();

        if ((!(direction.equals("north"))) && (!(direction.equals("south"))) && (!(direction.equals("east"))) && (!(direction.equals("west")))) {
            return "Invalid direction provided. ";
        }
        if ((currentRoom.getExitByName(direction) == null) || (currentRoom.getExitByName(direction).getHidden())) {
            return "No exit found in that direction.";
        } else {
            map.setCurrentRoom(currentRoom.getExitByName(direction).getNextRoom());
            if (gameState.getMap().getCurrentRoom().isLastRoom()) {
                System.out.println("You've beaten the game! ");
                return "Player Score: " + gameState.getPlayer().getScore();
            }
            if (map.getMap() != null) {
            map.updateExit(direction);
            map.movePlayer(direction);
            }
            player.adjustScore(-5);
            return "Moving towards " + direction +"\n";
        }
    }
}
