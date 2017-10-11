import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Extends off AbstOrganism to create a basic organism that reproduces, and moves around the screen.
 *
 * CHANGELOG October 9, 2017
 *  - Added draw code, so we can visualize health.
 *
 * @author Uzair Ahmed
 * @version 0.5
 */

public class Organism extends AbstOrganism {
  //draws the organism
  public void drawOrganism(Color c, int rad){
      GreenfootImage img = new GreenfootImage(rad, rad);
      img.setColor(c);
      img.drawOval(0,0,rad,rad);
      img.fillOval(0,0,rad,rad);
      setImage(img);
  }
  
  //Moves in a random motion, until it sees food in its perimeter, might be moved to an AI class
  public void moveAround() {
    //Gets all Food objects in the sight radius
    List foodNearby = getObjectsInRange(sight, Food.class);
    //Gets all Food objects in the sight radius
    List orgsNearby = getObjectsInRange(sight, Organism.class);
    //Gets the food object it is touching
    Food foodBeingEaten = (Food) getOneIntersectingObject(Food.class);
    //Gets the organism object it is touching
    Organism orgsBeingEaten = (Organism) getOneIntersectingObject(Organism.class);
    
    //if theres something near it
    if ((foodNearby.size()) > 0){
      //track stuff
      follow(foodNearby);
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
    if (foodBeingEaten != null){
        removeTouching(Food.class);
        consumeFood();
    }
    
    if (orgsBeingEaten != null){
        if (isOnSameTeam()){
            turn(180);
        }
        else if (!isOnSameTeam()){
            if (threatLevel > orgsBeingEaten.threatLevel){
                consumePlayer(0);
            }
            else if (threatLevel <= orgsBeingEaten.threatLevel){
                consumePlayer(1);
            }
        }
    }
    
    //turns around if it hits an edge
    if (isAtEdge()){
      turn(180);
    }
  }

  //takes in a list of objects nearby, to turn towards it
  public void follow(List l){
    //gets the first instance of the class
    Food nearest = (Food)l.get(0);
    //turns towards it
    turnTowards(nearest.getX(),nearest.getY());
  }

  public void consumeFood() {
    int foodConsumed = Food.foodMass;
    xp+=foodConsumed/10;
    System.out.println(xp);
  }

  public void consumePlayer(int mode) {
      int displacedHealth = 0;
      Timer timer = new Timer();
      displacedHealth++;
      //timer.scheduleAtFixedRate(addedHealth+=attMult, 0, (def/att)*attMult);
      if (mode == 0){
            if (health < maxHealth){
                health+=displacedHealth;
          }
        }
        else if (mode == 1){
            if (health<0){
                health-=displacedHealth;
            }
        }
  }
  
  
  public boolean isOnSameTeam(){
      //check if thing is on the same team
      return false;
  }

  //I haven't thought about things this far yet :/
  
  
  
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
