package lootquest.system;

import lootquest.component.Direction;
import lootquest.component.Player;
import lootquest.component.Position;
import lutebox.core.Lutebox;
import lutebox.ecs.Entity;
import lutebox.ecs.Filter;
import lutebox.ecs.IteratingEntitySystem;
import lutebox.input.Input;



public class PlayerMovementSystem extends IteratingEntitySystem
{
	public PlayerMovementSystem()
	{
		super(Filter.include(Position.class, Direction.class, Player.class).create());
	}
	
	public void updateEntity(Entity e)
	{
	    Direction d = e.get(Direction.class); 
	    
	    d.moving = false; 
	    
		if(Lutebox.input.getKey(Input.KEY_W) || Lutebox.input.getKey(Input.KEY_I)) {
		    d.direction = Direction.UP;
		    d.moving = true; 
		}
        if(Lutebox.input.getKey(Input.KEY_S) || Lutebox.input.getKey(Input.KEY_K)) {
            d.direction = Direction.DOWN;
            d.moving = true; 
        }
        if(Lutebox.input.getKey(Input.KEY_A) || Lutebox.input.getKey(Input.KEY_J)) {
            d.direction = Direction.LEFT;
            d.moving = true; 
        }
        if(Lutebox.input.getKey(Input.KEY_D) || Lutebox.input.getKey(Input.KEY_L)) {
            d.direction = Direction.RIGHT;
            d.moving = true; 
        }
        
        move(e);
	}
	
	public void move(Entity e)
	{
		Position p = e.get(Position.class);
		Direction d = e.get(Direction.class); 
		
		if (!d.moving) return; 
		
		int direction = d.direction; 
		
		float speed = 3; 
		
		if(direction == Direction.UP)
		{
			p.set(p.x, (p.y) - speed * Lutebox.deltaTime);
		}
		
		if(direction == Direction.DOWN)
		{
			p.set(p.x, (p.y) + speed * Lutebox.deltaTime);
		}
		
		if(direction == Direction.RIGHT)
		{
			p.set((p.x) + speed * Lutebox.deltaTime, p.y);
		}
		
		if(direction == Direction.LEFT)
		{
			p.set((p.x) - speed * Lutebox.deltaTime, p.y);
		}
	}
	
}
