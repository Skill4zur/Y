package map;

import java.util.ArrayList;

public class Room {
    private int ID;
    private int type; //0 = Classic room, 1=Pathway
    private int x, y;
    private int sizeX, sizeY;
    private ArrayList<Object> listObjects;

    public Room(int ID, int type, int x, int y, int sizeX, int sizeY) {
        this.ID = ID;
        this.type = type;
        this.x = x;
        this.y = y;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.listObjects = new ArrayList<>();
    }

    public int getID() {
        return ID;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public ArrayList<Object> getListObjects() {
        return listObjects;
    }

    public void addObject(Object obj) {
        listObjects.add(obj);
    }
}
