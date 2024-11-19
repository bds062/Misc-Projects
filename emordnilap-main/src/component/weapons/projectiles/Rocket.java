package component.weapons.projectiles;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import core.Game;
import entities.Entity;
import entities.Explosion;
import entities.enemy.Turret;

public class Rocket extends Projectile{
	
	private static Image rocketImage;
	private static SpriteSheet rocketSheet;
	private Image rotatedImage;
	private int tile;
	private int timer;
	private float projAngle;
	private float speedMult;
	private float smoother;
	private float shotAngle;
	private float drawAngle;

	
	public Rocket(float ix,float iy, float tX, float tY, float multiplier, float angle)
	{
		super(ix,iy,tX,tY, multiplier);
		drawAngle=angle;
		
		speedMult = multiplier;
		getSpeeds();
		createRocketSheet();
	}
	
	public Rocket(float ix,float iy, int frame, float angle)
	{
		super(ix,iy,ix,iy, 0);
		ghost = true;
		projAngle = angle;
		drawAngle = angle;
		createRocketSheet();
		this.frame = frame;
		super.id = 2;
	}
	
	public void render(Graphics g) {
		timer++;
    	if (timer%3==0) {
    		tile++;
    	}
    	if (tile>8) {
    		tile=0;
    	}
    	if (tile<0) {
    		tile=8;
    	}
    	rotatedImage = rocketSheet.getSprite(tile, 0);
    	rotatedImage.setRotation((float)(drawAngle*(180/Math.PI)));;
    	rotatedImage.draw(xPos,yPos);
    	
//    	rocketSheet.getSprite(tile, 0).draw(xPos,yPos);
	}
	private void createRocketSheet()
	{
		rocketImage.setRotation((float)(drawAngle*(180/Math.PI)));
		rocketSheet = new SpriteSheet(rocketImage, 128, 128);
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
		if(timer>1)
		{
			Entity collison = collideWithWhatEntities(xPos, yPos, 64, 64);
			if(collideWithMap(xPos, yPos, 64, 64))
			{
				explode();
			}
			if(collison!=null)
			{
	
				if(collison==Game.curLevel.getPlayer() && timer < 20)
				{
//					Game.curLevel.getPlayer().killed();
				}
				else {
					explode();
				}
			}
		}
		xPos += xSpeed;
		yPos += ySpeed;
		
	}
	public static void init() throws SlickException
	{
		rocketImage = new Image("res/RocketSpriteSheet.png");
		rocketSheet = new SpriteSheet(rocketImage, 128, 128);
	}
	public void explode()
	{
		Game.curLevel.addExplosion(new Explosion(xPos,yPos,0,0));
		Game.curLevel.removeThisRocket(this);
	}
	
	public int getFrame(){
		return tile;
	}

	public float getAngle(){
		return drawAngle;
	}
}
