package ambient.levels;

import org.newdawn.slick.SlickException;

import core.Main;
import entities.*;
import world.*;


public class Five extends Level{
	

	
	protected WoodBox woodBox1;
	protected WoodBox woodBox2;
	protected WoodBox woodBox3;
	protected WoodBox woodBox4;
	protected WoodBox woodBox5;
	protected WoodBox woodBox6;
	protected WoodBox woodBox7;
	protected WoodBox woodBox8;
	protected WoodBox woodBox9;
	protected WoodBox woodBox10;
	protected WoodBox woodBox11;
	protected WoodBox woodBox12;
	
	public Five(int id) {
		super(id);
	}

	public void init() throws SlickException {
		super.init();
		BlackHole.init();
		
		player1 = new Player(200, 500);
		grapple1 = new Grapple(0, 0);
		repel1 = new RepelBeam(0, 0);
//		blackHole1=new BlackHole(500,100);
//		blackHoleList.add(blackHole1);
//		woodBox1= new WoodBox(500,10);
//		boxList.add(woodBox1);
		target = new Target(Main.getScreenWidth()*.9f, Main.getScreenHeight()*.9f, 50, 50);
		
	
		Tile floor = new Tile(-50, Main.getScreenHeight()*.99f, Main.getScreenWidth()+50, 50);
		tiles.add(floor);
//		Tile roof = new Tile(-50,-50, Main.getScreenWidth()+50,60);
//		tiles.add(roof);
		Tile leftWall = new Tile(-50,-50,60,Main.getScreenHeight()+50);
		tiles.add(leftWall);
		Tile rightWall = new Tile(Main.getScreenWidth()-10,-50,10,Main.getScreenHeight()+50);
		tiles.add(rightWall);
		
		
		Tile tile1 = new Tile(10, Main.getScreenHeight()*.72f, (int)(Main.getScreenWidth()*.2), (int)(Main.getScreenHeight()*.6));
		tiles.add(tile1);
		Tile tile2 = new Tile(Main.getScreenWidth()*.18f, Main.getScreenHeight()*.58f, (int)(Main.getScreenWidth()*.03), (int)(Main.getScreenHeight()*.02));
		tiles.add(tile2);
		Tile tile3 = new Tile(Main.getScreenWidth()*.6f, Main.getScreenHeight()*.46f, (int)(Main.getScreenWidth()*.04), (int)(Main.getScreenHeight()*.035));
		tiles.add(tile3);
		
		woodBox1= new WoodBox(Main.getScreenWidth()*.4f,Main.getScreenHeight()*.86f);
		boxList.add(woodBox1);
		woodBox2= new WoodBox(Main.getScreenWidth()*.41f,Main.getScreenHeight()*.72f);
		boxList.add(woodBox2);
		woodBox3= new WoodBox(Main.getScreenWidth()*.42f,Main.getScreenHeight()*.58f);
		boxList.add(woodBox3);
		woodBox4= new WoodBox(Main.getScreenWidth()*.43f,Main.getScreenHeight()*.44f);
		boxList.add(woodBox4);
		woodBox5= new WoodBox(Main.getScreenWidth()*.44f,Main.getScreenHeight()*.30f);
		boxList.add(woodBox5);
		woodBox6= new WoodBox(Main.getScreenWidth()*.45f,Main.getScreenHeight()*.16f);
		boxList.add(woodBox6);
		woodBox7= new WoodBox(Main.getScreenWidth()*.46f,Main.getScreenHeight()*.02f);
		boxList.add(woodBox7);
		woodBox8= new WoodBox(Main.getScreenWidth()*.47f,Main.getScreenHeight()*-.12f);
		boxList.add(woodBox8);
		woodBox9= new WoodBox(Main.getScreenWidth()*.7f,Main.getScreenHeight()*.86f);
		boxList.add(woodBox9);
//		woodBox10= new WoodBox(Main.getScreenWidth()*.62f,Main.getScreenHeight()*.44f);
//		boxList.add(woodBox10);
		woodBox11= new WoodBox(Main.getScreenWidth()*.65f,Main.getScreenHeight()*.30f);
		boxList.add(woodBox11);
		woodBox12= new WoodBox(Main.getScreenWidth()*.60f,Main.getScreenHeight()*.86f);
		boxList.add(woodBox12);
		
		
	}
	
	public void update() {
		super.update();
	}
	
}
