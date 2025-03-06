package draw;

import static org.lwjgl.opengl.GL11.*;
import java.util.List;

import map.Entity;
import map.Room;
import player.Player;
import player.Camera;
import map.Map;

public class DrawMain {
    static float ScreenWidth;
    static float ScreenHeight;
    static Camera camera;

    public static float[] convertCartesianToGL(float x, float y) {
        float adjustedX = x - camera.getX() + ScreenWidth/2;
        float adjustedY = -(y - camera.getY()) + ScreenHeight/2;
        return new float[]{adjustedX, adjustedY};
    }

    public static void drawAll(Player player, Map map, float screenWidth, float screenHeight, Camera cam) {
        ScreenWidth = screenWidth;
        ScreenHeight = screenHeight;
        camera = cam;

        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        DrawRoom.draw(map);

        float[] glCoords = convertCartesianToGL(player.getX(), player.getY());

        DrawEntity.draw(glCoords[0], glCoords[1], player);

        Animation.update(map);

        for (Entity entity : Map.getListEntities()) {
            // Check if entity is within screen bounds before rendering
            if (isEntityVisible(entity)) {
                float[] entityCoords = convertCartesianToGL(entity.getX(), entity.getY());
                DrawEntity.draw(entityCoords[0], entityCoords[1], entity);
            }
        }

    }
    private static boolean isEntityVisible(Entity entity) {
        float entityX = entity.getX();
        float entityY = entity.getY();

        float cameraX = camera.getX();
        float cameraY = camera.getY();

        float halfScreenWidth = ScreenWidth / 2;
        float halfScreenHeight = ScreenHeight / 2;

        // Check if entity is inside the visible screen bounds
        return entityX >= (cameraX - halfScreenWidth) &&
                entityX <= (cameraX + halfScreenWidth) &&
                entityY >= (cameraY - halfScreenHeight) &&
                entityY <= (cameraY + halfScreenHeight);
    }
}
