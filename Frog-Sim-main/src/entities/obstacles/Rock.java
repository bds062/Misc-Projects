package entities.obstacles;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import core.Game;
import entities.Entity;
import environment.biomes.Biome;
import environment.biomes.Grass;
import environment.biomes.Sand;
import environment.biomes.Snow;
import environment.biomes.Water;
import media.ImageLoader;

public class Rock extends Entity{
	private Color colorAccent;
	private Image image;
	private boolean color;
	private float angle;
	public Rock(float x, float y) { 
		super(x,y,128,128); 
		image=ImageLoader.boulder;
		color=false;
		colorAccent = Color.white;
	}
	public void update() {}
	public void render(Graphics g) {
		Image tmp = image;
		tmp.setCenterOfRotation(tmp.getWidth() / 2 * Game.zoomScale, tmp.getHeight() / 2 * Game.zoomScale);
		tmp.draw(xPos, yPos, colorAccent);
	}
	public void colorChange(Biome b) {
		if(b instanceof Grass) {
			colorAccent = new Color((int) (Math.random() * 255), (int) (Math.random() * 80)+175, (int) (Math.random() * 255));
			color=true;
		} else if (b instanceof Water) {
			image=ImageLoader.lilypad;
			color=false;
//			colorAccent = new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 80)+175);
		} else if (b instanceof Sand) {
			image=ImageLoader.tumbleweed;
			color=false;
//			colorAccent = new Color((int) ((int) (Math.random() * 50)+205), (int) (Math.random() * 50)+205, (int) (Math.random() * 255));
		} else if (b instanceof Snow) {
			colorAccent = new Color(149, 229, 245);
			color=true;
		}
	}
}
