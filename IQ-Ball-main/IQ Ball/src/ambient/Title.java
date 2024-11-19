package ambient;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import core.Main;

public class Title extends BasicGameState 
{	
	private int id;
	StateBasedGame sbg;
	private int xPos2;
	private int yPos2;
	
	public static int level;

	public Title(int id) 
	{
		this.id = id;
		level = 1;
	}
	
	public int getID() 
	{
		return id;		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException 
	{
		// This code happens when you enter a game state for the *first time.*
		gc.setShowFPS(true);
		this.sbg = sbg;
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{	
		// This is updates your game's logic every frame.  NO DRAWING.
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException 
	{
		g.setColor(new Color(255,255,255));

		g.setColor(new Color(0,255,0));
		g.drawRect(Main.getScreenWidth()/2-30, 3*Main.getScreenHeight()/4, 60, 30);
		g.drawString("PLAY",Main.getScreenWidth()/2-20,(3*Main.getScreenHeight()/4)+5);
		
		g.setColor(Color.cyan);
		g.drawRect(400, Main.getScreenHeight()/4, 120, 30);
		g.drawString("Level One", 410, Main.getScreenHeight()/4+10);
		
		g.setColor(Color.green);
		g.drawRect(800, Main.getScreenHeight()/4, 120, 30);
		g.drawString("Level Two", 810, Main.getScreenHeight()/4+10);
		
		g.setColor(Color.pink);
		g.drawString("Current level :" + level, 0, 50);
		// This code renders shapes and images every frame.
	}
	
	public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException 
	{
		// This code happens when you enter a gameState.  
	}

	public void leave(GameContainer gc, StateBasedGame sbg) throws SlickException 
	{
		// This code happens when you leave a gameState.
		Main.game.init(gc, sbg);
	}

	public void keyPressed(int key, char c)
	{
		if (key == Input.KEY_R) 
	    {
			sbg.enterState(Main.TRUETITLE_ID);
	    }
//		switch (key) {
//			case Input.KEY_1: {
//				level = 1;
//				break;
//			}
//			case Input.KEY_2: {
//				level = 2;
//				break;
//			}
//			default: break;
//		}
	}
	
	public void mousePressed(int button, int x, int y)
	{
		xPos2 = Mouse.getX();
		yPos2= Mouse.getY();
		if (yPos2>Main.getScreenHeight()/2) {
	    	yPos2=Main.getScreenHeight()/2-(yPos2-(Main.getScreenHeight()/2));
	    } 
		else if (yPos2<Main.getScreenHeight()/2) {
	    	yPos2=Main.getScreenHeight()/2+(-yPos2+(Main.getScreenHeight()/2));
	    }
		
		
		if (xPos2>Main.getScreenWidth()/2-30 && xPos2<Main.getScreenWidth()/2+30 && 
				yPos2>3*Main.getScreenHeight()/4 && yPos2<3*Main.getScreenHeight()/4+30) {
			sbg.enterState(Main.GAME_ID);
		}
		if (xPos2 > 400 && xPos2 < 520 &&
				yPos2 > Main.getScreenHeight()/4+10 && yPos2 < Main.getScreenHeight()/4+40) {
			level = 1;
		}
		if (xPos2 > 800 && xPos2 < 920 &&
				yPos2 > Main.getScreenHeight()/4+10 && yPos2 < Main.getScreenHeight()/4+40) {
			level = 2;
		}
		
		
		// This code happens every time the user presses the mouse
	}
	
	


}
