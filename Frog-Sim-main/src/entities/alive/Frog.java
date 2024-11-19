package entities.alive;


import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Point;

import animations.Smoke;
import core.Game;
import media.ImageLoader;

public class Frog extends Animal{

	protected static final int FROG_SIZE=100;
	protected float curJumpTime;
	protected float jumpTimer;
	protected float jumpCooldown;
	protected float jumpDistance;
	protected boolean isJumping;
	protected boolean canJump;
	protected Point destination;
	
	protected Image image;
	protected Color color;
	protected Image imageAccent;
	protected Color colorAccent;
	protected Image imageExtra;
	protected int extra;
	protected Color colorExtra;
	protected Image imageJump;
	protected SpriteSheet sheet;
	protected int decal;
	
	public Frog(float x, float y) 
	{ 
		super(x, y, FROG_SIZE, FROG_SIZE); 
		jumpTimer=30; 
		jumpDistance=200; 
		canJump=true; 
		sheet=ImageLoader.frogOne;
		image = sheet.getSprite(0, 0);
        imageAccent = sheet.getSprite(0, 1);
        extra=(int) (Math.random() * 3 + 2);
        imageExtra = sheet.getSprite(0, extra);
        imageJump = sheet.getSprite(0, 5);
        color = new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));
        colorAccent = new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));
        colorExtra = new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));
	}
	
	public Frog(float x, float y, int extra) 
	{ 
		super(x, y, FROG_SIZE, FROG_SIZE); 
		jumpTimer=30; 
		jumpDistance=200; 
		canJump=true; 
		sheet=ImageLoader.frogOne;
		image = sheet.getSprite(0, 0);
        imageAccent = sheet.getSprite(0, 1);
        imageExtra = sheet.getSprite(0, extra);
        imageJump = sheet.getSprite(0, 5);
        color = new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));
        colorAccent = new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));
        colorExtra = new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));
	}
	
	public Frog(float x, float y, boolean player) {
		super(x, y, FROG_SIZE, FROG_SIZE); 
		jumpTimer=30; 
		jumpDistance=200; 
		canJump=true; 
		sheet=ImageLoader.frogOne;
		image = sheet.getSprite(0, 0);
		imageAccent = sheet.getSprite(0, 1);

		imageJump = sheet.getSprite(0, 5);
		if(player) {
			this.color = new Color(Color.white);
			this.colorAccent = new Color(Color.green);
			this.colorExtra = new Color(Color.white);
			imageExtra = sheet.getSprite(0, 6);
		} else {
			color = new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));
			colorAccent = new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));
			colorExtra = new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));
			decal=(int)(Math.random() * 3 + 2);
			imageExtra = sheet.getSprite(0, decal);
		}
    }
	
	public void update() {
		
		if(isJumping)
		{
			jump();
		}
		else {
			jumpCooldown--;
			canJump = jumpCooldown<0;
		}
		super.update();	
	}
	private void jump() {
		curJumpTime++;
		if(curJumpTime>jumpTimer || getDistance(destination)<(jumpDistance/jumpTimer)*1.5f)
		{
			isJumping = false;
			xVel=0;
			yVel=0;
			jumpCooldown=jumpTimer/4;
			return;
		}
		float speed = jumpDistance/jumpTimer;
		xVel=(float) (speed*Math.cos(getAngle()));
		yVel=(float) (speed*Math.sin(getAngle()));
	}
	public void startJump(float angle)
	{
		if(canJump)
		{
			canJump=false;
			this.setAngle(angle);
			curJumpTime=0;
			isJumping=true;
			destination= new Point(Integer.MAX_VALUE, Integer.MAX_VALUE);
		}
	}
	public void startJump(float targetX, float targetY)
	{
		if(canJump)
		{
			canJump=false;
			this.setAngle(getAngleTo(targetX,targetY)) ;
			curJumpTime=0;
			isJumping=true;
			destination= new Point(targetX, targetY);
		}
	}
	public void startJump()
	{
		if(canJump)
		{
			canJump=false;
			curJumpTime=0;
			isJumping=true;
			destination= new Point(Integer.MAX_VALUE, Integer.MAX_VALUE);
		}
	}
	
	public void render(Graphics g) {
		super.render(g);
		Image bodyImage;
		if(isJumping) bodyImage=imageJump;
		else bodyImage=image;
		if (bodyImage != null) 
		{
			Image tmp = bodyImage.getScaledCopy(Game.zoomScale);
			tmp.setCenterOfRotation(tmp.getWidth() / 2 * Game.zoomScale, tmp.getHeight() / 2 * Game.zoomScale);
			tmp.rotate(90 + (float) ((180/Math.PI)*angle));
			tmp.draw(xPos, yPos, color);
		}
		if (imageAccent != null) 
		{
			
			Image tmp = imageAccent.getScaledCopy(Game.zoomScale);
			tmp.setCenterOfRotation(tmp.getWidth() / 2 * Game.zoomScale, tmp.getHeight() / 2 * Game.zoomScale);
			tmp.rotate(90 + (float) ((180/Math.PI)*angle));
			tmp.draw(xPos, yPos, colorAccent);
		}
		if (imageExtra != null) 
		{
			Image tmp = imageExtra.getScaledCopy(Game.zoomScale);
			tmp.setCenterOfRotation(tmp.getWidth() / 2 * Game.zoomScale, tmp.getHeight() / 2 * Game.zoomScale);
			tmp.rotate(90 + (float) ((180/Math.PI)*angle));
			tmp.draw(xPos, yPos, colorExtra);
		}
		
	}
	public void render(Graphics g, boolean inOrbital) {
		super.render(g);
		Image bodyImage;
		if(isJumping || inOrbital) bodyImage=imageJump;
		else bodyImage=image;
		if (bodyImage != null) 
		{
			Image tmp = bodyImage.getScaledCopy(Game.zoomScale);
			tmp.setCenterOfRotation(tmp.getWidth() / 2 * Game.zoomScale, tmp.getHeight() / 2 * Game.zoomScale);
			tmp.rotate(90 + (float) ((180/Math.PI)*angle));
			tmp.draw(xPos, yPos, color);
		}
		if (imageAccent != null) 
		{
			
			Image tmp = imageAccent.getScaledCopy(Game.zoomScale);
			tmp.setCenterOfRotation(tmp.getWidth() / 2 * Game.zoomScale, tmp.getHeight() / 2 * Game.zoomScale);
			tmp.rotate(90 + (float) ((180/Math.PI)*angle));
			tmp.draw(xPos, yPos, colorAccent);
		}
		if (imageExtra != null) 
		{
			Image tmp = imageExtra.getScaledCopy(Game.zoomScale);
			tmp.setCenterOfRotation(tmp.getWidth() / 2 * Game.zoomScale, tmp.getHeight() / 2 * Game.zoomScale);
			tmp.rotate(90 + (float) ((180/Math.PI)*angle));
			tmp.draw(xPos, yPos, colorExtra);
		}
		
	}
	public void modifyHealth(float multi)
	{
		maxHealth*=multi;
	}
	public void modifyRegen(float multi)
	{
		regen*=multi;
	}
	public void modifyAttackDamage(float multi)
	{
		attackDamage*=multi;
	}
	public void modifyAttackSpeed(float multi)
	{
		jumpDistance*=multi;
	}
	public void modifyJumpTimer(float multi)
	{
		jumpTimer*=multi;
	}
	
	public void modifyJumpDistance(float multi)
	{
		jumpDistance*=multi;
	}
	
	
	public void setHealthBonus(float newHealth) {
		this.maxHealth=newHealth;
	}
	public void setAttackDamageBonus(float newAttack) {
		this.attackDamage=newAttack;
	}
	public void setAttackSpeedBonus(float newAttack) {
		this.attackSpeed=newAttack;
	}
	public void setJumpDistance(float jump){
		jumpDistance=jump;
	}
	public void setJumpTimer(float timer){
		jumpTimer=timer;
	}
	public void resetJump()
	{
		if(Math.random()<0.1f)
		{
			isJumping=false;
			this.xVel=0;
			this.yVel=0;
			jumpCooldown=-100;
		}
		
	}
	@Override
	public void onDeath() {
		
	}
	
}