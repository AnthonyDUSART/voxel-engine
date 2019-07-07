package voxel.manager.engine;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_A;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_D;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_S;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_W;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_SPACE;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_LEFT_SHIFT;
import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.glfwGetKey;

import java.nio.IntBuffer;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;

import voxel.engine.render.Camera;
import voxel.gui.Window;
import voxel.main.Main;

public abstract class CameraManager {
	
	private static Vector2f lastCursorPosition = Main.getGame().getWindow().getCursor().getPosition();

	public static Matrix4f createPerspectiveProjection(Camera camera) {
		IntBuffer width = BufferUtils.createIntBuffer(4);
		IntBuffer height = BufferUtils.createIntBuffer(4);
		GLFW.glfwGetWindowSize(Main.getGame().getWindow().getContext(), width, height);
		return new Matrix4f().perspective((float)Math.toRadians(camera.getFov()),(float) Main.getGame().getWindow().getWidth()/Main.getGame().getWindow().getHeight(), camera.getZNear(), camera.getZFar());
	}
	
	public static Matrix4f createFpsView(Camera camera) {
		Matrix4f m = new Matrix4f();
		float pitch = (float)Math.toRadians(camera.getPitch());
		float yaw = (float)Math.toRadians(camera.getYaw());
		float cosPitch = (float)Math.cos(pitch);
		float sinPitch = (float)Math.sin(pitch);
		float cosYaw = (float)Math.cos(yaw);
		float sinYaw = (float)Math.sin(yaw);
		
		Vector3f xAxis = new Vector3f(cosYaw, 0, -sinYaw);
		Vector3f yAxis = new Vector3f(sinYaw * sinPitch, cosPitch, cosYaw * sinPitch);
		Vector3f zAxis = new Vector3f(sinYaw * cosPitch, -sinPitch, cosPitch * cosYaw);
		
		m._m00(xAxis.x);
		m._m01(yAxis.x);
		m._m02(zAxis.x);
		m._m03(0);
		
		m._m10(xAxis.y);
		m._m11(yAxis.y);
		m._m12(zAxis.y);
		m._m13(0);
		
		m._m20(xAxis.z);
		m._m21(yAxis.z);
		m._m22(zAxis.z);
		m._m23(0);
		
		m._m30(-xAxis.dot(camera.getPosition()));
		m._m31(-yAxis.dot(camera.getPosition()));
		m._m32(-zAxis.dot(camera.getPosition()));
		m._m33(1);
		
		return m;
	}
	
	public static void input(Camera camera) {
		Window window = Main.getGame().getWindow();
		long context = window.getContext();
		
		
		if(!window.getIsPaused()) {
			// camera direction
			if(glfwGetKey(context, GLFW_KEY_W) == GLFW_PRESS)
				camera.moveForward();
			if(glfwGetKey(context, GLFW_KEY_S) == GLFW_PRESS)
				camera.moveBackward();
			if(glfwGetKey(context, GLFW_KEY_A) == GLFW_PRESS)
				camera.moveLeft();
			if(glfwGetKey(context, GLFW_KEY_D) == GLFW_PRESS)
				camera.moveRight();
			if(glfwGetKey(context, GLFW_KEY_SPACE) == GLFW_PRESS)
				camera.moveUp();
			if(glfwGetKey(context, GLFW_KEY_LEFT_SHIFT) == GLFW_PRESS)
				camera.moveDown();
			
			float x = (float)window.getCursor().getX();
			float y = (float)window.getCursor().getY();
			
			if(CameraManager.lastCursorPosition.x != x || CameraManager.lastCursorPosition.y != y) {
				camera.increaseOrientation(
						(float)(CameraManager.lastCursorPosition.x - x),
						(float)(CameraManager.lastCursorPosition.y - y)
				);
				CameraManager.lastCursorPosition.set(x, y);
			}
		}
	}
	
}
