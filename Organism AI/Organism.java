import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Extends off AbstOrganism to create a basic organism that reproduces, and moves around the screen.
 *
 * CHANGELOG October 12, 2017
 *  - Added Setter and Getter Variables for team, and method for team differentiation.
 *
 * @author Uzair Ahmed
 * @author Ethan Gale
 * @version 1.2
 */

public class Organism extends AbstOrganism {
  public Organism(int smh, int smxp, int ss, int sa, int sd, int ssi) {
    //XP Upgradeable Variables-------------------------------------------------
    maxHealth = smh; //Maximum Health
    maxXp = smxp; //Max XP Storage
    speed = ss; //Nuff Said
    att = sa; //Attack power
    def = sd; //Defensive power
    sight = ssi; //Sight

    //"Live" Variables. ----------------------------------------------------
    age = 0; //Time
    health = maxHealth-2; //Out of maxHealth
    xp = 0; //Out of maxXp
    radius = health*2;
    fill = new Color(0, 0, 0);
    border = new Color(0, 0, 255);
    threatLevel = (att*health*def)/(1+age);
    
    myTeam = 1;
    enemies = new ArrayList();
    family = new ArrayList();

    MainWorld world;
  }
  
  public void act()
    {
    //Gets all objects in the sight radius and puts them into thier proper lists.
    List foodNearby = getObjectsInRange(sight, Food.class);
    List organismsNearby  = getObjectsInRange(sight, Organism.class);
    //Gets the food object it is touching
    Food foodBeingEaten = (Food) getOneIntersectingObject(Food.class);
    
    //Draws the organism
    drawOrganism(Color.GREEN, radius);
    
    //checks for nearby friends or enemies.
    friendOrFoe(organismsNearby);
    
    //Runs Mutation Method
    //mutate(xp, speed, att, def, sight);

    //Runs the AI Method
    AI.think(this, foodNearby, family, enemies, foodBeingEaten);
    }

  //draws the organism
  public void drawOrganism(Color c, int rad){
      GreenfootImage img = new GreenfootImage(rad, rad);
      img.setColor(c);
      img.drawOval(0,0,rad,rad);
      img.fillOval(0,0,rad,rad);
      setImage(img);
  }

  public void consumeFood(Food foodBeingEaten){
      if (foodBeingEaten != null){
          removeTouching(Food.class);
          //int foodConsumed = foodBeingEaten.foodMass;
          //xp+=foodConsumed/10;
      }
  }

  public boolean isOnSameTeam(){
      //check if thing is on the same team
      return false;
  }

  public void healthToSize() {
    radius = health*2;
  }

  //I haven't thought about things this far yet :/

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
