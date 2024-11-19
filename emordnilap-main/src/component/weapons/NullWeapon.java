package component.weapons;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.lwjgl.input.Mouse;
import core.Game;
import core.Main;
import entities.player.Player;
import component.weapons.projectiles.Rocket;

public class NullWeapon extends Weapon{
	
	
	public NullWeapon(Player gunman) {
		holder=gunman;
		myX=holder.getCenterX();
		myY=holder.getCenterY();
		ready = true;
		rotationAngle = 0;
		mouseX=0;
		mouseY=0;
	}
	public void render(Graphics g) {
	
    }
	public static void init() throws SlickException
	{
		
	}

	public void update()
	{
		super.update();
	}
	public void shoot()
	{
		
	}

}
