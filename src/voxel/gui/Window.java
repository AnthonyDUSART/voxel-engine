package voxel.gui;

import org.lwjgl.glfw.GLFW;

public class Window {
	
	private String title;
	private int width;
	private int height;
	private int isResizable;
	private int isFullscreen;
	private long context;
	private boolean isPaused;
	private Cursor cursor;
	
	public Window(String title, int width, int height, int isResizable, int isFullscreen) {
		this.title = title;
		this.width = width;
		this.height = height;
		this.isResizable = isResizable;
		this.isFullscreen = isFullscreen;
		this.isPaused = false;
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
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public void setHeight(int height) {
		this.height = height;
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
	
	public boolean getIsPaused() {
		return this.isPaused;
	}
	
	public void setIsPaused(boolean isPaused) {
		this.isPaused = isPaused;
	}
	
	public Cursor getCursor() {
		return this.cursor;
	}
	
	public void setCursor(Cursor cursor) {
		this.cursor = cursor;
	}
}
