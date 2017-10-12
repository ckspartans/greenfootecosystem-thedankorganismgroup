import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Extends off AbstOrganism to create a basic organism that reproduces, and moves around the screen.
 *
 * CHANGELOG October 12, 2017
 *  - Added Ethan's Mutation Code
 *
 * @author Uzair Ahmed
 * @author Ethan Gale
 * @version 1.1
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

  public void consumeFood(Food foodBeingEaten){
      if (foodBeingEaten != null){
          removeTouching(Food.class);
          int foodConsumed = foodBeingEaten.foodMass;
          xp+=foodConsumed/10;
      }
  }

  public boolean isOnSameTeam(){
      //check if thing is on the same team
      return false;
  }

  public void healthToSize() {
    radius = health*2;
  }

  //Mutation Function Made By Ethan Gale
  public static void mutate(int xp, int ss, int sa, int sd, int ssi) {
    if (xp > 9){
        if (Greenfoot.getRandomNumber(3) == 1){ //mutation rate
            int chosenMutation = Greenfoot.getRandomNumber(4);
            if (chosenMutation == 1){ //attack
                sa = sa++;
            }
            else if (chosenMutation == 2){ //defense
                sd = sd++;
            }
            else if (chosenMutation == 3){ //speed
                ss = ss + 2;
            }
            else if (chosenMutation == 4){ //sight range
                ssi = ssi + 10;
            }
        }
    }
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
