package lutebox.ecs;

import lutebox.util.BitField;

/**
 * 
 * Used for getting specific types of entities. 
 * 
 * @author Nicholas Hamilton 
 *
 */
public class Filter {

    private BitField include; 
    
    public static class Builder {
        
        private BitField include = new BitField(); 
        
        @SuppressWarnings("unchecked")
        public Builder include(Class<? extends Component>... comps) {
            for (int i = 0; i < comps.length; i++) {
                include.set(ComponentId.get(comps[i])); 
            }
            return this; 
        }
        
        public Filter create() {
            return new Filter(include); 
        }
        
    }
    
    private Filter(BitField include) {
        this.include = include; 
    }
    
    @SafeVarargs
    public static Builder include(Class<? extends Component>... comps) {
        return new Builder().include(comps); 
    }
    
    public boolean matches(BitField componentBits) {
        return include.containsAll(componentBits); 
    }
    
    public boolean matches(Entity e) {
        return e.compBits.containsAll(include); 
    }
    
    @Override
    public boolean equals(Object other) {
        if (other == this) return true; 
        if (other == null) return false; 
        if (!(other instanceof Filter)) return false; 
        
        Filter f = (Filter) other; 
        
        return include.equals(f.include); 
    }
    
    @Override
    public int hashCode() {
        return include.hashCode(); 
    }
    
    @Override
    public String toString() {
        return include.toString(); 
    }
    
}
