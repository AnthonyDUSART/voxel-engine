package voxel.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public abstract class Loader {
	
	public static final String RESSOURCES_PATH = "res/";

	public static String getRessourceFile(String path) throws FileNotFoundException {
		
		String line;
		StringBuilder result = new StringBuilder();
		BufferedReader reader = new BufferedReader(new FileReader(new File(Loader.RESSOURCES_PATH + path)));
		try {
			
			while((line = reader.readLine()) != null)
				result.append(line).append("\n");
				
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result.toString();
	}
	
}
