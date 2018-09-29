package lutebox.ecs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FilterRegistry {

    private int nextId = 0; 
    private List<Entity> entities; 
    private Map<Filter, Entry> entries = new HashMap<>(); 
    private List<Entry> entryList = new ArrayList<>(); 
    
    public FilterRegistry(Scene scene) {
        this.entities = scene.getEntities(); 
    }
    
    public List<Entity> getFilter(Filter f) {
        Entry e = getOrCreateEntry(f); 
        return e.constEntities; 
    }
    
//    public boolean removeFilter(Filter f) {
//        Entry e = entries.get(f); 
//        if (e != null) {
//            e.count--; 
//            if (e.count <= 0) {
//                entries.remove(f); 
//                entryList.remove(e); 
//                return true; 
//            }
//        }
//        return false; 
//    }
    
    public void onAddEntity(Entity e) {
        for (Entry entry : entryList) {
            if (entry.filter.matches(e) && !e.filterBits.get(entry.id)) {
                entry.entities.add(e); 
                e.filterBits.set(entry.id); 
            }
        }
    }
    
    public void onRemoveEntity(Entity e) {
        for (Entry entry : entryList) {
            if (e.filterBits.get(entry.id)) {
                entry.entities.remove(e); 
                e.filterBits.clear(entry.id);
            }
        }
    }
    
    // should already be in entities list 
    public void onUpdateEntity(Entity e) {
        for (Entry entry : entryList) {
            if (e.filterBits.get(entry.id)) {
                if (entry.filter.matches(e)) {
                    // already in, do nothing 
                }
                else {
                    // in, but no longer matches
                    entry.entities.remove(e); 
                }
            }
            else { // not currently in filter 
                if (entry.filter.matches(e)) {
                    // not add, add it
                    entry.entities.add(e); 
                }
                else {
                    // not in, and still does not apply, do nothing 
                }
            }
        }
    }
    
    private Entry getOrCreateEntry(Filter f) {
        Entry e = entries.get(f); 
        if (e == null) {
            e = new Entry(f); 
            entries.put(f, e); 
            entryList.add(e); 
        }
        return e; 
    }
    
    private class Entry {
        Filter filter; 
        int id = nextId++; 
        List<Entity> entities = new ArrayList<>(); 
        List<Entity> constEntities = Collections.unmodifiableList(entities); 
        
        public Entry(Filter f) {
            filter = f; 
            for (Entity e : FilterRegistry.this.entities) {
                if (filter.matches(e)) {
                    e.filterBits.set(id); 
                    this.entities.add(e); 
                }
            }
        }
    }
    
}
