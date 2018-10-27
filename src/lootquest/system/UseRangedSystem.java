package lootquest.system;

import lootquest.EntityFactory;
import lootquest.component.Direction;
import lootquest.component.EquipedCrossbow;
import lootquest.component.Movement;
import lootquest.component.Position;
import lootquest.component.Size;
import lutebox.ecs.Entity;
import lutebox.ecs.Filter;
import lutebox.ecs.IteratingEntitySystem;

public class UseRangedSystem extends IteratingEntitySystem{
	
	public UseRangedSystem() {
		super(Filter.include(Position.class, Size.class, Direction.class, EquipedCrossbow.class).create());
	}
	
	public void updateEntity(Entity e) {
        EquipedCrossbow crossbow = e.get(EquipedCrossbow.class); 
        
        //arrow.addTime(Lutebox.deltaTime); 
    }
	
	public void spawnProjectile(Entity e) {
		EquipedCrossbow crossbow = e.get(EquipedCrossbow.class);
		
		Direction dir = e.get(Direction.class);
        Position pos = e.get(Position.class);
        Movement mov = e.get(Movement.class);
		
        
        
		if (crossbow.shouldShowAnimation()) {
			
			if(dir.moving) {
				EntityFactory.createArrow(pos.x + mov.getDx(), pos.y + mov.getDy(), mov.maxSpeed * mov.getDx(), mov.maxSpeed * mov.getDy());
			}else {
				if(dir.direction == Direction.UP) {
					EntityFactory.createArrow(pos.x, pos.y, 0, -mov.maxSpeed);
				}else if(dir.direction == Direction.DOWN) {
					EntityFactory.createArrow(pos.x, pos.y, 0, mov.maxSpeed);
				}else if(dir.direction == Direction.LEFT) {
					EntityFactory.createArrow(pos.x, pos.y, -mov.maxSpeed, 0);
				}else if(dir.direction == Direction.RIGHT) {
					EntityFactory.createArrow(pos.x, pos.y, mov.maxSpeed, 0);
				}
			}
		}
	}
}
