package lootquest.component;

import lutebox.ecs.Component;

public class Size extends Component {

    public float w, h; 
    
    public Size set(float w, float h) {
        this.w = w; 
        this.h = h; 
        return this; 
    }
    
}
