package environment;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import core.MapView;
import environment.biomes.Biome;
import environment.biomes.Grass;

public class MiniTile {
	protected String terrain;
	protected int x;
	protected int y;
	
	/****************************** CORE METHODS ******************************/
	
	public MiniTile(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	
	public void render(Graphics g) 
	{
		if(terrain.equals("G")) {
			g.setColor(new Color(0, 255,0));
		} else if (terrain.equals("W")) {
			g.setColor(new Color(0,0,255));
		} else if (terrain.equals("Sand")) {
			g.setColor(new Color(255,255, 0));
		} else if (terrain.equals("Snow")) {
			g.setColor(new Color(255,255,255));
		} else {
			g.setColor(Color.black);
		}
		g.fillRect(x * MapView.TILE_SIZE, y * MapView.TILE_SIZE, MapView.TILE_SIZE, MapView.TILE_SIZE);
		g.setColor(Color.black);
		g.drawString("x:"+x, x, y);
	}
	
	
	
	/****************************** ACCESSORS ******************************/
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	

	
	
	
	
	/****************************** MUTATORS ******************************/
	
	
	public void setTerrain(String t)
	{
		terrain = t;
	}
	
		
}
