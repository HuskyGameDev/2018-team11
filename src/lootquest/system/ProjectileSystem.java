package lootquest.system;

import lootquest.component.Projectile;
import lootquest.component.Collider;
import lootquest.component.Health;
import lootquest.component.Player;
import lootquest.component.Position;
import lutebox.ecs.Entity;
import lutebox.ecs.Filter;
import lutebox.ecs.IteratingEntitySystem;

public class ProjectileSystem extends IteratingEntitySystem{

	public ProjectileSystem() {
		super(Filter.include(Projectile.class,Collider.class).create());
	}
	
	public void updateEntity(Entity e) {
		Collider col = e.get(Collider.class);
		Projectile pro = e.get(Projectile.class);
		Position pos = e.get(Position.class);
		Position shooterPos = e.get(Position.class);
		
		if (Math.abs(pos.x - shooterPos.x) > 20 || Math.abs(pos.y - shooterPos.y) > 20 ) {
			e.destroy();
		}else if(col.isHittingWall) {
//			System.out.println("ProjectileSystem: destroy arrow");
			e.destroy();
		}else if (pro.shooter.get(Player.class) != null ){
			for(Entity other : col.collidingEntities) {
				if (other == pro.shooter) continue;  
				Health hp = other.get(Health.class);
				if (hp == null) {
					continue;
				}else {
					hp.current -= pro.damage;
//					System.out.println("ProjectileSystem: used up arrow");
					e.destroy();
				}
			}
		}else {
			for(Entity other : col.collidingEntities) {
				if (other == pro.shooter) continue; 
				if (other.get(Player.class) == null) continue; 
				Health hp = other.get(Health.class);
				if (hp == null) {
					continue;
				}else {
					hp.current -= pro.damage;
//					System.out.println("ProjectileSystem: used up arrow");
					e.destroy();
				}
			}
		}
	}
	
}
