package com.aetio.ld33.entities;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.util.vector.Vector3f;

import com.aetio.ld33.render.Texture;
import com.aetio.ld33.world.Terrain;
import com.aetio.ld33.world.platform.TurretPlatform;

public class Mob extends Entity{
	
	public static Texture kirbyRight = new Texture("kirbyRIGHT.png", GL_NEAREST);
	public static Texture kirbyLeft = new Texture("kirbyLEFT.png", GL_NEAREST);
	public static Texture kirbyBack = new Texture("kirbyBACK.png", GL_NEAREST);
	int i = 0, t = 0;
	
	public static final int LEFT = 0;
	public static final int RIGHT = 1;
	public static final int TOP = 2;
	public static final int BOTTOM = 3;
	
	private int move;
	

	public Mob(Terrain terrain) {
		super(terrain, terrain.getStartPlatform(), 3);
		this.move = LEFT;
		w = 2;
		h = 2;
		
		color = new Vector3f(0, 1, 0);
	}
	
	public Mob(Terrain terrain, int life) {
		super(terrain, terrain.getStartPlatform(), life);
		this.move = LEFT;
		w = 2;
		h = 2;
	}

	@Override
	public void update(){
		
		if(life <= 0) dead = true;
		
		// Changer %
		if(life <= 50 * maxLife / 100) color = new Vector3f(1, 0, 0);
		
		plat = terrain.getPlatform((int)(x / 5), (int)(y / 5));
		
		int xPlat = (int)(x / 5);
		int yPlat = (int)(y / 5);
		
		if(move == LEFT && (terrain.getPlatform(xPlat + 1, yPlat) == null || terrain.getPlatform(xPlat + 1, yPlat) instanceof TurretPlatform)){
			x = plat.getX() + 2.5f;
			y = plat.getY() + 2.5f;
			if(terrain.getPlatform(xPlat, yPlat + 1) == null) move = BOTTOM;
			else move = TOP;
		}
		
		if(move == RIGHT && (terrain.getPlatform(xPlat - 1, yPlat) == null || terrain.getPlatform(xPlat - 1, yPlat) instanceof TurretPlatform)){
			x = plat.getX() + 2.5f;
			y = plat.getY() + 2.5f;
			if(terrain.getPlatform(xPlat, yPlat + 1) == null) move = BOTTOM;
			else move = TOP;
		}
		
		if(move == TOP && (terrain.getPlatform(xPlat, yPlat + 1) == null || terrain.getPlatform(xPlat, yPlat + 1) instanceof TurretPlatform)){
			x = plat.getX() + 2.5f;
			y = plat.getY() + 2.5f;
			if(terrain.getPlatform(xPlat + 1, yPlat) == null) move = RIGHT;
			else move = LEFT;
		}
		
		if(move == BOTTOM && (terrain.getPlatform(xPlat, yPlat - 1) == null || terrain.getPlatform(xPlat, yPlat - 1) instanceof TurretPlatform)){
			x = plat.getX() + 2.5f;
			y = plat.getY() + 2.5f;
			if(terrain.getPlatform(xPlat + 1, yPlat) == null) move = RIGHT;
			else move = LEFT;
		}
		
		if(move == LEFT) x+=0.05f;
		if(move == RIGHT) x-=0.05f;
		if(move == TOP) y+=0.05f;
		if(move == BOTTOM) y-=0.05f;
	}
	
	@Override
	public void render(){
		super.render();
		
		if(move == LEFT) kirbyLeft.bind();
		else if(move == TOP) kirbyBack.bind();
		else kirbyRight.bind();
		glBegin(GL_QUADS);
			glTexCoord2f(((float)(i+1)/10), 1);   	glVertex3f(x - 1, 0, y);
			glTexCoord2f(((float)(i)/10), 1);   	glVertex3f(x + 1, 0, y);
			glTexCoord2f(((float)(i)/10), 0);   	glVertex3f(x + 1, 2, y);
			glTexCoord2f(((float)(i+1)/10), 0);   	glVertex3f(x - 1, 2, y);
		glEnd();
		Texture.unbind();
		
		t++;
		if(t >= 20){
			if(i < 10)
				i++;
			else
				i = 0;
			t = 0;
		}
	}
	
}
