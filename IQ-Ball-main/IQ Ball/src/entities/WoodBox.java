package entities;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import core.Game;
import core.Main;

public class WoodBox extends Entity{


    private static final float friction = 0.95f;
    private static final float airResistance = 0.99f;
    
    private int stuckTimer;
    
    float elapsedTime;
    
    private static Image boxImage;

    public static void init() throws SlickException {
    	boxImage = new Image("res/Box.png");
	}
    
    public WoodBox (float x, float y) {
        super(x, y, boxImage.getWidth(), boxImage.getHeight());
        elapsedTime = 0;
    }

    public void render(Graphics g) {
    	//g.drawImage(playerImage, pos.x, pos.y);
    	
//    	g.drawOval(xPos, yPos, width, height);
    	g.drawImage(boxImage, xPos, yPos);
    }

    public void update(float deltaTime, float gravity) {
       
         if(grounded) {
            xVel *= friction;
         }
         xVel *= airResistance;

        
         float newY = yPos;

         this.yVel += gravSpeed;
         newY += this.yVel;
         
         if (collideWithMap(xPos, newY, getWidth(), getHeight()) || collideWithBox(xPos,newY,getWidth(),getHeight())!=null || collideWithPlayer(xPos,newY,getWidth(),getHeight())!=null){
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
        moveBoxX(xVel);
    }  
    protected void moveBoxX(float shift)
    {
        float newX = xPos + shift;
        if (collideWithPlayer(newX,yPos,this.getWidth(),this.getHeight())!=null){
            stuckTimer++;
            if(stuckTimer>180)
            {
            	yPos++;
            }
        }
        else
        {
        	stuckTimer=0;
        }
        if (!collideWithMap(newX, yPos, getWidth(), getHeight()) && collideWithBox(newX,yPos,this.getWidth(),this.getHeight())==null && collideWithPlayer(newX,yPos,this.getWidth(),this.getHeight())==null){
            this.xPos= newX;
        }
        else
        {
        	xVel=0;
        }
    }
}