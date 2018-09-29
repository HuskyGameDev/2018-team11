package lutebox.backend.awt;

import lutebox.backend.Backend;
import lutebox.backend.GraphicsBackend;
import lutebox.core.Display;
import lutebox.graphics.Graphics;
import lutebox.input.Cursor;
import lutebox.input.Input;

public class AwtBackend implements Backend {
    
    private AwtGraphicsBackend graphicsBackend = new AwtGraphicsBackend(); 
    private AwtDisplay display = new AwtDisplay();

    @Override
    public Display getDisplay() {
        return display; 
    }

    @Override
    public Input getKeyboard() {
        return display.getKeyboard(); 
    }

    @Override
    public void update() {
        display.update(); 
    }

    @Override
    public Graphics getGraphics() {
        return display.getGraphics(); 
    } 
    
    @Override
    public GraphicsBackend getGraphicsBackend() {
        return graphicsBackend; 
    }

    @Override
    public Cursor getCursor() {
        return display.getCursor(); 
    }
    
}
