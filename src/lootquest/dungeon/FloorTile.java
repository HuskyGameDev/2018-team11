package lootquest.dungeon;

import lutebox.graphics.Texture;

public class FloorTile extends Tile {
    public FloorTile(int x, int y, String name ) {
        super(x, y, new Texture("assets/textures/cave/"+ name +".png"), false, TileType.FLOOR);
    }
}
