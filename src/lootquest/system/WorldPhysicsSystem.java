package lootquest.system;

import java.util.List;

import lootquest.LootquestGame;
import lootquest.component.Collider;
import lootquest.component.Movement;
import lootquest.component.Position;
import lootquest.component.Size;
import lootquest.dungeon.Tile;
import lootquest.dungeon.World;
import lutebox.core.Lutebox;
import lutebox.ecs.Entity;
import lutebox.ecs.Filter;
import lutebox.ecs.IteratingEntitySystem;
import lutebox.util.AABB;

/**
 * Keeps entities out of walls, and checks what entities they are colliding with 
 */
public class WorldPhysicsSystem extends IteratingEntitySystem {

	public WorldPhysicsSystem() {
		super(Filter.include(Position.class, Movement.class, Size.class, Collider.class).create()); 
	}
	
	private Filter collisionFilter = Filter.include(Position.class, Size.class, Collider.class).create(); 
	
	public void updateEntity(Entity e) {
		Collider col = e.get(Collider.class); 
		Movement move = e.get(Movement.class); 
		Position pos = e.get(Position.class); 
		Size size = e.get(Size.class); 
		
		col.isHittingWall = false; 
		col.collidingEntities.clear(); 
		
		// do world collision detection and resolution 
		// if that collider property is set (default: true) 
		if (col.collideWithWorld) {
			move(e, move.getDx(), move.getDy()); 
		}
		
		// detect collision with entities (resolving collisions not supported) 
		if (col.collideWithEntities) {
			AABB a = new AABB(pos.x, pos.y, size.w, size.h); 
			AABB b = new AABB(0, 0, 0, 0); 
			
			List<Entity> entities = Lutebox.scene.getEntities(collisionFilter); 
			
			for (Entity other : entities) {
				if (other != e) {
					Position p2 = other.get(Position.class); 
					Size s2 = other.get(Size.class); 
					b.set(p2.x, p2.y, s2.w, s2.h); 
					
					if (a.intersects(b)) {
						col.collidingEntities.add(other); 
					}
				}
			}
		}
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
			
			// set tile range to check 
			int xStart = (int) Math.floor(pos.x); 
			int yStart = (int) Math.floor(pos.y); 
			int xSize = xStart + (int) Math.ceil(size.w) + 1; 
			int ySize = yStart + (int) Math.ceil(size.h) + 1; 
			
			for (int y = yStart; y < yStart + ySize; y++) {
				for (int x = xStart; x < xStart + xSize; x++) {
					tmp.x = x; 
					tmp.y = y; 
					
					if (world.inBounds(x, y)) {
						// we don't care about collision if the tile isn't solid 
						Tile tile = world.getTile(x, y);
						if ( tile != null ) {
						    if (!tile.isSolid) continue; 
						}
					}
					
					if (tmp.intersects(entityCollider)) {
						// collision 
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
			
			// actual collision resolution 
			if (foundTile) collision = resolve(world, pos, size, move, bestX, bestY); 
			if (collision) col.isHittingWall = true; 
			
		} while (collision && i++ < 10); 
	}
	
	// moves entity out of a solid tile 
	private static boolean resolve(World world, Position pos, Size size, Movement move, int cx, int cy) {
		float vx = move.getDx(); 
		float vy = move.getDy(); 
		
		
		float speed = move.getSpeed(); 
		if (speed == 0) return false; // requires nonzero velocity 
		float invlen = 1.0f /  speed; 
		vx *= invlen; 
		vy *= invlen; 
		
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
		
		if (vy > 0) {
			dy = cy - y - h; 
		}
		else {
			dy = cy + 1 - y; 
		}
		
		float sx = dx / vx; 
		float sy = dy / vy; 
		
		boolean corrected = false; 
		
		if (vy == 0 && vx != 0) {
			pos.x += dx; 
			corrected = true; 
		}
		else if (vy != 0 && vx == 0) {
			pos.y += dy; 
			corrected = true; 
		}
		else if (vy != 0 && vx != 0) {
			if (Math.abs(sx) < Math.abs(sy)) {
				pos.x += dx; 
			}
			else {
				pos.y += dy; 
			}
			corrected = true; 
		}
		
		return corrected; 
	}
	
}
