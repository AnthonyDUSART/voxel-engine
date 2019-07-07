package voxel.manager.engine;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_ELEMENT_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL15.glGenBuffers;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;

import voxel.engine.render.Loader;

public abstract class LoaderManager {

	public static void storeData(Loader loader, int attribute, float[] data) {
		int vbo = glGenBuffers();
		loader.addVbo(vbo);
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		FloatBuffer buffer = LoaderManager.getBufferData(data);
		glBufferData(GL_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
		glVertexAttribPointer(attribute, 3, GL_FLOAT, false, 0, 0);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
	}
	
	public static void bindIndicesBuffer(Loader loader, int[] indices) {
		int vbo = glGenBuffers();
		loader.addVbo(vbo);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, vbo);
		IntBuffer buffer = LoaderManager.getBufferData(indices);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
	}
	
	public static int createVao(Loader loader) {
		int vao = glGenVertexArrays();
		loader.addVao(vao);
		glBindVertexArray(vao);
		return vao;
	}

	private static FloatBuffer getBufferData(float[] data) {
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}
	
	private static IntBuffer getBufferData(int[] data) {
		IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}
	
}
