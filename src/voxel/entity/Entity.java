package voxel.entity;

import org.joml.Vector3f;

public class Entity {
	
	private float[] vertices;
	private float[] color;
	private Vector3f position;
	private Vector3f rotation;
	
	public Entity(float[] vertices, float[] color) {
		this(vertices, color, new Vector3f(0, 0, 0), new Vector3f(0, 0, 0));
	}
	
	public Entity(float[] vertices, float[] color, Vector3f position, Vector3f rotation) {
		this.vertices = vertices;
		this.color = color;
		this.position = position;
		this.rotation = rotation;
	}
	
	public Vector3f increasePosition(float x, float y, float z) {
		return this.position.add(x, y, z);
	}
	
	public Vector3f increaseRotation(float rx, float ry, float rz) {
		return this.rotation.add(rx, ry, rz);
	}
	
	public float[] getVertices() {
		return this.vertices;
	}
	
	public void setVertices(float[] vertices) {
		this.vertices = vertices;
	}
	
	public float[] getColor() {
		return this.color;
	}
	
	public Vector3f getPosition() {
		return this.position;
	}
	
	public void setPosition(Vector3f position) {
		this.position = position;
	}
	
	public Vector3f getRotation() {
		return this.rotation;
	}
	
	public void setRotation(Vector3f rotation) {
		this.rotation = rotation;
	}

}
