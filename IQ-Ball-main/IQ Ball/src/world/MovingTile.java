package world;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class MovingTile extends Tile{
	
	protected float xMax, xMin;
	protected float yMax, yMin;
	
	protected final float initX, initY;
	
	protected boolean right = true;
	protected boolean bottom = true;
	
	private static Image cloudImage;
	
	protected float vel;
	
    public MovingTile(float x, float y, int width, int height, float xDist, float yDist, float vel, boolean mirror) {
    	super(x, y, width, height);
    	initX = x;
    	initY = y;
    	xMax = x + xDist;
    	yMax = y + yDist;
    	xMin = x - xDist;
    	yMax = y - yDist;	
    	this.vel = vel;
    	if(mirror)
    	{
    		LTX+=xDist-1;
    	}
    }
    public static void init()
    {
    	try {    		
			cloudImage = new Image("res/IceCream.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void render(Graphics g) {
    	g.setColor(Color.cyan);
    	g.drawImage(cloudImage, LTX, LTY);
    }
    
    public void update() {
    	if (LTX <= xMax && right) {
    		LTX += vel;
    		if (LTX == xMax) {
    			right = !right;
    		}
    	}
    	else if (LTX >= initX && !right) {
    		LTX -= vel;
    		if (LTX == initX) {
    			right = !right;
    		}
    	}
    	if (LTY <= yMax && bottom) {
    		LTY+=vel;
    		if (LTY == yMax) {
    			bottom = !bottom;
    		}
    	} else if (LTY >= initY && !bottom) {
    		LTY -= vel;
    		if (LTY==initY) {
    			bottom=!bottom;
    		}
    	}
    }
    public float getMovement()
    {
    	
    	if (right)
    	{
    		return vel;
    	}
    	else
    	{
    		return -vel;
    	}
    }
}
