package draw;

import player.Player;

import static org.lwjgl.opengl.GL11.*;

public class DrawMain {
    public static void drawAll(Player player) {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // Clear screen
        DrawPlayer.draw(player); // Draw the player
    }
}
