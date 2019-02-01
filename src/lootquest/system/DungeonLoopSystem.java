package lootquest.system;


import java.util.List;

import lootquest.LootquestGame;
import lootquest.component.Player;
import lootquest.component.Position;
import lutebox.audio.Sound;
import lutebox.core.Lutebox;
import lutebox.ecs.Entity;
import lutebox.ecs.Filter;
import lutebox.ecs.IteratingEntitySystem;

public class DungeonLoopSystem extends IteratingEntitySystem {

    private float endX;
    private float endY;
    
    public DungeonLoopSystem( int endX, int endY ) {
        super(Filter.include(Player.class).create());
        this.endX = endX;
        this.endY = endY;
    }
    
    public void updateEntity(Entity e) {
        Filter playerSearch = Filter.include(Position.class,Player.class).create();
        List<Entity> players = getScene().getEntities(playerSearch);
        
        if (players.isEmpty()) return; 
        Entity player = players.get(0);
        
        Position playerPos = player.get(Position.class);
        
        if ( playerPos.x <= endX+0.5 && playerPos.x >= endX-0.5 && playerPos.y <= endY+0.5 && playerPos.y >= endY-0.5 ) {
            System.out.println("YOU REACHED THE END");
            Sound music = new Sound("assets/music/Dungeon.wav"); 
            Lutebox.audio.stop(music);
            LootquestGame.reload();
        }
        
    }   

}
