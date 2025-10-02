package gameobjects;

public class Person extends GameObject {
    String dialogue;

    public Person(String id, String name, String description, boolean hidden, String dialogue) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.hidden = hidden;
        this.dialogue = dialogue;
    }

    public String getDialogue() {
        return dialogue;
    }

}
