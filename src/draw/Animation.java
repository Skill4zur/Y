package draw;
import static main.Main.dt;
import map.Entity;



public class Animation {
    private static double currentFrameTime;

    public static void update(Entity entity) {
        currentFrameTime+=dt;
        System.out.println(currentFrameTime);

        if (currentFrameTime>0.250) {
            int currentFrame = (int) entity.getAnimationTagID()[1];
            int totalFrames = entity.getAnimationSet(entity.getAnimationTagID()[0]).size();
            currentFrame++;

            if (currentFrame >= totalFrames) {
                currentFrame = 0;
            }
            entity.setAnimationTagIDB(currentFrame);
            currentFrameTime=0;
        }
    }

    private double getCurrentFrameTime(){
        return this.currentFrameTime;
    }

    private void setCurrentFrameTime(int i){
        this.currentFrameTime = i;
    }
}
