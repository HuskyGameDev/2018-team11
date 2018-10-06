package lutebox.core;

/**
 * 
 * Your actual game implementation. Setup the starting 
 * scene in this class. 
 * 
 * @author Nicholas Hamilton 
 *
 */
public abstract class GameListener {

    public static final int DEFAULT_PRIORITY = 1000; 
    
    private int priority = DEFAULT_PRIORITY; 
    
    public GameListener() {} 
    
    public GameListener(int priority) {
        this.priority = priority; 
    }
    
    public final int getPriority() {
        return priority; 
    }
    
    /**
     * Called when the game is starting. 
     * All subsystems have been initialized at this point. 
     */
    public void init() {} 
    
    /**
     * Called before the scene is updated. 
     */
    public void preUpdate() {} 
    
    /**
     * Called after the scene is updated. 
     */
    public void postUpdate() {} 
    
    /**
     * Called before the scene is rendered. 
     */
    public void preRender() {} 
    
    /**
     * Called after the scene is rendered. 
     */
    public void postRender() {} 
    
}
