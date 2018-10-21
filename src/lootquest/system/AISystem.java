package lootquest.system;

import java.util.List;

import lootquest.component.AI;
import lootquest.component.Enemy;
import lootquest.component.EquipedSword;
import lootquest.component.Health;
import lootquest.component.Movement;
import lootquest.component.Player;
import lootquest.component.Position;
import lootquest.component.Size;
import lutebox.ecs.Entity;
import lutebox.ecs.Filter;
import lutebox.ecs.IteratingEntitySystem;

public class AISystem extends IteratingEntitySystem{

	public AISystem() {
		super(Filter.include(Position.class, Health.class, Enemy.class, AI.class, Size.class, Movement.class, EquipedSword.class).create());
	}
	
	public void updateEntity(Entity e) {
		Filter playerSearch = Filter.include(Position.class,Player.class).create();
		List<Entity> players = getScene().getEntities(playerSearch);
		Entity player = players.get(0);
		
		Position enemyPos = e.get(Position.class);
		Position playerPos = player.get(Position.class);
		Movement enemyMov = e.get(Movement.class);
		Size playerSize = player.get(Size.class);
		EquipedSword armed = e.get(EquipedSword.class);
		AI bob = e.get(AI.class);
		
		float xDiff = playerPos.x - enemyPos.x;
		float yDiff = playerPos.y - enemyPos.y;
		bob.counterCur--;
		
		if(Math.abs(xDiff) > 4 || Math.abs(yDiff) > 4 || bob.counterCur < 0 || bob.counterCur > 35) {
			if(bob.counterCur < 0) {
				enemyMov.set((float) (Math.random()*4 - 2), (float) (Math.random()*4 - 2));
				bob.counterCur = bob.counterMax;
			}
		}else if (Math.abs(yDiff) >= playerSize.h + 0.25 || Math.abs(xDiff) >= playerSize.h + 0.25) {
			enemyMov.set(xDiff, yDiff);
		}else {
			enemyMov.set(0, 0);
			
			armed.use(); 
		}
		
//		if (Math.abs(xDiff) > 3 || Math.abs(yDiff) > 3 || bob.counterCur < 0 || bob.counterCur > 35) {
//			enemyDir.moving = true;
//			if (bob.counterCur <= 0) {
//				enemyDir.direction = (int) (Math.random()*4);
//				bob.counterCur = bob.counterMax;
//			}
//		
//		}else if (Math.abs(xDiff) >= Math.abs(yDiff)) {
//			//move along x direction
//			if (xDiff < 0) {
//				enemyDir.direction = Direction.LEFT;
//			}else if (xDiff > 0) {
//				enemyDir.direction = Direction.RIGHT;
//			}
//			
//			if (Math.abs(xDiff) <= playerSize.w + 0.3) {
//				//change if statement to change when enemy stops moving
//				enemyDir.moving = false;
//				//attack
//				armed.isUsing = true;
//			}else {
//				enemyDir.moving = true;
//				armed.isUsing = false;
//			}
//		}
//		else if(Math.abs(xDiff) < Math.abs(yDiff)) {
//			//move along y direction
//			if (yDiff < 0) {
//				enemyDir.direction = Direction.UP;
//			}else if (yDiff > 0) {
//				enemyDir.direction = Direction.DOWN;
//			}
//			
//			if (Math.abs(yDiff) <= playerSize.h + 0.3) {
//				//change if statement to change when enemy stops moving
//				enemyDir.moving = false;
//				//attack
//				armed.isUsing = true;
//			}else {
//				enemyDir.moving = true;
//				armed.isUsing = false;
//			}
//		}
		
	}

}
