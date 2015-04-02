package test;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class Main {
    private int mouseX, mouseY,fps , lastfps;
    private long lastframe = 0;

        public Main()
        {
            try {
                Display.setDisplayMode(new DisplayMode(800, 600));
                Display.create();
                init();
                while(!Display.isCloseRequested()) {
                    loop();
                }
                Display.destroy();
            } catch(LWJGLException e) {
                e.printStackTrace();
            }
        }
    //home of functions that only need be called once
    private void init()
    {
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0, 800, 0, 600, 1, -1);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        lastfps = (int) getTime();
        lastframe = getTime();
    }
    //home of functions that need to be called on each update
    private void loop()
    {
        Display.sync(30);
        // Clear the screen and depth buffer
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

        // set the color of the quad (R,G,B,A)
        GL11.glColor3f(0.5f,0.5f,1.0f);

        // draw quad
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glVertex2f(100,100);
        GL11.glVertex2f(300,100);
        GL11.glVertex2f(300,300);
        GL11.glVertex2f(100,300);
        GL11.glEnd();
        /*
        mouseX = Mouse.getX();
        mouseY = Mouse.getY();
        while (Keyboard.next())
        {
            //keyboard down events
            if (Keyboard.getEventKeyState()) {
                if (Keyboard.getEventKey() == Keyboard.KEY_E) {
                    System.out.println("E pressed");
                }
            }else
            //keyboard up events
            {
                if (Keyboard.getEventKey() == Keyboard.KEY_E) {
                    System.out.println("E released");
                }
            }
        }
        //System.out.println("(" + mouseX + " , " + mouseY +")");
        */
        Display.update();
    }
    public static void main(String[] args) {
        new Main();
    }

    private long getTime()
    {
        return (Sys.getTime() * 1000 ) / Sys.getTimerResolution();
    }
    private int getDelta()
    {
        long time = getTime();
        int delta = (int) (time-lastframe);
        lastframe = time;
        return delta;
    }
    private void updateFPS()
    {
        if (getTime() - lastfps > 1000)
        {
            Display.setTitle("FPS: " + fps);
            fps = 0;
            lastfps += 1000;
        }
        fps++;
    }
}