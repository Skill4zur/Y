package main;

import utils.Window;

import org.lwjgl.glfw.*;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class Main{
	private static final int TARGET_FPS = 60; // Set TARGET_FPS to -1 if you don't want to limit
	private static final double TARGET_TIME = 1/(double)TARGET_FPS;
	
	private Window window;
	private int width = 1920;
	private int height = 1080;
	private double dt;
	private int currentFPS;

	private void run() {
		this.init();
		this.loop();
		this.terminate();
	}
	
	private void init() {
		GLFWErrorCallback.createPrint(System.err).set();

		if (!glfwInit()) {
			throw new IllegalStateException("Unable to initialize GLFW");
		}
		
		this.window = new Window(this.width, this.height, "Y");
		this.window.create();
		
	}
	
	private void loop() {
		long lastTime = System.nanoTime()/1000;
		
		while (!window.shouldClose()) {
			
			long currentTime = System.nanoTime()/1000;
			this.dt = (currentTime-lastTime)/1000.0;
			
			if ( TARGET_TIME <= this.dt ) {
				this.currentFPS = (int) (1/this.dt);
				this.render();
				this.update();
				lastTime = System.nanoTime()/1000;
			}
		}
		
	}
	
	private void terminate() {
		this.window.delete();
		// Terminate GLFW and free the error callback
		glfwTerminate();
		glfwSetErrorCallback(null).free();
	}
	
	
	
	private void render() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}
	
	private void update() {
		this.window.update();
	}
	
	public static void main(String[] args) {
		new Main().run();
	}
}