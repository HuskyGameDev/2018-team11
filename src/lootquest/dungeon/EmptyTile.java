package lootquest.dungeon;

public class EmptyTile extends Tile {
    public EmptyTile(int x, int y ) {
        super(x, y, null, false, TileType.FLOOR);
    }
}
