package lutebox.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

// list of elements, with reusable IDs
public class IdList<T> {

    private List<T> list = new ArrayList<>(); 
    private Queue<Integer> available = new LinkedList<>(); 
    private Set<Integer> availableSet = new HashSet<>(); 
    
    public T get(int id) {
        return list.get(id); 
    }
    
    public boolean inBounds(int id) {
        return id >= 0 && id < list.size(); 
    }
    
    public boolean isUsed(int id) {
        return inBounds(id) && !availableSet.contains(id); 
    }
    
    public boolean isAvailable(int id) {
        return inBounds(id) && availableSet.contains(id); 
    }
    
    public int add(T t) {
        if (available.isEmpty()) {
            list.add(t); 
            return list.size() - 1; 
        }
        else {
            int i = available.poll(); 
            availableSet.remove(i); 
            list.set(i, t); 
            return i; 
        }
    }
    
    public void free(int id) {
        checkBounds(id); 
        if (isAvailable(id)) throw new IllegalStateException("ID is already free: " + id); 
        
        list.set(id, null); 
        available.add(id); 
    }
    
    private void checkBounds(int id) {
        if (!inBounds(id)) throw new IllegalStateException("ID out of bounds: " + id);
    }
    
}
