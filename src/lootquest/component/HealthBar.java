package lootquest.component;

import lutebox.ecs.Component;
import lutebox.ecs.Entity;

public class HealthBar extends Component {
	
	public Entity above = null;
	
	public HealthBar setAbove(Entity e) {
		 above = e; 
		return this; 
	}

}
