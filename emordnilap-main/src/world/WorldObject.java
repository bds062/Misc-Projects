package world;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.util.BufferedImageUtil;

public abstract class WorldObject {
	protected float LTX;
    protected float LTY;
    
    protected float RBX;
    protected float RBY;
    
    protected int width;
    protected int height;
    
    public WorldObject(float x, float y, int width, int height) {
    	LTX = x;
    	LTY = y;
        this.width = width;
        this.height = height;
        RBX = x + this.width;
    	RBY = y + this.height;
    }
    
    public void render(Graphics g) {

    }

	public float getLTX() {
		return LTX;
	}

	public float getLTY() {
		return LTY;
	}

	public float getRBX() {
		return RBX;
	}

	public float getRBY() {
		return RBY;
	}
	public int getHeight()
	{
		return height;	
	}
	public int getWidth()
	{
		return width;	
	}
	
	public static Image loadImage(String path) throws IOException, SlickException
	{
	    BufferedImage bufferedImage = ImageIO.read(new File(path));
	    Texture texture = BufferedImageUtil.getTexture("", bufferedImage);
	    Image image = new Image(texture.getImageWidth(), texture.getImageHeight());
	    image.setTexture(texture); 
	    return image;
	}
}
