





package ambient.levels;

import org.newdawn.slick.SlickException;

import core.Main;
import entities.*;
import world.*;


public class One extends Level{
	
	protected BlackHole blackHole1;
	protected BlackHole blackHole2;
	protected BlackHole blackHole3;
	protected BlackHole blackHole4;
	
	protected WoodBox woodBox1;
	protected WoodBox woodBox2;
	protected WoodBox woodBox3;
	
	protected MovingTile moveTile1;
	protected MovingTile moveTile2;
	protected MovingTile moveTile3;

	protected Trampoline trampoline;
	
	
	public One(int id) {
		super(id);
		completed=true;
	}

	public void init() throws SlickException {
		super.init();
		BlackHole.init();
		
		player1 = new Player(Main.getScreenWidth()*.02f, Main.getScreenHeight()*.9f);
		grapple1 = new Grapple(0, 0);
		repel1 = new RepelBeam(0, 0);
//		blackHole1=new BlackHole(500,100);
//		blackHoleList.add(blackHole1);
//		woodBox1= new WoodBox(500,10);
//		boxList.add(woodBox1);
		target = new Target(Main.getScreenWidth()*.02f, Main.getScreenHeight()*.1f, 50, 50);
		
	
		Tile floor = new Tile(-50, Main.getScreenHeight()*.99f, Main.getScreenWidth()+50, 50);
		tiles.add(floor);
		Tile roof = new Tile(-50,-50, Main.getScreenWidth()+50,60);
		tiles.add(roof);
		Tile leftWall = new Tile(-50,-50,60,Main.getScreenHeight()+50);
		tiles.add(leftWall);
		Tile rightWall = new Tile(Main.getScreenWidth()-10,-50,50,Main.getScreenHeight()+50);
		tiles.add(rightWall);
		
		Tile tile1 = new Tile(10, Main.getScreenHeight()*.75f, (int)(Main.getScreenWidth()*.8), 30);
		tiles.add(tile1);
		Tile tile2 = new Tile(Main.getScreenWidth()*.2f, Main.getScreenHeight()*.5f, (int)(Main.getScreenWidth()*.8), 30);
		tiles.add(tile2);
		Tile tile3 = new Tile(10, Main.getScreenHeight()*.25f, (int)(Main.getScreenWidth()*.8), 30);
		tiles.add(tile3);
		Trampoline trampoline1 = new Trampoline(Main.getScreenWidth()-138,Main.getScreenHeight()*.99f-128,128,128);
		trampolines.add(trampoline1);
		Tile tile4 = new Tile(Main.getScreenWidth()-168,Main.getScreenHeight()*.99f-128,30,128);
		tiles.add(tile4);
		Trampoline trampoline2 = new Trampoline(Main.getScreenWidth()-138,Main.getScreenHeight()*.5f-128,128,128);
		trampolines.add(trampoline2);
		Tile tile5 = new Tile(Main.getScreenWidth()-168,Main.getScreenHeight()*.5f-128,30,128);
		tiles.add(tile5);
		Trampoline trampoline3 = new Trampoline(10,Main.getScreenHeight()*.75f-128,128,128);
		trampolines.add(trampoline3);
		Tile tile6 = new Tile(138,Main.getScreenHeight()*.75f-128,30,128);
		tiles.add(tile6);
		
	}
	
	public void update() {
		super.update();
		for(BlackHole b: blackHoleList)
		{
			b.update();
		}
	}
	
}




