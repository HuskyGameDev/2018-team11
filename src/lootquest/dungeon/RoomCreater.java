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
                    if ( (x == ((rW/2) - 1) || x == ((rW/2) + 1)) && y > (rH/2) ) {
                        room[x][y] = "1";
                    } else if ( x == (rW/2) && y > (rH/2) ) {
                        room[x][y] = "0";
                    }
                }
            } 
        }
        if ( DOWN == true ) {
            for ( int y = 0; y < rH; y++ ) {
                for ( int x = 0; x < rW; x++ ) {
                    if ( (x == ((rW/2) - 1) || x == ((rW/2) + 1)) && y < (rH/2) ) {
                        room[x][y] = "1";
                    } else if ( x == (rW/2) && y < (rH/2) ) {
                        room[x][y] = "0";
                    }
                }
            } 
        }
        if ( LEFT == true ) {
            for ( int y = 0; y < rH; y++ ) {
                for ( int x = 0; x < rW; x++ ) {
                    if ( (y == ((rH/2) - 1) || y == ((rH/2) + 1)) && x < (rW/2) ) {
                        room[x][y] = "1";
                    } else if ( y == (rH/2) && x < (rW/2) ) {
                        room[x][y] = "0";
                    }
                }
            } 
        }
        if ( RIGHT == true ) {
            for ( int y = 0; y < rH; y++ ) {
                for ( int x = 0; x < rW; x++ ) {
                    if ( (y == ((rH/2) - 1) || y == ((rH/2) + 1)) && x > (rW/2) ) {
                        room[x][y] = "1";
                    } else if ( y == (rH/2) && x > (rW/2) ) {
                        room[x][y] = "0";
                    }
                }
            } 
        }
        
        //Creating the actual Room
        int xScale = r.nextInt(7) - 3;
        int yScale = r.nextInt(7) - 3;
        
        int Xmin = 3 - xScale;
        int Ymin = 3 - yScale;
        int Xmax = (rW-3) + xScale;
        int Ymax = (rH-3) + yScale;
        
        for ( int y = Ymin; y < Ymax; y++ ) {
            for ( int x = Xmin; x < Xmax ; x++ ) {
                if ( y == Ymin || y == Ymax-1 ) {
                    if ( room[x][y].equals("0") ) {
                        room[x][y] = "D";
                    } else {
                        room[x][y] = "1";
                    }
                }else if (x == Xmin || x == Xmax-1 ) {
                    if ( room[x][y].equals("0") ) {
                        room[x][y] = "D";
                    } else {
                        room[x][y] = "1";
                    }
                } else {
                    room[x][y] = "0";
                }
            }
        }
        
        return room;
    }
    
    public String[][] createStart() throws FileNotFoundException {
        int roomNum = r.nextInt(SR) + 1;
        String[] roomInfo = loadRoom("assets/Rooms/Generic/Room" + roomNum + ".txt");
        String[][] room = new String[64][64];
        
        roomTranslate( roomInfo, room );
        
        return room;
    }
    
    public String[][] createExit() throws FileNotFoundException {
        int roomNum = r.nextInt(ER) + 1;
        String[] roomInfo = loadRoom("assets/Rooms/Generic/Room" + roomNum + ".txt");
        String[][] room = new String[64][64];
        
        roomTranslate( roomInfo, room );
        
        return room;
    }
    
    public String[][] createEmpty() {
        String[][] room = new String[16][16];
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
