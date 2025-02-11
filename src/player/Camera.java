package player;

import map.Map;
import map.Room;
import player.Player;

public class Camera {
    private float x, y; // Camera position
    private Player player;

    public Camera(Player player) {
        this.player = player;
    }

    public void update(Map map, float screenHeight, float screenWidth) {
        int CurrentRoomId = player.getCurrentRoomId();
        System.out.println(CurrentRoomId);

        Room currentRoom = getRoomById(CurrentRoomId, map);

        if (currentRoom.getSizeX() < screenWidth && currentRoom.getSizeY() < screenHeight) {
            // Center the camera on the current room
            x = currentRoom.getX() + (float) currentRoom.getSizeX() / 2;
            y = currentRoom.getY() + (float) currentRoom.getSizeY() / 2;
        }
        else {
            // Add logic in order to make the camera stop when reaching the border of the room
            x = player.getX();
            y = player.getY();
        }
    }

    private Room getRoomById(int roomId, Map map) {
        for (Room room : map.getRoomList()) {
            if (room.getID() == roomId) {
                return room;
            }
        }
        return null;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
