package lootquest.system;

import lootquest.LootquestGame;
import lootquest.component.Collider;
import lootquest.component.Movement;
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
		super(Filter.include(Position.class, Movement.class, Size.class, Collider.class).create()); 
	}
	
	public void updateEntity(Entity e) {
		Position pos = e.get(Position.class); 
		Movement move = e.get(Movement.class); 
		Size size = e.get(Size.class); 
		Collider col = e.get(Collider.class); 
		
		if (!col.collideWithWorld) return; 
		
		// world physics 
		
		World world = LootquestGame.world; 
		
		boolean collision = false; 
		
		do {
			collision = false; 
			
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
						
						collision |= resolve(world, pos, size, move, x, y);
					}
				} 
			}
		} while (collision); 
	}
	
	private static boolean resolve(World world, Position pos, Size size, Movement move, int cx, int cy) {
		float vx = move.getDx(); 
		float vy = move.getDy(); 
		
		
		float len = (float) Math.sqrt(vx * vx + vy * vy);; 
		if (len == 0) return false; // requires nonzero velocity 
		float invlen = 1.0f /  len; 
		vx *= invlen; 
		vy *= invlen; 
		
//		System.out.println(vx + " " + vy);
		
		float x = pos.x; 
		float y = pos.y;
		float w = size.w; 
		float h = size.h; 
		float dx; 
		float dy; 
		
		if (vx > 0) {
			if (world.isSolid(cx - 1, cy)) vx = 0; 
			dx = cx - x - w;
		}
		else {
			if (world.isSolid(cx + 1, cy)) vx = 0; 
			dx = cx + 1 - x; 
		}
//		System.out.println("dx " + dx); 
		
		if (vy > 0) {
			dy = cy - y - h; 
		}
		else {
			dy = cy + 1 - y; 
		}
//		System.out.println("dy " + dy); 
		
		float sx = dx / vx; 
		float sy = dy / vy; 
		
		if (vy == 0 && vx != 0) {
//			System.out.println("!vy and vx");
			pos.x += dx; 
			return true; 
		}
		else if (vy != 0 && vx == 0) {
//			System.out.println("vy and !vx"); 
			pos.y += dy; 
			return true; 
		}
		else if (vy != 0 && vx != 0) {
			if (Math.abs(sx) < Math.abs(sy)) {
//				System.out.println("sx < sy");
				pos.x += dx; 
			}
			else {
//				System.out.println("sy < sx");
				pos.y += dy; 
			}
			return true; 
		}
		
		return false; 
	}
	
}
