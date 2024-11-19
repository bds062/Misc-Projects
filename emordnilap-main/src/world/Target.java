package world;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Target extends WorldObject {

    private static Image targetImage;
    private static SpriteSheet targetSheet;
    private int tick;
    private int tile;

    public Target(float x, float y, int width, int height) {
        super(x, y, width, height);
        tile =0;
    }

    public void render(Graphics g) {
        getImage();
        g.drawImage(targetImage, LTX, LTY);
    }
    
    public void getImage() {
    	
    	tick++;
    	if(tick==10) {
    		tick =0;
    		tile++;
    	}
    	if(tile > 3) {
    		tile =0;
    	}
    	targetImage = targetSheet.getSprite(tile, 0);
    }
    
    public static void init() {
    	
        try {
        	targetSheet = new SpriteSheet("res/target.png",64,64);
        } catch (SlickException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
