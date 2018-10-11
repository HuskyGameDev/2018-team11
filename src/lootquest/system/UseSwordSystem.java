package lootquest.system;

import lootquest.LootquestGame;
import lootquest.component.Direction;
import lootquest.component.EquipedSword;
import lootquest.component.Position;
import lootquest.component.Size;
import lootquest.util.AABB;
import lutebox.core.Lutebox;
import lutebox.ecs.Entity;
import lutebox.ecs.Filter;
import lutebox.ecs.IteratingEntitySystem;
import lutebox.input.Input;

public class UseSwordSystem extends IteratingEntitySystem {

    public UseSwordSystem() {
        super(Filter.include(Position.class, Direction.class, EquipedSword.class, Size.class).create());
    }

    public void updateEntity(Entity e) {
        EquipedSword sword = e.get(EquipedSword.class); 
        
        sword.isUsing = Lutebox.input.getKey(Input.KEY_SPACE); 
    }
    
    public void renderEntity(Entity e) {
        EquipedSword sword = e.get(EquipedSword.class); 
        
        if (sword.isUsing) {
            // sword logic here 
        	AABB swordHitbox = getSwordHitbox(e); 
        	if (swordHitbox == null) return; 
        	
        	Lutebox.graphics.setColor(0xff00ff);
        	Lutebox.graphics.drawRect(
        			swordHitbox.x * LootquestGame.scale, 
        			swordHitbox.y * LootquestGame.scale, 
        			swordHitbox.w * LootquestGame.scale, 
        			swordHitbox.h * LootquestGame.scale);
        	
        }
    }
    
    private static AABB getSwordHitbox(Entity e) {
        EquipedSword sword = e.get(EquipedSword.class); 
        Direction dir = e.get(Direction.class);
        Position pos = e.get(Position.class);
        Size size = e.get(Size.class);
        
        if (sword.isUsing) {
            // sword logic here 
            if (dir.direction == Direction.UP) {
                //y- North
                return new AABB(pos.x - (size.w)/4,pos.y - size.h,(size.w) * 1.5f,size.h);
            }else if (dir.direction == Direction.RIGHT) {
                //x+ East
                return new AABB(pos.x + (size.w),pos.y - (size.h)/4,(size.w),(size.h)*1.5f);
            }else if (dir.direction == Direction.DOWN) {
                //y+ South
                return new AABB(pos.x - (size.w)/4,pos.y + (size.h),(size.w) * 1.5f,size.h);
            }else if (dir.direction == Direction.LEFT) {
                //x- West
                return new AABB(pos.x - (size.w),pos.y - (size.h)/4,(size.w),(size.h)*1.5f);
            }
        }
        
        return null; 
    }
    
}
