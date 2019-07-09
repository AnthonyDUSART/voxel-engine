package voxel.manager.engine;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import voxel.engine.render.Camera;
import voxel.engine.render.Loader;
import voxel.engine.render.Renderer;
import voxel.engine.render.shader.StaticShader;
import voxel.entity.Entity;

public abstract class RendererManager {
	
	public static Renderer create() {
		return new Renderer(
			ShaderManager.getStaticShader("static"),
			new Camera(0.1f, 600.0f),
			new Loader()
		);
	}
	
	public static void prepare() {
		glEnable(GL_DEPTH_TEST);
		
		// enable culling after normal consideration in shader
		glEnable(GL_CULL_FACE);
		glCullFace(GL_BACK);
//		glCullFace(GL_FRONT_FACE);
		glDepthFunc(GL_LESS);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	}
	
	public static void enable(Renderer renderer, Entity entity) {
		
		// create perspective projection
		glUseProgram(renderer.getShader().getProgramId());
		ShaderManager.loadPerspectiveProjection(
				(StaticShader) renderer.getShader(),
				CameraManager.createPerspectiveProjection(renderer.getCamera())
			);
		glUseProgram(0);
	}
	
	private static void bind(Entity entity) {
		glBindVertexArray(entity.getModel().getVao());
		glEnableVertexAttribArray(0);
		glEnableVertexAttribArray(1);
	}
	
	private static void unbind() {
		glDisableVertexAttribArray(0);
		glDisableVertexAttribArray(1);
		glBindVertexArray(0);
	}
	
	public static void render(Renderer renderer, Entity entity) {
		RendererManager.bind(entity);
		glUseProgram(renderer.getShader().getProgramId());
		RendererManager.prepare();
		RendererManager.prepareInstance(renderer, entity);
		glDrawElements(GL_TRIANGLES, entity.getModel().getVertexCount(), GL_UNSIGNED_INT, 0);
		glUseProgram(0);
		RendererManager.unbind();
	}
	
	private static void prepareInstance(Renderer renderer, Entity entity) {
		ShaderManager.loadTransformation(
				(StaticShader) renderer.getShader(), 
				RendererManager.createTransformation(
					entity.getPosition(),
					entity.getRotation(),
					1
				)
			);
		ShaderManager.loadView((StaticShader) renderer.getShader(), CameraManager.createFpsView(renderer.getCamera()));
	}
	
	public static Matrix4f createTransformation(Vector3f translation, Vector3f rotation, float scale) {
		Matrix4f m = new Matrix4f();
		m.identity();
		m.translate(translation);
		
		m.rotate((float) Math.toRadians(rotation.x), 1, 0, 0);
		m.rotate((float) Math.toRadians(rotation.y), 0, 1, 0);
		m.rotate((float) Math.toRadians(rotation.z), 0, 0, 1);
		m.scale(scale);
		return m;
	}
}
