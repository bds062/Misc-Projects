package ambient.ui;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

import core.Main;

public class Button {
	protected float LTX;
    protected float LTY;
    
    protected float RBX;
    protected float RBY;
    
    public Integer mouseX;
	public Integer mouseY;
    
    protected int width;
    protected int height;
    
    public boolean clicked;
    protected Image image;
    
    private int id;
    
    public Button(Image image, int id, float x, float y, int width, int height) {
    	this.id = id;
    	this.image = image;
    	this.width = width;
        this.height = height;
    	LTX = x;
    	LTY = y;
        RBX = x + width;
    	RBY = y + height;
    	clicked = false;
    }
    
    public void render(Graphics g) {
    	image.draw(LTX, LTY, width, height);
    }	
    
    public void update(GameContainer gc) {   	
    	mouseX = Mouse.getX();
		mouseY = Mouse.getY();
		if (mouseY > Main.getScreenHeight()/2) {
	    	mouseY = Main.getScreenHeight()/2 - (mouseY - (Main.getScreenHeight()/2));
	    } 
		else if (mouseY < Main.getScreenHeight()/2) {
	    	mouseY = Main.getScreenHeight()/2 + (-mouseY + (Main.getScreenHeight()/2));
	    }

    	if ((Mouse.isButtonDown(0)) && (mouseX < RBX && mouseX > LTX && mouseY < RBY && mouseY > LTY)){
    		clicked = true;
    	}
    	else {
    		clicked = false;
    	}
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
    
	public int getId() {
		return id;
	}
	
	public int getWidth() {
		return width;
	}
    
}
