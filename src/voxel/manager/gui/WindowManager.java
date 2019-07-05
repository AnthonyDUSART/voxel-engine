package voxel.manager.gui;

import voxel.gui.Cursor;
import voxel.gui.Window;
import org.lwjgl.glfw.*;
import java.nio.IntBuffer;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryStack;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.*;
import static org.lwjgl.system.MemoryStack.*;


public abstract class WindowManager {

	public static long create(Window window) throws Exception {
		GLFWErrorCallback.createPrint(System.err).set();

		if ( !glfwInit() )
			throw new IllegalStateException("Unable to initialize GLFW");
		
		glfwDefaultWindowHints();
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		glfwWindowHint(GLFW_RESIZABLE, window.getIsResizable());
		long context = glfwCreateWindow(window.getWidth(), window.getHeight(), window.getTitle(), NULL, NULL);
		if(context == NULL)
			throw new RuntimeException("Une erreur est survenu pendant la création de la fenetre");
		else
			window.setContext(context);
		
		try ( MemoryStack stack = stackPush() ) {
			IntBuffer pWidth = stack.mallocInt(1);
			IntBuffer pHeight = stack.mallocInt(1);

			glfwGetWindowSize(context, pWidth, pHeight);
//			glfwSetWindowAspectRatio(window.getContext(), 1200, 900);

			GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

			glfwSetWindowPos(
				context,
				(vidmode.width() - pWidth.get(0)) / 2,
				(vidmode.height() - pHeight.get(0)) / 2
			);
			
		}

		glfwMakeContextCurrent(context);
		glfwSwapInterval(1);
		
		// cursor
		Cursor cursor = CursorManager.create();
		window.setCursor(cursor);
		glfwSetCursor(context, window.getCursor().getContext());
		
		
		// inputs
		
		glfwSetFramebufferSizeCallback(context, WindowManager.onResize());
		
		glfwShowWindow(context);
		
		//glfwSetCursorPos(context, window.getWidth()/2, window.getHeight()/2);
		CursorManager.setPosition(cursor, window.getWidth() / 2, window.getHeight() / 2);
		glfwSetInputMode(context, GLFW_CURSOR, GLFW_CURSOR_DISABLED);
		
		return context;
	}
	
	public static GLFWFramebufferSizeCallback onResize() {
		return new GLFWFramebufferSizeCallback() {
			
			@Override
			public void invoke(long arg0, int arg1, int arg2) {
				GL11.glViewport(0, 0, arg1, arg2);
			}
		};
	}
	
	public static void init() {
		GL11.glClearColor(0, 0, 0.2f, 0);
	}
	
	public static void destroy(Window window) {
		CursorManager.destroy(window.getCursor());
		glfwDestroyWindow(window.getContext());
	}
	
	public static long getCurrentContext() {
		return glfwGetCurrentContext();
	}
	
}
