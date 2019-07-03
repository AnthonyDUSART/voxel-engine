package voxel.manager;

import voxel.engine.render.Renderer;
import voxel.game.Game;
import org.lwjgl.opengl.*;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.glUseProgram;

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
		game.setRenderer(new Renderer(ShaderManager.getStaticShader("static")));
		float[] triangle = {
		   -0.5f, -0.5f, 0.0f,
		   0.5f, -0.5f, 0.0f,
		   0.0f,  0.5f, 0.0f,
		};
		
		RendererManager.enable(game.getRenderer(), triangle);
		WindowManager.init();
		while (!game.getWindow().isCloseRequested()) {
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			
			RendererManager.render(game.getRenderer());
			
			glUseProgram(game.getRenderer().getShader().getProgramId());
			
			glfwSwapBuffers(game.getWindow().getContext());

			glfwPollEvents();
		}
		RendererManager.disable(game.getRenderer());
	}
	
}
