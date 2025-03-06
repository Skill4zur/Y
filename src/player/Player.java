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
        x = newX;
        y = newY;
    }

    public float getX() { return x; }
    public float getY() { return y; }
}
