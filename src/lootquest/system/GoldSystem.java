package lootquest.system;

import lootquest.component.Collider;
import lootquest.component.EquipedSword;
import lootquest.component.Gold;
import lootquest.component.Health;
import lootquest.component.Movement;
import lootquest.component.Player;
import lootquest.component.Position;
import lutebox.ecs.Entity;
import lutebox.ecs.Filter;
import lutebox.ecs.IteratingEntitySystem;

public class GoldSystem extends IteratingEntitySystem {

	public GoldSystem() {
		super(Filter.include(Gold.class, Position.class, Collider.class).create());
	}

	public void updateEntity(Entity e) {
		Collider col = e.get(Collider.class);
		Gold gold = e.get(Gold.class);
		
		for(Entity other : col.collidingEntities) {
			if (other.get(Player.class) == null) {
				continue;
			}else {
				
				System.out.println("GoldSystem: gold collected");
				e.destroy();
			} 
			
		}
	}
}
