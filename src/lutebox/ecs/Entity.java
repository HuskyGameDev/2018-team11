package lutebox.ecs;

import java.util.HashMap;
import java.util.Map;

import lutebox.util.BitField;

public class Entity {

    private Map<Integer, Component> compMap = new HashMap<>(); 
    private boolean destroyed = false; 
    
    BitField compBits = new BitField(); 
    BitField filterBits = new BitField(); 
    
    Scene scene; 
    
    private boolean contains(int id) {
        return compBits.get(id); 
    }
    
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
    
    @SuppressWarnings("unchecked")
    public <T extends Component> T get(Class<T> type) {
        return (T) get(ComponentId.get(type)); 
    }

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
    
    public <T extends Component> T attach(Class<T> type) {
        try {
            return attach(type.newInstance());
        } catch (Exception e) {
            throw new IllegalArgumentException("Component cannot be automatically instanciated: " + type); 
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
    
    public <T extends Component> boolean detach(Class<T> type) {
        return detach(ComponentId.get(type)); 
    }
    
    public void destroy() {
        destroyed = true; 
    }
    
    public boolean isDestroyed() {
        return destroyed; 
    }
    
}
