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
    public int baseSiphonRate = 0; //amount of health it steals
    public int power = 0; //the strength of the parasite (counter against Organism.parasiteResistance)
    public boolean organismInfections = false; //can it infect other organisms by touching
    public int mutateRate = 100; //XP cap before mutation    
    public int xp = 0; //XP - allows for RPG-like store for mutationpublic int xp = 0; //xp

    //XP Upgradeable Variables
    public int attBoost = 0; //attack boost
    public int defBoost = 0; //defense boost
    public int maxHealthBoost = 0; //max health boost
    public int speedBoost = 0; //speed boost
    public int sightBoost = 0; //sight boost
    public int infectChance = 10; //chance to infect an organism that is touching the host (requires organismInfections)

    boolean insane = false; //goes insane and attacks everyone
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

    public void act() {
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
        o.parasite.mutate();
    }

    public void mutate(){
        xp = 0;
        Mutation.mutate(this);
    }

    public  void siphonHealth(){
        int h1 = host.health;
        host.health -= siphonRate();
        int h2 = host.health;

        if(host.health <= 0){
            host.die();
        }
        else{
            xp += (h2-h1);
        }
    }

    public  void die(){
        host.infected = false;
        host.parasite = null;
        world.removeObject(this);
    }

    public void boost(boolean mhb, boolean sb,boolean ab,boolean db,boolean sib){
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
        if(infectChance >= Greenfoot.getRandomNumber(100)){
            return true;
        }
        else{
            return false;
        }
    }

    public int siphonRate(){
        int rate = baseSiphonRate;

        rate += (attBoost/10);
        rate += (defBoost/10);
        rate += (maxHealthBoost/10);
        rate += (speedBoost/10);
        rate += (infectChance/10);
        rate += (mutateRate/10);
        rate += (power/10);
        if(organismInfections){
            rate += 2;
        }
        if(insane){
            rate -= 10;
        }
        return rate;
    }
}
