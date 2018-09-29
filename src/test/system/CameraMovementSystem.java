package test.system;

import lutebox.core.Lutebox;
import lutebox.ecs.SceneSystem;
import lutebox.input.Input;
import test.TestGame;

public class CameraMovementSystem extends SceneSystem {

    public void update() {
        float move = -500; 
        if (Lutebox.input.getKey(Input.KEY_I)) TestGame.camY -= move * Lutebox.deltaTime;
        if (Lutebox.input.getKey(Input.KEY_K)) TestGame.camY += move * Lutebox.deltaTime; 
        if (Lutebox.input.getKey(Input.KEY_J)) TestGame.camX -= move * Lutebox.deltaTime; 
        if (Lutebox.input.getKey(Input.KEY_L)) TestGame.camX += move * Lutebox.deltaTime; 
    }
    
}
