package lootquest.util;

import lootquest.component.Position;
import lootquest.component.Size;
import lutebox.ecs.Entity;
import lutebox.util.AABB;

public final class EntityUtil {

	private EntityUtil() {} 
	
	/**
	 * Tries to create an AABB, returns null if it fails. 
	 * 
	 * Entity must have Position and Size to succeed
	 */
	public static AABB getAABB(Entity e) {
		Position p = e.get(Position.class); 
		Size s = e.get(Size.class); 
		
		if (p != null && s != null) {
			return new AABB(p.x, p.y, s.w, s.h); 
		}
		else {
			return null; 
		}
	}
	
}
