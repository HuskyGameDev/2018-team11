package lutebox.backend.awt;

import java.awt.Canvas;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import lutebox.input.Cursor;

public class AwtCursor implements Cursor {

//    private LockMode lockMode = LockMode.FREE; 
    private boolean visible; 
    
    private Canvas canvas; 
    
    private java.awt.Cursor visibleCursor; 
    private java.awt.Cursor hiddenCursor; 
    
    public AwtCursor(Canvas canvas) {
        this.canvas = canvas; 
        
        visibleCursor = canvas.getCursor(); 
        hiddenCursor = Toolkit.getDefaultToolkit().createCustomCursor(
                new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB), 
                new Point(0, 0), 
                "hidden cursor"); 
    }
    
//    @Override
//    public LockMode getLockMode() {
//        return lockMode; 
//    }
//
//    @Override
//    public void setLockMode(LockMode mode) {
//        lockMode = mode; 
//    }

    @Override
    public boolean isVisible() {
        return visible; 
    }

    @Override
    public void setVisible(boolean visible) {
        this.visible = visible; 
        canvas.setCursor(visible ? visibleCursor : hiddenCursor); 
    }

}
