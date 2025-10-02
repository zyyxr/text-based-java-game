package gameobjects;

import java.util.ArrayList;

/**
 * Represents a room in the game, which is a type of {@code GameObject}.
 * 
 * <p>
 * Rooms can have items, equipment, features, and exits. They also manage navigation
 * and interactions within the game world.
 * </p>
 */
public class Room extends GameObject {
        ArrayList<Item> items = new ArrayList<>();
    ArrayList<Feature> features = new ArrayList<>();
    ArrayList<Exit> exits = new ArrayList<>();
    ArrayList<Equipment> equipment = new ArrayList<>();
    ArrayList<GameObject> allObjects = new ArrayList<>();
    ArrayList<Person> people = new ArrayList<>();
    boolean isLastRoom;


    public Room(String id, String name, String description, boolean hidden) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.hidden = hidden;
    }

    public boolean isLastRoom() {
        return isLastRoom;
    }

    public void setIsLastRoom(boolean bool) {
        this.isLastRoom = bool;
    }

    public void removeEquipmentID(String id) {
        for (Equipment e: equipment) {
            if (e.id.equals(id)) {
                equipment.remove(e);
                break;
            }
        }
    }

    public void removeItemId(String id) {
        for (Item i: items) {
            if (i.id.equals(id)) {
                items.remove(i);
                break;
            }
        }
    }

    public ArrayList<Person> getPeople() {
        return people;
    }

    public void addPerson(Person person) {
        people.add(person);
    }

    public boolean hasPerson(String name) {
        for (Person p: people) {
            if (p.name.equals(name)) {
                return true;
            }
        }
        return false;
    }

    public Person getPerson(String name) {
        for (Person p: people) {
            if (p.name.equals(name)) {
                return p;
            }
        }
        return null;
    }



    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public ArrayList<Exit> getExits() {
        return exits;
    }

    public void addExit(Exit exit) {
        exits.add(exit);
    }


    public boolean hasExit(String name) {
        for (Exit e: exits) {
            if (e.name.equals(name)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasExitUsingID(String id) {
        for (Exit e: exits) {
            if (e.id.equals(id)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public Item getItem(String id) {
        for (Item i: items) {
            if (i.id.equals(id)) {
                return i;
            }
        }
        return null;
    }

    public Item getItemByName(String name) {
        for (Item i: items) {
            if (i.name.equals(name)) {
                return i;
            }
        }
        return null;
    }

    public Feature getFeatureByName(String name) {
        for (Feature f: features) {
            if (f.name.equals(name)) {
                return f;
            }
        }
        return null;
    }

    public boolean hasFeature(String name) {
        for (Feature f: features) {
            if (f.name.equals(name)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Equipment> getEquipments() {
        return equipment;
    }

    public Equipment getEquipmentByName(String name) {
        for (Equipment e: equipment) {
            if (e.name.equals(name)) {
                return e;
            }
        }
        return null;
    }

    public Equipment getEquipment(String id) {
        for (Equipment e: equipment) {
            if (e.id.equals(id)) {
                return e;
            }
        }
        return null;
    }

    public Exit getExit(String id) {
        for (Exit e: exits) {
            if (e.id.equals(id)) {
                return e;
            }
        }
        return null;
    }

    public Exit getExitByName(String name) {
        for (Exit e: exits) {
            if (e.name.equals(name)) {
                return e;
            }
        }
        return null;
    }

    public void addEquipment(Equipment newEquipment) {
        equipment.add(newEquipment);
    }

    public Feature getFeature(String id) {
        for (Feature f: features) {
            if (f.id.equals(id)) {
                return f;
            }
        }
        return null;
    }



    public void addItem(Item item) {
        items.add(item);
    }

    public ArrayList<Feature> getFeatures() {
        return features;
    }

    public ArrayList<GameObject> getAll() {
        allObjects.clear();
        allObjects.addAll(items);
        allObjects.addAll(features);
        allObjects.addAll(exits);
        allObjects.addAll(equipment);
        return allObjects;
    }

    public void addFeature(Feature feature) {
        features.add(feature);
    }

    public boolean hasItem(String itemName) {
        for (Item i: items) {
            if (i.name.equals(itemName)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasEquipment(String equipmentName) {
        for (Equipment e: equipment) {
            if (e.getName().equals(equipmentName)) {
                return true;
            }
        }
        return false;
    }

    public void removeItem(String itemName) {
        for (Item i: items) {
            if (itemName.equals(i.name)) {
                items.remove(i);
                break;
            }
        }
    }

    public void removeEquipment(String equipmentName) {
        for (Equipment e: equipment) {
            if (equipmentName.equals(e.name)) {
                equipment.remove(e);
                break;
            }
        }
    }


    /**
     * Returns a string representation of the room, including its contents and description.
     *
     * @return a string describing the room
     */
    @Override
    public String toString() {
        String out = "[" + id + "] Room: " + name + "\nDescription: " + description + "\nIn the room there is: ";
        for (Item i : this.items) {
            out += i + "\n";
        }
        for (Equipment e : this.equipment) {
            out += e + "\n";
        }
        for (Feature f : this.features) {
            out += f + "\n";
        }
        for (Exit e : this.exits) {
            out += e + "\n";
        }
        return out + "\n";
    }
}
