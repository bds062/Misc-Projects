package data;

import core.Game;
import org.newdawn.slick.Graphics;

public final class InversionManager {
	private static PhaseManager phaseOne;
    private static PhaseManager phaseTwo;
    private static PhaseManager phaseThree;
    private static PhaseManager phaseFour;
    private static PhaseManager phaseFive;

    private InversionManager(){

    }

    public static void init(){
        phaseOne = new PhaseManager();
        phaseTwo = new PhaseManager();
        phaseThree = new PhaseManager();
        phaseFour = new PhaseManager();
        phaseFive = new PhaseManager();
    }

    public static void update(Graphics g){
        phaseOne.update(g);
        if (Game.inversionCount >= 1){
            phaseTwo.update(g);
        }
        if (Game.inversionCount >= 2){
            phaseThree.update(g);
        }
        if (Game.inversionCount >= 3){
            phaseFour.update(g);
        }
        if (Game.inversionCount >= 4){
            phaseFive.update(g);
        }

    }

    public static void nextPhase(){
        if (!phaseOne.isInverse()){
            phaseOne.setInverse();
        }
        else {
            phaseOne.phaseSwitch();
            if (!phaseTwo.isInverse()){
                phaseTwo.setInverse();
            }
            else {
                phaseTwo.phaseSwitch();
                if (!phaseThree.isInverse()){
                    phaseThree.setInverse();
                }
                else {
                    phaseThree.phaseSwitch();
                    if (!phaseFour.isInverse()){
                        phaseFour.setInverse();
                    }
                    else {
                        phaseFour.phaseSwitch();
                        if (!phaseFive.isInverse()){
                            phaseFive.setInverse();
                        }
                    }
                }
            }
        }
    }
}