package test.system;

import lutebox.core.Lutebox;
import lutebox.ecs.Entity;
import lutebox.ecs.Filter;
import lutebox.ecs.IteratingEntitySystem;
import test.TestGame;
import test.component.Size;
import test.component.Position;

public class RectangleRenderSystem extends IteratingEntitySystem {

    public int size = 64; 
    
    public RectangleRenderSystem() {
        super(Filter.include(Position.class, Size.class).create()); 
    }
    
    public void preRenderEntities() {
        for (int y = 0; y < 128; y++) {
            for (int x = 0; x < 128; x++) {
                Lutebox.graphics.setColor((x + y) % 2 == 0 ? 0xAAAAAA : 0x888888); 
                Lutebox.graphics.fillRect(TestGame.camX + x * size, TestGame.camY + y * size, size, size);
            }
        }
    }
    
    public void renderEntity(Entity e) {
        Position tfm = e.get(Position.class); 
        Size rect = e.get(Size.class); 
        
        Lutebox.graphics.setColor(0xFF0000);
        Lutebox.graphics.fillRect(
                TestGame.camX + (tfm.x + rect.xOffset) * size, 
                TestGame.camY + (tfm.y + rect.yOffset) * size, 
                rect.width * size, 
                rect.height * size);
    }
    
}
