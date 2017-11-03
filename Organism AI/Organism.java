import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Extends off AbstOrganism to create a basic organism that reproduces, and moves around the screen.
 *
 * @author Uzair Ahmed
 * @author Josh Dhori
 * @author Cameron Dickie
 * @version 2.0
 */

public class Organism extends AbstOrganism {

    public Organism(double smh, double smx, int ss, int sa, int sd, int ssi, Family fam){
        // Uzair Ahmed
        //Runs on creation of a new Organism

        init(smh,smx,ss,sa,sd,ssi,fam);
    }

    public Organism(Organism p) {
        //Josh Dhori

        init(p.maxHealth, p.maxXp, p.speed, p.att, p.def, p.sight, p.myFamily);
        if (p.infected == true) {
            infected = true;
            parasite = new Parasite (this, p.parasite);
            maxHealth += p.parasite.maxHealthBoost; //Maximum Health
            speed += p.parasite.speedBoost; //Nuff Said
            att += p.parasite.attBoost; //Attack power
            def += p.parasite.defBoost; //Defensive power
            sight += p.parasite.sightBoost; //Sight
        }
    }

    public void init(double smh, double smx, int ss, int sa, int sd, int ssi, Family fam){
        //Uzair Ahmed
        //Initializes all the variables required

        maxXp = smx; //Max XP Storage
        maxHealth = smh; //Maximum Health
        speed = ss; //Nuff Said
        att = sa; //Attack power
        def = sd; //Defensive power
        sight = ssi; //Sight

        //"Live" Variables. ----------------------------------------------------
        age = 0; //Time
        health = maxHealth; //Out of maxHealth
        xp = 0; //Out of maxXp
        radius = (health*2); //Radius
        isAlive = true; //is it alive
        attackMode = false; //Is it attacking
        infected = false; //Is it infected
        threatLevel = (maxHealth + att + def + speed); //threatLevel

        //Team Variables
        myFamily = fam; //Family reference
        myFamily.addOrganism(this); //adds organism to family
        myColor = myFamily.color; //Original family color for reference

        //Declares world class
        MainWorld world;
        
        hpBar = new HealthBar(this, 10, maxHealth, health);

    }

    public void act(){
        //Runs all the organism code.

        //Instantiates world class
        if (world == null){
            world = (MainWorld) getWorld();
        }
       
            
        
        //Gets all objects in the sight radius and puts them into thier proper lists.
        List foodNearby = getObjectsInRange(sight, Food.class);
        List organismsNearby  = getObjectsInRange(sight, Organism.class);
        //Gets the food object it is touching
        Food foodBeingEaten = (Food) getOneIntersectingObject(Food.class);

        //Checks if its touching an organism, and also gets a list of it, if not null
        Boolean isTouchingOrg = isTouching(Organism.class);
        List touchingOrgs = getIntersectingObjects(Organism.class);

        //Starts the timer for age.
        startTimer();

        //Updates Variables like Size and Color, and caps variables like speed.
        updateandCapVariables();

        //Runs Mutation Method
        Mutation.mutate(this);

        //Checks if the organism is alive, if not, return. (placed here specifically because mutate
        //and updateAndCapVariables runs the die method, but should not run think.)
        if (!isAlive){
            return;
        }
        
        //Draws the organism
        drawOrganism(myColor, Math.abs(radius+1));

        //Runs the AI Method
        AI.think(this, foodNearby, foodBeingEaten, organismsNearby, touchingOrgs, isTouchingOrg);

    }

    public void drawOrganism(Color c, double r){
        //Uzair Ahmed,
        //Draws the organism

        int rad = (int)r;
        //Creates new greenfoot image
        GreenfootImage img = new GreenfootImage(rad+1, rad+1);
        //Sets the color, draws an oval, and fills it.
        img.setColor(c);

        img.drawOval(0,0,rad,rad);
        img.fillOval(0,0,rad,rad);

        if(infected == true) {
            img.setColor(Color.WHITE);
            img.fillOval(0,0,(rad-5),(rad-5));
        }

        //Sets the objects image to the created image.
        setImage(img);
        
        //Runs Cameron's Draw Function
        updateBars();
    }

