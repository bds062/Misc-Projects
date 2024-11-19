package component.weapons;

import core.Game;
import entities.Entity;
import entities.Explosion;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Gernade extends Entity{
	private static int LAUNCH_POWER=38;
	private static Image grenadeImage;
	private static SpriteSheet grenadeSheet;

	private static final float friction = 0.92f;
	private static final float airResistance = 0.97f;
	
	public static final int WIDTH = 64;
	public static final int HEIGHT = 74;
	
	private static int SIZE=128;
	
	private int tile;
	private int timer;
	
	public Gernade(float x, float y, int width, int height, float angle) {
		super(x, y, width, height);
		this.xVel=(float) (Math.cos(angle)*LAUNCH_POWER);
		this.yVel=(float) (Math.sin(angle)*LAUNCH_POWER);
		tile=0;
		timer=0;
	}

	public static void init() throws SlickException
	{
		grenadeImage = new Image("res/grenadesprites.png");
		grenadeSheet = new SpriteSheet(grenadeImage, WIDTH, HEIGHT);
	}
	
	public void render(Graphics g) {
		grenadeSheet.getSprite(tile, 0).draw(xPos,yPos);
	}
	
	public void update(float deltaTime, float gravity)
	{
//		if(grounded)
//		{
//			xVel *= friction;
//		}
//		xVel *= airResistance;
//
//		super.update(deltaTime, gravity);
//		moveX(xVel);
		float newY = yPos;

		this.yVel += gravSpeed;
		newY += this.yVel;

		if (hit(xPos, newY, getWidth(), getHeight())){
			if (yVel >= 0.115){
				this.yPos = (float) Math.floor(yPos);
				grounded = true;
				xVel*=friction;
			}
//            this.yVel = 0;
			xVel*=airResistance;
			this.yVel=(yPos-newY)*.4f;
		}
		else {
			yPos = newY;
			grounded = false;
		}

		float shift=xVel;

		float newX = xPos + shift;

		if (!hit(newX, yPos, getWidth(), getHeight())){
			this.xPos = newX;
		}
		else
		{
//        	this.xVel=0;
			this.xVel=-shift*.5f;
		}
        
		timer++;

		float avgVel = (Math.abs(xVel)+Math.abs(xVel))/2;

		int ticks = (int) (1/(avgVel)) + 3;

    	if (timer%ticks==0) {
    		if(xVel>0)
    		{
    			tile--;
    		}
    		else
    		{
    			tile++;
    		}
    	} 
    	if (tile>7) {
    		tile=0;
    	}
    	if (tile<0) {
    		tile=7;
    	}

	}

	public int getTimer() {
		return timer;
	}
	public void explode()
	{
		Game.curLevel.addExplosion(new Explosion(xPos,yPos,0,0));
	}
}
