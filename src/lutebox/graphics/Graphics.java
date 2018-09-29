package lutebox.graphics;

public interface Graphics {

    void setColor(int color); 
    
    void clear(); 
    
    void drawLine(float x, float y, float x2, float y2); 
    
    void drawRect(float x, float y, float w, float h);
    
    void fillRect(float x, float y, float w, float h); 
    
    void drawTexture(Texture t, float x, float y, float w, float h); 
    
}
