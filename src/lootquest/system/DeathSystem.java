package lootquest.system;

import lootquest.component.Health;
import lootquest.component.Player;
import lutebox.ecs.Entity;
import lutebox.ecs.Filter;
import lutebox.ecs.IteratingEntitySystem;

public class DeathSystem extends IteratingEntitySystem {
	
	public DeathSystem()
	{
		super(Filter.include(Player.class, Health.class).create());	
	}
	
	public void updateEntity(Entity e)
	{
	  Health h = e.get(Health.class);
	  Player p = e.get(Player.class);
	  if(h.getCurrent() <= 0)
	  {
//		 if (p != null) System.out.println("PLAYER FAINTED");
		 e.destroy();
	  }
	  
	  if(h.getCurrent() <= 25 && p != null) {
//	     System.out.println("LOW HEALTH");
	  }
	}	
	
}
