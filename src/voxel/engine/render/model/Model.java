package voxel.engine.render.model;

public class Model {
	
	private int vao;
	private int vertexCount;
	
	public Model() {
		this(0, 0);
	}
	
	public Model(int vao, int vertexCount) {
		this.vao = vao;
		this.vertexCount = vertexCount;
	}
	
	public int getVao() {
		return this.vao;
	}
	
	public void setVao(int vao) {
		this.vao = vao;
	}
	
	public int getVertexCount() {
		return this.vertexCount;
	}
	
	public void setVertexCount(int vertexCount) {
		this.vertexCount = vertexCount;
	}

}
