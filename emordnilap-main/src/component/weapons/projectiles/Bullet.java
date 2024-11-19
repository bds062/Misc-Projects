package component.weapons.projectiles;

import data.Snapshot;
import entities.Entity;
import entities.enemy.Drone;
import entities.enemy.Frog;
import entities.enemy.Turret;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import core.Game;

public class Bullet extends Projectile{

	private boolean canInvert;
	private float projAngle;
	private float speedMult;
	private float smoother;
	private float shotAngle;
	private int lifetime;

	public Bullet(float ix,float iy, float tX, float tY, float multiplier, boolean invert)
	{
		super(ix,iy,tX,tY, multiplier);
		super.id = 1;
		speedMult = multiplier;
		getSpeeds();
	}

	public Bullet(float ix,float iy, float tX, float tY)
	{
		super(ix,iy, tX, tY,0);
		ghost = true;
		super.id = 1;
	}

	public void render(Graphics g) {
		if (isGhost()){
			g.setColor(Color.red);
		}
		else {
			g.setColor(Color.green);
		}
		g.fillOval(xPos, yPos, 10, 10);
	}

	public void getSpeeds() {
		super.getSpeeds();
//		if(tarY >= yPos) {
//			ySpeed = (tarY-yPos)*speedMult;
//		}
//		else
//		{
//			ySpeed = (yPos-tarY)*speedMult;
//		}
//		if(tarX >= xPos) {
//			xSpeed = (tarX-xPos)*speedMult;
//		}
//		else
//		{
//			xSpeed = (xPos-tarX)*speedMult;
//		}
		if(tarX > xPos)
		{
			if(tarY < yPos) {
				shotAngle = (float) Math.atan((tarY-yPos)/(xPos-tarX));
			}
			else {
				shotAngle = (float) Math.atan(-(yPos-tarY)/(tarX-xPos));
			}
		}
		else
		{
			if(tarY < yPos) {
				shotAngle = (float) Math.atan((yPos-tarY)/(xPos-tarX));
			}
			else {
				shotAngle = (float) Math.atan(-(tarY-yPos)/(tarX-xPos));
			}
		}
		ySpeed = (float) (Math.sin(shotAngle)* speedMult);
		xSpeed = (float) (Math.cos(shotAngle)* speedMult);



//		xSpeed = Math.abs(tarX-xPos)*speedMult;
//		ySpeed = Math.abs(tarY-yPos)*speedMult;
		if(tarX < xPos && tarY < yPos)
		{
			xSpeed = -1* xSpeed;
			ySpeed = -1*ySpeed;
		}
		else if(tarX < xPos && tarY > yPos)
		{
			xSpeed = -1* xSpeed;
			ySpeed = ySpeed;
		}
		else if(tarX > xPos && tarY < yPos)
		{
			xSpeed = xSpeed;
			ySpeed = -1*ySpeed;
		}
		else if(tarX > xPos && tarY > yPos)
		{
			xSpeed = xSpeed;
			ySpeed = ySpeed;
		}
		else
		{
			xSpeed = 0;
			ySpeed = 0;
		}


	}

	public void update() {
		super.update();
		lifetime++;
		xPos += xSpeed;
		yPos += ySpeed;
		if (!ghost) {
			if(lifetime>1)
			{
				Entity collison = collideWithWhatEntities(xPos, yPos, 5, 5);
				if(collideWithMap(xPos, yPos, 5, 5))
				{
					Game.curLevel.removeThisBullet(this);
				}
				if(collison!=null)
				{
					if(collison==Game.curLevel.getPlayer())
					{
						if(!canInvert && lifetime<20)
						{
//							Game.curLevel.getPlayer().killed();
//							System.out.print("killed myself");
						}
						else
						{
							Game.curLevel.getPlayer().killed();
						}

					}
					if(collison instanceof Turret)
					{
						if(!canInvert && lifetime<20)
						{
//							Game.curLevel.removeThisTurret((Turret) collison);
						}
						else
						{
							Game.curLevel.removeThisTurret((Turret) collison);
						}

					}
					if(collison instanceof Frog)
					{
						Game.curLevel.removeThisFrog((Frog) collison);
					}
					if(collison instanceof Drone)
					{
						if(!canInvert && lifetime<10)
						{
//							Game.curLevel.removeThisDrone((Drone) collison);
						}
						else
						{
							Game.curLevel.removeThisDrone((Drone) collison);
						}

					}
				}
			}
		}
		else {
			Entity collison = collideWithWhatEntities(xPos, yPos, 5, 5);
			if(collideWithMap(xPos, yPos, 5, 5))
			{
				Game.curLevel.removeThisBullet(this);
			}
			if(collison!=null)
			{
				if(collison==Game.curLevel.getPlayer())
				{
					Game.curLevel.getPlayer().killed();
				}
				if(collison instanceof Turret)
				{
					Game.curLevel.removeThisTurret((Turret) collison);
				}
				if (collison instanceof Drone) {
					Game.curLevel.removeThisDrone((Drone) collison);
				}
				if(collison instanceof Frog)
				{
					Game.curLevel.removeThisFrog((Frog) collison);
				}
			}
		}

	}

	public float getX() {
		return xPos;
	}

	public float getY() {
		return yPos;
	}

	public Snapshot getSnap(int time){
		if(canInvert) {
			return new Snapshot(time, (int) xPos, (int) yPos);
		}
		return null;

	}

}