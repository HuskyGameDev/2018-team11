package lootquest.dungeon;

import java.io.FileNotFoundException;

import java.util.Random;

public class World {

    private Tile[] tiles; 
    private int worldIndex;
    private String[] floor;
    private int worldWidth; 
    private int worldHeight; 
    public int roomWidth;
    public int roomHeight;
    private Random r;
	
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
		
		String[][]room = crtRm.createRoom( true, false, false, true);
		addRoom( room, 0, 0);
		
		room = crtRm.createRoom( false, true, true, true );
		addRoom( room, 1, 0);
		
		room = crtRm.createEmpty();
		addRoom( room, 0, 1);
		
		room = crtRm.createRoom( true, false, false, false );
		addRoom( room, 1, 1);
	}
	
	public void addRoom( String[][] room, int xOffset, int yOffset ) {
	    Random ran = new Random();
	    int name = 0;
	    for ( int y = 0; y < roomHeight; y++ ) {
	        for ( int x = 0; x < roomWidth; x++ ) {
	            if ( room[x][y].equals("1") ) {
	                if ( y == 0 && x > 0 && x < roomWidth-1 ) {
	                    setTile(x + (roomWidth * xOffset), y + (roomHeight * yOffset), new WallTile((x + (roomWidth * xOffset)) * Tile.size, (y + (roomHeight * yOffset)) * Tile.size, "wall-up" ));
	                } else if ( y == roomHeight-1 && x > 0 && x < roomWidth-1 ) {
	                    setTile(x + (roomWidth * xOffset), y + (roomHeight * yOffset), new WallTile((x + (roomWidth * xOffset)) * Tile.size, (y + (roomHeight * yOffset)) * Tile.size, "wall-down" ));
	                } else if ( x == 0 && y > 0 && y < roomHeight-1 ) {
                        setTile(x + (roomWidth * xOffset), y + (roomHeight * yOffset), new WallTile((x + (roomWidth * xOffset)) * Tile.size, (y + (roomHeight * yOffset)) * Tile.size, "wall-down" ));
                    } else if ( x == roomWidth-1 && y > 0 && y < roomHeight-1 ) {
                        setTile(x + (roomWidth * xOffset), y + (roomHeight * yOffset), new WallTile((x + (roomWidth * xOffset)) * Tile.size, (y + (roomHeight * yOffset)) * Tile.size, "wall-down" ));
                    } else {
	                    setTile(x + (roomWidth * xOffset), y + (roomHeight * yOffset), new DirtTile((x + (roomWidth * xOffset)) * Tile.size, (y + (roomHeight * yOffset)) * Tile.size));
	                }
	                
	            } else if ( room[x][y].equals("wu")) {
                    setTile(x + (roomWidth * xOffset), y + (roomHeight * yOffset), new WallTile((x + (roomWidth * xOffset)) * Tile.size, (y + (roomHeight * yOffset)) * Tile.size, "wall-up"));
                } else if ( room[x][y].equals("wd")) {
                    setTile(x + (roomWidth * xOffset), y + (roomHeight * yOffset), new WallTile((x + (roomWidth * xOffset)) * Tile.size, (y + (roomHeight * yOffset)) * Tile.size, "wall-down"));
                } else if ( room[x][y].equals("wl")) {
                    setTile(x + (roomWidth * xOffset), y + (roomHeight * yOffset), new WallTile((x + (roomWidth * xOffset)) * Tile.size, (y + (roomHeight * yOffset)) * Tile.size, "wall-left"));
                } else if ( room[x][y].equals("wr")) {
                    setTile(x + (roomWidth * xOffset), y + (roomHeight * yOffset), new WallTile((x + (roomWidth * xOffset)) * Tile.size, (y + (roomHeight * yOffset)) * Tile.size, "wall-right"));
                } else if ( room[x][y].equals("citl")) {
                    setTile(x + (roomWidth * xOffset), y + (roomHeight * yOffset), new WallTile((x + (roomWidth * xOffset)) * Tile.size, (y + (roomHeight * yOffset)) * Tile.size, "corner-itl"));
                } else if ( room[x][y].equals("citr")) {
                    setTile(x + (roomWidth * xOffset), y + (roomHeight * yOffset), new WallTile((x + (roomWidth * xOffset)) * Tile.size, (y + (roomHeight * yOffset)) * Tile.size, "corner-itr"));
                } else if ( room[x][y].equals("cibl")) {
                    setTile(x + (roomWidth * xOffset), y + (roomHeight * yOffset), new WallTile((x + (roomWidth * xOffset)) * Tile.size, (y + (roomHeight * yOffset)) * Tile.size, "corner-ibl"));
                } else if ( room[x][y].equals("cibr")) {
                    setTile(x + (roomWidth * xOffset), y + (roomHeight * yOffset), new WallTile((x + (roomWidth * xOffset)) * Tile.size, (y + (roomHeight * yOffset)) * Tile.size, "corner-ibr"));
                } else if ( room[x][y].equals("cotl")) {
                    setTile(x + (roomWidth * xOffset), y + (roomHeight * yOffset), new WallTile((x + (roomWidth * xOffset)) * Tile.size, (y + (roomHeight * yOffset)) * Tile.size, "corner-otl"));
                } else if ( room[x][y].equals("cotr")) {
                    setTile(x + (roomWidth * xOffset), y + (roomHeight * yOffset), new WallTile((x + (roomWidth * xOffset)) * Tile.size, (y + (roomHeight * yOffset)) * Tile.size, "corner-otr"));
                } else if ( room[x][y].equals("cobl")) {
                    setTile(x + (roomWidth * xOffset), y + (roomHeight * yOffset), new WallTile((x + (roomWidth * xOffset)) * Tile.size, (y + (roomHeight * yOffset)) * Tile.size, "corner-obl"));
                } else if ( room[x][y].equals("cobr")) {
                    setTile(x + (roomWidth * xOffset), y + (roomHeight * yOffset), new WallTile((x + (roomWidth * xOffset)) * Tile.size, (y + (roomHeight * yOffset)) * Tile.size, "corner-obr"));
                } else if ( room[x][y].equals("0") || room[x][y].equals("D") ) {
	                name = ran.nextInt(4);
	                
	                if ( name == 0 ) {
	                    setTile(x + (roomWidth * xOffset), y + (roomHeight * yOffset), new FloorTile((x + (roomWidth * xOffset)) * Tile.size, (y + (roomHeight * yOffset)) * Tile.size, "floor1"));
	                } else {
	                    setTile(x + (roomWidth * xOffset), y + (roomHeight * yOffset), new FloorTile((x + (roomWidth * xOffset)) * Tile.size, (y + (roomHeight * yOffset)) * Tile.size, "floor2"));
	                }
	                
	            } else if ( room[x][y].equals("e")) {
	                setTile(x + (roomWidth * xOffset), y + (roomHeight * yOffset), new FillerTile((x + (roomWidth * xOffset)) * Tile.size, (y + (roomHeight * yOffset)) * Tile.size));
	            } else {
	                setTile(x + (roomWidth * xOffset), y + (roomHeight * yOffset), new GrassTile((x + (roomWidth * xOffset)) * Tile.size, (y + (roomHeight * yOffset)) * Tile.size));
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
