package environment;

import environment.biomes.Biome;
import environment.biomes.Grass;
import environment.biomes.Sand;
import environment.biomes.Snow;
import environment.biomes.Water;
import grouping.Pack;

import org.newdawn.slick.Graphics;

import core.Game;
import entities.alive.Follower;
import entities.alive.Frog;
import entities.alive.KingToad;
import entities.alive.Wanderer;
import entities.obstacles.Rock;


public class Tile {
    public final static float SCALE = .0025f;
    protected Biome biome;
    protected float initX;
    protected float initY;
    protected float x;
    protected float y;

    public Tile(float x, float y) {
    	int numFrogs=Game.bestFrog.getPack().getFrogs().size();
    	double rand = Math.random();
        this.x = x;
        this.y = y;
        float noiseValue = Map.getNoise().GetNoise(x * SCALE, y * SCALE);
        float noiseValueTwo = Map.getNoiseTwo().GetNoise(x*SCALE, y*SCALE);
        if (noiseValue < .2) {
        	if (noiseValueTwo<.35  ) {
        		setBiome(new Grass(noiseValue));
        	} else {
        		setBiome(new Sand(noiseValue));
        	}
        } else {
        	if(noiseValue<.3) {
        		setBiome(new Water(noiseValue));
        	} else {
        		setBiome(new Snow(noiseValue));
        	}
        }
        if(numFrogs<7) {
        	if(rand<.05) addWanderer();
        	else if (rand<.15) addObstacle();
        	else if (rand<.03) addPool();
        } else if (numFrogs<15) {
        	if(rand<.025) addWanderer();
        	else if (rand<.045) addObstacle();
        	else if (rand<.06) addPool();
        	else if (rand>.995) addToad();
        } else {
        	if(rand<.02) addWanderer();
        	else if (rand<.04) addPool();
        	else if (rand<.1) addObstacle();
        	else if (rand>.98) addWanderer();
        	else if (rand>.96) addToad();
        }
    }

    public void addWanderer() {
    	Wanderer w = new Wanderer(x,y);
    	Game.addEntity(w);
    	w.colorChange(biome);
    }

    public void addObstacle() {
    		Rock o = new Rock(x+(float)(Math.random()*Map.TILE_SIZE),y+(float)(Math.random()*Map.TILE_SIZE));
    		Game.addEntity(o);
        	o.colorChange(biome);	
    }
    public void addPool() {
    	Game.createPool(x+(float)(Math.random()*Map.TILE_SIZE),y+(float)(Math.random()*Map.TILE_SIZE));
    }

    public void addToad() {
    	KingToad k= new KingToad(x,y);
		Game.entities.add(k);
		Pack pack= new Pack(k);
		k.setPack(pack);
    }
    
//    public void addEnemyPack() {
//
//    	KingToad kingToad1= new KingToad(-10000,-30000);
//    	Pack p=new Pack(kingToad1);
//		Game.addEntity(kingToad1);
//		float boost = 60/Game.bestFrog.getPack().getAttackSpeed();
//		p.boostAll(boost);
//		kingToad1.setPack(p);
//		int enemyToadsNumber=Game.bestFrog.getPack().getFrogs().size()/2;
//		for(int i=0;i<enemyToadsNumber;i++)
//		{
//			Frog temp=new Follower((float)(Game.bestFrog.getX()+(2000*Math.random()-1000)),(float)( Game.bestFrog.getY()+(2000*Math.random()-1000)));
//			((Follower) temp).turnEvil();
////			Frog temp= new Follower(-10000+i*10,-30000,p);
//			Game.addEntity(temp);
//			p.addFrog(temp);
//		}
//    }

    public void update() {
        if (biome != null) biome.update();
    }

    public void render(Graphics g) {
        if (biome != null) biome.render(g);
    }

    public void setBiome(Biome b) {
        biome = b;
        b.setTile(this);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Biome getTerrain() {
        return biome;
    }
}