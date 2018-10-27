package lootquest.system;

import lootquest.component.Direction;
import lootquest.component.Player;
import lootquest.component.Position;
import lutebox.core.Lutebox;
import lutebox.ecs.Entity;
import lutebox.ecs.Filter;
import lutebox.ecs.IteratingEntitySystem;
import lutebox.input.Input;



public class MovementSystem extends IteratingEntitySystem
{
	public MovementSystem()
	{
		super(Filter.include(Position.class, Direction.class).create());
	}
	
	public void updateEntity(Entity e)
	{
	    Direction d = e.get(Direction.class); 
	    Player pl = e.get(Player.class);
	     
	    
	    if(pl != null)
	    {
	    	d.moving = false;
		  if(Lutebox.input.getKey(Input.KEY_W) || Lutebox.input.getKey(Input.KEY_I) || Lutebox.input.getKey(Input.KEY_UP)) {
		    d.direction = Direction.UP;
		    d.moving = true; 
		  }
          if(Lutebox.input.getKey(Input.KEY_S) || Lutebox.input.getKey(Input.KEY_K) || Lutebox.input.getKey(Input.KEY_DOWN)) {
            d.direction = Direction.DOWN;
            d.moving = true; 
          }
          if(Lutebox.input.getKey(Input.KEY_A) || Lutebox.input.getKey(Input.KEY_J) || Lutebox.input.getKey(Input.KEY_LEFT)) {
            d.direction = Direction.LEFT;
            d.moving = true; 
          }
          if(Lutebox.input.getKey(Input.KEY_D) || Lutebox.input.getKey(Input.KEY_L) || Lutebox.input.getKey(Input.KEY_RIGHT)) {
            d.direction = Direction.RIGHT;
            d.moving = true; 
          }
	    }
	  
	  if(d.moving)
		move(e);
	}
	
	public void move(Entity e)
	{
		Position p = e.get(Position.class);
		Direction d = e.get(Direction.class); 
		
		if (!d.moving) return; 
		
		int direction = d.direction; 
		 
		if(direction == Direction.UP)
		{
			p.set(p.x, (p.y) - d.speed * Lutebox.deltaTime);
		}
		
		if(direction == Direction.DOWN)
		{
			p.set(p.x, (p.y) + d.speed * Lutebox.deltaTime);
		}
		
		if(direction == Direction.RIGHT)
		{
			p.set((p.x) + d.speed * Lutebox.deltaTime, p.y);
		}
		
		if(direction == Direction.LEFT)
		{
			p.set((p.x) - d.speed * Lutebox.deltaTime, p.y);
		}
	}
	
}
