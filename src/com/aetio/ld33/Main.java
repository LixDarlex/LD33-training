package com.aetio.ld33;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import static org.lwjgl.opengl.GL11.*;

public class Main {
	
	public static final String TITLE = "LD 33 Training";

	public static final int TPS_CAP = 60;
    public static final int FPS_CAP = 120;

    private Game game;
    private boolean running;

    public static void main(String[] args){
        new Main().start();
    }

    public void start(){
        try {
            Display.setDisplayMode(new DisplayMode(1000, 600));
            Display.setTitle(TITLE + "     ||     FPS = ?   |   TPS = ?");
            Display.create();
            
            glMatrixMode(GL_PROJECTION);
            glLoadIdentity();
            glMatrixMode(GL_MODELVIEW);
            glLoadIdentity();
            
            glEnable(GL_TEXTURE_2D);
            
            glEnable(GL_BLEND);
            glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
            
            glEnable(GL_DEPTH_TEST);
            
            //glEnable(GL_CULL_FACE);
        } catch (LWJGLException e) {
            e.printStackTrace();
        }

        game = new Game();

        running = true;
        loop();
    }

    public void stop(){
        running = false;
    }

    private long lastTick = System.nanoTime();
    private long lastFrame = System.nanoTime();
    private long lastSecond = System.nanoTime();
    private int tps = 0, fps = 0;

    public void loop(){
        game.init();
        while (running){
            if(Display.isCloseRequested()) stop();

            if(System.nanoTime() >= lastTick + 1000000000 / TPS_CAP){
                game.update();
                lastTick += 1000000000 / TPS_CAP;
                tps++;
            }
            if(System.nanoTime() >= lastFrame + 1000000000 / FPS_CAP){
                game.render();
                Display.update();
                lastFrame += 1000000000 / FPS_CAP;
                fps++;
            }
            if(System.nanoTime() >= lastSecond + 1000000000){
                lastSecond += 1000000000;
                Display.setTitle(TITLE + "     ||     FPS = " + fps + "   |   TPS = " + tps);
                fps = 0;
                tps = 0;
            }
        }
        Display.destroy();
        System.exit(0);
    }
}
