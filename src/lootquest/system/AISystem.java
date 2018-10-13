package lootquest.system;

import java.util.List;

import lootquest.component.AI;
import lootquest.component.Direction;
import lootquest.component.Enemy;
import lootquest.component.Health;
import lootquest.component.Player;
import lootquest.component.Position;
import lootquest.component.Size;
import lootquest.component.Speed;
import lutebox.ecs.Entity;
import lutebox.ecs.Filter;
import lutebox.ecs.IteratingEntitySystem;

public class AISystem extends IteratingEntitySystem{

	public AISystem() {
		super(Filter.include(Position.class, Health.class, Enemy.class, AI.class, Size.class, Direction.class, Speed.class).create());
	}
	
	public void updateEntity(Entity e) {
		Filter playerSearch = Filter.include(Position.class,Player.class).create();
		List<Entity> players = getScene().getEntities(playerSearch);
		Entity player = players.get(0);
		
		Position enemyPos = e.get(Position.class);
		Position playerPos = player.get(Position.class);
		Direction enemyDir = e.get(Direction.class);
		Size playerSize = player.get(Size.class);
		
		float xDiff = playerPos.x - enemyPos.x;
		float yDiff = playerPos.y - enemyPos.y;
		
		if (Math.abs(xDiff) >= Math.abs(yDiff)) {
			//move along x direction
			if (xDiff < 0) {
				enemyDir.direction = Direction.LEFT;
			}else if (xDiff > 0) {
				enemyDir.direction = Direction.RIGHT;
			}
			
			if (Math.abs(xDiff) <= playerSize.w) {
				//change if statement to change when enemy stops moving
				enemyDir.moving = false;
				//attack
			}else {
				enemyDir.moving = true;
			}
		}
		else if(Math.abs(xDiff) < Math.abs(yDiff)) {
			//move along y direction
			if (yDiff < 0) {
				enemyDir.direction = Direction.UP;
			}else if (yDiff > 0) {
				enemyDir.direction = Direction.DOWN;
			}
			
			if (Math.abs(yDiff) <= playerSize.h) {
				//change if statement to change when enemy stops moving
				enemyDir.moving = false;
				//attack
			}else {
				enemyDir.moving = true;
			}
		}
		
	}

}
