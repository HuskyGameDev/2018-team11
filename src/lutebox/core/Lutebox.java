package lutebox.core;

import java.awt.event.KeyEvent;

import lutebox.backend.Backend;
import lutebox.backend.GraphicsBackend;
import lutebox.backend.awt.AwtBackend;
import lutebox.ecs.Scene;
import lutebox.graphics.Graphics;
import lutebox.input.Cursor;
import lutebox.input.Input;

public class Lutebox {

    public static Cursor cursor; 
    public static Display display; 
    public static Graphics graphics; 
    public static Input input; 
    public static Scene scene; 
    
    public static GraphicsBackend graphicsBackend; 
    
    public static float deltaTime = 1 / 60f; 
    
    private static GameListener game; 
    
    private static Backend backend; 
    private static boolean running = false; 
    
    private static void initSystems() {
        backend = new AwtBackend(); 
        
        cursor = backend.getCursor(); 
        display = backend.getDisplay(); 
        graphics = backend.getGraphics(); 
        input = backend.getKeyboard(); 
        
        scene = new Scene(); 
        
        graphicsBackend = backend.getGraphicsBackend(); 
    }
    
    public static void start(GameListener game) {
        start(null, game); 
    }
    
    public static void start(Config config, GameListener game) {
        if (config == null) config = new Config();
        
        initSystems(); 
        
        Lutebox.game = game; 
        
        display.setTitle(config.title); 
        display.setSize(config.width, config.height); 
        display.setVisible(true); 
        
        if (running) throw new IllegalStateException("Game is already running");
        running = true; 
        gameLoop(); 
    }
    
    public static void stop() {
        if (!running) System.out.println("[WARNING] Game is not running, but it is trying to be stopped");
        
        running = false; 
    }
    
    private static void gameLoop() {
        long tickTimer = System.nanoTime(); 
        int skipTicks = 1000000000 / 60; 
        
        game.init(); 
        
        while (running) 
        {
            backend.update(); 
            graphics.setColor(0); 
            graphics.clear(); 
            
            while (tickTimer < System.nanoTime()) {
                game.preUpdate(); 
                scene.update(); 
                game.postUpdate(); 
                tickTimer += skipTicks; 
            }
            
            game.preRender(); 
            scene.render(); 
            game.postRender(); 
            
            if (display.shouldClose() || input.getKey(KeyEvent.VK_ESCAPE)) stop(); 
            
            try { Thread.sleep(1); } catch (Exception e) {} 
        }
        
        System.exit(0); 
    }
    
}
