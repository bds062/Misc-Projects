package ambient.levels;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import core.Game;
import entities.BlackHole;
import entities.Grapple;
import entities.Player;
import entities.RepelBeam;
import entities.WoodBox;
import world.Hazard;
import world.MovingTile;
import world.Target;
import world.Tile;
import world.Trampoline;

public class Level {
	
	protected ArrayList<Tile> tiles;
	protected ArrayList<MovingTile> moveTiles;
	protected ArrayList<Hazard> hazards; 
	protected ArrayList<Trampoline> trampolines;
	protected ArrayList<BlackHole> blackHoleList;
	protected ArrayList<WoodBox> boxList;
	
	
	protected Target target;
	protected Player player1;
	protected Grapple grapple1;
	protected RepelBeam repel1;
	
	protected Image background1;
	protected Image background2;
	protected Image background3;
	
	protected boolean completed = false;
	
	protected int frames;
	protected int time;
	protected int clicks;
	
	public int score;

	public int id;
	
	public Level(int id) {
		this.id = id;
	}
	
	public void init() throws SlickException {
		tiles = new ArrayList<>();
		moveTiles = new ArrayList<>();
		hazards = new ArrayList<>();
		trampolines = new ArrayList<>();
		blackHoleList=new ArrayList<>();
		boxList = new ArrayList<>();
		
		background1 = new Image("res/background1.png");
		background2 = new Image("res/background2.png");
		background3 = new Image("res/background3.png");

		Player.init();
		Target.init();
		Hazard.init();
		Grapple.init();
		BlackHole.init();
		WoodBox.init();
		Tile.init();
		MovingTile.init();
		Trampoline.init();
		
		time = 0;
		clicks = 0;
	}
	
	public void render(Graphics g) {
		if (id<3) {
			g.drawImage(background1, 0, 0);
		} else if (id<6) {
			g.drawImage(background2, 0, 0);
		} else {
			g.drawImage(background3, 0, 0);
		}
		
		for (Tile t : tiles) {
			t.render(g); 
		}
		
		for (MovingTile m : moveTiles) {
			m.render(g);
		}
		 
		for (Hazard h : hazards) {
			h.render(g);
		}
		
		for (Trampoline t : trampolines) {
			t.render(g);
		}
		
		for(BlackHole b : blackHoleList)
		{
			b.render(g);
		}
		for(WoodBox b : boxList)
		{
			b.render(g);
		}
		
		player1.render(g);
		target.render(g);
		grapple1.render(g);
		repel1.render(g);
		
		g.setColor(Color.blue);
		g.drawString(String.valueOf(time), 1880, 10);
	}
	
	public void update() {
		frames++;
		if (frames % 60 == 0) {
			time++;
		}
		for (MovingTile m : moveTiles) {
			m.update();
		}
		for(WoodBox b : boxList)
		{
			b.update(Game.delta,0.1f);
		}
		
		player1.update(Game.delta, 0.1f);
		grapple1.update();
		repel1.update();
	}
	
	public ArrayList<Tile> getTiles() {
		return tiles;
	}

	public ArrayList<MovingTile> getMoveTiles() {
		return moveTiles;
	}

	public ArrayList<Hazard> getHazards() {
		return hazards;
	}
	
	public ArrayList<Trampoline> getTrampolines() {
		return trampolines;
	}
	
	public ArrayList<BlackHole> getBlackHoles() {
		return blackHoleList;
	}
	public ArrayList<WoodBox> getBoxs() {
		return boxList;
	}

	public Target getTarget() {
		return target;
	}

	public Player getPlayer() {
		return player1;
	}

	public Grapple getGrapple() {
		return grapple1;
	}

	public RepelBeam getRepel() {
		return repel1;
	}

	public boolean isCompleted() {
		return completed;
	}

	public int getId() {
		return id;
	}
	
	public void setCompleted() {
		completed = true;
	}
	
	public int getTime() {
		return time;
	}
	
	public int getClicks() {
		return clicks;
	}
	
	public void addClick() {
		clicks++;
	}
}
