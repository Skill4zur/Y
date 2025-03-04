package player;

import map.Entity;
import map.Map;
import map.Room;
import map.Wall;

import java.awt.*;
import java.util.ArrayList;

import static org.lwjgl.glfw.GLFW.*;

public class Player extends Entity {
    private float x, y;
    private float speed = 12.0f;
    private float size = 50.0f; // Adjusted size for better wall collision

    public Player(int id, float x, float y) {
        super(id, x, y);
        init(x,y);
    }

    public void init(float startX, float startY) {
        this.x = startX;
        this.y = startY;
    }

    public void update(long window, Map map) {
        float newX = x;
        float newY = y;

        if (glfwGetKey(window, GLFW_KEY_W) == GLFW_PRESS) newY += speed;
        if (glfwGetKey(window, GLFW_KEY_S) == GLFW_PRESS) newY -= speed;
        if (glfwGetKey(window, GLFW_KEY_A) == GLFW_PRESS) newX -= speed;
        if (glfwGetKey(window, GLFW_KEY_D) == GLFW_PRESS) newX += speed;

        // Update the current room based on the player's new position
        map.updateCurrentRoom(newX, newY);

        // Ensure player stays in room and does not collide with walls
        updatePosition(newX, newY, map);
    }

    public void updatePosition(float newX, float newY, Map map) {
        // Correct position if there are wall collisions
        float[] correctedPosition = getCorrectedPosition(newX, newY, map);
        x = correctedPosition[0];
        y = correctedPosition[1];
    }

    private float[] getCorrectedPosition(float newX, float newY, Map map) {
        float[] corrected;
        Room currentRoom = map.getCurrentRoom();
        corrected = checkRoomCollide(newX, newY, currentRoom);
        corrected =  checkWallCollide(corrected, currentRoom);
        return corrected;
    }

    private float[] checkRoomCollide(float newX, float newY, Room currentRoom){
         float roomX = currentRoom.getX();
         float roomY = currentRoom.getY();
         float roomSizeX = currentRoom.getSizeX();
         float roomSizeY = currentRoom.getSizeY();

        newX = (newX <= roomX) ? roomX : newX;
        newX = (newX + 2*size >= roomX + roomSizeX) ? roomX + roomSizeX - 2*size : newX;
        newY = (newY - 2*size <= roomY) ? roomY+2*size : newY;
        newY = (newY >= roomY + roomSizeY) ? roomY+roomSizeY : newY;

        return new float[] {newX, newY};
    }

    private float[] checkWallCollide(float[] corrected, Room currentRoom) {
        float roomX = currentRoom.getX();
        float roomY = currentRoom.getY();
        float newX = corrected[0];
        float newY = corrected[1];

        for (Wall wall : currentRoom.getListWalls()) {
            float wallX = roomX + wall.getX(); // Absolute position of the wall
            float wallY = roomY + wall.getY();
            float wallSizeX = wall.getSizeX();
            float wallSizeY = wall.getSizeY();

            // Check for collision with walls
            boolean collidesLeft = false;
            boolean collidesRight = false;
            boolean collidesBottom = false;
            boolean collidesTop = false;

            newX = collidesLeft ? wallX - 2*size : collidesRight ? wallX + wallSizeX : newX;
            newY = collidesTop ? wallY + wallSizeY + 2*size : collidesBottom ? wallY : newY;
        }

        return new float[]{newX, newY};
    }

    public float getX() { return x; }
    public float getY() { return y; }
}
