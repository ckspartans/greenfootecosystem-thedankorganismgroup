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
  public Organism(int smh, int smxp, int ss, int sa, int sd, int ssi, int t) {
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
    threatLevel = (att*health*def)/(1+age);

    //Game Variables
    myTeam = t;
    MainWorld.lifeForms.get(myTeam-1).add(this);


    MainWorld world;
  }

  public void act(){
    //Gets all objects in the sight radius and puts them into thier proper lists.
    List foodNearby = getObjectsInRange(sight, Food.class);
    List organismsNearby  = getObjectsInRange(sight, Organism.class);
    //Gets the food object it is touching
    Food foodBeingEaten = (Food) getOneIntersectingObject(Food.class);

    //Draws the organism
    drawOrganism(Color.GREEN, radius);

    //Checks for nearby friends or enemies.
    //friendOrFoe();

    //Runs Mutation Method
    mutate();

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
          int foodConsumed = foodBeingEaten.foodMass;
          xp+=foodConsumed/10;
      }
  }
 
  
  public void mutate(){
      if (xp >= 10){
        if (Greenfoot.getRandomNumber(5) == 1){ //mutation rate
            int chosenMutation = Greenfoot.getRandomNumber(4);
            if (chosenMutation == 1){ //attack
                att += 1;
            } 
            else if (chosenMutation == 2){ //defense
                def += 1;
            }
            else if (chosenMutation == 3){ //speed
                speed += 2;
            }
            else if (chosenMutation == 4){ //sight range
                sight += 10;
            }
            xp=0;
        }
    }
  }
  //I haven't thought about things this far yet :/

  public void age() {
    //start a timer in act() and use that value to edit age value, and color
  }

  public void reproduce() {
      world.addObject(new Organism(maxHealth, xp,speed, att, def, sight, myTeam), 
      (Greenfoot.getRandomNumber(50)-25)+getX(), 
      (Greenfoot.getRandomNumber(50)-25)+getY());
  }

  public void die() {
    //Remove the object.
  }

}
