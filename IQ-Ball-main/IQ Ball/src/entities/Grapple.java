package entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import core.Game;
import world.Hazard;
import world.MovingTile;
import world.Target;
import world.Tile;

public class Grapple {
	int stateMode;  //0=inactive/off 1=attaching 2=connected 3=connected to moving platform 4=connected box
	float destinationX;
	float destinationY;
	float curX;
	float curY;
	float grappleAngle;
	float launchAngle;
	float grappleDistance;
	final float maxGrappleDistance=750;
	Player Parent;
	MovingTile movingAnchor;
	WoodBox boxAnchor;
	final float PULLCONSTANT=1.5f;
	final float grappleSpeed=30;
	static Image grappleHand;
	//opposite (vert) Parent.getY()-destinationY
	//adjacent (hori) Parent.getX()-destinationX
	public Grapple(float hitX, float hitY)
	{
		grappleDistance=0;
		destinationX=hitX;
		destinationY=hitY;
		movingAnchor=null;
		Parent = Game.curLevel.getPlayer();
	}
	public static void init() throws SlickException
	{
		grappleHand = new Image("res/GrappleHand.png");	
	}
	public void render(Graphics g) 
	{
    	if(stateMode==2||stateMode==3 || stateMode==4) 
    	{
    		g.setColor(new Color(118,235,10));
    		g.setLineWidth(5);
    		g.drawLine(Parent.getX()+Parent.getWidth()/2, Parent.getY()+Parent.getHeight()/2, destinationX, destinationY);
    		g.drawImage(grappleHand, destinationX-32, destinationY-32);
    		g.setLineWidth(1);
    	}
    	if(stateMode==1)
    	{
    		g.setColor(new Color(118,235,10));
    		g.setLineWidth(5);
    		g.drawLine(Parent.getX()+Parent.getWidth()/2, Parent.getY()+Parent.getHeight()/2, curX, curY);
    		g.drawImage(grappleHand, curX-32, curY-32);
//    		g.drawString(""+grappleAngle, destinationX, destinationY);
    		g.setLineWidth(1);
    	}
    }

