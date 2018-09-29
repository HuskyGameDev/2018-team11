package test.component;

import lutebox.ecs.Component;

public class Size extends Component {

    public float xOffset = 0; 
    public float yOffset = 0; 
    public float width = 1; 
    public float height = 1; 
    
    public void set(float x, float y, float w, float h) {
        xOffset = x; 
        yOffset = y; 
        width = w; 
        height = h; 
    }
    
}
