package component.weapons;

import core.Game;
import core.Main;
import entities.player.Player;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Sword extends Weapon{
	
	private static Image swordImage;
	private static Image swordImageFlipped;
	private static SpriteSheet swordSheet;
	private static SpriteSheet swordSheetFlipped;
	
	private int step=0;
	private int frames=0;
	
	public Sword(Player gunman) {
		holder=gunman;
		myX=holder.getCenterX();
		myY=holder.getCenterY();
		ready = true;
		rotationAngle = 0;
		mouseX=0;
		mouseY=0;
	}
	public void render(Graphics g) {
		Image temp;
		float drawY = Main.getScreenHeight()-myY;
		if(mouseX>myX)
		{
			if(mouseY < drawY) {
			rotationAngle = (float) Math.atan((mouseY-drawY)/(myX-mouseX));
			}
			else {
				rotationAngle = (float) Math.atan((drawY-mouseY)/(mouseX-myX));
			}
//			swordImage.setRotation((float)(rotationAngle*(180/Math.PI)));
//			swordSheet.getSprite(step, 0).draw(myX,myY-100);
			temp=swordSheetFlipped.getSprite(step, 0);
			temp.setRotation((float)(rotationAngle*(180/Math.PI)));
			g.drawImage(temp,myX,myY-100);
		}
		else
		{
			if(mouseY < myY) {
				rotationAngle = (float) Math.atan(-(drawY-mouseY)/(myX-mouseX));
				}
				else {
					rotationAngle = (float) Math.atan(-(mouseY-drawY)/(mouseX-myX));
				}
			temp=swordSheet.getSprite(step, 0);
			temp.setRotation((float)(rotationAngle*(180/Math.PI)));
			g.drawImage(temp,myX,myY-100);
		}
    }
	public static void init() throws SlickException
	{
		swordImage = new Image("res/swordMinuteTilted.png");
		swordImageFlipped=swordImage.getFlippedCopy(true, false);
		swordSheet = new SpriteSheet(swordImage, 200,140);
		swordSheetFlipped=new SpriteSheet(swordImageFlipped, 200,140);
		width = 200;
		height = 140;
	}

	public void update()
	{
		super.update();
		frames++;
		if(frames%10==0) {
			step++;
		}
		if (step>=swordSheet.getHorizontalCount()) {
			step=0;
		}
//		mouseX=(int) (Mouse.getX()+Game.curLevel.cam.getX());
//		mouseY=Mouse.getY()-100;
//		myX=holder.getCenterX() - width/2;
//		myY=holder.getCenterY() + height/2;
	}
	public void shoot()
	{
		if(myX<mouseX)
		{
			Game.curLevel.addSwing(new SwordSwipe(myX+128,myY-50, 10, 10));
		}
		else
		{
			Game.curLevel.addSwing(new SwordSwipe(myX-128,myY-50, 10, 10));
		}
	}

}