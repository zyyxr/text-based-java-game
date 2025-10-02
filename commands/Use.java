package commands;

import gameobjects.*;

/**
 * Represents the use command, allowing the player to use equipment on a specific target in the game.
 * 
 * <p>
 * The use command checks if the player has the specified equipment and whether it can interact with
 * the target. The target can be a feature, item, or the current room, depending on the game context.
 * </p>
 */
public class Use extends Command {
    String equipmentName;
    String target;
    String preposition;

    public Use(String equipmentName, String target) {
        this.equipmentName = equipmentName;
        this.target = target;
        this.commandType = CommandType.USE  ;

    }

    public void setPreposition(String preposition) {
        this.preposition = preposition;
    }
    @Override
    public String toString() {
        return "Command: " + this.commandType.toString() + " " + equipmentName + " " + preposition + " " + target;
    }

    @Override
    public String execute(GameState gameState) {
        Player player = gameState.getPlayer();
        Room currentRoom = gameState.getMap().getCurrentRoom();
        if (player.hasItem(equipmentName)) {
            return "You presented an item - the object in use must be a piece of equipment. ";
        } else if (player.getEquipment(equipmentName) == null) {
            return "You do not have " + equipmentName;
        } else if (player.getEquipment(equipmentName).getUseInformation().isUsed()) {
            return "You have already used " + equipmentName;
        } else if ((target.equals("room"))  && (player.getEquipment(equipmentName).getUseInformation().getTarget().equals(gameState.getMap().getCurrentRoom().getId()))) {
            gameState.getMap().makeRoomVisible();
            return player.getEquipment(equipmentName).use(gameState.getMap().getCurrentRoom(), gameState);
        } else if (currentRoom.getFeatureByName(target) != null) {
            if (player.getEquipment(equipmentName).getUseInformation().getTarget().equals(currentRoom.getFeatureByName(target).getId()) && !currentRoom.getFeatureByName(target).getHidden()) {
                return player.getEquipment(equipmentName).use(currentRoom.getFeatureByName(target), gameState);
            }
        } else if (player.getItem(target) != null) {
            if (player.getEquipment(equipmentName).getUseInformation().getTarget().equals(player.getItem(target).getId()) && !player.getItem(target).getHidden()) {
                return player.getEquipment(equipmentName).use(currentRoom.getItemByName(target), gameState);
            }
        }
        return "Invalid use target";
    }
}

