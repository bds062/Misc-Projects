package entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import core.Game;
import world.Hazard;
import world.MovingTile;
import world.Target;
import world.Tile;

public class BlackHole {
	float myX;
	float myY;
	float grappleAngle;
	float grappleDistance;
	Player Parent;
	float pullPower;
	float pullRange;
	static Image blackHoleImage;
	static SpriteSheet blackHoleSheet;
	static int timer=0;
	static int tile=0;
	//opposite (vert) Parent.getY()-destinationY
	//adjacent (hori) Parent.getX()-destinationX
	public BlackHole(float hitX, float hitY)
	{
		grappleDistance=0;
		Parent = Game.curLevel.getPlayer();
		myX=hitX;
		myY=hitY;
		pullPower=0;
		pullRange=400;
	}
	public BlackHole(float hitX, float hitY, float strength)
	{
		grappleDistance=0;
		Parent = Game.curLevel.getPlayer();
		myX=hitX;
		myY=hitY;
		pullPower=0;
		pullRange=strength;
	}
	public static void init() throws SlickException
	{
		blackHoleImage=new Image("res/blackhole.png");
		blackHoleSheet=new SpriteSheet(blackHoleImage, 64, 64);
		
	}
	public void render(Graphics g) 
	{	
//    	g.drawString(""+pullPower,0,30);
//    	g.drawString(""+grappleDistance,0,130);
    	blackHoleSheet.getSprite(tile, 0).draw(myX,myY);
    }

    public void update() 
    {
    	grappleDistance=(float) Math.sqrt(Math.pow(Parent.getY()-myY,2)+Math.pow(Parent.getX()-myX, 2));
    	grappleAngle=(float) Math.atan((Parent.getY()-myY)/(Parent.getX()-myX));
    	pullPower=pullRange/grappleDistance;
    	timer++;
    	if (timer%10==0) {
    		tile++;
    	} 
    	if (tile>=blackHoleSheet.getHorizontalCount()) {
    		tile=0;
    	}
    	if(pullPower<0.6 && pullPower>-1.5)
    	{
    		pullPower=0;
    	}
        if(myX>Parent.getX())
        {
        	 Parent.accelX((float)Math.cos(grappleAngle)*pullPower);
             Parent.accelY((float)Math.sin(grappleAngle)*pullPower);
        }
        else if(myX<Parent.getX())
        {
        	 Parent.accelX(-(float)Math.cos(grappleAngle)*pullPower);
             Parent.accelY(-(float)Math.sin(grappleAngle)*pullPower);
        }
        for(WoodBox w : Game.curLevel.getBoxs())
        {
        	grappleDistance=(float) Math.sqrt(Math.pow(w.getY()-myY,2)+Math.pow(w.getX()-myX, 2));
        	grappleAngle=(float) Math.atan((w.getY()-myY)/(w.getX()-myX));
        	pullPower=pullRange/grappleDistance;
        	if(pullPower<0.6 && pullPower>-1.5)
        	{
        		pullPower=0;
        	}

        	if(myX>w.getX())
            {
            	 w.accelX((float)Math.cos(grappleAngle)*pullPower);
                 w.accelY((float)Math.sin(grappleAngle)*pullPower);
            }
            else if(myX<w.getX())
            {
            	 w.accelX(-(float)Math.cos(grappleAngle)*pullPower);
                 w.accelY(-(float)Math.sin(grappleAngle)*pullPower);
            }
        }
    }
}
