package entities.alive;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;

import core.Game;
import entities.Entity;

public abstract class Animal extends Entity{
	public Animal(float x, float y, float width, float height) { super(x,y,width,height);
	destinationPoint= new Point(100, 100);
	dead=false;
	}
	
	protected float maxHealth=1000;
	protected float curHealth=800;
	protected float attackSpeed=40;
	protected float attackDamage=100;
	protected int attackTimer;
	protected float regen= 0.5f;
	protected boolean flying;
	public Point destinationPoint;
	public boolean dead;
	public abstract void onDeath();
	public void update()
	{
		destinationPoint.setX((float) (getX()+1000*Math.cos(angle)));
		destinationPoint.setY((float) (getY()+1000*Math.sin(angle)));
		if(curHealth<0)
		{
			onDeath();
			dead=true;
		}
		xPos=this.xPos += xVel;
		yPos=this.yPos += yVel;
		ArrayList<Entity> allEntities=Game.getEntities();
		for(Entity e: allEntities)
		{
			if(collideWith(e) && e!=this)
			{
				float newangle=(float) (getAngleTo(e)+Math.PI);
				xPos=this.xPos -= xVel;
				yPos=this.yPos -= yVel;
				xPos+=(float)(1*Math.cos(newangle));
				yPos+=(float)(1*Math.sin(newangle));
				return;
			}
		}
		if(curHealth<maxHealth)
		{
			curHealth+=regen;
		}	
		attackTimer++;
	}
	public void render(Graphics g)
	{
		if(curHealth<0)
		{
			return;
		}
		float opacity = 1-(curHealth/maxHealth);
		if(curHealth<maxHealth)
		{
			float compressionFactor=maxHealth/128;
			g.setColor(new Color(0,0,0, opacity));
			g.drawRect(xPos-1, yPos-1, 128+1, 21);
			g.setColor(new Color(0,255,0, opacity));
			g.fillRect(xPos, yPos, curHealth/compressionFactor, 20);
			g.setColor(new Color(255,0,0, opacity));
			g.fillRect(xPos+curHealth/compressionFactor, yPos, (maxHealth-curHealth)/compressionFactor, 20);
		}
		
	}
	
	public boolean isFlying()
	{
		return flying;
	}
	
	public boolean isDead() {
		return dead;
	}
}
