package core;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Main extends StateBasedGame {
    public final static int FRAMES_PER_SECOND = 60;
    public static final int TITLE_ID = 1;
    public static final int GAME_ID = 2;
    public static final int MAP_ID = 3;
    public static final int PAUSE_ID = 4;
    public static final int DEATH_ID = 5;
    public static final int END_ID = 6;
    private static AppGameContainer appgc;
    private final BasicGameState title;
    private final BasicGameState map;
    private final BasicGameState pause;
    public BasicGameState game;
    private final BasicGameState death;
    private final BasicGameState end;
    

    public Main(String name) {
        super(name);
        title = new Title(TITLE_ID);
        game = new Game(GAME_ID);
        map = new MapView(MAP_ID);
        pause = new Pause(PAUSE_ID);
        death = new DeathScreen(DEATH_ID);
        end = new End(END_ID);
    }

    public static int getScreenWidth() {
        return appgc.getScreenWidth();
    }

    public static int getScreenHeight() {
        return appgc.getScreenHeight();
    }

    public static void main(String[] args) {
        try {
            appgc = new AppGameContainer(new Main("My First Project"));
            System.setProperty("org.lwjgl.opengl.Window.undecorated", "true");

            appgc.setDisplayMode(appgc.getScreenWidth(), appgc.getScreenHeight(), false);
            appgc.setTargetFrameRate(FRAMES_PER_SECOND);
            appgc.setVSync(true);
            appgc.start();
            

        } catch (SlickException e) {
            e.printStackTrace();
        }

    }

    public void initStatesList(GameContainer gc) throws SlickException {
        addState(title);
        addState(game);
        addState(map);
        addState(pause);
        addState(end);
        addState(death);
    }
}