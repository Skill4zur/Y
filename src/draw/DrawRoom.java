package draw;

import map.Map;
import map.Room;
import map.Wall;
import static org.lwjgl.opengl.GL11.*;

public class DrawRoom {

    public static void draw(Map map) {
        for (Room room : map.getRoomList()) {
            drawRoom(room);
            drawWalls(room); // Draw walls inside the room
        }
    }

    private static void drawRoom(Room room) {
        float x = room.getX();
        float y = room.getY();
        float sizeX = room.getSizeX();
        float sizeY = room.getSizeY();

        // Convert room position and size to OpenGL coordinates
        float[] glCoords1 = DrawMain.convertCartesianToGL(x, y);
        float[] glCoords2 = DrawMain.convertCartesianToGL(x + sizeX, y + sizeY);

        // Draw the room as a rectangle
        glColor3f(0.5f, 0.5f, 0.5f); // Room color (gray)
        glBegin(GL_QUADS);
        glVertex2f(glCoords1[0], glCoords1[1]);
        glVertex2f(glCoords2[0], glCoords1[1]);
        glVertex2f(glCoords2[0], glCoords2[1]);
        glVertex2f(glCoords1[0], glCoords2[1]);
        glEnd();
    }

    private static void drawWalls(Room room) {
        for (Wall wall : room.getListWalls()) {
            float wallX = room.getX() + wall.getX(); // Wall relative to room
            float wallY = room.getY() + wall.getY();
            float wallSizeX = wall.getSizeX();
            float wallSizeY = wall.getSizeY();

            // Convert wall position and size to OpenGL coordinates
            float[] glCoords1 = DrawMain.convertCartesianToGL(wallX, wallY);
            float[] glCoords2 = DrawMain.convertCartesianToGL(wallX + wallSizeX, wallY + wallSizeY);

            // Draw the wall as a filled rectangle
            glColor3f(0.6f, 0.3f, 0.1f); // Room color (gray)
            glBegin(GL_QUADS);
            glVertex2f(glCoords1[0], glCoords1[1]);
            glVertex2f(glCoords2[0], glCoords1[1]);
            glVertex2f(glCoords2[0], glCoords2[1]);
            glVertex2f(glCoords1[0], glCoords2[1]);
            glEnd();
        }
    }
}
