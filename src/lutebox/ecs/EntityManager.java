package lutebox.ecs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EntityManager {

    private static final int OP_ADD = 0; 
    private static final int OP_REMOVE = 1; 
    private static final int OP_UPDATE = 2;
    
    private Scene scene; 
    private List<Entity> entities = new ArrayList<>(); 
    private List<Entity> constEntities = Collections.unmodifiableList(entities); 
    
    private List<EntityOp> opQueue = new ArrayList<>(); 
    
    public EntityManager(Scene scene) {
        this.scene = scene; 
    }
    
    public Entity createEntity(boolean updating) {
        Entity e = new Entity(); 
        addEntity(e, updating); 
        return e; 
    }
    
    public List<Entity> getEntities() {
        return constEntities; 
    }
    
    public void addEntity(Entity e, boolean wait) {
        if (e.scene != null) {
            throw new IllegalArgumentException("Entity is already owned, cannot add it"); 
        }
        
        if (wait) {
            opQueue.add(new EntityOp(e, OP_ADD)); 
        }
        else {
            e.scene = scene; 
            entities.add(e); 
            scene.onAddEntity(e);
        }
    }
    
    public void removeEntity(Entity e, boolean wait) {
        if (e.scene != scene) {
            throw new IllegalArgumentException("Entity must be owned by manager to remove it"); 
        }
        
        if (wait) {
            opQueue.add(new EntityOp(e, OP_REMOVE)); 
        }
        else {
            e.scene = null; 
            entities.remove(e); 
            scene.onRemoveEntity(e);
        }
    }
    
    public void flush() {
        for (EntityOp op : opQueue) {
            switch (op.op) {
            case OP_ADD: 
                addEntity(op.e, false); 
                break; 
            case OP_REMOVE: 
                removeEntity(op.e, false); 
                break; 
            case OP_UPDATE: 
                updateEntity(op.e, false); 
                break; 
            }
        }
        opQueue.clear(); 
    }
    
    public void updateEntity(Entity e, boolean wait) {
        if (wait) {
            opQueue.add(new EntityOp(e, OP_UPDATE)); 
        }
        else {
            scene.getFilterRegistry().onUpdateEntity(e); 
        }
    }
    
    private class EntityOp {
        Entity e; 
        int op; 
        
        public EntityOp(Entity e, int op) { 
            this.e = e; 
            this.op = op; 
        }
    }
    
}
