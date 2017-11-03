import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Main GameState
 *
 * @author Uzair Ahmed
 * @author Cameron Dickie
 * @author Laura Balofsky
 * @version 2.0
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

    //Declares Families
    Family fam1;
    Family fam2;
    Family fam3;
    Family fam4;

    ArrayList fams;

    public MainWorld() {
        //Creates new world of the size given
        super(1920,1080, 1);

        //Instantiates other world classes
        intro = new IntroScreen(this);
        pause = new PauseWorld(this);

        //Sets the world to Introscreen
        Greenfoot.setWorld(intro);

        //Sets paint order
        setPaintOrder(Button.class, Family.class, UIBack.class, Parasite.class, Organism.class, Food.class);

        //Creates Families, and spawns organisms and food
        spawn();

        //Runs UI method
        openUI();
    }

    public void act() {
        checkButtons();
        if (gameOver()){
            System.out.println("GAMEOVER");
        }
    }

    public void spawn(){
        //Uzair Ahmed

        //Instantiates Families and list
        fam1 = new Family(Color.RED);
        fam2 = new Family(Color.BLUE);
        fam3 = new Family(Color.GREEN);
        fam4 = new Family(Color.YELLOW);

        fams = new ArrayList<Family>();

        //Adds the family to the world
        addObject(fam1, 1780,1060);
        addObject(fam2, 1820,1060);
        addObject(fam3, 1860,1060);
        addObject(fam4, 1900,1060);

        fams.add(fam1);fams.add(fam2);fams.add(fam3);fams.add(fam4);

        spawnOrganism(fam1, 200, 200);
        spawnOrganism(fam2, 880, 200);
        spawnOrganism(fam3, 200, 880);
        spawnOrganism(fam4, 880, 880);       

        // Spawns the initial food
        for (int i = 0; i < startingFood; i++) {
            spawnFood();
        }
    }

    public void spawnOrganism(Family fam, int x, int y){
        //Uzair Ahmed
        //Spawns an organism

        Organism o = new Organism(startingMaxHealth, startingMaxXp, startingSpeed, startingAttackPower, startingDefensePower, startingSight, fam);
        addObject(o, x, y);
    }

    public void spawnFood(){
        // Uzair Ahmed
        // Spawns food

        addObject(new Food(false), Greenfoot.getRandomNumber(1080), Greenfoot.getRandomNumber(1080));
    }

    public void checkButtons() {
        //Cameron Dickie

        if(pauseButton.active) {
            Greenfoot.setWorld(pause);
            pauseButton.setActive(false);
        }
    }

    public void openUI() {
        //Cameron Dickie

        UI = new UIBack(); // creates a reference to draw the back of the UI
        pauseButton = new Button(200,100, this);
        addObject(UI, 1920 - UI.width/2, 0 + UI.height/2); // spawning the ui back at the right hand side of the screen
        addObject(pauseButton, UI.getX() -250,UI.getY() - 400);
    }

    public boolean gameOver(){
        //Uzair Ahmed
        //checks when theres only one family left

        Family fam;
        //goes through each family
        for(int i = 0; i < fams.size(); i++){
            fam = (Family) fams.get(i);
            //checks if the size is zero
            if (fam.familyList.size() == 0){
                //removes family from world and list
                removeObject(fam);
                fams.remove(fam);
            }
        }

        //if the list size is 1 return true
        if (fams.size() == 1){
            return true;
        }
        return false;

    }
}
