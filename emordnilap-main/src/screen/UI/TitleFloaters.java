package screen.UI;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import core.Main;

public class TitleFloaters {
	private float xPos;
	private float yPos;
	private float xSpeed;
	private float ySpeed;
	public static Image batmanImage;
	private static int timer=0;
	private static int tile=0;
	
	TitleFloaters () {
		xPos= (float) (Math.random()*Main.getScreenWidth());
		yPos= (float) (Math.random()*Main.getScreenHeight());
		xSpeed= (float) (Math.random()*20-10);
		ySpeed= (float) (Math.random()*20-10);
	}
	void update() {
		yPos=yPos+ySpeed/10;
		xPos=xPos+xSpeed/10;
		if (yPos>Main.getScreenHeight()) {
			ySpeed=-ySpeed;
		} else if (yPos<0) {
			ySpeed=-ySpeed;
		}
		if (xPos>Main.getScreenWidth()) {
			xSpeed=-xSpeed;
		} else if (xPos<0) {
			xSpeed=-xSpeed;
		}	
	}
	void render(Graphics g) {
		timer++;
    	if (timer%10==0) {
    		tile++;
    	}
    	if (tile>8) {
    		tile=0;
    	}
    	//g.drawImage(batmanImage, xPos, yPos);
	}
	public static void init(){
		try {    		
			batmanImage = new Image("res/titleTextImage.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
