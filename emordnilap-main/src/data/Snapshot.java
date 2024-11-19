package data;

import component.weapons.projectiles.Projectile;
import entities.player.Player;

public class Snapshot {
	private int x, y;
	private int tX, tY;
	private int time;

	private int frame;
	private int angle;

	private int id;
	// 0: NULL 1: BULLET 2: ROCKET 3: GRENADE
	
	public Snapshot(int time, int x, int y) {
		this.time = time;
		this.x = x;
		this.y = y;
	}

	public Snapshot(int time, int x, int y, int frame){
		this.time = time;
		this.x = x;
		this.y = y;
		this.frame = frame;
	}

	public Snapshot(int time, int x, int y, int frame, int angle) {
		this.time = time;
		this.x = x;
		this.y = y;
		this.frame = frame;
		this.angle = angle;
	}
	
	public Snapshot(Player p) {
		this.x = (int) p.getX();
		this.y = (int) p.getY();
	}
	
	public Snapshot(Projectile p) {
		this.x = (int) p.getX();
		this.y = (int) p.getY();
		this.tX = (int) p.getTarX();
		this.tY = (int) p.getTarY();
		this.frame = p.getFrame();
		this.angle = (int) p.getAngle();
		this.id = p.getID();
	}

	public Snapshot() {
		this.id = 0;
	}
	
	public int getTime() {
		return time;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public int getFrame() {
		return frame;
	}
	
	public int getAngle() {
		return angle;
	}

	public int getID(){
		return id;
	}

	public int gettX(){
		return tX;
	}

	public int gettY(){
		return tY;
	}
}