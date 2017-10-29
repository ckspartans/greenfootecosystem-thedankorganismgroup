import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Parasite here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Parasite extends Actor
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
    
    public Parasite(Organism o, int bmh, int bs, int ba, int bd, int bsi, int mr, int p, int sr, boolean crazy, boolean oi){

        //Base Variables
        mutateRate = mr;
        power = p;
        siphonRate = sr;
        host = o;

        //Boost Variables
        attBoost = bmh;
        defBoost = bd;
        maxHealthBoost = bmh;
        speedBoost = bs;
        sightBoost = bsi;

        //Behaviour booleans
        organismInfections = oi;
        attackAllies = crazy;

    }

    public void act() {
    }

    public  void infect(){

    };

    public void mutate(){
        //Mutation.mutate(this);
    }

    public  void siphonEnergy(){

    };

    public  void die(){

    };
    
    public void boost(){
        
    }
}
