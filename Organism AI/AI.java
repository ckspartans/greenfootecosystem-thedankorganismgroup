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
    //static List totalFamily;
    //static List totalEnemy;

    //This is the only called function by outside classes. This will choose what to do based on if statements.
    public static void think(Organism o, List fn, List fam, List en, Food fbe){//, List fams, List nMes){
        //Set the values to what was given in by the upper class.
        foodNearby = fn;
        familyNearby = fam;
        enemiesNearby = en;
        foodBeingEaten = fbe;
        //totalFamily = fams;
        //totalEnemy = nMes;

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
        else if (o.getX() > 1000){
            o.turn(180);
        }
    }

    //Dhoir's Code:
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

    public void defend(){*/
    /*search for the amount of enemies in sight range (this needs to be constantly run so the defender knows if more enemies are coming)
     *check the enemies coming at you (threat level and position)
     *determine the best strategy to use
     *Tatics:
     *A) Run like hell away
     *B) Attack closest enemy
     *C) Attack enemies based on position, threat level
     *
     *keep track of all the energy they gain by eating the bodies
     *eat the bodies (if they win at the end)
     */
    /*for (int i=0; i > enemiesNearby.size(); i++){ //finds all the enemies nearby
    if (enemiesNearby.get(i).attackMode == true){ //if any enemy is attacking
    //if (intelligence >= Xamount){ //if this organism is smart
    //if (enemiesNearby.get(i.chosenEnemy) == ){ //only notice it is being attacked if it is acutally being attacked, not an organism near it
    //go towards enemy
    //hit enemy
    //}
    //}
    //else {
    //turn towards enemy (Uzair)
    //move towards enemy (Uzair)
    //hit enemy
    hit(i);
    //}
    }
    }
    }

    public int calculateAttack(){
    //This gets all the attack power of the organisms in the group
    //needs uzair's code
    int groupAtt = 0;

    for (int i = 0;i < totalFamily.size(); i++){
    groupAtt += totalFamily.get(i).att;
    }

    return groupAtt;
    }

    public boolean attackMode(boolean attacking){ //if you choose to attack someone, set attackMode to true+
    return attacking;
    }

    public int chosenEnemy(int enemyNum){//if you are attacking someone, this is the enemy you are attacking (number is the number it is on the enemy list)
    return enemyNum;
    }

    public void checkGroupAttack(){ //checks if anyone in the group is attacking someone
    for (int i = 0; i > totalFamily.size(); i++){ 
    if (totalFamily.get(i).attackMode == true){ //if anyone in your family is attaccking someone
    int temp = totalFamily.get(i).chosenEnemy();
    chosenEnemy(temp); //using their chosenEnemy value, which enemy is it?
    attackMode(true); //set your own attackmode to true
    attack(i); //attack that enemy
    }
    }
    }

    public void hit(int enemy){ //hits enemy
    if (isTouching(totalEnemy.enemy)){ //if touching the X organism in totalEnemy list
    totalEnemy.get(enemy.health) -= (att - totalEnemy.get(enemy.def)); //hits selected enemy for your attack - enemy defense
    totalEnemy.get(enemy.def) -= (totalEnemy.get(enemy.def)*(att/totalEnemy.get(enemy.def))); //reduce the defensive power by the your attack divided by their defense percentage eg att-->1 def -->2 new def = 1
    }
    }*/
}

