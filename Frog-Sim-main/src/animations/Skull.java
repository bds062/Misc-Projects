package animations;

import core.Game;
import entities.Entity;
import media.ImageLoader;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Skull{

	protected int timer;
    protected Image image;
    protected float tick;
    protected float xPos;
    protected float yPos;
    
    protected boolean alive;
    
    public Skull(float x, float y) {
    	xPos=x; yPos=y;
        timer = 60;
        image = ImageLoader.deathSkull;
        tick = 1;
        alive=true;
    }

    public void update() {
        if (tick > 0) {
            tick -= 0.004;
        }

    }

    public void render(Graphics g) {

    	g.setColor(new Color(0, 0, 0));
//      if (image != null) {
      Image tmp = image.getScaledCopy(Game.zoomScale);
      tmp.setAlpha(tick);
      if (tmp.getAlpha() == 0) {
          alive=false;
      }
      tmp.draw(xPos, yPos);
//      }
  }
  public boolean getAlive() { return alive; }

}
