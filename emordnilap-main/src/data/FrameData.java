package data;

import java.util.ArrayList;

import component.weapons.projectiles.Projectile;
import entities.player.Player;

public class FrameData {
	private ArrayList<Snapshot> projectiles;
	private Snapshot player;
	private int time;
	
	public FrameData(int time) {
		this.time = time;
	}

	public void init(){
		projectiles = new ArrayList<>();
	}
	
	public void add(Projectile p){
		projectiles.add(new Snapshot(p));
	}

	public void add(){

	}

	public void addPlayer(Player p) {
		player = new Snapshot(p);
	}
	
	public ArrayList<Snapshot> getProjectileSnap(){
		return projectiles;
	}
	
	public Snapshot getPlayerSnap() {
		return player;
	}
	
	public int time() {
		return time;
	}
}