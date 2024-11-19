package world;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class MovingTile extends Tile{
	
	protected float xMax, xMin;
	protected float yMax, yMin;
	
	protected final float initX, initY;
	
	protected boolean right = true;
	protected boolean bottom = true;
	
	private static Image movingTileImage;
	
	protected float xVel;
	protected float yVel;
	
    public MovingTile(float x, float y, int width, int height, float xDist, float yDist, float xVel, float yVel) {
    	super(x, y, width, height);
    	initX = x;
    	initY = y;
    	xMax = x + xDist;
    	yMax = y + yDist;
    	xMin = x - xDist;
    	yMax = y - yDist;	
    	this.xVel = xVel;
    	this.yVel = yVel;
    }
    public static void init()
    {
//    	try {    		
//			movingTileImage = new Image("res/movingTileImagePast.png");
//		} catch (SlickException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
    }
    
    public void render(Graphics g) {
    	g.drawOval(LTX,LTY,width,height);
//    	g.drawImage(movingTileImage, LTX, LTY);
    }
    
    public void update() {
    	if (LTX <= xMax && right) {
    		LTX += xVel;
    		if (LTX == xMax) {
    			right = !right;
    		}
    	}
    	else if (LTX >= initX && !right) {
    		LTX -= xVel;
    		if (LTX == initX) {
    			right = !right;
    		}
    	}
    	if (LTY <= yMax && bottom) {
    		LTY+=yVel;
    		if (LTY == yMax) {
    			bottom = !bottom;
    		}
    	} else if (LTY >= initY && !bottom) {
    		LTY -= yVel;
    		if (LTY==initY) {
    			bottom=!bottom;
    		}
    	}
    }
    public float getXMovement() {	
    	if (right) {
    		return xVel;
    	} else {
    		return -xVel;
    	}
    }
    
    public float getYMovement() {
    	if (bottom) {
    		return yVel;
    	} else {
    		return -yVel;
    	}
    }
    
    public void switchState() throws SlickException {
    	movingTileImage= new Image("res/movingTileImageFuture.png");
    }
}
