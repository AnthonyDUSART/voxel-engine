package voxel.engine.render.model;

public class Obj {
	
	private String name;
	private float[] vertices;
	private int[] indices;
	private float[] normals;
	private float[] uvs;
	
	public Obj(String name, float[] vertices, int[] indices, float[] normals, float[] uvs) {
		this.name = name;
		this.vertices = vertices;
		this.indices = indices;
		this.normals = normals;
		this.uvs = uvs;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public float[] getVertices() {
		return this.vertices;
	}
	
	public void setVertices(float[] vertices) {
		this.vertices = vertices;
	}
	
	public int[] getIndices() {
		return this.indices;
	}
	
	public void setIndices(int[] indices) {
		this.indices = indices;
	}
	
	public float[] getNormals() {
		return this.normals;
	}
	
	public void setNormals(float[] normals) {
		this.normals = normals;
	}
	
	public float[] getUvs() {
		return this.uvs;
	}
	
	public void setUvs(float[] uvs) {
		this.uvs = uvs;
	}

}
