package utils;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

import gameobjects.*;

import javax.swing.*;

/**
 * Utility class for parsing a game state from a file.
 * 
 * <p>
 * This class reads a structured text file to create a {@code GameState} object,
 * including the player, map, rooms, items, equipment, features, and exits.
 * </p>
 */
public class GameStateFileParser {


    public static GameState parse(String filename) {
        File gameStateFile = new File(filename); // should be path but get away with just name?
        BufferedReader br;
        GameState gameState = null;
        try {
            Map map = new Map();
            br = new BufferedReader(new FileReader(gameStateFile));
            String line = "";
            String startingRoomID = "";
            while ((line = br.readLine()) != null) {
                if (line.contains(":")) {
                    String[] lineSplit = line.split(":");
                    String category = lineSplit[0];
                    String details = lineSplit[1];
                    switch (category) {
                        case ("player"):
                            gameState = new GameState(map, new Player(details));
                            break;
                        case ("map"):
                            String[] mapDetails = details.split(",");
                            startingRoomID = mapDetails[0];
                            if (mapDetails.length != 1) {
                            String mapLength = mapDetails[1];
                            String mapWidth = mapDetails[2];
                            String playerPositionX = mapDetails[3];
                            String playerPositionY = mapDetails[4];
                            gameState.getMap().createMap(Integer.parseInt(mapLength),Integer.parseInt(mapWidth));
                            gameState.getMap().setPlayerPosition(Integer.parseInt(playerPositionX),Integer.parseInt(playerPositionY));
                            }
                            break;
                        case ("room"):
                            String[] roomDetails = details.split(",");
                            String roomID = roomDetails[0];
                            String roomName = roomDetails[1];
                            String roomDesc = roomDetails[2];
                            boolean roomHidden = roomDetails[3].equals("true");
                            gameState.getMap().addRoom(new Room(roomID, roomName, roomDesc, roomHidden));
                            gameState.getMap().setCurrentRoom(roomID);
                            if (roomDetails.length == 5) {
                                boolean isLastRoom = roomDetails[4].equals("true");
                                gameState.getMap().getCurrentRoom().setIsLastRoom(isLastRoom);
                            }
                            break;
                        case ("container"):
                            String[] containerDetails = details.split(",");
                            String containerID = containerDetails[0];
                            String containerName = containerDetails[1];
                            String containerDescription = containerDetails[2];
                            boolean containerHidden = containerDetails[3].equals("true");
                            gameState.getMap().getCurrentRoom().addFeature(new Container(containerID, containerName, containerDescription, containerHidden));
                            break;
                        case ("equipment"):
                            String[] equipmentDetails = details.split(",");
                            String equipmentID = equipmentDetails[0];
                            String equipmentName = equipmentDetails[1];
                            String equipmentDescription = equipmentDetails[2];
                            boolean isEquipmentHidden = equipmentDetails[3].equals("true");
                            String equipmentUseAction = equipmentDetails[4];
                            String equipmentTarget = equipmentDetails[5];
                            String equipmentResult = equipmentDetails[6];
                            String equipmentUseDescription = equipmentDetails[7];
                            gameState.getMap().getCurrentRoom().addEquipment(new Equipment(equipmentID, equipmentName, equipmentDescription, isEquipmentHidden, new UseInformation(false, equipmentUseAction, equipmentTarget, equipmentResult, equipmentUseDescription)));
                            break;
                        case ("item"):
                            String[] itemDetails = details.split(",");
                            String itemID = itemDetails[0];
                            String itemName = itemDetails[1];
                            String itemDescription = itemDetails[2];
                            boolean itemHidden = itemDetails[3].equals("true");
                            gameState.getMap().getCurrentRoom().addItem(new Item(itemID, itemName, itemDescription, itemHidden));
                            break;
                        case ("exit"):
                            String[] exitDetails = details.split(",");
                            String exitID = exitDetails[0];
                            String exitName = exitDetails[1];
                            String exitDescription = exitDetails[2];
                            String exitNextRoomID = exitDetails[3];
                            boolean exitHidden = exitDetails[4].equals("true");
                            gameState.getMap().getCurrentRoom().addExit(new Exit(exitID, exitName, exitDescription, exitNextRoomID, exitHidden));
                            break;
                        case ("combination"):
                            String[] combinationDetails = details.split(",");
                            String itemOneID = combinationDetails[0];
                            String itemTwoID = combinationDetails[1];
                            String newObjectClass = combinationDetails[2];
                            if (newObjectClass.equals("item")) {
                                String newItemID = combinationDetails[3], newItemName = combinationDetails[4], newItemDescription = combinationDetails[5], isHidden = combinationDetails[6];
                                gameState.getPlayer().addCombinationItem(itemOneID, itemTwoID, newObjectClass, newItemID, newItemName, newItemDescription, isHidden);
                            } else {
                                String newEquipmentID = combinationDetails[3], newEquipmentName = combinationDetails[4], newEquipmentDescription = combinationDetails[5], isHidden = combinationDetails[6], action = combinationDetails[7], target = combinationDetails[8], result = combinationDetails[9], message = combinationDetails[10];
                                gameState.getPlayer().addCombinationEquipment(itemOneID, itemTwoID, newObjectClass, newEquipmentID, newEquipmentName, newEquipmentDescription, isHidden, action, target, result, message);
                            }
                            break;
                        case ("missions"):
                            String[] missionsDetails = details.split(",");
                            String mission = missionsDetails[0];
                            String missionReward = missionsDetails[1];
                            String missionsCompleted = missionsDetails[2];
                            String missionsCompletedMessage = missionsDetails[3];
                            String requiredItemID = missionsDetails[4];
                            gameState.getPlayer().addMission(mission,missionReward,missionsCompleted,missionsCompletedMessage,requiredItemID);
                            break;
                        case ("person"):
                            String[] personDetails = details.split(",");
                            String personID = personDetails[0];
                            String personName = personDetails[1];
                            String personDescription = personDetails[2];
                            boolean personHidden = personDetails[3].equals("true");
                            String personDialogue = personDetails[4];
                            gameState.getMap().getCurrentRoom().addPerson(new Person(personID,personName,personDescription,personHidden,personDialogue));
                            break;
                        default:
                            break;
                    }
                }
            }
            gameState.getMap().setCurrentRoom(startingRoomID);
            br.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
        return gameState;
    }
}