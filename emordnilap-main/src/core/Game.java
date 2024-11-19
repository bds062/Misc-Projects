package core;

import data.InversionManager;
import entities.Entity;
import entities.enemy.Turret;
import entities.player.Player;
import levels.Level;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.awt.Font;
import java.util.ArrayList;

public class Game extends BasicGameState 
{	
	
	StateBasedGame sbg;
	private int id;
	
	public Integer mouseX;
	public Integer mouseY;
	
	public static TrueTypeFont textFont;
	public static TrueTypeFont headingFont;
	
	public static Input input;
	
	public static int frames;
	public static int delta;
	
	public static Level curLevel = new Level();

	public static boolean inversion;
	public static boolean nextLevel;
	
	public static int inversionCount;

	public static void loadFonts()  
	{	
		textFont = new TrueTypeFont(new Font("Calibri", Font.BOLD, 50), false);	
		headingFont= new TrueTypeFont(new Font("Calibri", Font.BOLD, 80), false);
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException 
	{
		gc.setShowFPS(true);
		frames = 0;
		this.sbg = sbg;
		loadFonts();
		curLevel.init();
		InversionManager.init();
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{	
		input = gc.getInput();
		frames++;
		Game.delta = delta;
		curLevel.update();
		lastPressed++;
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException 
	{
		g.setAntiAlias(true);
		curLevel.render(g);
		InversionManager.update(g);
	}
	
	public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException 
	{
	}

	public void leave(GameContainer gc, StateBasedGame sbg) 
	{
		
	}

	public void keyPressed(int key, char c)
	{
		if (key == Input.KEY_R) 
	    {
			sbg.enterState(Main.MENU_ID);
	    }
		if (key == Input.KEY_ESCAPE) 
	    {
			sbg.enterState(Main.PAUSE_ID);
	    }
		if (key == Input.KEY_1) 
	    {
			curLevel.equipSword();
	    }
		if (key == Input.KEY_2) 
	    {
			curLevel.equipPistol();
	    }
		if (key == Input.KEY_3) 
	    {
			curLevel.equipRockets();
	    }
		if (key == Input.KEY_4) 
	    {
			curLevel.equipGrenades();
	    }
		if (key == Input.KEY_5) {
			curLevel.equipNull();
		}
	}

	int lastPressed;
	
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
			if (lastPressed > 0){
				curLevel.getCurWeapon().shoot();
				lastPressed = 0;
			}
		}
		else if(button==1)
		{
			curLevel.addTurret(new Turret(mouseX, mouseY));
		}
		else {}
	}
	
	public Game(int id) 
	{
		this.id = id;
	}
	
	public int getID() 
	{
		return id;		
	}
	
	public static Player getPlayer() {
		return curLevel.getPlayer();
	}

	public static ArrayList<Entity> getAll(){
		ArrayList<Entity> all = new ArrayList<>(curLevel.getLiveGernades());
		if (curLevel.getPlayer().getGhost() != null) {
			
		}
		all.add(curLevel.getPlayer());
		all.addAll(curLevel.getTurrets());
		all.addAll(curLevel.getFrogs());
		all.addAll(curLevel.getDrones());
		//all.add(curLevel.getPlayer().getGhost());
		return all;
	}
}
