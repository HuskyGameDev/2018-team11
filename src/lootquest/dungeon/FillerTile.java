package lootquest.dungeon;

import lutebox.graphics.Texture;

public class FillerTile extends Tile {
    public FillerTile(int x, int y) {
        super(x, y, new Texture("assets/textures/cave/filler.png"), true, TileType.FILLER);
    }
}
