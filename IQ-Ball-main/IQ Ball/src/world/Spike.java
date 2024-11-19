package world;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import core.Main;

public class Spike extends WorldObject {

	private static Image spikeImage;
	private static SpriteSheet spikesSheet;
	private static int timer=0;
	private static int tile=0;
    public Spike(float x, float y, int width, int height) {
    	super(x, y, width, height);
    }
    
    public void render(Graphics g) {
    	timer++;
    	if (timer%10==0) {
    		tile++;
    	}
    	if (tile>8) {
    		tile=0;
    	}
    	
    	for (int x=0; x<(width/32); x++) {
    	spikesSheet.getSprite(tile, 1).draw((LTX+(x*32)), LTY);
    	g.setColor(new Color(0,143,255));
    	g.fillRect((LTX+(x*32)), LTY+32, 32, (Main.getScreenHeight()-LTY));
    	}
    	//g.fillOval(LTX, LTY, width, height);
    }
    public static void init(){
    	try {    		
			spikeImage = new Image("res/Waves.png");
			spikesSheet =  new SpriteSheet(spikeImage,  32, 32);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}