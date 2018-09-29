package test;

import lutebox.core.GameListener;
import lutebox.core.Lutebox;
import test.system.CameraMovementSystem;
import test.system.RectangleRenderSystem;

public class TestGame extends GameListener {

    public static float camX, camY; 
    
    public void init() {
        Lutebox.scene.addSystem(new CameraMovementSystem()); 
        Lutebox.scene.addSystem(new RectangleRenderSystem()); 
        
        for (int i = 0; i < 10; i++) {
            EntityFactory.createRectangle(
                    (float) Math.random() * 10, 
                    (float) Math.random() * 10, 
                    1, 1); 
        }
    }
    
    public static void main(String[] args) {
        Lutebox.start(new TestGame()); 
    }
    
}
