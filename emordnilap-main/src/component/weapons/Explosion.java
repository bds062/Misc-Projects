package component.weapons;

import data.Snapshot;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import entities.Entity;

public class Explosion extends Entity{
	private static Image explosionImage;
	private static SpriteSheet explosionSheet;
	
	private int tile;
	private int timer;
	
	public static void init() throws SlickException
	{
		explosionImage = new Image("res/grenadesprites.png");
		explosionSheet = new SpriteSheet(explosionImage, 58, 58);
	}
	public Explosion(float x, float y, int width, int height) {
		super(x, y, width, height);
		
	}


	public void render(Graphics g) {
		if (timer%5==0) {
    		tile++;
    	} 
    	if (tile>8) {
    		tile=0;
    	}
    	explosionSheet.getSprite(tile, 0).draw(xPos,yPos);
	}

	public Snapshot getSnap(int time) {
		if (timer % 8 == 0) {
			return new Snapshot(timer, (int) this.getX(), (int) this.getY());
		} else {
			return null;
		}
	}
}
