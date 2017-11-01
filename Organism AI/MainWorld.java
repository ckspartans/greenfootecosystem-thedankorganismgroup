import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Main GameState
 *
 * @author Class
 * @author Uzair Ahmed
 * @author Cameron Dickie
 * @author Laura Balofsky
 * @version 1.3
 */

public class MainWorld extends World {

    //VARIABLES ---------------------------------------------------------
    static int startingMaxHealth = 100;
    static int startingMaxXp = 5;
    static int startingSpeed = 3;
    static int startingAttackPower = 1;
    static int startingDefensePower = 1;
    static int startingSight = 100;

    static int maxBuyableMaxHealth = 1000;
    static int maxBuyableMaxXp = 100;
    static int maxBuyableSpeed = 30;
    static int maxBuyableAtt = 10;
    static int maxBuyableDef = 10;
    static int maxBuyableSight = 1000;

    static int startingFood = 50;

    //ACTUAL CODE--------------------------------------------------------

    //Initializes classes
    IntroScreen intro;

    UIBack UI;
    Button pauseButton;
    PauseWorld pause;

    //Declares players
    Organism player1;
    Organism player2;
    Organism player3;
    Organism player4;

    //Declares Families
    Family fam1;
    Family fam2;
    Family fam3;
    Family fam4;

    public MainWorld() {
        //Creates new world of the size given
        super(1920,1080, 1);

        //Instantiates other world classes
        intro = new IntroScreen(this);
        pause = new PauseWorld(this);

        //Sets the world to Introscreen
        Greenfoot.setWorld(intro);

        //Sets paint order
        setPaintOrder(Actor.class);

        //Instantiates Families
        fam1 = new Family();
        fam2 = new Family();
        fam3 = new Family();
        fam4 = new Family();
        //Instantiates players
        player1 = new Organism(startingMaxHealth, startingMaxXp, startingSpeed, startingAttackPower, startingDefensePower, startingSight, fam1 , Color.RED);
        player2 = new Organism(startingMaxHealth, startingMaxXp, startingSpeed, startingAttackPower, startingDefensePower, startingSight, fam2, Color.BLUE);
        player3 = new Organism(startingMaxHealth, startingMaxXp, startingSpeed, startingAttackPower, startingDefensePower, startingSight, fam3, Color.ORANGE);
        player4 = new Organism(startingMaxHealth, startingMaxXp, startingSpeed, startingAttackPower, startingDefensePower, startingSight, fam4, Color.YELLOW);

        //Adds player to world
        addObject(player1, 200, 200);
        addObject(player2, 800, 200);
        addObject(player3, 200, 800);
        addObject(player4, 800, 800);

        //Adds the family to the world
        addObject(fam1, 10,10);
        addObject(fam2, 990,10);
        addObject(fam3, 10,990);
        addObject(fam4, 990,990);

        //Runs UI method
        openUI();

        // Spawns the initial food
        for (int i = 0; i < startingFood; i++) {
            addObject(new Food(false), Greenfoot.getRandomNumber(1000), Greenfoot.getRandomNumber(1000));
        }
    }

    public void checkButtons() {
        if(pauseButton.active) {
            Greenfoot.setWorld(pause);
            pauseButton.setActive(false);
        }
    }

    public void openUI() {
        UI = new UIBack(); // creates a reference to draw the back of the UI
        pauseButton = new Button(200,100, this);
        addObject(UI, 1920 - UI.width/2, 0 + UI.height/2); // spawning the ui back at the right hand side of the screen
        addObject(pauseButton, UI.getX() -250,UI.getY() - 400);
    }

    public void act() {
        checkButtons();
        //10% chance of new food being spawned
    }

}
