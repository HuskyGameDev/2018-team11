package lootquest.dungeon;

import lootquest.util.TextureCache;

public class FillerTile extends Tile {
    public FillerTile(int x, int y) {
        super(x, y, TextureCache.get("assets/textures/cave/filler.png"), true, TileType.FILLER);
    }
}
