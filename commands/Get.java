package commands;

import gameobjects.*;

/**
 * Represents the get command, allowing the player to pick up an item from the current room and add it to their inventory.
 * 
 * <p>
 * This command checks if the specified item is present in the current room. If the item exists and the player
 * does not already have it, the item is added to the player's inventory and removed from the room. Otherwise,
 * an appropriate message is returned.
 * </p>
 */
public class Get extends Command {
    String itemName;
    Item item;
    Equipment equipment;

    public Get(String itemName) {
        this.itemName = itemName;
        this.commandType = CommandType.GET;
    }


    @Override
    public String toString() {
        return "Command: " + this.commandType.toString() + " " + itemName;
    }

    @Override
    public String execute(GameState gameState) {
        Player player = gameState.getPlayer();
        Room currentRoom = gameState.getMap().getCurrentRoom();
        if (player.hasItem(itemName) || player.hasEquipment(itemName)) {
            return "You already have " + itemName;
        }
        item = currentRoom.getItemByName(itemName);
        equipment = currentRoom.getEquipmentByName(itemName);
        if (item != null) {
            player.addItem(item);
            currentRoom.removeItem(itemName);
            return "You pick up: " + itemName;
        } else if (equipment != null) {
            player.addEquipment(equipment);
            currentRoom.removeEquipment(itemName);
            return "You pick up: " + itemName;
        }
        return "No nonexistent to get.";
    }

   
}
