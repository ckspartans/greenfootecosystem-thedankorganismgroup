import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * AI Code (Things like patrolling the area, knowing how to move, and more.
 *
 * CHANGELOG: Added Pseudocode
 *
 * @author Uzair Ahmed
 * @version 0.6
 */


public class AI
{
   //Declare Lists/Variables to hold protected actor methods.
   static List foodNearby;
   static Food foodBeingEaten;
   static List familyNearby;
   static List enemiesNearby;

   //This is the only called function by outside classes. This will choose what to do based on if statements.
    public static void think(Organism o, List fn, List fam, List en, Food fbe){
        //Set the values to what was given in by the upper class.
        foodNearby = fn;
        familyNearby = fam;
        enemiesNearby = en;
        foodBeingEaten = fbe;

        //These are the basic methods implemented till now.
        patrol(o, foodNearby, 0);
        stayAwayFromEdges(o);
    }

    //Moves in a random motion, until it sees "the thing" food or organism in its perimeter.
    public static void patrol(Organism o, List thingsNearby, int mode){
        //If theres "the thing" near it.
        if ((thingsNearby.size()) > 0){
            //Move towards it.
            o.move(o.speed);
            //If the mode is set to food.

            if (mode==0){
              //Get the first instance of nearby Food.
              Food nearest = (Food) thingsNearby.get(0);
              //Turn towards it.
              o.turnTowards(nearest.getX(),nearest.getY());
            }

            //Otherwise, if the mode is set to organism
            else if(mode==1){
              //--------------------------------------------------------------
              //---------------JOSH'S THINKING CODE GOES HERE-----------------
              //--------------------------------------------------------------
            }
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

        //Consume any food you come across.
        o.consumeFood(foodBeingEaten);
    }

    //Turn around if organism is at the edge of the map.
    public static void stayAwayFromEdges(Organism o){
      if(o.isAtEdge()){
        o.turn(180);
      }
    }
}
