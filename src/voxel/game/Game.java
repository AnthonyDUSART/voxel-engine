package voxel.game;

import org.lwjgl.glfw.GLFW;

import voxel.engine.renderer.Renderer;
import voxel.gui.Window;

public class Game {
	
	private Window window;
	private Renderer renderer;

	public Game() {
		this.window = new Window("Voxel Engine", 1600, 900, GLFW.GLFW_TRUE, GLFW.GLFW_FALSE);
		this.renderer = new Renderer();
	}
	
	public Window getWindow() {
		return this.window;
	}
	
	public Renderer getRenderer() {
		return this.renderer;
	}
}
