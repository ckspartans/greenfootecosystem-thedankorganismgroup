import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Extends off AbstOrganism to create a basic organism that reproduces, and moves around the screen.
 *
 * @author Uzair Ahmed
 * @author Ethan Gale
 * @version 1.9
 */

public class Organism extends AbstOrganism {
    public Organism(Organism parent) {
        //XP Upgradeable Variables-------------------------------------------------
        maxXp = parent.maxXp; //Max XP Storage
        maxHealth = parent.maxHealth; //Maximum Health
        speed = parent.speed; //Nuff Said
        att = parent.att; //Attack power
        def = parent.def; //Defensive power
        sight = parent.sight; //Sight
        if (parent.infected == true) {
            infected = true;
            new Parasite (this, parent.parasite);
            maxHealth += parent.parasite.maxHealthBoost; //Maximum Health
            speed += parent.parasite.speedBoost; //Nuff Said
            att += parent.parasite.attBoost; //Attack power
            def += parent.parasite.defBoost; //Defensive power
            sight += parent.parasite.sightBoost; //Sight
        }

        //"Live" Variables. ----------------------------------------------------
        age = 0; //Time
        health = maxHealth; //Out of maxHealth
        xp = 0; //Out of maxXp
        radius = (health*2);
        isAlive = true;
        attackMode = false;
        isAlpha = false;
        threatLevel = (maxHealth + att + def + speed);
        infected = false;

        //Team Variables
        myFamily = parent.myFamily;
        myFamily.addOrganism(this);
        familyColor = parent.familyColor;
        myColor = familyColor;

        //Declares world class
        MainWorld world;

    }

    public Organism(double smh, int smx, int ss, int sa, int sd, int ssi, Family fam , Color c){
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
        radius = (health*2);
        isAlive = true;
        attackMode = false;
        isAlpha = false;
        threatLevel = (maxHealth + att + def + speed);
        infected = false;

        //Team Variables
        myFamily = fam;
        myFamily.addOrganism(this);
        familyColor = c;
        myColor = familyColor;

        //Declares world class
        MainWorld world;
    }

    public void act(){

        //Instantiates world class
        if (world == null){
            world = (MainWorld) getWorld();
        }

        //Gets all objects in the sight radius and puts them into thier proper lists.
        List foodNearby = getObjectsInRange(sight, Food.class);
        List organismsNearby  = getObjectsInRange(sight, Organism.class);
        //Gets the food object it is touching
        Food foodBeingEaten = (Food) getOneIntersectingObject(Food.class);

        Boolean isTouchingOrg = isTouching(Organism.class);
        List touchingOrgs = getIntersectingObjects(Organism.class);

        //Starts the timer for age.
        startTimer();

        //Updates Variables like Size and Color, and caps variables like speed.
        updateandCapVariables();

        //Runs Mutation Method
        Mutation.mutate(this);

        //if (isAlive){
        //Draws the organism
        drawOrganism(myColor, Math.abs(radius+1));
        //}
        //Checks if the organism is alive, if not, return.

        if (!isAlive){
            return;
        }

        if (isAlive){
            //Runs the AI Method
            AI.think(this, foodNearby, foodBeingEaten, organismsNearby, touchingOrgs, isTouchingOrg);
        }

    }

    //Draws the organism
    public void drawOrganism(Color c, double r){
        //Uzair Ahmed
        int rad = (int)r;
        //Creates new greenfoot image
        GreenfootImage img = new GreenfootImage(rad, rad);
        //Sets the color, draws an oval, and fills it.
        img.setColor(c);
        img.drawOval(0,0,rad,rad);
        img.fillOval(0,0,rad,rad);
        if(infected == true) {
            img.setColor(Color.GREEN);
            img.fillOval(0,0,(rad-5),(rad-5));
        }

        //Sets the objects image to the created image.
        setImage(img);
    }

