package environment.biomes;

import org.newdawn.slick.Graphics;

import environment.Tile;


public abstract class Biome {
	public final static float BIOME_SCALE = .0025f;
	protected Tile tile;
	protected float noiseValue;
	protected float bigNoiseValue;
	protected double rand = Math.random();
	public Biome(float noiseValue)
	{
		this.noiseValue=noiseValue;
		bigNoiseValue=calculateBigNoiseValue(noiseValue);
	}

	public void setTile(Tile t)
	{
		tile = t;
	}

	abstract public void update();
	abstract public void render(Graphics g);
	public float calculateBigNoiseValue (float value) {
		if(Math.abs(value)>10) return value;
		else return calculateBigNoiseValue(10*value);
	}
}