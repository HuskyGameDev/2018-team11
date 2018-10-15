package lootquest.dungeon;

import java.util.Random;

public class World {

    private Tile[] tiles; 
    private int worldWidth; 
    private int worldHeight; 
	
	public World( int worldWidth, int worldHeight ) {
		this.worldHeight = worldHeight;
		this.worldWidth = worldWidth;
		
		generateWorld();
	}
	
	public void generateWorld() {
		Random r = new Random();
		tiles = new Tile[worldWidth * worldHeight]; 
		int index = 0;
		
		for ( int x = 0; x < worldWidth; x++ ) {
			for ( int y = 0; y < worldHeight; y++ ) {
				index = r.nextInt(4);
				
				if (index==0) {
					setTile(x, y, new DirtTile(x * Tile.size, y * Tile.size));
				} else {
					setTile(x, y, new GrassTile(x * Tile.size, y * Tile.size));
				}
			}
		}
		
	}
	
	public boolean inBounds(int x, int y) {
		return x >= 0 && x < worldWidth && y >= 0 && y < worldHeight; 
	}
	
	public Tile getTile(int x, int y) {
		if (!inBounds(x, y)) throw new IllegalArgumentException("Position is out of bounds: " + x + ", " + y); 
	    return tiles[x + y * worldWidth]; 
	}
	
	public void setTile(int x, int y, Tile t) {
		if (!inBounds(x, y)) throw new IllegalArgumentException("Position is out of bounds: " + x + ", " + y); 
	    tiles[x + y * worldWidth] = t; 
	}
	
	public void tick() {
		for( Tile t : tiles ) {
			t.tick();
		}
	}
	
	public void render() {
		for( Tile t : tiles ) {
			t.render();
		}
	}
	
}
