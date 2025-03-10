package draw;

import static org.lwjgl.opengl.GL11.*;
import java.util.List;

import map.Entity;
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

        for (Room room : map.getRoomList()) {
            List<Entity> entities = Room.getListEntities();

            for (Entity entity : entities) {
                float[] entityCoords = convertCartesianToGL(entity.getX(), entity.getY());
                DrawEntity.draw(entityCoords[0], entityCoords[1], Animation.getTextureID(entity));
            }
        }
    }

}
