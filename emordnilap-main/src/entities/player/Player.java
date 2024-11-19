package entities.player;

import core.Game;
import data.InversionManager;
import entities.Entity;
import org.newdawn.slick.*;

public class Player extends Entity {

    private static final float xMaxSpeed = 10f;
    private static final float friction = 0.92f;
    private static final float airResistance = 0.99f;
    private static final double jumpPower = 25;

    protected enum State {FALLING, JUMPING, STANDING, RUNNING}

    protected State curState;
    protected State prevState;

    private final double initX;
    private final double initY;

    public static int elapsedTime;

    protected boolean batman;


    private static Image soldierImage;
    private static SpriteSheet soldierSheet;
    private static Image batmanImage;
    private int sheetTick;
    private int tile;

    protected boolean atTarget;

    private static int maxJumps;
    private int jumpsLeft;
    private int deathCount;

    public static void init() throws SlickException {
        soldierImage = new Image("res/player.png");
        batmanImage = new Image("res/player.png");
        soldierSheet = new SpriteSheet("res/soldgierRun.png",32,64);
    }

    public Player(float x, float y) {
        super(x, y, 60, 120);
        initX = x;
        initY = y;
        curState = State.STANDING;
        prevState = State.STANDING;
        elapsedTime = 0;
        batman = false;
        sheetTick = 5;
        tile= 0;
    }

    public int currentFrame;
    public static final int TICK = 1;
    private boolean repeated;

    public void render(Graphics g) {
    	sheetTick++;
    	if(sheetTick ==10){
    		sheetTick = 0;
    		tile++;
    	}
    	if(tile>6) {
    		tile=0;
    	}
    	soldierImage = soldierSheet.getSprite(tile, 0);
        if (!Game.isInversed()) {
            g.setColor(Color.red);
        } else {
            g.setColor(Color.blue);
        }
//        if(batman)
//        {
//        	batmanImage.draw(xPos, yPos);
//        }
//        else
//        {
//        	soldierImage.draw(xPos,yPos);
//        }

//        g.drawImage(playerImage, xPos, yPos);
//        g.fillRect(xPos, yPos, width, height);
       	g.drawImage(soldierImage, xPos, yPos);
    }

    public void update(float deltaTime, float gravity) {
        batman = Game.isInversed();
        if (batman) {
            maxJumps = 4;
        } else {
            maxJumps = 1;
        }
        if (Game.input.isKeyPressed(Input.KEY_W)) {//&& grounded){
            if (grounded) {
                jumpsLeft = maxJumps;
            }
            if (jumpsLeft > 0) {
                jumpsLeft--;
                yVel -= jumpPower;
            }
        }
        if(Game.input.isKeyPressed(Input.KEY_C))
        {
            crouch();
        }
        
        if (Game.input.isKeyDown(Input.KEY_A)) {
//            if (Math.abs(xVel) < xMaxSpeed) {
                if (batman) {
                    xVel = -xMaxSpeed;
                } else {
//                    xVel = -xAccel;
                	 xVel = -xMaxSpeed;
                }
//            }
        }
        if (Game.input.isKeyDown(Input.KEY_D)) {
//            if (Math.abs(xVel) < xMaxSpeed) {
                if (batman) {
                    xVel = xMaxSpeed;
                } else {
//                    xVel = xAccel;
                	xVel = xMaxSpeed;
                }
//            }
        }
        if (batman && !Game.input.isKeyDown(Input.KEY_D) && !Game.input.isKeyDown(Input.KEY_A)) {
            xVel *= friction * 0.98;
        }

        if (grounded) {
            xVel *= friction;
        }
        xVel *= airResistance;

        super.update(deltaTime, gravity);
        moveX(xVel);
        if (batman) {
            moveX((float) (xVel * 0.8));
        }
//        if (collideWithGhost(xPos, yPos, width, height)) {
//        	killed();
//        }
        if (hitTarget(xPos, yPos, width, height) && !atTarget) {
                atTarget = true;
                Game.setInversion();
                Game.incInversion();
                Game.incPhase();
                Game.openDoor();
                InversionManager.nextPhase();
        }
        else if (hitDoor(xPos, yPos, width, height) && Game.doorIsOpen()) {
                atTarget = false;
                Game.incInversion();
                Game.incPhase();
                Game.closeDoor();
                Game.setNormal();
                InversionManager.nextPhase();

                Game.curLevel.nextLevel();
        }
    }

    private void crouch() {
        int temp=this.getWidth();
        this.width=this.getHeight();
        this.height=temp;
        this.xPos=this.xPos-(this.width-this.height)/2;
        this.yPos=this.yPos-(this.height-this.width);
    } 
    public void killed() {
        if (atTarget) {
            this.xPos = Game.curLevel.getTarget().getLTX() - this.width / 2;
            this.yPos = Game.curLevel.getTarget().getLTY() - this.height / 2;
        } else {
            this.xPos = Game.curLevel.getDoor().getLTX() - this.width / 2;
            this.yPos = Game.curLevel.getDoor().getLTY() - this.height / 2;
        }

        Game.curLevel.removeBullets();
        InversionManager.reset();

        deathCount++;
    }

    public void reset(){
        atTarget = false;
        batman = false;
        Game.setNormal();
    }

    public int getDeaths() {
        return deathCount;
    }
}