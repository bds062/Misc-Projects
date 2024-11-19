package animations;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SpriteSheet;

import core.Game;
import media.ImageLoader;

public class Death {
	private int rand;
	private Animation a;
	private Smoke smoke;
	private Skull skull;
	
	public Death(float xPos, float yPos) {
		rand = (int) (Math.random()*3);
		switch(rand) {
		case 0:
			a = new Animation(xPos, yPos, ImageLoader.explosion, 10);
			break;
		case 1:
			smoke = new Smoke(xPos, yPos);
			break;
		case 2:
			skull = new Skull(xPos, yPos);
			break;
		}
	}
	public void update() {
		switch(rand) {
		case 0:
			a.update();
			if(!a.getAlive()) Game.removeAnimation(this);
			break;
		case 1:
			smoke.update();
			if(!smoke.getAlive()) Game.removeAnimation(this);
			break;
		case 2:
			skull.update();
			if(!skull.getAlive()) Game.removeAnimation(this);
			break;
		}
	}
	public void render(Graphics g) {
		switch(rand) {
		case 0:
			a.render(g);
			break;
		case 1:
			smoke.render(g);
			break;
		case 2:
			skull.render(g);
			break;
		}
	}
}
