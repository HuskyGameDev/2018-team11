package lootquest.system;

import lootquest.LootquestGame;
import lootquest.component.Enemy;
import lootquest.component.Player;
import lootquest.component.Position;
import lootquest.component.Size;
import lutebox.core.Lutebox;
import lutebox.ecs.Entity;
import lutebox.ecs.Filter;
import lutebox.ecs.IteratingEntitySystem;
import lutebox.graphics.Texture;

public class MapRenderSystem extends IteratingEntitySystem {

    public float scale; 
    
    public MapRenderSystem(float scale) {
        super(Filter.include(Position.class, Size.class).create()); 
        
        this.scale = scale;
        
        
    }
    
    public void preRenderEntities() {
        Lutebox.graphics.setColor(0x000000);
        Lutebox.graphics.clear();
        
        LootquestGame.world.render();
    }
    
    public void renderEntity(Entity e) {
        Position p = e.get(Position.class); 
        Size s = e.get(Size.class);
        if ( e.contains(Player.class) ) {
            Lutebox.graphics.drawTexture(new Texture("assets/textures/Player.png"), p.x*scale, p.y*scale, s.w*scale, s.h*scale);
        }else if ( e.contains(Enemy.class) ) {
            Lutebox.graphics.drawTexture(new Texture("assets/textures/enemies/sprite_0.png"), p.x*scale, p.y*scale, s.w*scale, s.h*scale);
        } else {
            Lutebox.graphics.setColor(0xFF0000);
            Lutebox.graphics.fillRect(p.x * scale, p.y * scale, s.w * scale, s.h * scale);
        } 
    }
    
}
