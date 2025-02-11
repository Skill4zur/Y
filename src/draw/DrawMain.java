package draw;

import static org.lwjgl.opengl.GL11.*;

import player.Player;
import player.Camera;
import map.Map;

public class DrawMain {
    static float ScreenWidth;
    static float ScreenHeight;
    static Camera camera;

    public static float[] convertCartesianToGL(float x, float y) {
        float adjustedX = x - camera.getX();
        float adjustedY = y - camera.getY();

        float glX = x // Use adjusted X for OpenGL
        float glY = ScreenHeight/2 - adjustedY; // Adjust Y based on screen height

        return new float[]{glX, glY};
    }


    public static void drawAll(Player player, Map map, float screenWidth, float screenHeight, Camera cam) {
        ScreenWidth = screenWidth;
        ScreenHeight = screenHeight;
        camera = cam;

        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        DrawRoom.draw(map);
        float[] glCoords = convertCartesianToGL(player.getX(), player.getY());
        DrawPlayer.draw(glCoords[0], glCoords[1]);
    }
}
