package lutebox.ecs;

import java.util.List;

public class Scene {

    private EntityManager entities = new EntityManager(this); 
    private FilterRegistry filters = new FilterRegistry(this); 
    private SystemManager systems = new SystemManager(this); 
    private boolean updating = false; 
    
    void addEntity(Entity e) {
        entities.addEntity(e, updating); 
    }
    
    FilterRegistry getFilterRegistry() {
        return filters; 
    }
    
    public Entity createEntity() {
        Entity e = new Entity(); 
        addEntity(e); 
        return e; 
    }
    
    public List<Entity> getEntities() {
        return entities.getEntities(); 
    }
    
    public List<Entity> getEntities(Filter f) {
        return filters.getFilter(f); 
    }
    
    public void addSystem(SceneSystem system) {
        systems.addSystem(system); 
    }
    
    public void update() {
        updating = true; 
        for (SceneSystem system : systems.getSystems()) {
            system.update(); 
        }
        updating = false; 
        entities.flush(); 
    }
    
    public void render() {
        updating = true; 
        for (SceneSystem system : systems.getSystems()) {
            system.render(); 
        }
        updating = false; 
        entities.flush(); 
    }
    
    void onAddEntity(Entity e) {
        filters.onAddEntity(e); 
    }
    
    void onRemoveEntity(Entity e) {
        filters.onRemoveEntity(e); 
    }
    
    void onUpdateEntity(Entity e) {
        entities.updateEntity(e, updating); 
    }
    
}
