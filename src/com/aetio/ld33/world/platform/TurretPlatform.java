package com.aetio.ld33.world.platform;

import static org.lwjgl.opengl.GL11.glColor3f;

public class TurretPlatform extends Platform{

	public TurretPlatform(int x, int y) {
		super(x, y);
	}

	public void render(){
		glColor3f(0, 0, 1);
		super.render();
		glColor3f(1, 1, 1);
	}
}
