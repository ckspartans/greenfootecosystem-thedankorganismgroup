import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Main GameState
 *
 * CHANGELOG: Change Class tree, now we have four organisms, and they will reproduce.
 *
 * @author Uzair Ahmed
 * @version 0.7
 */

public class MainWorld extends World {

    //VARIABLES ---------------------------------------------------------
    static public ArrayList<ArrayList> lifeForms;
    static public ArrayList<Organism> team1;
    static public ArrayList<Organism> team2;
    static public ArrayList<Organism> team3;
    static public ArrayList<Organism> team4;

    static int startingMaxHealth = 10;
    static int startingMaxXp = 10;
    static int startingSpeed = 3;
    static int startingAttackPower = 1;
    static int startingDefensePower = 1;
    static int startingSight = 100;

    static int maxBuyableMaxHealth = 100;
    static int maxBuyableMaxXp = 100;
    static int maxBuyableSpeed = 30;
    static int maxBuyableAtt = 10;
    static int maxBuyableDef = 10;
    static int maxBuyableSight = 10;

    static int startingFood = 25;

    //ACTUAL CODE--------------------------------------------------------
    IntroScreen intro;
    UIBack UI;
    PauseButton pauseButton;
    PauseWorld pause;
    Organism player1;
    Organism player2;
    Organism player3;
    Organism player4;

    public MainWorld() {

        super(1920,1080, 1);

        intro = new IntroScreen(this);
        pause = new PauseWorld();
        Greenfoot.setWorld(intro);
        setPaintOrder(Actor.class);

        team1 = new ArrayList<Organism>();
        team2 = new ArrayList<Organism>();
        team3 = new ArrayList<Organism>();
        team4 = new ArrayList<Organism>();
        lifeForms = new ArrayList<ArrayList>();
        lifeForms.add(team1);
        lifeForms.add(team2);
        lifeForms.add(team3);
        lifeForms.add(team4);

        player1 = new Organism(startingMaxHealth, startingMaxXp, startingSpeed,
            startingAttackPower, startingDefensePower, startingSight, 1 , Color.RED);

        player2 = new Organism(startingMaxHealth, startingMaxXp, startingSpeed,
            startingAttackPower, startingDefensePower, startingSight, 2, Color.BLUE);

        player3 = new Organism(startingMaxHealth, startingMaxXp, startingSpeed,
            startingAttackPower, startingDefensePower, startingSight, 3, Color.GREEN);

        player4 = new Organism(startingMaxHealth, startingMaxXp, startingSpeed,
            startingAttackPower, startingDefensePower, startingSight, 4, Color.YELLOW);

        addObject(player1, 0, 0);
        addObject(player2, 1000, 0);
        addObject(player3, 0, 1000);
        addObject(player4, 1000, 1000);

        openUI();

        for (int i = 0; i < startingFood; i++) {
            addObject(new Food(), Greenfoot.getRandomNumber(getWidth()), Greenfoot.getRandomNumber(getHeight()));
        }

    }

    public void act(){
        if (Greenfoot.getRandomNumber(200)<10){
            addObject(new Food(), Greenfoot.getRandomNumber(getWidth()), Greenfoot.getRandomNumber(getHeight()));
        }
    }

    public void openUI() {
        UI = new UIBack(); // creates a reference to draw the back of the UI
        pauseButton = new PauseButton(this, pause, 100,100);
        addObject(UI, 1920 - UI.width/2, 0 + UI.height/2); // spawning the ui back at the right hand side of the screen
        addObject(pauseButton, UI.getX(),UI.getY());
    }

}