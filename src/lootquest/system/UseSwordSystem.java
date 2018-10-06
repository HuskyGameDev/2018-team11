package lootquest.system;

import lootquest.util.AABB;
import lootquest.LootquestGame;
import lootquest.component.Direction;
import lootquest.component.EquipedSword;
import lootquest.component.Position;
import lootquest.component.Size;
import lutebox.core.Lutebox;
import lutebox.ecs.Entity;
import lutebox.ecs.Filter;
import lutebox.ecs.IteratingEntitySystem;

public class UseSwordSystem extends IteratingEntitySystem {

    public UseSwordSystem() {
        super(Filter.include(Position.class, Direction.class, EquipedSword.class, Size.class).create());
    }

    public void updateEntity(Entity e) {
        EquipedSword sword = e.get(EquipedSword.class); 
        Direction dir = e.get(Direction.class);
        Position pos = e.get(Position.class);
        Size size = e.get(Size.class);
        sword.isUsing = true;
        
        if (sword.isUsing) {
            // sword logic here 
        	AABB swordHitbox;
        	if (dir.direction == 1) {
        		//y- North
        		swordHitbox = new AABB(pos.x - (size.w)/4,pos.y - size.h,(size.w) * 1.5f,size.h);
        	}else if (dir.direction == 2) {
        		//x+ East
        		swordHitbox = new AABB(pos.x + (size.w),pos.y + (size.h)/4,(size.w),(size.h)*1.5f);
        	}else if (dir.direction == 3) {
        		//y+ South
        		swordHitbox = new AABB(pos.x - (size.w)/4,pos.y + (size.h),(size.w) * 1.5f,size.h);
        	}else if (dir.direction == 4) {
        		//x- West
        		swordHitbox = new AABB(pos.x - (size.w),pos.y - (size.h)/4,(size.w),(size.h)*1.5f);
        	}
        }
    }
    
    public void renderEntity(Entity e) {
        EquipedSword sword = e.get(EquipedSword.class); 
        Direction dir = e.get(Direction.class);
        Position pos = e.get(Position.class);
        Size size = e.get(Size.class);
        
        if (sword.isUsing) {
            // sword logic here 
        	AABB swordHitbox = null;
        	if (dir.direction == 1) {
        		//y- North
        		swordHitbox = new AABB(pos.x - (size.w)/4,pos.y - size.h,(size.w) * 1.5f,size.h);
        	}else if (dir.direction == 2) {
        		//x+ East
        		swordHitbox = new AABB(pos.x + (size.w),pos.y + (size.h)/4,(size.w),(size.h)*1.5f);
        	}else if (dir.direction == 3) {
        		//y+ South
        		swordHitbox = new AABB(pos.x - (size.w)/4,pos.y + (size.h),(size.w) * 1.5f,size.h);
        	}else if (dir.direction == 4) {
        		//x- West
        		swordHitbox = new AABB(pos.x - (size.w),pos.y - (size.h)/4,(size.w),(size.h)*1.5f);
        	}
        	Lutebox.graphics.setColor(0xff00ff);
        	Lutebox.graphics.drawRect(
        			swordHitbox.x * LootquestGame.scale, 
        			swordHitbox.y * LootquestGame.scale, 
        			swordHitbox.w * LootquestGame.scale, 
        			swordHitbox.h * LootquestGame.scale);
        	
        }
    }
    
}
