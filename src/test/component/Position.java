package test.component;

import lutebox.ecs.Component;

public class Position extends Component {

    public float x; 
    public float y; 
    
    public void set(float x, float y) {
        this.x = x; 
        this.y = y; 
    }
    
}
