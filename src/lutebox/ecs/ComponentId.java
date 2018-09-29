package lutebox.ecs;

import java.util.HashMap;
import java.util.Map;

public final class ComponentId {

    private ComponentId() {} 
    
    private static int nextId = 0; 
    private static Map<Class<?>, Integer> map = new HashMap<>(); 
    
    public static int get(Class<? extends Component> type) {
        if (map.containsKey(type)) {
            return map.get(type); 
        }
        else {
            int id = nextId++; 
            map.put(type, id); 
            return id; 
        }
    }
    
}
