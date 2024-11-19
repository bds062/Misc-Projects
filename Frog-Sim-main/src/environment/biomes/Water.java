package environment.biomes;

import core.Game;
import media.ImageLoader;

import org.newdawn.slick.Graphics;

public class Water extends Biome {

    public Water(float noiseValue) {
        super(noiseValue);
    }

    public void update() {

    }

    public void render(Graphics g) {
    	 if (rand < .2) g.drawImage(ImageLoader.waterOne, tile.getX(), tile.getY());
         else if (rand < .4) g.drawImage(ImageLoader.waterFour, tile.getX() , tile.getY() );
         else if (rand < .6) g.drawImage(ImageLoader.waterThree, tile.getX() , tile.getY()); 
         else if (rand<.8) g.drawImage(ImageLoader.waterFive, tile.getX(), tile.getY());
         else g.drawImage(ImageLoader.waterSix, tile.getX() , tile.getY() );
    }
}