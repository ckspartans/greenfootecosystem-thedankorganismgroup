import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * AI Code (Things like patrolling the area, knowing how to move, and more.
 *
 * @author Uzair Ahmed
 * @author Josh Dhori
 * @version 1.0
 */

public class AI
{
    //Declare Lists/Variables to hold protected actor methods.
    static List foodNearby;
    static Food foodBeingEaten;
    
    static List touchingOrganisms;
    static Boolean isTouchingOrganism;

    //This is the only called function by outside classes. This will choose what to do based on if statements.
    public static void think(Organism o, List fn, Food fbe, List to, Boolean ito){//, List fams, List nMes){
        //Set the values to what was given in by the upper class.
        foodNearby = fn;
        foodBeingEaten = fbe;
        touchingOrganisms = to;
        isTouchingOrganism = ito;


        //These are the basic methods implemented till now.
        patrol(o, foodNearby);
        stayAwayFromEdges(o);
        whoDis(o);
    }

    //Moves in a random motion, until it sees "the thing" food or organism in its perimeter.
    public static void patrol(Organism o, List thingsNearby){
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
      if (!o.isAlpha){
        familyHuddle(o);
      }

      //Consume any food you come across.
      o.consumeFood(foodBeingEaten);
    }

    //Turn around if organism is at the edge of the map.
    public static void stayAwayFromEdges(Organism o){
        if(o.isAtEdge()){
            o.turn(180);
        }
        else if (o.getX() >= 1000){
            o.turn(180);
        }
    }
    
    public static void familyHuddle(Organism o){
        Organism alpha = o.myFamily.alpha;
        o.turnTowards(alpha.getX(), alpha.getY());
        o.move(o.speed);
    }
    
    public static void whoDis(Organism o){
        if (isTouchingOrganism){
            for (int i = 0; i<touchingOrganisms.size(); i++){
                Organism touchingOrganism = (Organism)touchingOrganisms.get(i);
                if (touchingOrganism.myFamily == o.myFamily){
                    bounceOff(o, touchingOrganism);
                }
                else if (touchingOrganism.myFamily != o.myFamily){
                    //Enemy
                }
            }
        }
    }
    
    public static void bounceOff(Organism o, Organism touchingOrganism){
        int bx = touchingOrganism.getX();
        int by = touchingOrganism.getY();
        int addedRadiix = (o.radius/2)+(touchingOrganism.radius/2);
        int addedRadiiy = (o.radius/2)+(touchingOrganism.radius/2);
        if (o.myFamily.familyList.indexOf(o)>o.myFamily.familyList.indexOf(touchingOrganism)){
            if (bx>o.getX()){
                addedRadiix*=-1;
            }
            if(by>o.getY()){
                addedRadiiy*=-1;
            }
            o.turnTowards(bx+addedRadiix, by+addedRadiiy);
            o.move(o.speed);
        }
    }
}
    