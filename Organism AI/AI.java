import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * AI Code (Things like patrolling the area, knowing how to move, and more.
 *
 *  TODO:
 *      - FIX THIS: <A>getObjectsInRange has protected access in Greenfoot.Actor.
 *      - Do I have to put o. move, o.this,o.that?
 *      - //NONSTATICMETHOD think() CANNOT BE REFERED BY A STATIC CONTEXT
 * @author Uzair Ahmed
 * @version 0.5
 */
public class AI
{
   static List foodNearby;
   static Food foodBeingEaten;
   static List enemiesNearby;
    public static void think(Organism o, List fn, List en, Food fbe){
        foodNearby = fn;
        enemiesNearby = en;
        foodBeingEaten = fbe;

        patrol(o, foodNearby, 0);
        patrol(o, enemiesNearby, 1);
        stayAwayFromEdges(o);
    }

    //Moves in a random motion, until it sees something in its perimeter.
    public static void patrol(Organism o, List thingsNearby, int mode){
        //If theres something near it.
        if ((thingsNearby.size()) > 0){
            o.move(o.speed);
            if (mode==0){
              //Gets the first instance of the class.
              Food nearest = (Food) thingsNearby.get(0);
              //Turns towards it.
              o.turnTowards(nearest.getX(),nearest.getY());
            }
            else if(mode==1){
              //--------------------------------------------------------------
              //---------------JOSH'S THINKING CODE GOES HERE-----------------
              //--------------------------------------------------------------
            }
            o.move(o.speed);
        }
        else{
            o.move(o.speed);
            //One in 4 chance of it turning a random number,
            if (Greenfoot.getRandomNumber(100) < 25){
                //45 degrees on either side of the vertical
                o.turn(Greenfoot.getRandomNumber(90)-45);
            }
        }
        o.consumeFood(foodBeingEaten);
    }

    public static void stayAwayFromEdges(Organism o){
      if(o.isAtEdge()){
        o.turn(180);
      }
    }
}
