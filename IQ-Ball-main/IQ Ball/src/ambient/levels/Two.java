package ambient.levels;

import org.newdawn.slick.SlickException;

import core.Main;
import entities.*;
import world.*;

public class Two extends Level{
	protected BlackHole blackHole1;
	protected WoodBox woodBox1;
	protected MovingTile jeff;
	protected MovingTile moveTile2;
	
	public Two(int id) {
		super(id);
	}

	public void init() throws SlickException {
		super.init();
		player1 = new Player(500, 500);
		grapple1 = new Grapple(0, 0);
		repel1 = new RepelBeam(0, 0);
		jeff=new MovingTile(Main.getScreenWidth()*.2f, 500, 64, 64,300, 0, 1,false);
		moveTiles.add(jeff);
		moveTile2=new MovingTile(Main.getScreenWidth()*.6f, 500, 64, 64,300, 0, 1,true);
		moveTiles.add(moveTile2);
//		blackHole1=new BlackHole(500,100);
//		blackHoleList.add(blackHole1);
//		woodBox1= new WoodBox(500,10);
//		boxList.add(woodBox1);
		target = new Target(Main.getScreenWidth()*.9f, Main.getScreenHeight()*.9f, 50, 50);
		
		Tile floor = new Tile(-50, Main.getScreenHeight()*.99f, Main.getScreenWidth()+50, 50);
		tiles.add(floor);
		Tile roof = new Tile(-50,-50, Main.getScreenWidth()+50,60);
		tiles.add(roof);
		Tile leftWall = new Tile(-50,-50,60,Main.getScreenHeight()+50);
		tiles.add(leftWall);
		Tile rightWall = new Tile(Main.getScreenWidth()-10,-50,50,Main.getScreenHeight()+50);
		tiles.add(rightWall);
	
		
		Tile tile1 = new Tile(Main.getScreenWidth()*.45f,-50f,(int)(Main.getScreenWidth()*.1),(int)(Main.getScreenHeight()*.4+50));
		tiles.add(tile1);
		Tile tile2 = new Tile(Main.getScreenWidth()*.36f,980,(int)(Main.getScreenWidth()*.04),100);
		tiles.add(tile2);
		Tile tile3 = new Tile(Main.getScreenWidth()*.58f,980,(int)(Main.getScreenWidth()*.04),100);
		tiles.add(tile3);
		Hazard hazard1 = new Hazard(Main.getScreenWidth()*.4f, 1000f, (int)(Main.getScreenWidth()*.2), 70,true);
		hazards.add(hazard1);
//		MovingTile movingTile1 = new MovingTile()
//		Hazard hazard1 = new Hazard(Main.getScreenWidth()*.3f,Main.getScreenWidth()*.95f,(int)(Main.getScreenWidth()*.4),(int)(Main.getScreenHeight()*Main.getScreenHeight()*.1));
//		hazards.add(hazard1);
	}
}