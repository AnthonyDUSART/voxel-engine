package voxel.gui;

import org.lwjgl.glfw.GLFWImage;

public class Cursor {
	
	private long context;
	private double x;
	private double y;
	private GLFWImage image;
	
	public Cursor(long context, double x, double y, GLFWImage image) {
		this.context = context;
		this.x = x;
		this.y = y;
		this.image = image;
	}
	
	public long getContext() {
		return this.context;
	}
	
	public void setContext(long context) {
		this.context = context;
	}
	
	public double getX() {
		return this.x;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public double getY() {
		return this.y;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public GLFWImage getImage() {
		return this.image;
	}
	
	public void setImage(GLFWImage image) {
		this.image = image;
	}
}