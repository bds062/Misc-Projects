package core;

import core.Game;
import core.Main;
import media.Button;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class End extends BasicGameState {
    private final int id;
    StateBasedGame sbg;
    private Image background;
    private Image info;

    private static Sound clickSound;
    
    private Button restart;
    private Button continueButton;
    
    public End(int id) {
        this.id = id;
    }

    public int getID() {
        return id;
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        this.sbg = sbg;
        Image restartImage = new Image("res/Restart.png");
        restart = new Button(restartImage, 0, Main.getScreenWidth() / 2 - restartImage.getWidth() / 2, 680, restartImage.getWidth(), restartImage.getHeight());
        Image continueImage = new Image("res/Continue.png");
        continueButton = new Button (continueImage, 0, Main.getScreenWidth()/2 - continueImage.getWidth()/2, 500, continueImage.getWidth(), continueImage.getHeight());
        clickSound = new Sound("res/Audio/Click.wav");
        info = new Image("res/Win.png");
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        restart.update(gc);
        continueButton.update(gc);
        if (restart.clicked) {
        	Game.restart();
        	sbg.enterState(Main.TITLE_ID);
        }
        if(continueButton.clicked) {
        	sbg.enterState(Main.GAME_ID);
        }
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
    	g.setColor(new Color(197, 219, 72));
        g.fillRect(0, 0, Main.getScreenWidth(), Main.getScreenHeight());
        g.drawImage(info, Main.getScreenWidth() / 2 - info.getWidth() / 2, Main.getScreenHeight() / 4);
        restart.render(g);
        continueButton.render(g);
    }

    public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {

    }

    public void leave(GameContainer gc, StateBasedGame sbg) throws SlickException {

    }

    public void keyPressed(int key, char c) {
        if (key == Input.KEY_R) {
            sbg.enterState(Main.TITLE_ID);
        }
        if (key == Input.KEY_ESCAPE || key == Input.KEY_SPACE) {
            sbg.enterState(Main.TITLE_ID);
        }
    }

    public void mousePressed(int button, int x, int y) {
    	clickSound.play();
    }
}
