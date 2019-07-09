package voxel.manager.engine;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import voxel.engine.render.model.Obj;
import voxel.util.Util;

public abstract class ObjManager {
	
	public static final String SHADER_PATH = "model/";
	
	public static Obj getObjFromFile2(String filename) {
		String name = null;
		ArrayList<Float> vertices = new ArrayList<Float>();
		ArrayList<Integer> indices = new ArrayList<Integer>();
		try {
			String content = Util.getRessourceFile(ObjManager.SHADER_PATH + filename + ".obj");
			String[] lines = content.split("\r\n|\r|\n");
			for(String line : lines) {
				String[] values = line.split(" ");
				if(line.startsWith("o ")) {
					name = values[1];
				}
				else if(line.startsWith("v ")) {
					vertices.add(Float.parseFloat(values[1]));
					vertices.add(Float.parseFloat(values[2]));
					vertices.add(Float.parseFloat(values[3]));
				}
				else if(line.startsWith("f ")) {
					indices.add(Integer.parseInt(values[1].split("/")[0]) - 1);
					indices.add(Integer.parseInt(values[2].split("/")[0]) - 1);
					indices.add(Integer.parseInt(values[3].split("/")[0]) - 1);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return new Obj(name, Util.floatListToArray(vertices), Util.intListToArray(indices), null, null);
	}

}