    public void updateandCapVariables(){
        //Uzair Ahmed
        //As the name suggests, updates values like size and age, and limits values like age...

        //--------------------UPDATERS---------------------

        //Colors the organism to show a visual rep. of age every 30 seconds of an organisms lifetime
        if ((age%4)==0){
            //Creates temporary color
            Color c = myColor;
            //Sets color equal to current color, and gets the rgb vals
            int r = c.getRed()-1;
            int g = c.getGreen()-1;
            int b = c.getBlue()-1;
            //caps the color to its lowest point
            if (r<0){r=0;}if (g<0){g=0;}if (b<0){b=0;}
            //sets current color to newly created color
            myColor = new Color(r,g,b);
        }

        //Updates the radius to match the size
        radius = health/5;

        //Increases health slowly if its not being attacked
        if(!attackMode){
            health -= 0.25;
        }

        //Updates threatLevel
        threatLevel = (maxHealth + att + def + speed);

        //--------------------LIMITERS---------------------
        //Dies when the health is zero
        if (health == 0){
            die();
        }

        //Dies after exactly 30 seconds
        if (age >= 900){
            die();
        }

        //Caps maxhealth to max buyable health
        if (maxHealth > world.maxBuyableMaxHealth){
            maxHealth = world.maxBuyableMaxHealth;
        }

        //Caps health to the maximum health
        if (health > maxHealth){
            health = maxHealth;
        }

        //Caps speed to max buyable speed
        if (speed > world.maxBuyableSpeed){
            speed = world.maxBuyableSpeed;
        }

        //Sets minimum speed to 1
        if (speed<1){
            speed = 1;
        }

        //Caps sight to maxBuyableSight
        if (sight > world.maxBuyableSight){
            sight = world.maxBuyableSight;
        }

        //Caps attack to max buyable attack
        if (att > world.maxBuyableAtt){
            att = world.maxBuyableAtt;
        }

        //Caps defense to max buyable defense
        if (def > world.maxBuyableDef){
            def = world.maxBuyableDef;
        }
        

    }

    public void reproduce() {
        //Uzair Ahmed
        //Creates two new organisms and kills the OG

        //Creates a temporary organism with the same traits as its parent.
        Organism tempOrg1 = new Organism(this);
        Organism tempOrg2 = new Organism(this);

        //Adds it to myWorld
        world.addObject(tempOrg1,(getX()+Greenfoot.getRandomNumber(30)-15),(getY()+Greenfoot.getRandomNumber(30)-15));
        world.addObject(tempOrg2,(getX()+Greenfoot.getRandomNumber(30)-15),(getY()+Greenfoot.getRandomNumber(30)-15));

        //nuff said
        die();
    }

    public void die() {
        //Uzair Ahmed
        //Kills the Organism

        //Kills parasite if there was one
        if (parasite != null){
            parasite.die();
        }

        //sets isalive to false
        isAlive = false;

        //removes organism from family
        myFamily.remOrganism(this);
        
        //moves it away from the world (to avoid any confusions with AIs)
        setLocation(2000,1000);

        //removes the object from the world
        world.removeObject(this);
        
        //removes health bar
        world.removeObject(hpBar);
    }

    public void consumeFood(Food foodBeingEaten){
        //Uzair Ahmed
        //Removes the food it touches, and adds the mass to xp

        //Check if the organism is currently on top of something
        if (foodBeingEaten != null){

            //Did I eat a parasite? I am now infected
            if (!infected && foodBeingEaten.infected){
                infected = true;
                parasite = new Parasite(this);
            }

            //Remove the food
            removeTouching(Food.class);

            //Gets the mass of the food and adds it to xp and health.
            int foodConsumed = foodBeingEaten.foodMass;
            xp+=foodConsumed/10;
            health+=5;
        }
    }

    public void startTimer(){
        //Uzair Ahmed
        //Simple frame counter timer.

        //Frame counter
        age++;
    }

    public void flee(){
        turn(180);
    }

    public List getNearby(){
        //Josh Dhori

        return getObjectsInRange(sight, Organism.class);
    }

    public void kill(Organism prey, boolean share){
        //Josh Dhori
        //Calculates the energy gained & kills enemy

        //if prey is alive
        if (prey.isAlive){
            //sharing is true when they attack as a group
            if(share == true){
                //int energy gain is the xp gained by each team member (prey max health divided by amount of team mates)
                double energyGain = (prey.maxHealth/(myFamily.familyList.size()));
                //distributes the energy gain between team members
                for(int i = 0; i < (myFamily.familyList.size()); i++){
                    //creates a temp organism
                    Organism tempOrg;
                    //sets that temp organism as the "I" member of the family list
                    tempOrg = (Organism)(myFamily.familyList.get(i));
                    //adds xp to the member
                    tempOrg.xp += energyGain/10;
                    //adds health to the member
                    tempOrg.health += prey.maxHealth/myFamily.familyList.size();
                }
            }
            //sharing is false when organism goes solo
            else if (share == false){
                //organism gets all their health
                double energyGain = prey.maxHealth;
                //xp increases
                xp+= energyGain;
            }
            prey.die();

            chosenEnemy = null;
        }
    }

