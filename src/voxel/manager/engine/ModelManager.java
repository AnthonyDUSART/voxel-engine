package voxel.manager.engine;

import voxel.engine.render.Loader;
import voxel.engine.render.model.Model;

public abstract class ModelManager {
	
	public static Model create(Loader loader, float[] vertices, int[] indices, float[] colors) {
		int vao = LoaderManager.createVao(loader);
		LoaderManager.storeData(loader, 0, vertices);
		LoaderManager.storeData(loader, 1, colors);
		LoaderManager.bindIndicesBuffer(loader, indices);
		
		return new Model(vao, indices.length);
	}
	
}
