package voxel.engine.render;

import voxel.engine.render.shader.Shader;

public class Renderer {
	
	private int vbo;
	private int cbo;
	
	private Shader shader;
	private Camera camera;
	private Loader loader;
	
	public Renderer(Shader shader, Camera camera, Loader loader) {
		this.shader = shader;
		this.camera = camera;
		this.loader = loader;
	}
	
	public int getVbo() {
		return this.vbo;
	}
	
	public void setVbo(int vbo) {
		this.vbo = vbo;
	}
	
	public int getCbo() {
		return this.cbo;
	}
	
	public void setCbo(int cbo) {
		this.cbo = cbo;
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
	
	public Loader getLoader() {
		return this.loader;
	}
	
	public void setLoader(Loader loader) {
		this.loader = loader;
	}
}
