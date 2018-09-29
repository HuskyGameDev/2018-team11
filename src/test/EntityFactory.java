package test;

import lutebox.core.Lutebox;
import lutebox.ecs.Entity;
import test.component.Size;
import test.component.Position;

public final class EntityFactory {

    private EntityFactory() {} 
    
    public static Entity createRectangle(float x, float y, float w, float h) {
        Entity e = Lutebox.scene.createEntity(); 
        
        e.attach(Position.class).set(x, y); 
        e.attach(Size.class).set(0, 0, w, h); 
        
        return e; 
    }
    
}
