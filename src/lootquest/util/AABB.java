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
    
    /**
     * Check if this AABB intersects with another AABB 
     */
    public boolean intersects(AABB other) {
        if (x + w < other.x || x > other.x + other.w) return false; 
        if (y + h < other.y || y > other.y + other.h) return false;
        return true; 
    }
    
}
