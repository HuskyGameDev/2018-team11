package lootquest.system;

import lootquest.component.Projectile;
import lootquest.component.Collider;
import lootquest.component.Health;
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
		
		if(col.isHittingWall) {
			e.destroy();
		}else {
			for(Entity other:col.collidingEntities) {
				Health hp = other.get(Health.class);
				if (hp == null) {
					continue;
				}else {
					hp.current -= pro.damage;
					e.destroy();
				}
			}
		}
	}
	
}
