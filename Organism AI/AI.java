import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class AI here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
/*public class AI
{
    public void think(Organism o){
        //Gets all objects in the sight radius and puts them into thier proper lists.
        List foodNearby = o.getObjectsInRange(o.sight, Food.class);
        List enemiesNearby  = o.getObjectsInRange(o.sight, EnemyOrganism.class);

        patrol(o, foodNearby);
        patrol(o, enemiesNearby);
    }

    //Moves in a random motion, until it sees something in its perimeter.
    public void patrol(Organism o, List thingsNearby){
        //If theres something near it.
        if ((thingsNearby.size()) > 0){
            o.move(o.speed);
            //Follow stuff.
            follow(o, thingsNearby);
        }
        else{
            o.move(o.speed);
            //One in 4 chance of it turning a random number,
            if (Greenfoot.getRandomNumber(100) < 25){
                //45 degrees on either side of the vertical
                o.turn(Greenfoot.getRandomNumber(90)-45);
            }
        }
    }

    //Takes in a list of objects nearby, to turn towards it.
    public void follow(Organism o, List l){
        //Gets the first instance of the class.
        Food nearest = (Food) l.get(0);
        //Turns towards it.
        o.turnTowards(nearest.getX(),nearest.getY());
    }
}*/
