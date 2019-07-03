package voxel.manager;

import java.io.FileNotFoundException;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import static org.lwjgl.opengl.GL20.*;

import voxel.engine.render.shader.StaticShader;
import voxel.util.Loader;

public abstract class ShaderManager {
	
	public static final String SHADER_PATH = "shader/";

	public static StaticShader getStaticShader(String filename) {
		int vertexShaderId = glCreateShader(GL_VERTEX_SHADER);
		int fragmentShaderId = glCreateShader(GL_FRAGMENT_SHADER);
		try {
			glShaderSource(vertexShaderId, Loader.getRessourceFile(SHADER_PATH + filename + ".vs"));
			glShaderSource(fragmentShaderId, Loader.getRessourceFile(SHADER_PATH + filename + ".fs"));
		} catch (FileNotFoundException e) {
			System.err.println("Impossible de trouver le shader: " + filename);
			e.printStackTrace();
		}
		
		try {
			ShaderManager.compileShader(vertexShaderId);
			
		} catch (Exception e) {
			System.err.println("Erreur dans la compilation du shader vertex : " + filename);
			e.printStackTrace();
		}
		
		try {
			ShaderManager.compileShader(fragmentShaderId);
		} catch (Exception e) {
			System.err.println("Erreur dans la compilation du shader fragment : " + filename);
			e.printStackTrace();
		}
		
		int programId = glCreateProgram();
		glAttachShader(programId, vertexShaderId);
		glAttachShader(programId, fragmentShaderId);
		glLinkProgram(programId);
		glValidateProgram(programId);
		
		glDetachShader(programId, vertexShaderId);
		glDetachShader(programId, fragmentShaderId);
		glDeleteShader(vertexShaderId);
		glDeleteShader(fragmentShaderId);

		return new StaticShader(programId, vertexShaderId, fragmentShaderId);
	}
	
	private static void compileShader(int id) throws Exception {
		glCompileShader(id);
		System.out.println("Compile shader");
		if(GL20.glGetShaderi(id, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
			throw new Exception("Impossible de compiler le shader id : " + id + " -> " + glGetShaderInfoLog(id, 500));
		}
	}
	
}
