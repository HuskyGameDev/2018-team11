package lootquest.system;

import lootquest.component.Direction;
import lootquest.component.Movement;
import lutebox.ecs.Entity;
import lutebox.ecs.Filter;
import lutebox.ecs.IteratingEntitySystem;

public class UpdateFromMovementSystem extends IteratingEntitySystem {

	public UpdateFromMovementSystem() {
		super(Filter.include(Direction.class, Movement.class).create()); 
	}
	
	public void preUpdateEntities() {
		System.out.println("Start");
	}
	
	public void updateEntity(Entity e) {
		Direction d = e.get(Direction.class); 
		Movement m = e.get(Movement.class); 
		
		if (!d.updateFromMovement) return; 
		
		if (m.getSpeed() > 0) {
			d.moving = true; 
			
			if (Math.abs(m.getDx()) > Math.abs(m.getDy())) {
				if (m.getDx() < 0) {
					d.direction = Direction.LEFT; 
					System.out.println("left");
				}
				else {
					d.direction = Direction.RIGHT;
					System.out.println("right");
				}
			}
			else {
				if (m.getDy() < 0) {
					d.direction = Direction.UP; 
					System.out.println("up");
				}
				else {
					d.direction = Direction.DOWN;
					System.out.println("down"); 
				}
			}
		}
		else {
			d.moving = false; 
			System.out.println("not moving");
		}
	}
	
}
