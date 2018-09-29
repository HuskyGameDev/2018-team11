package lutebox.core;

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
    
    public void init() {} 
    
    public void preUpdate() {} 
    
    public void postUpdate() {} 
    
    public void preRender() {} 
    
    public void postRender() {} 
    
}
