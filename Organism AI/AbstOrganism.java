import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
* AbstOrganism is the base class for all organism operations. Has functionality to feed, grow, clone, kill, move and mutate an organism.
* This is Mr. Rowbottom's version for us to use
* Needs lots of clean up
* 
* @author Uzair Ahmed 
* @version 0.0.3
*/
public abstract class AbstOrganism extends Actor
{
    static public ArrayList <AbstOrganism> lifeforms; //list of all the organsims in the game
    public ArrayList <Object> prey; //list of all that the types of organism can feed on
    public ArrayList <Object> predators; //list of all the types of organsims that the organism can be eaten by 
    
    public GreenfootImage [] imgs;
    
    public int age;
    public double health;
    public int trophicLevel;
    public int energy;
    public int size;
    public int speed;
    
    public int repro_age;
    public int repro_energy;
    public int mutation_rate;
    public int att;
    public int def;

    MainWorld world;
    
    public abstract void move();
    
    public abstract void feed();
    
    public abstract void grow();
    
    public abstract void reproduce();
    
    public abstract int age();
    
    public abstract void die();
    
    public abstract void mutate(); 

}
