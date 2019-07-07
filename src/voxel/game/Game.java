package voxel.game;

import org.lwjgl.glfw.GLFW;

import voxel.engine.render.Renderer;
import voxel.gui.Window;

public class Game {
	
	private Window window;
	private Renderer renderer;

	public Game() {
		this.window = new Window("Voxel Engine", 800, 800, GLFW.GLFW_TRUE, GLFW.GLFW_FALSE);
	}
	
	public Window getWindow() {
		return this.window;
	}
	
	public Renderer getRenderer() {
		return this.renderer;
	}
	
	public void setRenderer(Renderer renderer) {
		this.renderer = renderer;
	}
}
