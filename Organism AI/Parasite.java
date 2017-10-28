import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Parasite here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Parasite extends AbstParasite
{
    public Parasite(Organism o, int bmh, int bs, int ba, int bd, int bsi, int mr, int p, int sr, boolean crazy, boolean oi){

        //Base Variables
        mutateRate = mr;
        power = p;
        siphonRate = sr;
        host = 0;

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

    public void act() 
    {
    }

    public  void reproduce(){
        if(host.touchingOrganism() == true){
            
        }

    };

    public void mutate(){
        //Mutation.mutate(this);
    }

    public  void siphonEnergy(){

    };

    public  void die(){

    };
}
