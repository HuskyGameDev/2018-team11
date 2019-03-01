package lootquest;

import java.util.List;

import java.util.Random;

import lootquest.component.Consumable;
import lootquest.component.Enemy;
import lootquest.component.Health;
import lootquest.component.Player;
import lootquest.component.HealthBar;
import lootquest.component.Position;
import lootquest.component.Size;
import lootquest.dungeon.World;
import lootquest.system.AISystem;
import lootquest.system.AnimationSystem;
import lootquest.system.BossSystem;
import lootquest.system.ConsumableSystem;
import lootquest.system.DeathSystem;
import lootquest.system.DungeonLoopSystem;
import lootquest.system.FollowPlayerCameraSystem;
import lootquest.system.GoldSystem;
import lootquest.system.MovementSystem;
import lootquest.system.HealthDisplaySystem;
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
import lutebox.ecs.Entity;
import lutebox.ecs.Filter;

public class LootquestGame extends GameListener {
    // add map
    public static World world; 
//    public static float scale = 48;

    public static int endX = -1;
    public static int endY = -1;
    public static boolean end = false;
    public static RenderSystem render;
    public static DungeonLoopSystem dls;
    public static HealthDisplaySystem phds;
    
    public static LootquestGame game;

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
        
        endX = world.getExitX();
        endY = world.getExitY();
        
        // add render systems 
        render = new RenderSystem();
        Lutebox.scene.addSystem(render); 
        
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
        
        Lutebox.scene.addSystem(new GoldSystem());
        
        dls = new DungeonLoopSystem(endX, endY);
        Lutebox.scene.addSystem(dls);
        
        Lutebox.scene.addSystem(new BossSystem());
        
        phds = new HealthDisplaySystem();
        Lutebox.scene.addSystem(phds);
        
        // add entities
        //Player
        EntityFactory.createPlayer(world.getSpawnX(), world.getSpawnY()); 
        
        //Enemies
        String[][] flr = world.getFloor();

        for ( int y = 0; y < flr[0].length; y++ ) {
            for ( int x = 0; x < flr.length; x++ ) {
            	if (flr[x][y].equals("E")) {
                	float [] point = world.getEnemySpawn(x, y);
                	//EntityFactory.createConsumable(point[0], point[1], 2, 0, 0);
                	EntityFactory.createEnemyBoss(point[0], point[1]);

                }else if ( flr[x][y].equals("X") ) {
                    Random r = new Random();
                    int roomType = r.nextInt(3);
                    if ( roomType == 0 ) {
                        float [] point = world.getEnemySpawn(x, y);
                        EntityFactory.createConsumable(point[0], point[1], 2, 0, 0);
                    }
                    for ( int e = 0; e < 3; e++ ) {
                        EntityFactory.createEnemy1((float) ((tiles * x) + (tiles)/2 + r.nextInt(tiles/2) - tiles/4), (float) ((tiles * y) + (tiles/2) + r.nextInt(tiles/2) - tiles/4), (int) (Math.random() * 3));
                    }
                    
                } else if ( flr[x][y].equals("S") ) {
                    float [] point = world.getEnemySpawn(x, y);
                    EntityFactory.createConsumable(point[0], point[1], 2, 0, 0);
                    EntityFactory.createEnemyBoss(point[0], point[1]);
                }
            }
        }

        //consumable
//        EntityFactory.createConsumable(world.getSpawnX() + 1, world.getSpawnY() + 1, 1, 0, 0);
        
