package lootquest.dungeon;
import java.util.Random;

public class FloorGen2 {
    public Random r = new Random();
    public int totalRooms = 0;
    public int roomChance = 1;
    public int cUP = 7;
    public int cDOWN = 9;
    public int cLEFT = 9;
    public int cRIGHT = 12;
    public int cc = 2;
    public int xE = -1;
    public int yE = -1;
    
    public String[][] createFloor( int w, int h, int xStart, int yStart ) {
        totalRooms = 0;
        
        String[][] floor = new String[w][h];
        for ( int y = 0; y < h; y++ ) {
            for ( int x = 0; x < w; x++ ) {
                floor[x][y] = " ";
            }
        }
        floor[xStart][yStart] = "S";
        
        genRooms(floor, w, h, xStart, yStart);
        
        createExit( floor, w, h, xStart, yStart );
        
        return floor;
    }
    
    public void genRooms( String[][] floor, int w, int h, int xS, int yS ) {
        int chance = -1;
        for ( int i = 0; i < 4; i++ ) {
            if ( i == 0 ) {
                chance = r.nextInt(totalRooms + roomChance) - cUP;
                if ( chance <= 0 ) {
                    createRoom( "UP", floor, w, h, xS, yS );
                }
            } else if ( i == 1 ) {
                chance = r.nextInt(totalRooms + roomChance) - cDOWN;
                if ( chance <= 0 ) {
                    createRoom( "DOWN", floor, w, h, xS, yS );
                }
            } else if ( i == 2 ) {
                chance = r.nextInt(totalRooms + roomChance) - cLEFT;
                if ( chance <= 0 ) {
                    createRoom( "LEFT", floor, w, h, xS, yS );
                }
            } else {
                chance = r.nextInt(totalRooms + roomChance) - cRIGHT;
                if ( chance <= 0 ) {
                    createRoom( "RIGHT", floor, w, h, xS, yS );
                }
            }
        }
    }
    
    public void createRoom( String loc, String[][] floor, int w, int h, int x, int y ) {
        if ( loc == "UP" && y-1 >= 0 ) {
            if ( floor[x][y-1].equals(" ") ) {
                floor[x][y-1] = "X";
                totalRooms++;
                genRooms( floor, w, h, x, y-1 );
            }
        } else if ( loc == "DOWN" && y+1 < h ) {
            if ( floor[x][y+1].equals(" ") ) {
                floor[x][y+1] = "X";
                totalRooms++;
                genRooms( floor, w, h, x, y+1 );
            }
        }  else if ( loc == "LEFT" && x-1 >= 0 ) {
            if ( floor[x-1][y].equals(" ") ) {
                floor[x-1][y] = "X";
                totalRooms++;
                genRooms( floor, w, h, x-1, y );
            }
        }  else if ( loc == "RIGHT" && x+1 < w ) {
            if ( floor[x+1][y].equals(" ") ) {
                floor[x+1][y] = "X";
                totalRooms++;
                genRooms( floor, w, h, x+1, y );
            }
        }
    }
    
    public void createExit( String[][] floor, int w, int h, int xS, int yS ) {
        boolean exit = false;
        int chance = 0;
        int iter = 0;
        boolean room = false;
        while (exit == false) {
            for ( int y = 0; y < h; y++ ) {
                for ( int x = 0; x < w; x++ ) {
                    if ( floor[x][y].equals("X") && exit == false ) {
                        room = true;
                        if ( !(y <= yS+2 && y >= yS-2 && x <= xS+2 && x >= xS-2) ) {
                            chance = r.nextInt(20) - iter;
                            if ( chance <= 0 ) {
                                floor[x][y] = "E";
                                xE = x;
                                yE = y;
                                exit = true;
                                break;
                            }
                        }
                    }
                }
            }
            iter++;
            if ( iter >= 100 ) {
                System.out.println( "Too hard to find an exit. There may not be enough rooms or \nrooms are too close to the exit. ");
                for ( int y = 0; y < h; y++ ) {
                    for ( int x = 0; x < w; x++ ) {
                        System.out.print("[" + floor[x][y] + "] ");
                    }
                    System.out.println();
                }
                System.out.println(iter);
                break;
            }
            if ( room == false ) {
                System.out.println( "There are no rooms.");
                for ( int y = 0; y < h; y++ ) {
                    for ( int x = 0; x < w; x++ ) {
                        System.out.print("[" + floor[x][y] + "] ");
                    }
                    System.out.println();
                }
                break;
            }
        }
    }
    
