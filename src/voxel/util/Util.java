package voxel.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public abstract class Util {
	
	public static final String RESSOURCES_PATH = "res/";

	public static String getRessourceFile(String path) throws FileNotFoundException {
		
		String line;
		StringBuilder result = new StringBuilder();
		BufferedReader reader = new BufferedReader(new FileReader(new File(Util.RESSOURCES_PATH + path)));
		try {
			
			while((line = reader.readLine()) != null)
				result.append(line).append("\n");
				
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result.toString();
	}
	
	public static float[] floatListToArray(List<Float> list) {
		float[] array = new float[list.size()];
		for(int i = 0; i < array.length; i++)
			array[i] = list.get(i);
		return array;
			
	}
	
	public static int[] intListToArray(List<Integer> list) {
		int[] array = new int[list.size()];
		for(int i = 0; i < array.length; i++)
			array[i] = list.get(i);
		return array;
	}
	
}
