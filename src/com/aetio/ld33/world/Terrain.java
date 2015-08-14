package com.aetio.ld33.world;

import static org.lwjgl.opengl.GL11.GL_NEAREST;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex3f;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import com.aetio.ld33.render.Texture;
import com.aetio.ld33.world.platform.EndPlatform;
import com.aetio.ld33.world.platform.Platform;
import com.aetio.ld33.world.platform.StartPlatform;
import com.aetio.ld33.world.platform.TurretPlatform;

public class Terrain {
	
	Texture world, way;
	BufferedImage worldImage;
	
	Platform[][] platforms = new Platform[10][10];
	Platform start, stop;
	
	List<Platform> turretPlatforms = new ArrayList<Platform>();
	
	public Terrain(){
		
		world = new Texture("world.png", GL_NEAREST);
		way = new Texture("way.png", GL_NEAREST);
		worldImage = world.getImage();
		
		for(int x = 0; x < 10; x++){
        	for(int y = 0; y < 10; y++){
        		if(way.getImage().getRGB(x, y) == 0xff000000) platforms[x][y] = new Platform(x, y);
        		else if(way.getImage().getRGB(x, y) == 0xff00ff00){
        			platforms[x][y] = new StartPlatform(x, y);
        			start = platforms[x][y];
        		}
        		else if(way.getImage().getRGB(x, y) == 0xffff0000){
        			platforms[x][y] = new EndPlatform(x, y);
        			stop = platforms[x][y];
        		}
        		else if(way.getImage().getRGB(x, y) == 0xff0000ff){
        			platforms[x][y] = new TurretPlatform(x, y);
        			turretPlatforms.add(platforms[x][y]);
        		}
        		else platforms[x][y] = null;
            }
        }
	}

	public void render(){
		world.bind();
        glBegin(GL_QUADS);
        	glTexCoord2f(0, 0);   glVertex3f(0, 0, 0);
        	glTexCoord2f(1, 0);   glVertex3f(50, 0, 0);
        	glTexCoord2f(1, 1);   glVertex3f(50, 0, 50);
        	glTexCoord2f(0, 1);   glVertex3f(0, 0, 50);
    	glEnd();
        Texture.unbind();
        
    	for(int x = 0; x < 10; x++){
        	for(int y = 0; y < 10; y++){
        		if(platforms[x][y] != null)
        			platforms[x][y].render();
            }
        }
	}
	
	public Platform getPlatform(int x, int y){
		return platforms[x][y];
	}
	
	public Platform getStartPlatform(){
		return start;
	}
	
	public Platform getEndPlatform(){
		return stop;
	}
	
	public List<Platform> getTurretPlatforms(){
		return turretPlatforms;
	}
}
