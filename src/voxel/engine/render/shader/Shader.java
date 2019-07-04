package voxel.engine.render.shader;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;

public abstract class Shader {

	private int programId;
	private int vertexShaderId;
	private int fragmentShaderId;
	
	private static FloatBuffer buffer;
	
	public Shader(int programId, int vertexShaderId, int fragmentShaderId) {
		this.programId = programId;
		this.vertexShaderId = vertexShaderId;
		this.fragmentShaderId = fragmentShaderId;
		Shader.buffer = BufferUtils.createFloatBuffer(16);
	}

	public int getProgramId() {
		return this.programId;
	}
	
	public int getVertexShaderId() {
		return this.vertexShaderId;
	}
	
	public int getFragmentShaderId() {
		return this.fragmentShaderId;
	}
	
	public static FloatBuffer getBuffer() {
		return Shader.buffer;
	}
	
}
