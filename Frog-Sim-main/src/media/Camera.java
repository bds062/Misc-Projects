package media;

import core.Game;
import core.Main;

public class Camera {
	private float xPos;
	private float yPos;
	
	private final static float EASING = .06f;
	
	private Game g;
	
	public Camera(Game g) {
		xPos = 0; yPos = 0;
		this.g=g;
	}

	public float getX() { return xPos; }
	public float getY() { return yPos; }
	public void moveX(float x) {xPos+=x;} 
	public void moveY(float x) {yPos+=x;} 

	public void update() {
		float newX = (Game.bestFrog.getX() - Main.getScreenWidth()/2/Game.zoomScale);
		xPos += (newX - xPos) * EASING;
		float newY = (Game.bestFrog.getY() - Main.getScreenHeight()/2/Game.zoomScale);
		yPos += (newY - yPos) * EASING;
	}
}
