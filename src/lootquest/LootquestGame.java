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
        
        try {
            world = new World(32, 32, 16, 16);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println("A room file was not found or failed to load");
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
        EntityFactory.createPlayer(4, 4); 
        EntityFactory.createEnemy1(7, 7);

    }
    
    public static void main(String[] args) {
        Lutebox.start(new LootquestGame()); 
    }
    
}
