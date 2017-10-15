import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Extends off AbstOrganism to create a basic organism that reproduces, and moves around the screen.
 *
 * CHANGELOG October 12, 2017
 *      -Added differentiation code
 *
 * KNOWN BUGS TO ASK ABOUT:
 * - nullpointerException on line 127, in reproduce.
 * - lines 111, 114. Organism does not stop at adding itself once.
 *
 *
 * @author Uzair Ahmed
 * @author Ethan Gale
 * @version 1.2
 */

public class Organism extends AbstOrganism {
  public Organism(int smh, int smxp, int ss, int sa, int sd, int ssi, int t, Color c) {
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
    family = new ArrayList<Organism>();
    enemies = new ArrayList<Organism>();
    myTeam = t;
    myColor = c;

    MainWorld.lifeForms.get(myTeam-1).add(this);

    MainWorld world;
  }

  public void act(){
    
    if (world == null){
          world = (MainWorld) getWorld();
    }
    //Gets all objects in the sight radius and puts them into thier proper lists.
    List foodNearby = getObjectsInRange(sight, Food.class);
    List organismsNearby  = getObjectsInRange(sight, Organism.class);
    //Gets the food object it is touching
    Food foodBeingEaten = (Food) getOneIntersectingObject(Food.class);

    //Starts the timer for age.
    startTimer();

    //Draws the organism
    drawOrganism(myColor, radius);

    //Checks for friends or enemies.
    distinguishOrganisms();

    //Runs Mutation Method
    mutate();

    System.out.println(getAge());
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

  //Ethan Gale
  public void mutate(){
      if (xp >= 10){
        if (Greenfoot.getRandomNumber(5) == 1){ //mutation rate
            if (Greenfoot.getRandomNumber(5) == 1){
                reproduce();
                xp = 0;
            }
            else{
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
  }

  public void distinguishOrganisms(){
      for (int l = 0; l < world.lifeForms.size(); l++){
          for (int o = 0; o < world.lifeForms.get(l).size();o++){
            Organism org = (Organism) world.lifeForms.get(l).get(o);
            if (org.myTeam == myTeam){
                family.add(org);
            }
            else if (org.myTeam != myTeam){
                enemies.add(org);
            }
        }
    }
}


  public void startTimer(){
    age++;
  }

  public int getAge() {
      //Calculates the age in time, by taking the frames
      //and calculating based on an anerage 60 fps
      return timer/60;
  }

  public void reproduce() {
      Organism tempOrg = new Organism(maxHealth, xp,speed, att, def, sight, myTeam, myColor);
      world.addObject(tempOrg,(getX()+Greenfoot.getRandomNumber(30)-15),(getY()+Greenfoot.getRandomNumber(30)-15));
  }

    //I haven't thought about things this far yet :/

  public void die() {
    //Remove the object.
  }

}
