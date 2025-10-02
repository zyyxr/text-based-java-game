package commands;
import gameobjects.*;

import java.util.ArrayList;

/**
 * Represents the combine command, allowing the player to use equipment on a specific target in the game.
 *
 * <p>
 * The use command checks if the player has the specified equipment and whether it can interact with
 * the target. The target can be a feature, item, or the current room, depending on the game context.
 * </p>
 */
public class Combine extends Command {
    String itemOneName;
    String itemTwoName;
    CommandType commandType;

    public Combine(String itemOneName, String itemTwoName) {
        this.itemOneName = itemOneName;
        this.itemTwoName = itemTwoName;
        this.commandType = CommandType.COMBINE;

    }

    @Override
    public String toString() {
        return "Command: " + this.commandType.toString() + " " + itemOneName + "and " + itemTwoName;
    }

    @Override
    public String execute(GameState gameState) {
        Player player = gameState.getPlayer();
        if ((player.getItem(itemOneName) == null) || (player.getItem(itemTwoName) == null)) {
            return "BOTH items are not present in your inventory. ";
        } else {
            ArrayList<String> combinations = gameState.getPlayer().getCombinations();
            for (int i = 0; i < combinations.size(); i++) {
                if (((player.getItem(itemOneName).getId().equals(combinations.get(i))) || (player.getItem(itemOneName).getId().equals(combinations.get(i + 1)))) && ((player.getItem(itemTwoName).getId().equals(combinations.get(i))) || (player.getItem(itemTwoName).getId().equals(combinations.get(i + 1))))) {
                    if (combinations.get(i + 2).equals("item")) {
                        player.removeItem(itemOneName);
                        player.removeItem(itemTwoName);
                        player.addItem(new Item(combinations.get(i + 3), combinations.get(i + 4), combinations.get(i + 5), combinations.get(i + 6).equals("true")));
                        return "You have combined " + itemOneName + " and " + itemTwoName + " to make: " + combinations.get(i + 4);
                    } else if (combinations.get(i + 2).equals("equipment")) {
                        player.removeItem(itemOneName);
                        player.removeItem(itemTwoName);
                        player.addEquipment(new Equipment(combinations.get(i + 3), combinations.get(i + 4), combinations.get(i + 5), combinations.get(i + 6).equals("true"), new UseInformation(false, combinations.get(i + 7), combinations.get(i + 8), combinations.get(i + 9), combinations.get(i + 10))));
                        return "You have combined " + itemOneName + " and " + itemTwoName + " to make: " + combinations.get(i + 4);
                    } else {
                        return "Error in GameState file. ";
                    }
                }
            }
            return "You cannot combine these two items. ";
        }
    }
}
