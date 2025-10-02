package gameobjects;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.*;
import java.nio.file.*;

import utils.*;

/**
 * Represents the game map, which consists of a collection of rooms and the current room the player is in.
 * 
 * <p>
 * The map allows for navigation between rooms, adding new rooms, and managing the current room context.
 * </p>
 */
public class Map {

    Room currentRoom;
    ArrayList<Room> rooms = new ArrayList<>();
    char[][] mapDisplay;
    final char playerSymbol = 'X';
    int playerPositionX;
    int playerPositionY;
    int length;
    int width;

    public Map() {
    }

    public void createMap(int length,int width) {
        this.length = length + length - 1;
        this.width = width + width - 1;
        mapDisplay = new char[(this.length)][(this.width)];
        for (int i = 0; i < this.length; i++) {
            if (((i + 1) % 2 == 0)) {
                for (int j = 0; j < this.width; j++) {
                    mapDisplay[i][j] = ' ';
                }
            } else {
                for (int j = 0; j < this.width; j++) {
                    if (((j + 1) % 2 == 0)) {
                        mapDisplay[i][j] = ' ';
                    } else {
                        mapDisplay[i][j] = 'o';
                    }
                }
            }
        }
    }

    public void setPlayerPosition(int playerPositionX, int playerPositionY) {
        this.playerPositionX = playerPositionX;
        this.playerPositionY = playerPositionY;
        mapDisplay[playerPositionY][playerPositionX] = 'X';
    }

    public void updateExit(String direction) {
        switch (direction) {
            case "north":
                if (playerPositionY != 0) {
                    mapDisplay[playerPositionY - 1][playerPositionX] = '|';
                }
                break;
            case "south":
                if (playerPositionY != length-1) {
                    mapDisplay[playerPositionY + 1][playerPositionX] = '|';
                }
                break;
            case "west":
                if (playerPositionX != width-1) {
                    mapDisplay[playerPositionY][playerPositionX-1] = '—';
                }
                break;
            case "east":
                if (playerPositionX != 0) {
                    mapDisplay[playerPositionY][playerPositionX+1] = '—';
                }
                break;
        }
    }

    public void movePlayer(String direction) {
        switch (direction) {
            case "north": mapDisplay[playerPositionY][playerPositionX] = 'o'; mapDisplay[playerPositionY - 2][playerPositionX] = playerSymbol; playerPositionY-=2; break;
            case "south": mapDisplay[playerPositionY][playerPositionX] = 'o'; mapDisplay[playerPositionY + 2][playerPositionX] = playerSymbol; playerPositionY+=2;break;
            case "east": mapDisplay[playerPositionY][playerPositionX] = 'o'; mapDisplay[playerPositionY][playerPositionX + 2] = playerSymbol; playerPositionX+=2;break;
            case "west": mapDisplay[playerPositionY][playerPositionX] = 'o'; mapDisplay[playerPositionY][playerPositionX - 2] = playerSymbol; playerPositionX-=2;break;
        }

    }

    public Room getRoom(String roomID) {
        for (Room r: rooms) {
            if (r.id.equals(roomID)) {
                return r;
            }
        }
        return null;
    }

    public char[][] getMap() {
        return mapDisplay;
    }

    public String returnMap() {
        String map = "";
        for (char[] a: mapDisplay) {
            for (char b: a) {
                map += String.valueOf(b);
                map += " ";
            }
            map += "\n";
        }
        return map;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public void setCurrentRoom(String roomId) {
        for (Room r: rooms) {
            if (roomId.equals(r.id)) {
                currentRoom = r;
            }
        }
    }

    public void makeRoomVisible() {
        for (Exit e: currentRoom.getExits()) {
            e.setHidden(false);
        }
        for (Item i: currentRoom.getItems()) {
            i.setHidden(false);
        }
        for (Equipment e: currentRoom.getEquipments()) {
            e.setHidden(false);
        }
        for (Feature f: currentRoom.getFeatures()) {
            f.setHidden(false);
        }
    }
  
    /**
     * Returns a string representation of the map, including all rooms.
     *
     * @return a string describing the map and its rooms
     */
    @Override
    public String toString() {
        StringBuilder out = new StringBuilder("Map:\n");
        for (Room r : this.rooms) {
            out.append(r.toString()).append("\n");
        }
        return out.toString();
    }
}

