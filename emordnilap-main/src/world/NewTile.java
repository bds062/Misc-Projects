package world;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.opengl.Texture;

public class NewTile extends WorldObject {
	private static Image topLeftCorner;
	private static Image topLeftEdge;
	private static Image topRightEdge;
	private static Image topRightCorner;
	private static Image leftTopEdge;
	private static Image leftBottomEdge;
	private static Image leftBottomCorner;
	private static Image rightTopEdge;
	private static Image rightBottomEdge;
	private static Image rightBottomCorner;
	private static Image bottomLeftEdge;
	private static Image bottomRightEdge;
	private static Image empty;
	private int rand;
	private Image image;

	public NewTile(float x, float y, int id1, int id2, int id1Last, int id2Last) {
		super(x, y, 32, 32);
		rand = (int) (Math.random() * 2);
		// INSIDE
		image = empty;
		// EDGES
		if (id1 == 0) {
			if (rand==0) image = leftTopEdge; else image=leftBottomEdge;
		} else if (id2 == 0) {
			if (rand==0) image = topRightEdge; else image=topLeftEdge;
		} else if (id1==id1Last) {
			if (rand==0) image = rightTopEdge; else image=rightBottomEdge;
		} else if (id2==id2Last) {
			if (rand==0) image = bottomRightEdge; else image=bottomLeftEdge;
		}
		// CORNERS
		if (id1 == 0 && id2 == 0) {
			image = topLeftCorner;
		} else if (id1 == 0 && id2 == id2Last) {
			image = leftBottomCorner;
		} else if (id1 == id1Last && id2 == 0) {
			image = topRightCorner;
		} else if (id1 == id1Last && id2 == id2Last) {
			image = rightBottomCorner;
		}
	}

	public void render(Graphics g) {
		image.draw(LTX, LTY);

	}

	public static void init() {
		try {
			topLeftCorner = loadImage("res/Tileset/Individual_PNGs/Level_Tileset/tile000.png");
			topLeftEdge = loadImage("res/Tileset/Individual_PNGs/Level_Tileset/tile001.png");
			topRightEdge = loadImage("res/Tileset/Individual_PNGs/Level_Tileset/tile002.png");
			topRightCorner = loadImage("res/Tileset/Individual_PNGs/Level_Tileset/tile003.png");
			leftTopEdge = loadImage("res/Tileset/Individual_PNGs/Level_Tileset/tile010.png");
			leftBottomEdge = loadImage("res/Tileset/Individual_PNGs/Level_Tileset/tile020.png");
			leftBottomCorner = loadImage("res/Tileset/Individual_PNGs/Level_Tileset/tile030.png");
			rightTopEdge = loadImage("res/Tileset/Individual_PNGs/Level_Tileset/tile013.png");
			rightBottomEdge = loadImage("res/Tileset/Individual_PNGs/Level_Tileset/tile023.png");
			rightBottomCorner = loadImage("res/Tileset/Individual_PNGs/Level_Tileset/tile033.png");
			bottomLeftEdge = loadImage("res/Tileset/Individual_PNGs/Level_Tileset/tile031.png");
			bottomRightEdge = loadImage("res/Tileset/Individual_PNGs/Level_Tileset/tile032.png");
			empty = loadImage("res/Tileset/Individual_PNGs/Level_Tileset/tile038.png");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("HIIII");
			System.out.println(e);
		}
	}

	public void setEmpty() {
		image = empty;
	}

	public void setTLE() {
		image = topLeftEdge;

	}

	public void setTLC() {
		image = topLeftCorner;
	}

	public void setTRE() {
		image = topRightEdge;
	}

	public void setTRC() {
		image = topRightCorner;
	}

	public void setRTE() {
		image = rightTopEdge;
	}

	public void setRBE() {
		image = rightBottomEdge;
	}

	public void setLTE() {
		image = leftTopEdge;
	}

	public void setLBE() {
		image = leftBottomEdge;
	}

	public void setBLE() {
		image = bottomLeftEdge;
	}

	public void setBRE() {
		image = bottomRightEdge;
	}

	public void setRBC() {
		image = rightBottomCorner;
	}

	public void setLBC() {
		image = leftBottomCorner;
	}

	
	
}