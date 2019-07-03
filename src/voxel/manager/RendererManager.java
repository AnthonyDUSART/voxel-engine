package voxel.manager;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;

import java.nio.FloatBuffer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import voxel.engine.renderer.Renderer;

public class RendererManager {
	
	public static void enable(Renderer renderer, float[] vertices) {
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		renderer.setVbo(glGenBuffers());
		glBindBuffer(GL_ARRAY_BUFFER, renderer.getVbo());
		//glBufferData(GL_ARRAY_BUFFER, vertices.length, vertices, GL_STATIC_DRAW);
		glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW);
	}
	
	public static void render(Renderer renderer) {
		// 1rst attribute buffer : vertices
		glEnableVertexAttribArray(0);
		glBindBuffer(GL_ARRAY_BUFFER, renderer.getVbo());
		glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, FloatBuffer.allocate(0));
		// Draw the triangle !
		glDrawArrays(GL_TRIANGLES, 0, 3); // Starting from vertex 0; 3 vertices total -> 1 triangle
	}
	
	public static void disable(Renderer renderer) {
		glDisableVertexAttribArray(0);
	}

}
