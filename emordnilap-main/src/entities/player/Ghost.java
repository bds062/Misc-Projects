package entities.player;

import data.Snapshot;
import entities.Entity;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import java.util.ArrayList;

public class Ghost extends Entity {

    protected enum State {FALLING, JUMPING, STANDING, RUNNING}
    protected State curState;
    protected State prevState;

    private double initX;
    private double initY;

    private float curX;
    private float curY;

    private ArrayList<Snapshot> snap;

    private static Ghost single_instance = null;

    int elapsedTime;

    private static Image ghostImage;

    public static void init() {
        try {
            ghostImage = new Image("res/TacoPNG.png");
        } catch (SlickException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public Ghost(ArrayList<Snapshot> snap, int curFrame){
        super(snap.get(snap.size()-1).getX(), snap.get(snap.size()-1).getY(), ghostImage.getWidth(), ghostImage.getHeight());
        initX = snap.get(snap.size()-1).getX();
        initY = snap.get(snap.size()-1).getY();
        curState = State.STANDING;
        prevState = State.STANDING;
        elapsedTime = 0;

        this.snap = snap;
        currentFrame = curFrame;
    }

    public Ghost(int x, int y){
        super(x, y, ghostImage.getWidth(), ghostImage.getHeight());
        initX = x;
        initY = y;
        curState = State.STANDING;
        prevState = State.STANDING;
        elapsedTime = 0;
    }

    public int currentFrame;
    public int totalFrames;
    public boolean repeated;

    public void render(Graphics g) {
//        if (!repeated) {
//            totalFrames = currentFrame;
//            repeated = true;
//        }
//        else if (currentFrame <= 0){
//            currentFrame = totalFrames;
//        }
//
//        Snapshot s = snap.get(currentFrame-1);
//
//        if (s.getTime() == currentFrame) {
//            g.drawImage(ghostImage, s.getX(), s.getY());
//        }
//        if (Player.elapsedTime% Player.TICK == 0) {
//            currentFrame--;
//        }
        g.drawImage(ghostImage, curX, curY);
    }


    public void update(float deltaTime, float gravity) {

    }

    public void setX(int x){
        curX = x;
    }

    public void setY(int y){
        curY = y;
    }
}