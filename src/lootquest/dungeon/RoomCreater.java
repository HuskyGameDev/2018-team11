package lootquest.dungeon;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class RoomCreater {
    private int GR;
    private int SR;
    private int ER;
    private int rW;
    private int rH;
    public Random r = new Random();
    
    //GR = # of generic rooms
    //SR = # of start rooms
    //EX = # of exit rooms
    public RoomCreater ( int GR, int SR, int ER, int rW, int rH ) {
        this.GR = GR;
        this.SR = SR;
        this.ER = ER;
        this.rW = rW;
        this.rH = rH;
    }
    
    public String[][] createRoom ( boolean UP, boolean DOWN, boolean LEFT, boolean RIGHT ) throws FileNotFoundException {

        String[][] room = new String[rW][rH];
        //Initializing the Room
        room = createEmpty();
        
        //Creating the hallway(s)
        if ( UP == true ) {
            for ( int y = 0; y < rH; y++ ) {
                for ( int x = 0; x < rW; x++ ) {
                    if ( x == ((rW/2) - 2) && y < (rH/2) ) {
                        room[x][y] = "wl";
                    } else if ( x == ((rW/2) + 1) && y < (rH/2) ) {
                        room[x][y] = "wr";
                    }
                     else if ( (x == ((rW/2) - 1) || x == (rW/2)) && y < (rH/2) ) {
                        room[x][y] = "0";
                    }
                }
            } 
        }
        if ( DOWN == true ) {
            for ( int y = 0; y < rH; y++ ) {
                for ( int x = 0; x < rW; x++ ) {
                    if ( x == ((rW/2) - 2) && y > (rH/2) ) {
                        room[x][y] = "wl";
                    } else if ( x == ((rW/2) + 1) && y > (rH/2) ) {
                        room[x][y] = "wr";
                    }
                     else if ( (x == ((rW/2) - 1) || x == (rW/2)) && y > (rH/2) ) {
                        room[x][y] = "0";
                    }
                }
            }
        }
        if ( LEFT == true ) {
            for ( int y = 0; y < rH; y++ ) {
                for ( int x = 0; x < rW; x++ ) {
                    if ( y == ((rH/2) - 2) && x < (rW/2) ) {
                        room[x][y] = "wu";
                    } else if ( y == ((rH/2) + 1) && x < (rW/2) ) {
                        room[x][y] = "wd";
                    }
                     else if ( (y == ((rH/2) - 1) || y == (rH/2)) && x < (rW/2) ) {
                        room[x][y] = "0";
                    }
                }
            } 
        }
        if ( RIGHT == true ) {
            for ( int y = 0; y < rH; y++ ) {
                for ( int x = 0; x < rW; x++ ) {
                    if ( y == ((rH/2) - 2) && x > (rW/2) ) {
                        room[x][y] = "wu";
                    } else if ( y == ((rH/2) + 1) && x > (rW/2) ) {
                        room[x][y] = "wd";
                    }
                     else if ( (y == ((rH/2) - 1) || y == (rH/2)) && x > (rW/2) ) {
                        room[x][y] = "0";
                    }
                }
            } 
        }
        
        //Creating the actual Room
        int xScale = r.nextInt(5) - 2;
        int yScale = r.nextInt(5) - 2;
        
        int Xmin = 2 - xScale;
        int Ymin = 2 - yScale;
        int Xmax = (rW-2) + xScale;
        int Ymax = (rH-2) + yScale;
        
        for ( int y = Ymin; y < Ymax; y++ ) {
            for ( int x = Xmin; x < Xmax ; x++ ) {
                if ( x == Xmin && y == Ymin ) {
                    if ( room[x][y].equals("0") ) {
                        room[x][y] = "9";
                    } else {
                        room[x][y] = "citl";
                    }
                } else if ( x == Xmax-1 && y == Ymin ) {
                    if ( room[x][y].equals("0") ) {
                        room[x][y] = "9";
                    } else {
                        room[x][y] = "citr";
                    }
                } else if ( x == Xmin && y == Ymax-1 ) {
                    if ( room[x][y].equals("0") ) {
                        room[x][y] = "9";
                    } else {
                        room[x][y] = "cibl";
                    }
                } else if ( x == Xmax-1 && y == Ymax-1 ) {
                    if ( room[x][y].equals("0") ) {
                        room[x][y] = "9";
                    } else {
                        room[x][y] = "cibr";
                    }
                } else if ( y == Ymin ) {
                    if ( room[x][y].equals("0") ) {
                        room[x][y] = "D";
                    } else {
                        room[x][y] = "wu";
                    }
                } else if ( y == Ymax-1 ) {
                    if ( room[x][y].equals("0") ) {
                        room[x][y] = "D";
                    } else {
                        room[x][y] = "wd";
                    }
                } else if (x == Xmin ) {
                    if ( room[x][y].equals("0") ) {
                        room[x][y] = "D";
                    } else {
                        room[x][y] = "wl";
                    }
                } else if ( x == Xmax-1 ) {
                    if ( room[x][y].equals("0") ) {
                        room[x][y] = "D";
                    } else {
                        room[x][y] = "wr";
                    }
                } else if ( x <= (Xmax-4) && x >= (Xmin+3) && y <= (Ymax-4) && (y >= Ymin+3)  ) {
                    room[x][y] = "1";
                } else {
                    room[x][y] = "0";
                }
            }
        }
        
        //Finding doors and putting outer corner walls
        for ( int y = Ymin; y < Ymax; y++ ) {
            for ( int x = Xmin; x < Xmax; x++ ) {
                if ( room[x][y].equals("D") ) {
                    if ( y == Ymin ) {
                        if ( room[x-1][y].equals("wu") ) {
                            room[x-1][y] = "cobr";
                        } else if ( room[x+1][y] == "wu") {
                            room[x+1][y] = "cobl";
                        }
                    } else if ( y == Ymax-1 ) {
                        if ( room[x-1][y].equals("wd") ) {
                            room[x-1][y] = "cotr";
                        } else if ( room[x+1][y] == "wd") {
                            room[x+1][y] = "cotl";
                        }
                    } else if ( x == Xmin ) {
                        if ( room[x][y-1].equals("wl") ) {
                            room[x][y-1] = "cobr";
                        } else if ( room[x][y+1] == "wl") {
                            room[x][y+1] = "cotr";
                        }
                    } else if ( x == Xmax-1 ) {
                        if ( room[x][y-1].equals("wr") ) {
                            room[x][y-1] = "cobl";
                        } else if ( room[x][y+1] == "wr") {
                            room[x][y+1] = "cotl";
                        }
                    }
                }
            }
        }
        
        return room;
    }
    
    public String[][] createStart( boolean UP, boolean DOWN, boolean LEFT, boolean RIGHT ) throws FileNotFoundException {
        String[][] room = createRoom( UP, DOWN, LEFT, RIGHT );
        boolean entrance = false;
        int chance = 0;
        while ( entrance == false ) {
            for ( int y = 0; y < rH; y++ ) {
                for ( int x = 0; x < rW; x++ ) {
                    if ( room[x][y].equals("1") && entrance == false ) {
                        chance = r.nextInt(15);
                        if ( chance == 0 ) {
                            room[x][y] = "S";
                            entrance = true;
                        }
                    }
                }
            }
        }
        
        return room;
    }
    
    public String[][] createExit( boolean UP, boolean DOWN, boolean LEFT, boolean RIGHT ) throws FileNotFoundException {
        String[][] room = createRoom( UP, DOWN, LEFT, RIGHT );
        boolean exit = false;
        int chance = 0;
        while ( exit == false ) {
            for ( int y = 0; y < rH; y++ ) {
                for ( int x = 0; x < rW; x++ ) {
                    if ( room[x][y].equals("1") && exit == false ) {
                        chance = r.nextInt(15);
                        if ( chance == 0 ) {
                            room[x][y] = "E";
                            exit = true;
                        }
                    }
                }
            }
        }
        
        return room;
    }
    
    public String[][] createEmpty() {
        String[][] room = new String[rW][rH];
        for ( int y = 0; y < rH; y++ ) {
            for ( int x = 0; x < rW; x++ ) {
                room[x][y] = "e";
            }
        }
        return room;
    }
    
    public String [] loadRoom ( String fileName ) throws FileNotFoundException {
        File f = new File("/Users/Kevin/eclipse-workspace/Test/Rooms/"+ fileName + ".txt");
        Scanner scan = new Scanner(f);
        
        String name = scan.next();
        int width = Integer.parseInt(scan.next());
        int height = Integer.parseInt(scan.next());
        
        String [] room = new String [ 3 + (width * height)];
        room[0] = name;
        room[1] = "" + width;
        room[2] = "" + height;
        
        String tile;
        int counter = 3;
        
        while (scan.hasNext() ) {
            tile = scan.next();
            room[counter] = tile;
            counter++;
        }
        
        scan.close();
        return room;
    }
    
    public void roomTranslate( String[] roomInfo, String[][] room ) {
        int index = 3;
        for ( int y = 63; y >= 0; y-- ) {
            for ( int x = 0; x < 64; x++ ) {
                if ( index < roomInfo.length ) {
                    room[x][y] = roomInfo[index];
                    index++;
                } else {
                    room[x][y] = "e";
                }
            }
        }
    }
    
    public String[][] createRoomSide () throws FileNotFoundException {
      String[][] room = new String[16][16];
      
      for ( int y = 0; y < 16; y++ ) {
          for ( int x = 0; x < 16; x++ ) {
              if ( x == 15 && ( y== 7 || y == 8 ) ) {
                  room[x][y] = "0";
              }else if ( y == 0 || y == 15 ) {
                  room[x][y] = "1";
              }else if (x == 0 || x == 15) {
                  room[x][y] = "1";
              } else {
                  room[x][y] = "0";
              }
          }
      }
      return room;
  }
    
    public String[][] createRoomUp () throws FileNotFoundException {
        String[][] room = new String[16][16];
        
        for ( int y = 0; y < 16; y++ ) {
            for ( int x = 0; x < 16; x++ ) {
                if ( (x == 7 || x == 8) && y == 0 ) {
                    room[x][y] = "0";
                }else if ( y == 0 || y == 15 ) {
                    room[x][y] = "1";
                }else if (x == 0 || x == 15) {
                    room[x][y] = "1";
                } else {
                    room[x][y] = "0";
                }
            }
        }
        
        return room;
    }
    
    public String[][] createCorridor () {
        String[][] corr = new String[16][16];
        
        for ( int y = 0; y < 16; y++ ) {
            for ( int x = 0; x < 16; x++ ) {
                if ( (x == 6 && y == 8) || ( x == 6 && y == 7 ) ) {
                    corr[x][y] = "0";
                } else if ( x == 9 && y > 6 ) {
                    corr[x][y] = "1";
                } else if ( x == 6 && y > 6 ) {
                    corr[x][y] = "1";
                } else if ( y == 6 && x < 10 ) {
                    corr[x][y] = "1";
                } else if ( y == 9 && x < 7 ) {
                    corr[x][y] = "1";
                } else {
                    corr[x][y] = "0";
                }
            }
        }
        
        return corr;
    }
}
