package core;

import core.Main;
import environment.Map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Title extends BasicGameState {
    private final int id;
    StateBasedGame sbg;

    private Image titleText;
    private Image backgroundImage;

    public static Sound clickSound;
    
    public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
        arg0.setShowFPS(true);
        this.sbg = arg1;
        titleText = new Image("res/toad.png");
        titleText=titleText.getScaledCopy(Map.TILE_SIZE, Map.TILE_SIZE);
        backgroundImage = new Image("res/titleScreen.png");
        
        clickSound = new Sound("res/Audio/Frog.wav");
    }

    public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
        arg2.drawImage(backgroundImage, 0, 0);
        arg2.drawImage(titleText, (float) ((Main.getScreenWidth()) - (titleText.getWidth() *1.5)), (Main.getScreenHeight() / 2) - (titleText.getHeight() / 2));
        arg2.drawImage(titleText, (float) ((Main.getScreenWidth()) - (titleText.getWidth() *1.5)), (Main.getScreenHeight() / 2) - 4*(titleText.getHeight() / 2));
        arg2.drawImage(titleText, (float) ((Main.getScreenWidth()) - (titleText.getWidth() *1.5)), (float) ((Main.getScreenHeight() / 2) + 2.5*(titleText.getHeight() / 2)));
        arg2.drawImage(titleText, (float) ((Main.getScreenWidth()) - (titleText.getWidth() *3)), (float) ((Main.getScreenHeight() / 2) + 2.5*(titleText.getHeight() / 2)));
        arg2.drawImage(titleText, (float) ((Main.getScreenWidth()) - (titleText.getWidth() *5)), (float) ((Main.getScreenHeight() / 2) + 2.5*(titleText.getHeight() / 2)));
    }

    public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
        
    }

    public Title(int id) {
        this.id = id;
    }

    public int getID() {
        return id;
    }

    public void keyPressed(int key, char c) {
    	if(key==Input.KEY_SPACE) {
        sbg.enterState(Main.GAME_ID);
    	}
    	
    }

    public void mousePressed(int button, int x, int y) {
    	clickSound.play();
    }

}
