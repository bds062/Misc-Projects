package entities.alive;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Point;

import core.Game;
import entities.Pool;
import grouping.Pack;

public class PlayerFrog extends Frog{
	public Pack playerPack;
	public boolean idle;

	public PlayerFrog(float x, float y) {
		super(x, y, true);
		playerPack=new Pack(this);
		this.flying=true;
		// TODO Auto-generated constructor stub
	}
	public void render(Graphics g) {
		
//		g.setColor(Color.white);
//		g.drawString("X: "+xPos, xPos-10, yPos-10);
//		g.drawString("Y: "+yPos, xPos-10, yPos-50);
//		g.drawString("Angle: "+angle, xPos-10, yPos-50);
//		float testRadius= (float) Math.sqrt(Math.pow(this.height/2, 2)+Math.pow(this.width/2, 2));
//		g.drawOval(getCenterX()-testRadius, getCenterY()-testRadius, testRadius*2, testRadius*2);

			super.render(g);
	}
	public void update()
	{
//		curHealth-=0.1f;
//		if(this.isJumping)
//		{
//			playerPack.moveAll(angle);
//		}
		ArrayList<Pool> pools =Game.getPools();
		for(int i=0; i<pools.size();i++)
		{
			if(getDistance(pools.get(i))<500)
			{
				for(int j=0; j<playerPack.getFrogs().size()/2 && j<11; j++)
				{
					Frog newFrog = new Follower((float)(pools.get(i).getX()+Math.random()*300-150), (float)(pools.get(i).getY()+Math.random()*300-150));
					Game.addEntity(newFrog);
				}
				Game.removePool(pools.get(i));
			}
		}
		playerPack.update();
		deathRing();
		super.update();
	}
	public Pack getPack()
	{
		return playerPack;
	}
	private void deathRing()
	{
		
	}
	public void setDestPoint(Point p)
	{
		destinationPoint=p;
	}
	public void onDeath()
	{
		Game.entities.remove(this);
	}

	

}