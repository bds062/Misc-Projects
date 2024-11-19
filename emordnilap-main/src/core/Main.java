package core;

import org.lwjgl.LWJGLUtil;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import screen.UI.*;

import java.io.File;

public class Main extends StateBasedGame 
{
	public final static int FRAMES_PER_SECOND = 60;
	private static AppGameContainer appgc;
	
	public static final int TITLE_ID = 0;
    public static final int GAME_ID  = 1;
    public static final int PAUSE_ID  = 2;
    public static final int SETTINGS_ID  = 3;
    public static final int MENU_ID = 4;
    
    public static BasicGameState title;
    public static BasicGameState game;
    public static BasicGameState pause;  
    public static BasicGameState settings; 
    public static BasicGameState menu; 

    
	public Main(String name) 
	{
		super(name);
		title = new Title(TITLE_ID);
		game = new Game(GAME_ID);
		pause = new Pause(PAUSE_ID);
		settings = new Settings(SETTINGS_ID);
		menu = new Menu(MENU_ID);
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
		addState(title);
		addState(game);
		addState(pause);
		addState(settings);
		addState(menu);
	}


	public static void main(String[] args)
	{
		try
		{
			File JGLLib = null;
			switch(LWJGLUtil.getPlatform())
			{
				case LWJGLUtil.PLATFORM_WINDOWS:
				{
					JGLLib = new File("libs/natives/");
				}
				break;

				case LWJGLUtil.PLATFORM_LINUX:
				{
					JGLLib = new File("libs/natives/");
				}
				break;

				case LWJGLUtil.PLATFORM_MACOSX:
				{
					JGLLib = new File("libs/natives/");
				}
				break;
			}

			System.setProperty("org.lwjgl.librarypath", JGLLib.getAbsolutePath());

			appgc = new AppGameContainer(new Main("Emordnilap"));
			System.setProperty("org.lwjgl.opengl.Window.undecorated", "true");
			appgc.setDisplayMode(appgc.getScreenWidth(), appgc.getScreenHeight(), true);
			appgc.setTargetFrameRate(FRAMES_PER_SECOND);
			appgc.setVSync(true);
			appgc.start();
		}
		catch (SlickException e)
		{
			e.printStackTrace();
		}

	}
}