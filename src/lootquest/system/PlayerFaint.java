package lootquest.system;

import lootquest.component.Direction;
import lootquest.component.EquipedSword;
import lootquest.component.Player;
import lootquest.component.Health;
import lootquest.component.Movement;
import lutebox.core.Lutebox;
import lutebox.ecs.Entity;
import lutebox.ecs.Filter;
import lutebox.ecs.IteratingEntitySystem;
import lutebox.input.Input;

public class PlayerFaint extends IteratingEntitySystem {
	
	public PlayerFaint()
	{
		super(Filter.include(Player.class, Health.class).create());	
	}
	
	public void updateEntity(Entity e)
	{
	  Health h = e.get(Health.class);
	  Player p = e.get(Player.class);
	  if(h.getCurrent() <= 0 && p != null)
	  {
		 System.out.println("PLAYER FAINTED");
		 e.destroy();
	  }
	  
	  if(h.getCurrent() <= 25 && p != null)
	     System.out.println("LOW HEALTH");
	}	
	
}
