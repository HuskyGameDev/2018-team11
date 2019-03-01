package lootquest.dungeon;

public class OverWorldGen {
    private Tile[][] tiles;
    private String[][] OWG;
    private int spawnX;
    private int spawnY;
    private int dungX;
    private int dungY;
    
    public OverWorldGen() {
        tiles = new Tile[32][32];
        OWG = new String[32][32];
       
        //Encode overworld
        for ( int y = 0; y < 32; y++ ) {
            for ( int x = 0; x < 32; x++ ) {
                if ( x == 16 && y == 13 ) {
                    OWG[x][y] = "DE"; // Dungeon Entrance
                } else {
                    OWG[x][y] = "G"; //Grass
                }
            }
        }
        spawnX = 16;
        spawnY = 16;
        dungX = 16;
        dungY = 13;
        
        //Turn code into tiles
        for ( int y = 0; y < 32; y++ ) {
            for ( int x = 0; x < 32; x++ ) {
                if ( OWG[x][y] == "G" ) {
                    setTile(x, y, new GrassTile((x) * Tile.size, (y) * Tile.size));
                } else if ( OWG[x][y] == "DE" ) {
                    setTile(x, y, new FloorTile((x) * Tile.size, (y) * Tile.size, "stairs"));
                } else {
                    setTile(x, y, new GrassTile((x) * Tile.size, (y) * Tile.size));
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
    
    public int getDungX() {
        return dungX;
    }
    
    public int getDungY() {
        return dungY;
    }
    
    public void setTile(int x, int y, Tile t) {
        if (!inBounds(x, y)) throw new IllegalArgumentException("Position is out of bounds: " + x + ", " + y); 
        tiles[x][y] = t;
    }
    
    public boolean inBounds(int x, int y) {
        return x >= 0 && x < 32 && y >= 0 && y < 32; 
    }
    
    public Tile getTile(int x, int y) {
        if (!inBounds(x, y)) throw new IllegalArgumentException("Position is out of bounds: " + x + ", " + y); 
        return tiles[x][y]; 
    }
    
    public boolean isSolid(int x, int y) {
        if (!inBounds(x, y)) return true; 
        if ( getTile(x,y) == null ) return true;
        return getTile(x, y).isSolid; 
    }
    
    public Tile[][] getEveryTile() {
        return tiles;
    }
    
    public String[][] getFloorPlan() {
        return OWG;
    }
}
