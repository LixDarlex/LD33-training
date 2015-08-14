package com.aetio.ld33;

import static org.lwjgl.opengl.GL11.*;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.glu.GLU;

import com.aetio.ld33.entities.Entity;
import com.aetio.ld33.entities.Mob;
import com.aetio.ld33.entities.Nexus;
import com.aetio.ld33.entities.Turret;
import com.aetio.ld33.world.Terrain;
import com.aetio.ld33.world.platform.EndPlatform;
import com.aetio.ld33.world.platform.Platform;

public class Game {
	
	int t = 60, tv = 60 * 15;
	
	private float xCam, yCam = 3, zCam;
	private float xRot, yRot;
	
	Terrain terrain;
	List<Entity> entities = new ArrayList<Entity>();
	List<Turret> turrets = new ArrayList<Turret>();
	Nexus nexus;

	public void init(){
		terrain = new Terrain();
		
		for(Platform platform:terrain.getTurretPlatforms()){
			turrets.add(new Turret(terrain, platform, entities));
		}
		
		nexus = new Nexus(terrain);
	}
	
	private float xDir, yDir, zDir;
	private float speed = 0.3f;
	
	public void update(){
		tv++;
		if(tv >= 60 * 15){
			t++;
			if(t >= 100){
				entities.add(new Mob(terrain));
				t = 0;
			}
			if(tv >= 60 * 20){
				tv = 0;
			}
		}
		
		for(Turret t:turrets){
			t.update();
		}
		
		for(Entity entity:entities){
			if(!entity.isDead()){ entity.update();
				if(entity.getPlatform() instanceof EndPlatform){
					if(!nexus.isDead()) nexus.addLife(-100);
					entity.setDead(true);
				}
			}
		}
		
		if(!nexus.isDead()) nexus.update();
		
		xDir = 0;
		yDir = 0;
		zDir = 0;
		
		if (Mouse.isButtonDown(0) && !Mouse.isGrabbed()) Mouse.setGrabbed(true);
    	if (!Mouse.isGrabbed()) return;
    	if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) Mouse.setGrabbed(false);
    	
    	xRot -= Mouse.getDY() * 0.5;
    	yRot += Mouse.getDX() * 0.5;
    	
    	if(Keyboard.isKeyDown(Keyboard.KEY_Z)) zDir = -1;
    	if(Keyboard.isKeyDown(Keyboard.KEY_S)) zDir = 1;
    	if(Keyboard.isKeyDown(Keyboard.KEY_D)) xDir = 1;
    	if(Keyboard.isKeyDown(Keyboard.KEY_Q)) xDir = -1;
    	
    	if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)) yDir = 1;
    	if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) yDir = -1;
		
    	xCam += (xDir * Math.cos(Math.toRadians(yRot)) - zDir * Math.sin(Math.toRadians(yRot))) * speed;
    	yCam += yDir * speed;
    	zCam += (zDir * Math.cos(Math.toRadians(yRot)) + xDir * Math.sin(Math.toRadians(yRot))) * speed;
	}
	
	public void render(){
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glClearColor(0.2f, 0.4f, 0.8f, 1.0f);
        
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        GLU.gluPerspective(70, (float) Display.getWidth() / (float) Display.getHeight(), 0.1f, 1000.0f);
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
        
        glRotatef(xRot, 1, 0, 0);
        glRotatef(yRot, 0, 1, 0);
        glTranslatef(-xCam, -yCam, -zCam);
        
        terrain.render();
        for(Entity entity:entities){
			if(!entity.isDead()) entity.render();
		}
        
        for(Turret t:turrets){
			t.render();
		}
        
        nexus.render();
	}
}
