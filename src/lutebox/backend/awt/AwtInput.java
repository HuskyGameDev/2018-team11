package lutebox.backend.awt;

import java.awt.Canvas;
import java.awt.Point;
import java.awt.Robot;

import lutebox.input.Input;

public class AwtInput implements Input {

    private static final int MAX_KEYS = 4096; 
    private static final int MAX_BUTTONS = 5; 
    
    private boolean[] keys = new boolean[MAX_KEYS];
    private boolean[] curKeys = new boolean[MAX_KEYS]; 
    private boolean[] lastKeys = new boolean[MAX_KEYS]; 
    
    private int x, y; 
    private int curX, curY; 
    private int lastX, lastY; 
    private int wheel; 
    private int curWheel; 
    
    private boolean[] buttons = new boolean[5]; 
    private boolean[] curButtons = new boolean[5]; 
    private boolean[] lastButtons = new boolean[5]; 
    
    private Robot robot; 
    private Canvas canvas; 
    
    public AwtInput(Canvas canvas) {
        this.canvas = canvas; 
        try {
            robot = new Robot(); 
        } catch (Exception e) {
            System.out.println("[WARNING] Could not create AWT robot"); 
        }
    } 
    
    public void update() {
        for (int i = 0; i < MAX_KEYS; i++) {
            lastKeys[i] = curKeys[i]; 
            curKeys[i] = keys[i]; 
        }
        
        lastX = curX; 
        lastY = curY; 
        curX = x; 
        curY = y; 
        
        curWheel = wheel; 
        wheel = 0; 
        
        for (int i = 0; i < buttons.length; i++) {
            lastButtons[i] = curButtons[i]; 
            curButtons[i] = buttons[i]; 
        }
    }
    
    public boolean getKey(int keycode) {
        return keycode < 0 || keycode >= MAX_KEYS ? false : curKeys[keycode]; 
    }
    
    void setKey(int keycode, boolean pressed) {
        if (keycode >= 0 && keycode < MAX_KEYS) {
            keys[keycode] = pressed; 
        }
    }
    
    void setButton(int button, boolean down) {
        if (button >= 0 && button < MAX_BUTTONS) {
            buttons[button] = down; 
        }
    }
    
    void addWheel(int amt) {
        wheel += amt; 
    }
    
    void setPos(int x, int y) {
        this.x = x; 
        this.y = y; 
    }
    
    @Override
    public float getMouseX() {
        return curX; 
    }

    @Override
    public float getMouseY() {
        return curY; 
    }

    @Override
    public float getMouseDX() {
        return curX - lastX; 
    }

    @Override
    public float getMouseDY() {
        return curY - lastY; 
    }

    @Override
    public boolean getButton(int button) {
        return button >= 0 && button < MAX_BUTTONS ? curButtons[button] : false; 
    }

    @Override
    public int getMouseWheel() {
        return curWheel; 
    }

//    @Override
//    public void setMousePosition(float x, float y) {
//        Point pos = canvas.getLocationOnScreen(); 
//        robot.mouseMove(pos.x + (int) x, pos.y + (int) y);
//        this.curX = (int) x; 
//        this.curY = (int) y; 
//    }
//
//    @Override
//    public void centerMousePosition() {
//        setMousePosition(canvas.getWidth() / 2, canvas.getHeight() / 2); 
//    } 
    
}
