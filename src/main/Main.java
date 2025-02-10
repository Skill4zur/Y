package main;

import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;
import player.Player;
import draw.DrawPlayer; // Ensure you have DrawPlayer class

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Main {

	public static void main(String[] args) {
		new Main().run();
	}

	// The window handle
	private long window;
	private Player player; // Declare player at class level

	public void run() {
		System.out.println("Hello LWJGL " + Version.getVersion() + "!");

		init();
		loop();

		// Free the window callbacks and destroy the window
		glfwFreeCallbacks(window);
		glfwDestroyWindow(window);

		// Terminate GLFW and free the error callback
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

		window = glfwCreateWindow(vidmode.width(), vidmode.height(),"Hello World!", NULL, NULL);
		if (window == NULL)
			throw new RuntimeException("Failed to create the GLFW window");

		player = new Player();  // Initialize player here
		player.init(100, 100);

		glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
			if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
				glfwSetWindowShouldClose(window, true);
		});

		// Set window position to (0,0) for fullscreen effect
		glfwSetWindowPos(
				window,
				(0),
				(0)
		);

		glfwMakeContextCurrent(window);
		glfwSwapInterval(1);
		glfwShowWindow(window);
	}

	private void loop() {
		GL.createCapabilities();

		// Set the clear color
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

		// Set up a basic orthogonal projection matrix for 2D rendering
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, glfwGetVideoMode(glfwGetPrimaryMonitor()).width(), glfwGetVideoMode(glfwGetPrimaryMonitor()).height(), 0, -1, 1);
		glMatrixMode(GL_MODELVIEW);

		// Run the rendering loop until the user has attempted to close
		// the window or has pressed the ESCAPE key.
		while (!glfwWindowShouldClose(window)) {
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

			player.update(window);  // Update player position based on input
			DrawPlayer.draw(player); // Draw the player

			// Draw the red square in the center of the window
			drawRedSquare();

			glfwSwapBuffers(window); // swap the color buffers
			glfwPollEvents();
		}
	}

	private void drawRedSquare() {

		// for a 100px * 100px square
		float x = glfwGetVideoMode(glfwGetPrimaryMonitor()).width() / 2 - 50;
		float y = glfwGetVideoMode(glfwGetPrimaryMonitor()).height() / 2 - 50;
	}

}