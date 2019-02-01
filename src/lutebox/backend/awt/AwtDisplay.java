package lutebox.backend.awt;

import java.awt.Canvas;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferStrategy;

import lutebox.core.Display;

public class AwtDisplay implements Display {

    private Frame frame = new Frame(); 
    private Canvas canvas = new Canvas(); 
    private boolean shouldClose = false; 
    
    private AwtGraphics graphics = new AwtGraphics(); 
    private AwtInput input = new AwtInput(canvas); 
    private AwtCursor cursor = new AwtCursor(canvas); 
    private EventHandler eventHandler = new EventHandler(); 
    
    public AwtDisplay() {
        frame.setTitle("Sample Game");
        frame.addWindowListener(eventHandler);
        
        canvas.setSize(800, 600); 
        canvas.addKeyListener(eventHandler);
        canvas.addMouseListener(eventHandler);
        canvas.addMouseMotionListener(eventHandler);
        canvas.addMouseWheelListener(eventHandler); 
        frame.add(canvas); 
        frame.pack(); 
        
        frame.setLocationRelativeTo(null); 
    }
    
    public void update() {
        input.update(); 
        
        BufferStrategy bs = canvas.getBufferStrategy(); 
        bs.show(); 
        graphics.g.dispose(); 
        graphics.setGraphics2D((Graphics2D) bs.getDrawGraphics(), getWidth(), getHeight()); 
    }
    
    public AwtGraphics getGraphics() {
        return graphics; 
    }
    
    public AwtInput getKeyboard() {
        return input; 
    }
    
    public AwtCursor getCursor() {
        return cursor; 
    }
    
    public boolean shouldClose() {
        return shouldClose; 
    }
    
    public void setTitle(String title) {
        frame.setTitle(title); 
    }
    
    public String getTitle() {
        return frame.getTitle(); 
    }
    
    public void setSize(int width, int height) {
        canvas.setSize(width, height); 
        frame.pack(); 
    }
    
    public void center() {
        frame.setLocationRelativeTo(null); 
    }
    
    public int getWidth() {
        return canvas.getWidth(); 
    }
    
    public int getHeight() {
        return canvas.getHeight(); 
    }
    
    public boolean isVisible() {
        return frame.isVisible(); 
    }
    
    public void dispose( ) {
        frame.dispose();
    }
    
    public void setVisible(boolean visible) {
        frame.setVisible(visible); 
        
        if (visible) {
            canvas.requestFocus(); 
            canvas.createBufferStrategy(2); 
            
            BufferStrategy bs = canvas.getBufferStrategy(); 
            graphics.setGraphics2D((Graphics2D) bs.getDrawGraphics(), getWidth(), getHeight()); 
        }
    }
    
    private class EventHandler implements WindowListener, KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            input.addWheel(e.getWheelRotation()); 
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            input.setPos(e.getX(), e.getY());
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            input.setPos(e.getX(), e.getY());
        }

        @Override
        public void mouseClicked(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) {}

        @Override
        public void mousePressed(MouseEvent e) {
            input.setButton(e.getButton(), true); 
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            input.setButton(e.getButton(), false); 
        }

        @Override
        public void keyPressed(KeyEvent e) {
            input.setKey(e.getKeyCode(), true); 
        }

        @Override
        public void keyReleased(KeyEvent e) {
            input.setKey(e.getKeyCode(), false); 
        }

        @Override
        public void keyTyped(KeyEvent e) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void windowActivated(WindowEvent e) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void windowClosed(WindowEvent e) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void windowClosing(WindowEvent e) {
            shouldClose = true; 
        }

        @Override
        public void windowDeactivated(WindowEvent e) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void windowDeiconified(WindowEvent e) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void windowIconified(WindowEvent e) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void windowOpened(WindowEvent e) {
            // TODO Auto-generated method stub
            
        }
        
    }
    
}
