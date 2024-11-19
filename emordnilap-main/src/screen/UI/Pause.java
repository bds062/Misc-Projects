package screen.UI;

import core.Main;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Pause extends BasicGameState
{
	private int id;
	StateBasedGame sbg;
	private Image background;
	private Image info;

	private Button restart, skip;
	private SpriteSheet skipSheet;
	private int timer = 0, tile = 0;

	private final int skipX = 1850, skipY = 1000;

	public Pause(int id)
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
		Image restartImage = new Image("res/restart.png");
		Image skipImage = new Image("res/skipstatic.png");
		restart = new Button(restartImage, 0, 1115, 680, restartImage.getWidth(), restartImage.getHeight());
		skip  = new Button(skipImage, 0, skipX, skipY, skipImage.getWidth(), skipImage.getHeight());
		skipSheet = new SpriteSheet(new Image("res/skip.png"),  56, 57);

		background = new Image("res/sky.png");
		info = new Image("res/paused.png");
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{
		restart.update(gc);
		skip.update(gc);
		if (restart.clicked) {
			Main.game.init(gc, sbg);
			sbg.enterState(Main.GAME_ID);
		}
		else if (skip.clicked) {
			timer++;
			if (timer%20==0) {
				tile++;
			}
			if (tile>4) {
				//LevelSelect.level++;
				Main.game.init(gc, sbg);
				sbg.enterState(Main.GAME_ID);
			}
		}
		else {
			timer = 0;
			tile = 0;
		}
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
	{
		g.drawImage(background, 0, 0);
		g.drawImage(info, Main.getScreenWidth()/2-info.getWidth()/2, Main.getScreenHeight()/4);
		restart.render(g);
		if (!skip.clicked) {
			skip.render(g);
		}
		else {
			skipSheet.getSprite(tile, 0).draw(skipX, skipY);
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
		if (key == Input.KEY_R)
		{
			sbg.enterState(Main.TITLE_ID);
		}
		if (key == Input.KEY_ESCAPE || key == Input.KEY_SPACE)
		{
			sbg.enterState(Main.GAME_ID);
		}
	}

	public void mousePressed(int button, int x, int y)
	{
		// This code happens every time the user presses the mouse
	}
}
