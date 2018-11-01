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
                } else if ( x < (Xmax) && x >= (Xmin) && y < (Ymax) && (y >= Ymin)  ) {
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
    
    public String[][] createCorridor( boolean UP, boolean DOWN, boolean LEFT, boolean RIGHT ) {
        String[][] room = new String[rW][rH];
        room = createEmpty();
        
        if ( UP == true ) {
            for ( int y = 0; y < rH; y++ ) {
                for ( int x = 0; x < rW; x++ ) {
                    if ( y >= 0 && y <= (rH/2)-3 ) {
                        if ( x == (rW/2)-2 ) {
                            room[x][y] = "wl";
                        } else if ( x == (rW/2) + 1 ) {
                            room[x][y] = "wr";
                        } else if ( x == (rW/2)-1 || x == (rW/2) ) {
                            room[x][y] = "0";
                        }
                    } else if ( y == (rH/2)-2 ) {
                        if ( x == (rW/2)-1 || x == (rW/2) ) {
                            room[x][y] = "wd";
                        } else if ( x == (rW/2)-2 ) {
                            room[x][y] = "cibl";
                        } else if ( x == (rW/2)+1 ) {
                            room[x][y] = "cibr";
                        }
                    }
                }
            }
        }
        if ( DOWN == true ) {
            for ( int y = 0; y < rH; y++ ) {
                for ( int x = 0; x < rW; x++ ) {
                    if ( y < rH && y > (rH/2)+1 && y != (rH/2)+1 ) {
                        if ( x == (rW/2)-2 ) {
                            room[x][y] = "wl";
                        } else if ( x == (rW/2) + 1 ) {
                            room[x][y] = "wr";
                        } else if ( x == (rW/2)-1 || x == (rW/2) ) {
                            room[x][y] = "0";
                        }
                    } else if ( y == (rH/2)+1 ) {
                        if ( x == (rW/2)-1 || x == (rW/2) ) {
                            room[x][y] = "wu";
                        } else if ( x == (rW/2)-2 ) {
                            room[x][y] = "citl";
                        } else if ( x == (rW/2)+1 ) {
                            room[x][y] = "citr";
                        }
                    }
                }
            }
        }
        if ( LEFT == true ) {
            for ( int y = 0; y < rH; y++ ) {
                for ( int x = 0; x < rW; x++ ) {
                    if ( x >= 0 && x <= (rW/2)-3 ) {
                        if ( y == (rH/2)-2 ) {
                            room[x][y] = "wu";
                        } else if ( y == (rH/2) + 1 ) {
                            room[x][y] = "wd";
                        } else if ( y == (rH/2)-1 || y == (rH/2) ) {
                            room[x][y] = "0";
                        }
                    } else if ( x == (rW/2)-2 ) {
                        if ( y == (rH/2)-1 || y == (rH/2) ) {
                            room[x][y] = "wr";
                        } else if ( y == (rH/2)-2 ) {
                            room[x][y] = "citr";
                        } else if ( y == (rH/2)+1 ) {
                            room[x][y] = "cibr";
                        }
                    }
                }
            }
        }
        if ( RIGHT == true ) {
            for ( int y = 0; y < rH; y++ ) {
                for ( int x = 0; x < rW; x++ ) {
                    if ( x < rW && x >= (rW/2)+2 ) {
                        if ( y == (rH/2)-2 ) {
                            room[x][y] = "wu";
                        } else if ( y == (rH/2) + 1 ) {
                            room[x][y] = "wd";
                        } else if ( y == (rH/2)-1 || y == (rH/2) ) {
                            room[x][y] = "0";
                        }
                    } else if ( x == (rW/2)+1 ) {
                        if ( y == (rH/2)-1 || y == (rH/2) ) {
                            room[x][y] = "wl";
                        } else if ( y == (rH/2)-2 ) {
                            room[x][y] = "citl";
                        } else if ( y == (rH/2)+1 ) {
                            room[x][y] = "cibl";
                        }
                    }
                }
            }
        }
        
        //Clean up
        int yMin = (rH/2)-2;
        int yMax = (rH/2)+1;
        int xMin = (rW/2)-2;
        int xMax = (rW/2)+1;
        if ( UP && DOWN && !LEFT && !RIGHT ) {
            //Up and Down
            for ( int y = yMin; y <= yMax; y++ ) {
                for ( int x = xMin; x <= xMax; x++ ) {
                    if ( x == xMin ) {
                        room[x][y] = "wl";
                    } else if ( x == xMax ) {
                        room[x][y] = "wr";
                    } else {
                        room[x][y] = "0";
                    }
                }
            }
        } else if ( UP && !DOWN && !LEFT && RIGHT ) {
            //Up and Right
            for ( int y = yMin; y <= yMax; y++ ) {
                for ( int x = xMin; x <= xMax; x++ ) {
                    if ( y == yMax && x > xMin ) {
                        room[x][y] = "wd";
                    } else if ( y == yMax && x == xMin ) {
                        room[x][y] = "cibl";
                    } else if ( x == xMin ) {
                        room[x][y] = "wl";
                    } else if ( x == xMax && y == yMin ) {
                        room[x][y] = "cobl";
                    } else {
                        room[x][y] = "0";
                    }
                }
            }
        } else if ( UP && !DOWN && LEFT && !RIGHT ) {
            //Up and Left
            for ( int y = yMin; y <= yMax; y++ ) {
                for ( int x = xMin; x <= xMax; x++ ) {
                    if ( y == yMax && x < xMax ) {
                        room[x][y] = "wd";
                    } else if ( y == yMax && x == xMax ) {
                        room[x][y] = "cibr";
                    } else if ( x == xMax ) {
                        room[x][y] = "wr";
                    } else if ( x == xMin && y == yMin ) {
                        room[x][y] = "cobr";
                    } else {
                        room[x][y] = "0";
                    }
                }
            }
        } else if ( !UP && DOWN && !LEFT && RIGHT ) {
            //Down and Right
            for ( int y = yMin; y <= yMax; y++ ) {
                for ( int x = xMin; x <= xMax; x++ ) {
                    if ( y == yMin && x > xMin ) {
                        room[x][y] = "wu";
                    } else if ( y == yMin && x == xMin ) {
                        room[x][y] = "citl";
                    } else if ( x == xMin ) {
                        room[x][y] = "wl";
                    } else if ( x == xMax && y == yMax ) {
                        room[x][y] = "cotl";
                    } else {
                        room[x][y] = "0";
                    }
                }
            }
        } else if ( !UP && DOWN && LEFT && !RIGHT ) {
            //Down and Left
            for ( int y = yMin; y <= yMax; y++ ) {
                for ( int x = xMin; x <= xMax; x++ ) {
                    if ( y == yMin && x < xMax ) {
                        room[x][y] = "wu";
                    } else if ( y == yMin && x == xMax ) {
                        room[x][y] = "citr";
                    } else if ( x == xMax ) {
                        room[x][y] = "wr";
                    } else if ( x == xMin && y == yMax ) {
                        room[x][y] = "cotr";
                    } else {
                        room[x][y] = "0";
                    }
                }
            }
        } else if ( !UP && !DOWN && LEFT && RIGHT ) {
            //Left and Right
            for ( int y = yMin; y <= yMax; y++ ) {
                for ( int x = xMin; x <= xMax; x++ ) {
                    if ( y == yMin ) {
                        room[x][y] = "wu";
                    } else if ( y == yMax ) {
                        room[x][y] = "wd";
                    } else {
                        room[x][y] = "0";
                    }
                }
            }
        }  else if ( UP && DOWN && LEFT && !RIGHT ) {
            //Up and Down and Left
            for ( int y = yMin; y <= yMax; y++ ) {
                for ( int x = xMin; x <= xMax; x++ ) {
                    if ( x == xMax ) {
                        room[x][y] = "wr";
                    } else if ( x == xMin && y == yMin ) {
                        room[x][y] = "cobr";
                    } else if ( x == xMin && y == yMax ) {
                        room[x][y] = "cotr";
                    } else {
                        room[x][y] = "0";
                    }
                }
            }
        }  else if ( UP && DOWN && !LEFT && RIGHT ) {
            //Up and Down and Right
            for ( int y = yMin; y <= yMax; y++ ) {
                for ( int x = xMin; x <= xMax; x++ ) {
                    if ( x == xMin ) {
                        room[x][y] = "wl";
                    } else if ( x == xMax && y == yMin ) {
                        room[x][y] = "cobl";
                    } else if ( x == xMax && y == yMax ) {
                        room[x][y] = "cotl";
                    } else {
                        room[x][y] = "0";
                    }
                }
            }
        } else if ( UP && !DOWN && LEFT && RIGHT ) {
            //Up and Left and Right
            for ( int y = yMin; y <= yMax; y++ ) {
                for ( int x = xMin; x <= xMax; x++ ) {
                    if ( y == yMax ) {
                        room[x][y] = "wd";
                    } else if ( x == xMin && y == yMin ) {
                        room[x][y] = "cobr";
                    } else if ( x == xMax && y == yMin ) {
                        room[x][y] = "cobl";
                    } else {
                        room[x][y] = "0";
                    }
                }
            }
        } else if ( !UP && DOWN && LEFT && RIGHT ) {
            //Down and Left and Right
            for ( int y = yMin; y <= yMax; y++ ) {
                for ( int x = xMin; x <= xMax; x++ ) {
                    if ( y == yMin ) {
                        room[x][y] = "wu";
                    } else if ( x == xMin && y == yMax ) {
                        room[x][y] = "cotr";
                    } else if ( x == xMax && y == yMax ) {
                        room[x][y] = "cotl";
                    } else {
                        room[x][y] = "0";
                    }
                }
            }
        } else if ( UP && DOWN && LEFT && RIGHT ) {
            //CrossRoads
            for ( int y = yMin; y <= yMax; y++ ) {
                for ( int x = xMin; x <= xMax; x++ ) {
                    if ( y == yMin && x == xMin ) {
                        room[x][y] = "cobr";
                    } else if ( x == xMin && y == yMax ) {
                        room[x][y] = "cotr";
                    } else if ( x == xMax && y == yMax ) {
                        room[x][y] = "cotl";
                    } else if ( x == xMax && y == yMin ) {
                        room[x][y] = "cobl";
                    } else {
                        room[x][y] = "0";
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
}
