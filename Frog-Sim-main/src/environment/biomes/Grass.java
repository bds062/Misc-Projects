package environment.biomes;

import core.Game;
import environment.Map;
import media.ImageLoader;
import org.newdawn.slick.Graphics;

public class Grass extends Biome {



    public Grass(float noiseValue) {
        super(noiseValue);
    }

    public void update() {

    }

    public void render(Graphics g) {
		if (rand < .2) g.drawImage(ImageLoader.grassOne, tile.getX(), tile.getY());
        else if (rand < .4) g.drawImage(ImageLoader.grassTwo, tile.getX(), tile.getY());
        else if (rand < .6) g.drawImage(ImageLoader.grassThree, tile.getX(), tile.getY());
        else if (rand < .8) g.drawImage(ImageLoader.grassFour, tile.getX(), tile.getY());
        else g.drawImage(ImageLoader.grassFive, tile.getX(), tile.getY());
    }
}