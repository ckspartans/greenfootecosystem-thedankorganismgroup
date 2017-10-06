import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Extends off AbstOrganism to create a basic organism that reproduces, and moves around the screen.
 *
 * CHANGELOG October 5, 2017
 *  - Added Tracking methods, cleaning up this code.
 *
 * @author Uzair Ahmed
 * @version 0.5
 */

public class MyOrganism extends AbstOrganism {

  //Takes in the custom variables (located in MainWorld), and sets them for the whole class to use.
  public MyOrganism(int smh, int smxp, int ss, int sa, int sd, int ssi) {

    //XP Upgradeable Variables-------------------------------------------------
    maxHealth = smh; //Maximum Health
    maxXp = smxp; //Max XP Storage
    speed = ss; //Nuff Said
    att = sa; //Attack power
    def = sd; //Defensive power
    sight = ssi; //Sight

    //"Live" Variables. ----------------------------------------------------
    age = 0; //Time
    health = maxHealth; //Out of maxHealth
    xp = 0; //Out of maxXp
    radius = health;
    fill = new Color(0, 0, 0);
    border = new Color(0, 0, 255);

    MainWorld world;
  }


  public void act() {
    moveAround();
  }

  //Moves in a random motion, until it sees food in its perimeter, might be moved to an AI class
  public void moveAround() {
    //Gets all Food objects in the sight radius
    List foodNearby = getObjectsInRange(sight, Food.class);
    //if theres something near it
    if ((foodNearby.size()) > 0){
      //track stuff
      track(foodNearby);
      move(speed);
    }
    else{
      move(speed);
      //One in 4 chance of it turning a random number,
      if (Greenfoot.getRandomNumber(100) < 25){
        //45 degrees on either side of the vertical
        turn(Greenfoot.getRandomNumber(90)-45);
      }
    }
    //removes the food it touches, consume will be run after this.
    removeTouching(Food.class);
    //turns around if it hits an edge
    if (isAtEdge()){
      turn(180);
    }
  }

  //takes in a list of objects nearby, to turn towards it
  public void track(List l){
    //gets the first instance of the class
    Food nearest = (Food) l.get(0);
    //turns towards it
    turnTowards(nearest.getX(),nearest.getY());
  }

  public void consumeFood() {
    //Consumes Food
  }

  public void consumePlayer() {
      //Consumes a Player
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
  }

}
