package voxel.engine.render;

import voxel.engine.render.shader.Shader;

public class Renderer {
	
	private int vbo;
	private Shader shader;
	private Camera camera;
	
	public Renderer(Shader shader, Camera camera) {
		this.shader = shader;
		this.camera = camera;
	}
	
	public int getVbo() {
		return this.vbo;
	}
	
	public void setVbo(int vbo) {
		this.vbo = vbo;
	}
	
	public Shader getShader() {
		return this.shader;
	}
	
	public void setShader(Shader shader) {
		this.shader = shader;
	}
	
	public Camera getCamera() {
		return this.camera;
	}
	
	public void setCamera(Camera camera) {
		this.camera = camera;
	}
}
