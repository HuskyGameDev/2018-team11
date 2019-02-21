package lootquest.system;

import lootquest.LootquestGame;
import lootquest.component.Enemy;
import lootquest.component.Health;
import lootquest.component.Player;
import lutebox.ecs.Entity;
import lutebox.ecs.Filter;
import lutebox.ecs.IteratingEntitySystem;

public class DeathSystem extends IteratingEntitySystem {
	
	public DeathSystem()
	{
		super(Filter.include(Health.class).create());	
	}
	
	public void updateEntity(Entity e) {
	  Health h = e.get(Health.class);
	  Player p = e.get(Player.class);
	  Enemy en = e.get(Enemy.class);
	  if(h.getCurrent() <= 0)
	  {
		  if(en != null) {
		     System.out.println("DeathSystem: enemy died");
		  } else if(p != null) {
		     System.out.println("DeathSystem: player died");
	      } else {
		     System.out.println("DeathSystem: nothing using health");
		  }
		 
		 if ( p != null ) {
		     LootquestGame.reloadNEW();
		 } else {
		     e.destroy();
		 }
		 
	  }
	  
	}	
	
}
