package lootquest.system;

import lootquest.component.Position;
import lootquest.component.Movement;
import lutebox.core.Lutebox;
import lutebox.ecs.Entity;
import lutebox.ecs.Filter;
import lutebox.ecs.IteratingEntitySystem;



public class MovementSystem extends IteratingEntitySystem
{
	public MovementSystem()
	{
		super(Filter.include(Position.class, Movement.class).create());
	}
	
	public void updateEntity(Entity e)
	{
		Position p = e.get(Position.class); 
	    Movement v = e.get(Movement.class); 
	    
	    p.x += v.getDx() * Lutebox.deltaTime; 
	    p.y += v.getDy() * Lutebox.deltaTime; 
	}
	
}
