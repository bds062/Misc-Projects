package ambient.levels;

import org.newdawn.slick.SlickException;

import core.Main;
import entities.*;
import world.*;


public class Three extends Level{
	
	protected BlackHole blackHole1;
	protected WoodBox woodBox1;
	
	public Three(int id) {
		super(id);
	}

	public void init() throws SlickException {
		super.init();
		BlackHole.init();
		
		player1 = new Player(500, 500);
		grapple1 = new Grapple(0, 0);
		repel1 = new RepelBeam(0, 0);
//		blackHole1=new BlackHole(500,100);
//		blackHoleList.add(blackHole1);
//		woodBox1= new WoodBox(500,10);
//		boxList.add(woodBox1);
		target = new Target(Main.getScreenWidth()*.9f, Main.getScreenHeight()*.2f, 50, 50);
		
	
		Tile floor = new Tile(-50, Main.getScreenHeight()*.99f, Main.getScreenWidth()+50, 50);
		tiles.add(floor);
		Tile roof = new Tile(-50,-50, Main.getScreenWidth()+50,60);
		tiles.add(roof);
		Tile leftWall = new Tile(-50,-50,60,Main.getScreenHeight()+50);
		tiles.add(leftWall);
		Tile rightWall = new Tile(Main.getScreenWidth()-10,-50,50,Main.getScreenHeight()+50);
		tiles.add(rightWall);
		
		
		Tile tile1 = new Tile(10, Main.getScreenHeight()*.3f, (int)(Main.getScreenWidth()*.4), 30);
		tiles.add(tile1);
		Tile tile2 = new Tile(Main.getScreenWidth()*.5f, Main.getScreenHeight()*.3f, (int)(Main.getScreenWidth()*.55), 30);
		tiles.add(tile2);
		Tile tile3 = new Tile(Main.getScreenWidth()*.3f, Main.getScreenHeight()*.6f, (int)(Main.getScreenWidth()*.4), (int)(Main.getScreenHeight()*.45));
		tiles.add(tile3);
		Hazard hazard1 = new Hazard(Main.getScreenWidth()*.72f, 1000f, (int)(Main.getScreenWidth()*.35), 70, true);
		hazards.add(hazard1);
		Hazard hazard2 = new Hazard(10, Main.getScreenHeight()*.275f, (int)(Main.getScreenWidth()*.4), 30, false);
		hazards.add(hazard2);
		Tile tile4 = new Tile(Main.getScreenWidth()*.4f+10, Main.getScreenHeight()*.275f, 30, 30);
		tiles.add(tile4);
	}
	
	public void update() {
		super.update();
//		blackHole1.update();
	}
	
}
