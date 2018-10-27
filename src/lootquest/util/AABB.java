package lootquest.util;

/**
 * 
 * Axis-aligned bounding box. Useful for testing collisions
 * 
 * @author Nicholas Hamilton 
 *
 */
public class AABB {

    public float x, y; 
    public float w, h; 
    
    public AABB(float x, float y, float w, float h) {
        this.x = x; 
        this.y = y; 
        this.w = w; 
        this.h = h; 
    }
    
    public AABB set(AABB from) {
    	x = from.x; 
    	y = from.y; 
    	w = from.w; 
    	h = from.h; 
    	return this; 
    }
    
    public AABB set(float x, float y, float w, float h) {
    	this.x = x; 
    	this.y = y; 
    	this.w = w; 
    	this.h = h; 
    	return this; 
    }
    
    /**
     * Check if this AABB intersects with another AABB 
     */
    public boolean intersects(AABB other) {
        if (x + w <= other.x || x >= other.x + other.w) return false; 
        if (y + h <= other.y || y >= other.y + other.h) return false;
        return true; 
    }
    
    public float getIntersectionArea(AABB other) {
    	if (!intersects(other)) return 0.0f; 
    	
    	float xStart = Math.max(x, other.x);
    	float xEnd = Math.min(x + w, other.x + other.w); 
    	float yStart = Math.max(y, other.y); 
    	float yEnd = Math.min(y + h, other.y + other.h); 
    	
    	return (xEnd - xStart) * (yEnd - yStart); 
    }
    
    public float getArea() {
    	return w * h; 
    }
    
    public String toString() {
    	return "[" + x + ", " + y + ", " + w + ", " + h + "]"; 
    }
    
}
