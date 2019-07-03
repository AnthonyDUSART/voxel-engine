package voxel.engine.render.shader;

public abstract class Shader {

	private int programId;
	private int vertexShaderId;
	private int fragmentShaderId;
	
	public Shader(int programId, int vertexShaderId, int fragmentShaderId) {
		this.programId = programId;
		this.vertexShaderId = vertexShaderId;
		this.fragmentShaderId = fragmentShaderId;
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
	
}
