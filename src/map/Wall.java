package map;

public class Wall {
    private float x, y;
    private float sizeX, sizeY;

    public Wall(float x, float y, float sizeX, float sizeY) {
        this.x = x;
        this.y = y;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    public float getX() { return x; }
    public float getY() { return y; }
    public float getSizeX() { return sizeX; }
    public float getSizeY() { return sizeY; }
}
