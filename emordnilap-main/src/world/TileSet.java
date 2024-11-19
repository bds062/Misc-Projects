package world;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;

public class TileSet extends WorldObject{
	public ArrayList<NewTile> tiles;
	public int rows;
	public int cols;
	
	public TileSet(float x, float y, int width, int height) {
		super(x, y, width, height);
		tiles = new ArrayList<NewTile>();
		rows=(int)(height/y);
		cols=(int)(width/x);
		
		for (int i=0; i<=(width/32); i++) {
			for (int j=0; j<=(height/32);j++) {
				if (width-i*32 < 32) {
					if (height-j*32 < 32) {
						tiles.add(new NewTile(LTX+i*32, LTY+j*32, i, j, width/32, height/32));
					}
					tiles.add(new NewTile(LTX+i*32, LTY+j*32, i, j, width/32, height/32));
				}
				else {
					tiles.add(new NewTile(LTX+i*32, LTY+j*32, i, j, width/32, height/32));
				}
			}
		}
		for (int i=0; i<tiles.size(); i++) {
			
		}
		
	}
	public void render(Graphics g) {
		for (NewTile t : tiles) {
			t.render(g);
		}
//		for (int i=0; i<tiles.size(); i++) {
//			g.drawString(String.valueOf(i), (int)(tiles.get(i).getLTX()), (int)(tiles.get(i).getLTY()));
//		}
	}
}