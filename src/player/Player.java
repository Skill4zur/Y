package player;

import static org.lwjgl.glfw.GLFW.*;

public class Player {
    private float x, y;
    private float speed = 12.0f;

    public void init(float startX, float startY) {
        this.x = startX;
        this.y = startY;
    }

    public void update(long window) {
        if (glfwGetKey(window, GLFW_KEY_W) == GLFW_PRESS) y -= speed;  // Up
        if (glfwGetKey(window, GLFW_KEY_S) == GLFW_PRESS) y += speed;  // Down
        if (glfwGetKey(window, GLFW_KEY_A) == GLFW_PRESS) x -= speed;  // Left
        if (glfwGetKey(window, GLFW_KEY_D) == GLFW_PRESS) x += speed;  // Right
    }

    public float getX() { return x; }
    public float getY() { return y; }
}