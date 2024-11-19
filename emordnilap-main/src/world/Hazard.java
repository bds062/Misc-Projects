package world;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import core.Main;

public class Hazard extends WorldObject{
	private static Image poisonImage;
	private static SpriteSheet poisonSheet;
	private static Image spikeLeftImage;
	private static Image spikeRightImage;
	private static Image spikeUpImage;
	private static int timer=0;
	private static int tile=0;
	private boolean fill;
	private String dir;

	public Hazard(float x, float y, int width, int height, boolean fill, String dir) {
		super(x, y, width, height);
		this.fill=fill;
		this.dir=dir;
	}

	public void render(Graphics g) {
		g.drawOval(LTX, LTY, width, height);
		timer++;
		if (timer%10==0) {
			tile++;
		}
		if (tile>poisonSheet.getHorizontalCount()) {
			tile=0;
		}
		switch(dir) {
		case "floor":
			for (int x=0; x<=(width/32); x++) {
				poisonSheet.getSprite(tile, 1).draw((LTX+(x*32)), LTY);
			}
			if (fill==true) {
				g.setColor(new Color(0,143,255));
				g.fillRect(LTX, LTY+32, width, Main.getScreenHeight()-LTY+32);
			}
			break;
		case "roof":
			for (int x=0; x<=(width/32); x++) {
				g.drawImage(spikeUpImage, LTX+(x*32), LTY);
			}
			if (fill==true) {
				g.setColor(new Color(0,143,255));
				g.fillRect(LTX, 0, width, LTY);
			}
			break;
		case "left":
			for (int y=0; y<=(height/32); y++) {
				g.drawImage(spikeLeftImage, LTX, LTY+(y*32));
			}
			if (fill==true) {
				g.setColor(new Color(0,143,255));
				g.fillRect(0, LTY, LTX, height);
			}
			break;
		case "right":
			for (int y=0; y<=(height/32); y++) {
				g.drawImage(spikeRightImage, LTX, LTY+(y*32));
			}
			if (fill==true) {
				g.setColor(new Color(0,143,255));
				g.fillRect(LTX+32, LTY, Main.getScreenHeight()-LTX+32, height);
			}
		}
	}
	public static void init(){
//		try {    		
//			
//
//			poisonImage = new Image ("res/Poison.png");
//			poisonSheet =  new SpriteSheet(poisonImage,  32, 32);
//
//		} catch (SlickException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
}
