package world;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import core.Main;

public class Tile extends WorldObject{
	
	private static Image tileImage;
	
    public Tile(float x, float y, int width, int height) {
    	super(x, y, width, height);
    }
    
    public void render(Graphics g) {
//    	g.setColor(Color.black);
//    	g.drawRect(LTX, LTY, width, height);
    	for (int x=0; x<=(width/32); x++) {
    		for (int y=0; y<=(height/32);y++) {
    			if (width-x*32 < 32) {
    				if (height-y*32 < 32) {
    					tileImage.draw(LTX+(x*32), LTY+(y*32), width-x*32, height-y*32);
    				}
    				tileImage.draw(LTX+(x*32), LTY+(y*32), width-x*32, 32);
    			}
    			else {
    				tileImage.draw(LTX+(x*32), LTY+(y*32));
    			}
    		}
        }
    }
    
    public static void init(){
    	try {    		
			tileImage = new Image("res/tile.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}