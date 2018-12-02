package lutebox.backend.awt;

import javax.sound.sampled.Clip;

import lutebox.backend.NativeSound;

public class ClipSound implements NativeSound {

    public Clip clip; 
    
    public ClipSound(Clip clip) {
        this.clip = clip; 
    }
    
}
