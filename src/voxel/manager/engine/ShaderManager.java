package voxel.manager.engine;

import java.io.FileNotFoundException;

import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import static org.lwjgl.opengl.GL20.*;

import voxel.engine.render.shader.Shader;
import voxel.engine.render.shader.StaticShader;
import voxel.util.Loader;

public abstract class ShaderManager {
	
	public static final String SHADER_PATH = "shader/";

	private static void compileShader(int id) throws Exception {
		glCompileShader(id);
		System.out.println("Compile shader");
		if(GL20.glGetShaderi(id, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
			throw new Exception("Impossible de compiler le shader id : " + id + " -> " + glGetShaderInfoLog(id, 500));
		}
	}
	
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

		StaticShader shader = new StaticShader(programId, vertexShaderId, fragmentShaderId);
		ShaderManager.setUniformsLocation(shader);
		ShaderManager.bindAttributes(shader);
		
		return shader;
	}
	
	private static void bindAttribute(Shader shader, int attribute, String variableName) {
		glBindAttribLocation(shader.getProgramId(), attribute, variableName);
	}
	
	private static void bindAttributes(StaticShader shader) {
		ShaderManager.bindAttribute(shader, 0, "i_position");
		ShaderManager.bindAttribute(shader, 2, "i_color");
	}
	
	private static void setUniformsLocation(StaticShader shader) {
		shader.setPerspectiveProjectionLocation(glGetUniformLocation(shader.getProgramId(), "u_projection"));
		shader.setTransformationLocation(glGetUniformLocation(shader.getProgramId(), "u_transformation"));
		shader.setViewLocation(glGetUniformLocation(shader.getProgramId(), "u_view"));
	}
	
	private static void loadMatrix4f(int location, Matrix4f m) {
		m.get(Shader.getBuffer());
		glUniformMatrix4fv(location, false, Shader.getBuffer());
	}
	
	public static void loadPerspectiveProjection(StaticShader shader, Matrix4f m) {
		ShaderManager.loadMatrix4f(shader.getPespectiveProjectionLocation(), m);
	}
	
	public static void loadTransformation(StaticShader shader, Matrix4f m) {
		ShaderManager.loadMatrix4f(shader.getTransformationLocation(), m);
	}
	
	public static void loadView(StaticShader shader, Matrix4f m) {
		ShaderManager.loadMatrix4f(shader.getViewLocation(), m);
	}
}
