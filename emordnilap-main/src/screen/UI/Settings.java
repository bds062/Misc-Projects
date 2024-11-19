package screen.UI;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Settings extends BasicGameState {
	private int id;
	StateBasedGame sbg;
	
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		this.sbg = arg1;
	}

	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		
	}

	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		
	}
	
	public Settings(int id) 
	{
		this.id = id;
	}

	public int getID() 
	{
		return id;		
	}

}
