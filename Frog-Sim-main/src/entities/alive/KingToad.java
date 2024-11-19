package entities.alive;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Point;

import animations.Animation;
import core.Game;
import grouping.Pack;
import media.ImageLoader;

public class KingToad extends Animal {
	final static float TOAD_SIZE=256;
	protected static final int FROG_SIZE=100;
	protected float curJumpTime;
	protected float jumpTimer;
	protected float jumpCooldown;
	protected float jumpDistance;
	protected boolean isJumping;
	protected boolean canJump;
	protected Point destination;
	private Pack myArmy;
	private boolean spinner=false;
	private float deathAngle;
	private float deathAccel;
	private int tick; 
	private Image image;
	private Image image2;
	private boolean king;
	public KingToad (float x, float y, Pack army) { 
		super(x,y,TOAD_SIZE,TOAD_SIZE);  
		myArmy=army;
		jumpTimer=30; jumpDistance=200; canJump=true; attackDamage+=10000;
		image=ImageLoader.toad.getScaledCopy(Game.zoomScale);
		image2=ImageLoader.toadCrown.getScaledCopy(Game.zoomScale);
		spinner=false;
		king=true;
//		flying=true;
		}
	public KingToad (float x, float y) { 
		super(x,y,TOAD_SIZE,TOAD_SIZE);  
		jumpTimer=30; jumpDistance=200; canJump=true; attackDamage+=10000;
		image=ImageLoader.toad.getScaledCopy(Game.zoomScale);
		image2=ImageLoader.toadCrown.getScaledCopy(Game.zoomScale);
		spinner=false;
		king=false;
//		flying=true;
		}
	public void setPack(Pack p)
	{
		myArmy=p;
	}
	public void update() {
		if (spinner) {
			
            tick++;
            this.setAngle(tick);
            xPos+=(float)(deathAccel*Math.cos(deathAngle));
			yPos+=(float)(deathAccel*Math.sin(deathAngle));
            this.image = this.image.getScaledCopy(this.image.getWidth() + tick, this.image.getHeight() + tick);
            if (tick >= 140) {
                Game.entities.remove(this);
            }
        } else {
		
		if(myArmy!=null)
		{
			myArmy.update(); 
		}
		
		startJump(getAngleTo(Game.bestFrog));
		if(isJumping)
		{
			jump();
		}
		else {
			jumpCooldown--;
			canJump = jumpCooldown<0;
		}
		destinationPoint=destination;
		super.update();
        }
	}
	public void render(Graphics g) {
		super.render(g);
		Image tmp = image;
		tmp.setCenterOfRotation(tmp.getWidth() / 2 * Game.zoomScale, tmp.getHeight() / 2 * Game.zoomScale);
		tmp.rotate(90 + (float) ((180/Math.PI)*angle));
		tmp.draw(xPos, yPos);
		image=ImageLoader.toad.getScaledCopy(Game.zoomScale);
		if(king) {
			Image tmp2=image2;
			tmp2.setCenterOfRotation(tmp.getWidth() / 2 * Game.zoomScale, tmp.getHeight() / 2 * Game.zoomScale);
			tmp2.rotate(90 + (float) ((180/Math.PI)*angle));
			tmp2.draw(xPos, yPos);
			image2=ImageLoader.toadCrown.getScaledCopy(Game.zoomScale);
		}
	}
	private void jump() {
		curJumpTime++;
		if(curJumpTime>jumpTimer) //|| getDistance(destination)<(jumpDistance/jumpTimer)*1.5f)
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
	public void attackClosest()
	{
		Pack enemy=null;
		if(attackTimer>attackSpeed)
		{	
			enemy = myArmy.getEnemyPack();
			if (enemy != null)
			{
				ArrayList<Frog> enemyFrogs = enemy.getFrogs();
				Animal target=null;
			
				float minDist=300;
				for(Frog f: enemyFrogs)
				{
					if(this.getDistance(f)<minDist)
					{
						target=f;
						minDist=this.getDistance(f);
					}
				}
				Animal a=enemy.alphaFrog;
				if(this.getDistance(a)<minDist)
				{
					target=a;
				}
				
				
				if(target != null)
				{
					target.curHealth-=attackDamage;
					attackTimer =0;
				}
			}
		}
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
	public void onDeath()
	{
		Game.bestFrog.playerPack.randomBoost(1.01f);
		myArmy.disband();
		spinner=true;
		deathAngle=(float) (Math.random()*Math.PI);
		deathAccel=(float)Math.random()*5+5;
//		Game.animations.add(new Animation(this.getCenterX(), this.getCenterY(),ImageLoader.frogOne, 50));
	}
}