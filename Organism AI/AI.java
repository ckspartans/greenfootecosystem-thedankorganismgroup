import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * AI Code (Things like patrolling the area, knowing how to move, and more.
 *
 * @author Uzair Ahmed
 * @version 1.4
 */

public class AI
{
    //Declare Lists/Variables to hold protected actor methods.
    //Gets a list of all food nearby
    static List foodNearby;
    //Gets Food Object that is currently being eaten
    static Food foodBeingEaten;
    //Checks if its touching an organism
    static Boolean isTouchingOrganism;
    //Gets a list of all organisms it touches
    static List touchingOrganisms;

    //This is the only called function by outside classes. This will choose what to think
    public static void think(Organism o, List fn, Food fbe, List to, Boolean ito){
        //Uzair Ahmed

        //Set the values to what was given in by the upper class.
        foodNearby = fn;
        foodBeingEaten = fbe;
        touchingOrganisms = to;
        isTouchingOrganism = ito;

        //Think Functions
        patrol(o, foodNearby);
        stayAwayFromEdges(o);
        whoDis(o);
    }

    //Moves in a random motion, until it sees food or organism in its perimeter.
    public static void patrol(Organism o, List thingsNearby){
      //Uzair Ahmed

      //If the organism in question is an alpha
      if (o.isAlpha){
          //If theres "the thing" near it.
          if ((thingsNearby.size()) > 0){
              //Move towards it.
              o.move(o.speed);
              //Get the first instance of nearby Food.
              Food nearest = (Food) thingsNearby.get(0);
              //Turn towards it.
              o.turnTowards(nearest.getX(),nearest.getY());
          }
          //If there's nothing near it
          else{
              //Move in original direction
              o.move(o.speed);
              //25% chance to turn
              if (Greenfoot.getRandomNumber(100) < 25){
                  //within 45 degrees on either side of of the direction im facing
                  o.turn(Greenfoot.getRandomNumber(90)-45);
              }
          }
      }
      //if the organism in question is not an alpha
      if (!o.isAlpha){
          //if the alpha is alive
          if(o.myFamily.alpha.isAlive){
              //Follow the alpha
              familyHuddle(o);
            }
      }
      //Consume any food you come across.
      o.consumeFood(foodBeingEaten);
    }

    //Turn around if organism is at the edge of the map.
    public static void stayAwayFromEdges(Organism o){
        //Uzair Ahmed

        //If it is at the edge
        if(o.isAtEdge()){
            //Turn around
            o.turn(180);
        }
        //If its at the egde of the UI
        else if (o.getX() >= 1000){
            //Turn Around
            o.turn(180);
        }
    }

    //Moves the family together
    public static void familyHuddle(Organism o){
        //Uzair Ahmed

        //Creates temp organism of alpha
        Organism alpha = o.myFamily.alpha;
        //Turns towards the alpha
        o.turnTowards(alpha.getX(), alpha.getY());
        //And moves
        o.move(o.speed);
    }

    //Checks if the nearby organism is a friend or foe
    public static void whoDis(Organism o){
        //Uzair Ahmed

        //Checks if its touching the organism
        if (isTouchingOrganism){
            //For each organism its touching
            for (int i = 0; i<touchingOrganisms.size(); i++){
                //Create a temp organism
                Organism touchingOrganism = (Organism)touchingOrganisms.get(i);
                //If the organism is a family member
                if (touchingOrganism.myFamily == o.myFamily){
                    //Run bounceoff
                    bounceOff(o, touchingOrganism);
                }
                //If the organism is an enemy
                else if (touchingOrganism.myFamily != o.myFamily){
                    //Enemy
                }
            }
        }
    }

    //Prevents the organisms from overlapping each other
    public static void bounceOff(Organism o, Organism touchingOrganism){
        //Uzair Ahmed

        //Set x and y values for the organism it touches
        int bx = touchingOrganism.getX();
        int by = touchingOrganism.getY();
        //Create x and y values for added radii
        int addedRadiix = (o.radius/2)+(touchingOrganism.radius/2);
        int addedRadiiy = (o.radius/2)+(touchingOrganism.radius/2);
        //If the current organism is older than the other (to choose one organism)
        if (o.myFamily.familyList.indexOf(o)>o.myFamily.familyList.indexOf(touchingOrganism)){
            //move it to the proper side of the other organism in the x andd y directions
            if (bx>o.getX()){
                addedRadiix*=-1;
            }
            if(by>o.getY()){
                addedRadiiy*=-1;
            }
            //Turn towards the other organisms
            o.turnTowards(bx+addedRadiix, by+addedRadiiy);
            //Move
            o.move(o.speed);
        }
    }
}
