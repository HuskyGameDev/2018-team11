package lootquest.system;

import lootquest.component.EquipedSword;
import lootquest.component.Position;
import lutebox.ecs.Entity;
import lutebox.ecs.Filter;
import lutebox.ecs.IteratingEntitySystem;

public class UseSwordSystem extends IteratingEntitySystem {

    public UseSwordSystem() {
        super(Filter.include(Position.class, EquipedSword.class).create());
    }

    public void updateEntity(Entity e) {
        EquipedSword sword = e.get(EquipedSword.class); 
        
        if (sword.isUsing) {
            // sword logic here 
        }
    }
    
}
