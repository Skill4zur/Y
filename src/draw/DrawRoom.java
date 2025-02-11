package draw;

import map.Map;
import map.Room;
import static org.lwjgl.opengl.GL11.*;

public class DrawRoom {

    public static void draw(Map map) {
        for (Room room : map.getRoomList()) {
            drawRoom(room);
        }
    }

    private static void drawRoom(Room room) {
        float x = room.getX();
        float y = room.getY();
        float sizeX = room.getSizeX();
        float sizeY = room.getSizeY();

        // Convert both position and size from Cartesian to GL in one go
        float[] glCoords1 = DrawMain.convertCartesianToGL(x, y);
        float[] glCoords2 = DrawMain.convertCartesianToGL(x + sizeX, y + sizeY);

        // Draw the room as a rectangle
        glBegin(GL_QUADS);
        glVertex2f(glCoords1[0], glCoords1[1]);
        glVertex2f(glCoords2[0], glCoords1[1]);
        glVertex2f(glCoords2[0], glCoords2[1]);
        glVertex2f(glCoords1[0], glCoords2[1]);
        glEnd();
    }

}
