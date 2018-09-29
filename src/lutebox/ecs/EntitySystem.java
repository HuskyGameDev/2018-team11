package lutebox.ecs;

import java.util.List;

public abstract class EntitySystem extends SceneSystem {

    private Filter filter; 
    
    public EntitySystem(Filter filter) {
        this(SceneSystem.DEFAULT_PRIORITY, filter); 
    }
    
    public EntitySystem(int priority, Filter filter) {
        super(priority); 
        this.filter = filter; 
    }
    
    public Filter getFilter() {
        return filter; 
    }
    
    public final void update() {
        updateEntities(getScene().getEntities(filter)); 
    }
    
    public final void render() {
        renderEntities(getScene().getEntities(filter)); 
    }
    
    public void updateEntities(List<Entity> entities) {} 
    
    public void renderEntities(List<Entity> entities) {} 
    
}
