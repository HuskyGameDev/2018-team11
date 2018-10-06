package lootquest.system;

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
		super(Filter.include(Position.class, Player.class).create());
	}
	
	public void updateEntity(Entity e)
	{
		if(Lutebox.input.getKey(Input.KEY_W))
           direction = up;
        if(Lutebox.input.getKey(Input.KEY_S))
           direction = down;
        if(Lutebox.input.getKey(Input.KEY_A))
           direction = right;
        if(Lutebox.input.getKey(Input.KEY_S))
           direction = left;
        
        move(e);
	}
	
	public void move(Entity e)
	{
		Position p = e.get(Position.class);
		if(direction == up)
		{
			p.set(p.x, (p.y) + 1 * Lutebox.deltaTime);
		}
		
		if(direction == down)
		{
			p.set(p.x, (p.y) - 1 * Lutebox.deltaTime);
		}
		
		if(direction == right)
		{
			p.set((p.x) + 1 * Lutebox.deltaTime, p.y);
		}
		
		if(direction == left)
		{
			p.set((p.x) - 1 * Lutebox.deltaTime, p.y);
		}
	}
	
	
	public int direction = 0;
	public static int up = 1;
	public static int down = 2;
	public static int right = 3;
	public static int left = 4;
}
