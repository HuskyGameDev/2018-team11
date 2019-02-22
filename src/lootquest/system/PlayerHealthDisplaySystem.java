package lootquest.system;

import java.util.List;

import lootquest.component.Health;
import lootquest.component.Player;
import lootquest.component.PlayerHealthBarComponent;
import lootquest.component.Position;
import lootquest.component.Size;
import lutebox.ecs.Entity;
import lutebox.ecs.Filter;
import lutebox.ecs.IteratingEntitySystem;

public class PlayerHealthDisplaySystem extends IteratingEntitySystem{
	
	public PlayerHealthDisplaySystem() {
		super(Filter.include(PlayerHealthBarComponent.class).create());
	}
	
	public void updateEntity(Entity e) {
		Filter playerSearch = Filter.include(Position.class, Player.class).create();
		List<Entity> players = getScene().getEntities(playerSearch);
		if (players.isEmpty())
			return;
		Entity player = players.get(0);
		Position playerPos = player.get(Position.class);
		Health Hp = player.get(Health.class);
		Position HpPos = e.get(Position.class);
		Size Bar = e.get(Size.class);
		
		HpPos.x = playerPos.x;
		HpPos.y = playerPos.y - 1f;
		
		Bar.w = 0.08f * Hp.getCurrent(); 
		Bar.h = .2f;
	}

}
