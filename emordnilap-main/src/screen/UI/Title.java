package screen.UI;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import core.Main;

public class Title extends BasicGameState {
	private int id;
	StateBasedGame sbg;

	private Image titleText;
	private Image backgroundImage;

	private TitleFloaters[] floaters;
	
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		arg0.setShowFPS(true);
		this.sbg = arg1;
		TitleFloaters.init();
		titleText = new Image("res/titleTextImage.png");
		backgroundImage= new Image ("res/backgroundTitleImage.png");
		floaters=new TitleFloaters[16];
		for (int i=0; i<floaters.length;i++) {
			floaters[i]=new TitleFloaters();
		}
	}

	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		arg2.drawImage(backgroundImage, 0, 0);
		for (int i=0; i<floaters.length; i++) {
			floaters[i].render(arg2);
		}
		arg2.drawImage(titleText, (Main.getScreenWidth()/2)-(titleText.getWidth()/2), (Main.getScreenHeight()/2)-(titleText.getHeight()/2));
	}

	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		for (int i=0; i<floaters.length; i++) {
			floaters[i].update();
		}
	}
	
	public Title(int id) 
	{
		this.id = id;
	}

	public int getID() {
		return id;
	}
	
	public void keyPressed(int key, char c)
	{
		sbg.enterState(Main.GAME_ID);
	}
	public void mousePressed(int button, int x, int y)
	{
		sbg.enterState(Main.GAME_ID);
	}

}
