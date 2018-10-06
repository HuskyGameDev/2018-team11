package lootquest;

import lootquest.dungeon.world.World;
import lootquest.system.MapRenderSystem;
import lootquest.system.UseSwordSystem;
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
        
        world = new World(16, 16);
        
        // add systems 
        Lutebox.scene.addSystem(new MapRenderSystem(48)); 
        Lutebox.scene.addSystem(new UseSwordSystem());
        
        // add entities
        EntityFactory.createPlayer(2, 2); 

    }
    
    public static void main(String[] args) {
        Lutebox.start(new LootquestGame()); 
    }
    
}
