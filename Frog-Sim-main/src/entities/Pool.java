package entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import media.ImageLoader;

public class Pool extends Entity{
	final static private float POOL_SIZE = 200;
	public Pool(float x, float y) { super(x,y,POOL_SIZE,POOL_SIZE); }
	public void update() {}
	public void render(Graphics g) {
		 g.drawImage(ImageLoader.pool, xPos, yPos);
	}
}
