package core;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Death extends BasicGameState{
	private int id;
	private StateBasedGame sbg;

	public Death(int id) 
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
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{	
		// This is updates your game's logic every frame.  NO DRAWING.
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException 
	{
		// This code renders shapes and images every frame.
		g.setColor(Color.red);
		g.drawString("Death", 100, 100);
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
}
