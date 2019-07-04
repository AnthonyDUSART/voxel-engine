package voxel.engine.render;

import org.joml.Vector2f;
import org.joml.Vector3f;

public class Camera {
	
	private Vector3f position;
	private float pitch;
	private float yaw;
	private float zNear;
	private float zFar;
	private float fov;
	private static float mouseSpeed;
	
	public Camera(float zNear, float zFar) {
		this(new Vector3f(0, 0, 10), 0, 0, zNear, zFar, 70);
	}
	
	public Camera(Vector3f position, float pitch, float yaw, float zNear, float zFar, float fov) {
		this.position = position;
		this.pitch = pitch;
		this.yaw = yaw;
		this.zNear = zNear;
		this.zFar = zFar;
		this.fov = fov;
		Camera.mouseSpeed = 0.08f;
		
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
	
	public Vector3f increasePosition(float x, float y, float z) {
		return this.position.add(x, y, z);
	}
	
	public Vector2f increaseOrientation(float x, float y) {
		this.pitch -= x * mouseSpeed;
		this.yaw -= y * mouseSpeed;
		
		if(this.yaw > 90)
			this.yaw = 90;
		else if(this.yaw < -90)
			this.yaw = -90;
		
		System.out.println("pitch:" + this.pitch + ", yaw:" + this.yaw);
		
		return new Vector2f(this.pitch, this.yaw);
	}
	
	public Vector3f getPosition() {
		return this.position;
	}
	
	public void setPosition(Vector3f position) {
		this.position = position;
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
