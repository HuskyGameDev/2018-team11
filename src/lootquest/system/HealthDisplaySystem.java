package lootquest.system;

import lootquest.component.Health;
import lootquest.component.HealthBar;
import lootquest.component.Position;
import lootquest.component.Size;
import lutebox.ecs.Entity;
import lutebox.ecs.Filter;
import lutebox.ecs.IteratingEntitySystem;

public class HealthDisplaySystem extends IteratingEntitySystem{
	
	public HealthDisplaySystem() {
		super(Filter.include(HealthBar.class).create());
	}
	
	public void updateEntity(Entity e) {
		HealthBar bar = e.get(HealthBar.class);
		Entity ent = bar.above;
		Position entPos = ent.get(Position.class);
		Health Hp = ent.get(Health.class);
		Position HpPos = e.get(Position.class);
		Size max = ent.get(Size.class);
		Size Bar = e.get(Size.class);
		
		HpPos.x = entPos.x;
		HpPos.y = entPos.y - 1f;
		
		Bar.w = max.w * ((float)Hp.getCurrent())/Hp.getMax();
		Bar.h = .2f;
	}

}
