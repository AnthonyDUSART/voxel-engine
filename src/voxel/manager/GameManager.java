package voxel.manager;

import voxel.entity.Entity;
import voxel.game.Game;
import voxel.gui.Cursor;
import voxel.manager.engine.CameraManager;
import voxel.manager.engine.RendererManager;
import voxel.manager.gui.CursorManager;
import voxel.manager.gui.WindowManager;

import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.opengl.*;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

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
		glfwSetKeyCallback(game.getWindow().getContext(), GameManager.keyInputs(game));
		glfwSetCursorPosCallback(game.getWindow().getContext(), CursorManager.cursorPosEvent(game.getWindow().getCursor()));
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
		
		float[] color = {
				.583f,  0.771f,  0.014f,
			    0.609f,  0.115f,  0.436f,
			    0.327f,  0.483f,  0.844f,
			    0.822f,  0.569f,  0.201f,
			    0.435f,  0.602f,  0.223f,
			    0.310f,  0.747f,  0.185f,
			    0.597f,  0.770f,  0.761f,
			    0.559f,  0.436f,  0.730f,
			    0.359f,  0.583f,  0.152f,
			    0.483f,  0.596f,  0.789f,
			    0.559f,  0.861f,  0.639f,
			    0.195f,  0.548f,  0.859f,
			    0.014f,  0.184f,  0.576f,
			    0.771f,  0.328f,  0.970f,
			    0.406f,  0.615f,  0.116f,
			    0.676f,  0.977f,  0.133f,
			    0.971f,  0.572f,  0.833f,
			    0.140f,  0.616f,  0.489f,
			    0.997f,  0.513f,  0.064f,
			    0.945f,  0.719f,  0.592f,
			    0.543f,  0.021f,  0.978f,
			    0.279f,  0.317f,  0.505f,
			    0.167f,  0.620f,  0.077f,
			    0.347f,  0.857f,  0.137f,
			    0.055f,  0.953f,  0.042f,
			    0.714f,  0.505f,  0.345f,
			    0.783f,  0.290f,  0.734f,
			    0.722f,  0.645f,  0.174f,
			    0.302f,  0.455f,  0.848f,
			    0.225f,  0.587f,  0.040f,
			    0.517f,  0.713f,  0.338f,
			    0.053f,  0.959f,  0.120f,
			    0.393f,  0.621f,  0.362f,
			    0.673f,  0.211f,  0.457f,
			    0.820f,  0.883f,  0.371f,
			    0.982f,  0.099f,  0.879f
			};
		
		Entity entity = new Entity(triangle, color);
		entity.setPosition(new Vector3f(0, 0, 0));
		
		
		RendererManager.enable(game.getRenderer(), entity);
		WindowManager.init();
		glLoadIdentity();
		GameManager.input(game);
		while (!game.getWindow().isCloseRequested()) {
				glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
				
				CameraManager.input(game.getRenderer().getCamera());
				entity.increaseRotation(0, 1, 0);
				
				RendererManager.render(game.getRenderer(), entity);
				
				WindowManager.update(game.getWindow());
		}
		RendererManager.disable(game.getRenderer());
	}
	
	public static GLFWKeyCallback keyInputs(Game game) {
		return new GLFWKeyCallback() {
			
			@Override
			public void invoke(long arg0, int arg1, int arg2, int arg3, int arg4) {
				if(arg1 == GLFW_KEY_ESCAPE && arg3 == GLFW_RELEASE) {
					System.out.println("key callback " + arg0 + " " + GLFW_CURSOR + " " + GLFW_CURSOR_DISABLED);
					if(glfwGetInputMode(arg0, GLFW_CURSOR) == GLFW_CURSOR_DISABLED) {
						game.getWindow().setIsPaused(true);
						glfwSetInputMode(arg0, GLFW_CURSOR, GLFW_CURSOR_NORMAL);
						glfwSetCursorPos(arg0, game.getWindow().getWidth() / 2, game.getWindow().getHeight() / 2);
					}
					else {
						game.getWindow().setIsPaused(false);
						glfwSetInputMode(arg0, GLFW_CURSOR, GLFW_CURSOR_DISABLED);
						Cursor cursor = game.getWindow().getCursor();
						glfwSetCursorPos(arg0, cursor.getX(), cursor.getY());
					}
				}
			}
		};
	}
	
}
