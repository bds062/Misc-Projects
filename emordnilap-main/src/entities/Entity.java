package entities;

import component.weapons.Gernade;
import core.Game;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.util.BufferedImageUtil;
import world.Door;
import world.Target;
import world.TileSet;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class Entity {
    protected Vector2f pos;

    protected float xPos;
    protected float yPos;

    protected float yVel = 0;
    protected float xVel = 0;
    protected float xAccel = 2;

    protected int width;
    protected int height;
    protected int weight = 20;

    final protected float gravSpeed=1f;
    protected boolean grounded;

    public Entity(float x, float y, int width, int height) {
        xPos = x;
        yPos = y;
        this.width = width;
        this.height = height;
    }

    public abstract void render(Graphics g);

    public void update(float deltaTime, float gravity){
        float newY = yPos;

        this.yVel += gravSpeed;
        newY += this.yVel;

        if (hit(xPos, newY, getWidth(), getHeight())){
            if (yVel >= 0.115){
                this.yPos = (float) Math.floor(yPos);
                grounded = true;
            }
            this.yVel = 0;
//            this.yVel=(yPos-newY)*.4f;
        }
        else {
            yPos = newY;
            grounded = false;
        }
    }

    public void update2(float deltaTime, float gravity){
        float newY = yPos;

        this.yVel += gravSpeed;
        newY += this.yVel;

        if (collideWithMap(xPos, newY, getWidth(), getHeight())){
            if (yVel >= 0.115){
                //this.yPos = (float) Math.floor(yPos);
                grounded = true;
            }
            this.yVel = 0;
        }
        else {
            yPos = newY;
            grounded = false;
        }
    }

    protected void moveX(float shift)
    {
        float newX = xPos + shift;

        if (!hit(newX, yPos, getWidth(), getHeight())){
            this.xPos= newX;
        }
        else
        {
        	this.xVel=0;
//            this.xVel=-shift*1f;
        }
    }

    protected void moveX2(float shift)
    {
        float newX = xPos + shift;

        if (!collideWithMap(newX, yPos, getWidth(), getHeight())){
            this.xPos= newX;
        }
        else
        {
            this.xVel=0;
        }
    }


    protected boolean collideWithMap(float x, float y, int width, int height) {
    	for (TileSet t : Game.curLevel.getTileSets()) {
    		if(x < t.getRBX() &&
    	    		   x + width > t.getLTX() &&
    	    		   y < t.getRBY() &&
    	    		   y + height > t.getLTY()){
    	    			return true;
    	    		}
    	}
    	return false;
    }

    protected boolean collideWithEntities(float x, float y, int width, int height) {
        if (Game.getAll().size() > 0){
            for (int i = 0; i < Game.getAll().size(); i++) {
                Entity t = Game.getAll().get(i);
                if (t != this){
                	if (this instanceof Gernade) {
                		if (!(((Gernade)this).getTimer() > 20)) {
                			break;
                		}
                	}
                    if(x < t.getRBX() &&
                            x + width > t.getX() &&
                            y < t.getRBY() &&
                            y + height > t.getY()){
                        return true;
                    }
                }
            }
            return false;
        }
        return false;
    }
    protected Entity collideWithWhatEntities(float x, float y, int width, int height) {
        if (Game.getAll().size() > 0){
            for (int i = 0; i < Game.getAll().size(); i++) {
                Entity t = Game.getAll().get(i);
                    if(x < t.getRBX() &&
                            x + width > t.getX() &&
                            y < t.getRBY() &&
                            y + height > t.getY()){
                        return t;
                    }
                }
            }
            return null;
    }
    protected boolean hit(float x, float y, int width, int height){
        return collideWithEntities(x, y, width, height) || collideWithMap(x, y, width, height);
    }
    
    protected boolean hitTarget(float x, float y, int width, int height) {
    	Target t = Game.curLevel.getTarget();
    	if(x < t.getRBX() &&
     		   x + width > t.getLTX() &&
     		   y < t.getRBY() &&
     		   y + height > t.getLTY()){
     			return true;
     	}
    	return false;
    }

    protected boolean hitDoor(float x, float y, int width, int height) {
    	Door d= Game.curLevel.getDoor();
    	if(x < d.getRBX() &&
      		   x + width > d.getLTX() &&
      		   y < d.getRBY() &&
      		   y + height > d.getLTY()){
      			return true;
      	}
     	return false;
    }
    
    public void accelX(float shift){
        xVel = xVel + shift;
    }
    public void accelY(float shift){
        yVel = yVel + shift;
    }
    public Vector2f getPos() {
        return pos;
    }

    public float getX() {
        return xPos;
    }

    public float getY() {
        return yPos;
    }

    public float getRBX(){return xPos+getWidth();}

    public float getRBY(){
        return yPos+getHeight();
    }
    
    public float getCenterX() {
    	return xPos + width/2;
    }
    
    public float getCenterY() {
    	return yPos - height/2;
    }
    
    public float getVelX() {
        return xVel;
    }

    public float getVelY() {
        return yVel;
    }

    public boolean isGrounded() {
        return grounded;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public float getWeight(){
        return weight;
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