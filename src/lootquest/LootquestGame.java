package lootquest;

import lootquest.dungeon.World;
import lootquest.system.AISystem;
import lootquest.system.MapRenderSystem;
import lootquest.system.MovementSystem;
import lootquest.system.DeathSystem;
import lootquest.system.PlayerInputSystem;
import lootquest.system.UpdateFromMovementSystem;
import lootquest.system.UseSwordSystem;
import lootquest.system.WorldPhysicsSystem;
import lutebox.core.GameListener;
import lutebox.core.Lutebox;

public class LootquestGame extends GameListener {
    // add map
    public static World world; 
    
    public static float scale = 48; 
    
    public void init() {
        Lutebox.display.setTitle("Lootquest: Depths of Koderia");
        Lutebox.display.setSize(800, 600);
        Lutebox.cursor.setVisible(true); 
        
        int tiles = 16;
        int rooms = tiles * 10;
        try {
            // int #ofRoomsX, #ofRoomsY, #ofTiles/RoomX, #ofTiles/RoomY
            world = new World(rooms, rooms, tiles, tiles);
        } catch (Exception e) {
            System.out.println("Something went wrong with world creating. Look at the world call to see if it is properly called.");
            e.printStackTrace();
        }
        
        // add render systems 
        Lutebox.scene.addSystem(new MapRenderSystem(48)); 
        
        // add update systems 
        Lutebox.scene.addSystem(new AISystem());
        Lutebox.scene.addSystem(new PlayerInputSystem());
        Lutebox.scene.addSystem(new UseSwordSystem());
        
        Lutebox.scene.addSystem(new MovementSystem());
        Lutebox.scene.addSystem(new UpdateFromMovementSystem()); 
        
        Lutebox.scene.addSystem(new DeathSystem());
        
        Lutebox.scene.addSystem(new WorldPhysicsSystem()); 
        
        // add entities
        EntityFactory.createPlayer(world.getSpawnX(), world.getSpawnY()); 
        EntityFactory.createEnemy1(world.getSpawnX()+5, world.getSpawnY()+5);

    }
    
    public static void main(String[] args) {
        Lutebox.start(new LootquestGame()); 
    }
    
}
