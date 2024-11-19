package entities.death;

import core.Game;
import entities.Entity;
import media.ImageLoader;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class baseDeath extends Entity {

    protected int timer;
    protected Image image;
    protected float tick;

    public baseDeath(float x, float y, float width, float height) {
        super(x, y, width, height);
        timer = 60;
        image = ImageLoader.deathSkull;
        tick = 1;
    }

    @Override
    public boolean collideWith(Entity e) {
        return false;
    }

    public void update() {
        if (tick > 0) {
            tick -= 0.001;
        }

    }

    public void render(Graphics g) {

        g.setColor(new Color(0, 0, 0));
//        if (image != null) {
        Image tmp = image.getScaledCopy(Game.zoomScale);
        tmp.setAlpha(tick);

        tmp.draw(xPos, yPos);
//        }
    }

}
