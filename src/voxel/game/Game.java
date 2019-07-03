package voxel.game;

import org.lwjgl.glfw.GLFW;

import voxel.gui.Window;

public class Game {
	
	private Window window;

	public Game() {
		this.window = new Window("Voxel Engine", 300, 300, GLFW.GLFW_TRUE, GLFW.GLFW_FALSE);
	}
	
	public Window getWindow() {
		return this.window;
	}
}
