package main;

import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.nio.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

<<<<<<< Updated upstream
public class Main {
=======
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
		this.player.update(window.getWindow(), map);
		this.camera.update(map, screenHeight, screenWidth);
		this.window.update();
	}
>>>>>>> Stashed changes
	
	public static void main(String[] args) {
		new Main().run();
	}
	
	// The window handle
	private long window;

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
		// Setup an error callback. The default implementation
		// will print the error message in System.err.
		GLFWErrorCallback.createPrint(System.err).set();

		// Initialize GLFW. Most GLFW functions will not work before doing this.
		if ( !glfwInit() )
			throw new IllegalStateException("Unable to initialize GLFW");

		// Configure GLFW
		glfwDefaultWindowHints(); // optional, the current window hints are already the default
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be resizable

		// Create the window
		window = glfwCreateWindow(300, 300, "Hello World!", NULL, NULL);
		if ( window == NULL )
			throw new RuntimeException("Failed to create the GLFW window");

		// Setup a key callback. It will be called every time a key is pressed, repeated or released.
		glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
			if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
				glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop
		});

		// Get the thread stack and push a new frame
		try ( MemoryStack stack = stackPush() ) {
			IntBuffer pWidth = stack.mallocInt(1); // int*
			IntBuffer pHeight = stack.mallocInt(1); // int*

			// Get the window size passed to glfwCreateWindow
			glfwGetWindowSize(window, pWidth, pHeight);

			// Get the resolution of the primary monitor
			GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

			// Center the window
			glfwSetWindowPos(
				window,
				(vidmode.width() - pWidth.get(0)) / 2,
				(vidmode.height() - pHeight.get(0)) / 2
			);
		} // the stack frame is popped automatically

		// Make the OpenGL context current
		glfwMakeContextCurrent(window);
		// Enable v-sync
		glfwSwapInterval(1);

		// Make the window visible
		glfwShowWindow(window);
	}

	private void loop() {
		// This line is critical for LWJGL's interoperation with GLFW's
		// OpenGL context, or any context that is managed externally.
		// LWJGL detects the context that is current in the current thread,
		// creates the GLCapabilities instance and makes the OpenGL
		// bindings available for use.
		GL.createCapabilities();

		// Set the clear color
		glClearColor(1.0f, 0.0f, 0.0f, 0.0f);

		// Run the rendering loop until the user has attempted to close
		// the window or has pressed the ESCAPE key.
		while ( !glfwWindowShouldClose(window) ) {
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

			glfwSwapBuffers(window); // swap the color buffers

			// Poll for window events. The key callback above will only be
			// invoked during this call.
			glfwPollEvents();
		}
	}

}