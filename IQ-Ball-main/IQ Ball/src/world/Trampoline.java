package world;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Trampoline extends Tile{
	
	protected float xMax, xMin;
	protected float yMax, yMin;
	
	private static Image trampolineImage;
	
	protected final float initX, initY;
	;
	
    public Trampoline(float x, float y, int width, int height) {
    	super(x, y, width, height);
    	initX = x;
    	initY = y;
    }
    
    public void render(Graphics g) {
    	g.drawImage(trampolineImage,LTX,LTY);
    }
    
    public void update() {
    	
    }
    public static void init(){
    	try {    		
			trampolineImage = new Image("res/Trampoline.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}