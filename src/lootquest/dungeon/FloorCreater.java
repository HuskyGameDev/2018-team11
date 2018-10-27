package lootquest.dungeon;
import java.util.Random;

public class FloorCreater {
    public int roomCount = 0;
    public int roomChance = 2;
    public Random r = new Random();
    
    /*
     * @Parameters
     * floor = an array for the layout of the floor. An "X" denotes a room, "S" denotes 
     *  the start room, and an "E" denotes the exit room.
     * the x and y are the cordinates of the start room.
     * the w and h are the width and height of the world where w = 1 and h = 1 is a 64x64
     *  tile world.
     */
    public void createRooms ( String [] floor, int x, int y, int w, int h ) {
        int chance = -1;
        for ( int i = 1; i <= 4; i++ ) {
            if ( i == 1 ) {
                chance = r.nextInt(roomChance + roomCount);
                if ( chance <= 0 ) {
                    addRoom( floor, x, y, "Above", w, h);
                }
            } else if ( i == 2 ) {
                chance = r.nextInt(roomChance + roomCount) - 1;
                if ( chance <= 0 ) {
                    addRoom( floor, x, y, "Below", w, h);
                }
            } else if ( i == 3 ) {
                chance = r.nextInt(roomChance + roomCount) - 3;
                if ( chance <= 0 ) {
                    addRoom( floor, x, y, "Left", w, h);
                }
            } else {
                chance = r.nextInt(roomChance + roomCount) - 5;
                if ( chance <= 0 ) {
                    addRoom( floor, x, y, "Right", w, h);
                }
            }
        }
        createExit( floor, x, y, w, h);
    }
    
    //Generates a room at a specified location given a position around that location.
    private void addRoom( String[] floor, int x, int y, String loc, int w, int h ) {
        int count = 0;
        if ( x == 1 || x == w || y == 1 || y == h ) {
            return;
        }
        if ( loc == "Above" ) {
            for (int Y = 1; Y <= h; Y++) {
                for (int X = 1; X <= w; X++ ) {
                    if ( X == x && Y == y-1 ) {
                        if ( floor[count].equals(" ")) {
                            floor[count] = "X";
                            roomCount++;
                            createRooms( floor, X, Y, w, h );
                        }
                        return;
                    }
                    count++;
                }
            }
        } else if ( loc == "Below" ) {
            for (int Y = 1; Y <= h; Y++) {
                for (int X = 1; X <= w; X++ ) {
                    if ( X == x && Y == y+1 ) {
                        if ( floor[count].equals(" ")) {
                            floor[count] = "X";
                            roomCount++;
                            createRooms( floor, X, Y, w, h );
                        }
                        return;
                    }
                    count++;
                }
            }
        } else if ( loc == "Left" ) {
            for (int Y = 1; Y <= h; Y++) {
                for (int X = 1; X <= w; X++ ) {
                    if ( X == x-1 && Y == y ) {
                        if ( floor[count].equals(" ")) {
                            floor[count] = "X";
                            roomCount++;
                            createRooms( floor, X, Y, w, h );
                        }
                        return;
                    }
                    count++;
                }
            }
        } else if ( loc == "Right" ) {
            for (int Y = 1; Y <= h; Y++) {
                for (int X = 1; X <= w; X++ ) {
                    if ( X == x+1 && Y == y ) {
                        if ( floor[count].equals(" ")) {
                            floor[count] = "X";
                            roomCount++;
                            createRooms( floor, X, Y, w, h );
                        }
                        return;
                    }
                    count++;
                }
            }
        }
    }
    
    //Goes through the entire world. Each room that is at least one room away from
    //the start room has a chance to be the exit room.
    private void createExit ( String [] floor, int x, int y, int w, int h ) {
        int chance = -1;
        int index = 0;
        
        boolean exit = false;
        
        int indexX = 1;
        int indexY = 1;
        
        while ( exit == false ) {
            if (index == floor.length) {
                index = 0;
            }
            if ( floor[index].equals("X") ) {
                if ( ( indexX != x+1 ) && (indexY != y+1) ) {
                    if ( ( indexX != x-1 ) && (indexY != y-1) ) {
                        chance = r.nextInt(15);
                        if ( chance == 0 ) {
                            floor[index] = "E";
                            System.out.println("x = " + indexX + ", y= " + indexY);
                            exit = true;
                        } 
                    }
                }
            }
            index++;
            indexX++;
            if ( indexX == w + 1 ) {
                indexX = 1;
                indexY++;
            }
            if ( indexY == h + 1 ) {
                indexY = 1;
            } 
        }
    }
}
