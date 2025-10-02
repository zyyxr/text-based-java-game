package commands;

import gameobjects.*;

import java.util.ArrayList;


public class Missions extends Command {

    public Missions() {
        this.commandType = CommandType.MISSIONS;

    }

    @Override
    public String execute(GameState gameState) {
        ArrayList<String> missions = gameState.getPlayer().getMissions();
        StringBuilder strMissions = new StringBuilder("Missions: \n");
        for (int i=0; i < missions.size(); i++) {
            if (missions.get(i).equals("true")) {
                strMissions.append("- ");
                strMissions.append(missions.get(i-2));
                strMissions.append(" : COMPLETED \n");
            } else if (missions.get(i).equals("false")) {
                strMissions.append("- ");
                strMissions.append(missions.get(i-2));
                strMissions.append(" : NOT COMPLETED \n");
            }
        }
        return String.valueOf(strMissions);

    }


}
