package lootquest;

import java.util.Random;

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
        //Player
        EntityFactory.createPlayer(world.getSpawnX(), world.getSpawnY()); 
        //Enemies
        String[][] flr = world.getFloor();
        for ( int y = 0; y < 10; y++ ) {
            for ( int x = 0; x < 10; x++ ) {
                if ( flr[x][y].equals("X") ) {
                    Random r = new Random();
                    //int numE = r.nextInt(3);
                    for ( int e = 0; e < 3; e++ ) {
                        EntityFactory.createEnemy1((tiles * x) + (tiles)/2, (tiles * y) + (tiles/2));
                    }
                }
            }
        }
        
        
    }
    
    public static void main(String[] args) {
        Lutebox.start(new LootquestGame()); 
    }
    
}
