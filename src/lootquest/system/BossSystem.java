package lootquest.system;

import java.util.List;

import lootquest.component.Boss;
import lootquest.component.Collider;
import lootquest.component.Direction;
import lootquest.component.Enemy;
import lootquest.component.EquipedSword;
import lootquest.component.Health;
import lootquest.component.Movement;
import lootquest.component.Player;
import lootquest.component.Position;
import lootquest.component.Size;
import lutebox.ecs.*;

public class BossSystem extends IteratingEntitySystem {

	public BossSystem() {
		super(Filter.include(Boss.class, Enemy.class, EquipedSword.class, Health.class, Movement.class, Position.class,
				Size.class).create());
	}

	public void updateEntity(Entity e) {
		Filter playerSearch = Filter.include(Position.class, Player.class).create();
		List<Entity> players = getScene().getEntities(playerSearch);

		if (players.isEmpty())
			return;
		Entity player = players.get(0);

		Position enemyPos = e.get(Position.class);
		Position playerPos = player.get(Position.class);
		Movement enemyMov = e.get(Movement.class);
		Size playerSize = player.get(Size.class);
		Size bossSize = e.get(Size.class);
		EquipedSword armed = e.get(EquipedSword.class);
		Collider col = e.get(Collider.class);
		Boss skeletor = e.get(Boss.class);

		float xDiff = playerPos.x - enemyPos.x - bossSize.w/2;
		float yDiff = playerPos.y - enemyPos.y - bossSize.h/2;

		if (skeletor.delay > 0) {
			skeletor.delay --;
			if (skeletor.delay == 0) {
				armed.use();
			}
		}else if (armed.shouldShowAnimation()) {
			
		}else if (Math.abs(xDiff) > skeletor.distance || Math.abs(yDiff) > skeletor.distance) {
			skeletor.counterCur--;
			if (skeletor.counterCur < 0) {
				enemyMov.set((float) (Math.random() * 4 - 2), (float) (Math.random() * 4 - 2));
				skeletor.counterCur = skeletor.counterMax;
			}
		} else if (Math.abs(yDiff) >= playerSize.h * 2 || Math.abs(xDiff) >= playerSize.h * 2) {
			if (!col.collidingEntities.isEmpty() && col.collidingEntities.get(0).get(Enemy.class) != null) {
				float colX = enemyPos.x - col.collidingEntities.get(0).get(Position.class).x;
				float colY = enemyPos.y - col.collidingEntities.get(0).get(Position.class).y;
				enemyMov.set(colX, colY);
			} else {
				enemyMov.set(xDiff, yDiff);
			}
		} else {
			enemyMov.set(0, 0);
			skeletor.delay = 20;
		}
	}

}
