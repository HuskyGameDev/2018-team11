package lutebox.backend.awt;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import lutebox.core.Lutebox;
import lutebox.graphics.Graphics;
import lutebox.graphics.Texture;

public class AwtGraphics implements Graphics {

    private int width, height; 
    Graphics2D g; 
    
    public void setGraphics2D(Graphics2D g, int width, int height) {
        this.g = g; 
        this.width = width; 
        this.height = height; 
    }
    
    public void setColor(int argb) {
        g.setColor(new Color(argb)); 
    }
    
    public void clear() {
        g.fillRect(0, 0, width, height);
    }
    
    public void drawLine(float x, float y, float x2, float y2) {
        g.drawLine(camx(x), camy(y), camx(x2), camy(y2)); 
    }
    
    public void drawRect(float x, float y, float w, float h) {
        g.drawRect(camx(x), camy(y), camw(w), camh(h));
    }
    
    public void fillRect(float x, float y, float w, float h) {
        g.fillRect(camx(x), camy(y), camw(w), camh(h));
    }
    
    public void drawTexture(Texture t, float x, float y, float w, float h) {
        Image img = ((BufferedImageTexture) t.getNativeTexture()).getImage(); 
        g.drawImage(img, camx(x), camy(y), camw(w), camh(h), null); 
        //g.drawImage(img, camx(x), camy(y), img.getWidth(null), img.getHeight(null), null);
    }

    public void drawScreenLine(float x, float y, float x2, float y2) {
        g.drawLine(x(x), y(y), x(x2), y(y2)); 
    }
    
    public void drawScreenRect(float x, float y, float w, float h) {
        g.drawRect(x(x), y(y), w(w), h(h));
    }
    
    public void fillScreenRect(float x, float y, float w, float h) {
        g.fillRect(x(x), y(y), w(w), h(h));
    }
    
    public void drawScreenTexture(Texture t, float x, float y, float w, float h) {
        Image img = ((BufferedImageTexture) t.getNativeTexture()).getImage(); 
        g.drawImage(img, x(x), y(y), w(w), h(h), null); 
    }
    
    private int camx(float x) {
        return (int) Math.round((x - Lutebox.camera.getX()) * Lutebox.camera.getUnitSize() + Lutebox.display.getWidth() / 2); 
    }
    
    private int camy(float y) {
        return (int) Math.round((y - Lutebox.camera.getY()) * Lutebox.camera.getUnitSize() + Lutebox.display.getHeight() / 2); 
    }
    
    private int camw(float w) {
        return (int) Math.ceil(w * Lutebox.camera.getUnitSize()); 
    }
    
    private int camh(float h) {
        return (int) Math.ceil(h * Lutebox.camera.getUnitSize()); 
    }
    
    private int x(float x) {
        return (int) Math.round(x); 
    }
    
    private int y(float y) {
        return (int) Math.round(y); 
    }
    
    private int w(float w) {
        return (int) Math.ceil(w); 
    }
    
    private int h(float h) {
        return (int) Math.ceil(h); 
    }

}
