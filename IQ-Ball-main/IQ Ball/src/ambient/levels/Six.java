package ambient.levels;

import org.newdawn.slick.SlickException;

import core.Main;
import entities.*;
import world.*;


public class Six extends Level{
	
	protected BlackHole blackHole1;
	protected WoodBox woodBox1;
	
	protected MovingTile moveTile1;
	protected MovingTile moveTile2;
	protected MovingTile moveTile3;
	
	public Six(int id) {
		super(id);
	}

	public void init() throws SlickException {
		
		super.init();
		BlackHole.init();
		
		player1 = new Player(Main.getScreenWidth()*.02f, Main.getScreenHeight()*.9f);
		grapple1 = new Grapple(0, 0);
		repel1 = new RepelBeam(0, 0);
		
		blackHole1=new BlackHole(Main.getScreenWidth()*-.05f,Main.getScreenHeight()*0.9f,-1600);
		blackHoleList.add(blackHole1);
		
		woodBox1= new WoodBox(Main.getScreenWidth()*.71f,Main.getScreenHeight()*.01f);
		boxList.add(woodBox1);

		target = new Target(Main.getScreenWidth()*.46f, Main.getScreenHeight()*.41f, 50, 50);
		
		moveTile1=new MovingTile(Main.getScreenWidth()*.5f, Main.getScreenHeight()*.57f, 64, 64 ,Main.getScreenWidth()*.4f, 0, 1,false);
		moveTiles.add(moveTile1);
		moveTile2=new MovingTile(Main.getScreenWidth()*.6f, Main.getScreenHeight()*.4f, 64, 64 ,Main.getScreenWidth()*.3f, 0, 1,true);
		moveTiles.add(moveTile2);
		moveTile3=new MovingTile(Main.getScreenWidth()*.65f, Main.getScreenHeight()*.18f, 64, 64 ,Main.getScreenWidth()*.2f, 0, 1,false);
		moveTiles.add(moveTile3);
	
		Tile floor = new Tile(-50, Main.getScreenHeight()*.99f, Main.getScreenWidth()+50, 50);
		tiles.add(floor);
		Tile roof = new Tile(-50,-50, Main.getScreenWidth()+50,60);
		tiles.add(roof);
		Tile leftWall = new Tile(-50,-50,60,Main.getScreenHeight()+50);
		tiles.add(leftWall);
		Tile rightWall = new Tile(Main.getScreenWidth()-10,-50,50,Main.getScreenHeight()+50);
		tiles.add(rightWall);
		
		
		Tile tile1 = new Tile(Main.getScreenWidth()*-0.1f, Main.getScreenHeight()*.85f, (int)(Main.getScreenWidth()*0.8f), 30);
		tiles.add(tile1);
		Tile tile2 = new Tile(Main.getScreenWidth()*.46f, Main.getScreenHeight()*.48f, (int)(Main.getScreenWidth()*.04f), (int)(Main.getScreenHeight()*.37f));
		tiles.add(tile2);
		Tile tile3 = new Tile(Main.getScreenWidth()*.46f, 0, (int)(Main.getScreenWidth()*.04f), (int)(Main.getScreenHeight()*.37f));
		tiles.add(tile3);
		Tile tile4 = new Tile(Main.getScreenWidth()*.95f, Main.getScreenHeight()*.38f, (int)(Main.getScreenWidth()*.04f), (int)(Main.getScreenHeight()*.04f));
		tiles.add(tile4);


		
		Hazard hazard1 = new Hazard(Main.getScreenWidth()*-0.1f, Main.getScreenHeight()*.83f, (int)(Main.getScreenWidth()*0.7f), 30, true);
		hazards.add(hazard1);
		
	}
	
	public void update() {
		super.update();
		blackHole1.update();
	}
	
}