    public void update() 
    {
    	grappleDistance=(float) Math.sqrt(Math.pow(Parent.getY()-curY,2)+Math.pow(Parent.getX()-curX, 2));
    	if(grappleDistance>maxGrappleDistance)
    	{
    		clearGrapple();
    	}
    	if(stateMode==1)
        {
    		
    		if(collideWithMap(curX,curY,1,1)||collideWithTarget(curX,curY,1,1))
    		{
    			destinationX=curX;
    			destinationY=curY;
    			stateMode=2;
    		}
    		else if(collideWithHazard(curX,curY,1,1))
    		{
    			stateMode=0;
    		}
    		else if(collideWithMoveTiles(curX,curY)!=null)
    		{
    			stateMode=3;
    			destinationX=curX;
    			destinationY=curY;
    			movingAnchor=collideWithMoveTiles(curX,curY);
    		}
    		else if(collideWithBox(curX,curY)!=null)
    		{
    			stateMode=4;
    			destinationX=curX;
    			destinationY=curY;
    			boxAnchor=collideWithBox(curX,curY);
    		}
        	curX+=Math.cos(launchAngle)*grappleSpeed;
        	curY+=Math.sin(launchAngle)*grappleSpeed;

        }
    	if(stateMode==2)
        {
    		grappleAngle=(float) Math.atan((Parent.getY()-destinationY)/(Parent.getX()-destinationX));
        	if(destinationX>Parent.getX())
        	{
        		 Parent.accelX((float)Math.cos(grappleAngle)*PULLCONSTANT);
                 Parent.accelY((float)Math.sin(grappleAngle)*PULLCONSTANT);
        	}
        	else if(destinationX<Parent.getX())
        	{
        		 Parent.accelX(-(float)Math.cos(grappleAngle)*PULLCONSTANT);
                 Parent.accelY(-(float)Math.sin(grappleAngle)*PULLCONSTANT);
        	}

        }
    	if(stateMode==3)
    	{
    		destinationX+=movingAnchor.getMovement();
    		grappleAngle=(float) Math.atan((Parent.getY()-destinationY)/(Parent.getX()-destinationX));
        	if(destinationX>Parent.getX())
        	{
        		 Parent.accelX((float)Math.cos(grappleAngle)*PULLCONSTANT);
                 Parent.accelY((float)Math.sin(grappleAngle)*PULLCONSTANT);
        	}
        	else if(destinationX<Parent.getX())
        	{
        		 Parent.accelX(-(float)Math.cos(grappleAngle)*PULLCONSTANT);
                 Parent.accelY(-(float)Math.sin(grappleAngle)*PULLCONSTANT);
        	}
    	}
    	if(stateMode==4)
    	{
    		grappleAngle=(float) Math.atan((Parent.getY()-destinationY)/(Parent.getX()-destinationX));
        	if(destinationX>Parent.getX())
        	{
        		 Parent.accelX((float)Math.cos(grappleAngle)*PULLCONSTANT/2);
                 Parent.accelY((float)Math.sin(grappleAngle)*PULLCONSTANT);
                 boxAnchor.accelX((float)-Math.cos(grappleAngle)*PULLCONSTANT);
                 boxAnchor.accelY((float)-Math.sin(grappleAngle)*PULLCONSTANT);
                 destinationX+=boxAnchor.getVelX()*0.95*0.99;
                if(!boxAnchor.isGrounded())
                {
                	 destinationY+=boxAnchor.getVelY();
                }
                
        	}
        	else if(destinationX<Parent.getX())
        	{
        		 Parent.accelX(-(float)Math.cos(grappleAngle)*PULLCONSTANT/2);
                 Parent.accelY(-(float)Math.sin(grappleAngle)*PULLCONSTANT);
                 boxAnchor.accelX((float)Math.cos(grappleAngle)*PULLCONSTANT);
                 boxAnchor.accelY((float)Math.sin(grappleAngle)*PULLCONSTANT);
                 destinationX+=boxAnchor.getVelX()*0.95*0.99;
                 if(!boxAnchor.isGrounded())
                 {
                 	 destinationY+=boxAnchor.getVelY();
                 }
        	}
    	}
    }  
    public void clearGrapple()
    {
    	destinationX=0;
    	destinationY=0;
    	stateMode=0;
    }
    public void attachGrapple(float hitX, float hitY)
    {
    	grappleDistance=0;
    	destinationX=hitX;
		destinationY=hitY;
		stateMode=1;
		curX=Parent.getX();
		curY=Parent.getY();
		if(curX<destinationX)
		{
			launchAngle=(float) Math.atan((Parent.getY()-destinationY)/(Parent.getX()-destinationX));
		}
		else
		{
			launchAngle=(float) Math.atan((Parent.getY()-destinationY)/(Parent.getX()-destinationX));
			launchAngle+=(float)Math.PI;
		}
    }
    protected boolean collideWithMap(float x, float y, int width, int height) {
    	for (Tile t : Game.curLevel.getTiles()) {
    		if(x < t.getRBX() &&
    		   x + width > t.getLTX() &&
    		   y < t.getRBY() &&
    		   y + height > t.getLTY()){
    			return true;
    		}
    	}	
    	return false;
    }
    protected boolean collideWithTarget(float x, float y, int width, int height) {
    	Target s = Game.curLevel.getTarget();
    		if(x < s.getRBX() &&
    		   x + width > s.getLTX() &&
    		   y < s.getRBY() &&
    		   y + height > s.getLTY()){
    			return true;
    		}
    		
    	return false;
    }
    protected boolean collideWithHazard(float x, float y, int width, int height) {
    	for (Hazard s : Game.curLevel.getHazards()) {
    		if(x < s.getRBX() &&
    		   x + width > s.getLTX() &&
    		   y < s.getRBY() &&
    		   y + height > s.getLTY()){
    			return true;
    		}
    	}	
    	return false;
    }
    protected MovingTile collideWithMoveTiles(float x, float y) {
    	for (MovingTile t : Game.curLevel.getMoveTiles()) {
    		if(x > t.getLTX() &&x< t.getLTX()+t.getWidth() && y > t.getLTY() && y< t.getLTY()+t.getHeight()){
    			return t;
    		}
    	}	
    	return null;
    }
    protected WoodBox collideWithBox(float x, float y)
    {
    	for (WoodBox t : Game.curLevel.getBoxs()) {
    		if(x > t.getX() &&
    		   x <t.getWidth() +t.getX() &&
    		   y > t.getY() &&
    		   y < t.getHeight() + t.getY()){
    			return t;
    		}
    	}	
    	return null;
    }
}
