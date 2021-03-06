package lootquest.system;

import java.util.List;

import lootquest.component.AI;
import lootquest.component.Direction;
import lootquest.component.Enemy;
import lootquest.component.EquipedSword;
import lootquest.component.Health;
import lootquest.component.Player;
import lootquest.component.Position;
import lootquest.component.Projectile;
import lootquest.component.Size;
import lootquest.util.EntityUtil;
import lootquest.util.TextureCache;
import lutebox.audio.Sound;
import lutebox.core.Lutebox;
import lutebox.ecs.Entity;
import lutebox.ecs.Filter;
import lutebox.ecs.IteratingEntitySystem;
import lutebox.util.AABB;

public class UseSwordSystem extends IteratingEntitySystem {

	private Filter playerFilter, enemyFilter, projectileFilter; 
	
	private Sound[] swordSounds, batSounds; 
	
    public UseSwordSystem() {
        super(Filter.include(Position.class, Direction.class, EquipedSword.class, Size.class).create());
        
        playerFilter = Filter.include(Player.class).create(); 
        enemyFilter = Filter.include(Enemy.class).create();
        projectileFilter = Filter.include(Projectile.class).create();
        
        // get a variety of sword sounds 
        
        swordSounds = new Sound[3 * 2]; 
        for (int i = 0; i < swordSounds.length; i++) {
            swordSounds[i] = new Sound("assets/sounds/sword_" + (i % 3) + ".wav"); 
        }
        
        // damage sounds 
        
        batSounds = new Sound[3 * 2]; 
        for (int i = 0; i < batSounds.length; i++) {
            batSounds[i] = new Sound("assets/sounds/bat_" + (i % 3) + ".wav"); 
        }
    }
    
    private void playSound(Sound[] sounds) {
        Lutebox.audio.play(sounds[(int)(Math.random() * sounds.length)], true, false); 
    }

    public void updateEntity(Entity e) {
        EquipedSword sword = e.get(EquipedSword.class); 
        
        // attack enemies here 
        
        
        
        if (sword.shouldApplyDamage()) {
            if (e.get(AI.class) != null) {
                playSound(batSounds); 
            }
            else {
                playSound(swordSounds); 
            }
        	AABB swordHitbox = getSwordHitbox(e);
        	List<Entity> playerList = Lutebox.scene.getEntities(playerFilter); 
            List<Entity> enemyList = Lutebox.scene.getEntities(enemyFilter); 
            List<Entity> projectileList = Lutebox.scene.getEntities(projectileFilter);
            
            // cause damage to target 
        	if (e.get(Player.class) != null) {
        		for(Entity other : enemyList) {
            		if (!swordHitbox.intersects(EntityUtil.getAABB(other))) continue; 
            		
            		Health hp = other.get(Health.class);
            		if (hp == null) {
            			continue;
            		}else {
            			
            			hp.current -= sword.damage;
            		}
            	}
        		for (Entity other : projectileList) {
        			if (swordHitbox.intersects(EntityUtil.getAABB(other))) {
        				other.destroy();
        			}
        		}
        	}else {
        		for(Entity other : playerList) {
        			if (!swordHitbox.intersects(EntityUtil.getAABB(other))) continue;
            		
            		Health hp = other.get(Health.class);
            		if (hp == null) {
            			continue;
            		}else {
            			hp.current -= sword.damage;
            		}
            	}
        	}
        	
        }
        
        sword.addTime(Lutebox.deltaTime);
    }
    
    public void renderEntity(Entity e) {
        EquipedSword sword = e.get(EquipedSword.class); 
        
        if (sword.shouldShowAnimation()) {
            // sword logic here 
        	AABB swordHitbox = getSwordHitbox(e); 
        	if (swordHitbox == null) return; 
        	
//        	Lutebox.graphics.setColor(0xff00ff);
//        	Lutebox.graphics.drawRect(
//        			swordHitbox.x, 
//        			swordHitbox.y, 
//        			swordHitbox.w, 
//        			swordHitbox.h);
        	Direction dir = e.get(Direction.class);
        	if (dir.direction == Direction.UP ) {
        	    Lutebox.graphics.drawTexture(TextureCache.get("assets/textures/animations/swordSlash0.png"), swordHitbox.x, swordHitbox.y, swordHitbox.w, (float) (swordHitbox.h*1));
        	} else if ( dir.direction == Direction.DOWN ) {
        	    Lutebox.graphics.drawTexture(TextureCache.get("assets/textures/animations/swordSlashDown.png"), swordHitbox.x, swordHitbox.y, swordHitbox.w, (float) (swordHitbox.h*1));
        	} else if ( dir.direction == Direction.LEFT ) {
                Lutebox.graphics.drawTexture(TextureCache.get("assets/textures/animations/swordSlahLeft.png"), swordHitbox.x, swordHitbox.y, swordHitbox.w, (float) (swordHitbox.h*1));
            } else {
                Lutebox.graphics.drawTexture(TextureCache.get("assets/textures/animations/swordSlashRight.png"), swordHitbox.x, swordHitbox.y, swordHitbox.w, (float) (swordHitbox.h*1));
            } 
        }
    }
    
    private static AABB getSwordHitbox(Entity e) {
        Direction dir = e.get(Direction.class);
        Position pos = e.get(Position.class);
        Size size = e.get(Size.class);
        
        // sword logic here 
		if (e.get(Player.class) != null) {  // if its a player sword hitbox has the following size
			if (dir.direction == Direction.UP) {
				// up
				return new AABB(pos.x - (size.w) / 4, pos.y - size.h, (size.w) * 1.5f, size.h);
			} else if (dir.direction == Direction.RIGHT) {
				// right
				return new AABB(pos.x + (size.w), pos.y - (size.h) / 4, (size.w), (size.h) * 1.5f);
			} else if (dir.direction == Direction.DOWN) {
				// down
				return new AABB(pos.x - (size.w) / 4, pos.y + (size.h), (size.w) * 1.5f, size.h);
			} else if (dir.direction == Direction.LEFT) {
				// left
				return new AABB(pos.x - (size.w), pos.y - (size.h) / 4, (size.w), (size.h) * 1.5f);
			}
		}else { // if not player it has a different size
			if (dir.direction == Direction.UP) {
				// up
				return new AABB(pos.x - (size.w) / 8, pos.y - size.h*0.75f, (size.w) * 1.25f, size.h*0.75f);
			} else if (dir.direction == Direction.RIGHT) {
				// right
				return new AABB(pos.x + (size.w)*0.75f, pos.y - (size.h) / 8, (size.w)*0.75f, (size.h) * 1.25f);
			} else if (dir.direction == Direction.DOWN) {
				// down
				return new AABB(pos.x - (size.w) / 8, pos.y + (size.h)*0.75f, (size.w) * 1.25f, size.h*0.75f);
			} else if (dir.direction == Direction.LEFT) {
				// left
				return new AABB(pos.x - (size.w)*0.75f, pos.y - (size.h) / 8, (size.w)*0.75f, (size.h) * 1.25f);
			}
		}
        
        return null; 
    }
    
}
