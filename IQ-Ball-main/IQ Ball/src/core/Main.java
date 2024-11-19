package core;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import ambient.TrueTitle;
import ambient.screen.End;
import ambient.screen.Pause;
import ambient.screen.LevelSelect;

public class Main extends StateBasedGame 
{
	public final static int FRAMES_PER_SECOND = 120;
	private static AppGameContainer appgc;
	
	public static final int TRUETITLE_ID=0;
	public static final int TITLE_ID = 1;
    public static final int GAME_ID  = 2;
    public static final int PAUSE_ID  = 3;
    public static final int END_ID  = 4;
    
    public static BasicGameState truetitle;
    public static BasicGameState title;
    public static BasicGameState game;  
    public static BasicGameState pause; 
    public static BasicGameState end; 

    
	public Main(String name) 
	{
		super(name);
		truetitle = new TrueTitle(TRUETITLE_ID);
		title = new LevelSelect(TITLE_ID);
		game = new Game(GAME_ID);
		pause = new Pause(PAUSE_ID);
		end = new End(END_ID);
	}

	public static int getScreenWidth()
	{
		return appgc.getScreenWidth();
	}
	
	public static int getScreenHeight()
	{
		return appgc.getScreenHeight();
	}
	

	public void initStatesList(GameContainer gc) throws SlickException 
	{
		addState(truetitle);
		addState(title);
		addState(game);
		addState(pause);
		addState(end);

	}
	

	public static void main(String[] args) 
	{
		try 
		{
			appgc = new AppGameContainer(new Main("IQ Ball"));
			System.setProperty("org.lwjgl.opengl.Window.undecorated", "true");
			appgc.setDisplayMode(appgc.getScreenWidth(), appgc.getScreenHeight(), true);
			appgc.setTargetFrameRate(FRAMES_PER_SECOND);
			appgc.start();
			appgc.setVSync(true);
		} 
		catch (SlickException e) 
		{
			e.printStackTrace();
		}

	}
}