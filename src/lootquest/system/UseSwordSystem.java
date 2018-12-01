package lootquest.system;

import java.util.List;

import lootquest.component.Direction;
import lootquest.component.Enemy;
import lootquest.component.EquipedSword;
import lootquest.component.Health;
import lootquest.component.Player;
import lootquest.component.Position;
import lootquest.component.Size;
import lootquest.util.EntityUtil;
import lutebox.core.Lutebox;
import lutebox.ecs.Entity;
import lutebox.ecs.Filter;
import lutebox.ecs.IteratingEntitySystem;
import lutebox.util.AABB;

public class UseSwordSystem extends IteratingEntitySystem {

	private Filter playerFilter, enemyFilter; 
	
    public UseSwordSystem() {
        super(Filter.include(Position.class, Direction.class, EquipedSword.class, Size.class).create());
        
        playerFilter = Filter.include(Player.class).create(); 
        enemyFilter = Filter.include(Enemy.class).create();
    }

    public void updateEntity(Entity e) {
        EquipedSword sword = e.get(EquipedSword.class); 
        
        // attack enemies here 
        
        
        
        if (sword.shouldApplyDamage()) {
        	AABB swordHitbox = getSwordHitbox(e);
        	List<Entity> playerList = Lutebox.scene.getEntities(playerFilter); 
            List<Entity> enemyList = Lutebox.scene.getEntities(enemyFilter); 
            
        	if (e.get(Player.class) != null) {
        		for(Entity other : enemyList) {
            		if (!swordHitbox.intersects(EntityUtil.getAABB(other))) continue; 
            		System.out.println("player hit an enemy");
            		Health hp = other.get(Health.class);
            		if (hp == null) {
            			continue;
            		}else {
            			hp.current -= sword.damage;
            		}
            	}
        	}else {
        		for(Entity other : playerList) {
        			if (!swordHitbox.intersects(EntityUtil.getAABB(other))) continue;
            		System.out.println("enemy hit a player");
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
        	
        	Lutebox.graphics.setColor(0xff00ff);
        	Lutebox.graphics.drawRect(
        			swordHitbox.x, 
        			swordHitbox.y, 
        			swordHitbox.w, 
        			swordHitbox.h);
        	
        }
    }
    
    private static AABB getSwordHitbox(Entity e) {
        Direction dir = e.get(Direction.class);
        Position pos = e.get(Position.class);
        Size size = e.get(Size.class);
        
        // sword logic here 
        if (dir.direction == Direction.UP) {
            //up
        	return new AABB(pos.x - (size.w)/4,pos.y - size.h,(size.w) * 1.5f,size.h);
        }else if (dir.direction == Direction.RIGHT) {
            //right
        	return new AABB(pos.x + (size.w),pos.y - (size.h)/4,(size.w),(size.h)*1.5f);
        }else if (dir.direction == Direction.DOWN) {
            //down
        	return new AABB(pos.x - (size.w)/4,pos.y + (size.h),(size.w) * 1.5f,size.h);
        }else if (dir.direction == Direction.LEFT) {
            //left
            return new AABB(pos.x - (size.w),pos.y - (size.h)/4,(size.w),(size.h)*1.5f);
        } 
        
        return null; 
    }
    
}
