import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Parasite infects organisms and decreeases their health ovetime
 * Parasite also has stat boosts for it's host organism
 * 
 * 
 * @Josh Dhori
 * @1.0
 */
public class Parasite extends Actor{
    //"Live" Variables
    public int mutateRate = 100; //XP cap before mutation    
    public int xp = 0; //XP - allows for RPG-like store for mutationpublic int xp = 0; //xp

    //XP Upgradeable Variables
    public boolean organismInfections = false; //can it infect other organisms by touching
    public boolean insane = false; //goes insane and attacks everyone

    public int baseSiphonRate = 0; //amount of health it steals
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
        //Used when host is infected by a plant
        host = o;
    }

    public Parasite(Organism o, int bmh, int bs, int ba, int bd, int bsi, int mr, int p, int sr, int ic, boolean crazy, boolean oi){
        //This is used when the host infects another organism

        //Base Variables
        mutateRate = mr;
        power = p;
        baseSiphonRate = sr;
        host = o;

        //Boost Variables
        attBoost = ba;
        defBoost = bd;
        maxHealthBoost = bmh;
        speedBoost = bs;
        sightBoost = bsi;
        infectChance = ic;

        //Behaviour booleans
        organismInfections = oi;
        insane = crazy;

    }

    public void update(){ //updates the parasite
        siphonHealth(); //siphons the health from host
        if (xp >= mutateRate){ //if xp has reached mutation threshold then mutate
            mutate();
        }
    }

    public  void infect(Organism o){ //spawns a parasite in "o" organism
        //if the organism reproduces or touches another organism parasite has a chance to infect
        o.parasite = new Parasite(o, maxHealthBoost, speedBoost, attBoost, defBoost, sightBoost, mutateRate, power, baseSiphonRate, infectChance, insane, organismInfections); //sets the parasite in "o" organism to be a duplicate to this
        o.parasite.mutate(); //mutates the new parasite
    }

    public void mutate(){
        xp = 0; //sets xp to 0
        Mutation.mutate(this); //runs Ethan's parasite mutation code
    }

    public int siphonRate(){ //calculates the siphon rate of this parasite
        //Laura needs to edit these values to balance it
        int rate = baseSiphonRate; //This returns "rate" which is orginally set to base siphon rate 
        rate += (attBoost/10); //adds attBoost / 10 to rate
        rate += (defBoost/10); //adds defBoost / 10 to rate
        rate += (maxHealthBoost/10); //adds maxHealthBoost / 10 to rate
        rate += (speedBoost/10); //adds speedBoost / 10 to rate
        rate += (infectChance/10); //adds infectChance / 10 to rate
        rate += (mutateRate/10); //adds mutateRate / 10 to rate
        rate += (power/10); //adds power / 10 to rate

        if(organismInfections){ //if this can infect other organisms
            rate += 2; //then increase the rate by 2
        }

        if(insane){ //if this organism is crazy
            rate -= 10; //reduce the rate by 10 (so it can live longer and kill more)
        }
        return rate; //return the rate
    }

    public  void siphonHealth(){ //main function that siphons the health of it's host organism
        host.health -= siphonRate(); //subtract this parasite's siphonRate from it's host's health
        if(host.health <= 0){ //if the host's health is below or equal 0
            host.die(); //kills the host
        }
        else{ //if the host is still alive
            xp += (siphonRate); //convert the amount of health stolen from the host to xp for this parasite
        }
    }

    public  void die(){ //parasite die function
        host.infected = false; //switches the host infected boolean to false
        host.parasite = null; //sets the host's parasite to null (no parasite)
        world.removeObject(this); //remove this object from world
    }

    public void boost(boolean mhb, boolean sb,boolean ab,boolean db,boolean sib){ //boost functions
        /*
         * This runs when Ethan mutates the parasite
         * Once the parasite mutates, 
         * it applys that boost to the host
         */
        if(mhb){ //if the boost is maxHealth
            host.maxHealth += maxHealthBoost; //add parasite's boost to maxHealth
        }
        if (sb){ //if the boost is speed
            host.speed += speedBoost; //add parasite's boost to speed
        }
        if (ab){ //if the boost is att 
            host.att += attBoost; //add parasite's boost to att
        }
        if (db){ //if the boost is def
            host.def += defBoost; //add parasite's boost to def
        }
        if (sib){ //if the boost is sight
            host.sight += sightBoost; //add parasite's boost to sight
        }
    }

    public boolean infectionChance(){ //chance for the organism to infect another organism
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
