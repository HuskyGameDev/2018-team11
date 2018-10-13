package lootquest.component;

import lutebox.ecs.Component;

public class Direction extends Component{
    public static final int UP = 0; 
    public static final int DOWN = 1; 
    public static final int LEFT = 2; 
    public static final int RIGHT = 3; 
    
	public int direction = DOWN;
	public boolean moving = false; 

	public float speed = 3;
}