    public String[][] createExitPath( String[][] floor, int w, int h, int xS, int yS ) {
        String[][] path = new String[w][h];

        for ( int y = 0; y < h; y++ ) {
            for ( int x = 0; x < w; x++ ) {
                path[x][y] = " ";
            }
        }
        path[xS][yS] = "S";
        
        createPath( path, w, h, xE, yE, xS, yS );
        
        //Combining to make sure rooms exist
        for ( int y = 0; y < h; y++ ) {
            for ( int x = 0; x < w; x++ ) {
                if ( floor[x][y].equals(" ") && path[x][y].equals("X") ) {
                    floor[x][y] = path[x][y];
                }
            }
        }
        
        return path;
    }
    
    public void createPath( String[][] path, int w, int h, int xE, int yE, int xP, int yP ) {
        //Vertical with Exit
        if ( xE == xP ) {
            //Below, Above, Equal
            if ( yE < yP ) {
                path[xP][yP-1] = "X";
                createPath( path, w, h, xE, yE, xP, yP-1 );
            } else if ( yE > yP ) {
                path[xP][yP+1] = "X";
                createPath( path, w, h, xE, yE, xP, yP+1 );
            } else {
                //They are on the exit
                path[xP][yP] = "E";
                return;
            }
        } else if ( xE > xP ) {
            if ( yE < yP ) {
                //The exit is to the right and below
                int chance = r.nextInt(2);
                if ( chance == 0 ) {
                    path[xP][yP-1] = "X";
                    createPath( path, w, h, xE, yE, xP, yP-1 );
                } else {
                    path[xP + 1][yP] = "X";
                    createPath( path, w, h, xE, yE, xP+1, yP );
                }
            } else if ( yE > yP ) {
                //The exit is to the right and above
                int chance = r.nextInt(2);
                if ( chance == 0 ) {
                    path[xP][yP+1] = "X";
                    createPath( path, w, h, xE, yE, xP, yP+1 );
                } else {
                    path[xP + 1][yP] = "X";
                    createPath( path, w, h, xE, yE, xP+1, yP );
                }
            } else {
                path[xP + 1][yP] = "X";
                createPath( path, w, h, xE, yE, xP+1, yP );
            }
        } else {
            if ( yE < yP ) {
                //The exit is to the left and below
                int chance = r.nextInt(2);
                if ( chance == 0 ) {
                    path[xP][yP-1] = "X";
                    createPath( path, w, h, xE, yE, xP, yP-1 );
                } else {
                    path[xP - 1][yP] = "X";
                    createPath( path, w, h, xE, yE, xP-1, yP );
                }
            } else if ( yE > yP ) {
                //The exit is to the left and above
                int chance = r.nextInt(2);
                if ( chance == 0 ) {
                    path[xP][yP+1] = "X";
                    createPath( path, w, h, xE, yE, xP, yP+1 );
                } else {
                    path[xP - 1][yP] = "X";
                    createPath( path, w, h, xE, yE, xP-1, yP );
                }
            } else {
                path[xP - 1][yP] = "X";
                createPath( path, w, h, xE, yE, xP-1, yP );
            }
        }
    }
    
