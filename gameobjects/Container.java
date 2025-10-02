package gameobjects;

/**
 * Represents a conatiner in the game, which is a type of feature that may contain items
 * or serve as an interactable object within a room.
 * 
 * <p>
 * Conatiner can have a name, description, and visibility state, which determines if they
 * are initially hidden or visible to the player.
 * </p>
 */
public class Container extends Feature {

    public Container(String id, String name, String description, boolean hidden){
        this.id = id;
        this.name = name;
        this.description = description;
        this.hidden = hidden;

    }


    @Override
    public String getName() {
        return name;
    }


    
    /**
     * Returns a string representation of the container.
     *
     * @return a string containing the container's id, name, description, and hidden status
     */
    @Override
    public String toString() {
        return "Container {" +
                "id='" + getId() + '\'' +
                ", name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", hidden=" + getHidden() +
                '}';
    
    }
 
}
