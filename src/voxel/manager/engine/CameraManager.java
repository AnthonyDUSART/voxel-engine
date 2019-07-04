package voxel.manager.engine;

import java.nio.IntBuffer;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCursorPosCallback;

import voxel.engine.render.Camera;
import voxel.main.Main;

public abstract class CameraManager {

	public static Matrix4f createPerspectiveProjection(Camera camera) {
		IntBuffer width = BufferUtils.createIntBuffer(4);
		IntBuffer height = BufferUtils.createIntBuffer(4);
		GLFW.glfwGetWindowSize(Main.getGame().getWindow().getContext(), width, height);
		return new Matrix4f().perspective(camera.getFov(),(float) width.get()/height.get(), camera.getZNear(), camera.getZFar());
	}
	
	public static Matrix4f createView(Camera camera) {
		return new Matrix4f().lookAt(
				camera.getPosition(), 
				camera.getTarget(), 
				new Vector3f(0, 1, 0)
				);
	}
	
	public static GLFWCursorPosCallback cameraTarget(Camera camera) {
		return new GLFWCursorPosCallback() {
			
			@Override
			public void invoke(long arg0, double arg1, double arg2) {
				camera.setTarget(
						new Vector3f(
								(float)Math.sin(arg1 * Camera.getTargetSpeed()), 
								-(float)Math.cos(arg1 * Camera.getTargetSpeed()), 
								0)
						);
				System.out.println(camera.getTarget());
			}
		};
	}
	
}
