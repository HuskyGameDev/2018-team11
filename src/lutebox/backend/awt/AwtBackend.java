package lutebox.backend.awt;

import lutebox.audio.Audio;
import lutebox.backend.AudioBackend;
import lutebox.backend.Backend;
import lutebox.backend.GraphicsBackend;
import lutebox.core.Display;
import lutebox.graphics.Graphics;
import lutebox.input.Cursor;
import lutebox.input.Input;

public class AwtBackend implements Backend {
    
    private AwtGraphicsBackend graphicsBackend = new AwtGraphicsBackend(); 
    private AwtDisplay display = new AwtDisplay();
    private AwtAudio audio = new AwtAudio(); 
    
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
    public Audio getAudio() {
        return audio; 
    }
    
    @Override
    public AudioBackend getAudioBackend() {
        return audio; 
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
