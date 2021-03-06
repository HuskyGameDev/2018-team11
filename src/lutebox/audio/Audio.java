package lutebox.audio;

public interface Audio {

    public void play(Sound sound, boolean fromBeginning, boolean loop); 
    
    public void stop(Sound sound); 
    
    public boolean isPlaying(Sound sound); 
    
    public void setVolume(Sound sound, float volume); 
    
    public void stopAll();

    public void muteAll();

    public void unmuteALL();

    public void lowerVol();

    public void increaseVol();
}
