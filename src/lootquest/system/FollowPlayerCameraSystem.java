package lootquest.system;

import java.util.List;

import lootquest.component.Player;
import lootquest.component.Position;
import lootquest.component.Size;
import lutebox.core.Lutebox;
import lutebox.ecs.Entity;
import lutebox.ecs.Filter;
import lutebox.ecs.SceneSystem;

public class FollowPlayerCameraSystem extends SceneSystem {

    private Filter playerFilter = Filter.include(Player.class, Position.class, Size.class).create(); 
    
    public FollowPlayerCameraSystem() {
        super(0); 
    }
    
    public void update() {
        List<Entity> playerList = Lutebox.scene.getEntities(playerFilter); 
        
        if (playerList.size() > 0) {
            Entity e = playerList.get(0); 
            Position p = e.get(Position.class); 
            Size s = e.get(Size.class); 
            
            Lutebox.camera.setPosition(p.x + s.w / 2, p.y + s.h / 2); 
        }
    }
    
}
