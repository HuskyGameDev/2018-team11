package lutebox.ecs;

import java.util.HashMap;
import java.util.Map;

import lutebox.util.BitField;

/**
 * 
 * Container for component 
 * 
 * @author Nicholas Hamilton 
 *
 */
public class Entity {

    private Map<Integer, Component> compMap = new HashMap<>(); 
    private boolean destroyed = false; 
    
    BitField compBits = new BitField(); 
    BitField filterBits = new BitField(); 
    
    Scene scene; 
    
    private boolean contains(int id) {
        return compBits.get(id); 
    }
    
    /**
     * Check if this entity has a specific component 
     */
    public <T extends Component> boolean contains(Class<T> type) {
        return contains(ComponentId.get(type)); 
    }
    
    private Component get(int id) {
        if (contains(id)) {
            return compMap.get(id); 
        }
        else {
            return null; 
        }
    }
    
    /**
     * Get a component of the entity. If the entity does not 
     * have this component, it will return null 
     */
    @SuppressWarnings("unchecked")
    public <T extends Component> T get(Class<T> type) {
        return (T) get(ComponentId.get(type)); 
    }

    /**
     * Attach and return a component of the entity. If the 
     * entity already had a component of this type, it will 
     * be replaced. 
     */
    public <T extends Component> T attach(T comp) {
        if (comp == null) throw new IllegalArgumentException("Cannot attach a null component"); 
        
        int id = ComponentId.get(comp.getClass()); 
        
        Component last = compMap.get(id); 
        
        compBits.set(id); 
        compMap.put(id, comp); 
        
        if (last == null) {
            // not replaced, so this is a new component type 
            if (scene != null) scene.onUpdateEntity(this); 
        }
        return comp; 
    }
    
    /**
     * Attach and return a component of the entity. If the 
     * entity already had a component of this type, it will 
     * be replaced. 
     */
    public <T extends Component> T attach(Class<T> type) {
        try {
            return attach(type.newInstance());
        } catch (Exception e) {
            throw new IllegalArgumentException("Component cannot be automatically instantiated: " + type); 
        } 
    }
    
    private boolean detach(int id) {
        if (contains(id)) {
            compMap.remove(id); 
            compBits.clear(id); 
            if (scene != null) scene.onUpdateEntity(this); 
            return true; 
        }
        else {
            return false; 
        }
    }
    
    /**
     * Remove a component from the entity. 
     * 
     * @return true if the component existed and was removed 
     */
    public <T extends Component> boolean detach(Class<T> type) {
        return detach(ComponentId.get(type)); 
    }
    
    /**
     * Destroy the entity, the entity will be removed from the
     * scene after the current update. 
     */
    public void destroy() {
    	destroyed = true; 
    }
    
    public boolean isDestroyed() {
        return destroyed; 
    }
    
}
