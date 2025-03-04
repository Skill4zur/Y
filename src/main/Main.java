package main;

import utils.Window;

import org.lwjgl.glfw.*;
import player.Player;
import map.Map;
import map.Room;
import player.Camera;
import map.Wall;
import draw.DrawMain;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class Main{
	private static final int TARGET_FPS = 60; // Set TARGET_FPS to -1 if you don't want to limit
	private static final double TARGET_TIME = 1/(double)TARGET_FPS;
	
	private Window window;
	private int screenWidth;
	private int screenHeight;
	private double dt;
	private int currentFPS;
	// For the Game
	private Player player;
	private Map map;
	private Camera camera;

	private void run() {
		this.init();
		this.loop();
		this.terminate();
	}
	
	private void init() {
		//Start lwjgl
		GLFWErrorCallback.createPrint(System.err).set();
		if (!glfwInit()) {
			throw new IllegalStateException("Unable to initialize GLFW");
		}

		// Create Window
		GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		screenWidth = vidmode.width();
		screenHeight = vidmode.height();
		this.window = new Window(screenWidth, screenHeight, "Y");
		this.window.create();

		//Instance game data
		player = new Player(0, 700, 600);

		map = new Map();
		Room room1 = new Room(0, 0, 300, 300, 500, 200);
		Room room2 = new Room(1, 0, 600, 600, 5500, 500);
		map.addRoom(room1);
		map.addRoom(room2);

		Wall wall1 = new Wall(150, 150, 200, 200); // (x, y, width, height)
		room2.addWall(wall1);

		camera = new Camera(player);
		
	}
	
	private void loop() {
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		glOrtho(0, screenWidth, screenHeight, 0, -1, 1);
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
		DrawMain.drawAll(player, map, screenWidth, screenHeight, camera);
	}
	
	private void update() {
		this.player.update(window, map);
		this.camera.update(map, screenHeight, screenWidth);
		this.window.update();
	}
	
	public static void main(String[] args) {
		new Main().run();
	}
}