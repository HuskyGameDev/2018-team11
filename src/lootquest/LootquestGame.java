package lootquest;

import java.util.Random;

import lootquest.dungeon.World;
import lootquest.system.AISystem;
import lootquest.system.ConsumableSystem;
import lootquest.system.DeathSystem;
import lootquest.system.FollowPlayerCameraSystem;
import lootquest.system.MovementSystem;
import lootquest.system.PlayerInputSystem;
import lootquest.system.ProjectileSystem;
import lootquest.system.RenderSystem;
import lootquest.system.UpdateFromMovementSystem;
import lootquest.system.UseRangedSystem;
import lootquest.system.UseSwordSystem;
import lootquest.system.WorldPhysicsSystem;
import lutebox.audio.Sound;
import lutebox.core.GameListener;
import lutebox.core.Lutebox;

public class LootquestGame extends GameListener {
    // add map
    public static World world; 
//    public static float scale = 48; 
    
    public void init() {
        Lutebox.display.setTitle("Lootquest: Depths of Koderia");
        Lutebox.display.setSize(800, 600);
        Lutebox.cursor.setVisible(true); 
        Lutebox.camera.setUnitSize(64); 
        
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
        Lutebox.scene.addSystem(new RenderSystem(48)); 
        
        // add update systems 
        Lutebox.scene.addSystem(new FollowPlayerCameraSystem());
        Lutebox.scene.addSystem(new AISystem());
        Lutebox.scene.addSystem(new PlayerInputSystem());
        Lutebox.scene.addSystem(new UseSwordSystem());
        Lutebox.scene.addSystem(new UseRangedSystem()); 
        Lutebox.scene.addSystem(new ProjectileSystem());
        
        Lutebox.scene.addSystem(new MovementSystem());
        Lutebox.scene.addSystem(new UpdateFromMovementSystem()); 
        
        Lutebox.scene.addSystem(new DeathSystem());
        
        Lutebox.scene.addSystem(new WorldPhysicsSystem());
        
        Lutebox.scene.addSystem(new ConsumableSystem());
        
        // add entities
        //Player
        EntityFactory.createPlayer(world.getSpawnX(), world.getSpawnY()); 
        //Enemies
        String[][] flr = world.getFloor();
<<<<<<< HEAD
        for ( int y = 0; y < 2; y++ ) {
            for ( int x = 0; x < 2; x++ ) {
                if ( flr[x][y].equals("X") ) {
                    for ( int e = 0; e < 3; e++ ) {
                        int [] point = world.getEnemySpawn(x, y);
                        EntityFactory.createEnemy1(point[0], point[1]);
=======
        for ( int y = 0; y < flr[0].length; y++ ) {
            for ( int x = 0; x < flr.length; x++ ) {
                if (flr[x][y].equals("S") || flr[x][y].equals("E")) {
                	Random r = new Random();
                	EntityFactory.createConsumable((float) ((tiles * x) + (tiles)/2 + r.nextInt(tiles/2) - tiles/4),(float) ((tiles * y) + (tiles/2) + r.nextInt(tiles/2) - tiles/4) , 1, 0, 0);
                }else if ( flr[x][y].equals("X") ) {
                    Random r = new Random();
                    for ( int e = 0; e < 3; e++ ) {
                        EntityFactory.createEnemy1((float) ((tiles * x) + (tiles)/2 + r.nextInt(tiles/2) - tiles/4), (float) ((tiles * y) + (tiles/2) + r.nextInt(tiles/2) - tiles/4), 
                        		(int) (Math.random() * 3));
>>>>>>> 23c1825a6bf916e30224886e7d6be917e0297883
                    }
                } else if ( flr[x][y].equals("S") ) {
                    int [] point = world.getEnemySpawn(x, y);
                    EntityFactory.createConsumable(point[0], point[1], 1, 0, 0);
                }
            }
        }
<<<<<<< HEAD

=======
>>>>>>> 23c1825a6bf916e30224886e7d6be917e0297883
        //consumable
//        EntityFactory.createConsumable(world.getSpawnX() + 1, world.getSpawnY() + 1, 1, 0, 0);
        
<<<<<<< HEAD
=======
        Sound music = new Sound("assets/music/Dungeon.wav"); 
        Lutebox.audio.play(music, true, true); 
>>>>>>> 23c1825a6bf916e30224886e7d6be917e0297883
    }
    
    public static void main(String[] args) {
        Lutebox.start(new LootquestGame()); 
    }
}
