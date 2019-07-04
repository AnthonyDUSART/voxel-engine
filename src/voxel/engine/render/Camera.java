package voxel.engine.render;

import org.joml.Vector3f;

public class Camera {
	
	private Vector3f position;
	private Vector3f target;
	private float pitch;
	private float yaw;
	private float roll;
	private float zNear;
	private float zFar;
	private float fov;
	private static float targetSpeed;
	
	public Camera(float zNear, float zFar) {
		this(new Vector3f(4, 3, -3), new Vector3f(0, 0, 0), 0, 0, 0, zNear, zFar, 70);
	}
	
	public Camera(Vector3f position, Vector3f target, float pitch, float yaw, float roll, float zNear, float zFar, float fov) {
		this.position = position;
		this.target = target;
		this.pitch = pitch;
		this.yaw = yaw;
		this.roll = roll;
		this.zNear = zNear;
		this.zFar = zFar;
		this.fov = fov;
		Camera.targetSpeed = 0.005f;
	}
	
	public Vector3f increasePosition(float x, float y, float z) {
		return this.position.add(x, y, z);
	}
	
	public Vector3f increaseRotation(float x, float y, float z) {
		return this.target.add(x, y, z);
	}
	
	public Vector3f getPosition() {
		return this.position;
	}
	
	public void setPosition(Vector3f position) {
		this.position = position;
	}
	
	public Vector3f getTarget() {
		return this.target;
	}
	
	public void setTarget(Vector3f target) {
		this.target = target;
	}
	
	public float getPitch() {
		return this.pitch;
	}
	
	public void setPitch(float pitch) {
		this.pitch = pitch;
	}
	
	public float getYaw() {
		return this.yaw;
	}
	
	public void setYaw(float yaw) {
		this.yaw = yaw;
	}
	
	public float getRoll() {
		return this.roll;
	}
	
	public void setRoll(float roll) {
		this.roll = roll;
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
	
	public static float getTargetSpeed() {
		return Camera.targetSpeed;
	}

}
