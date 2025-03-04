package draw;

import data.TextureLoader;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;

public class DrawEntity {
    private static int playerTextureID;

    static {
        try {
            playerTextureID = TextureLoader.loadTexture("D:/Dev/Y/src/assets/player/char.png");
            if (playerTextureID == 0) {
                throw new Exception("Failed to load texture.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void draw(float x, float y) {
        float size = 100.0f;
        glColor3f(1.0f, 1.0f, 1.0f); // Room color (gray)

        glEnable(GL_TEXTURE_2D);
        glBindTexture(GL_TEXTURE_2D, playerTextureID);

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

        glBegin(GL_QUADS);
        glTexCoord2f(0.0f, 0.0f); glVertex2f(x, y);
        glTexCoord2f(1.0f, 0.0f); glVertex2f(x + size, y);
        glTexCoord2f(1.0f, 1.0f); glVertex2f(x + size, y + size);
        glTexCoord2f(0.0f, 1.0f); glVertex2f(x, y + size);
        glEnd();

        glDisable(GL_TEXTURE_2D);
    }
}
