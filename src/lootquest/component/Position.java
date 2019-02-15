package lootquest.component;

import lutebox.ecs.Component;

public class Position extends Component {

    public float x, y; 
    
    public Position set(float x, float y) {
        this.x = x; 
        this.y = y; 
        return this; 
    }
    
    
}
