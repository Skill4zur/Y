package map;

import data.TextureLoader;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Entity {
    private int id;
    private float x, y;
    private ArrayList<ArrayList<Integer>> renderAnimations; // Store OpenGL texture IDs
    private int[] AnimationTagID;

    public Entity(int id, float x, float y) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.renderAnimations = new ArrayList<>();
        this.AnimationTagID = new int[]{id, 0};

        for (int i = 0; i < 5; i++) {
            renderAnimations.add(new ArrayList<>());
        }

        // Load textures into OpenGL IDs
        loadTextures(System.getProperty("user.dir") + File.separator + "src" + File.separator + "assets" + File.separator + "render" + File.separator + id + File.separator);
    }

    private void loadTextures(String basePath) {
        // Load texture sets for different animations
        loadTextureSet(renderAnimations.get(0), basePath + "movement/");   // Movement
        loadTextureSet(renderAnimations.get(1), basePath + "attack/");     // Attack
        loadTextureSet(renderAnimations.get(2), basePath + "damage/");     // Damage
        loadTextureSet(renderAnimations.get(3), basePath + "item/");       // Item animation
        loadTextureSet(renderAnimations.get(4), basePath + "idle/");       // Idle

        // Print loaded textures (for debugging)
        System.out.println("Loaded movement textures:");
        for (int i = 0; i < renderAnimations.get(0).size(); i++) {
            System.out.println("Texture " + (i + 1) + ": " + renderAnimations.get(0).get(i));
        }
    }

    private void loadTextureSet(ArrayList<Integer> targetList, String path) {
        File folder = new File(path);
        File[] files = folder.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".png")) {
                    // Load texture into OpenGL and store the texture ID
                    int textureID = TextureLoader.loadTexture(file.getAbsolutePath());
                    targetList.add(textureID);
                }
            }
        }
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public float getX() { return x; }
    public void setX(float x) { this.x = x; }
    public float getY() { return y; }
    public void setY(float y) { this.y = y; }
    public int[] getAnimationTagID() {
        return AnimationTagID;
    }    public void setAnimationTagIDA(int i) { this.AnimationTagID[0] = i; }
    public void setAnimationTagIDB(int i) { this.AnimationTagID[1] = i; }

    public ArrayList<Integer> getAnimationSet(int index) {
        return renderAnimations.get(index);
    }

    public int getRenderAnimation(int index1, int index2) {
        ArrayList<Integer> animationSet = renderAnimations.get(index1);
        return animationSet.get(index2);  // Return texture ID instead of Image
    }

}
