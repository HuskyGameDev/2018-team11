package lootquest.system;

import lootquest.component.Direction;
import lootquest.component.Player;
import lootquest.component.Movement;
import lutebox.core.Lutebox;
import lutebox.ecs.Entity;
import lutebox.ecs.Filter;
import lutebox.ecs.IteratingEntitySystem;
import lutebox.input.Input;

public class PlayerInputSystem extends IteratingEntitySystem {

	public PlayerInputSystem() {
		super(Filter.include(Player.class, Direction.class).create()); 
	}
	
	public void updateEntity(Entity e) {
		Direction d = e.get(Direction.class); 
		Movement v = e.get(Movement.class);
		
		d.moving = false;
		
		float dx = 0; 
		float dy = 0; 
		float s = v.maxSpeed; 
		
		if (Lutebox.input.getKey(Input.KEY_A) || Lutebox.input.getKey(Input.KEY_J)) {
			d.direction = Direction.LEFT;
			dx -= s; 
			d.moving = true;
		}
		if (Lutebox.input.getKey(Input.KEY_D) || Lutebox.input.getKey(Input.KEY_L)) {
			d.direction = Direction.RIGHT;
			dx += s; 
			d.moving = true;
		}
		if (Lutebox.input.getKey(Input.KEY_W) || Lutebox.input.getKey(Input.KEY_I)) {
			d.direction = Direction.UP;
			dy -= s; 
			d.moving = true;
		}
		if (Lutebox.input.getKey(Input.KEY_S) || Lutebox.input.getKey(Input.KEY_K)) {
			d.direction = Direction.DOWN;
			dy += s; 
			d.moving = true;
		}
		
		v.set(dx, dy); 
	}
	
}
