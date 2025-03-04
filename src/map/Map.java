package map;

import java.util.ArrayList;

public class Map {
    private ArrayList<Room> roomList;
    private Room currentRoom;

    public Map() {
        this.roomList = new ArrayList<>();
        this.currentRoom = null; // Default value if no room is set initially
    }

    public void addRoom(Room room) {
        roomList.add(room);
    }

    public ArrayList<Room> getRoomList() {
        return roomList;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void updateCurrentRoom(float playerX, float playerY) {
        for (Room room : roomList) {
            float roomX = room.getX();
            float roomY = room.getY();
            float roomSizeX = room.getSizeX();
            float roomSizeY = room.getSizeY();

            // Check if the player is inside this room's bounds
            if (playerX >= roomX && playerX <= roomX + roomSizeX &&
                    playerY >= roomY && playerY <= roomY + roomSizeY) {
                currentRoom = room;
                break; // If a room is found, break the loop
            }
        }
    }

    // Optional: If you want a quick lookup by room ID
    public Room getRoomById(int roomId) {
        for (Room room : roomList) {
            if (room.getID() == roomId) {
                return room;
            }
        }
        return null; // No room found
    }
}
