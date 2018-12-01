package lootquest.system;

import lootquest.component.Direction;
import lootquest.component.EquipedSword;
import lootquest.component.Position;
import lootquest.component.Size;
import lutebox.core.Lutebox;
import lutebox.ecs.Entity;
import lutebox.ecs.Filter;
import lutebox.ecs.IteratingEntitySystem;
import lutebox.util.AABB;

public class UseSwordSystem extends IteratingEntitySystem {

    public UseSwordSystem() {
        super(Filter.include(Position.class, Direction.class, EquipedSword.class, Size.class).create());
    }

    public void updateEntity(Entity e) {
        EquipedSword sword = e.get(EquipedSword.class); 
        
        // attack enemies here 
        
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
