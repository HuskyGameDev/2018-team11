package lutebox.graphics;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

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
    
    public Texture(String filename) {
        try {
            BufferedImage img = ImageIO.read(new File(filename));
            this.width = img.getWidth(); 
            this.height = img.getHeight(); 
            this.internal = Lutebox.graphicsBackend.createNativeTexture(width, height); 
            
            int[] argb = new int[width * height]; 
            img.getRGB(0, 0, width, height, argb, 0, width); 
            internal.setData(0, 0, width, height, argb); 
        } catch (Exception e) {
            System.out.println("Could not load image: " + filename); 
        } 
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
