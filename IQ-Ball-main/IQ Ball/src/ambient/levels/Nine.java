package ambient.levels;

import org.newdawn.slick.SlickException;

import core.Main;
import entities.*;
import world.*;


public class Nine extends Level{
	
	protected BlackHole blackHole1;
	protected BlackHole blackHole2;
	protected BlackHole blackHole3;
	protected BlackHole blackHole4;
	protected BlackHole blackHole5;
	protected BlackHole blackHole6;
	protected BlackHole blackHole7;
	protected BlackHole blackHole8;
	
	protected WoodBox woodBox1;
	protected WoodBox woodBox2;
	protected WoodBox woodBox3;
	
	protected MovingTile moveTile1;
	protected MovingTile moveTile2;
	protected MovingTile moveTile3;
	
	public Nine(int id) {
		super(id);
	}

	public void init() throws SlickException {
		super.init();
		BlackHole.init();
		
		player1 = new Player(Main.getScreenWidth()*.4f, Main.getScreenHeight()*.09f);
		grapple1 = new Grapple(0, 0);
		repel1 = new RepelBeam(0, 0);
		
		blackHole1=new BlackHole(Main.getScreenWidth()*.46f,Main.getScreenHeight()*0.75f,150);
		blackHoleList.add(blackHole1);
//		blackHole2=new BlackHole(Main.getScreenWidth()*.26f,Main.getScreenHeight()*0.9f,200);
//		blackHoleList.add(blackHole2);
		blackHole3=new BlackHole(Main.getScreenWidth()*.576f,Main.getScreenHeight()*0.65f,180);
		blackHoleList.add(blackHole3);
//		blackHole4=new BlackHole(Main.getScreenWidth()*.66f,Main.getScreenHeight()*0.85f,150);
//		blackHoleList.add(blackHole4);
//		blackHole5=new BlackHole(Main.getScreenWidth()*.09f,Main.getScreenHeight()*0.82f,150);
//		blackHoleList.add(blackHole5);
		blackHole6=new BlackHole(Main.getScreenWidth()*.25f,Main.getScreenHeight()*0.74f,180);
		blackHoleList.add(blackHole6);
		blackHole7=new BlackHole(Main.getScreenWidth()*.86f,Main.getScreenHeight()*0.82f,100);
		blackHoleList.add(blackHole7);
		blackHole8=new BlackHole(Main.getScreenWidth()*.35f,Main.getScreenHeight()*0.63f,120);
		blackHoleList.add(blackHole8);

		target = new Target(Main.getScreenWidth()*.6f, Main.getScreenHeight()*.09f, 50, 50);
		
		moveTile1=new MovingTile(Main.getScreenWidth()*.8f, Main.getScreenHeight()*.21f, 64, 64 ,Main.getScreenWidth()*.2f, 0, 1f,false);
		moveTiles.add(moveTile1);
		moveTile2=new MovingTile(Main.getScreenWidth()*.8f, Main.getScreenHeight()*.29f, 64, 64 ,Main.getScreenWidth()*.2f, 0, 1,true);
		moveTiles.add(moveTile2);
		moveTile3=new MovingTile(Main.getScreenWidth()*.8f, Main.getScreenHeight()*.37f, 64, 64 ,Main.getScreenWidth()*.2f, 0, 1f,false);
		moveTiles.add(moveTile3);
	
		Tile floor = new Tile(-50, Main.getScreenHeight()*.99f, Main.getScreenWidth()+50, 50);
		tiles.add(floor);
		Tile roof = new Tile(-50,-50, Main.getScreenWidth()+50,60);
		tiles.add(roof);
		Tile leftWall = new Tile(-50,-50,60,Main.getScreenHeight()+50);
		tiles.add(leftWall);
		Tile rightWall = new Tile(Main.getScreenWidth()-10,-50,1000,Main.getScreenHeight()+50);
		tiles.add(rightWall);
		
		woodBox1= new WoodBox(Main.getScreenWidth()*.2f,Main.getScreenHeight()*.86f);
		boxList.add(woodBox1);
		woodBox2= new WoodBox(Main.getScreenWidth()*.51f,Main.getScreenHeight()*.72f);
		boxList.add(woodBox2);
		woodBox3= new WoodBox(Main.getScreenWidth()*.42f,Main.getScreenHeight()*.58f);
		boxList.add(woodBox3);
		
		
		
		Tile tile1 = new Tile(Main.getScreenWidth()*.46f, 0, (int)(Main.getScreenWidth()*.04f), (int)(Main.getScreenHeight()*.37f));
		tiles.add(tile1);
		Tile tile2 = new Tile(Main.getScreenWidth()*.20f, Main.getScreenHeight()*.2f, (int)(Main.getScreenWidth()*.6f), (int)(Main.getScreenHeight()*.2f));
		tiles.add(tile2);
		Tile tile3 = new Tile(Main.getScreenWidth()*.0f, Main.getScreenHeight()*.6f, (int)(Main.getScreenWidth()*.17f), (int)(Main.getScreenHeight()*.4f));
		tiles.add(tile3);
		
		


		
		Hazard hazard1 = new Hazard(Main.getScreenWidth()*-0.1f, Main.getScreenHeight()*.95f, (int)(Main.getScreenWidth()*1.1f), 30,true);
		hazards.add(hazard1);
		
		
		
	}
	
	public void update() {
		super.update();
		blackHole1.update();
//		blackHole2.update();
		blackHole3.update();
//		blackHole4.update();
//		blackHole5.update();
		blackHole6.update();
		blackHole7.update();
		blackHole8.update();
	}
	
}
