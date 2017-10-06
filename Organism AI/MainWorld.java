import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Main GameState
 *
 * @author Uzair Ahmed
 * @version 0.1
 */
public class MainWorld extends World {

  //VARIABLES ---------------------------------------------------------
  static public ArrayList<Actor>  lifeForms = new ArrayList<Actor>();
  static public ArrayList<Object> food;
  static public ArrayList<Object> myOrgansims;
  static public ArrayList<Object> enemyOrganisms;

  static int startingMaxHealth = 10;
  static int startingMaxXp = 10;
  static int startingSpeed = 5;
  static int startingAttackPower = 1;
  static int startingDefensePower = 1;
  static int startingSight = 100;

  static int maxBuyableMaxHealth = 100;
  static int maxBuyableMaxXp = 100;
  static int maxBuyableSpeed = 10;
  static int maxBuyableAtt = 10;
  static int maxBuyableDef = 10;
  static int maxBuyableSight = 10;

  //ACTUAL CODE--------------------------------------------------------
  IntroScreen intro;
  MyOrganism player;

  public MainWorld() {

    super(500,500, 1);
    intro = new IntroScreen(this);
    Greenfoot.setWorld(intro);

    setPaintOrder(Actor.class);

    player = new MyOrganism(startingMaxHealth, startingMaxXp, startingSpeed,
                            startingAttackPower, startingDefensePower, startingSight);

    addObject(player, 250, 250);

    for (int i = 0; i < 10; i++) {
      addObject(new Food(), Greenfoot.getRandomNumber(getWidth()), Greenfoot.getRandomNumber(getHeight()));
    }
  }
}
