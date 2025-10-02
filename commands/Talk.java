package commands;
import gameobjects.*;

import java.util.ArrayList;


public class Talk extends Command {
    String targetName;
    CommandType commandType;

    public Talk(String name) {
        this.targetName = name;
        this.commandType = CommandType.TALK;

    }

    @Override
    public String toString() {
        return "Command: " + this.commandType.toString() + " to " + targetName;
    }

    @Override
    public String execute(GameState gameState) {
        if (gameState.getMap().getCurrentRoom().getPerson(targetName) == null) {
            return "This person does not exist in the room. ";
        } else {
            return gameState.getMap().getCurrentRoom().getPerson(targetName).getDialogue();
        }
    }
}
