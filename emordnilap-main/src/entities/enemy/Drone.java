package entities.enemy;

import data.Snapshot;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import component.weapons.projectiles.Bullet;
import core.Game;
import core.Main;
import entities.Entity;
import entities.player.Player;
import levels.Level;


public class Drone extends Entity{
	
	private double initX;
    private double initY;
    private float rotateAngle;
    private int shootTick;
    private int tick;
    private int xSpeed;

	private static Image turretImage;
	private static Image turretFlipped;
	private static Image turretStand;
	private static Image turretGun;
	private static Image drone;
	private Image turFlipped;
	
	private static float tarX;
	private static float tarY;
	
	public static Level curLevel;
	
	public static void init() throws SlickException {
		turretImage = new Image("res/turretgun.png");
		turretGun = new Image("res/turretgun.png");
		turretFlipped = turretGun.getFlippedCopy(true, false);
		drone = new Image("res/drone.png");
		turretStand = new Image("res/turretstand.png");
	}

	public Drone(float x, float y) {
		super(x,y,turretImage.getWidth(), turretImage.getWidth());
		initX = x;
        initY = y;
        rotateAngle = 0;
        shootTick = 100;
        tick = 0;
        xSpeed = 5;
		// TODO Auto-generated constructor stub
	}
	
	public void update()
	{
		if(collideWithMap(xPos, yPos, turretImage.getWidth(), turretImage.getWidth()))
		{
			xSpeed = -1*xSpeed;
		}
		moveX(xSpeed);
	}
	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
//		g.drawImage(turretImage,xPos,yPos);
		float drawY = Main.getScreenHeight()-yPos;
		tick++;
		if(tick ==61)
		{
			tick =0;
		}
		if(tick ==60) {
			shoot(g, Game.getPlayer());
		}
		float tarX = Game.getPlayer().getCenterX();
		float tarY = Main.getScreenHeight()-Game.getPlayer().getCenterY();
		float mouseX = Mouse.getX();
		float mouseY = Mouse.getY();
//		g.drawString(""+ mouseX + "," + mouseY, 100, 100);
//		g.drawString(""+ tarX + "," + tarY, 100, 200);
		g.drawImage(drone,xPos,yPos);
		if(tarX > xPos)
		{
			if(tarY < yPos) {
			rotateAngle = (float) Math.atan((tarY-drawY)/(xPos-tarX));
			}
			else {
				rotateAngle = (float) Math.atan((drawY-tarY)/(tarX-xPos));
			}
			turretFlipped.setRotation((float)(rotateAngle*(180/Math.PI)));
			g.drawImage(turretFlipped, xPos, yPos);
		}
		else
		{
			if(tarY < yPos) {
				rotateAngle = (float) Math.atan(-(drawY-tarY)/(xPos-tarX));
				}
				else {
					rotateAngle = (float) Math.atan(-(tarY-drawY)/(tarX-xPos));
//					rotateAngle = 45;
				}
			turretGun.setRotation((float)(rotateAngle*(180/Math.PI)));
			g.drawImage(turretGun, xPos, yPos);
//			g.drawString("" + mouseY + "", 30, 30);
		}
		

	}
	
	
	public void shoot(Graphics g, Player target)
	{
		float mouseX = Mouse.getX();
		float mouseY = Mouse.getY();
		tarX = target.getCenterX();
        tarY = target.getCenterY();
//		g.drawRect(mouseX, Main.getScreenHeight()-mouseY, 20, 20);
//		g.drawString(""+ xPos + yPos + mouseX + (Main.getScreenHeight()-mouseY)+"", 100, 100);
		Level.addBullet(new Bullet(xPos+turretStand.getWidth()/2,yPos+turretStand.getHeight()/2,tarX, tarY,5,false));
	}

	public Snapshot getSnap(int time){
		return new Snapshot(time, (int) this.getX(), (int) this.getY(), 1, (int) this.rotateAngle);
	}
}