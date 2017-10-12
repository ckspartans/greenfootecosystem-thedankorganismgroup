import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Organism class for player. This will the the player specific functions.
 *
 * @author Uzair Ahmed
 * @version 0.5
 */
public class MyOrganism extends Organism
{
  //Takes in the custom variables (located in MainWorld), and sets them for the whole class to use.
  public MyOrganism(int smh, int smxp, int ss, int sa, int sd, int ssi, int am) {
    //Game Variables
    attMult = am;
    //XP Upgradeable Variables-------------------------------------------------
    maxHealth = smh; //Maximum Health
    maxXp = smxp; //Max XP Storage
    speed = ss; //Nuff Said
    att = sa; //Attack power
    def = sd; //Defensive power
    sight = ssi; //Sight

    //"Live" Variables. ----------------------------------------------------
    age = 0; //Time
    health = maxHealth-2; //Out of maxHealth
    xp = 0; //Out of maxXp
    radius = health*2;
    fill = new Color(0, 0, 0);
    border = new Color(0, 0, 255);
    threatLevel = (att*health*def)/(1+age);

    MainWorld world;
  }

    public void act()
    {
        //Gets all objects in the sight radius and puts them into thier proper lists.
        List foodNearby = getObjectsInRange(sight, Food.class);
        List enemiesNearby  = getObjectsInRange(sight, EnemyOrganism.class);
        //Gets the food object it is touching
        Food foodBeingEaten = (Food) getOneIntersectingObject(Food.class);

        //Draws the organism
        drawOrganism(Color.GREEN, radius);

        //Runs the AI Method
        AI.think(this, foodNearby, enemiesNearby, foodBeingEaten);
    }
    
    public void consumeFood(){
        if (getOneIntersectingObject(Food.class)!= null){
          //world.foodEaten ++;
          //world.removeObject(); //Try and find a way to remove the object it touches
          //call grow (or add it to a temp health variable) to increase health
          //add xp
        }
        
    }
    
    public void consumePlayer(){
        //if (getOneIntersectingObject(TestOrganism.class)!=null){ //Change testOrganism to enemyOrganism
            //do stuff to return damage int to enemy and to self.
            //add xp
       // }
        
    }
    
    public void shrink(int damageTaken){
        //takes damagetaken and applys it to health and radius
    }
    
    public void grow(int damageGiven){
        //takes damageGiven and applys it to health and radius
    }
    
    public void age(){
        //start a timer in act() and use that value to edit age value, and color
    }  
    
    public void reproduce(){
        //create new myOrganism object and do magic
        //remove xp
        //drop in size
    }
    
    public void die(){
        //Remove the object.
        //disperse as food througout the world
    }
    
}
