package map;

import java.util.ArrayList;

public class Room {
    private int ID;
    private int type; // 0 = Classic room, 1 = Pathway
    private int x, y;
    private int sizeX, sizeY;
    private ArrayList<Wall> listWalls;

    public Room(int ID, int type, int x, int y, int sizeX, int sizeY) {
        this.ID = ID;
        this.type = type;
        this.x = x;
        this.y = y;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.listWalls = new ArrayList<>();
    }

    public int getID() { return ID; }
    public int getX() { return x; }
    public int getY() { return y; }
    public int getSizeX() { return sizeX; }
    public int getSizeY() { return sizeY; }
    public ArrayList<Wall> getListWalls() { return listWalls; }

    public void addWall(Wall wall) { listWalls.add(wall); }
}
