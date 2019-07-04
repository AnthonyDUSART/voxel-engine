package voxel.manager;

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
		
		
		// inputs
		
		glfwSetFramebufferSizeCallback(context, WindowManager.onResize());
		
		glfwShowWindow(context);
		
		glfwSetCursorPos(context, window.getWidth()/2, window.getHeight()/2);
		glfwSetInputMode(window.getContext(), GLFW_CURSOR, GLFW_CURSOR_DISABLED);
		
		return window.getContext();
	}
	
	public static GLFWFramebufferSizeCallback onResize() {
		return new GLFWFramebufferSizeCallback() {
			
			@Override
			public void invoke(long arg0, int arg1, int arg2) {
				GL11.glViewport(0, 0, arg1, arg2);
			}
		};
	}
	
	public static GLFWKeyCallback input(Window window) {
		
		
		return new GLFWKeyCallback() {
			
			@Override
			public void invoke(long arg0, int arg1, int arg2, int arg3, int arg4) {
				
				if(arg1 == GLFW_KEY_ESCAPE && arg3 == GLFW_RELEASE) {
					System.out.println("key callback " + arg0 + " " + GLFW_CURSOR + " " + GLFW_CURSOR_DISABLED);
					if(glfwGetInputMode(arg0, GLFW_CURSOR) == GLFW_CURSOR_DISABLED) {
						window.setIsPaused(true);
						glfwSetInputMode(arg0, GLFW_CURSOR, GLFW_CURSOR_NORMAL);
					}
					else {
						window.setIsPaused(false);
						glfwSetInputMode(arg0, GLFW_CURSOR, GLFW_CURSOR_DISABLED);
					}
				}
			}
		};
	}
	
	public static void init() {
		GL11.glClearColor(0, 0, 0.2f, 0);
	}
	
	public static void destroy(Window window) {
		glfwDestroyWindow(window.getContext());
	}
	
}
