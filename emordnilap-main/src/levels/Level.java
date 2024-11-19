package levels;

import component.weapons.*;
import component.weapons.projectiles.Bullet;
import component.weapons.projectiles.Projectile;
import component.weapons.projectiles.Rocket;
import core.Game;
import core.Main;
import entities.Explosion;
import entities.enemy.Drone;
import entities.enemy.Frog;
import entities.enemy.TestEnemy;
import entities.enemy.Turret;
import entities.player.Ghost;
import entities.player.Player;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import world.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Level {
	// BASIC ELEMENTS OF A LEVEL
	protected ArrayList<Tile> tiles;
	protected ArrayList<TileSet> tilesets;
	protected ArrayList<MovingTile> movingTiles;
	protected ArrayList<Hazard> hazards;
	protected ArrayList<TestEnemy> testEnemies;
	protected ArrayList<Player> players;
	protected ArrayList<Ghost> ghosts;
	protected ArrayList<Gernade> gernadeList;
	protected ArrayList<Turret> turretList;
	protected ArrayList<Explosion> explosionList;
	protected static ArrayList<Bullet> bullets;
	protected ArrayList<Drone> droneList;
	protected ArrayList<SwordSwipe> swingList;

//	protected ArrayList<Spike> spikeList;
	protected ArrayList<Frog> frogList;
	protected GrenadeLauncher grenadeGun;
	protected ArrayList<Rocket> rocketList;
	protected Weapon curWeapon;
	protected RocketLauncher rocketGun;
	protected Revolver bulletGun;
	protected NullWeapon noGun;
	protected Sword swordGun;

	// KEY ELEMENTS
	protected Target target;
	protected Door door;
	public Camera cam;

	// SCANNING VARIABLES
	protected File levelOne;
	protected Scanner scan;
	ArrayList<String> lines;
	protected String object;

	// LEVEL PROGRESSION VARIABLES
	protected boolean levelOneCompleted = false;
	public int phase;
	public boolean flag;

	// GENERAL VARIABLES
	protected int frames;
	protected int time;
	protected int curLevel;
	public int id = 0;

	//BACKGROUND IMAGES
	protected Image background;
	protected Image backgroundOne;
	
	// COMPUTER MONITOR SIZE: 1920*1080

	public Level() {
	}

	public void init() throws SlickException {
		// INITIALIZING ALL COMPONENTS
		cam = new Camera();
		tiles = new ArrayList<>();
		tilesets = new ArrayList<>();
		movingTiles = new ArrayList<>();
		hazards = new ArrayList<>();
		testEnemies = new ArrayList<>();
		players = new ArrayList<>();
		ghosts = new ArrayList<>();
		gernadeList = new ArrayList<>();
		turretList = new ArrayList<>();
		bullets = new ArrayList<>();
		rocketList = new ArrayList<>();
		explosionList = new ArrayList<>();
		droneList = new ArrayList<>();
		swingList = new ArrayList<>();
//		spikeList = new ArrayList<>();
		frogList = new ArrayList<>();
		GrenadeLauncher.init();
		Explosion.init();
		Player.init();
		Target.init();
		Hazard.init();
		Drone.init();
//		Spike.init();
		Frog.init();
		Tile.init();
		MovingTile.init();
		Turret.init();
		GrenadeLauncher.init();
		Gernade.init();
		Revolver.init();
		NewTile.init();
		RocketLauncher.init();
		Rocket.init();
		Sword.init();
		SwordSwipe.init();
		Ghost.init();
		//IMAGE INIT
		backgroundOne=new Image("res/backgroundOne.png");
		// BASIC LEVEL VARS
		time = 0;
		// START LEVEL ONE (TEMP)
		levelOne = new File("res/LevelOne.txt");
//		startLevelOne(levelOne);
//		startLevelTwo();
		startLevelThree();
		// loadLevel(levelOne);
	}

	public void render(Graphics g) {
		// SIDESCROLLING
		g.translate(-cam.getX(), -cam.getY());
		g.drawImage(background, 0, 0);
		// RENDERING ALL COMPONENTS
		for (Tile t : tiles) {
			t.render(g);
		}
		for (TileSet t : tilesets) {
			t.render(g);
		}
		for (MovingTile m : movingTiles) {
			m.render(g);
		}
		for (Hazard h : hazards) {
			h.render(g);
		}
		for (Turret tr : turretList) {
			tr.render(g);
		}
		for (Player p : players) {
			p.render(g);
		}
		for (Drone dr : droneList) {
			dr.render(g);
		}
		for (int s = 0; s < swingList.size(); s++) {
			swingList.get(s).render(g);
		}
//		for(Spike s : spikeList) {
//			s.render(g);
//		}
		for(Frog f : frogList) {
			f.render(g);
		}
		for (Explosion explo : explosionList) {
			explo.render(g);
		}
//		for (Ghost p : ghosts) {
//			p.render(g);
//		}
		for (Gernade gr : gernadeList) {
			gr.render(g);
		}
		for (Bullet b : bullets) {
			b.render(g);
		}
		for (Rocket r : rocketList) {
			r.render(g);
		}
		curWeapon.render(g);
		target.render(g);
		door.render(g);
		// TIMER
		g.setColor(Color.white);
		g.setFont(Game.textFont);
		g.drawString(String.valueOf(time), Main.getScreenWidth(), 25);
		g.setColor(Color.red);
		g.drawString(String.valueOf(this.getPlayer().getDeaths()), Main.getScreenWidth(), 85);
	}

	public void update() {
		// TIMER
		frames++;
		if (frames % 60 == 0) {
			time++;
		}
		// UPDATING COMPONENTS
		cam.update();
		for (MovingTile m : movingTiles) {
			m.update();
		}
		for (Player p : players) {
			p.update(Game.delta, 0.2f);
		}
		for (int s = 0; s < swingList.size(); s++) {
			swingList.get(s).update();
		}
//		for(Ghost p : ghosts)
//		{
//			p.update();
//		}
		for(int i=0; i<rocketList.size(); i++)
		{
			rocketList.get(i).update();
		}
		for (Drone dr : droneList) {
			dr.update();
		}
//		for(Spike s : spikeList) {
//			s.update();
//		}
		for(Frog f : frogList) {
			f.update(Game.delta, 0.2f);
		}
		for(int i=0; i<explosionList.size(); i++)
		{
			explosionList.get(i).update();
		}
		curWeapon.update();
		// DELETE GRENADES AFTER TIME
		for (int g = 0; g < gernadeList.size(); g++) {
			Gernade cur = gernadeList.get(g);
			if (cur.getTimer() > 50) {
				gernadeList.get(g).explode();
				gernadeList.remove(g);
				g--;
			} else {
				cur.update(Game.delta, 0.1f);
			}
		}
		// DELETE BULLETS AFTER OUT OF BOUNDS
		for (int i = 0; i < bullets.size(); i++) {
			if ((bullets.get(i).getX() < 0 || bullets.get(i).getX() > Main.getScreenWidth() * 2
					|| bullets.get(i).getY() < 0 || bullets.get(i).getY() > Main.getScreenHeight()) || (bullets.get(i).isGhost() && bullets.get(i).getTimer() > 0)) {
				bullets.remove(i);
				i--;
			}
			else {
				bullets.get(i).update();
			}
		}

		for (int i = 0; i < rocketList.size(); i++) {
			if ((rocketList.get(i).getX() < 0 || rocketList.get(i).getX() > Main.getScreenWidth() * 2
					|| rocketList.get(i).getY() < 0 || rocketList.get(i).getY() > Main.getScreenHeight()) || (rocketList.get(i).isGhost() && rocketList.get(i).getTimer() > 1)) {
				rocketList.remove(i);
				i--;
			}
			else {
				rocketList.get(i).update();
			}
		}
		// PHASE CREATION
		if (flag) {
			switch (phase) {
			case 1:
				phaseOne(id);
				break;
			case 2:
				phaseTwo(id);
				break;
			case 3:
				phaseThree(id);
				break;
			}
			flag = false;
		}
	}

	// START LEVELS
	public void startLevelOne(File text) {
		// ALL FOR PHASE 0
		background=backgroundOne;
		// LOAD BOUNDARY TILESETS AND PLAYER
		loadBounds(250, 500);
		// CREATE KEY ELEMENTS
		target = new Target(Main.getScreenWidth() * 1.95f, Main.getScreenHeight() * .9f, 50, 50);
		door = new Door(Main.getScreenWidth() * .05f, Main.getScreenHeight() * .8f, 64, 64);
		// CREATE ALL TILESETS
		tilesets.add(new TileSet(0, Main.getScreenHeight() - 60, 20 * 32 - 1, 127));
		tilesets.add(new TileSet(30 * 32, Main.getScreenHeight() - 300, 20 * 32 - 1, 127));
		tilesets.add(new TileSet(60 * 32, Main.getScreenHeight() - 500, 20 * 32 - 1, 127));
		tilesets.add(new TileSet(90 * 32, Main.getScreenHeight() - 300, 20 * 32 - 1, 127));
		// CREATE ALL ENEMIES
//		frogList.add(new Frog(800,500));
//		frogList.add(new Frog(1000,500));
//		frogList.add(new Frog(900,500));
//		frogList.add(new Frog(100,500));
//		frogList.add(new Frog(2000,500));
		droneList.add(new Drone(500,500));
		turretList.add(new Turret((48 * 32), Main.getScreenHeight() - (300 - 127)));
		turretList.add(new Turret((70 * 32), 10));
		turretList.add(new Turret((108 * 32), Main.getScreenHeight() - 364));
	}

	public void startLevelTwo() {
		// ALL FOR PHASE 0
		background=backgroundOne;
		// LOAD BOUNDARY TILESETS AND PLAYER
		loadBounds(250, 500);
		// CREATE KEY ELEMENTS
		target = new Target(Main.getScreenWidth() * 1.95f, Main.getScreenHeight() * .9f, 50, 50);
		door = new Door(Main.getScreenWidth() * .05f, Main.getScreenHeight() * .8f, 64, 64);
		
		tilesets.add(new TileSet(0, Main.getScreenHeight() - 60, 15 * 32 - 1, 127));
		tilesets.add(new TileSet(550, Main.getScreenHeight() - 300, 5 * 32 - 1, 100));
		tilesets.add(new TileSet(700, Main.getScreenHeight() - 400, 5 * 32 - 1, 200));
		tilesets.add(new TileSet(850, Main.getScreenHeight() - 500, 5 * 32 - 1, 300));
		for(int i =0; i< 29; i++)
		{
			frogList.add(new Frog(900,500+ 16*i));
		}
		tilesets.add(new TileSet(1400, Main.getScreenHeight() - 600, 5 * 32 - 1, 900));
		tilesets.add(new TileSet(1600, Main.getScreenHeight() - 500, 10 * 32 - 1, 127));
		turretList.add(new Turret(1784, Main.getScreenHeight() - 375));
		tilesets.add(new TileSet(2200, Main.getScreenHeight() - 400, 10 * 32 - 1, 127));
		tilesets.add(new TileSet(1600, Main.getScreenHeight() - 200, 10 * 32 - 1, 127));
		tilesets.add(new TileSet(3000, Main.getScreenHeight() - 300, 5 * 32 - 1, 300));
		turretList.add(new Turret(3064, Main.getScreenHeight() - 475));
		tilesets.add(new TileSet(3000, Main.getScreenHeight() - 600, 5 * 32 - 1, 127));
	}
	
	public void startLevelThree() {
		// ALL FOR PHASE 0
		background=backgroundOne;
		// LOAD BOUNDARY TILESETS AND PLAYER
		loadBounds(100, 300);
		// CREATE KEY ELEMENTS
		target = new Target(Main.getScreenWidth() * 1.95f, Main.getScreenHeight() * .9f, 50, 50);
		door = new Door(70, Main.getScreenHeight() -660, 64, 64);
		
		tilesets.add(new TileSet(0, Main.getScreenHeight() - 600, 10 * 32 - 1, 600));
		tilesets.add(new TileSet(600, Main.getScreenHeight() - 600, 5 * 32 - 1, 200));
		
	}

	// PHASE CONTROLLER
	public void phaseOne(int level) {
		switch (level) {
		case 0:
			tilesets.add(new TileSet(50, Main.getScreenHeight()/2, 63, 63));
			
			turretList.add(new Turret(50,Main.getScreenHeight()/2+63));
		}
	}
	public void phaseTwo(int level) {
		switch (level) {
		case 0:
			tilesets.add(new TileSet(150, Main.getScreenHeight()/2, 63, 63));
			
			turretList.add(new Turret(150,Main.getScreenHeight()/2+63));
		}
	}
	public void phaseThree(int level) {
		switch (level) {
		case 0:
			tilesets.add(new TileSet(250, Main.getScreenHeight()/2, 63, 63));
			
			turretList.add(new Turret(250,Main.getScreenHeight()/2+63));
		}
	}

	// BASE LEVELS
	public void loadBounds(int playerX, int playerY) {
		players.add(new Player(playerX, playerY));
		grenadeGun = new GrenadeLauncher(Game.curLevel.getPlayer());
		rocketGun = new RocketLauncher(Game.curLevel.getPlayer());
		bulletGun = new Revolver(Game.curLevel.getPlayer());
		noGun = new NullWeapon (Game.curLevel.getPlayer());
		swordGun = new Sword(Game.curLevel.getPlayer());
		curWeapon = swordGun;
		TileSet floor = new TileSet(-50, Main.getScreenHeight() * .99f, 1983 * 2 + 1, 63);
		tilesets.add(floor);
		TileSet roof = new TileSet(-50, -53, 1983 * 2 + 1, 63);
		tilesets.add(roof);
		TileSet leftWall = new TileSet(-53, -50, 63, 1151);
		tilesets.add(leftWall);
		TileSet rightWall = new TileSet(Main.getScreenWidth() * 2 - 10, -50, 63, 1151);
		tilesets.add(rightWall);
	}

	public void loadEmptyLevel() {
		players.add(new Player(500, 500));
		grenadeGun = new GrenadeLauncher(Game.curLevel.getPlayer());
		rocketGun = new RocketLauncher(Game.curLevel.getPlayer());
		swordGun = new Sword(Game.curLevel.getPlayer());
		bulletGun = new Revolver(Game.curLevel.getPlayer());
		curWeapon = swordGun;
		Tile floor = new Tile(-50, Main.getScreenHeight() * .99f, Main.getScreenWidth() + 50, 50);
		tiles.add(floor);
		Tile roof = new Tile(-50, -50, Main.getScreenWidth() + 50, 60);
		tiles.add(roof);
		Tile leftWall = new Tile(-50, -50, 60, Main.getScreenHeight() + 50);
		tiles.add(leftWall);
		Tile rightWall = new Tile(Main.getScreenWidth() - 10, -50, 50, Main.getScreenHeight() + 50);
		tiles.add(rightWall);
	}

	// SCANNER
	public void loadLevel(File text) {
		try {
			scan = new Scanner(text);

			// Read first line
			String firstLine = scan.nextLine();
			int xIndex = firstLine.indexOf('x');
			if (xIndex == -1) {
				System.out.println("File Error1");
				loadEmptyLevel();
				return;
			} else {
				int LevelWidth = Integer.parseInt(firstLine.substring(0, xIndex));
				int LevelHeight = Integer.parseInt(firstLine.substring(xIndex + 1));
			}
			try {
				int sid = -1;
				while (scan.hasNextLine()) {
					String row = scan.nextLine();
					while (row.indexOf(" ") != -1) {
						String type = row.substring(0, 2);
						switch (type) {
						case "Ti":
							while (row.indexOf(" ") != -1) {

							}
						}
					}
				}
			} catch (Exception e) {
				System.out.println(e);
				System.out.println("File Error2");
				loadEmptyLevel();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("File Error3");
			e.printStackTrace();
		}
	}

	//SETTER GETTERS / MISC
	public void addTurret(Turret tr) {
		turretList.add(tr);
	}

	public void addGrenade(Gernade gr) {
		gernadeList.add(gr);
	}

	public void addDrone(Drone dr) {
		droneList.add(dr);
	}
//	public void addSpike(Spike s)
//	{
//		spikeList.add(s);
//	}
	
	public void addFrog(Frog f)
	{
		frogList.add(f);
	}
	public static void addBullet(Bullet b) {
		bullets.add(b);
	}

	public void addRocket(Rocket r) {
		rocketList.add(r);
	}
	public void addExplosion(Explosion explo) {
		explosionList.add(explo);
	}

	public Weapon getCurWeapon() {
		return curWeapon;
	}
	public void equipNull() {
		curWeapon= noGun;
	}

	public void equipPistol() {
		curWeapon = bulletGun;
	}
	public void equipSword() {
		curWeapon= swordGun;
	}

	public void equipRockets() {
		curWeapon = rocketGun;
	}

	public void equipGrenades() {
		curWeapon = grenadeGun;
	}

	public Revolver getRevolver() {
		return bulletGun;
	}

	public ArrayList<Tile> getTiles() {
		return tiles;
	}

	public ArrayList<TileSet> getTileSets() {
		return tilesets;
	}

	public ArrayList<MovingTile> getMovingTiles() {
		return movingTiles;
	}

	public ArrayList<Hazard> getHazards() {
		return hazards;
	}

	public ArrayList<TestEnemy> getTestEnemies() {
		return testEnemies;
	}

	public Player getPlayer() {
		return players.get(0);
	}

	public ArrayList<Gernade> getGernades() {
		return gernadeList;
	}
	public ArrayList<Frog> getFrogs() {
		return frogList;
	}
	public ArrayList<Drone> getDrones() {
		return droneList;
	}
	public ArrayList<Turret> getTurrets() {
		return turretList;
	}
	public ArrayList<Gernade> getLiveGernades() {
		ArrayList<Gernade> live = new ArrayList<>();
		for (Gernade g : gernadeList) {
			if (g.getTimer() > 20) {
				live.add(g);
			}
		}
		return live;
	}

	public Ghost getGhost() {
		return ghosts.get(0);
	}

	public Target getTarget() {
		return target;
	}

	public Door getDoor() {
		return door;
	}
	

	public boolean isLevelOneCompleted() {
		return levelOneCompleted;
	}

	public int getTurretCount() {
		return turretList.size();
	}

	public void setLevelOneCompleted() {
		levelOneCompleted = true;
	}

	public int getTime() {
		return time;
	}

	public void removeThisBullet(Projectile projectile) {
		for(int i=0; i<bullets.size(); i++)
		{
			if(bullets.get(i).equals(projectile))
			{
				bullets.remove(i);
				break;
			}
		}
		
	}
	public void removeThisTurret(Turret t) {
		for(int i=0; i<turretList.size(); i++)
		{
			if(turretList.get(i).equals(t))
			{
				turretList.remove(i);
				break;
			}
		}
		
	}
	public void removeThisFrog(Frog f) {
		for(int i=0; i<frogList.size(); i++)
		{
			if(frogList.get(i).equals(f))
			{
				frogList.remove(i);
				break;
			}
		}
		
	}
	public void removeThisDrone(Drone d) {
		for(int i=0; i<droneList.size(); i++)
		{
			if(droneList.get(i).equals(d))
			{
				droneList.remove(i);
				break;
			}
		}
		
	}

	public void removeThisExplosion(Explosion explo) {
		for(int i=0; i<explosionList.size(); i++)
		{
			if(explosionList.get(i).equals(explo))
			{
				explosionList.remove(i);
				break;
			}
		}
	}

	public void removeThisRocket(Rocket rocket) {
		for(int i=0; i<rocketList.size(); i++)
		{
			if(rocketList.get(i).equals(rocket))
			{
				rocketList.remove(i);
				break;
			}
		}
		
	}
	
	public ArrayList<Projectile> getProjectiles() {
		ArrayList<Projectile> all = new ArrayList(bullets);
		all.addAll(rocketList);
		return all;
	}

	public void addSwing(SwordSwipe ss) {
		swingList.add(ss);
	}

	public void removeThisSwing(SwordSwipe swordSwipe) {
		for(int i=0; i<swingList.size(); i++)
		{
			if(swingList.get(i).equals(swordSwipe))
			{
				swingList.remove(i);
				break;
			}
		}
	}

	public void nextLevel(){
		curLevel++;
	}
}
