package map;

import java.util.ArrayList;

public class Map {
    private ArrayList<Room> roomList;
    private static ArrayList<Entity> listEntities;

    private Room currentRoom;


    public Map() {
        this.listEntities = new ArrayList<>();
        this.roomList = new ArrayList<>();
        this.currentRoom = null;
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

            if (playerX >= roomX && playerX <= roomX + roomSizeX &&
                    playerY >= roomY && playerY <= roomY + roomSizeY) {
                currentRoom = room;
                break;
            }
        }
    }

    public Room getRoomById(int roomId) {
        for (Room room : roomList) {
            if (room.getID() == roomId) {
                return room;
            }
        }
        return null;
    }

    public void addEntity(Entity entity) { listEntities.add(entity); }
    public static ArrayList<Entity> getListEntities() { return listEntities; }

}
