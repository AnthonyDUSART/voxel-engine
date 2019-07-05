package voxel.manager.gui;

import static org.lwjgl.glfw.GLFW.*;

import java.nio.ByteBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWImage;

import voxel.gui.Cursor;
import voxel.main.Main;

import static org.lwjgl.system.MemoryUtil.NULL;

public abstract class CursorManager {
	
	public static void setPosition(Cursor cursor, double x, double y) {
		cursor.setX(x);
		cursor.setY(y);
		glfwSetCursorPos(WindowManager.getCurrentContext(), x, y);
	}
	
	public static Cursor create() {
		int size = 8;
		byte[] pixels = new byte[size * size * 4];
		
		for(int i = 0; i < pixels.length; i++) {
			pixels[i] = (byte)0xff;
		}
		
		ByteBuffer container = BufferUtils.createByteBuffer(pixels.length);
		//container.put(pixels, 0, pixels.length);
		container.put(pixels);
		container.flip();
		GLFWImage image = GLFWImage.malloc();
		image.set(size, size, container);
		long context = glfwCreateCursor(image, 0, 0);
		
		if(context == NULL)
			throw new RuntimeException("Une erreur est survenu pendant la création du curseur");
		
		return new Cursor(context, 0,0, image);
	}
	
	public static void destroy(Cursor cursor) {
		glfwDestroyCursor(cursor.getContext());
	}
	
	public static GLFWCursorPosCallback cursorPosEvent(Cursor cursor) {
		return new GLFWCursorPosCallback() {
			
			@Override
			public void invoke(long arg0, double arg1, double arg2) {
				if(!Main.getGame().getWindow().getIsPaused()) {
					if(cursor.getX() != arg1)
						cursor.setX((float)arg1);
					if(cursor.getY() != arg2)
						cursor.setY((float)arg2);
				}
			}
		};
	}

}