    public void hit(Organism prey, boolean attackOrDefend){
        //Josh Dhori
        //hits enemy

        if (prey.isAlive) {//if prey is alive
            if (intersects(prey) == true){ //if touching the X organism in totalEnemy list
                if ((att - prey.def) > 0){ //if your attack is greater than their defense
                    prey.health -= (att - prey.def); //hits selected enemy for your attack - enemy defense
                    health += (att - prey.def);
                }
                //This is giving problems --> prey.def -= (prey.def*(att/prey.def)); //reduce their defensive power by your attack by dividing their defense percentage eg att-->1 def -->2 new def = 1
                prey.health -= (prey.def*(att/prey.def));
                if(prey.health <=0){ //if their health goes below 0
                    kill(prey, attackOrDefend);
                }
            }
        }
    }

    public void checkIfAttacking(){
        //Josh Dhori
        //checks if anyone in the group is attacking someone

        List organismsInSight = getObjectsInRange(sight, Organism.class); //
        Organism tempOrg;
        for(int j = 0; j < organismsInSight.size(); j++){
            tempOrg = (Organism)organismsInSight.get(j);
            if ((tempOrg.myFamily == myFamily) && (tempOrg.attackMode == true)){
                Organism enemy = tempOrg.chosenEnemy;
                //using their chosenEnemy value, which enemy is it?
                chosenEnemy = enemy;
                //set your own attackmode to true
                attackMode = true;
                //finds which tatic that organism is using
                int tatic = tempOrg.attackTatic;
                //Brings nearby family members into the fight
                AI.assemble(this, enemy);
                //attack that enemy
                AI.attack(this, enemy, tatic);
            }
        }
    }

    public void checkDefend(){
        //Josh Dhori
        //Check if it is under attack

        //creates a list of organisms in sight range
        List tempList = getObjectsInRange(sight, Organism.class);
        //creates a temp organism
        Organism tempOrg;
        for (int i = 0; i < tempList.size(); i++){
            //sets the temp organism to "i" organism in the sight range list
            tempOrg = (Organism)tempList.get(i);
            //if that organism has it's attackMode set to true
            if(tempOrg.attackMode == true){
                //if that organism is attacking this organism
                if (tempOrg.chosenEnemy == this){
                    //if the threat level is smaller or equal to this organism
                    if(tempOrg.threatLevel <= threatLevel){
                        AI.defend(this, tempOrg, 1); //1vs1 attack
                    }
                    //if the threat level is greater than this organism
                    else if (tempOrg.threatLevel > threatLevel){
                        //if the organism is greater than this organism's plus its nearby family
                        if (tempOrg.threatLevel > getGroupThreatLevel()){
                            AI.defend(this, tempOrg, 0);
                            //run away
                        }
                        //if the family threatLevel is greater or equal, attack as a group
                        else if (tempOrg.threatLevel <= getGroupThreatLevel()){
                            //attack as family
                            AI.defend(this, tempOrg, 2);
                        }
                    }
                }
            }
        }
    }

    public int getGroupThreatLevel(){
        //Josh Dhori
        //gets family threat level based on if YOU see THEM

        //group threat level
        int groupThreatLevel = 0;
        //gets a list of organisms nearby
        List organismClose = getObjectsInRange(sight, Organism.class);
        //creates temp organism
        Organism tempOrg;
        for (int i = 0; i < organismClose.size(); i++){
            //sets the temp org to "i" organism in the sight range
            tempOrg = (Organism)organismClose.get(i);
            //if the touching Organism's family is this organism's family
            if(tempOrg.myFamily == myFamily){
                //add its attackLevel to groupThreatLevel
                groupThreatLevel += tempOrg.threatLevel;
            }
        }
        return groupThreatLevel;
    }

    public void infect(){
        //Josh Dhori
        //Infects an organism with a parasite

        if (parasite != null){
            if(isTouching(Organism.class)){
                List touchingOrganisms = getIntersectingObjects(Organism.class);
                Organism touchingOrg;
                for (int i = 0; i < touchingOrganisms.size(); i++) {
                    touchingOrg = (Organism)touchingOrganisms.get(i);
                    if((!touchingOrg.infected) && (parasite.infectionChance()) && (parasite.power >= touchingOrg.parasiteResistance)){
                        parasite.infect(touchingOrg);
                    }
                }
            }
        }
    }
   
    
    public void updateBars() {
        //Cameron Dickie
        //Updates Health Bars
        
        if(hpBar.isActive == false) {
           world.addObject(hpBar, getX(), getY() - (int)radius - 10);
           hpBar.isActive = true;
        }
        hpBar.updateValue(health, maxHealth);
        hpBar.setLocation(this.getX(), this.getY() -  (int)radius - 10);
    }
}
