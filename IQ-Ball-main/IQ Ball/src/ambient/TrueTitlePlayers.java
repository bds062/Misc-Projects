package ambient;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import core.Main;

public class TrueTitlePlayers {
	private float xPos;
	private float yPos;
	private float xSpeed;
	private float ySpeed;
	private int rand;
	public static Image playerImage;
	public static Image targetImage;
	TrueTitlePlayers () {
		xPos= (float) (Math.random()*Main.getScreenWidth());
		yPos= (float) (Math.random()*Main.getScreenHeight());
		xSpeed= (float) (Math.random()*12-6);
		ySpeed= (float) (Math.random()*12-6);
		rand=(int)(Math.random()*2);
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
		if (rand==0) {
		g.drawImage(playerImage, xPos, yPos);
		} else if (rand==1) {
			g.drawImage(targetImage, xPos, yPos);
		}
	}
	public static void init(){
    	try {    		
			playerImage = new Image("res/player.png");
			targetImage = new Image("res/target.png");
			targetImage = targetImage.getScaledCopy(64, 64);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
