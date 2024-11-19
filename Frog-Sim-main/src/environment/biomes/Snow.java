package environment.biomes;

import core.Game;
import media.ImageLoader;

import org.newdawn.slick.Graphics;

public class Snow extends Biome {

    public Snow(float noiseValue) {
        super(noiseValue);
    }

    public void update() {

    }

    public void render(Graphics g) {
    	 if (rand < .2) g.drawImage(ImageLoader.snowOne, tile.getX(), tile.getY());
         else if (rand < .4) g.drawImage(ImageLoader.snowTwo, tile.getX(), tile.getY());
         else if (rand < .6) g.drawImage(ImageLoader.snowThree, tile.getX(), tile.getY());
         else if (rand < .8) g.drawImage(ImageLoader.snowFour, tile.getX(), tile.getY());
         else g.drawImage(ImageLoader.snowFive, tile.getX() , tile.getY());
    }
}