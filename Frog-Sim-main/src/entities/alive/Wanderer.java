package entities.alive;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import core.Game;
import environment.biomes.Biome;
import environment.biomes.Grass;
import environment.biomes.Sand;
import environment.biomes.Snow;
import environment.biomes.Water;

public class Wanderer extends Frog{
	public Wanderer(float x, float y) { super(x,y); }
	public void update() {
		if(jumpCooldown<-60)
		{
			startJump((float)(Math.random()*2*Math.PI));
		}
		if(getDistance(Game.bestFrog)<100)
		{
			Game.addEntity(new Follower(xPos,yPos, color, colorAccent, colorExtra, extra));
			Game.removeEntity(this);
		}
		super.update();
	}
	public void render(Graphics g) {
	
		super.render(g);
	}
	public void onDeath()
	{
		Game.entities.remove(this);
	}
	public void colorChange(Biome b) {
		if(b instanceof Grass) {
			color = new Color((int) (Math.random() * 255), (int) (Math.random() * 80)+175, (int) (Math.random() * 255));
			colorAccent = new Color((int) (Math.random() * 255), (int) (Math.random() * 80)+175, (int) (Math.random() * 255));
			colorExtra = new Color((int) (Math.random() * 255), (int) (Math.random() * 80)+175, (int) (Math.random() * 255));
		} else if (b instanceof Water) {
			color = new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 80)+175);
			colorAccent = new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 80)+175);
			colorExtra = new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 80)+175);
		} else if (b instanceof Sand) {
			color = new Color((int) ((int) (Math.random() * 50)+205), (int) (Math.random() * 50)+205, (int) (Math.random() * 255));
			colorAccent = new Color((int) ((int) (Math.random() * 50)+205), (int) (Math.random() * 50)+205, (int) (Math.random() * 255));
			colorExtra = new Color((int) ((int) (Math.random() * 50)+205), (int) (Math.random() * 50)+205, (int) (Math.random() * 255));
		} else if (b instanceof Snow) {
			color = new Color((int) (Math.random() * 80)+175, (int) (Math.random() * 80)+175, (int) (Math.random() * 80)+175);
			colorAccent = new Color((int) (Math.random() * 80)+175, (int) (Math.random() * 80)+175, (int) (Math.random() * 80)+175);
			colorExtra = new Color((int) (Math.random() * 80)+175, (int) (Math.random() * 80)+175, (int) (Math.random() * 80)+175);
		}
		colorAccent = Color.black;
	}
}
