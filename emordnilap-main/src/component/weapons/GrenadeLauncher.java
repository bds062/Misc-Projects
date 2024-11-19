package component.weapons;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.lwjgl.input.Mouse;

import core.Game;
import core.Main;
import entities.player.Player;

public class GrenadeLauncher extends Weapon{

	private float myX;
	private float myY;
	
	private int mouseX;
	private int mouseY;
	
	private static int width;
	private static int height;
	
	private float rotationAngle;
	private boolean ready;
	private static float FIRE_RATE = 0.5f;
	private Player holder;
	
	private static Image revolverImage;
	private static Image revolverImageFlipped;
	
	
	
	public GrenadeLauncher(Player gunman) {
		holder=gunman;
		myX=holder.getCenterX();
		myY=holder.getCenterY();
		ready = true;
		rotationAngle = 0;
		mouseX=0;
		mouseY=0;
	}
	public void render(Graphics g) {;
    	float drawY = Main.getScreenHeight()-myY;

		if(mouseX>myX)
		{
			if(mouseY < drawY) {
			rotationAngle = (float) Math.atan((mouseY-drawY)/(myX-mouseX));
			}
			else {
				rotationAngle = (float) Math.atan((drawY-mouseY)/(mouseX-myX));
			}
			revolverImage.setRotation((float)(rotationAngle*(180/Math.PI)));
			g.drawImage(revolverImage, myX, myY);
		}
		else
		{
			if(mouseY < myY) {
				rotationAngle = (float) Math.atan(-(drawY-mouseY)/(myX-mouseX));
				}
				else {
					rotationAngle = (float) Math.atan(-(mouseY-drawY)/(mouseX-myX));
//					rotateAngle = 45;
				}
			revolverImageFlipped.setRotation((float)(rotationAngle*(180/Math.PI)));
			g.drawImage(revolverImageFlipped, myX, myY);
//			g.drawString("" + mouseY + "", 30, 30);
		}
    }
	public static void init() throws SlickException
	{
		revolverImage = new Image("res/grenadelauncher.png");
		revolverImage = revolverImage.getScaledCopy(0.6f);
		width = revolverImage.getWidth();
		height = revolverImage.getHeight();
		revolverImageFlipped = revolverImage.getFlippedCopy(true,false);
	}

	public void update()
	{
		mouseX=(int) (Mouse.getX()+Game.curLevel.cam.getX());
		mouseY=Mouse.getY();
//		if (mouseY > Main.getScreenHeight()/2) {
//	    	mouseY = Main.getScreenHeight()/2 - (mouseY - (Main.getScreenHeight()/2));
//	    } 
//		else if (mouseY < Main.getScreenHeight()/2) {
//	    	mouseY = Main.getScreenHeight()/2 + (-mouseY + (Main.getScreenHeight()/2));
//	    }
//		
//		rotationAngle=(float) Math.atan((holder.getY()-mouseY)/(holder.getX()-mouseX));
		
		myX=holder.getCenterX() - width/2;
		myY=holder.getCenterY() + height/2;
	}
	public float getRotationAngle()
	{
		if(myX>mouseX)
    	{
			return (float) (rotationAngle+Math.PI);
    	}
    	else
    	{
    		return rotationAngle;
    	}
	}
	@Override
	public void shoot()
	{
		if(myX>mouseX)
		{
			rotationAngle+=Math.PI;
		}
		Game.curLevel.addGrenade(new Gernade(Game.curLevel.getPlayer().getCenterX(), Game.curLevel.getPlayer().getCenterY(), Gernade.WIDTH, Gernade.HEIGHT, rotationAngle));
	}
}
