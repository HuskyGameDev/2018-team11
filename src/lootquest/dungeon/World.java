package lootquest.dungeon;

import java.io.FileNotFoundException;

import java.util.Random;

public class World {

    private Tile[][] tiles; 
    private int worldWidth; 
    private int worldHeight; 
    public int roomWidth;
    public int roomHeight;
    private int spawnX;
    private int spawnY;
    private int exitX;
    private int exitY;
    private String[][] floor;
	
	public World( int worldWidth, int worldHeight, int roomWidth, int roomHeight ) {
		this.worldHeight = worldHeight;
		this.worldWidth = worldWidth;
		this.roomHeight = roomHeight;
        this.roomWidth = roomWidth;
		
		try {
            generateWorld();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
	
	public int getWidth() {
	    return worldWidth; 
	}
	
	public int getHeight() {
	    return worldHeight; 
	}
	
	public void generateWorld() throws FileNotFoundException {
		tiles = new Tile[worldWidth][worldHeight]; 
		FloorGen2 crtFlr = new FloorGen2();
		RoomCreater crtRm = new RoomCreater(0, 0, 0, roomWidth, roomHeight);
		
		floor = crtFlr.createFloor((worldWidth/roomWidth), (worldHeight/roomHeight), (worldWidth/roomWidth)/2, (worldHeight/roomHeight)/2);
		//floor = crtFlr.createFloor((worldWidth/roomWidth), (worldHeight/roomHeight), 0, 0);
		
		String[][] path = crtFlr.createExitPath(floor, (worldWidth/roomWidth), (worldHeight/roomHeight), (worldWidth/roomWidth)/2, (worldHeight/roomHeight)/2);
		//String[][] path = crtFlr.createExitPath(floor, (worldWidth/roomWidth), (worldHeight/roomHeight), 0, 0);
		
		String[][] cors = crtFlr.corridorCreater(floor, path, (worldWidth/roomWidth), (worldHeight/roomHeight));
		
		boolean U = false;
		boolean D = false; 
		boolean L = false;
		boolean R = false;
		Random r = new Random();
		int chance = 0;
		String[][] room;
		for ( int y = 0; y < (worldHeight/roomHeight); y++ ) {
		    for ( int x = 0; x < (worldWidth/roomWidth); x++ ) {
		        if ( floor[x][y].equals("X") ) {
		            //Checking for connections to generic room
		            if ( cors[x][y].contains("^") ) {
		                U = true;
		            }
		            if ( cors[x][y].contains("v") ) {
		                D = true;
		            }
		            if ( cors[x][y].contains("<") ) {
		                L = true;
		            }
		            if ( cors[x][y].contains(">") ) {
		                R = true;
		            }
		            
		            //Creating Generic room or Corridor
		            chance = r.nextInt(5)-2;
		            if ( chance <= 0 ) {
		                floor[x][y] = "C";
		                room = crtRm.createCorridor(U, D, L, R);
		                addRoom( room, x, y);
		            } else {
		                room = crtRm.createRoom(U, D, L, R);
                        addRoom( room, x, y);
		            }
		        } else if ( floor[x][y].equals("S") ) {
		            //Checking for connections to start room
                    if ( cors[x][y].contains("^") ) {
                        U = true;
                    }
                    if ( cors[x][y].contains("v") ) {
                        D = true;
                    }
                    if ( cors[x][y].contains("<") ) {
                        L = true;
                    }
                    if ( cors[x][y].contains(">") ) {
                        R = true;
                    }
                    room = crtRm.createStart(U, D, L, R);
                    addRoom( room, x, y);
		        } else if ( floor[x][y].equals("E") ) {
		            //Checking for connections to exit room
                    if ( cors[x][y].contains("^") ) {
                        U = true;
                    }
                    if ( cors[x][y].contains("v") ) {
                        D = true;
                    }
                    if ( cors[x][y].contains("<") ) {
                        L = true;
                    }
                    if ( cors[x][y].contains(">") ) {
                        R = true;
                    }
                    room = crtRm.createExit(U, D, L, R);
                    addRoom( room, x, y );
		        } else {
		            room = crtRm.createEmpty();
		            addRoom( room, x, y );
		        }
		        U = false;
		        D = false;
		        L = false;
		        R = false;
		    }
		}
	}
	
	public void addRoom( String[][] room, int xOffset, int yOffset ) {
	    Random ran = new Random();
	    int name = 0;
	    for ( int y = 0; y < roomHeight; y++ ) {
	        for ( int x = 0; x < roomWidth; x++ ) {
	            if ( room[x][y].equals("wu")) {
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
                } else if ( room[x][y].equals("S")) {
                    spawnX = x + (roomWidth * xOffset);
                    spawnY = y + (roomHeight * yOffset);
                    setTile(x + (roomWidth * xOffset), y + (roomHeight * yOffset), new FloorTile((x + (roomWidth * xOffset)) * Tile.size, (y + (roomHeight * yOffset)) * Tile.size, "entrance0"));
                } else if ( room[x][y].equals("E")) {
                    exitX = x + (roomWidth * xOffset);
                    exitY = y + (roomHeight * yOffset);
                    setTile(x + (roomWidth * xOffset), y + (roomHeight * yOffset), new FloorTile((x + (roomWidth * xOffset)) * Tile.size, (y + (roomHeight * yOffset)) * Tile.size, "entrance1"));
                } else if ( room[x][y].equals("0") || room[x][y].equals("D") || room[x][y].equals("1") ) {
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
	
	public int getSpawnX() {
	    return spawnX;
	}
	
	public int getSpawnY() {
	    return spawnY;
	}
	
	public int getExitX() {
        return exitX;
    }
	
	public int getExitY() {
        return exitY;
    }
	
	public String[][] getFloor() {
	    return floor;
	}
	
	public float[] getEnemySpawn( int flrX, int flrY ) {
	    Random r = new Random();
	    int x = r.nextInt(6)+5;
	    int y = r.nextInt(6)+5;
	    float [] point = {(flrX * roomWidth) + x, (flrY * roomHeight) + y};
	    return point;
	}
	
	public boolean inBounds(int x, int y) {
		return x >= 0 && x < worldWidth && y >= 0 && y < worldHeight; 
	}
	
	public Tile getTile(int x, int y) {
		if (!inBounds(x, y)) throw new IllegalArgumentException("Position is out of bounds: " + x + ", " + y); 
	    return tiles[x][y]; 
	}
	
	public void setTile(int x, int y, Tile t) {
		if (!inBounds(x, y)) throw new IllegalArgumentException("Position is out of bounds: " + x + ", " + y); 
	    tiles[x][y] = t;
	}
	
	public boolean isSolid(int x, int y) {
		if (!inBounds(x, y)) return true; 
		if ( getTile(x,y) == null ) return true;
		return getTile(x, y).isSolid; 
	}
	
//	public void tick() {
//	    for ( int y = 0; y < worldHeight; y++ ) {
//            for ( int x = 0; x < worldWidth; x++ ) {
//                Tile t = tiles[x][y];
//                t.tick();
//            }
//        }
//	}
//	
//	public void render() {
//	    for ( int y = 0; y < worldHeight; y++ ) {
//	        for ( int x = 0; x < worldWidth; x++ ) {
//	            Tile t = tiles[x][y];
//	            if ( t != null ) {
//	                t.render();
//	            }
//	        }
//	    }
//	}
	
}
