package component.weapons;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import component.Component;
import core.Game;
import entities.player.Player;

public abstract class Weapon extends Component{
	protected float myX;
	protected float myY;
	
	protected int mouseX;
	protected int mouseY;
	
	protected static int width;
	protected static int height;
	
	protected float rotationAngle;
	protected boolean ready;
	protected static float FIRE_RATE = 2000f;
	protected Player holder;
	
	
	public abstract void render(Graphics g);
	
	public static void init() throws SlickException
	{
		
	}

	public void update()
	{
		mouseX=(int) (Mouse.getX()+Game.curLevel.cam.getX());
		mouseY=Mouse.getY();
		myX=holder.getCenterX() - width/2;
		myY=holder.getCenterY() + height/2;
	}
	public abstract void shoot();
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
}
