package voxel.manager.engine;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_A;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_D;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_S;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_W;
import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.glfwGetKey;

import java.nio.IntBuffer;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;

import voxel.engine.render.Camera;
import voxel.gui.Window;
import voxel.main.Main;
import voxel.manager.gui.CursorManager;

public abstract class CameraManager {

	public static Matrix4f createPerspectiveProjection(Camera camera) {
		IntBuffer width = BufferUtils.createIntBuffer(4);
		IntBuffer height = BufferUtils.createIntBuffer(4);
		GLFW.glfwGetWindowSize(Main.getGame().getWindow().getContext(), width, height);
		return new Matrix4f().perspective((float)Math.toRadians(camera.getFov()),(float) width.get()/height.get(), camera.getZNear(), camera.getZFar());
	}
	
	public static Matrix4f createView(Camera camera) {
		
		Matrix4f viewMatrix = new Matrix4f();
		viewMatrix.identity();
		viewMatrix.rotate((float) java.lang.Math.toRadians(camera.getYaw()), new Vector3f(1, 0, 0), viewMatrix);
		viewMatrix.rotate((float) java.lang.Math.toRadians(camera.getPitch()), new Vector3f(0, 1, 0), viewMatrix);
		viewMatrix.rotate((float) java.lang.Math.toRadians(0), new Vector3f(0, 0, 1), viewMatrix);
		Vector3f cameraPos = camera.getPosition();
		Vector3f negativeCameraPos = new Vector3f(-cameraPos.x, -cameraPos.y, -cameraPos.z);
		viewMatrix.translate(negativeCameraPos, viewMatrix);
		return viewMatrix;
	}
	
	public static void input(Camera camera) {
		Window window = Main.getGame().getWindow();
		long context = window.getContext();
		
		
		if(!window.getIsPaused()) {
			// camera direction
			if(glfwGetKey(context, GLFW_KEY_W) == GLFW_PRESS && glfwGetKey(context, GLFW_KEY_S) != GLFW_PRESS)
				camera.moveForward();
			if(glfwGetKey(context, GLFW_KEY_S) == GLFW_PRESS && glfwGetKey(context, GLFW_KEY_W) != GLFW_PRESS)
				camera.moveBackward();
			if(glfwGetKey(context, GLFW_KEY_A) == GLFW_PRESS)
				camera.moveLeft();
			if(glfwGetKey(context, GLFW_KEY_D) == GLFW_PRESS)
				camera.moveRight();
			
			// camera orientation
			float w = window.getWidth() / 2;
			float h = window.getHeight() / 2;
			
			camera.increaseOrientation(
					(float)(w - window.getCursor().getX()),
					(float)(h - window.getCursor().getY())
			);
			CursorManager.setPosition(window.getCursor(), w, h);
		}
	}
	
}
