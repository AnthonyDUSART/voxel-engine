package voxel.manager.engine;

import org.joml.Matrix4f;

import voxel.engine.render.Camera;
import voxel.main.Main;

public abstract class CameraManager {

	public static Matrix4f createPerspectiveProjection(Camera camera) {
		
		float aspectRatio = Main.getGame().getWindow().getWidth() / Main.getGame().getWindow().getHeight();
		float y_scale = (float) ((1f / Math.tan(Math.toRadians(camera.getFov() / 2f))) * aspectRatio);
		float x_scale = y_scale / aspectRatio;
		float frustum_lenght = camera.getZFar() - camera.getZNear();
		
		Matrix4f m = new Matrix4f();
		m._m00(x_scale);
		m._m11(y_scale);
		m._m22(-((camera.getZFar() + camera.getZNear()) / frustum_lenght));
		m._m23(-1);
		m._m32(-((2 * camera.getZNear() * camera.getZFar()) / frustum_lenght));
		m._m33(0);
		
		return m;
	}
	
}
