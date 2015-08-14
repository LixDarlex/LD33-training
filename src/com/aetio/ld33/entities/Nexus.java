package com.aetio.ld33.entities;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.util.vector.Vector3f;

import com.aetio.ld33.render.Texture;
import com.aetio.ld33.world.Terrain;

public class Nexus extends Entity{
	
	public static Texture nexus = new Texture("nexus.png", GL_LINEAR);

	public Nexus(Terrain terrain) {
		super(terrain, terrain.getEndPlatform(), 1000);
		
		color = new Vector3f(0, 1, 1);
		w = 3;
		h = 4;
	}
	
	@Override
	public void update(){
		if(life <= 0) dead = true;
		if(dead){
			System.out.println("[ LD33 Training ] Nexus détruit!");
		}
	}
	
	@Override
	public void render(){
		super.render();
		
		nexus.bind();
		glBegin(GL_QUADS);
			glTexCoord2f(1, 1);   	glVertex3f(x - 1.5f, 0, y);
			glTexCoord2f(0, 1);   	glVertex3f(x + 1.5f, 0, y);
			glTexCoord2f(0, 0);   	glVertex3f(x + 1.5f, 4, y);
			glTexCoord2f(1, 0);   	glVertex3f(x - 1.5f, 4, y);
		glEnd();
		Texture.unbind();
	}
}
