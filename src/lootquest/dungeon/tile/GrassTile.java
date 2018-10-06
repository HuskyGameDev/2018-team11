package lootquest.dungeon.tile;

import lutebox.graphics.Texture;

public class GrassTile extends Tile {

	public GrassTile(int x, int y) {
		super(x, y, new Texture("assets/textures/Grass.png"), false, TileType.GRASS);
	}
	
}
