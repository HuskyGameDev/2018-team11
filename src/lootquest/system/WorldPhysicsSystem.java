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
		Collider col = e.get(Collider.class); 
		Movement move = e.get(Movement.class); 
		
		if (!col.collideWithWorld) return; 
		
		move(e, move.getDx(), move.getDy()); 
	}
	
	private static void move(Entity e, float dx, float dy) {
		Position pos = e.get(Position.class); 
		Movement move = e.get(Movement.class); 
		Size size = e.get(Size.class); 
		Collider col = e.get(Collider.class); 
		
		World world = LootquestGame.world; 
		
		boolean collision = false; 
		
		AABB entityCollider = new AABB(pos.x, pos.y, size.w, size.h);  
		AABB tileCollider = new AABB(0, 0, 1, 1); 
		AABB tmp = new AABB(0, 0, 1, 1); 
		int bestX = 0, bestY = 0; 
		float bestArea = 0; 
		boolean foundTile = false; 
		int i = 0; 
		
		do {
			entityCollider.set(pos.x, pos.y, size.w, size.h); 
			collision = false; 
			foundTile = false; 
			bestArea = 0; 
			
			int xStart = (int) Math.floor(pos.x); 
			int yStart = (int) Math.floor(pos.y); 
			int xSize = xStart + (int) Math.ceil(size.w) + 1; 
			int ySize = yStart + (int) Math.ceil(size.h) + 1; 
			
	//		System.out.println(xStart + " " + yStart + " " + xSize + " " + ySize); 
			
			for (int y = yStart; y < yStart + ySize; y++) {
				for (int x = xStart; x < xStart + xSize; x++) {
					tmp.x = x; 
					tmp.y = y; 
					
					if (world.inBounds(x, y)) {
						Tile tile = world.getTile(x, y); 
						if (!tile.isSolid) continue; 
					}
					
					if (tmp.intersects(entityCollider)) {
						// collision 
	//					System.out.println("Collision"); 
						float area = entityCollider.getIntersectionArea(tmp); 
						if (area > bestArea) {
							bestX = x; 
							bestY = y; 
							bestArea = area; 
							foundTile = true; 
							tileCollider.set(tmp); 
						}
					}
				} 
			}
			
			if (foundTile) collision = resolve(world, pos, size, move, bestX, bestY); 
		} while (collision && i++ < 10); 
	}
	
	private static boolean resolve(World world, Position pos, Size size, Movement move, int cx, int cy) {
		float vx = move.getDx(); 
		float vy = move.getDy(); 
		
		
		float speed = move.getSpeed(); 
		if (speed == 0) return false; // requires nonzero velocity 
		float invlen = 1.0f /  speed; 
		vx *= invlen; 
		vy *= invlen; 
		
		float scaleX = vx == 0 ? 0 : (move.getDx() < 0 ? -1 : 1); 
		float scaleY = vy == 0 ? 0 : (move.getDy() < 0 ? -1 : 1); 
		
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
		
		boolean corrected = false; 
		
		if (vy == 0 && vx != 0) {
//			System.out.println("!vy and vx");
			pos.x += dx; 
			scaleX = 0; 
			corrected = true; 
		}
		else if (vy != 0 && vx == 0) {
//			System.out.println("vy and !vx"); 
			pos.y += dy; 
			scaleY = 0; 
			corrected = true; 
		}
		else if (vy != 0 && vx != 0) {
			if (Math.abs(sx) < Math.abs(sy)) {
//				System.out.println("sx < sy");
				pos.x += dx; 
				scaleX = 0; 
			}
			else {
//				System.out.println("sy < sx");
				pos.y += dy; 
				scaleY = 0; 
			}
			corrected = true; 
		}
		
//		if (keepVel) {
//			if (scaleX != 0) {
//				// adjusted y, add extra vel to x 
//				pos.x += (move.getSpeed() - Math.abs(move.getDx())) * scaleX * Lutebox.deltaTime; 
//			}
//			else if (scaleY != 0) {
//				// adjusted x, add extra vel to y 
//				pos.y += (move.getSpeed() - Math.abs(move.getDy())) * scaleY * Lutebox.deltaTime; 
//			}
//		}
		
		return corrected; 
	}
	
}
