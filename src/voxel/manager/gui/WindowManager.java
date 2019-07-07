package voxel.manager.gui;

import voxel.engine.render.shader.StaticShader;
import voxel.gui.Cursor;
import voxel.gui.Window;
import voxel.main.Main;
import voxel.manager.engine.CameraManager;
import voxel.manager.engine.ShaderManager;

import org.lwjgl.glfw.*;
import java.nio.IntBuffer;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
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
			//glfwSetWindowAspectRatio(window.getContext(), pWidth.get(), pHeight.get());

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
		glfwSetFramebufferSizeCallback(context, WindowManager.onResize(window));
		
		glfwShowWindow(context);
		
		glfwSetCursorPos(context, window.getWidth() / 2, window.getHeight() / 2);
		glfwSetInputMode(context, GLFW_CURSOR, GLFW_CURSOR_DISABLED);
		
		return context;
	}
	
	public static void update(Window window) {
		glfwSwapBuffers(window.getContext());
        glfwPollEvents();
	}
	
	public static GLFWFramebufferSizeCallback onResize(Window window) {
		return new GLFWFramebufferSizeCallback() {
			
			@Override
			public void invoke(long arg0, int arg1, int arg2) {
				GL11.glViewport(0, 0, arg1, arg2);
				//glfwSetWindowAspectRatio(arg0, arg1, arg2);
				System.out.println("update projection");
				window.setWidth(arg1);
				window.setHeight(arg2);
				GL20.glUseProgram(Main.getGame().getRenderer().getShader().getProgramId());
				ShaderManager.loadPerspectiveProjection(
						(StaticShader) Main.getGame().getRenderer().getShader(),
						CameraManager.createPerspectiveProjection(Main.getGame().getRenderer().getCamera())
					);
				GL20.glUseProgram(0);
			}
		};
	}
	
	public static void init() {
		GL11.glClearColor(0, 0, 0.2f, 0);
	}
	
	public static void destroy(Window window) {
		CursorManager.destroy(window.getCursor());
		glfwDestroyWindow(window.getContext());
		glfwTerminate();
	}
	
	public static long getCurrentContext() {
		return glfwGetCurrentContext();
	}
	
}
