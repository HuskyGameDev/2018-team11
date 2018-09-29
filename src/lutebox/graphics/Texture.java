package lutebox.graphics;

import lutebox.backend.NativeTexture;
import lutebox.core.Lutebox;

public class Texture {

    private NativeTexture internal; 
    private int width, height; 
    
    public Texture(int width, int height) {
        this.width = width; 
        this.height = height; 
        this.internal = Lutebox.graphicsBackend.createNativeTexture(width, height); 
    }
    
    public NativeTexture getNativeTexture() {
        return internal; 
    }
    
    public int getWidth() {
        return width; 
    }
    
    public int getHeight() {
        return height; 
    }
    
    public void setPixels(int x, int y, int w, int h, int[] argb) {
        internal.setData(x, y, w, h, argb); 
    }
    
}
