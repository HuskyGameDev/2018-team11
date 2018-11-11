package lootquest.system;


import lootquest.component.Collider;
import lootquest.component.Consumable;
import lootquest.component.EquipedCrossbow;
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
		
		for(Entity other:col.collidingEntities) {
			Player player = other.get(Player.class);
			if (player == null) {
				continue;
			}else {
				Movement s = other.get(Movement.class);
				EquipedSword sword = other.get(EquipedSword.class);
				//EquipedCrossbow bow = other.get(EquipedCrossbow.class);
				Health Hp = other.get(Health.class);
				
				s.maxSpeed += consume.SpeedUp;
				sword.damage += consume.SwordDmgUp;
				//bow.
				Hp.current += consume.HPRestore;
				
				e.destroy();
			} 
			
		}
	}
}
