package lutebox.backend;

import lutebox.core.Display;
import lutebox.graphics.Graphics;
import lutebox.input.Cursor;
import lutebox.input.Input;

public interface Backend {

    void update(); 
    
    Cursor getCursor(); 
    
    Display getDisplay(); 
    
    Input getKeyboard(); 
    
    Graphics getGraphics();
    
    GraphicsBackend getGraphicsBackend(); 
    
}
