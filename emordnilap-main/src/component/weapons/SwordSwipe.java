package component.weapons;

import core.Game;
import entities.Entity;
import entities.enemy.Drone;
import entities.enemy.Frog;
import entities.enemy.Turret;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class SwordSwipe extends Entity{

	private int rotationAngle;
	private int tile;
	private int lifetime;
	private static Image swingImage;


	public static void init() throws SlickException
	{
		swingImage = new Image("res/swordAttack.png");
	}
	public SwordSwipe(float x, float y, int width, int height) {
		super(x, y, 128, 128);
		lifetime=0;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(Graphics g) {
		rotationAngle=lifetime*5;
		swingImage.setRotation(rotationAngle);
		swingImage.draw(xPos,yPos);
	}

	public void update()
	{
		lifetime++;
		if(lifetime>36)
		{
			Game.curLevel.removeThisSwing(this);
		}
		if(lifetime>1)
		{
			Entity collison = collideWithWhatEntities(xPos, yPos, 128, 128);
			if(collison!=null)
			{

				if(collison instanceof Turret)
				{
					Game.curLevel.removeThisTurret((Turret) collison);
				}
				if(collison instanceof Frog)
				{
					Game.curLevel.removeThisFrog((Frog) collison);
				}
				if(collison instanceof Drone)
				{
					Game.curLevel.removeThisDrone((Drone) collison);
				}

			}
		}
	}
}