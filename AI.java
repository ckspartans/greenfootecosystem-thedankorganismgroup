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
    static List organismsNearby;
    static List foodNearby;
    //Gets Food Object that is currently being eaten
    static Food foodBeingEaten;
    //Checks if its touching an organism
    static Boolean isTouchingOrganism;
    //Gets a list of all organisms it touches
    static List touchingOrganisms;

    //This is the only called function by outside classes. This will choose what to think
    public static void think(Organism o, List fn, Food fbe, List on, List to, Boolean ito){
        //Uzair Ahmed

        //Set the values to what was given in by the upper class.
        foodNearby = fn;
        organismsNearby = on;
        foodBeingEaten = fbe;
        touchingOrganisms = to;
        isTouchingOrganism = ito;

        //Think Functions
        patrol(o, foodNearby);
        choosePrey(o);
        attackManager(o);
        //o.checkIfAttacking();
        o.checkDefend();
        stayAwayFromEdges(o);
        whoDis(o);
    }

    //Moves in a random motion, until it sees food or organism in its perimeter.
    public static void patrol(Organism o, List thingsNearby){
        //Uzair Ahmed

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
            o.flee();
        }
        //If its at the egde of the UI
        else if (o.getX() >= 1000){
            //Turn Around
            o.flee();
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
                    o.isTouchingEnemy = true;
                }

            }
        }
    }

    public static void attackManager(Organism o){
        if (o.chosenEnemy != null){
            attack(o,o.chosenEnemy, 0);
        }
    }

    public static void bounceOff(Organism o, Organism touchingOrganism){
        //Uzair Ahmed
        //Set x and y values for the organism it touches
        int bx = touchingOrganism.getX();
        int by = touchingOrganism.getY();
        //Create x and y values for added radii
        int addedRadiix = (int) ((o.radius/2)+(touchingOrganism.radius/2));
        int addedRadiiy = (int) ((o.radius/2)+(touchingOrganism.radius/2));
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

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~////~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~////~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    //~~~~~~~~~~~~~~~~~~~~~~~~~~Dhori's Code~~~~~~~~~~~~~~~~~~~~~~~~~~////~~~~~~~~~~~~~~~~~~~~~~~~~~Dhori's Code~~~~~~~~~~~~~~~~~~~~~~~~~~////~~~~~~~~~~~~~~~~~~~~~~~~~~Dhori's Code~~~~~~~~~~~~~~~~~~~~~~~~~~//
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~////~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~////~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//

    public static void attack(Organism o, Organism enemy, int tatic){
        if(enemy.isAlive){
            //o.chosenEnemy = enemy; //choose which enemy to attack their chosenEnemy value, which enemy is it?
            //o.attackMode = true; //set your own attackmode to true
            o.attackTatic = tatic;
            o.getGroupThreatLevel(); //figure out how badass your squad is (not used rn)
            if (tatic == 0){ //basic group attack
                //while((enemy != null) && (o.isTouchingEnemy == false)){
                //turn towards enemy (Uzair)
                o.turnTowards(enemy.getX(), enemy.getY());
                //move towards enemy (Uzair)
                o.move(o.speed);
                //}

                //Prevents the organisms from overlapping each other
                o.hit(enemy, true);
                o.move(-o.speed); //take a step back after attacking
            }
        }
    }
    //else{
    //    o.chosenEnemy = null;
    //}

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
            while((enemy != null) && (o.isTouchingEnemy == false)){
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