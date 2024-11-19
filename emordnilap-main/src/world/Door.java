package world;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Door extends WorldObject{
	
	private static Image doorImage;
	public boolean open=false;
	
    public Door(float x, float y, int width, int height) {
    	super(x, y, width, height);
    }
    
    public void render(Graphics g) {
    	if (!open) {
    	g.setColor(Color.red);
    	} else {
    		g.setColor(Color.green);
    	}
    	g.fillOval(LTX, LTY, width, height);
    	//g.drawImage(doorImage, LTX, LTY);
    }

    public static void init(){
//    	try {
//			doorImage = new Image("res/target.png");
//			doorImage = doorImage.getScaledCopy(64, 64);
//		} catch (SlickException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
    
    public boolean getOpen() {
    	return open;
    }
}
