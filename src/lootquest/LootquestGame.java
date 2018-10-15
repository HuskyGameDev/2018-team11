package lootquest;

//<<<<<<< HEAD
//import lootquest.dungeon.world.World;
import lootquest.system.AISystem;
//=======
import lootquest.dungeon.World;
//>>>>>>> master
import lootquest.system.MapRenderSystem;
import lootquest.system.MovementSystem;
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
        
        world = new World(16, 16);
        
        // add systems 
        Lutebox.scene.addSystem(new MapRenderSystem(48)); 
        Lutebox.scene.addSystem(new UseSwordSystem());
        Lutebox.scene.addSystem(new MovementSystem());
        Lutebox.scene.addSystem(new WorldPhysicsSystem()); 
        Lutebox.scene.addSystem(new AISystem());
        // add entities
        EntityFactory.createPlayer(2, 2); 
        EntityFactory.createEnemy1(5, 6);

    }
    
    public static void main(String[] args) {
        Lutebox.start(new LootquestGame()); 
    }
    
}
