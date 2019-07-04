package voxel.manager.engine;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;

import java.nio.FloatBuffer;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL11;

import voxel.engine.render.Camera;
import voxel.engine.render.Renderer;
import voxel.engine.render.shader.StaticShader;
import voxel.entity.Entity;

public abstract class RendererManager {
	
	public static Renderer create() {
		return new Renderer(
			ShaderManager.getStaticShader("static"),
			new Camera(0.1f, 100.0f)
		);
	}
	
	public static void enable(Renderer renderer, Entity entity) {
		
		// create perspective projection
		glUseProgram(renderer.getShader().getProgramId());
		ShaderManager.loadPerspectiveProjection(
				(StaticShader) renderer.getShader(),
				CameraManager.createPerspectiveProjection(renderer.getCamera())
			);
		glUseProgram(0);
		
		
		glEnable(GL11.GL_DEPTH_TEST);
		glEnable(GL_BLEND);
		glDepthFunc(GL_LESS);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
		renderer.setCbo(glGenBuffers());
		glBindBuffer(GL_ARRAY_BUFFER, renderer.getCbo());
		glBufferData(GL_ARRAY_BUFFER, entity.getColor(), GL_STATIC_DRAW);
		
		renderer.setVbo(glGenBuffers());
		glBindBuffer(GL_ARRAY_BUFFER, renderer.getVbo());
		glBufferData(GL_ARRAY_BUFFER, entity.getVertices(), GL_STATIC_DRAW);
	}
	
	public static void render(Renderer renderer, Entity entity) {
		glUseProgram(renderer.getShader().getProgramId());
		ShaderManager.loadTransformation(
				(StaticShader) renderer.getShader(), 
				RendererManager.createTransformation(
					entity.getPosition(),
					entity.getRotation(),
					1
				)
			);
		
		ShaderManager.loadView((StaticShader) renderer.getShader(), CameraManager.createView(renderer.getCamera()));
		
		
		// 1rst attribute buffer : vertices
		glEnableVertexAttribArray(0);
		glEnableVertexAttribArray(1);
		
		glBindBuffer(GL_ARRAY_BUFFER, renderer.getVbo());
		glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, FloatBuffer.allocate(0));
		
		glBindBuffer(GL_ARRAY_BUFFER, renderer.getCbo());
		glVertexAttribPointer(1, 3, GL_FLOAT, false, 0, FloatBuffer.allocate(0));
		
		
		
		// Draw the triangle !
		glDrawArrays(GL_TRIANGLES, 0, 12*3); // Starting from vertex 0; 3 vertices total -> 1 triangle
		glUseProgram(0);
	}
	
	public static void disable(Renderer renderer) {
		glDisableVertexAttribArray(0);
		glDisableVertexAttribArray(1);
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