    //As the name suggests, updates values like size and age, and limits values like age...
    public void updateandCapVariables(){
        //Uzair Ahmed
        
        //--------------------UPDATERS---------------------
        if (myFamily.alpha == this){
            isAlpha = true;
        }
        else{
            isAlpha = false;
        }

        if (myFamily.familyAttackMode == true){
            attackMode = true;
            chosenEnemy = myFamily.targetEnemy;
        }
        //Colors the organism to show a visual rep. of age
        //every 30 seconds of an organisms lifetime
        if ((getAge()%100)==30){
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
        if(!attackMode){
            health += 0.1;
        }
        threatLevel = (maxHealth + att + def + speed);

        //--------------------LIMITERS---------------------
        if (health == 0){
            die();
        }

        //Dies after exactly 120 seconds
        if (getAge() >= 120){
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

    //Creates two new organisms and kills the OG
    public void reproduce() {
        //Uzair Ahmed

        //Creates a temporary organism with the same traits as its parent.
        Organism tempOrg1 = new Organism(this);
        Organism tempOrg2 = new Organism(this);

        //Adds it to myWorld
        world.addObject(tempOrg1,(getX()+Greenfoot.getRandomNumber(30)-15),(getY()+Greenfoot.getRandomNumber(30)-15));
        world.addObject(tempOrg2,(getX()+Greenfoot.getRandomNumber(30)-15),(getY()+Greenfoot.getRandomNumber(30)-15));

        die();
    }

    //Kills the Organism
    public void die() {
        //Uzair Ahmed
        //kills parasite
        if (parasite != null){
            parasite.die();
        }

        //sets isalive to false
        isAlive = false;
        //removes organism from family
        myFamily.remOrganism(this);

        //moves it away from the world
        setLocation(2000,1000);
        //removes the object from the world
        world.removeObject(this);
    }

    //Removes the food it touches, and adds the mass to xp
    public void consumeFood(Food foodBeingEaten){
        //Uzair Ahmed

        //Check if the organism is currently on top of something
        if (foodBeingEaten != null){
            //Remove the food
            if (!infected && foodBeingEaten.infected){
                infected = true;
                parasite = new Parasite(this);
            }
            removeTouching(Food.class);
            //Gets the mass of the food and adds it to xp.
            int foodConsumed = foodBeingEaten.foodMass;
            xp+=foodConsumed/10;
            health++;
        }
    }

    //Simple frame counter timer.
    public void startTimer(){
        //Uzair Ahmed

        //Frame counter
        age++;
    }

    //Returns the Age in seconds
    public int getAge() {
        //Uzair Ahmed

        //Calculates the age in time, by taking the frames
        //and calculating based on an anerage 60 fps
        return age/60;
    }

    public void flee(){
        turn(180);
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~////~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~////~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    //~~~~~~~~~~~~~~~~~~~~~~~~~~Dhori's Code~~~~~~~~~~~~~~~~~~~~~~~~~~////~~~~~~~~~~~~~~~~~~~~~~~~~~Dhori's Code~~~~~~~~~~~~~~~~~~~~~~~~~~////~~~~~~~~~~~~~~~~~~~~~~~~~~Dhori's Code~~~~~~~~~~~~~~~~~~~~~~~~~~//
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~////~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~////~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//

    public List getNearby(){
        //Josh Dhori
        List temp = getObjectsInRange(sight, Organism.class);
        return temp;
    }

    public void kill(Organism prey, boolean share){ //Calculates the energy gained & kills enemy
        //Josh Dhori
        if (prey.isAlive){ //if prey is alive
            if(share == true){ //sharing is true when they attack as a group
                double energyGain = (prey.maxHealth/(myFamily.familyList.size())); //int energy gain is the xp gained by each team member (prey max health divided by amount of team mates)
                for(int i = 0; i < (myFamily.familyList.size()); i++){ //distributes the energy gain between team members
                    Organism tempOrg; //creates a temp organism
                    tempOrg = (Organism)(myFamily.familyList.get(i)); //sets that temp organism as the "I" member of the family list
                    tempOrg.xp += energyGain/10; //adds xp to the member
                    tempOrg.health += prey.maxHealth/myFamily.familyList.size();
                }
            }
            else if (share == false){ //sharing is false when organism goes solo
                double energyGain = prey.maxHealth; //organism gets all their health
                xp+= energyGain; //xp increases
            }
            prey.die();
            chosenEnemy = null;
        }
    }

    public void hit(Organism prey, boolean attackOrDefend){ //hits enemy
        //Josh Dhori
        if (prey.isAlive) {//if prey is alive
            if (intersects(prey) == true){ //if touching the X organism in totalEnemy list
                if ((att - prey.def) > 0){ //if your attack is greater than their defense
                    prey.health -= (att - prey.def); //hits selected enemy for your attack - enemy defense
                }
                //This is giving problems --> prey.def -= (prey.def*(att/prey.def)); //reduce their defensive power by your attack by dividing their defense percentage eg att-->1 def -->2 new def = 1
                prey.health -= (prey.def*(att/prey.def));
                if(prey.health <=0){ //if their health goes below 0
                    kill(prey, attackOrDefend);
                }
            }
        }
    }

    public void checkIfAttacking(){ //checks if anyone in the group is attacking someone
        //Josh Dhori, Edited by Uzair Ahmed

        List organismsInSight = getObjectsInRange(sight, Organism.class); //
        Organism tempOrg;
        for(int j = 0; j < organismsInSight.size(); j++){
            tempOrg = (Organism)organismsInSight.get(j);
            if ((tempOrg.myFamily == myFamily) && (tempOrg.attackMode == true)){
                Organism enemy = tempOrg.chosenEnemy;
                chosenEnemy = enemy; //using their chosenEnemy value, which enemy is it?
                attackMode = true; //set your own attackmode to true
                int tatic = tempOrg.attackTatic; //finds which tatic that organism is using
                AI.assemble(this, enemy);
                AI.attack(this, enemy, tatic); //attack that enemy
            }
        }
    }

    public void checkDefend(){ //Check if it is under attack
        //Josh Dhori
        List tempList = getObjectsInRange(sight, Organism.class); //creates a list of organisms in sight range
        Organism tempOrg; //creates a temp organism
        for (int i = 0; i < tempList.size(); i++){
            tempOrg = (Organism)tempList.get(i); //sets the temp organism to "i" organism in the sight range list
            if(tempOrg.attackMode == true){ //if that organism has it's attackMode set to true
                if (tempOrg.chosenEnemy == this){ //if that organism is attacking this organism
                    if(tempOrg.threatLevel <= threatLevel){ //if the threat level is smaller or equal to this organism
                        AI.defend(this, tempOrg, 1); //1vs1 attack
                    }
                    else if (tempOrg.threatLevel > threatLevel){ //if the threat level is greater than this organism
                        if (tempOrg.threatLevel > getGroupThreatLevel()){ //if the organism is greater than this organism's plus its nearby family
                            AI.defend(this, tempOrg, 0); //run away
                        }
                        else if (tempOrg.threatLevel <= getGroupThreatLevel()){ //if the family threatLevel is greater or equal, attack as a group
                            AI.defend(this, tempOrg, 2); //attack as family
                        }
                    }
                }
            }
        }
    }

    public int getGroupThreatLevel(){ //gets family threat level based on if YOU see THEM
        //Josh Dhori
        int groupThreatLevel = 0; //group threat level

        List organismClose = getObjectsInRange(sight, Organism.class); //gets a list of organisms nearby
        Organism tempOrg; //creates temp organism
        for (int i = 0; i < organismClose.size(); i++){
            tempOrg = (Organism)organismClose.get(i); //sets the temp org to "i" organism in the sight range
            if(tempOrg.myFamily == myFamily){ //if the touching Organism's family is this organism's family
                groupThreatLevel += tempOrg.threatLevel; //add its attackLevel to groupThreatLevel
            }
        }
        return groupThreatLevel;
    }

    //Dhori's parasite code
    public void infect(){
        if (parasite != null){
            //Josh Dhori
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
}
