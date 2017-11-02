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

    public static void think(Organism o, List fn, Food fbe, List on, List to, Boolean ito){
        //Uzair Ahmed
        //This is the only called function by outside classes. This will choose what to think

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
        o.checkIfAttacking();
        o.checkDefend();
        marcoPolo(o);
        whoDis(o);

        //Parasite
        if (o.infected == true){
            o.parasite.update();
            o.infect();
        }
    }


    public static void patrol(Organism o, List thingsNearby){
        //Uzair Ahmed
        //Moves in a random motion, until it sees food or organism in its perimeter.

        //If theres "the thing" near it.
        if ((thingsNearby.size()) > 0){
            //Move towards it.
            o.move((int)(o.speed*1.1));
            //Get the first instance of nearby Food.
            Food nearest = (Food) thingsNearby.get(0);
            //Turn towards it.
            o.turnTowards(nearest.getX(),nearest.getY());
        }
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


    public static void marcoPolo(Organism o){
        //Uzair Ahmed
        //Wrap around if organism is at the edge of the map.

        //Get current position values
        int posX = o.getX();
        int posY = o.getY();

        //If it is at the edge, move to the other edge
        if(posX <= 0){
            posX = 999;
        }
        else if (posX >= 1080){
            posX = 1;
        }

        if(posY <= 0){
            posY = 999;
        }
        else if (posY >= 1080){
            posY = 1;
        }

        //Set final location
        o.setLocation(posX, posY);
    }


    public static void assemble(Organism o, Organism alpha){
        //Uzair Ahmed
        //Moves the family together

        //Turns towards the alpha
        o.turnTowards(alpha.getX(), alpha.getY());
        //And moves
        o.move(o.speed);
    }

    public static void choosePrey(Organism o){
        //Uzair Ahmed
        //Chooses prey based on its attack power

        //Creates nearby organism, checks if its on the same team
        Organism nearbyOrg;
        for (int i = 0; i< organismsNearby.size(); i++){
            nearbyOrg = (Organism) organismsNearby.get(i);
            //if not a family member
            if (nearbyOrg.myFamily != o.myFamily){
              //if im better, attack
                if (nearbyOrg.threatLevel <= o.threatLevel){
                    o.chosenEnemy = nearbyOrg;
                }
                //otherwise, run!
                else{
                        o.flee();
                    }
                }
            }
        }



    public static void whoDis(Organism o){
        //Uzair Ahmed
        //Checks if the nearby organism is a friend or foe

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
      //Uzair Ahmed
      //Runs attack code

        //kills everyone
        killEveryone(o);
        //if there is a targetted enmy, attack it
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


    public static void killEveryone(Organism o){
        //Josh Dhori

        if( o.parasite != null){
            if(o.parasite.insane){
                while(o.isAlive){
                    List temp = o.getNearby();
                    Organism enemy = (Organism)temp.get(0);
                    attack(o, enemy, 0);
                }
            }
        }
    }

    public static void attack(Organism o, Organism enemy, int tatic){
        //Josh Dhori
        //attacks.

        if(enemy.isAlive){
            o.attackTatic = tatic;
            //figure out how badass your squad is (not used rn)
            o.getGroupThreatLevel();
            //basic group attack
            if (tatic == 0){
                //turn towards enemy (Uzair)
                o.turnTowards(enemy.getX(), enemy.getY());
                //move towards enemy (Uzair)
                o.move(o.speed*2);
                //Prevents the organisms from overlapping each other
                o.hit(enemy, true);
                //take a step back after attacking
                o.move(-o.speed);
            }
        }
    }

    public static void defend(Organism o, Organism enemy, int tatic){
        //Josh Dhori
        //defends.

        //Option 0: RUN!!!!
        if(tatic == 0){
            while((enemy != null)){
                o.flee();
            }
        }
        //Option 1: 1 vs 1 that dude
        else if (tatic == 1){
            while((enemy != null) && (o.isTouchingEnemy == false)){
                //turn towards enemy
                o.turnTowards(enemy.getX(), enemy.getY());
                //move towards enemy
                o.move(o.speed);
            }
            //hit enemy
            o.hit(enemy, false);
            //take a step back after attacking
            o.move(-o.speed);
        }
         //Option 3: Straight up attack that guy
        else if (tatic == 2){
            while((enemy != null)){
                attack(o, enemy, 0);
            }
        }
    }
}
