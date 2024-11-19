package entities;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import core.Game;
import core.Main;

public class Player extends Entity{

    private static final float xMaxSpeed = 10f;
    private static final float friction = 0.95f;
    private static final float airResistance = 0.99f;
    private static final double jumpPower = 25;

    protected enum State {FALLING, JUMPING, STANDING, RUNNING}
    protected State curState;
    protected State prevState;
    
    private double initX;
    private double initY;

    
    float elapsedTime;
    
    private static Image playerImage;

    public static void init() throws SlickException {
    	playerImage = new Image("res/player.png");
	}
    
    public Player(float x, float y) {
        super(x, y, playerImage.getWidth(), playerImage.getHeight());
        initX = x;
        initY = y;
        curState = State.STANDING;
        prevState = State.STANDING;
        elapsedTime = 0;
    }

    public void render(Graphics g) {
    	//g.drawImage(playerImage, pos.x, pos.y);
    	//tiles.getSprite(1 ,0).draw(pos.x, pos.y);
    	//g.setColor(Color.white);
    	//g.fillOval(xPos, yPos, width, height);
    	g.drawImage(playerImage, xPos, yPos);
//    	g.drawString(String.valueOf(grounded), xPos, yPos-60);
//    	g.drawString(String.valueOf(xPos), xPos-40, yPos-80);
//    	g.drawString(String.valueOf(yPos), xPos+40, yPos-80);
//    	g.drawString(String.valueOf(yVel), xPos, yPos-40);
    }

    public void update(float deltaTime, float gravity) {
//        if (Game.input.isKeyPressed(Input.KEY_W) ) {//&& grounded){
//            yVel -= jumpPower;
//        }
//
//        if (Game.input.isKeyDown(Input.KEY_A)){
//            if(Math.abs(xVel) < xMaxSpeed) {
//                xVel -= xAccel;
//            }
//        }
//        else if (Game.input.isKeyDown(Input.KEY_D)){
//            if(Math.abs(xVel) < xMaxSpeed) {
//                xVel += xAccel;
//            }
//        }
        
         if(grounded) 
         {
            xVel *= friction;
         }
         xVel *= airResistance;
         
        super.update(deltaTime, gravity);
        moveX(xVel);
        
        if (collideWithHazard(xPos, yPos, width, height)) {
        	xPos = (float) initX;
        	yPos = (float) initY;
        	Game.dead = true;
        }
        else if (collideWithTarget(xPos, yPos, width, height)) {
        	Game.nextLevel = true;
        }
        else if (bounce(xPos, yPos, width, height)) {
        	yVel *= -0.8;
        	yPos = yPos - 0.55f;
        }
        
        if (xPos<-50 || xPos>Main.getScreenWidth()+50 ||  yPos>Main.getScreenHeight()+50) {
        	Game.dead=true;
        }
    }  
}