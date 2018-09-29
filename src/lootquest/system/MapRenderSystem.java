package lootquest.system;

import lootquest.component.Position;
import lootquest.component.Size;
import lutebox.core.Lutebox;
import lutebox.ecs.Entity;
import lutebox.ecs.Filter;
import lutebox.ecs.IteratingEntitySystem;

public class MapRenderSystem extends IteratingEntitySystem {

    public float scale; 
    
    public MapRenderSystem(float scale) {
        super(Filter.include(Position.class, Size.class).create()); 
        
        this.scale = scale; 
    }
    
    public void preRenderEntities() {
        Lutebox.graphics.setColor(0x66BBFF); 
        Lutebox.graphics.clear(); 
    }
    
    public void renderEntity(Entity e) {
        Position p = e.get(Position.class); 
        Size s = e.get(Size.class); 
        
        Lutebox.graphics.setColor(0xFF0000); 
        Lutebox.graphics.fillRect(p.x * scale, p.y * scale, s.w * scale, s.h * scale); 
    }
    
}
