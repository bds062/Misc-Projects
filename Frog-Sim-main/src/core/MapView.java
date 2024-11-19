package core;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import environment.FastNoiseLite;
import environment.Map;
import environment.MiniTile;
import environment.biomes.Grass;
import environment.biomes.Sand;
import environment.biomes.Snow;
import environment.biomes.Water;


public class MapView extends BasicGameState{
	private int id;
	private StateBasedGame sbg;

	public static int TILE_SIZE = 5;
	private static MiniTile[][] tiles;
	
	public MapView(int id) 
	{
		this.id = id;
		
	}
	
	public int getID() 
	{
		return id;		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException 
	{
		// This code happens when you enter a game state for the *first time.*
		gc.setShowFPS(true);
		this.sbg=sbg;
		tiles = new MiniTile[getTilesHorizontal()][getTilesVertical()];
		generateWorld();
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{	
		// This is updates your game's logic every frame.  NO DRAWING.
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException 
	{
		// This code renders shapes and images every frame.
		for(int i = 0; i < getTilesHorizontal(); i++)
		{
			for(int j = 0; j < getTilesVertical(); j++)
			{
				tiles[i][j].render(g);
			}
		}
	}
	
	public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException 
	{
		// This code happens when you enter a gameState.  
	}

	public void leave(GameContainer gc, StateBasedGame sbg) 
	{
		// This code happens when you leave a gameState. 
	}

	public void keyPressed(int key, char c)
	{
		// This code happens every time the user presses a key
		if(key==Input.KEY_M) sbg.enterState(Main.GAME_ID);
	}
	
	public void mousePressed(int button, int x, int y)
	{
		// This code happens every time the user presses the mouse
	}
	public static int getTilesHorizontal()
	{
		return Main.getScreenWidth() / TILE_SIZE;
	}
	
	public static int getTilesVertical()
	{
		return Main.getScreenHeight() / TILE_SIZE;
	}

	public void generateWorld()
	{
		// Create the geography of the world
		for(int i = 0; i < getTilesHorizontal(); i++)
		{
			for(int j = 0; j < getTilesVertical(); j++)
			{
				tiles[i][j] = new MiniTile(i, j);
				generateTileAdvancedNoise(i,j);
			}
		}
	}
	public void generateTileAdvancedNoise(int x, int y) {
		float SCALE = .0025f;
		FastNoiseLite noise = Map.getNoise();
		FastNoiseLite noiseTwo = Map.getNoiseTwo();
		 noise.SetFractalType(FastNoiseLite.FractalType.PingPong);
	     noiseTwo.SetFractalType(FastNoiseLite.FractalType.PingPong);
		
	     float noiseValue = Map.getNoise().GetNoise(x * SCALE, y * SCALE);
	     float noiseValueTwo = Map.getNoiseTwo().GetNoise(x*SCALE, y*SCALE);
	        
	     if (noiseValue < .2) {
	        	if (noiseValueTwo<.35  ) {
	        		tiles[x][y].setTerrain("G");
	        	} else {
	        		tiles[x][y].setTerrain("Sand");
	        	}
	        } else {
	        	if(noiseValue<.3) {
	        		tiles[x][y].setTerrain("W");
	        	} else {
	        		tiles[x][y].setTerrain("Snow");
	        	}
	        }
	}
}
