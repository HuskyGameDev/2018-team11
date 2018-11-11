package lootquest.dungeon;

import lootquest.util.TextureCache;

public class FloorTile extends Tile {
    public FloorTile(int x, int y, String name ) {
        super(x, y, TextureCache.get("assets/textures/cave/"+ name +".png"), false, TileType.FLOOR);
    }
}
