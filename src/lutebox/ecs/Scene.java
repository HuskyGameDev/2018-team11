package lutebox.ecs;

import java.util.List;

/**
 * 
 * Handles most of the logic of the game. 
 * Updates systems and contains entities. 
 * 
 * @author Nicholas Hamilton 
 *
 */
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
    
    /**
     * Creates and returns a new entity that is linked with this 
     * scene. 
     */
    public Entity createEntity() {
        Entity e = new Entity(); 
        addEntity(e); 
        return e; 
    }
    
    /**
     * Returns an immutable list of all entities linked with this 
     * scene. This list is updated as entities are added and removed. 
     */
    public List<Entity> getEntities() {
        return entities.getEntities(); 
    }
    
    /**
     * Returns an immutable list of all entities linked with this 
     * scene, that match the filter given. The list is updated as 
     * entities are added and removed. 
     */
    public List<Entity> getEntities(Filter f) {
        return filters.getFilter(f); 
    }
    
    /**
     * Add a system to the scene. 
     */
    public void addSystem(SceneSystem system) {
        systems.addSystem(system); 
    }
    
    /**
     * Update all systems in the scene. Most of these systems will 
     * handle a filtered list of entities. 
     */
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
