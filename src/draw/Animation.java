package draw;

import static main.Main.dt;
import map.Entity;
import map.Map;

public class Animation {
    private static double currentFrameTime;

    public static void update(Map map) {
        currentFrameTime += dt;
        if (currentFrameTime > 0.450) {
            for (Entity entity : Map.getListEntities()) {
                int currentFrame = entity.getAnimationTag()[1];
                int totalFrames = entity.getTextures(entity.getAnimationTag()[0]).size();
                currentFrame++;

                if (currentFrame >= totalFrames) {
                    currentFrame = 0;
                }
                entity.setAnimationTag(currentFrame, 1);
            }
            currentFrameTime = 0;
        }
    }

    public double getCurrentFrameTime() {return this.currentFrameTime;}

    public void setCurrentFrameTime(double currentFrameTime) {this.currentFrameTime = currentFrameTime;}
}
