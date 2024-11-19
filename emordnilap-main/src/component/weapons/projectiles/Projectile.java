package component.weapons.projectiles;

import org.newdawn.slick.Graphics;

import core.Game;
import entities.Entity;
import world.Tile;
import world.TileSet;

public abstract class Projectile {
	protected float xPos;
	protected float yPos;
	protected float tarX;
	protected float tarY;

	protected int frame;
	
	protected float projAngle;
	protected float projMult;

	protected float xSpeed;
	protected float ySpeed;

	protected int timer;
	protected int id;

	protected boolean ghost;
	
	public Projectile(float ix, float iy, float tX, float tY, float multiplier) {
		xPos = ix;
		yPos = iy;
		tarX = tX;
		tarY = tY;
		
		projMult = multiplier;
	}
	
	public abstract void render(Graphics g);
	
	public void update() {
		timer++;
		xPos += xSpeed;
		yPos += ySpeed;
	}
	
	public void getSpeeds()
	{
//		xSpeed = (float) (Math.cos(projAngle)*projMult);
//		ySpeed = (float) (Math.sin(projAngle)*projMult);
	}
	public float getX()
	{
		return xPos;
	}
	public float getY()
	{
		return yPos;
	}

	public float getAngle() {
		return projAngle;
	}

	public int getTimer(){
		return timer;
	}

	public int getID(){
		return id;
	}

	public float getTarX() {
		return tarX;
	}

	public float getTarY() {
		return tarY;
	}

	public boolean isGhost(){
		return ghost;
	}

	public int getFrame(){
		return frame;
	}
	 protected boolean collideWithMap(float x, float y, int width, int height) {
	    	for (Tile t : Game.curLevel.getTiles()) {
	    		if(x < t.getRBX() &&
	    		   x + width > t.getLTX() &&
	    		   y < t.getRBY() &&
	    		   y + height > t.getLTY()){
	    			return true;
	    		}
	    	}
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
	
}

