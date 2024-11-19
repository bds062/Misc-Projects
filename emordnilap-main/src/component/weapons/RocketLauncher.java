package component.weapons;

import component.weapons.projectiles.Rocket;
import core.Game;
import core.Main;
import entities.player.Player;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class RocketLauncher extends Weapon{
	
	private static Image revolverImage;
	private static Image revolverImageFlipped;
	
	public RocketLauncher(Player gunman) {
		holder=gunman;
		myX=holder.getCenterX();
		myY=holder.getCenterY();
		ready = true;
		rotationAngle = 0;
		mouseX=0;
		mouseY=0;
	}
	public void render(Graphics g) {
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
				}
			revolverImageFlipped.setRotation((float)(rotationAngle*(180/Math.PI)));
			g.drawImage(revolverImageFlipped, myX, myY);
		}
		
    }
	public static void init() throws SlickException
	{
		revolverImage = new Image("res/RocketLauncher.png");
		revolverImage = revolverImage.getScaledCopy(1);
		width = revolverImage.getWidth();
		height = revolverImage.getHeight();
		revolverImageFlipped = revolverImage.getFlippedCopy(true,false);
	}

	public void update()
	{
		super.update();
//		mouseX=Mouse.getX();
//		mouseY=Mouse.getY();
//		myX=holder.getCenterX() - width/2;
//		myY=holder.getCenterY() + height/2;
	}
	public void shoot()
	{
		if(myX>mouseX)
		{
			rotationAngle+=Math.PI;
		}
//		Game.curLevel.addGrenade(new Gernade(myX, myY, Gernade.WIDTH, Gernade.HEIGHT, rotationAngle));
		Game.curLevel.addRocket(new Rocket(Game.curLevel.getPlayer().getCenterX()-50, Game.curLevel.getPlayer().getCenterY()+10, mouseX, Main.getScreenHeight()-mouseY, 10, rotationAngle));	}

}
