package com.aetio.ld33.world.platform;

import com.aetio.ld33.render.Texture;

import static org.lwjgl.opengl.GL11.*;

public class Platform {
	
	public static Texture plat = new Texture("case.png", GL_NEAREST);
	int x, y;
	
	public Platform(int x, int y){
		this.x = x * 5;
		this.y = y * 5;
	}
	
	public void render(){
		plat.bind();
	    glBegin(GL_QUADS);
	    	glTexCoord2f(0, 0);   glVertex3f(x + 1, 0.1f, y + 1);
	    	glTexCoord2f(1, 0);   glVertex3f(x + 4, 0.1f, y + 1);
	    	glTexCoord2f(1, 1);   glVertex3f(x + 4, 0.1f, y + 4);
	    	glTexCoord2f(0, 1);   glVertex3f(x + 1, 0.1f, y + 4);
		glEnd();
		Texture.unbind();
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
