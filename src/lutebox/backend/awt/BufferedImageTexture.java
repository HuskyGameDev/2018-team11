package lutebox.backend.awt;

import java.awt.image.BufferedImage;

import lutebox.backend.NativeTexture;

public class BufferedImageTexture implements NativeTexture {

    private BufferedImage image; 
    
    public BufferedImageTexture(int w, int h) {
        image = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB); 
    }
    
    public BufferedImage getImage() {
        return image; 
    }
    
    @Override
    public void setData(int x, int y, int width, int height, int[] argb) {
        image.setRGB(x, y, width, height, argb, 0, width); 
    }

}
