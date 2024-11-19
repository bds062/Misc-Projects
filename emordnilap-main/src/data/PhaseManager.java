package data;

import component.weapons.projectiles.Bullet;
import component.weapons.projectiles.Projectile;
import component.weapons.projectiles.Rocket;
import core.Game;
import entities.player.Ghost;
import org.newdawn.slick.Graphics;
import java.util.ArrayList;

public class PhaseManager {
    private ArrayList<FrameData> frames;
    private Ghost ghost;

    private int time;
    private int maxTime;

    private boolean inverse;
    private boolean repeated;
    private boolean cycled;

    public PhaseManager() {
        init();
    }

    public void init(){
        frames = new ArrayList<FrameData>();
        ghost = new Ghost((int) Game.curLevel.getTarget().getLTX(), (int) Game.curLevel.getTarget().getLTY());
    }

    public void update(Graphics g) {
        if (inverse){
            if (!repeated){
                maxTime = time;
                cycled = false;
            }
            if (time <= 0){
                time = maxTime;
                cycled = true;
            }

            time--;

            if (!cycled){
                for (Snapshot p : frames.get(time).getProjectileSnap()){
                    if (p.getID() == 1){
                        //g.drawRect(p.getX(), p.getY(), 10, 10);
                        Game.curLevel.addBullet(new Bullet(p.getX(), p.getY(), p.gettX(), p.gettY()));
                    }
                    else if (p.getID() == 2){
                        Game.curLevel.addRocket(new Rocket(p.getX(), p.getY(), p.getFrame(), p.getAngle()));
                    }
                }

                ghost.setX(frames.get(time).getPlayerSnap().getX());
                ghost.setY(frames.get(time).getPlayerSnap().getY());

                ghost.render(g);
            }

            repeated = true;
        }
        else {
            FrameData curFrame =  new FrameData(time);
            curFrame.init();
            curFrame.addPlayer(Game.curLevel.getPlayer());
            if (!Game.curLevel.getProjectiles().isEmpty()) {
                for (Projectile p : Game.curLevel.getProjectiles()) {
                    curFrame.add(p);
                }
            }

            frames.add(curFrame);
            time++;
        }
    }

    public boolean isInverse(){
        return inverse;
    }

    public void setInverse(){
        inverse = true;
    }

    public void setNormal(){
        inverse = false;
    }

    public void phaseSwitch(){
        time = maxTime;
        cycled = false;
    }

    public void fullReset(){
        frames = null;
        repeated = false;
        cycled = false;

        time = 0;

        init();
    }
}
