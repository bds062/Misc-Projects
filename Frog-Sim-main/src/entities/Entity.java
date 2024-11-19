package entities;

import org.newdawn.slick.Graphics;

import core.Game;
import entities.alive.Animal;
import entities.alive.Follower;
import entities.alive.Frog;
import entities.alive.PlayerFrog;

import org.newdawn.slick.geom.Point;

public abstract class Entity {
	protected float xPos;
	protected float yPos;
	protected float xVel;
	protected float yVel;
	protected float angle;
	protected float height;
	protected float width;
	
	public abstract void update();
	public abstract void render(Graphics g);
	public Entity(float x,float y, float width, float height) { xPos=x; yPos=y; this.width=width; this.height= height;}
	
	public final float getAngleTo(float targetX, float targetY) 
	{
		return (float) Math.atan2(targetY - yPos, targetX - xPos);
	}
	public final float getAngleTo(Entity frog) 
	{
		return (float) Math.atan2(frog.getY() - yPos, frog.getX() - xPos);
	}
	public float getX() {return xPos;}
	public float getY() {return yPos;}
	public float getCenterX()
	{
		return this.getX()+this.width/2;
	}
	public float getCenterY()
	{
		return this.getY()+this.height/2;
	}
	public float getDistance(Point p)
	{
		return (1/Game.zoomScale)*(float) Math.sqrt(Math.pow(getCenterX()-p.getX(), 2)+Math.pow(getCenterY()-p.getY(), 2));
	}

	public float getDistance(Entity e) {
		return (1/Game.zoomScale)*(float) Math.sqrt(Math.pow(getCenterX()-e.getCenterX(), 2)+Math.pow(getCenterY()-e.getCenterY(), 2));
	}
	public boolean collideWith(Entity e)
	{	
		if(this instanceof Animal && e instanceof Animal)
		{
			if(this instanceof Follower && e instanceof Follower)
			{
				if(((Follower)this).myPack != ((Follower)e).myPack)
				{
					return getDistance(e)<1*(Math.sqrt(Math.pow(this.height/2, 2)+Math.pow(this.width/2, 2))+Math.sqrt(Math.pow(e.height/2, 2)+Math.pow(e.width/2, 2)));
				}
				if(((Follower)this).getOrbital() != ((Follower)e).getOrbital())
				{
					return getDistance(e)<0.4*(Math.sqrt(Math.pow(this.height/2, 2)+Math.pow(this.width/2, 2))+Math.sqrt(Math.pow(e.height/2, 2)+Math.pow(e.width/2, 2)));
				}
				return getDistance(e)<0.6*(Math.sqrt(Math.pow(this.height/2, 2)+Math.pow(this.width/2, 2))+Math.sqrt(Math.pow(e.height/2, 2)+Math.pow(e.width/2, 2)));
			}
			if(((Animal)this).isFlying() && ((Animal)e).isFlying())
			{
				return getDistance(e)<0.6*(Math.sqrt(Math.pow(this.height/2, 2)+Math.pow(this.width/2, 2))+Math.sqrt(Math.pow(e.height/2, 2)+Math.pow(e.width/2, 2)));
			}
			else if(((Animal)this).isFlying() || ((Animal) e).isFlying())
			{
				return false;
			}
		}
		return getDistance(e)<0.8*(Math.sqrt(Math.pow(this.height/2, 2)+Math.pow(this.width/2, 2))+Math.sqrt(Math.pow(e.height/2, 2)+Math.pow(e.width/2, 2)));
	}
	public float getAngle() {
		return angle;
	}
	public void setAngle(float angle) {
		this.angle = angle;
	}
	
}
