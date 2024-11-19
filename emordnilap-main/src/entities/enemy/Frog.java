package entities.enemy;

import data.Snapshot;
import entities.Entity;
import entities.player.Player;
import levels.Level;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class Frog extends Entity{
	private double initX;
    private double initY;
    private float rotateAngle;
    private int shootTick;
    private int tick;
    private int xSpeed;
    private int ySpeed;
    private float gravSpeed;

	private static Image frog;
	
	private static float tarX;
	private static float tarY;
	
	public static Level curLevel;
	
	public static void init() throws SlickException {
		frog = new Image("res/frog.png");
	}

	public Frog(float x, float y) {
		super(x,y,frog.getWidth(), frog.getWidth());
		initX = x;
        initY = y;
        rotateAngle = 0;
        shootTick = 20;
        tick = 0;
        xSpeed = 5;
        gravSpeed = 1f;
        width = 32;
        height = 32;
		// TODO Auto-generated constructor stub
	}
	
	public void update(float deltaTime, float gravity)
	{
		if (collideWithMap(xPos, yPos, width, height)) {
        	xSpeed=(int)(-xSpeed*.8);
        	ySpeed=(int)(-ySpeed*.8);
        }
		super.update(deltaTime, gravity);
		tick++;
		if(tick >=50){
			if(Math.random() > 0.5)
			{
				
				xVel = 5;
			}
			else {
				xVel = -5;
			}
			tick = 0;
			ySpeed = -30;
		}
		moveX(xVel);
		yPos += ySpeed;
		


		

	}
	

	
	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(frog, xPos, yPos);
	}
	
	
	public void shoot(Graphics g, Player target)
	{
			}

	public Snapshot getSnap(int time){
		return new Snapshot(time, (int) this.getX(), (int) this.getY(), 1, (int) this.rotateAngle);
	}
}