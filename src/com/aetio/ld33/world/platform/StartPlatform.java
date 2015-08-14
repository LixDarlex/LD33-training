package com.aetio.ld33.world.platform;

import static org.lwjgl.opengl.GL11.*;

public class StartPlatform extends Platform{

	public StartPlatform(int x, int y) {
		super(x, y);
	}

	public void render(){
		glColor3f(0, 1, 0);
		super.render();
		glColor3f(1, 1, 1);
	}
}
