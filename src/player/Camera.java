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

        int currentRoomId = player.getCurrentRoomId();
        Room currentRoom = getRoomById(currentRoomId, map);

        // Center the camera on the current room if room is smaller than the screen
        if (currentRoom.getSizeX() < screenWidth && currentRoom.getSizeY() < screenHeight) {
            x = currentRoom.getX() + (float) currentRoom.getSizeX() / 2;
            y = currentRoom.getY() + (float) currentRoom.getSizeY() / 2;
        } else {
            // Update x based on player position and room size
            x = (currentRoom.getSizeX() < screenWidth)
                    ? currentRoom.getX() + (float) currentRoom.getSizeX() / 2
                    : (player.getX() - screenWidth / 2 > currentRoom.getX() && player.getX() + screenWidth / 2 < currentRoom.getX() + currentRoom.getSizeX())
                    ? player.getX()
                    : (player.getX() - screenWidth / 2 < currentRoom.getX())
                    ? currentRoom.getX() + screenWidth / 2
                    : currentRoom.getX() + currentRoom.getSizeX() - screenWidth / 2;

            // Update y based on player position and room size
            y = (currentRoom.getSizeY() < screenHeight)
                    ? currentRoom.getY() + (float) currentRoom.getSizeY() / 2
                    : (player.getY() - screenHeight / 2 > currentRoom.getY() && player.getY() + screenHeight / 2 < currentRoom.getY() + currentRoom.getSizeY())
                    ? player.getY()
                    : (player.getY() - screenHeight / 2 < currentRoom.getY())
                    ? currentRoom.getY() + screenHeight / 2
                    : currentRoom.getY() + currentRoom.getSizeY() - screenHeight / 2;
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
