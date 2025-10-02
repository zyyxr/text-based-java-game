package commands;

import gameobjects.*;

/**
 * Represents the drop command, allowing the player to drop an item from their inventory into the current room.
 * 
 * <p>
 * This command checks if the player possesses the specified item and, if so, removes it from their inventory
 * and adds it to the current room. If the player does not have the item, an error message is returned.
 * </p>
 */
public class Drop extends Command {
    String objectName;

    public Drop(String itemName) {
        this.objectName = itemName;
        this.commandType = CommandType.DROP;
    }

    @Override
    public String toString() {
        return "Command: " + this.commandType.toString() + " " + objectName;
    }

    @Override
    public String execute(GameState gameState) {
        Player player = gameState.getPlayer();
        Map map = gameState.getMap();
        if (player.hasItem(objectName)) {
            map.getCurrentRoom().addItem(gameState.getPlayer().getItem(objectName));
            player.removeItem(objectName);
            return "You drop: " + objectName;
        } else  if (player.hasEquipment(objectName)) {
            map.getCurrentRoom().addEquipment(gameState.getPlayer().getEquipment(objectName));
            player.removeEquipment(objectName);
            return "You drop: " + objectName;
        } else {
            return "You cannot drop " + objectName;
        }
    }
}

