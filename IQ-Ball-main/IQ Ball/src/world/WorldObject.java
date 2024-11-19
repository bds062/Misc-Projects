package world;

import org.newdawn.slick.Graphics;

public class WorldObject {
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
    
    
}