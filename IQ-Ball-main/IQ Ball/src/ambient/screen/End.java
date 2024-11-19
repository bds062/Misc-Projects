package ambient.screen;

import java.awt.Font;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import ambient.levels.Level;
import ambient.ui.Button;
import core.Game;
import core.Main;

public class End extends BasicGameState 
{	
	private int id;
	StateBasedGame sbg;
	private int xPos2;
	private int yPos2;
	
	private Image score0, score1, score2, score3;
	private Image background;
	
	private Font font;
	private TrueTypeFont ttf;
	
	private Button respawn, nextLevel;
	
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
		respawn = new Button(new Image("res/respawn.png"), 0, Main.getScreenWidth()/2-new Image("res/respawn.png").getWidth()/2, 3*Main.getScreenHeight()/4, new Image("res/respawn.png").getWidth(), new Image("res/respawn.png").getHeight());
		nextLevel = new Button(new Image("res/nextlevel.png"), 0, Main.getScreenWidth()/2-new Image("res/nextlevel.png").getWidth()/2, (int)(3.2*Main.getScreenHeight()/4), new Image("res/nextlevel.png").getWidth(), new Image("res/nextlevel.png").getHeight());
		score0 = new Image("res/scoreboard/0.png");
		score1 = new Image("res/scoreboard/1.png");
		score2 = new Image("res/scoreboard/2.png");
		score3 = new Image("res/scoreboard/3.png");
		background = new Image("res/sky.png");
		
		font = new Font("Monospaced", Font.BOLD, 36);
		ttf = new TrueTypeFont(font, true);
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{
		respawn.update(gc);
		nextLevel.update(gc);
		// This is updates your game's logic every frame.  NO DRAWING.
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException 
	{
		g.drawImage(background, 0, 0);
		if (Game.dead) {
			respawn.render(g);
		}
		else if (Game.nextLevel) {
				Level curLevel = Game.levels[LevelSelect.level-1];
				
				int newScore;
				
				if (curLevel.getTime() < 10) {
					newScore = 3;
					g.drawImage(score3, Main.getScreenWidth()/2-score3.getWidth()/2, Main.getScreenHeight()/4);
				}
				else if (curLevel.getTime() < 20) {
					newScore = 2;
					g.drawImage(score2, Main.getScreenWidth()/2-score3.getWidth()/2, Main.getScreenHeight()/4);
				}
				else if (curLevel.getTime() < 40) {
					newScore = 1;
					g.drawImage(score1, Main.getScreenWidth()/2-score3.getWidth()/2, Main.getScreenHeight()/4);
				}
				else {
					newScore = 0;
					g.drawImage(score0, Main.getScreenWidth()/2-score3.getWidth()/2, Main.getScreenHeight()/4);
				}
				//g.drawString(String.valueOf(curLevel.score), 720, 800);
				
				if (newScore > curLevel.score) {
					curLevel.score = newScore;
				}
				
				curLevel.setCompleted();
				if (!(LevelSelect.level == 9)) {
					Game.levels[LevelSelect.level].setCompleted();
				}
				
				ttf.drawString(780, 630, "Time: " + curLevel.getTime() + " seconds", Color.black);
				ttf.drawString(780, 750, "Clicks: " + curLevel.getClicks(), Color.black);
				
				nextLevel.render(g);
			}
			
			if (respawn.clicked) {
				Main.game.init(gc, sbg);
				sbg.enterState(Main.GAME_ID);
			}
			if (nextLevel.clicked) {
				if (LevelSelect.level == 9) {
					sbg.enterState(Main.TITLE_ID);
				}
				else {
					LevelSelect.level = LevelSelect.level + 1;
					Main.game.init(gc, sbg);
					sbg.enterState(Main.GAME_ID);
				}
			}
	}
	
	public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException 
	{
		init(gc, sbg);
	}

	public void leave(GameContainer gc, StateBasedGame sbg) 
	{
	}

	public void keyPressed(int key, char c)
	{
		
	}
}
