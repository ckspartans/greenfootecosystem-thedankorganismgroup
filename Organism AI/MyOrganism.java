import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Extends off AbstOrganism to create a basic organism that reproduces, and moves around the screen.
 *
 * CHANGELOG September 30, 2017
 *      - Added variables to complement that of AbstOrganism 0.0.5
 *
 * @author Uzair Ahmed
 * @version 0.4
 */

public class MyOrganism extends AbstOrganism {

  public MyOrganism() {

    // Custom Variables


    //XP Upgradeable Variables
    int maxHealth = gvars.startingMaxHealth; //Maximum Health
    int maxXp = gvars.startingMaxXp; //Max XP Storage
    int speed = gvars.startingSpeed; //Nuff Said
    int att = gvars.startingAttackPower; //Attack power
    int def = gvars.startingDefensePower; //Defensive power
    int sight = gvars.startingSight; //Sight

    //"Current" Variables. (These are live variables that are constantly changing.
    int age = 0; //Time
    int health = gvars.startingMaxHealth; //Out of maxHealth
    int xp = 0; //Out of maxXp
    int radius = health; //Same as value from health
    Color playerFill = new Color(0, 0, 0); //Same as value mapped to 255.
    Color playerBorder = new Color(0, 0, 255);
    //int organismX = 0;
    //int organismY = 0;

    MainWorld world;
  }

  public void act() {

    moveAround();
    //drawRadius(35,35,500);
    //consumeFood();
    //consumePlayer();
    //reproduce();
    //age();
  }

  public void moveAround() {
    List foodNearby = getObjectsInRange(100, Actor.class);
    if ((foodNearby.size()) > 0){
      track(foodNearby);
      move(gvars.startingSpeed+5);
    }
    else{
      move(gvars.startingSpeed);
      if (Greenfoot.getRandomNumber(100) < 25){
        turn(Greenfoot.getRandomNumber(90)-45);
      }
    }
    removeTouching(Food.class);
    if (isAtEdge()){
      turn(180);
    }
  }

  public void track(List l){
    Food nearest = (Food) l.get(0);
    turnTowards(nearest.getX(),nearest.getY());
  }

  public static void drawRadius(int x,int y,int sight){
    GreenfootImage img = new GreenfootImage(sight,sight);
    img.setColor(new Color(0,0,0));
    img.drawOval(x,y,sight,sight);
  }

  public void consumeFood() {
    if (getOneIntersectingObject(Food.class) != null) {

    }

  }

  public void consumePlayer() {
      //
  }

  public void shrink(int damageTaken) {
    //takes damagetaken and applys it to health and radius
  }

  public void grow(int damageGiven) {
    //takes damageGiven and applys it to health and radius
  }

  public void age() {
    //start a timer in act() and use that value to edit age value, and color
  }

  public void reproduce() {
    //create new myOrganism object and do magic
    //remove xp
    //drop in size
  }

  public void die() {
    //Remove the object.
    //disperse as food througout the world
  }

}
