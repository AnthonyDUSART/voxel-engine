package voxel.manager;

import voxel.entity.Entity;
import voxel.game.Game;
import voxel.manager.engine.RendererManager;

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
	}
	
	public static void loop(Game game) {
		GL.createCapabilities();
		glClearColor(0, 0, 0, 1);
		game.setRenderer(RendererManager.create());
		float[] triangle = {
		   -0.5f, -0.5f, 0.0f,
		   0.5f, -0.5f, 0.0f,
		   0.0f,  0.5f, 0.0f,
		};
		
		Entity entity = new Entity(triangle);
		
		RendererManager.enable(game.getRenderer(), entity);
		WindowManager.init();
		while (!game.getWindow().isCloseRequested()) {
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			
			entity.increaseRotation(0.2f, 1, 0);
			// render entity
			RendererManager.render(game.getRenderer(), entity);
			
			glUseProgram(game.getRenderer().getShader().getProgramId());
			
			glfwSwapBuffers(game.getWindow().getContext());

			glfwPollEvents();
		}
		RendererManager.disable(game.getRenderer());
	}
	
}
