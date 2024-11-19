package entities;

import core.Game;
import data.Snapshot;
import entities.enemy.Drone;
import entities.enemy.Frog;
import entities.enemy.Turret;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Explosion extends Entity{
	
	private int tile;
	private int lifetime;
	private static Image explosionImage;
	private static SpriteSheet explosionSheet;
	
	public static void init() throws SlickException
	{
		explosionImage = new Image("res/explosion.png");
		explosionSheet = new SpriteSheet(explosionImage,128, 128);
	}
	public Explosion(float x, float y, int width, int height) {
		super(x, y, 128, 128);
		lifetime=0;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(Graphics g) {
		if(lifetime<10)
		{
			tile=0;
		}	
		else if(lifetime<20)
		{
			tile=1;
		}	
		else if(lifetime<30)
		{
			tile=2;
		}	
		else if(lifetime<40)
		{
			tile=3;
		}	
		else if(lifetime<50)
		{
			tile=4;
		}	
		else if(lifetime<60)
		{
			tile=5;
		}	
		else if(lifetime<70)
		{
			tile=6;
		}	
		else if(lifetime<80)
		{
			tile=7;
		}	
		else if(lifetime<90)
		{
			tile=8;
		}	
		explosionSheet.getSprite(tile, 0).draw(xPos,yPos);		
	}

	public void update()
	{
		lifetime++;
		if(lifetime>90)
		{
			Game.curLevel.removeThisExplosion(this);
		}
		if(lifetime>60)
		{
			width=0;
			height=0;
		}	
		if(lifetime<60)
		{
			Entity collison = collideWithWhatEntities(xPos, yPos, width, height);
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
				if(collison instanceof Frog)
				{
					Game.curLevel.removeThisFrog((Frog) collison);
				}
				if(collison instanceof Drone)
				{
					Game.curLevel.removeThisDrone((Drone) collison);
				}
			}
		}
	}

	public Snapshot getSnap(int time) {
		// TODO Auto-generated method stub
		return new Snapshot(time, (int) this.getX(), (int) this.getY());
	}

}