    public String[][] corridorCreater ( String[][] floor, String[][] path, int w, int h ) {
        String[][] newFloor = new String[w][h];
        for ( int y = 0; y < h; y++ ) {
            for ( int x = 0; x < w; x++ ) {
                newFloor[x][y] = "____";
            }
        }
        int chance = 0;
        for ( int y = 0; y < h; y++ ) {
            for ( int x = 0; x < w; x++ ) {
                //Get the current corridor(cor) pointers of the room at floor[x][y]
                String cor = newFloor[x][y];
                
                if ( path[x][y].equals("E") ) {
                    //If path[x][y] is E the exit, we need to find the next room and link them with a corridor
                    //cor = cor + "E";
                    
                    if ( y > 0 && path[x][y-1].equals("X") ) {
                        //The next room is above the Exit, make a link on Exit pointing up
                        if ( !cor.contains("^") ) {
                            cor = cor.replaceFirst("_", "^");
                        }
                        
                        if ( !newFloor[x][y-1].contains("v") ) {
                            //If the next room doesn't already point down to the Exit, make it.
                            newFloor[x][y-1] = newFloor[x][y-1].replaceFirst("_", "v");
                        }
                        
                    } else if ( y < h-1 && path[x][y+1].equals("X") ) {
                        //The next room is below the Exit, make a link on Exit pointing down
                        if ( !cor.contains("v") ) {
                            cor = cor.replaceFirst("_", "v");
                        }
                        
                        if ( !newFloor[x][y+1].contains("^") ) {
                           //If the next room doesn't already point up to the Exit, make it.
                            newFloor[x][y+1] = newFloor[x][y+1].replaceFirst("_", "^");
                        }
                        
                    } else if ( x > 0 && path[x-1][y].equals("X") ) {
                        //The next room is to the left of the Exit, make a link on Exit pointing left
                        if ( !cor.contains("<") ) {
                            cor = cor.replaceFirst("_", "<");
                        }
                        
                        if ( !newFloor[x-1][y].contains(">") ) {
                            //If the next room doesn't already point right to the Exit, make it.
                            newFloor[x-1][y] = newFloor[x-1][y].replaceFirst("_", ">");
                        }
                        
                    } else if ( x < w-1 && path[x+1][y].equals("X") ) {
                        //The next room is to the right of the Exit, make a link on Exit pointing right
                        if ( !cor.contains(">") ) {
                            cor = cor.replaceFirst("_", ">");
                        }
                        
                        if ( !newFloor[x+1][y].contains("<") ) {
                            //If the next room doesn't already point left to the Exit, make it.
                            newFloor[x+1][y] = newFloor[x+1][y].replaceFirst("_", "<");
                        }
                        
                    }
                    
                } else if ( path[x][y].equals("S") ) {
                    //If path[x][y] is the Start, do the same thing as Exit and find the next room to go to
                    //cor = cor + "S";
                    
                    if ( y > 0 && path[x][y-1].equals("X") ) {
                        //The next room is above
                        if ( !cor.contains("^") ) {
                            cor = cor.replaceFirst("_", "^");
                        }
                        
                        if ( !newFloor[x][y-1].contains("v") ) {
                            //Make the room point to Start if it doesn't already
                            newFloor[x][y-1] = newFloor[x][y-1].replaceFirst("_", "v");
                        }
                        
                    } else if ( y < h-1 && path[x][y+1].equals("X") ) {
                        //The next room is below
                        if ( !cor.contains("v") ) {
                            cor = cor.replaceFirst("_", "v");
                        }
                        
                        if ( !newFloor[x][y+1].contains("^") ) {
                            //Make the room point to Start if it doesn't already
                            newFloor[x][y+1] = newFloor[x][y+1].replaceFirst("_", "^");
                        }
                        
                    } else if ( x > 0 && path[x-1][y].equals("X") ) {
                        //The next room is to the left
                        if ( !cor.contains("<") ) {
                            cor = cor.replaceFirst("_", "<");
                        }
                        
                        if ( !newFloor[x-1][y].contains(">") ) {
                            //Make the room point to the Start if it doesn't already
                            newFloor[x-1][y] = newFloor[x-1][y].replaceFirst("_", ">");
                        }
                        
                    } else if ( x < w-1 && path[x+1][y].equals("X") ) {
                        //The next room is to the right
                        if ( !cor.contains(">") ) {
                            cor = cor.replaceFirst("_", ">");
                        }
                        
                        if ( !newFloor[x+1][y].contains("<") ) {
                            //Make the room point to the Start if it doesn't already
                            newFloor[x+1][y] = newFloor[x+1][y].replaceFirst("_", "<");
                        }
                        
                    }
                } else if ( path[x][y].equals("X") ) {
                    //Now if there is any room along the path, make sure it is connected to the other rooms (Other X's, S, and E)
                    if ( y > 0 && (path[x][y-1].equals("X") || path[x][y-1].equals("S") || path[x][y-1].equals("E")) ) {
                        //The room is above
                        if ( !cor.contains("^") ) {
                            cor = cor.replaceFirst("_", "^");
                        }
                        
                        if ( !newFloor[x][y-1].contains("v") ) {
                            newFloor[x][y-1] = newFloor[x][y-1].replaceFirst("_", "v");
                        }
                        
                    }
                    if ( y < h-1 && (path[x][y+1].equals("X") || path[x][y+1].equals("S") || path[x][y+1].equals("E")) ) {
                        //The room is below
                        if ( !cor.contains("v") ) {
                            cor = cor.replaceFirst("_", "v");
                        }
                        
                        if ( !newFloor[x][y+1].contains("^") ) {
                            newFloor[x][y+1] = newFloor[x][y+1].replaceFirst("_", "^");
                        }
                        
                    }
                    if ( x > 0 && (path[x-1][y].equals("X") || path[x-1][y].equals("S") || path[x-1][y].equals("E")) ) {
                        //The room is to the left
                        if ( !cor.contains("<") ) {
                            cor = cor.replaceFirst("_", "<");
                        }
                        
                        if ( !newFloor[x-1][y].contains(">") ) {
                            newFloor[x-1][y] = newFloor[x-1][y].replaceFirst("_", ">");
                        }
                        
                    }
                    if ( x < w-1 && (path[x+1][y].equals("X") || path[x+1][y].equals("S") || path[x+1][y].equals("E")) ) {
                        //The room is to the right
                        if ( !cor.contains(">") ) {
                            cor = cor.replaceFirst("_", ">");
                        }
                        
                        if ( !newFloor[x+1][y].contains("<") ) {
                            newFloor[x+1][y] = newFloor[x+1][y].replaceFirst("_", "<");
                        }
                        
                    }
                }
                
                //Generate more pointers so there isn't just the one path
                if ( floor[x][y].equals("S") ) {
                    //The current room is the Start room.
                    //Find more rooms next to Start and random pointers
                    
                    if ( y > 0 && floor[x][y-1].equals("X") ) {
                        chance = r.nextInt(cc);
                        if ( chance == 0 && !cor.contains("^") ) {
                            cor = cor.replaceFirst("_", "^");
                            if ( !newFloor[x][y-1].contains("v") ) {
                                newFloor[x][y-1] = newFloor[x][y-1].replaceFirst("_", "v");
                            }
                        }
                    }
                    if ( y < h-1 && floor[x][y+1].equals("X") ) {
                        chance = r.nextInt(cc);
                        if ( chance == 0 && !cor.contains("v") ) {
                            cor = cor.replaceFirst("_", "v");
                            if ( !newFloor[x][y+1].contains("^") ) {
                                newFloor[x][y+1] = newFloor[x][y+1].replaceFirst("_", "^");
                            }
                        }
                    }
                    if ( x > 0 && floor[x-1][y].equals("X") ) {
                        chance = r.nextInt(cc);
                        if ( chance == 0 && !cor.contains("<") ) {
                            cor = cor.replaceFirst("_", "<");
                            if ( !newFloor[x-1][y].contains(">") ) {
                                newFloor[x-1][y] = newFloor[x-1][y].replaceFirst("_", ">");
                            }
                        }
                    }
                    if ( x < w-1 && floor[x+1][y].equals("X") ) {
                        chance = r.nextInt(cc);
                        if ( chance == 0 && !cor.contains(">") ) {
                            cor = cor.replaceFirst("_", ">");
                            if ( !newFloor[x+1][y].contains("<") ) {
                                newFloor[x+1][y] = newFloor[x+1][y].replaceFirst("_", "<");
                            }
                        }
                    }
                    
                } else if ( floor[x][y].equals("E") ) {
                    if ( y > 0 && floor[x][y-1].equals("X") ) {
                        chance = r.nextInt(cc);
                        if ( chance == 0 && !cor.contains("^") ) {
                            cor = cor.replaceFirst("_", "^");
                            if ( !newFloor[x][y-1].contains("v") ) {
                                newFloor[x][y-1] = newFloor[x][y-1].replaceFirst("_", "v");
                            }
                        }
                    }
                    if ( y < h-1 && floor[x][y+1].equals("X") ) {
                        chance = r.nextInt(cc);
                        if ( chance == 0 && !cor.contains("v") ) {
                            cor = cor.replaceFirst("_", "v");
                            if ( !newFloor[x][y+1].contains("^") ) {
                                newFloor[x][y+1] = newFloor[x][y+1].replaceFirst("_", "^");
                            }
                        }
                    }
                    if ( x > 0 && floor[x-1][y].equals("X") ) {
                        chance = r.nextInt(cc);
                        if ( chance == 0 && !cor.contains("<") ) {
                            cor = cor.replaceFirst("_", "<");
                            if ( !newFloor[x-1][y].contains(">") ) {
                                newFloor[x-1][y] = newFloor[x-1][y].replaceFirst("_", ">");
                            }
                        }
                    }
                    if ( x < w-1 && floor[x+1][y].equals("X") ) {
                        chance = r.nextInt(cc);
                        if ( chance == 0 && !cor.contains(">") ) {
                            cor = cor.replaceFirst("_", ">");
                            if ( !newFloor[x+1][y].contains("<") ) {
                                newFloor[x+1][y] = newFloor[x+1][y].replaceFirst("_", "<");
                            }
                        }
                    }
                } else if ( floor[x][y].equals("X") ) {
                    if ( y > 0 && floor[x][y-1].equals("X") ) {
                        chance = r.nextInt(cc);
                        if ( chance == 0 && !cor.contains("^") ) {
                            cor = cor.replaceFirst("_", "^");
                            if ( !newFloor[x][y-1].contains("v") ) {
                                newFloor[x][y-1] = newFloor[x][y-1].replaceFirst("_", "v");
                            }
                        }
                    }
                    if ( y < h-1 && floor[x][y+1].equals("X") ) {
                        chance = r.nextInt(cc);
                        if ( chance == 0 && !cor.contains("v") ) {
                            cor = cor.replaceFirst("_", "v");
                            if ( !newFloor[x][y+1].contains("^") ) {
                                newFloor[x][y+1] = newFloor[x][y+1].replaceFirst("_", "^");
                            }
                        }
                    }
                    if ( x > 0 && floor[x-1][y].equals("X") ) {
                        chance = r.nextInt(cc);
                        if ( chance == 0 && !cor.contains("<") ) {
                            cor = cor.replaceFirst("_", "<");
                            if ( !newFloor[x-1][y].contains(">") ) {
                                newFloor[x-1][y] = newFloor[x-1][y].replaceFirst("_", ">");
                            }
                        }
                    }
                    if ( x < w-1 && floor[x+1][y].equals("X") ) {
                        chance = r.nextInt(cc);
                        if ( chance == 0 && !cor.contains(">") ) {
                            cor = cor.replaceFirst("_", ">");
                            if ( !newFloor[x+1][y].contains("<") ) {
                                newFloor[x+1][y] = newFloor[x+1][y].replaceFirst("_", "<");
                            }
                        }
                    }
                }
                
                if ( !cor.equals("____") ) {
                    newFloor[x][y] = cor;
                }
            }
        }
        return newFloor;
    }
    
