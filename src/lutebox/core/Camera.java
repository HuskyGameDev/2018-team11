package lutebox.core;

import lutebox.util.AABB;

public class Camera {

    private float x, y; 
    private float size; 
    
    public Camera(float x, float y, float size) {
        this.x = x; 
        this.y = y; 
        this.size = size; 
    }
    
    public float getX() {
        return x; 
    }
    
    public float getY() {
        return y; 
    }
    
    public float getUnitSize() {
        return size; 
    }
    
    public void setPosition(float x, float y) {
        this.x = x; 
        this.y = y; 
    }
    
    public void setUnitSize(float size) {
        this.size = size; 
    }
    
    public AABB computeViewport() {
        float width = Lutebox.display.getWidth() / size; 
        float height = Lutebox.display.getHeight() / size; 
        
        return new AABB(x - width/2, y - height/2, width, height); 
    }
    
}
