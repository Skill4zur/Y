package player;
import map.Map;
import map.Room;

import static org.lwjgl.glfw.GLFW.*;

public class Player {
    private float x, y;
    private float speed = 12.0f;
    private float size = 100.0f;
    private int CurrentRoomID;

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


        if ((glfwGetKey(window, GLFW_KEY_D) == GLFW_PRESS)){
        }

        if (isInsideRoom(newX, newY, map)) {
            x = newX;
            y = newY;
        }
    }

    private boolean isInsideRoom(float newX, float newY, Map map) {
        for (Room room : map.getRoomList()) {
            float roomX = room.getX();
            float roomY = room.getY();
            float roomSizeX = room.getSizeX();
            float roomSizeY = room.getSizeY();

            if (newX >= roomX && newX + size <= roomX + roomSizeX &&
                    newY - size >= roomY && newY <= roomY + roomSizeY) {
                CurrentRoomID = room.getID();
                return true;
            }
        }
        return false;
    }

    public float getX() { return x; }
    public float getY() { return y; }
    public int getCurrentRoomId() {return CurrentRoomID; }
}
