package lootquest.dungeon;

import lootquest.util.TextureCache;

public class WallTile extends Tile {
    public WallTile(int x, int y, String name ) {
        super(x, y, TextureCache.get("assets/textures/cave/"+ name +".png"), true, TileType.WALL);
    }
}
