package lootquest.system;

import java.util.List;

import lootquest.LootquestGame;
import lootquest.component.Enemy;
import lootquest.component.Player;
import lootquest.component.Position;
import lootquest.component.Size;
import lootquest.dungeon.Tile;
import lootquest.util.TextureCache;
import lutebox.core.Lutebox;
import lutebox.ecs.Entity;
import lutebox.ecs.Filter;
import lutebox.ecs.IteratingEntitySystem;
import lutebox.util.AABB;

public class RenderSystem extends IteratingEntitySystem {

    public float scale; 
    public float camX = 0; 
    public float camY = 0; 
    
    public RenderSystem(float scale) {
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
        
//        LootquestGame.world.render();
        
        AABB view = Lutebox.camera.computeViewport(); 
        int startX = (int) (view.x) - 1; 
        int startY = (int) (view.y) - 1; 
        int endX = (int) (view.x + view.w) + 1; 
        int endY = (int) (view.y + view.h) + 1; 
        
        for (int y = startY; y <= endY; y++) {
            if (y < 0 || y >= LootquestGame.world.getHeight()) continue; 
            for (int x = startX; x <= endX; x++) {
                if (x < 0 || x >= LootquestGame.world.getWidth()) continue; 
                
                Tile t = LootquestGame.world.getTile(x, y); 
                
                if (t != null) {
                    Lutebox.graphics.drawTexture(t.image, x, y, 1, 1); 
                }
            }
        }
    }
    
    public void renderEntity(Entity e) {
        Position p = e.get(Position.class); 
        Size s = e.get(Size.class);
        if ( e.contains(Player.class) ) {
            Lutebox.graphics.drawTexture(TextureCache.get("assets/textures/Player/player_0.png"), p.x, p.y - s.h, s.w, s.h * 2);
        }else if ( e.contains(Enemy.class) ) {
            Lutebox.graphics.drawTexture(TextureCache.get("assets/textures/enemies/sprite_0.png"), p.x, p.y, s.w, s.h);
        } else {
            Lutebox.graphics.setColor(0xFF0000);
            Lutebox.graphics.fillRect(p.x, p.y, s.w, s.h);
        } 
    }
    
}
