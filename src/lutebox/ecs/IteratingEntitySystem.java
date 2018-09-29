package lutebox.ecs;

public abstract class IteratingEntitySystem extends SceneSystem {

    private Filter filter; 
    
    public IteratingEntitySystem(Filter filter) {
        this(SceneSystem.DEFAULT_PRIORITY, filter); 
    }
    
    public IteratingEntitySystem(int priority, Filter filter) {
        super(priority); 
        this.filter = filter; 
    }
    
    public Filter getFilter() {
        return filter; 
    }
    
    public final void update() {
        preUpdateEntities(); 
        for (Entity e : getScene().getEntities(filter)) {
            updateEntity(e); 
        }
        postUpdateEntities(); 
    }
    
    public final void render() {
        preRenderEntities(); 
        for (Entity e : getScene().getEntities(filter)) {
            renderEntity(e); 
        }
        postRenderEntities(); 
    }
    
    public void preUpdateEntities() {} 
    
    public void postUpdateEntities() {} 
    
    public void updateEntity(Entity e) {} 
    
    public void preRenderEntities() {} 
    
    public void postRenderEntities() {} 
    
    public void renderEntity(Entity e) {} 
    
}
