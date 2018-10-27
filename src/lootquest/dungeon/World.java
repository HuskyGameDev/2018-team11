package lootquest.dungeon;

import java.io.FileNotFoundException;

//import java.util.Random;

public class World {

    private Tile[] tiles; 
    private int worldIndex;
    private String[] floor;
    private int worldWidth; 
    private int worldHeight; 
    public int roomWidth;
    public int roomHeight;
	
	public World( int worldWidth, int worldHeight, int roomWidth, int roomHeight ) {
		this.worldHeight = worldHeight;
		this.worldWidth = worldWidth;
		this.roomHeight = roomHeight;
        this.roomWidth = roomWidth;
		this.worldIndex = 0;
		
		try {
            generateWorld();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
	
	public void generateWorld() throws FileNotFoundException {
		tiles = new Tile[worldWidth * worldHeight]; 
		FloorCreater crtFlr = new FloorCreater();
		RoomCreater crtRm = new RoomCreater(0, 0, 0, roomWidth, roomHeight);
		
		String[][]room = crtRm.createRoom( false, false, false, true );
		addRoom( room, 0, 0);
		
		room = crtRm.createCorridor();
		addRoom( room, 1, 0);
		
		room = crtRm.createEmpty();
		addRoom( room, 0, 1);
		
		room = crtRm.createRoom( true, false, false, false );
		addRoom( room, 1, 1);
	}
	
	public void addRoom( String[][] room, int xOffset, int yOffset ) {
	    for ( int y = 0; y < 16; y++ ) {
	        for ( int x = 0; x < 16; x++ ) {
	            if ( room[x][y].equals("1") ) {
	                setTile(x + (16 * xOffset), y + (16 * yOffset), new DirtTile((x + (16 * xOffset)) * Tile.size, (y + (16 * yOffset)) * Tile.size));
	            } else {
	                setTile(x + (16 * xOffset), y + (16 * yOffset), new GrassTile((x + (16 * xOffset)) * Tile.size, (y + (16 * yOffset)) * Tile.size));
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
	
	public boolean isSolid(int x, int y) {
		if (!inBounds(x, y)) return true; 
		return getTile(x, y).isSolid; 
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
