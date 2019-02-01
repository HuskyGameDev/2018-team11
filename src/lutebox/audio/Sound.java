package lutebox.audio;

import lutebox.backend.NativeSound;
import lutebox.core.Lutebox;

public class Sound {

    private String filename; 
    private NativeSound internal; 
    
    
    //Takes sound file and plays the audio loudly
    public Sound(String filename) {
        this.filename = filename; 
        this.internal = Lutebox.audioBackend.createNativeSound(filename); 
    }
    
    public NativeSound getNativeSound() {
        return internal; 
    }
    
    public String getFileName() {
        return filename; 
    }
    
}
