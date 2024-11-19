package ambient;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import core.Game;
import core.Main;

public class End extends BasicGameState 
{	
	private int id;
	StateBasedGame sbg;
	private int timer;
	private boolean click;
	private int xPos2;
	private int yPos2;
	
	public End(int id) 
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
		this.sbg = sbg;
		timer = 0;
		click= false;
		
		
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{	
		timer++;
		
		// This is updates your game's logic every frame.  NO DRAWING.
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException 
	{
		g.setColor(new Color(255, 255, 255));
	
			if (Game.dead) {
				g.setColor(Color.red);
				g.drawString("You died :C", 620, 400);
			}
			else if (Game.win) {
				g.setColor(Color.green);
				g.drawString("You have won!", 620, 400);
			}
			else if (Game.nextLevel) {
				g.setColor(Color.cyan);
				g.drawString("Going to the next level...", 720, 400);
			}
		
		if (click==true){
			if (Game.nextLevel) {
				Main.game.init(gc, sbg);
				sbg.enterState(Main.GAME_ID);
			}
			else {
				sbg.enterState(Main.TITLE_ID);
			}
		}
		if (Game.dead) { 
			g.setColor(Color.white);
			g.drawRect(Main.getScreenWidth()/2-40, 3*Main.getScreenHeight()/4, 80, 30);
			g.drawString("RESPAWN",Main.getScreenWidth()/2-33,(3*Main.getScreenHeight()/4)+5);
		} else if (Game.nextLevel) {
			g.setColor(Color.white);
			g.drawRect(Main.getScreenWidth()/2-60, 3*Main.getScreenHeight()/4, 120, 30);
			g.drawString("NEXT LEVEL",Main.getScreenWidth()/2-45,(3*Main.getScreenHeight()/4)+5);
		}
	}
	
	public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException 
	{
		// This code happens when you enter a gameState.  
		init(gc, sbg);
	}

	public void leave(GameContainer gc, StateBasedGame sbg) 
	{
		// This code happens when you leave a gameState. 
	}

	public void keyPressed(int key, char c)
	{
		
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
		if (xPos2 > Main.getScreenWidth()/2-40 && xPos2 < Main.getScreenWidth()/2+40 &&
				yPos2 > 3*Main.getScreenHeight()/4 && yPos2 < 3*Main.getScreenHeight()/4+30) {
			click=true;
		}
		// This code happens every time the user presses the mouse
	}
	
	


}
