package voxel.gui;

import org.lwjgl.glfw.GLFW;

public class Window {
	
	private String title;
	private int width;
	private int height;
	private int isResizable;
	private int isFullscreen;
	private long context;
	
	public Window(String title, int width, int height, int isResizable, int isFullscreen) {
		this.title = title;
		this.width = width;
		this.height = height;
		this.isResizable = isResizable;
		this.isFullscreen = isFullscreen;
	}
	
	public boolean isCloseRequested() {
		return GLFW.glfwWindowShouldClose(this.context);
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public int getIsResizable() {
		return this.isResizable;
	}
	
	public int getIsFullscreen() {
		return this.isFullscreen;
	}
	
	public long getContext() {
		return this.context;
	}
	
	public void setContext(long context) {
		this.context = context;
	}
	
}
