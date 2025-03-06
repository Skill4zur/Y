package map;

import data.TextureLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.File;

public class Entity {
    private int id;
    private float x, y;
    private float targetX, targetY;
    private String type;
    private float velocity;

    private ArrayList<ArrayList<Integer>> renderAnimations;
    private int[] AnimationTag;  // [0] -> animation type, [1] -> frame number

    private static final Map<Integer, ArrayList<ArrayList<Integer>>> animationCache = new HashMap<>();

    public Entity(int id, float x, float y) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.velocity = 25;
        this.AnimationTag = new int[]{0, 0};

        if (animationCache.containsKey(id)) {
            this.renderAnimations = animationCache.get(id);
            System.out.println("Reusing animation set for entity ID: " + id);
        } else {
            this.renderAnimations = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                renderAnimations.add(new ArrayList<>());
            }

            String projectDir = System.getProperty("user.dir");
            String assetsPath = new File(projectDir, "src" + File.separator + "assets" + File.separator).getPath();
            loadTextures(assetsPath);

            animationCache.put(id, renderAnimations);
        }
    }

    private void loadTextures(String basePath) {
        String renderPath = basePath + File.separator + "render" + File.separator + id + File.separator;

        loadTextureSet(renderAnimations.get(0), renderPath + "movement" + File.separator);
        loadTextureSet(renderAnimations.get(1), renderPath + "attack" + File.separator);
        loadTextureSet(renderAnimations.get(2), renderPath + "damage" + File.separator);
        loadTextureSet(renderAnimations.get(3), renderPath + "item" + File.separator);
        loadTextureSet(renderAnimations.get(4), renderPath + "idle" + File.separator);
    }

    private void loadTextureSet(ArrayList<Integer> textureList, String path) {
        File folder = new File(path);
        File[] files = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".png"));

        if (files != null && files.length > 0) {
            for (File file : files) {
                int textureID = TextureLoader.loadTexture(file.getAbsolutePath());
                textureList.add(textureID);
            }
        }
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public float getX() { return x; }
    public void setX(float x) { this.x = x; }

    public float getY() { return y; }
    public void setY(float y) { this.y = y; }

    public ArrayList<Integer> getTextures(int i) { return renderAnimations.get(i); }

    public int[] getAnimationTag() { return AnimationTag; }

    public void setAnimationTag(int value, int index) { AnimationTag[index] = value; }

    public int getCurrentTexture(int index1, int index2) { return renderAnimations.get(index1).get(index2); }
}
