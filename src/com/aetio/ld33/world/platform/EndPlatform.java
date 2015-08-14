package com.aetio.ld33.world.platform;

import static org.lwjgl.opengl.GL11.glColor3f;

public class EndPlatform extends Platform{

	public EndPlatform(int x, int y) {
		super(x, y);
	}

	public void render(){
		glColor3f(1, 0, 0);
		super.render();
		glColor3f(1, 1, 1);
	}
}

