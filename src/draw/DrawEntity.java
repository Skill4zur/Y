package draw;

import map.Entity;

import static org.lwjgl.opengl.GL11.*;

public class DrawEntity {
    public static void draw(float x, float y, Entity entity) {
        glEnable(GL_TEXTURE_2D);

        int textureID = entity.getRenderAnimation(entity.getAnimationTagID()[0], entity.getAnimationTagID()[1]);
        glBindTexture(GL_TEXTURE_2D, textureID);

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
        glColor3f(1.0f, 1.0f, 1.0f); // Room color (gray)

        // Draw the entity (a quad in this case)
        glBegin(GL_QUADS);
        glTexCoord2f(0.0f, 0.0f); glVertex2f(x, y);
        glTexCoord2f(1.0f, 0.0f); glVertex2f(x + 100, y);
        glTexCoord2f(1.0f, 1.0f); glVertex2f(x + 100, y + 100);
        glTexCoord2f(0.0f, 1.0f); glVertex2f(x, y + 100);
        glEnd();

        glDisable(GL_TEXTURE_2D);
    }
}
