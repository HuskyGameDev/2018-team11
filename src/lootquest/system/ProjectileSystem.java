package lootquest.system;

import lootquest.component.Projectile;
import lootquest.component.Collider;
import lutebox.ecs.Entity;
import lutebox.ecs.Filter;
import lutebox.ecs.IteratingEntitySystem;

public class ProjectileSystem extends IteratingEntitySystem{

	public ProjectileSystem() {
		super(Filter.include(Projectile.class,Collider.class).create());
	}
	
	public void updateEntity(Entity e) {
		
		
	}
	
}
