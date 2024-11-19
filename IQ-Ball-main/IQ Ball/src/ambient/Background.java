package ambient;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Background {
	private int step = 1;
	private int frames = 0;
	private int framesPerStep = 6;
	
	public static void init() throws SlickException {
		
	}
	Background () {
		
	}
	void update() {	
		
		 frames++;
		 if(frames % framesPerStep == 0){
		     step++;
		  }
		 if (step>31) {
			 step=1;
		 }
	}
	void render(Graphics g) {
	}
}
