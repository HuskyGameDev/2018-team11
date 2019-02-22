package lutebox.backend.awt;

import java.io.File;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

import lutebox.audio.Audio;
import lutebox.audio.Sound;
import lutebox.backend.AudioBackend;
import lutebox.backend.NativeSound;

public class AwtAudio implements Audio, AudioBackend {
    private ArrayList<Sound> sounds = new ArrayList<>();
    
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
            
            sounds.add( sound );
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
            if ( gain < -80 ) {
                gain = -80;
            } else if ( gain > 6 ) {
                gain = 6;
            }
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
    
    public void stopAll() {
        for ( int i = 0; i < sounds.size(); i++ ) {
            if ( isPlaying( sounds.get(i)) ) {
                stop( sounds.get(i) );
            }
        }
    }
    
    public void unmuteALL() {
        for ( int i = 0; i < sounds.size(); i++ ) {
            if ( !isPlaying( sounds.get(i)) ) {
                setVolume( sounds.get(i), 100 );
            }
        }
    }

    @Override
    public void muteAll() {
        for ( int i = 0; i < sounds.size(); i++ ) {
            if ( isPlaying( sounds.get(i)) ) {
                setVolume( sounds.get(i), 0 );
            }
        }
    }

    private double vol = 0.0;
    @Override
    public void lowerVol() {
        vol = vol - 0.1;
        if ( vol < -80 ) {
            vol = -80;
        }
        setVolume( sounds.get(0), (float) vol);
        
    }

    @Override
    public void increaseVol() {
        vol = vol + 0.1;
        if ( vol > 6 ) {
            vol = 6;
        }
        setVolume( sounds.get(0), (float) vol);
        
    }

}
