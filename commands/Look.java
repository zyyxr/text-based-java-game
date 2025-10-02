package commands;

import gameobjects.*;

/**
 * Represents the look command, allowing the player to examine various elements of the game world.
 * 
 * <p>
 * The look command can provide details about the current room, its exits, features, or specific items and equipment.
 * Hidden objects are not included in the output unless explicitly revealed.
 * </p>
 */
public class Look extends Command {
    String target;
    GameObject gameObjectTarget;

    public Look(String target) {
        this.target = target;
        this.commandType = CommandType.LOOK;

    }

    public void convertTargetToObj(GameState gameState) {
        if (gameState.getMap().getCurrentRoom().hasItem(target)) {
            gameObjectTarget = gameState.getMap().getCurrentRoom().getItem(target);// if our target is referring to an item
        } else if (gameState.getMap().getCurrentRoom().hasEquipment(target)) {
            gameObjectTarget = gameState.getMap().getCurrentRoom().getEquipmentByName(target);
        }
    }

    @Override
    public String toString() {
        return "Command: " + this.commandType.toString() + " " + target;
    }

    @Override
    public String execute(GameState gameState) {
        Player player = gameState.getPlayer();
        Room currentRoom = gameState.getMap().getCurrentRoom();
        convertTargetToObj(gameState);
        if (gameObjectTarget == null) {
            switch (target) {
                case "exits": {
                    String message = "The available exits are: \n";
                    for (Exit e : currentRoom.getExits()) {
                        if (e.getHidden()) {
                            continue;
                        }
                        message += (e.getDescription()) + "\n";
                    }
                    return message;
                }
                case "room": {
                    String message = (currentRoom.getDescription()) + "\nYou see: \n";
                    for (Person p : currentRoom.getPeople()) {
                        if (p.getHidden()) {
                            continue;
                        }
                        message += (p.getDescription()) + "\n";
                    }
                    for (Item i : currentRoom.getItems()) {
                        if (i.getHidden()) {
                            continue;
                        }
                        message += (i.getDescription()) + "\n";
                    }
                    for (Equipment e : currentRoom.getEquipments()) {
                        if (e.getHidden()) {
                            continue;
                        }
                        message += (e.getDescription()) + "\n";
                    }
                    for (Feature f : currentRoom.getFeatures()) {
                        if (f.getHidden()) {
                            continue;
                        }
                        message += (f.getDescription()) + "\n";
                    }
                    for (Exit e : currentRoom.getExits()) {
                        if (e.getHidden()) {
                            continue;
                        }
                        message += (e.getDescription()) + "\n";
                    }
                    return message;
                }
                case "features": {
                    String message = "You also see: \n";
                    for (Feature f : currentRoom.getFeatures()) {
                        if (f.getHidden()) {
                            continue;
                        }
                        message += (f.getDescription()) + "\n";
                    }
                    return message;
                }
                case "people": {
                    String message = "You also see: \n";
                    for (Person p : currentRoom.getPeople()) {
                        if (p.getHidden()) {
                            continue;
                        }
                        message += (p.getDescription()) + "\n";
                    }
                    return message;
                }
            }
        } else {
            return gameObjectTarget.getDescription();
        }
        return "";
    }


}
