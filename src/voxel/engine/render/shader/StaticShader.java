package voxel.engine.render.shader;

public class StaticShader extends Shader{

	private int perspectiveProjectionLocation;
	private int transformationLocation;
	
	public StaticShader(int programId, int vertexShaderId, int fragmentShaderId) {
		super(programId, vertexShaderId, fragmentShaderId);
	}
	
	public int getPespectiveProjectionLocation() {
		return this.perspectiveProjectionLocation;
	}
	
	public void setPerspectiveProjectionLocation(int perspectiveProjectionLocation) {
		this.perspectiveProjectionLocation = perspectiveProjectionLocation;
	}
	
	public int getTransformationLocation() {
		return this.transformationLocation;
	}
	
	public void setTransformationLocation(int transformationLocation) {
		this.transformationLocation = transformationLocation;
	}

}
