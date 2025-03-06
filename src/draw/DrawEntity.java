package draw;

import map.Entity;
import static org.lwjgl.opengl.GL11.*;

public class DrawEntity {
    public static void draw(float x, float y, Entity entity) {
        glEnable(GL_TEXTURE_2D);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        int textureID = entity.getCurrentTexture(entity.getAnimationTag()[0], entity.getAnimationTag()[1]);

        glBindTexture(GL_TEXTURE_2D, textureID);

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
        glColor3f(1.0f, 1.0f, 1.0f); // Set color (white)

        glBegin(GL_QUADS);
        glTexCoord2f(0.0f, 0.0f); glVertex2f(x, y);
        glTexCoord2f(1.0f, 0.0f); glVertex2f(x + 100, y);
        glTexCoord2f(1.0f, 1.0f); glVertex2f(x + 100, y + 100);
        glTexCoord2f(0.0f, 1.0f); glVertex2f(x, y + 100);
        glEnd();

        glDisable(GL_TEXTURE_2D);
    }
}
