package lootquest.system;

import lootquest.LootquestGame;
import lootquest.component.Collider;
import lootquest.component.Direction;
import lootquest.component.Position;
import lootquest.component.Size;
import lootquest.dungeon.Tile;
import lootquest.dungeon.World;
import lootquest.util.AABB;
import lutebox.ecs.Entity;
import lutebox.ecs.Filter;
import lutebox.ecs.IteratingEntitySystem;

public class WorldPhysicsSystem extends IteratingEntitySystem {

	public WorldPhysicsSystem() {
		super(Filter.include(Position.class, Direction.class, Size.class, Collider.class).create()); 
	}
	
	public void updateEntity(Entity e) {
		Position pos = e.get(Position.class); 
		Direction dir = e.get(Direction.class); 
		Size size = e.get(Size.class); 
		Collider col = e.get(Collider.class); 
		
		if (!col.collideWithWorld) return; 
		
		// world physics 
		
		World world = LootquestGame.world; 
		
		int xStart = (int) Math.floor(pos.x); 
		int yStart = (int) Math.floor(pos.y); 
		int xSize = xStart + (int) Math.ceil(size.w) + 1; 
		int ySize = yStart + (int) Math.ceil(size.h) + 1; 
		
		AABB tileCollider = new AABB(0, 0, 1, 1); 
		AABB entityCollider = new AABB(pos.x, pos.y, size.w, size.h); 
		
//		System.out.println(xStart + " " + yStart + " " + xSize + " " + ySize); 
		
		for (int y = yStart; y < yStart + ySize; y++) {
			for (int x = xStart; x < xStart + xSize; x++) {
				tileCollider.x = x; 
				tileCollider.y = y; 
				
				if (world.inBounds(x, y)) {
					Tile tile = world.getTile(x, y); 
					if (!tile.isSolid) continue; 
				}
				
				if (tileCollider.intersects(entityCollider)) {
					// collision 
//					System.out.println("Collision"); 
					
					resolve(pos, size, dir, x, y); 
				}
			} 
		}
	}
	
	private static void resolve(Position pos, Size size, Direction dir, int x, int y) {
		switch (dir.direction) {
		case Direction.UP: 
			pos.y = y + 1; 
			break; 
		case Direction.DOWN: 
			pos.y = y - size.h; 
			break; 
		case Direction.LEFT: 
			pos.x = x + 1; 
			break; 
		case Direction.RIGHT: 
			pos.x = x - size.w; 
			break; 
		}
	}
	
}
