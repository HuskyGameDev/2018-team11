package lutebox.core;

public interface Display {

    String getTitle(); 
    
    void setTitle(String title); 
    
    int getWidth(); 
    int getHeight(); 
    
    void setSize(int width, int height); 
    
    void center(); 
    
    boolean isVisible(); 
    
    void setVisible(boolean visible); 
    
    boolean shouldClose(); 
    
}
