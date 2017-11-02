import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Parasite infects organisms and decreeases their health ovetime
 * Parasite also has stat boosts for it's host organism
 *
 * @Josh Dhori
 * @1.0
 */

public class Parasite extends Actor{
    //"Live" Variables
    public int mutateRate = 100; //XP cap before mutation
    public int xp = 0; //XP - allows for RPG-like store for mutationpublic int xp = 0; //xp
    public int siphon = 0;

    //XP Upgradeable Variables
    public boolean organismInfections = false; //can it infect other organisms by touching
    public boolean insane = false; //goes insane and attacks everyone

    public int baseSiphonRate = 10; //amount of health it steals
    public int power = 0; //the strength of the parasite (counter against Organism.parasiteResistance)

    public int attBoost = 0; //attack boost
    public int defBoost = 0; //defense boost
    public int maxHealthBoost = 0; //max health boost
    public int speedBoost = 0; //speed boost
    public int sightBoost = 0; //sight boost
    public int infectChance = 10; //chance to infect an organism that is touching the host

    Organism host;
    MainWorld world;

    public Parasite(Organism o){
        //Josh Dhori
        //Used when host is infected by a plant

        host = o;
    }

    public Parasite(Organism o, Parasite p){
        //Josh Dhori
        //This is used when the host infects another organism

        //Base Variables
        mutateRate = p.mutateRate;
        power = p.power;
        baseSiphonRate = p.baseSiphonRate;
        host = o;

        //Boost Variables
        attBoost = p.attBoost;
        defBoost = p.defBoost;
        maxHealthBoost = p.maxHealthBoost;
        speedBoost = p.speedBoost;
        sightBoost = p.sightBoost;
        infectChance = p.infectChance;

        //Behaviour booleans
        organismInfections = p.organismInfections;
        insane = p.insane;

    }

    public void update(){
        //Josh Dhori
        //updates the parasite

        if(siphon == 120){
          //siphons the health from host
            siphonHealth();
            siphon = 0;
        }
        //if xp has reached mutation threshold then mutate
        if (xp >= mutateRate){
            mutate();
        }
        siphon++;
    }

    public  void infect(Organism o){
        //Josh Dhori
        //spawns a parasite in "o" organism

        //if the organism reproduces or touches another organism parasite has a chance to infect
        o.parasite = new Parasite(o, this);
    }

    public void mutate(){
        //Josh Dhori

        //sets xp to 0
        xp = 0;
        //runs Ethan's parasite mutation code
        Mutation.mutate(this);
    }

    public int siphonRate(){
        //Josh Dhori
        //calculates the siphon rate of this parasite

        //Laura needs to edit these values to balance it
        //This returns "rate" which is orginally set to base siphon rate
        int rate = baseSiphonRate;
        //adds ____Boost/10 to rate
        rate += (attBoost/10);
        rate += (defBoost/10);
        rate += (maxHealthBoost/10);
        rate += (speedBoost/10);
        rate += (infectChance/10);
        rate += (mutateRate/10);
        rate += (power/10);

        //if this can infect other organisms
        if(organismInfections){
          //then increase the rate by 2
            rate += 2;
        }
        //if this organism is crazy
        if(insane){
          //reduce the rate by 10 (so it can live longer and kill more)
            rate -= 10;
        }
        //return the rate
        return rate;
    }

    public  void siphonHealth(){
        //Josh Dhori
        //main function that siphons the health of it's host organism

        //subtract this parasite's siphonRate from it's host's health
        host.health -= siphonRate();
        //if the host's health is below or equal 0
        if(host.health <= 0){
          //kills the host
            host.die();
        }
        //if the host is still alive
        else{
          //convert the amount of health stolen from the host to xp for this parasite
            xp += siphonRate();
        }
    }

    public  void die(){
        //Josh Dhori
         //parasite die function

         //switches the host infected boolean to false
        host.infected = false;
        //sets the host's parasite to null (no parasite)
        host.parasite = null;
        //world.removeObject(this); //remove this object from world
    }

    public void boost(boolean mhb, boolean sb,boolean ab,boolean db,boolean sib){
        //Josh Dhori
        //boost functions

        /*
         * This runs when Ethan mutates the parasite
         * Once the parasite mutates,
         * it applys that boost to the host
         */

        //if the boost is ______, add boost to _______o
        if(mhb){
            host.maxHealth += maxHealthBoost;
        }
        if (sb){
            host.speed += speedBoost;
        }
        if (ab){
            host.att += attBoost;
        }
        if (db){
            host.def += defBoost;
        }
        if (sib){
            host.sight += sightBoost;
        }
    }

    public boolean infectionChance(){
        //Josh Dhori
        //chance for the organism to infect another organism
        /*
         * If this parasite can infect other organisms
         * then this parasite has a (infectChance)% to infect that organism
         */

        if((organismInfections) && (infectChance >= Greenfoot.getRandomNumber(100))){
            return true; //infect the organism
        }
        else{
            return false; //dont infect organism
        }
    }
}
