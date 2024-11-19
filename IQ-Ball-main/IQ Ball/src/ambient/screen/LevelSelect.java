package ambient.screen;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import ambient.ui.Button;
import core.Game;
import core.Main;

public class LevelSelect extends BasicGameState 
{	
	private int id;
	StateBasedGame sbg;
	private int xPos2;
	private int yPos2;
	
	private Image[] stars;
 	
	private Button[] levelMenu;
	
	private Button nextSlide, prevSlide;
	private Image nsImage, pvImage;
	
	public static int level;
	
	private Image background;
	
	private Image[] menu;
	private Image locked;
	private int curSlide;
	
	private int nsdelayTimer = 0, pvdelayTimer = 0;
	private final int delay = 60;

	public LevelSelect(int id) 
	{
		this.id = id;
		level = 1;
	}
	
	@Override
	public int getID() 
	{
		return id;		
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException 
	{
		gc.setShowFPS(true);
		this.sbg = sbg;	
		levelMenu = new Button[9];
		for (int i = 0; i < levelMenu.length; i++) {
				String path = new String("res/levels/0" + (i) + ".png");
				Image curImage = new Image(path);
				levelMenu[i] = new Button(curImage, (i), curImage.getWidth()*2*(i%3) + 750, curImage.getHeight()*1.5f + 300, curImage.getWidth(), curImage.getHeight());
		}
		
		stars = new Image[4];
		for (int i = 0; i < 4; i++) {
			String path = new String("res/stars/" + (i) + ".png");
			stars[i] =  new Image(path);
		}
		background = new Image("res/selection.png");
		curSlide = 0;
		menu = new Image[3];
		
		for (int i = 0; i < 3; i++) {
			String path = new String("res/menu/" + (i) + ".png");
			menu[i] = new Image(path);
		}
		
		locked = new Image("res/levels/locked.png");
		
		nsImage = new Image("res/next.png");
		nextSlide = new Button(nsImage, 0, 1250, 505, nsImage.getHeight(), nsImage.getWidth());
		
		pvImage = new Image("res/prev.png");
		prevSlide = new Button(pvImage, 0, 580, 505, pvImage.getHeight(), pvImage.getWidth());
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{	
		for (Button b : levelMenu) {
			if (curSlide == b.getId()/3) {
				if (Game.levels[b.getId()].isCompleted()) {
					b.update(gc);
					if (b.clicked) {
						level = b.getId() + 1;
				    	sbg.enterState(Main.GAME_ID);
					}
				}
			}
		}
		
		
		nextSlide.update(gc);
		prevSlide.update(gc);
		
		if (nextSlide.clicked && curSlide < 2 && nsdelayTimer >= delay) {
			curSlide++;
			nsdelayTimer = 0;
		}
		else if (prevSlide.clicked && curSlide > 0 && pvdelayTimer >= delay) {
			curSlide--;
			pvdelayTimer = 0;
		}
		else {
			nsdelayTimer++;
			pvdelayTimer++;
		}
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException 
	{
		g.drawImage(background, 0, 0);
		g.drawImage(menu[curSlide], Main.getScreenWidth()/2-menu[curSlide].getWidth()/2, Main.getScreenHeight()/6);
		for (Button b : levelMenu) {
			if (curSlide == b.getId()/3) {
				if (Game.levels[b.getId()].isCompleted()) {
					b.render(g);
					g.drawImage(stars[Game.levels[b.getId()].score], b.getLTX()+b.getWidth()/2-73, b.getRBY()+20);
				}
				else {
					g.drawImage(locked, b.getLTX(), b.getLTY());
				}
//				g.drawString(String.valueOf(Game.levels[b.getId()].isCompleted()), b.getLTX()-30, b.getLTY());
//				g.drawString(String.valueOf(b.getId()), b.getRBX(), b.getLTY()-40);
//				g.drawString(String.valueOf(Game.levels[b.getId()].score), b.getLTX(), b.getLTY()-40);
			}
		}
		
		nextSlide.render(g);
		prevSlide.render(g);
	}
	
	@Override
	public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException 
	{
  
	}

	@Override
	public void leave(GameContainer gc, StateBasedGame sbg) throws SlickException 
	{
		Main.game.init(gc, sbg);
	}

	@Override
	public void keyPressed(int key, char c)
	{
	}
	
	@Override
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
	}
}
