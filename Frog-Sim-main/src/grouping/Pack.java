package grouping;

import java.util.ArrayList;

import core.Game;
import entities.alive.Animal;
import entities.alive.Follower;
import entities.alive.Frog;
import entities.alive.KingToad;
import entities.alive.PlayerFrog;
import entities.alive.Wanderer;

public class Pack {
	private ArrayList<Frog> frogs; 
	public Animal alphaFrog;
	private float moveDistance=200;
	private float moveTimer=25;
	private float regen;
	private float healthBonus;
	private float attackDamageBonus;
	private float attackSpeedBonus;
	public boolean battling;
	private Pack enemyPack;
	public Pack(Frog alpha)
	{
		alphaFrog=alpha;
		frogs= new ArrayList<Frog>();
		frogs.add(alpha);
		Game.packs.add(this);
	}
	public Pack(KingToad alpha)
	{
		alphaFrog=alpha;
		frogs= new ArrayList<Frog>();
		Game.packs.add(this);
//		frogs.add(alpha);
	}
	public void update()
	{
		
		attackCheck();
		
		
		if(battling)
		{
			if(alphaFrog instanceof KingToad)
			{
				((KingToad) alphaFrog).attackClosest();
			}
			for(Frog f: frogs)
			{
				if((f instanceof Follower))
				{
					((Follower) f).attackClosest();
					((Follower) f).moveToClosestEnemy(); 
				}
				
			}
		}
		else
		{
			if(!(alphaFrog instanceof PlayerFrog && ((PlayerFrog)alphaFrog).idle==true))
			{
				moveAll(alphaFrog.destinationPoint.getX(),alphaFrog.destinationPoint.getY());
			}
			
		}
	}
	private void attackCheck()
	{
		ArrayList<Pack> packs = Game.getPacks();
		float minDist=1200;
		Pack closestPack=null;
		for(Pack p: packs)
		{
			if(p==this)
			{
				continue;
			}
			if(alphaFrog.getDistance(p.alphaFrog)<1000)
			{
				if(alphaFrog.getDistance(p.alphaFrog)<minDist)
				{
					closestPack=p;
					minDist=alphaFrog.getDistance(p.alphaFrog);
				}
				
			}
		}
		if(minDist==1200)
		{
			battling=false;
			enemyPack=null;
		}
		else
		{
			battling=true;
			enemyPack=closestPack;
		}
		
	}
	public void modifyJumpDistance(float multi)
	{
		moveDistance*=multi;
		for(Frog f: frogs)
		{
			f.modifyJumpDistance(multi);
		}
		if(alphaFrog instanceof KingToad)
		{
			((KingToad) alphaFrog).modifyJumpDistance(multi);
		}
	}
	private void modifyRegen(float multi) {
		regen*=multi;
		for(Frog f: frogs)
		{
			f.modifyRegen(multi);
		}
		if(alphaFrog instanceof KingToad)
		{
			((KingToad) alphaFrog).modifyRegen(multi);
		}
	}
	public void disband()
	{
		for(int i=0; i<frogs.size(); i++)
		{
			Game.entities.add(new Wanderer(frogs.get(i).getX(),frogs.get(i).getY()));
			Game.entities.remove(frogs.get(i));
		}
		Game.packs.remove(this);
	}
	public void modifyHealth(float multi)
	{
		healthBonus*=multi;
		for(Frog f: frogs)
		{
			f.modifyHealth(multi);
		}
		if(alphaFrog instanceof KingToad)
		{
			((KingToad) alphaFrog).modifyHealth(multi);
		}
	}
	public void modifyAttackDamage(float multi)
	{
		attackDamageBonus*=multi;
		for(Frog f: frogs)
		{
			f.modifyAttackDamage(multi);
		}
		if(alphaFrog instanceof KingToad)
		{
			((KingToad) alphaFrog).modifyAttackDamage(multi);
		}
	}
	public void modifyAttackSpeed(float multi)
	{
		attackSpeedBonus*=multi;
		
		for(Frog f: frogs)
		{
			f.modifyAttackSpeed(multi);
		}
		if(alphaFrog instanceof KingToad)
		{
			((KingToad) alphaFrog).modifyAttackSpeed(multi);
		}
	}
	public void modifyJumpTimer(float multi)
	{
		moveTimer*=multi;
		for(Frog f: frogs)
		{
			f.modifyJumpTimer(multi);
		}
		if(alphaFrog instanceof KingToad)
		{
			((KingToad) alphaFrog).modifyJumpTimer(multi);
		}
	}
	public void boostAll(float multi)
	{
		modifyAttackDamage(multi);
		modifyAttackSpeed(1/multi);
		modifyHealth(multi);
		modifyJumpDistance(multi);
		modifyJumpTimer(1/multi);
		modifyRegen(multi);
	}
	public void boostAllAttack(float multi)
	{
		modifyAttackDamage(multi);
		modifyAttackSpeed(1/multi);
	}
	public void randomBoost(float multi)
	{
		double rand=Math.random();
		if(rand<0.15)
		{
			modifyAttackDamage(multi);
		}
		else if(rand<0.3)
		{
			modifyAttackSpeed(1/multi);
		}
		else if(rand<0.45)
		{
			modifyHealth(multi);
		}
		else if(rand<0.6)
		{
			modifyJumpDistance(multi);
		}
		else if(rand<0.75)
		{
			modifyJumpTimer(1/multi);
		}
		else if(rand<0.9)
		{
			modifyRegen(multi);
		}	
	}
	
	public int getOrbital() {
		for(int i=1; i<20; i++)
		{
			if(frogs.size()<4*Math.pow(3, i)-1)
			{
				return i;
			}
				
		}
		return 1;
	}
	public void moveAll(float angle)
	{
		for(Frog f: frogs)
		{
			if(f!=alphaFrog)
			{
				f.startJump(angle);
			}
			
		}
	}
	public void moveAll(float x,float y)
	{
		for(Frog f: frogs)
		{
			if(f!=alphaFrog)
			{
				f.startJump(x,y);
			}
			
		}
	}
	public void addFrog(Frog f)
	{
		frogs.add(f);
	}
	public float getJumpDist()
	{
		return moveDistance;
	}
	public float getJumpTimer()
	{
		return moveTimer;
	}
	public ArrayList<Frog> getFrogs()
	{
		return frogs;
	}
	public Pack getEnemyPack()
	{
		return enemyPack;
	}
	public void allAttackClosest()
	{
		for(Frog f: frogs)
		{
			if((f instanceof Follower))
			{
				((Follower) f).attackClosest();
				((Follower) f).moveToClosestEnemy(); 
			}
			
		}
	}
	public float getAttackSpeed() {
		return attackSpeedBonus;
	}
}
