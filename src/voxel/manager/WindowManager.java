package voxel.manager;

import voxel.gui.Window;
import org.lwjgl.glfw.*;
import java.nio.IntBuffer;
import org.lwjgl.glfw.GLFWErrorCallback;
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

			GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

			glfwSetWindowPos(
				context,
				(vidmode.width() - pWidth.get(0)) / 2,
				(vidmode.height() - pHeight.get(0)) / 2
			);
		}

		glfwMakeContextCurrent(context);
		glfwSwapInterval(1);

		glfwShowWindow(context);
		
		return window.getContext();
	}
	
	public static void destroy(Window window) {
		glfwDestroyWindow(window.getContext());
	}
	
}
