package ambient.levels;

import org.newdawn.slick.SlickException;

import core.Main;
import entities.*;
import world.*;


public class Eight extends Level{
	
	protected BlackHole blackHole1;
	protected BlackHole blackHole2;
	protected BlackHole blackHole3;
	protected BlackHole blackHole4;
	
	protected WoodBox woodBox1;
	protected WoodBox woodBox2;
	protected WoodBox woodBox3;
	
	protected MovingTile moveTile1;
	
	public Eight(int id) {
		super(id);
	}

	public void init() throws SlickException {
		super.init();
		BlackHole.init();
		
		player1 = new Player(Main.getScreenWidth()*.02f, Main.getScreenHeight()*.7f);
		grapple1 = new Grapple(0, 0);
		repel1 = new RepelBeam(0, 0);
		
		blackHole1=new BlackHole(Main.getScreenWidth()*.1f,30,400);
		blackHoleList.add(blackHole1);
		blackHole2=new BlackHole(Main.getScreenWidth()*.3f,30,400);
		blackHoleList.add(blackHole2);
		blackHole3=new BlackHole(Main.getScreenWidth()*.5f,30,400);
		blackHoleList.add(blackHole3);
		blackHole4=new BlackHole(Main.getScreenWidth()*.7f,30,400);
		blackHoleList.add(blackHole4);
		
		woodBox1= new WoodBox(Main.getScreenWidth()*.4f,Main.getScreenHeight()*.75f);
		boxList.add(woodBox1);
		woodBox2= new WoodBox(Main.getScreenWidth()*.32f,Main.getScreenHeight()*.79f);
		boxList.add(woodBox2);
		woodBox3= new WoodBox(Main.getScreenWidth()*.36f,Main.getScreenHeight()*.6f);
		boxList.add(woodBox3);

		target = new Target(Main.getScreenWidth()*.1f, Main.getScreenHeight()*.9f, 50, 50);
		
		moveTile1=new MovingTile(Main.getScreenWidth()*.05f, Main.getScreenHeight()*.45f, 64, 64 ,Main.getScreenWidth()*.8f, 0, 1,false);
		moveTiles.add(moveTile1);
	
		Tile floor = new Tile(-50, Main.getScreenHeight()*.99f, Main.getScreenWidth()+50, 50);
		tiles.add(floor);
		Tile roof = new Tile(-50,-50, Main.getScreenWidth()+50,60);
		tiles.add(roof);
		Tile leftWall = new Tile(-50,-50,60,Main.getScreenHeight()+50);
		tiles.add(leftWall);
		Tile rightWall = new Tile(Main.getScreenWidth()-10,-50,50,Main.getScreenHeight()+50);
		tiles.add(rightWall);
		
		
		Tile tile1 = new Tile((float)(Main.getScreenWidth()*.4f), Main.getScreenHeight()*.1f, (int)(Main.getScreenWidth()), 30);
		tiles.add(tile1);
		Tile tile2 = new Tile(Main.getScreenWidth()*.07f, Main.getScreenHeight()*.48f, (int)(Main.getScreenWidth()*.01f), 1000);
		tiles.add(tile2);
		Tile tile3 = new Tile(10, Main.getScreenHeight()*.1f, (int)(Main.getScreenWidth()), (int)(Main.getScreenHeight()*0.1f));
		tiles.add(tile3);
		Tile tile4 = new Tile(Main.getScreenWidth()*0.081f, Main.getScreenHeight()*.52f, (int)(Main.getScreenWidth()*.63),33);
		tiles.add(tile4);
		Tile tile5 = new Tile(Main.getScreenWidth()*0.67f, Main.getScreenHeight()*.50f, (int)(Main.getScreenWidth()*.04), 40);
		tiles.add(tile5);
		
		Hazard hazard1 = new Hazard(Main.getScreenWidth()*0.081f, Main.getScreenHeight()*.5f, (int)(Main.getScreenWidth()*.6), 10, false);
		hazards.add(hazard1);
		
	}
	
	public void update() {
		super.update();
		blackHole1.update();
		blackHole2.update();
		blackHole3.update();
		blackHole4.update();
	}
	
}
