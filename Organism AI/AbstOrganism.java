import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Abstract Organism, this is the base class for Organism
 *
 * @author Uzair Ahmed
 * @author Josh Dhori
 * @version 2.0
 */
public abstract class AbstOrganism extends Actor {

    //"Live" Variables
    public int age; //Time in seconds
    public double health; //Health
    public double xp; //XP - allows for RPG-like store for mutation
    public double radius; //Based off health, to visually see health
    public Boolean attackMode; //if the organism is attacking
    public Boolean isAlive;
    public Boolean isTouchingEnemy;
    public Boolean isAlpha;
    public Boolean infected;

    public int attackTatic; //which attackTatic to use
    public double threatLevel;
    public Organism chosenEnemy;
    public Parasite parasite;

    //XP Upgradeable Variables
    public double maxHealth; //Maximum Health
    public double maxXp; //Max XP Storage
    public int speed; //Nuff Said
    public int att; //Attck power
    public int def; //defensive power
    public int sight; //how far it can see
    public int parasiteResistance;

    Family myFamily; //Variable to FamilyReference
    public Color myColor; //current color

    public HealthBar hpBar;

    public GreenfootSound sandstorm;

    MainWorld world;

    public abstract void init(double smh, double smx, int ss, int sa, int sd, int ssi, Family fam);

    public abstract void drawOrganism(Color c, double r);

    public abstract void updateandCapVariables();

    public abstract void consumeFood(Food foodBeingEaten);

    public abstract void reproduce();

    public abstract void startTimer();

    public abstract void die();

    public abstract void flee();

    public abstract List getNearby();

    public abstract void kill(Organism prey, boolean share);

    public abstract void hit(Organism prey, boolean attackOrDefend);

    public abstract void checkIfAttacking();

    public abstract void checkDefend();

    public abstract int getGroupThreatLevel();

    public abstract void infect();
}
