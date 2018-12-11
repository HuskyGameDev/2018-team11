package lootquest.component;

import java.util.ArrayList;
import java.util.List;

import lutebox.ecs.Component;
import lutebox.ecs.Entity;

public class Collider extends Component {

	public boolean collideWithWorld = true; 
	public boolean collideWithEntities = true; 
	
	// info about the entity for the current frame 
	public boolean isHittingWall = false; 
	public final List<Entity> collidingEntities = new ArrayList<>(); 
	
}
