package core;

import java.util.ArrayList;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import ambient.Background;
import ambient.levels.*;
import ambient.screen.LevelSelect;

import java.awt.Font;
import org.newdawn.slick.TrueTypeFont;

public class Game extends BasicGameState 
{	
	
	StateBasedGame sbg;
	private int id;
	
	public Integer mouseX;
	public Integer mouseY;
	
	ArrayList<Background> backgrounds;
	public static Level[] levels = new Level[]{new One(0), new Two(1), new Three(2), new Four(3), new Five(4), new Six(5), new Seven(6), new Eight(7), new Nine(8)};
	public static Level curLevel;
	
	public static TrueTypeFont textFont;
	public static TrueTypeFont headingFont;
	
	public static Input input;
	
	public static int frames;
	public static int delta;
	
	public static boolean win;
	public static boolean dead;
	public static boolean nextLevel;

	public static void loadFonts()  
	{	
		textFont = new TrueTypeFont(new Font("Calibri", Font.BOLD, 50), false);	
		headingFont= new TrueTypeFont(new Font("Calibri", Font.BOLD, 80), false);
	}

	public Game(int id) 
	{
		this.id = id;
	}
	
	public int getID() 
	{
		return id;		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException 
	{
		
		gc.setShowFPS(true);
		frames = 0;
		this.sbg = sbg;
		
		curLevel = levels[LevelSelect.level-1];
		curLevel.init();
		
//		for(Level l : levels)
//		{
//			l.setCompleted();
//		}
		win = false;
		dead = false;
		nextLevel = false;
		
		Background.init();
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{	
		input = gc.getInput();
		frames++;
		Game.delta = delta;
		if (frames % 2 == 0) {
			curLevel.update();
		}
		
		if (dead) {
			sbg.enterState(Main.END_ID);
		}
		else if (nextLevel) {
			sbg.enterState(Main.END_ID);
		} 
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException 
	{
		g.setAntiAlias(true);
		curLevel.render(g);
	}
	
	public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException 
	{
		nextLevel = false;
	}

	public void leave(GameContainer gc, StateBasedGame sbg) 
	{
		
	}

	public void keyPressed(int key, char c)
	{
		if (key == Input.KEY_R) 
	    {
			sbg.enterState(Main.TITLE_ID);
	    }
		if (key == Input.KEY_ESCAPE) 
	    {
			sbg.enterState(Main.PAUSE_ID);
	    }
//		if (key == Input.KEY_Z)
//		{
//			curLevel.getGrapple().clearGrapple();
//		}
		if (key == Input.KEY_X)
		{
			curLevel.getRepel().clearRepel();
		}
	}
	
	public void mousePressed(int button, int x, int y)
	{
		mouseX = Mouse.getX();
		mouseY = Mouse.getY();
		if (mouseY > Main.getScreenHeight()/2) {
	    	mouseY = Main.getScreenHeight()/2 - (mouseY - (Main.getScreenHeight()/2));
	    } 
		else if (mouseY < Main.getScreenHeight()/2) {
	    	mouseY = Main.getScreenHeight()/2 + (-mouseY + (Main.getScreenHeight()/2));
	    }
		if(button==0)
		{
			curLevel.getGrapple().attachGrapple(mouseX, mouseY);
			curLevel.addClick();
		}
		if(button==1)
		{
			curLevel.getGrapple().clearGrapple();
		}
		if (button==2) {
			curLevel.getRepel().attachRepel(mouseX, mouseY);
		}
	}
}
