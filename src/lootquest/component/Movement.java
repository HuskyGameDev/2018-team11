package lootquest.component;

import lutebox.ecs.Component;

public class Movement extends Component {

	public float maxSpeed = 1.0f; 
    private float dx, dy; 
    
    public Movement zero() {
    	dx = dy = 0; 
    	return this; 
    }
    
    public Movement fromDirection(Direction d) {
    	if (!d.moving) return set(0, 0); 
    	
    	switch (d.direction) {
    	case Direction.UP: return set(0, -maxSpeed); 
    	case Direction.DOWN: return set(0, maxSpeed); 
    	case Direction.LEFT: return set(-maxSpeed, 0); 
    	case Direction.RIGHT: return set(maxSpeed, 0); 
    	}
    	
    	return set(0, 0); 
    }
    
    public Movement set(float dx, float dy) {
        this.dx = dx; 
        this.dy = dy; 
        limitSpeed(); 
        return this; 
    }
    
    public Movement setMaxSpeed(float speed) {
    	maxSpeed = speed; 
    	return this; 
    }
    
    public float getDx() { return dx; } 
    public float getDy() { return dy; } 
    
    public float getSpeed() {
    	return (float) Math.sqrt(dx * dx + dy * dy); 
    }
    
    public void limitSpeed() {
    	float speed = getSpeed(); 
    	
    	if (speed > maxSpeed) {
    		float scale = maxSpeed / speed; 
    		dx *= scale; 
    		dy *= scale; 
    	}
    }
    
//    private void normalize() {
//    	float len2 = dx * dx + dy * dy; 
//    	
//    	if (Math.abs(len2) < 0.0001f) {
//    		dx = dy = 0; 
//    	}
//    	else {
//    		float invLen = 1.0f / (float) Math.sqrt(len2);
//    		dx *= invLen; 
//    		dy *= invLen; 
//    	}
//    }
    
}
