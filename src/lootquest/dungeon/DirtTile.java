package lootquest.dungeon;

import lootquest.util.TextureCache;

public class DirtTile extends Tile {
	public DirtTile(int x, int y) {
		super(x, y, TextureCache.get("assets/textures/Dirt.png"), true, TileType.DIRT);
	}
}
