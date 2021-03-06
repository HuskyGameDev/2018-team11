package lootquest.system;

import lootquest.EntityFactory;
import lootquest.component.Direction;
import lootquest.component.EquipedCrossbow;
import lootquest.component.Movement;
import lootquest.component.Player;
import lootquest.component.Position;
import lootquest.component.Size;
import lutebox.core.Lutebox;
import lutebox.ecs.Entity;
import lutebox.ecs.Filter;
import lutebox.ecs.IteratingEntitySystem;

public class UseRangedSystem extends IteratingEntitySystem{
	
	public UseRangedSystem() {
		super(Filter.include(Position.class, Size.class, Direction.class, EquipedCrossbow.class).create());
	}
	
	public void updateEntity(Entity e) {
        EquipedCrossbow crossbow = e.get(EquipedCrossbow.class); 
        
        spawnProjectile(e); 
        crossbow.addTime(Lutebox.deltaTime);
        if (e.get(Player.class) != null) {
        	crossbow.addTime(Lutebox.deltaTime * 2);
        }
        
        //arrow.addTime(Lutebox.deltaTime); 
        
        
    }
	
	public void spawnProjectile(Entity e) {
		EquipedCrossbow crossbow = e.get(EquipedCrossbow.class);
		
		Direction dir = e.get(Direction.class);
        Position pos = e.get(Position.class);
        Movement mov = e.get(Movement.class);
		
		if (crossbow.shouldFire()) {//crossbow.shouldFire()) {
			
//			System.out.println("UseRangedSystem: Firing arrow from " + e);
			
//			if(dir.moving) {
//				EntityFactory.createArrow(e, pos.x + mov.getDx(), pos.y + mov.getDy(), mov.maxSpeed * mov.getDx(), mov.maxSpeed * mov.getDy());
//			}else {
				if(dir.direction == Direction.UP) {
					EntityFactory.createArrow(e, pos.x, pos.y, 0, -mov.maxSpeed);
				}else if(dir.direction == Direction.DOWN) {
					EntityFactory.createArrow(e, pos.x, pos.y, 0, mov.maxSpeed);
				}else if(dir.direction == Direction.LEFT) {
					EntityFactory.createArrow(e, pos.x, pos.y, -mov.maxSpeed, 0);
				}else if(dir.direction == Direction.RIGHT) {
					EntityFactory.createArrow(e, pos.x, pos.y, mov.maxSpeed, 0);
				}
//			}k
		}
	}
}
