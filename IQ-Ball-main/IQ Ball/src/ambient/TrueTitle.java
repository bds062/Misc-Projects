package ambient;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import ambient.levels.TrueTitlePlayers;
import core.Main;

public class TrueTitle extends BasicGameState 
{	
	private int id;
	StateBasedGame sbg;
	private int xPos2;
	private int yPos2;
	private Image titleText;
	
	private TrueTitlePlayers[] players;
	
//	private ArrayList<Integer> players;
	
	private static Image titleImage;
	
	public static int level;

	public TrueTitle(int id) 
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
		TrueTitlePlayers.init();
		titleText = new Image("res/titleText3.png");
		players=new TrueTitlePlayers[128];
		for (int i=0; i<players.length;i++) {
			players[i]=new TrueTitlePlayers();
		}
//		titleImage = new Image("res/coolmath.png");
//		titleImage = titleImage.getScaledCopy(Main.getScreenWidth(), Main.getScreenHeight());
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{	
		// This is updates your game's logic every frame.  NO DRAWING.
		for (int i=0; i<players.length; i++) {
			players[i].update();
		}
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException 
	{
		for (int i=0; i<players.length; i++) {
			players[i].render(g);
		}
		g.drawImage(titleText, (Main.getScreenWidth()/2)-(titleText.getWidth()/2), (Main.getScreenHeight()/2)-(titleText.getHeight()/2));
//		g.setColor(new Color(0,255,0));
//		g.drawRect(Main.getScreenWidth()/2-30, 3*Main.getScreenHeight()/4, 60, 30);
//		g.drawString("PLAY",Main.getScreenWidth()/2-20,(3*Main.getScreenHeight()/4)+5);
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
		sbg.enterState(Main.TITLE_ID);
		
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
		
		
		if (xPos2>0 && xPos2<Main.getScreenWidth() && 
				yPos2>0 && yPos2<3*Main.getScreenHeight()) {
			sbg.enterState(Main.TITLE_ID);
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
