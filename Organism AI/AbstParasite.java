import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class AbstParasite here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class AbstParasite extends Actor
{
    public int siphonRate; //amount of energy it steals
    public int power; //the strength of the parasite (determines what it can infect)
    public boolean organismInfections;
    public int mutateRate; //speed of which it mutates
    
    public int attBoost;
    public int defBoost;
    public int maxHealthBoost;
    public int speedBoost;
    public int sightBoost;
    
    boolean attackAllies;
    
    Organism host;
    

    MainWorld world;
    public abstract void reproduce();

    public abstract void siphonEnergy();
    
    public abstract void mutate();

    public abstract void die();
}
