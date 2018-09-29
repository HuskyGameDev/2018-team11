package lutebox.ecs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SystemManager {

    private List<SceneSystem> systems = new ArrayList<>(); 
    private List<SceneSystem> constSystems = Collections.unmodifiableList(systems); 
    private Scene scene; 
    
    public SystemManager(Scene scene) {
        this.scene = scene; 
    }
    
    public List<SceneSystem> getSystems() {
        return constSystems; 
    }
    
    public void addSystem(SceneSystem system) {
        systems.add(system); 
        systems.sort(new Comparator<SceneSystem>() {
            public int compare(SceneSystem a, SceneSystem b) {
                return a.getPriority() - b.getPriority(); 
            }
        });
        system.scene = scene; 
        system.onAdded(); 
    }
    
    public boolean removeSystem(SceneSystem system) {
        return systems.remove(system); 
    }
    
}
