package voxel.engine.render;

import voxel.engine.render.shader.Shader;

public class Renderer {
	
	private int vbo;
	private Shader shader;
	
	public Renderer(Shader shader) {
		this.shader = shader;
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
}
