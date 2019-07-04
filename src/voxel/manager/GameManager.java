package voxel.manager;

import voxel.entity.Entity;
import voxel.game.Game;
import voxel.main.Main;
import voxel.manager.engine.CameraManager;
import voxel.manager.engine.RendererManager;

import org.lwjgl.glfw.GLFWKeyCallbackI;
import org.lwjgl.opengl.*;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.glUseProgram;

import org.joml.Vector3f;

public abstract class GameManager {
	
	public static void init(Game game) {
		try {
			WindowManager.create(game.getWindow());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		GL.createCapabilities();
		glClearColor(0, 0, 0, 1);
		game.setRenderer(RendererManager.create());
		GameManager.input(game);
	}
	
	public static void input(Game game) {
		glfwSetKeyCallback(game.getWindow().getContext(), WindowManager.input());
		glfwSetCursorPosCallback(game.getWindow().getContext(), CameraManager.cameraTarget(game.getRenderer().getCamera()));
	}
	
	public static void loop(Game game) {
		
		float[] triangle = {
				-1.0f,-1.0f,-1.0f, // triangle 1 : begin
			    -1.0f,-1.0f, 1.0f,
			    -1.0f, 1.0f, 1.0f, // triangle 1 : end
			    1.0f, 1.0f,-1.0f, // triangle 2 : begin
			    -1.0f,-1.0f,-1.0f,
			    -1.0f, 1.0f,-1.0f, // triangle 2 : end
			    1.0f,-1.0f, 1.0f,
			    -1.0f,-1.0f,-1.0f,
			    1.0f,-1.0f,-1.0f,
			    1.0f, 1.0f,-1.0f,
			    1.0f,-1.0f,-1.0f,
			    -1.0f,-1.0f,-1.0f,
			    -1.0f,-1.0f,-1.0f,
			    -1.0f, 1.0f, 1.0f,
			    -1.0f, 1.0f,-1.0f,
			    1.0f,-1.0f, 1.0f,
			    -1.0f,-1.0f, 1.0f,
			    -1.0f,-1.0f,-1.0f,
			    -1.0f, 1.0f, 1.0f,
			    -1.0f,-1.0f, 1.0f,
			    1.0f,-1.0f, 1.0f,
			    1.0f, 1.0f, 1.0f,
			    1.0f,-1.0f,-1.0f,
			    1.0f, 1.0f,-1.0f,
			    1.0f,-1.0f,-1.0f,
			    1.0f, 1.0f, 1.0f,
			    1.0f,-1.0f, 1.0f,
			    1.0f, 1.0f, 1.0f,
			    1.0f, 1.0f,-1.0f,
			    -1.0f, 1.0f,-1.0f,
			    1.0f, 1.0f, 1.0f,
			    -1.0f, 1.0f,-1.0f,
			    -1.0f, 1.0f, 1.0f,
			    1.0f, 1.0f, 1.0f,
			    -1.0f, 1.0f, 1.0f,
			    1.0f,-1.0f, 1.0f
			};
		
		Entity entity = new Entity(triangle);
		
		
		RendererManager.enable(game.getRenderer(), entity);
		WindowManager.init();
		glLoadIdentity();
		while (!game.getWindow().isCloseRequested()) {
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			
			
			entity.increaseRotation(0, 1, 0);
			// render entity
			RendererManager.render(game.getRenderer(), entity);
			
			glfwSwapBuffers(game.getWindow().getContext());

			glfwPollEvents();
		}
		RendererManager.disable(game.getRenderer());
	}
	
}
