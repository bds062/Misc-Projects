package world;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Target extends WorldObject{
	
	private static Image targetImage;
	
    public Target(float x, float y, int width, int height) {
    	super(x, y, width, height);
    }
    
    public void render(Graphics g) {
    	g.setColor(Color.cyan);
    	//g.fillOval(LTX, LTY, width, height);
    	g.drawImage(targetImage, LTX, LTY);
    }
//    public void init()
//    {
//    	try {
//			targetImage = new Image("res/target.png");
//		} catch (SlickException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    }
    public static void init(){
    	try {
			targetImage = new Image("res/target.png");
			targetImage = targetImage.getScaledCopy(64, 64);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
