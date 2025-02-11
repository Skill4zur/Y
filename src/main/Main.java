package main;

import draw.DrawMain;
import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import player.Player;
import map.Map;
import map.Room;
import player.Camera;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Main {

	public static void main(String[] args) {
		new Main().run();
	}

	private long window;
	private Player player;
	private Map map;
	private Camera camera;
	private float screenWidth;
	private float screenHeight;

	public void run() {
		System.out.println("Hello LWJGL " + Version.getVersion() + "!");

		init();
		loop();

		glfwFreeCallbacks(window);
		glfwDestroyWindow(window);
		glfwTerminate();
		glfwSetErrorCallback(null).free();
	}

	private void init() {
		GLFWErrorCallback.createPrint(System.err).set();

		if (!glfwInit())
			throw new IllegalStateException("Unable to initialize GLFW");

		glfwDefaultWindowHints();
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
		glfwWindowHint(GLFW_DECORATED, GLFW_FALSE);

		GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

		screenWidth = vidmode.width();
		screenHeight = vidmode.height();

		window = glfwCreateWindow((int) screenWidth, (int) screenHeight, "Hello World!", NULL, NULL);
		if (window == NULL)
			throw new RuntimeException("Failed to create the GLFW window");

		player = new Player();
		player.init(700, 700);

		map = new Map();
		Room room1 = new Room(0, 0, 300, 300, 200, 200);
		Room room2 = new Room(1, 0, 600, 600, 5500, 500);
		map.addRoom(room1);
		map.addRoom(room2);

		camera = new Camera(player);

		glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
			if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
				glfwSetWindowShouldClose(window, true);
		});

		glfwSetWindowPos(window, 0, 0);

		glfwMakeContextCurrent(window);
		glfwSwapInterval(1);
		glfwShowWindow(window);
	}

	private void loop() {
		GL.createCapabilities();

		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, screenWidth, screenHeight, 0, -1, 1);
		glMatrixMode(GL_MODELVIEW);

		while (!glfwWindowShouldClose(window)) {
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

			player.update(window, map);
			camera.update(map, screenHeight, screenWidth);
			DrawMain.drawAll(player, map, screenWidth, screenHeight, camera);

			glfwSwapBuffers(window);
			glfwPollEvents();
		}
	}
}
