package lutebox.ecs;

public abstract class SceneSystem {

    public static final int DEFAULT_PRIORITY = 1000; 
    
    private int priority; 
    private boolean enabled = true; 
    
    Scene scene; 
    
    public SceneSystem(int priority) {
        this.priority = priority; 
    }
    
    public SceneSystem() {
        this(DEFAULT_PRIORITY); 
    }
    
    public final Scene getScene() {
        return scene; 
    }
    
    public final int getPriority() {
        return priority; 
    }
    
    public final boolean isEnabled() {
        return enabled; 
    }
    
    public void setEnabled(boolean enabled) {
        this.enabled = enabled; 
    }
    
    public void onAdded() {} 
    
    public void update() {} 
    
    public void render() {} 
    
}
