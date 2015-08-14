package com.aetio.ld33.entities;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex3f;

import org.lwjgl.util.vector.Vector3f;

import com.aetio.ld33.world.Terrain;
import com.aetio.ld33.world.platform.Platform;

public class Entity {
	
	protected float x, y;
	protected float w, h;
	
	protected boolean dead = false;
	protected float life;
	protected float maxLife;
	
	protected Terrain terrain;
	protected Platform plat;
	
	protected Vector3f color;
	
	public Entity(Terrain terrain, Platform plat, int life){
		this.terrain = terrain;
		this.x = plat.getX() + 2.5f;
		this.y = plat.getY() + 2.5f;
		this.life = life;
		this.maxLife = life;
		
		this.w = 1;
		this.h = 1;
		
		this.color = new Vector3f(1, 0, 0);
	}
	
	public void update(){
		
	}
	
	public void render(){
		
		glColor4f(color.x, color.y, color.z, 0.8f);
		glBegin(GL_QUADS);
			glVertex3f(x + (w / 2) - w * (life / maxLife), h + 0.2f, y);
			glVertex3f(x + (w / 2), h + 0.2f, y);
			glVertex3f(x + (w / 2), h + 0.5f, y);
			glVertex3f(x + (w / 2) - w * (life / maxLife), h + 0.5f, y);
		glEnd();
		glColor4f(1, 1, 1, 1);
	}
	
	public boolean isDead(){
		return dead;
	}
	
	public void setDead(boolean state){
		dead = state;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}
	
	public float getWidth() {
		return w;
	}

	public float getHeight() {
		return h;
	}
	
	public float getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}
	
	public void addLife(int v){
		this.life = this.life + v;
	}
	
	public Platform getPlatform(){
		return plat;
	}
}
