package map;

import java.util.ArrayList;

public class Map {
    private ArrayList<Room> roomList;

    public Map() {
        this.roomList = new ArrayList<>();
    }

    public void addRoom(Room room) {
        roomList.add(room);
    }

    public ArrayList<Room> getRoomList() {
        return roomList;
    }
}
