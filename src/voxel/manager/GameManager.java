package voxel.manager;

import voxel.game.Game;
import org.lwjgl.opengl.*;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public abstract class GameManager {
	
	public static void init(Game game) {
		try {
			WindowManager.create(game.getWindow());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void loop(Game game) {
		GL.createCapabilities();
		glClearColor(0, 0, 0, 1);
		
		while (!game.getWindow().isCloseRequested()) {
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

			glfwSwapBuffers(game.getWindow().getContext());

			glfwPollEvents();
		}
	}
	
}
