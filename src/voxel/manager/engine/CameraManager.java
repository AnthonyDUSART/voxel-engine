package voxel.manager.engine;

import java.nio.IntBuffer;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWKeyCallback;

import voxel.engine.render.Camera;
import voxel.main.Main;

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
	
	public static GLFWCursorPosCallback cameraOrientation(Camera camera) {
		return new GLFWCursorPosCallback() {
			
			@Override
			public void invoke(long arg0, double arg1, double arg2) {
				if(!Main.getGame().getWindow().getIsPaused()) {
					float w = Main.getGame().getWindow().getWidth()/2;
					float h = Main.getGame().getWindow().getHeight()/2;
					camera.increaseOrientation(
							(float)(w - arg1), 
							(float)(h - arg2)
					);
					GLFW.glfwSetCursorPos(
							Main.getGame().getWindow().getContext(), 
							w, 
							h
					);
				}
			}
		};
	}
	
}