        Sound music = new Sound("assets/music/Dungeon.wav"); 
        Lutebox.audio.play(music, true, true); 
    }
    
    public static void reload( ) {
        System.out.println("It started the reload");
        int tiles = 16;
        int rooms = tiles * 10;
        world = new World(rooms, rooms, tiles, tiles);
        
        //Get rid of old enemies
        List<Entity> enemyList = Lutebox.scene.getEntities(Filter.include(Enemy.class, Position.class, Size.class).create());
        for ( int i = 0; i < enemyList.size(); i++ ) {
            Entity enemy = enemyList.get(i);
            enemy.destroy();
        }
        
        //Get rid of old consumables
        List<Entity> consList = Lutebox.scene.getEntities(Filter.include(Consumable.class).create());
        for ( int i = 0; i < consList.size(); i++ ) {
            Entity cons = consList.get(i);
            cons.destroy();
        }
        Lutebox.graphics.clear();
        
        //Find the new exit
        endX = world.getExitX();
        endY = world.getExitY();
        dls.setXY(endX, endY);
        
        //Player
      List<Entity> playerList = Lutebox.scene.getEntities(Filter.include(Player.class, Position.class, Size.class).create());
      Entity player = playerList.get(0);
      player.get(Position.class).x = world.getSpawnX();
      player.get(Position.class).y = world.getSpawnY();
        
      //Destroying all non-player health bars
      List<Entity> healthBarList = Lutebox.scene.getEntities(Filter.include(HealthBar.class).create());
      for ( int i = 0; i < healthBarList.size(); i++ ) {
          Entity healthBar = healthBarList.get(i);
          Entity thing = healthBar.get(HealthBar.class).above;
          
          if ( !thing.contains(Player.class) ) {
              healthBar.destroy();
          }
      }
        
        //Enemies and Consumables
        String[][] flr = world.getFloor();

        for ( int y = 0; y < flr[0].length; y++ ) {
            for ( int x = 0; x < flr.length; x++ ) {
                if (flr[x][y].equals("E")) {
                    float [] point = world.getEnemySpawn(x, y);
                    //EntityFactory.createConsumable(point[0], point[1], 2, 0, 0);
                    EntityFactory.createEnemyBoss(point[0], point[1]);

                }else if ( flr[x][y].equals("X") ) {
                    Random r = new Random();
                    int roomType = r.nextInt(3);
                    if ( roomType == 0 ) {
                        float [] point = world.getEnemySpawn(x, y);
                        EntityFactory.createConsumable(point[0], point[1], 2, 0, 0);
                    }
                    for ( int e = 0; e < 3; e++ ) {
                        EntityFactory.createEnemy1((float) ((tiles * x) + (tiles)/2 + r.nextInt(tiles/2) - tiles/4), (float) ((tiles * y) + (tiles/2) + r.nextInt(tiles/2) - tiles/4), (int) (Math.random() * 3));
                    }
                    
                } else if ( flr[x][y].equals("S") ) {
                    float [] point = world.getEnemySpawn(x, y);
                    EntityFactory.createConsumable(point[0], point[1], 2, 0, 0);
                }
            }
        }
        
        
        System.out.println("It finished the reload");
    }
    
    public static void reloadNEW( ) {
        System.out.println("It started the reload");
        int tiles = 16;
        int rooms = tiles * 10;
        world = new World(rooms, rooms, tiles, tiles);
        
        //Get rid of old enemies
        List<Entity> enemyList = Lutebox.scene.getEntities(Filter.include(Enemy.class, Position.class, Size.class).create());
        for ( int i = 0; i < enemyList.size(); i++ ) {
            Entity enemy = enemyList.get(i);
            enemy.destroy();
        }
        
        //Get rid of old consumables
        List<Entity> consList = Lutebox.scene.getEntities(Filter.include(Consumable.class).create());
        for ( int i = 0; i < consList.size(); i++ ) {
            Entity cons = consList.get(i);
            cons.destroy();
        }
        Lutebox.graphics.clear();
        
        //Find the new exit
        endX = world.getExitX();
        endY = world.getExitY();
        dls.setXY(endX, endY);
        
        //Player
        List<Entity> playerList = Lutebox.scene.getEntities(Filter.include(Player.class, Position.class, Size.class).create());
        Entity player = playerList.get(0);
        player.destroy();
        
        List<Entity> healthBarList = Lutebox.scene.getEntities(Filter.include(HealthBar.class).create());
        for ( int i = 0; i < healthBarList.size(); i++ ) {
            Entity healthBar = healthBarList.get(i);
            healthBar.destroy();
        }
        
        EntityFactory.createPlayer(world.getSpawnX(), world.getSpawnY()); 
        //EntityFactory.createPlayerHealthBar(world.getSpawnX(), world.getSpawnY());
        
        //Enemies and Consumables
        String[][] flr = world.getFloor();

        for ( int y = 0; y < flr[0].length; y++ ) {
            for ( int x = 0; x < flr.length; x++ ) {
                if (flr[x][y].equals("E")) {
                    float [] point = world.getEnemySpawn(x, y);
                    //EntityFactory.createConsumable(point[0], point[1], 2, 0, 0);
                    EntityFactory.createEnemyBoss(point[0], point[1]);

                }else if ( flr[x][y].equals("X") ) {
                    Random r = new Random();
                    int roomType = r.nextInt(3);
                    if ( roomType == 0 ) {
                        float [] point = world.getEnemySpawn(x, y);
                        EntityFactory.createConsumable(point[0], point[1], 2, 0, 0);
                    }
                    for ( int e = 0; e < 3; e++ ) {
                        EntityFactory.createEnemy1((float) ((tiles * x) + (tiles)/2 + r.nextInt(tiles/2) - tiles/4), (float) ((tiles * y) + (tiles/2) + r.nextInt(tiles/2) - tiles/4), (int) (Math.random() * 3));
                    }
                    
                } else if ( flr[x][y].equals("S") ) {
                    float [] point = world.getEnemySpawn(x, y);
                    EntityFactory.createConsumable(point[0], point[1], 2, 0, 0);
                }
            }
        }
        
        
        System.out.println("It finished the reload");
    }
    
    public static void main(String [] args) {
        game = new LootquestGame();
        Lutebox.start( null, game);
    }
}
