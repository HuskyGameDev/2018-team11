package lootquest.dungeon;

import lootquest.util.TextureCache;

public class GrassTile extends Tile {

	public GrassTile(int x, int y) {
		super(x, y, TextureCache.get("assets/textures/Grass.png"), false, TileType.GRASS);
	}
	
}
