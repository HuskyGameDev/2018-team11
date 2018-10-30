package lootquest.dungeon;

import lutebox.graphics.Texture;

public class WallTile extends Tile {
    public WallTile(int x, int y, String name ) {
        super(x, y, new Texture("assets/textures/cave/"+ name +".png"), true, TileType.WALL);
    }
}
