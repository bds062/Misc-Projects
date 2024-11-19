package ambient.levels;

import org.newdawn.slick.SlickException;

import core.Main;
import entities.*;
import world.*;


public class Seven extends Level{
	
	protected BlackHole blackHole1;
	protected WoodBox woodBox1;
	protected WoodBox woodBox2;
	protected WoodBox woodBox3;
	protected WoodBox woodBox4;
	protected WoodBox woodBox5;
	protected WoodBox woodBox6;
	
	public Seven(int id) {
		super(id);
	}

	public void init() throws SlickException {
		super.init();
		BlackHole.init();
		
		player1 = new Player(Main.getScreenWidth()*.02f, Main.getScreenHeight()*.7f);
		grapple1 = new Grapple(0, 0);
		repel1 = new RepelBeam(0, 0);
		target = new Target(Main.getScreenWidth()*.95f, Main.getScreenHeight()*.9f, 50, 50);
//		blackHole1=new BlackHole(Main.getScreenWidth()*.5f-32,Main.getScreenHeight()*.9f,-1000);
		blackHole1=new BlackHole(Main.getScreenWidth()*.5f-32,Main.getScreenHeight()*.9f,250);
		blackHoleList.add(blackHole1);
		woodBox1= new WoodBox(Main.getScreenWidth()*.4f-64,Main.getScreenHeight()*.5f);
		boxList.add(woodBox1);
		woodBox2= new WoodBox(Main.getScreenWidth()*.6f-64,Main.getScreenHeight()*.5f);
		boxList.add(woodBox2);
//		woodBox3= new WoodBox(Main.getScreenWidth()*.25f-64,130);
//		boxList.add(woodBox3);
		woodBox4= new WoodBox(Main.getScreenWidth()*.75f-64,20);
		boxList.add(woodBox4);

		
	
		Tile floor = new Tile(-50, Main.getScreenHeight()*.99f, Main.getScreenWidth()+50, 50);
		tiles.add(floor);
		Tile roof = new Tile(-50,-50, Main.getScreenWidth()+50,60);
		tiles.add(roof);
		Tile leftWall = new Tile(-50,-50,60,Main.getScreenHeight()+50);
		tiles.add(leftWall);
		Tile rightWall = new Tile(Main.getScreenWidth()-10,-50,50,Main.getScreenHeight()+50);
		tiles.add(rightWall);
		
		
		Tile tile1 = new Tile(Main.getScreenWidth()*.0f, Main.getScreenHeight()*.3f, 64, 64);
		tiles.add(tile1);
		
		Tile tile2 = new Tile(Main.getScreenWidth()*.1f, Main.getScreenHeight()*.25f, 64, 64);
		tiles.add(tile2);
		
		Tile tile5 = new Tile(Main.getScreenWidth()*.25f, Main.getScreenHeight()*.15f, 64, 64);
		tiles.add(tile5);
		
		Tile tile8 = new Tile(Main.getScreenWidth()*.4f, Main.getScreenHeight()*.1f, 64, 64);
		tiles.add(tile8);
		
		Tile tile7 = new Tile(Main.getScreenWidth()*.6f-64, Main.getScreenHeight()*.12f, 64, 64);
		tiles.add(tile7);
		
		Tile tile6 = new Tile(Main.getScreenWidth()*.75f-64, Main.getScreenHeight()*.2f, 64, 64);
		tiles.add(tile6);
		
		Tile tile3 = new Tile(Main.getScreenWidth()*.9f-64, Main.getScreenHeight()*.25f, 64, 64);
		tiles.add(tile3);
		
		Tile tile4 = new Tile(Main.getScreenWidth()-64, Main.getScreenHeight()*.3f, 64, 64);
		tiles.add(tile4);
		
		Hazard hazard1 = new Hazard(Main.getScreenWidth()*.1f, Main.getScreenHeight()*.95f, (int)(Main.getScreenWidth()*.8), 100,true);
		hazards.add(hazard1);
		
		
		
	}
	
	public void update() {
		super.update();
		blackHole1.update();
	}
	
}
