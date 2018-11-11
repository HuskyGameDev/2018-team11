package lootquest.system;

import java.util.List;

import lootquest.LootquestGame;
import lootquest.component.Enemy;
import lootquest.component.Player;
import lootquest.component.Position;
import lootquest.component.Size;
import lootquest.util.TextureCache;
import lutebox.core.Lutebox;
import lutebox.ecs.Entity;
import lutebox.ecs.Filter;
import lutebox.ecs.IteratingEntitySystem;
import lutebox.graphics.Texture;

public class MapRenderSystem extends IteratingEntitySystem {

    public float scale; 
    public float camX = 0; 
    public float camY = 0; 
    
    public MapRenderSystem(float scale) {
        super(Filter.include(Position.class, Size.class).create()); 
        
        this.scale = scale;
        
        
    }
    
    public void preRenderEntities() {
        Lutebox.graphics.setColor(0x000000);
        Lutebox.graphics.clear();
        
        List<Entity> playerList = Lutebox.scene.getEntities(Filter.include(Player.class, Position.class, Size.class).create()); 
        if (playerList.size() > 0) {
        	Entity player = playerList.get(0); 
        	camX = player.get(Position.class).x - Lutebox.display.getWidth() / 2; 
        }
        
        LootquestGame.world.render();
    }
    
    public void renderEntity(Entity e) {
        Position p = e.get(Position.class); 
        Size s = e.get(Size.class);
        if ( e.contains(Player.class) ) {
            Lutebox.graphics.drawTexture(TextureCache.get("assets/textures/Player.png"), p.x*scale, p.y*scale, s.w*scale, s.h*scale);
        }else if ( e.contains(Enemy.class) ) {
            Lutebox.graphics.drawTexture(TextureCache.get("assets/textures/enemies/sprite_0.png"), p.x*scale, p.y*scale, s.w*scale, s.h*scale);
        } else {
            Lutebox.graphics.setColor(0xFF0000);
            Lutebox.graphics.fillRect(p.x * scale, p.y * scale, s.w * scale, s.h * scale);
        } 
    }
    
}
