package entities;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

import core.Game;
import world.Hazard;
import world.MovingTile;
import world.Target;
import world.Tile;
import world.Trampoline;

public abstract class Entity {
    protected Vector2f pos;
    
    protected float xPos;
    protected float yPos;
    
    protected float yVel = 0;
    protected float xVel = 0;
    protected float xAccel = 2;
    
    protected int width;
    protected int height;
    protected int weight = 20;
    
    final protected float gravSpeed=1f;
    protected boolean grounded;

    public Entity(float x, float y, int width, int height) {
    	xPos = x;
    	yPos = y;
        this.width = width;
        this.height = height;
    }
    public abstract void render(Graphics g);
    public void update(float deltaTime, float gravity){
    	float newY = yPos;

        this.yVel += gravSpeed;
        newY += this.yVel;
        
        if (collideWithMap(xPos, newY, getWidth(), getHeight()) || collideWithBox(xPos,newY,getWidth(),getHeight())!=null){
            if (yVel >= 0.115){
                //this.yPos = (float) Math.floor(yPos);
                grounded = true;
            }
            this.yVel = 0;
        }
        else {
            yPos = newY;
            grounded = false;
        }
    }	
  	
    protected void moveX(float shift)
    {
        float newX = xPos + shift;

        if (!collideWithMap(newX, yPos, getWidth(), getHeight()) && collideWithBox(newX,yPos,getWidth(),getHeight())==null){
            this.xPos= newX;
        }
        else
        {
        	this.xVel=0;
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
    protected MovingTile collideWithMoveTiles(float x, float y, int width, int height) {
    	for (MovingTile t : Game.curLevel.getMoveTiles()) {
    		if(x < t.getRBX() &&
    		   x + width > t.getLTX() &&
    		   y < t.getRBY() &&
    		   y + height > t.getLTY()){
    			return t;
    		}
    	}	
    	return null;
    }
    protected WoodBox collideWithBox(float x, float y, int width, int height)
    {
    	for (WoodBox t : Game.curLevel.getBoxs()) {
    		if(x+width > t.getX() &&
    		   x + width < t.getX()+t.getWidth() &&
    		   y+height > t.getY() &&
    		   y + height < t.getY()+t.getHeight())
    		{
    			return t;
    		}
    		else if(
    			 x > t.getX() &&
    	 		 x < t.getX()+t.getWidth() &&
    	 		 y > t.getY() &&
    	 		 y < t.getY()+t.getHeight())
    	    	{
    	    		return t;
    	    	}
    		else if(
       			 x > t.getX() &&
       	 		 x < t.getX()+t.getWidth() &&
       	 		 y+height > t.getY() &&
     		     y + height < t.getY()+t.getHeight())
       	    	{
       	    		return t;
       	    	}
    		else if(
    			 x+width > t.getX() &&
    	         x + width < t.getX()+t.getWidth() &&
       	 		 y > t.getY() &&
       	 		 y < t.getY()+t.getHeight())
       	    	{
       	    		return t;
       	    	}
    	}	
    	return null;
    }
    protected Player collideWithPlayer (float x, float y, int width, int height)
    {

    	Player t = Game.curLevel.getPlayer();
    	if(t.getX()+t.getWidth() > x &&
 		   t.getX()+t.getWidth() <x + width &&
 		   t.getY()+t.getHeight() > y &&
 		   t.getY()+t.getHeight() < y + height)
		{
 			return t;
 		}
     	else if(
     		t.getX() > x &&
     		t.getX() < x + width &&
     		t.getY() > y &&
     		t.getY() < y + height)
    	{
    		return t;
    	}
     	else if(
     		t.getX()+t.getWidth() > x &&
      		t.getX()+t.getWidth() <x + width &&
      		t.getY() > y &&
      		t.getY() < y + height)
	    {
	    	return t;
	    }
     	else if(
     		t.getX() > x &&
         	t.getX() < x + width &&
         	t.getY()+t.getHeight() > y &&
       		t.getY()+t.getHeight() < y + height)
	    {
	    	return t;
	    }
     	return null;
    }

	protected boolean bounce(float x, float y, int width, int height) {
    		for (Trampoline t : Game.curLevel.getTrampolines()) {
    			if(x < t.getRBX() &&
    		   	x + width > t.getLTX() &&
    		   	y < t.getRBY() &&
    		   	y + height > t.getLTY()){
    				return true;
    			}
    		}	
    		return false;
    	}

    public void accelX(float shift){
        xVel = xVel + shift;
    }
    public void accelY(float shift){
        yVel = yVel + shift;
    }
    public Vector2f getPos() {
        return pos;
    }

    public float getX() {
        return xPos;
    }

    public float getY() {
        return yPos;
    }
    public float getVelX() {
        return xVel;
    }

    public float getVelY() {
        return yVel;
    }

    public boolean isGrounded() {
        return grounded;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public float getWeight(){
        return weight;
    }
    
}