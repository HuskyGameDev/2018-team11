package lootquest.dungeon.tile;

import lutebox.graphics.Texture;

public class DirtTile extends Tile {
	public DirtTile(int x, int y) {
		super(x, y, new Texture("assets/textures/Dirt.png"), true, TileType.DIRT);
	}
}
