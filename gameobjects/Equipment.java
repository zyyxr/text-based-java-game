package gameobjects;

public class Equipment extends GameObject implements Usable {
    UseInformation useInformation;

    public Equipment(String id, String name, String description, boolean hidden, UseInformation useInformation) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.hidden = hidden;
        this.useInformation = useInformation;
    }
    /**
     * Returns a string representation of this equipment, including the attributes inherited from {@code GameObject}
     * and the associated use information.
     *
     * @return a string describing the equipment
     */
    @Override
    public String toString() {
        return super.toString() + ", useInformation=" + useInformation;
    }

     public String use(GameObject target, GameState gameState) {
        Room currentRoom = gameState.getMap().getCurrentRoom();
        Player player = gameState.getPlayer();
        if (useInformation.getTarget().equals(target.getId())) {// checks if the target u passed is the correct target to this equipment
            useInformation.setUsed(true); // if its correct target, we set the equipment to used
            if (currentRoom.getItem(useInformation.getResult()) != null) { // if the target has no item, we print success msg
                player.addItem(gameState.getMap().getCurrentRoom().getItem(useInformation.result)); // otherwise we get item from target
                currentRoom.getItem(useInformation.getResult()).setHidden(false); // item would be hidden so we say it isnt hidden
                currentRoom.removeItemId(useInformation.getResult());
            } else if (currentRoom.getEquipment(useInformation.getResult()) != null) { // if the target has no item, we print success msg
                player.addEquipment(gameState.getMap().getCurrentRoom().getEquipment(useInformation.result)); // otherwise we get item from target
                currentRoom.getEquipment(useInformation.getResult()).setHidden(false);
                currentRoom.removeEquipmentID(useInformation.getResult());
            }
        } else if (target.getClass() == Room.class){
           currentRoom.setHidden(false);
        }
        return useInformation.message; // return success msg
    }

    public UseInformation getUseInformation() {
        return this.useInformation;
    }

    public void setUseInformation(UseInformation useInformation) {
        this.useInformation = useInformation;
    }
}
