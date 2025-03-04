package draw;

import data.TextureLoader;
import static org.lwjgl.opengl.GL11.*;

public class DrawEntity {
    public static void draw(float x, float y, int textureID) {
        glEnable(GL_TEXTURE_2D);
        glBindTexture(GL_TEXTURE_2D, textureID);

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

        glBegin(GL_QUADS);
        glTexCoord2f(0.0f, 0.0f); glVertex2f(x, y);
        glTexCoord2f(1.0f, 0.0f); glVertex2f(x, y);
        glTexCoord2f(1.0f, 1.0f); glVertex2f(x, y);
        glTexCoord2f(0.0f, 1.0f); glVertex2f(x, y);
        glEnd();

        glDisable(GL_TEXTURE_2D);
    }
}
