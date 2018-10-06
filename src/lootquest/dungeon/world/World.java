package lootquest.dungeon.world;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import lootquest.dungeon.tile.DirtTile;
import lootquest.dungeon.tile.GrassTile;
import lootquest.dungeon.tile.Tile;

public class World {

	ArrayList<Tile> tile = new ArrayList<Tile>();
	int worldWidth;
	int worldHeight;
	
	public World( int worldWidth, int worldHeight ) {
		this.worldHeight = worldHeight;
		this.worldWidth = worldWidth;
		
		generateWorld();
	}
	
	public void generateWorld() {
		Random r = new Random();
		int index = 0;
		
		for ( int x = 0; x < worldWidth; x++ ) {
			for ( int y = 0; y < worldHeight; y++ ) {
				index = r.nextInt(4);
				
				if (index==0) {
					tile.add(new DirtTile(x * Tile.size, y * Tile.size));
				} else {
					tile.add(new GrassTile(x * Tile.size, y * Tile.size));
				}
			}
		}
		
	}
	
	public void tick() {
		for( Tile t : tile ) {
			t.tick();
		}
	}
	
	public void render() {
		for( Tile t : tile ) {
			t.render();
		}
	}
	
}
