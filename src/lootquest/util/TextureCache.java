package lootquest.util;

import java.util.HashMap;
import java.util.Map;

import lutebox.graphics.Texture;

public class TextureCache {

	private static Map<String, Texture> textures = new HashMap<>(); 
	
	private TextureCache() {} 
	
	/**
	 * Use this instead of loading textures yourself, 
	 * Much more efficient
	 */
	public static Texture get(String filename) {
		if (!textures.containsKey(filename)) {
			textures.put(filename, new Texture(filename)); 
		}
		return textures.get(filename); 
	}
	
}
