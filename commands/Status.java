package commands;

import gameobjects.*;

/**
 * Represents the status command, allowing the player to retrieve information
 * about their inventory, specific items, or their overall status.
 *
 * <p>
 * The status command can display a list of items in the player's inventory,
 * provide details about a specific item, or show the player's general status.
 * </p>
 */
public class Status extends Command {
    String topic;


    public Status(String topic) {
        this.topic = topic;
        this.commandType = CommandType.STATUS;

    }

    @Override
    public String toString() {
        return "Command: " + this.commandType.toString() + " " + topic;
    }


    @Override
    public String execute(GameState gameState) {
        Player player = gameState.getPlayer();

        if (player.getEquipment(topic) != null) {
            return player.getEquipment(topic).getDescription();
        } else if (player.getItem(topic) != null) {
            return player.getItem(topic).getDescription();
        } else if (topic.equals("inventory")) {
            StringBuilder inventoryStrB = new StringBuilder();
            inventoryStrB.append("Inventory: \n--------------------\n");
            inventoryStrB.append("Items: \n");
            for (Item i : player.getInventory()) {
                inventoryStrB.append(i.getName());
                inventoryStrB.append("\n");
            }
            inventoryStrB.append("--------------------\n");
            inventoryStrB.append("Equipment: \n");
            for (Equipment e : player.getEquipment()) {
                inventoryStrB.append(e.getName());
                inventoryStrB.append("\n");
            }
            inventoryStrB.append("--------------------\n");
            return String.valueOf(inventoryStrB);
        } else if (topic.equals("player")) {
            StringBuilder sb = new StringBuilder();
            String str = "Player Name: ";
            sb.append((str += player.getName()));
            sb.append("\n------------\n");
            sb.append("Items: \n");
            for (Item i : player.getInventory()) {
                sb.append(i.getName());
                sb.append("\n");
            }
            sb.append("------------\n");
            sb.append("Equipment: \n");
            for (Equipment e : player.getEquipment()) {
                sb.append(e.getName());
                sb.append("\n");
            }
            sb.append("------------\n");
            return String.valueOf(sb);
        } else if (topic.equals("map")) {
            return gameState.getMap().returnMap();
        } else if (topic.equals("score")) {
            return "Player Score: " + player.getScore();
        }
        return "";
    }
}
