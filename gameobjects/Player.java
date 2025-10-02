package gameobjects;

import java.util.ArrayList;

/**
 * Represents the player in the game, including their name, inventory, and equipment.
 * 
 * <p>
 * The player can carry items and equipment, interact with the game world, and perform
 * actions using their inventory or equipment.
 * </p>
 */
public class Player {

    String name;
    ArrayList<Item> inventory = new ArrayList<>();
    ArrayList<Equipment> equipment = new ArrayList<>();
    ArrayList<String> combinations = new ArrayList<>();
    ArrayList<String> missions = new ArrayList<>();
    int score = 100;

    public ArrayList<String> getMissions() {
        return missions;
    }

    public void addMission(String mission, String missionReward, String missionCompleted, String missionCompleteMessage, String requiredItem) {
        missions.add(mission);
        missions.add(missionReward);
        missions.add(missionCompleted);
        missions.add(missionCompleteMessage);
        missions.add(requiredItem);
    }

    public void checkMission() {
        for (int i = 0; i < missions.size(); i++) {
            if (missions.get(i).equals("false") && (hasItemID(missions.get(i+2))  ||hasEquipmentID(missions.get(i+2)))) {
                System.out.println("MISSION COMPLETED: " + missions.get(i+1));
                adjustScore(Integer.parseInt(missions.get(i-1)));
                missions.set(i,"true");
            }
            }
        }

    public boolean hasItemID(String id) {
        for (Item i: inventory) {
            if (i == null) {
                break;
            }
            if (i.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasEquipmentID(String id) {
        for (Equipment e: equipment) {
            if (e == null) {
                break;
            }
            if (e.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }


    public void addCombinationItem(String itemOneID, String itemTwoID, String newObjectClass, String newItemID, String newItemName, String newItemDescription, String isHidden) {
        combinations.add(itemOneID);
        combinations.add(itemTwoID);
        combinations.add(newObjectClass);
        combinations.add(newItemID);
        combinations.add(newItemName);
        combinations.add(newItemDescription);
        combinations.add(isHidden);
    }

    public void addCombinationEquipment(String itemOneID, String itemTwoID, String newObjectClass, String newEquipmentID, String newEquipmentName, String newEquipmentDescription, String isHidden, String action, String target, String result, String message) {
        combinations.add(itemOneID);
        combinations.add(itemTwoID);
        combinations.add(newObjectClass);
        combinations.add(newEquipmentID);
        combinations.add(newEquipmentName);
        combinations.add(newEquipmentDescription);
        combinations.add(isHidden);
        combinations.add(action);
        combinations.add(target);
        combinations.add(result);
        combinations.add(message);
    }

    public String getScore() {
        return String.valueOf(score);
    }

    public void adjustScore(int adjustment) {
        score += adjustment;
    }

    public ArrayList<String> getCombinations() {
        return combinations;
    }

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public boolean hasItem(String itemName) {
        for (Item i : inventory) {
            if (i == null) {
                return false;
            }
            if (i.name.equals(itemName)) {
                return true;
            }
        }
        return false;
    }


    public void addItem(Item item) {
        inventory.add(item);
    }

    public Item getItem(String itemName) {
        if (hasItem(itemName)) {
            for (Item i: inventory) {
                if (i.name.equals(itemName)) {
                    return i;
                }
            }
        }
        return null;
    }

    public void removeItem(String itemName) {
        for (Item i: inventory) {
            if (i.name.equals(itemName)) {
                inventory.remove(i);
                break;
            }
        }
    }

    public boolean hasEquipment(String equipmentName) {
        for (Equipment e: equipment) {
            if (e == null) {
                return false;
            }
            if (e.name.equals(equipmentName)) {
                return true;
            }
        }
        return false;
    }

    public void addEquipment(Equipment newEquipment) {
        equipment.add(newEquipment);
    }

    public Equipment getEquipment(String equipmentName) {
        if (hasEquipment(equipmentName)) {
            for (Equipment e: equipment) {
                if (e.name.equals(equipmentName)) {
                    return e;
                }
            }
        }
        return null;
    }

    public void removeEquipment(String equipmentName) {
        for (Equipment e: equipment) {
            if (e.name.equals(equipmentName)) {
                equipment.remove(e);
                break;
            }
        }
    }

    public ArrayList<Equipment> getEquipment() {
        return equipment;
    }

   

    /**
     * Returns a string representation of the player's current state, including their name,
     * inventory, and equipment descriptions.
     *
     * @return a string describing the player, their inventory, and equipment
     */
    @Override
    public String toString() {
        StringBuilder out = new StringBuilder("Player Name: " + this.name + "\nInventory:\n");
        for (Item i : this.inventory) {
            out.append("- ").append(i.getDescription()).append("\n");
        }
        out.append("Equipment:\n");
        for (Equipment e : this.equipment) {
            out.append("- ").append(e.getDescription()).append("\n");
        }
        return out.toString();
    }
}
