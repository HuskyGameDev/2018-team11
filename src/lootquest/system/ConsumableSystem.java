package lootquest.system;

import lootquest.component.Collider;
import lootquest.component.Consumable;
import lootquest.component.EquipedSword;
import lootquest.component.Health;
import lootquest.component.Movement;
import lootquest.component.Player;
import lootquest.component.Position;
import lutebox.ecs.Entity;
import lutebox.ecs.Filter;
import lutebox.ecs.IteratingEntitySystem;

public class ConsumableSystem extends IteratingEntitySystem{

	public ConsumableSystem() {
		super(Filter.include(Consumable.class, Position.class, Collider.class).create());
	}
	
	public void updateEntity(Entity e) {
		Collider col = e.get(Collider.class);
		Consumable consume = e.get(Consumable.class);
		
		for(Entity other : col.collidingEntities) {
			if (other.get(Player.class) == null) {
				continue;
			}else {
				Movement s = other.get(Movement.class);
				EquipedSword sword = other.get(EquipedSword.class);
				Health Hp = other.get(Health.class);
				
				s.setMaxSpeed(s.maxSpeed + consume.SpeedUp);
				sword.damage += consume.SwordDmgUp;
				Hp.current += consume.HPRestore;
				
				System.out.println("ConsumableSystem: potion used");
				e.destroy();
			} 
			
		}
	}
}
