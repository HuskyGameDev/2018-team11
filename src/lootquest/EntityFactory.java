package lootquest;

import lootquest.component.Position;
import lootquest.component.Size;
import lutebox.core.Lutebox;
import lutebox.ecs.Entity;

public final class EntityFactory {

    // don't instantiate this class 
    private EntityFactory() {} 
    
    public static Entity createPlayer(float x, float y) {
        Entity e = Lutebox.scene.createEntity(); 
        
        e.attach(Position.class).set(x, y); 
        e.attach(Size.class).set(1, 1); 
        
        return e; 
    }
    
}
