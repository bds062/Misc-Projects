package core;

import java.io.IOException;
import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import animations.Animation;
import animations.Death;
import entities.Entity;
import entities.Pool;
import entities.alive.Frog;
import entities.alive.PlayerFrog;
import entities.alive.KingToad;
import entities.alive.Follower;
import entities.alive.Wanderer;
import entities.obstacles.Rock;
import environment.Map;
import grouping.Pack;
import media.Camera;
import media.ImageLoader;

public class Game extends BasicGameState 
{	
	private int id;
	private StateBasedGame sbg;
	private static Camera cam;
	private static Map map;
	private static int stage;
	public static float zoomScale=1;
	public static float idealZoomScale=1;
	public static PlayerFrog bestFrog;
	public static Pack enemyPack1;
	public static Pack enemyPack2;
	public static Pack enemyPack3;
	public static int time;
	final private float ZOOM_RATE=0.001f;

	private int mouseX;
	private int mouseY;
	//ENTITIES
	public static ArrayList<Death> animations;
	public static ArrayList<Entity> entities;
	public static ArrayList<Pack> packs;
	private static ArrayList<Pool> pools;
	
	public Game(int id) { this.id = id; }
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException 
	{
		this.sbg=sbg;
		try {
			ImageLoader.init();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		zoomScale = 1;
		idealZoomScale=1;
		entities = new ArrayList<Entity>();
		packs = new ArrayList<Pack>();
		pools = new ArrayList<Pool>();
		animations = new ArrayList<Death>();
		bestFrog= new PlayerFrog(Main.getScreenWidth()/2,Main.getScreenHeight()/2);
		entities.add(bestFrog);
		stage=0;
		cam=new Camera(this);
		map=new Map(this);
	}
	
	public static void restart() {
		zoomScale = 1;
		idealZoomScale=1;
		entities = new ArrayList<Entity>();
		packs = new ArrayList<Pack>();
		pools = new ArrayList<Pool>();
		animations = new ArrayList<Death>();
		bestFrog= new PlayerFrog(Main.getScreenWidth()/2,Main.getScreenHeight()/2);
		entities.add(bestFrog);
		stage=0;
		map.reset();
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{	
		int numFrogs=bestFrog.getPack().getFrogs().size();
		updateZoom();
		
		mouseX=(int) (Mouse.getX());
		mouseY=(int) (Mouse.getY());
		if (mouseY > Main.getScreenHeight()/2) {
	    	mouseY = Main.getScreenHeight()/2 - (mouseY - (Main.getScreenHeight()/2));
	    } 
		else if (mouseY < Main.getScreenHeight()/2) {
	    	mouseY = Main.getScreenHeight()/2 + (-mouseY + (Main.getScreenHeight()/2));
	    }
		mouseX/=zoomScale;
		mouseY/=zoomScale;
		
		mouseX+=cam.getX();
		mouseY+=cam.getY();
		time++;
		cam.update();
		map.update();
		if(bestFrog.getDistance(new Point(mouseX,mouseY))>150)
		{
			bestFrog.startJump(mouseX,mouseY);
			if(bestFrog.playerPack.battling=false)
			{
				bestFrog.getPack().moveAll(mouseX,mouseY);
			}
			bestFrog.setDestPoint(new Point(mouseX,mouseY));
			bestFrog.idle=false;
		}
		else
		{
			bestFrog.idle=true;
		}

		for(int i=0; i<entities.size(); i++)
		{
			entities.get(i).update();
		}
		for(int i=0; i<animations.size(); i++)
		{
			animations.get(i).update();
		}
		if(stage==0 && numFrogs>10) bossOne(bestFrog.getX(), bestFrog.getY());
		else if (stage==1 && numFrogs>20) bossTwo(bestFrog.getX(), bestFrog.getY());
		else if (stage==2 && numFrogs>35) bossThree(bestFrog.getX(), bestFrog.getY());
		else if (stage==3 && enemyPack3.alphaFrog.isDead()) {
			sbg.enterState(Main.END_ID);
			stage=4;
		}
		if(bestFrog.isDead()) sbg.enterState(Main.DEATH_ID);
	}

	private void updateZoom() {
		idealZoomScale=(float) (Math.pow(2, -(float)bestFrog.getPack().getFrogs().size()/100));
		if(zoomScale < idealZoomScale)
		{
			zoomScale = Math.min(zoomScale + ZOOM_RATE, idealZoomScale);
		}
		
		if(zoomScale > idealZoomScale)
		{
			zoomScale = Math.max(zoomScale - ZOOM_RATE, idealZoomScale);
		}
		zoom(1-((zoomScale-idealZoomScale)/2));
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException 
	{
		g.scale(zoomScale,zoomScale);
		g.translate(-cam.getX(), -cam.getY());
		map.render(g);
		for (int i = entities.size() - 1; i >= 0; i--) entities.get(i).render(g);
		for(int i=0; i<animations.size(); i++) {
			animations.get(i).render(g);
		}
	}

	public void keyPressed(int key, char c)
	{
//		if(key==Input.KEY_M) sbg.enterState(Main.MAP_ID);
//		if (key==Input.KEY_O) bestFrog.getPack().modifyJumpDistance(1.2f);
//		if (key==Input.KEY_P) bestFrog.getPack().modifyJumpTimer(0.8f);
//		if (key==Input.KEY_N) 
//			for(int i=0; i<25; i++)
//			{entities.add(new Wanderer((float)(1000*Math.random()),(float)(1000*Math.random())));}
//		if (key==Input.KEY_B) 
//			for(int i=0; i<5; i++)
//			{entities.add(new Follower((float)(1000*Math.random()),(float)(1000*Math.random())));}
		if (key == Input.KEY_ESCAPE) {
            sbg.enterState(Main.PAUSE_ID);
        }
	}
	public void mousePressed(int button, int x, int y)
	{
		//bestFrog.startJump(x+getCamX(), y+getCamY());
 	}
	public void mouseWheelMoved(int change)
	{
//		if (change > 0) {
//            zoom(1.02f);
//            //zoom in
//        } else {
//            zoom(.98f);
//            //zoom out
//        }
	}
	public static void addEntity(Entity guy)
	{
		entities.add(guy);
	}
	public static void removeEntity(Entity guy)
	{
		entities.remove(guy);
	}
	public static void addPack(Pack guy)
	{
		packs.add(guy);
	}
	public static void removePack(Pack guy)
	{
		packs.remove(guy);
	}
	public static void removePool(Pool p)
	{
		pools.remove(p);
	}
	public static void removeAnimation(Death d)
	{
		animations.remove(d);
	}
	public int getID() { return id; }
	public static float getCamX() { return cam.getX(); }
	public static float getCamY() { return cam.getY(); }
	public static ArrayList<Entity> getEntities()
	{
		return entities;
	}
	public static ArrayList<Pool> getPools()
	{
		return pools;
	}
	public static ArrayList<Pack> getPacks()
	{
		return packs;
	}

	private void bossOne(float xPos, float yPos) {
		float toadX=xPos+1500; float toadY=yPos+100;
		KingToad kingToad1= new KingToad(xPos+1000,yPos+1000, enemyPack1);
		entities.add(kingToad1);
		enemyPack1= new Pack(kingToad1);
		enemyPack1.boostAll(1.2f);
		kingToad1.setPack(enemyPack1);
		for(int i=0;i<4;i++)
		{
			Frog temp= new Follower((float)(toadX-50+Math.random()*100f),(float)(toadY-50+Math.random()*100),enemyPack1);
			entities.add(temp);
			enemyPack1.addFrog(temp);
			((Follower) temp).turnEvil();
		}
		stage=1;
	}
	private void bossTwo(float xPos, float yPos) {
		float toadX=xPos+100; float toadY=yPos+1500;
		KingToad kingToad2= new KingToad(xPos+1000,yPos+1000, enemyPack2);
		entities.add(kingToad2);
		enemyPack2= new Pack(kingToad2);
		enemyPack2.boostAll(1.2f);
		kingToad2.setPack(enemyPack2);
		for(int i=0;i<11;i++)
		{
			Frog temp= new Follower((float)(toadX-50+Math.random()*100f),(float)(toadY-50+Math.random()*100),enemyPack2);
			entities.add(temp);
			enemyPack2.addFrog(temp);
			((Follower) temp).turnEvil();
		}
		stage=2;
	}
	private void bossThree(float xPos, float yPos) {
		float toadX=xPos-1500; float toadY=yPos-100;
		KingToad kingToad3= new KingToad(xPos+1000,yPos+1000, enemyPack3);
		entities.add(kingToad3);
		enemyPack3= new Pack(kingToad3);
		enemyPack3.boostAll(1.2f);
		kingToad3.setPack(enemyPack3);
		for(int i=0;i<25;i++)
		{
			Frog temp= new Follower((float)(toadX-50+Math.random()*100f),(float)(toadY-50+Math.random()*100),enemyPack3);
			entities.add(temp);
			enemyPack3.addFrog(temp);
			((Follower) temp).turnEvil();
		}
		stage=3;
	}
	
//	private void setupPacks()
//	{
//		
//		KingToad kingToad1= new KingToad(-10000,-30000, enemyPack1);
//		KingToad kingToad2= new KingToad(15000,30000, enemyPack2);
//		KingToad kingToad3= new KingToad(20000,20000, enemyPack3);
//		entities.add(kingToad1); entities.add(kingToad2); entities.add(kingToad3);
//		enemyPack1= new Pack(kingToad1);
//		enemyPack1.boostAll(1.2f);
//		kingToad1.setPack(enemyPack1);
//		for(int i=0;i<10;i++)
//		{
//			Frog temp= new Follower(-10000+i*10,-30000,enemyPack1);
//			entities.add(temp);
//			enemyPack1.addFrog(temp);
//		}
//		enemyPack2= new Pack(kingToad2);
//		enemyPack2.boostAll(1.4f);
//		kingToad2.setPack(enemyPack2);
//		for(int i=0;i<20;i++)
//		{
//			Frog temp= new Follower(15000+i*10,30000,enemyPack2);
//			entities.add(temp);
//			enemyPack2.addFrog(temp);
//		}
//		enemyPack3= new Pack(kingToad3);
//		enemyPack3.boostAll(2.6f);
//		enemyPack3.boostAllAttack(10);
//		kingToad3.setPack(enemyPack3);
//		for(int i=0;i<30;i++)
//		{
//			Frog temp= new Follower(20000+i*10,20000,enemyPack3);
//			entities.add(temp);
//			enemyPack3.addFrog(temp);
//		}
//	}
	public static void createPool(float x, float y){
		Pool newPool = new Pool(x,y);
		pools.add(newPool);
		entities.add(newPool);
	}
	 public void zoom(float scale) {
	        zoomScale *= scale;
	    }
	 public void setZoom(float scale) {
	        zoomScale = scale;
	    }
}
