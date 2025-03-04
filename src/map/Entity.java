package map;

import java.awt.*;
import java.util.ArrayList;
import java.io.File;

public class Entity {
    private int id;
    private float x, y; // Position of the entity
    private ArrayList<Image> render; // List of images to render (textures)

    // Constructor to initialize the entity
    public Entity(int id, float x, float y) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.render = new ArrayList<>();
        loadTextures("assets/entity/" + id + "/"); // Directory path based on entity ID
    }

    private void loadTextures(String path) {
        File folder = new File(path);
        File[] files = folder.listFiles(); // No filter needed since only .png files are present
        if (files != null) {
            for (File file : files) {
                Image img = Toolkit.getDefaultToolkit().getImage(file.getAbsolutePath());
                render.add(img);
            }
        }
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public float getX() {return x;}
    public void setX(float x) {this.x = x;}
    public float getY() {return y;}
    public void setY(float y) {this.y = y;}
}