    public static void main( String[] args ) {
        FloorGen2 flr = new FloorGen2();
        int width = 10;
        int height = 10;
        String[][] floor = flr.createFloor(width, height, width/2 - 1 , height/2 - 1);
        String[][] path = flr.createExitPath(floor, width, height, width/2-1, height/2-1 );
        String[][] cors = flr.corridorCreater(floor, path, width, height);
        
//        int trials = 0;
//        int count = 0;
//        for ( int i = 0; i < 500; i++ ) {
//            floor = flr.createFloor( width, height, width/2-1, height/2-1 );
//            for ( int y = 0; y < height; y++ ) {
//                for ( int x = 0; x < width; x++ ) {
//                    if ( !floor[x][y].equals(" ") ) {
//                        count++;
//                    }
//                }
//            }
//            trials++;
//        }
//        System.out.println("Average Rooms per Floor: " + (count/trials) );
        System.out.println("The Floor (S = Start, E = Exit, X = Room): ");
        int count = 0;
        for ( int y = 0; y < height; y++ ) {
            for ( int x = 0; x < width; x++ ) {
                System.out.print("[" + floor[x][y] + "] ");
                if ( !floor[x][y].equals(" ") ) {
                    count++;
                }
            }
            System.out.println();
        }
        System.out.println("Rooms: " + count);
        System.out.println();
        
        System.out.println("Path from Start to Exit: ");
        for ( int y = 0; y < height; y++ ) {
            for ( int x = 0; x < width; x++ ) {
                System.out.print(" [ " + path[x][y] + " ] ");
            }
            System.out.println();
        }
        System.out.println();
        
        System.out.println("Floor Plan(corridors): ");
        for ( int y = 0; y < height; y++ ) {
            for ( int x = 0; x < width; x++ ) {
                System.out.print("[" + cors[x][y] + "] ");
            }
            System.out.println();
        }
    }
}
