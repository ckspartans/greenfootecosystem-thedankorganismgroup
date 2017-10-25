import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Abstract Organism, this is the base class for Organism
 *
 * @author Class
 * @author Uzair Ahmed
 * @author Ethan Gale
 * @author Josh Dhori
 * @version 1.0
 */
public abstract class AbstOrganism extends Actor {

    //"Live" Variables
    public int age; //Time in seconds
    public int health; //Health
    public int xp; //XP - allows for RPG-like store for mutation
    public int radius; //Based off health, to visually see health
    public Boolean attackMode; //if the organism is attacking
    public Boolean isAlive;
    public Boolean isAlpha;
    
    public int attackTatic; //which attackTatic to use
    public int threatLevel;
    public Organism chosenEnemy;
    
    
    //XP Upgradeable Variables
    public int maxHealth; //Maximum Health
    public int maxXp; //Max XP Storage
    public int speed; //Nuff Said
    public int att; //Attck power
    public int def; //defensive power
    public int sight; //how far it can see

    Family myFamily;
    public Color familyColor;
    public Color myColor;
    
    public int name;
    
    MainWorld world;
    public abstract void drawOrganism(Color c, int rad);

    public abstract void consumeFood(Food foodBeingEaten);

    public abstract void mutate();

    public abstract void updateandCapVariables();

    public abstract void reproduce();

    public abstract void startTimer();

    public abstract int getAge();

    public abstract void die();
}
