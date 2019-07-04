package voxel.engine.render;

public class Camera {
	
	private float zNear;
	private float zFar;
	private float fov;
	
	public Camera(float zNear, float zFar) {
		this(zNear, zFar, 70);
	}
	
	public Camera(float zNear, float zFar, float fov) {
		this.zNear = zNear;
		this.zFar = zFar;
		this.fov = fov;
	}
	
	public float getZNear() {
		return this.zNear;
	}
	
	public void setZNear(float zNear) {
		this.zNear = zNear;
	}
	
	public float getZFar() {
		return this.zFar;
	}
	
	public void setZFar(float zFar) {
		this.zFar = zFar;
	}
	
	public float getFov() {
		return this.fov;
	}
	
	public void setFov(float fov) {
		this.fov = fov;
	}

}
