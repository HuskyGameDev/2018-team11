package lutebox.backend.awt;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

import lutebox.audio.Audio;
import lutebox.audio.Sound;
import lutebox.backend.AudioBackend;
import lutebox.backend.NativeSound;

public class AwtAudio implements Audio, AudioBackend {

    @Override
    public NativeSound createNativeSound(String filename) {
        try {
            AudioInputStream in = AudioSystem.getAudioInputStream(new File(filename));
            Clip sound = AudioSystem.getClip(null); 
            sound.open(in); 
            return new ClipSound(sound); 
        } catch (Exception e) {
            System.out.println("Could not load sound file: " + filename);
            return new ClipSound(null); 
        } 
    }

    @Override
    public void play(Sound sound, boolean fromBeginning, boolean loop) {
        ClipSound c = (ClipSound) sound.getNativeSound(); 
        
        if (c.clip != null) {
            if (fromBeginning) c.clip.setFramePosition(0); 
            
            if (loop) {
                c.clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
            else {
                c.clip.loop(0); 
            }
            
            if (!c.clip.isRunning()) c.clip.start();
        }
    }

    @Override
    public void stop(Sound sound) {
        ClipSound c = (ClipSound) sound.getNativeSound(); 
        
        if (c.clip != null && c.clip.isRunning()) {
            c.clip.stop(); 
        }
    }

    @Override
    public void setVolume(Sound sound, float amt) {
        ClipSound c = (ClipSound) sound.getNativeSound(); 
        
        if (c.clip != null) {
            FloatControl volume = (FloatControl) c.clip.getControl(FloatControl.Type.MASTER_GAIN);
            float range = volume.getMaximum() - volume.getMinimum();
            float gain = (range * amt) + volume.getMinimum();
            volume.setValue(gain);
        }
    }
    
    @Override
    public boolean isPlaying(Sound sound) {
        ClipSound c = (ClipSound) sound.getNativeSound(); 
        
        if (c.clip != null) {
            return c.clip.isRunning(); 
        }
        
        return false; 
    }

}
