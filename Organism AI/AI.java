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
    static List organismsNearby;
    static List foodNearby;
    static Food foodBeingEaten;

    //Commented these out as they are no longer being used
    //static List familyNearby;
    //static List enemiesNearby;

    //static List totalFamily;
    //static List totalEnemy;

    //This is the only called function by outside classes. This will choose what to do based on if statements.
    public static void think(Organism o, List fn, List on, Food fbe){//, List fams, List nMes){
        //Set the values to what was given in by the upper class.
        foodNearby = fn;
        organismsNearby = on;
        foodBeingEaten = fbe;

        //totalFamily = fams;
        //totalEnemy = nMes;

        //These are the basic methods implemented till now.
        patrol(o, foodNearby);
        choosePrey(o);
        attackManager(o);
        o.checkIfAttacking();
        o.checkDefend();
        stayAwayFromEdges(o);
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
            Organism alpha = o.myFamily.alpha;
            int aSize = alpha.radius*4;
            o.turnTowards(alpha.getX(), alpha.getY());
            o.move(o.speed);
        }

        //Consume any food you come across.
        o.consumeFood(foodBeingEaten);
    }

    //Turn around if organism is at the edge of the map.
    public static void stayAwayFromEdges(Organism o){
        if(o.isAtEdge()){
            o.flee();
        }
        else if (o.getX() > 1000){
            o.flee();
        }
    }

    public static void choosePrey(Organism o){
        //Uzair Ahmed
        Organism tempOrg;
        Organism enemy;
        for (int i = 0; i< organismsNearby.size(); i++){
            tempOrg = (Organism) organismsNearby.get(i);
            if (tempOrg.myFamily != o.myFamily){
                if (tempOrg.threatLevel <= o.threatLevel){
                    if(tempOrg.getGroupThreatLevel() <=o.getGroupThreatLevel()){
                        o.chosenEnemy = tempOrg;
                    }
                    else{
                        if (tempOrg.myFamily.getAvgGroupPower() <= o.myFamily.getAvgGroupPower()){
                            o.chosenEnemy = tempOrg;
                        }
                        else{
                            o.flee();
                        }
                    }
                }
                else{
                    if(tempOrg.getGroupThreatLevel() <=o.getGroupThreatLevel()){
                        o.chosenEnemy = tempOrg;
                    }
                    else{
                        o.flee();
                    }
                }
            }
        }
    }

    public static void attackManager(Organism o){
        killEveryone(o);
        if (o.chosenEnemy != null){
            attack(o,o.chosenEnemy, 0);
        }
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~////~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~////~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    //~~~~~~~~~~~~~~~~~~~~~~~~~~Dhori's Code~~~~~~~~~~~~~~~~~~~~~~~~~~////~~~~~~~~~~~~~~~~~~~~~~~~~~Dhori's Code~~~~~~~~~~~~~~~~~~~~~~~~~~////~~~~~~~~~~~~~~~~~~~~~~~~~~Dhori's Code~~~~~~~~~~~~~~~~~~~~~~~~~~//
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~////~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~////~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    public static void killEveryone(Organism o){
        if(o.parasite.insane){
            while(o.isAlive){
                List temp = o.getNearby();
                Organism enemy = (Organism)temp.get(0);
                attack(o, enemy, 0);
            }
        }
    }

    public static void attack(Organism o, Organism enemy, int tatic){

        if(enemy.isAlive){
            //o.chosenEnemy = enemy; //choose which enemy to attack their chosenEnemy value, which enemy is it?
            //o.attackMode = true; //set your own attackmode to true  
            o.attackTatic = tatic;
            o.getGroupThreatLevel(); //figure out how badass your squad is (not used rn)
            if (tatic == 0){ //basic group attack
                while((enemy != null) && (o.istouchingEnemy(enemy) == false)){
                    //turn towards enemy (Uzair)
                    o.turnTowards(enemy.getX(), enemy.getY());
                    //move towards enemy (Uzair)
                    o.move(o.speed);
                }

                o.hit(enemy, true);
                o.move(-o.speed); //take a step back after attacking
            }
        }
        //else{
        //    o.chosenEnemy = null;
        //}
    }

    public static void defend(Organism o, Organism enemy, int tatic){
        /*search for the amount of enemies in sight range (this needs to be constantly run so the defender knows if more enemies are coming)
         *check the enemies coming at you (threat level and position)
         *determine the best strategy to use
         *Tatics:
         *A) Run like hell away
         *B) Attack closest enemy //NEW TATIC 1vs1
         *C) Attack enemies based on position, threat level //NEW TATIC team vs that guy
         *
         *keep track of all the energy they gain by eating the bodies
         *eat the bodies (if they win at the end)
         */
        if(tatic == 0){//Option 0: RUN!!!!
            while((enemy != null)){
                //turns away from bad dude
                o.turnTowards((enemy.getX() + 180), (enemy.getY() + 180));
                if(o.isAtEdge()){
                    o.flee();
                }
                else if (o.getX() > 1000){
                    o.flee();
                }
                //runs away
                o.move(o.speed);
            }
        }
        else if (tatic == 1){ //Option 1: 1 vs 1 that dude
            while((enemy != null) && (o.istouchingEnemy(enemy) == false)){
                //turn towards enemy
                o.turnTowards(enemy.getX(), enemy.getY());
                //move towards enemy
                o.move(o.speed);
            }
            //hit enemy
            o.hit(enemy, false);
            o.move(-o.speed); //take a step back after attacking
        }
        else if (tatic == 2){ //Option 3: Straight up attack that guy
            while((enemy != null)){
                attack(o, enemy, 0);
            }
        }
    }
}
