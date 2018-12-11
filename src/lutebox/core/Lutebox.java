package lutebox.core;

import java.awt.event.KeyEvent;

import lutebox.audio.Audio;
import lutebox.backend.AudioBackend;
import lutebox.backend.Backend;
import lutebox.backend.GraphicsBackend;
import lutebox.backend.awt.AwtBackend;
import lutebox.ecs.Scene;
import lutebox.graphics.Graphics;
import lutebox.input.Cursor;
import lutebox.input.Input;

/**
 * 
 * Manages the game and important subsystems 
 * 
 * @author Nicholas Hamilton 
 *
 */
public class Lutebox {

    /**
     * Settings for the mouse cursor 
     */
    public static Cursor cursor; 
    
    /**
     * Display interface. 
     * Allows you to set the title and window size, etc. 
     */
    public static Display display; 
    
    /**
     * Graphics interface. 
     * Use this to draw lines, shapes, images, etc. 
     */
    public static Graphics graphics; 
    
    /**
     * Audio interface. 
     * Use this to play, pause, stop sounds. 
     */
    public static Audio audio; 
    
    /**
     * Input interface. 
     * Use this to get information about the keyboard and mouse. 
     */
    public static Input input; 
    
    /**
     * Current scene. 
     * The core part of the game. 
     */
    public static Scene scene; 
    
    /**
     * Current game camera. 
     */
    public static Camera camera; 
    
    /**
     * For internal use only. 
     */
    public static GraphicsBackend graphicsBackend; 
    
    /**
     * For internal use only. 
     */
    public static AudioBackend audioBackend; 
    
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
        audio = backend.getAudio(); 
        
        scene = new Scene(); 
        camera = new Camera(0, 0, 48); 
        
        graphicsBackend = backend.getGraphicsBackend();
        audioBackend = backend.getAudioBackend(); 
    }
    
    /**
     * Starts the game with a default configuration. 
     * @see start(Config, GameListener) 
     */
    public static void start(GameListener game) {
        start(null, game); 
    }
    
    /**
     * Starts the game with a default configuration. 
     */
    public static void start(Config config, GameListener game) {
        if (running) throw new IllegalStateException("Game is already running");
        if (config == null) config = new Config();
        
        initSystems(); 
        
        Lutebox.game = game; 
        
        display.setTitle(config.title); 
        display.setSize(config.width, config.height); 
        display.setVisible(true); 
        
        running = true; 
        gameLoop(); 
    }
    
    /**
     * Quits the game after the current frame. 
     */
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
