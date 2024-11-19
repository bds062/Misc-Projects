package entities.obstacles;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import entities.Entity;

public class Obstacle extends Entity{
	public Obstacle(float x, float y) { super(x,y,200,200); }
	public void update() {}
	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.fillRect(xPos, yPos, height, width);
		float testRadius= (float) Math.sqrt(Math.pow(this.height/2, 2)+Math.pow(this.width/2, 2));
		g.drawOval(getCenterX()-testRadius, getCenterY()-testRadius, testRadius*2, testRadius*2);
	}
}
