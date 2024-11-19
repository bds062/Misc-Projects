package levels;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import core.Game;
import core.Main;

public class Camera {
	
	public float xPos;
	public float yPos;
	
	public final static float EASING=.06f;
	
	public Camera() {
		xPos=0;
		yPos=0;
	}
	
	public float getX() {
		return xPos;
	}
	public float getY() {
		return yPos;
	}
	
	public void update() {
//		float newY = Game.curLevel.getPlayer().getY()-Main.getScreenHeight()/2;
//		yPos+=(newY-yPos)*EASING;
//		if (yPos<0) {
//			yPos=0;
//		} else if (yPos>Main.getScreenHeight()) {
//			yPos=Main.getScreenHeight();
//		}
		float newX = Game.curLevel.getPlayer().getX()-Main.getScreenWidth()/2;
		xPos+=(newX-xPos)*EASING;
		if (xPos<0) {
			xPos=0;
		} else if (xPos>Main.getScreenWidth()) {
			xPos=Main.getScreenWidth();
		}
	}
	public void render (Graphics g) {
//		g.setColor(Color.blue);
//		g.fillRect(xPos, yPos, 10, 10);
	}
}
