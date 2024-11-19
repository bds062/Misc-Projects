package entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import core.Game;

public class RepelBeam {
	boolean connected;
	float destinationX;
	float destinationY;
	float grappleAngle;
	Player Parent;
	final float PULLCONSTANT=0.5f;
	//opposite (vert) Parent.getY()-destinationY
	//adjacent (hori) Parent.getX()-destinationX
	public RepelBeam(float hitX, float hitY)
	{
		destinationX=hitX;
		destinationY=hitY;
		Parent = Game.curLevel.getPlayer();
	}
	public void render(Graphics g) 
	{
    	if(connected) 
    	{
    		g.setColor(new Color(225,10,10));
    		g.setLineWidth(5);
    		g.drawLine(Parent.getX()+Parent.getWidth()/2, Parent.getY()+Parent.getHeight()/2, destinationX, destinationY);
//    		g.drawString(""+grappleAngle, destinationX, destinationY);
    		g.setLineWidth(1);
    	}
    }

    public void update() 
    {
        
    	if(connected)
        {
           	grappleAngle=(float) Math.atan((Parent.getY()-destinationY)/(Parent.getX()-destinationX));
        	if(destinationX>Parent.getX() && destinationY>Parent.getY()) //good
        	{
        		 Parent.accelX(-(float)Math.cos(grappleAngle)*PULLCONSTANT);
                 Parent.accelY(-(float)Math.sin(grappleAngle)*PULLCONSTANT);
        	}
        	else if(destinationX<Parent.getX() && destinationY>Parent.getY())
        	{
        		 Parent.accelX((float)Math.cos(grappleAngle)*PULLCONSTANT);
                 Parent.accelY((float)Math.sin(grappleAngle)*PULLCONSTANT);
        	}
        	else if(destinationX>Parent.getX() && destinationY<Parent.getY())
        	{
        		 Parent.accelX(-(float)Math.cos(grappleAngle)*PULLCONSTANT);
                 Parent.accelY(-(float)Math.sin(grappleAngle)*PULLCONSTANT);
        	}
        	else if(destinationX<Parent.getX() && destinationY<Parent.getY())
        	{
        		 Parent.accelX((float)Math.cos(grappleAngle)*PULLCONSTANT);
                 Parent.accelY((float)Math.sin(grappleAngle)*PULLCONSTANT);
        	}

            System.out.println("connected");
        }
    	
    }  
    public void clearRepel()
    {
    	destinationX=0;
    	destinationY=0;
    	connected=false;
    }
    public void attachRepel(float hitX, float hitY)
    {
    	destinationX=hitX;
		destinationY=hitY;
		connected=true;
    }
}
