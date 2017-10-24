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

    //Commented these out as they are no longer being used
    //static List familyNearby;
    //static List enemiesNearby;

    //static List totalFamily;
    //static List totalEnemy;

    //This is the only called function by outside classes. This will choose what to do based on if statements.
    public static void think(Organism o, List fn, Food fbe){//, List fams, List nMes){
        //Set the values to what was given in by the upper class.
        foodNearby = fn;
        foodBeingEaten = fbe;

        //totalFamily = fams;
        //totalEnemy = nMes;

        //These are the basic methods implemented till now.
        patrol(o, foodNearby);
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
            o.turnTowards(alpha.getX()-(Greenfoot.getRandomNumber(aSize)-aSize/2), alpha.getY()-(Greenfoot.getRandomNumber(aSize)-aSize/2));
            o.move(o.speed);
        }

    //Dhori's Code:
    //public void attack(int enemy){
    /*get the amount of organisms in the group
     *calculate the total group power
     *
     *To be done v2:
     *search for enemies
     *check if enemy is worth attacking (check enemy threat level, enemy size, enemy position & check group threat level, group number, group position)
     *if the enemy is worth attacking check to see how easy it is attacked, how many organisms the herd needs to kill the enemy, which organisms to use
     *choose a tatic and attack!!
     *
     *
     *fight until death
     *if defender has first strike they attack first
     *delete the loser
     *if win return how many energy they get
     */

    /*int eX;
    int eY;
    int radius;

    chosenEnemy(enemy); //choose which enemy to attack their chosenEnemy value, which enemy is it?
    attackMode(true); //set your own attackmode to true
    calculateAttack(); //figure out how badass your squad is

    //move towards enemy
    hit(enemy);

    //surround enemy
    //attack enemy at once

    }

    //Turn around if organism is at the edge of the map.
    public static void stayAwayFromEdges(Organism o){
        if(o.isAtEdge()){
            o.turn(180);
        }
        else if (o.getX() > 1000){
            o.turn(180);
        }
    }

    // Dhoir's Code:
    public static void attack(Organism o, Organism enemy, int tatic){

        o.chosenEnemy = enemy; //choose which enemy to attack their chosenEnemy value, which enemy is it?
        o.attackMode = true; //set your own attackmode to true	
        o.attackTatic = tatic;
        calculateAttack(o); //figure out how badass your squad is (not used rn)
        if (tatic == 0){ //basic group attack
            while((enemy != null) && (o.touching(enemy) == false)){
                //turn towards enemy (Uzair)
                o.turnTowards(enemy.getX(), enemy.getY());
                //move towards enemy (Uzair)
                o.move(o.speed);
            }

            o.hit(enemy);
            o.move(-o.speed); //take a step back after attacking
        }
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
                    o.turn(180);
                }
                else if (o.getX() > 1000){
                    o.turn(180);
                }
                //runs away
                o.move(o.speed);
            }
        }
        else if (tatic == 1){ //Option 1: 1 vs 1 that dude
            while((enemy != null) && (o.touching(enemy) == false)){
                //turn towards enemy
                o.turnTowards(enemy.getX(), enemy.getY());
                //move towards enemy
                o.move(o.speed);
            }
            //hit enemy
            o.hit(enemy);
            o.move(-o.speed); //take a step back after attacking
        }
        else if (tatic == 2){ //Option 3: Straight up attack that guy
            while((enemy != null)){
                attack(o, enemy, 0);
            }
        }
    }

    public static int calculateAttack(Organism o){
        //This gets all the attack power of the organisms in the group
        //needs uzair's code
        int groupAtt = 0;

        for (int i = 0;i < (o.myFamily.familyList.size()); i++){
            Organism tempOrg;
            tempOrg = (Organism)o.myFamily.familyList.get(i);
            groupAtt += tempOrg.att;
        }

        return groupAtt;
    }

    public static void checkGroupAttack(Organism o){ //checks if anyone in the group is attacking someone
        for (int i = 0; i > o.myFamily.familyList.size(); i++){ 
            Organism tempOrg;
            tempOrg = (Organism)o.myFamily.familyList.get(i);
            if (tempOrg.attackMode == true){ //if anyone in your family is attaccking someone
                Organism enemy = tempOrg.chosenEnemy;

                o.chosenEnemy = enemy; //using their chosenEnemy value, which enemy is it?
                o.attackMode = true; //set your own attackmode to true
                int tatic = o.attackTatic; //finds which tatic that organism is using
                attack(o, enemy, tatic); //attack that enemy
            }
        }
    }

}
