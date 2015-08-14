package com.aetio.ld33.entities;

import static org.lwjgl.opengl.GL11.GL_NEAREST;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex3f;

import java.util.List;

import org.lwjgl.util.vector.Vector3f;

import com.aetio.ld33.render.Texture;
import com.aetio.ld33.world.Terrain;
import com.aetio.ld33.world.platform.Platform;

public class Turret extends Entity{
	
	public static Texture turret = new Texture("turret.png", GL_NEAREST);
	
	private List<Entity> entities;
	
	private float cooldown;
	private int t = 0;
	
	private boolean canShoot;

	public Turret(Terrain terrain, Platform plat, List<Entity> entities) {
		super(terrain, plat, 60 * 5);
		this.entities = entities;
		w = 2;
		h = 4;
		
		cooldown = 5;
		canShoot = true;
		
		color = new Vector3f(0.5f, 0.5f, 0.9f);
	}
	
	@Override
	public void update(){
		for(Entity entity:entities){
			Mob mob = (Mob)entity;
			
			if(Math.abs(entity.getX() - x) < 15 && Math.abs(entity.getY() - y) < 15){
				if(canShoot){
					mob.addLife(-1);
					canShoot = false;
					life = 0;
				}
			}
		}
		
		if(!canShoot){
			t++;
			addLife(1);
			if(t >= cooldown * 60){
				canShoot = true;
				t = 0;
			}
		}else{
			t = 0;
		}
	}
	
	@Override
	public void render(){
		super.render();
		
		turret.bind();
		glBegin(GL_QUADS);
			glTexCoord2f(1, 1);   	glVertex3f(x - 1, 0, y);
			glTexCoord2f(0, 1);   	glVertex3f(x + 1, 0, y);
			glTexCoord2f(0, 0);   	glVertex3f(x + 1, 4, y);
			glTexCoord2f(1, 0);   	glVertex3f(x - 1, 4, y);
		glEnd();
		Texture.unbind();
	}
}
