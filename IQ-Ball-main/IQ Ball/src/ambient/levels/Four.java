package ambient.levels;

import org.newdawn.slick.SlickException;

import core.Main;
import entities.*;
import world.*;


public class Four extends Level{
	
	
	
	protected WoodBox woodBox1;
	protected WoodBox woodBox2;
	protected WoodBox woodBox3;
	protected WoodBox woodBox4;
	protected WoodBox woodBox5;
	
	protected MovingTile moveTile1;

	
	public Four(int id) {
		super(id);
	}

	public void init() throws SlickException {
		super.init();
		BlackHole.init();
		
		player1 = new Player(Main.getScreenWidth()*.02f, Main.getScreenHeight()*.7f);
		grapple1 = new Grapple(0, 0);
		repel1 = new RepelBeam(0, 0);
		target = new Target(Main.getScreenWidth()*.9f, Main.getScreenHeight()*.9f, 50, 50);
		
		woodBox1= new WoodBox(Main.getScreenWidth()*.8f,Main.getScreenHeight()*.7f);
		boxList.add(woodBox1);
		woodBox2= new WoodBox(Main.getScreenWidth()*.85f,Main.getScreenHeight()*.5f);
		boxList.add(woodBox2);
		woodBox3= new WoodBox(Main.getScreenWidth()*.9f,Main.getScreenHeight()*.3f);
		boxList.add(woodBox3);
		woodBox4= new WoodBox(Main.getScreenWidth()*.36f,Main.getScreenHeight()*.5f);
		boxList.add(woodBox4);
		woodBox5= new WoodBox(Main.getScreenWidth()*.46f,Main.getScreenHeight()*.3f);
		boxList.add(woodBox5);
		
	
		
		moveTile1=new MovingTile(Main.getScreenWidth()*.55f, Main.getScreenHeight()*.45f, 64, 64 ,Main.getScreenWidth()*.4f, 0, 1,false);
		moveTiles.add(moveTile1);
	
		Tile floor = new Tile(-50, Main.getScreenHeight()*.99f, Main.getScreenWidth()+50, 50);
		tiles.add(floor);
		Tile roof = new Tile(-50,-50, Main.getScreenWidth()+50,60);
		tiles.add(roof);
		Tile leftWall = new Tile(-50,-50,60,Main.getScreenHeight()+50);
		tiles.add(leftWall);
		Tile rightWall = new Tile(Main.getScreenWidth()-10,-50,50,Main.getScreenHeight()+50);
		tiles.add(rightWall);
		
		
		Tile tile1 = new Tile((Main.getScreenWidth()*.45f), Main.getScreenHeight()*.5f, (int)(Main.getScreenWidth()*.1), (int)(Main.getScreenHeight()*.5));
		tiles.add(tile1);
		Tile tile2 = new Tile((Main.getScreenWidth()*.35f), Main.getScreenHeight()*.7f, (int)(Main.getScreenWidth()*.1), (int)(Main.getScreenHeight()*.1));
		tiles.add(tile2);
		Tile tile3 = new Tile((Main.getScreenWidth()*.2f), Main.getScreenHeight()*.3f, (int)(Main.getScreenWidth()*.05), (int)(Main.getScreenHeight()*.05));
		tiles.add(tile3);
		Tile tile4 = new Tile((Main.getScreenWidth()*.4f), Main.getScreenHeight()*.2f, (int)(Main.getScreenWidth()*.05), (int)(Main.getScreenHeight()*.05));
		tiles.add(tile4);
		
		
		
	}
	
	public void update() {
		super.update();
		for(BlackHole b: blackHoleList)
		{
			b.update();
		}
	}
	
}
