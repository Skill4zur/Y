package player;

import map.Map;
import map.Room;

public class Camera {
    private float x, y; // Camera position
    private Player player;

    public Camera(Player player) {
        this.player = player;
    }

    public void update(Map map, float screenHeight, float screenWidth) {
        int CurrentRoomId = player.getCurrentRoomId();

        Room currentRoom = getRoomById(CurrentRoomId, map);

        if (currentRoom != null) {
            // Center the camera on the current room
            x = currentRoom.getX() + (float) currentRoom.getSizeX() / 2;
            y = currentRoom.getY() + (float) currentRoom.getSizeY() / 2;
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
