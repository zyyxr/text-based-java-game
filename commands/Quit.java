package commands;

import gameobjects.*;

/**
 * Represents the quit command, allowing the player to exit the game.
 * 
 * <p>
 * The quit command signals the end of the game session and provides a summary of the player's
 * current status before termination.
 * </p>
 */
public class Quit extends Command {

    public Quit() {
        this.commandType = CommandType.QUIT;

    }

    @Override
    public String execute(GameState gameState) {
        StringBuilder sb = new StringBuilder("Game over:\nYour current status: \n");
       String name = "Player Name: " + gameState.getPlayer().getName();
       sb.append(name);
       sb.append("\n");
       String score = "Player Score: " + gameState.getPlayer().getScore();
       sb.append(score);
       sb.append("\n");
       sb.append("------------\n");
       sb.append("Inventory: \n");
       if (gameState.getPlayer().getInventory() == null) {
           sb.append("empty \n");
       } else {
            for (Item i : gameState.getPlayer().getInventory()) {
                sb.append(i.getName().toLowerCase());
                sb.append("\n");
            }
       }
       sb.append("------------\n");
       sb.append("Equipment: \n");
        if (gameState.getPlayer().getEquipment() == null) {
            sb.append("empty \n");
        } else {
            for (Equipment e: gameState.getPlayer().getEquipment()) {
                sb.append(e.getName());
                sb.append("\n");
            }
        }
        sb.append("------------\n");
        return String.valueOf(sb);
    }

 
}
