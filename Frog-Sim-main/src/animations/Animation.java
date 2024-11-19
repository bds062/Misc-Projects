package animations;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SpriteSheet;

import core.Game;

public class Animation {
	protected float xPos;
	protected float yPos;
	protected SpriteSheet sprite;
	protected int slides;
	protected int timer;
	protected int slideTimer;
	protected int curSlide;
	protected boolean alive;
	
	public Animation(float xPos, float yPos, SpriteSheet sprite, int slideTimer) {
		this.xPos=xPos;
		this.yPos=yPos;
		this.sprite=sprite;
		slides=sprite.getHorizontalCount();
		timer=0;
		curSlide=0;
		this.slideTimer=slideTimer;
		alive=true;
	}
	
	public Animation(float xPos, float yPos, int slideTimer) {
		this.xPos=xPos;
		this.yPos=yPos;
		timer=0;
		curSlide=0;
		this.slideTimer=slideTimer;
		alive=true;
	}
	
	public void update() {
		timer++;
		if(timer%slideTimer==0) curSlide++;
		if(curSlide>=slides) alive=false;
	}
	
	public void render(Graphics g) {
		if(curSlide<slides) {
		g.drawImage(sprite.getSprite(curSlide, 0), xPos, yPos);
		}
	}
	
	public void setSprite(SpriteSheet sprite) {
		this.sprite=sprite;
		slides=sprite.getHorizontalCount();
	}
	public boolean getAlive() { return alive; }
}
