package world;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Spikes {
	protected float LTX;
    protected float LTY;
    
    protected float RBX;
    protected float RBY;
    
    protected int width;
    protected int height;
    
    public Spikes(float x, float y, int width, int height) {
    	LTX = x;
    	LTY = y;
        this.width = width;
        this.height = height;
        RBX = x + this.width;
    	RBY = y + this.height;
    }
    
    public void render(Graphics g) {
    	g.setColor(Color.red);
    	g.fillOval(LTX, LTY, width, height);
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
    
    
}