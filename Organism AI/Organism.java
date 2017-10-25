import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Extends off AbstOrganism to create a basic organism that reproduces, and moves around the screen.
 *
 * @author Uzair Ahmed
 * @author Ethan Gale
 * @version 1.7
 */

public class Organism extends AbstOrganism {
    public Organism(int smh, int smxp, int ss, int sa, int sd, int ssi, Family fam, Color c, int nameNum) {
        //XP Upgradeable Variables-------------------------------------------------
        maxHealth = smh; //Maximum Health
        maxXp = smxp; //Max XP Storage
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

        //Team Variables
        myFamily = fam;
        myFamily.addOrganism(this);
        familyColor = c;
        myColor = familyColor;
        name = nameNum;

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


        //Starts the timer for age.
        startTimer();

        //Updates Variables like Size and Color, and caps variables like speed.
        updateandCapVariables();

        //Runs Mutation Method
        mutate();

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
            AI.think(this, foodNearby, organismsNearby, foodBeingEaten);
        }

    }

    //Draws the organism
    public void drawOrganism(Color c, int rad){
        //Creates new greenfoot image
        GreenfootImage img = new GreenfootImage(rad, rad);
        //Sets the color, draws an oval, and fills it.
        img.setColor(c);
        img.drawOval(0,0,rad,rad);
        img.fillOval(0,0,rad,rad);
        //Sets the objects image to the created image.
        setImage(img);
    }

    //As the name suggests, updates values like size and age, and limits values like age...
    public void updateandCapVariables(){
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
        radius = health*2;

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

    public void mutate(){
        //Created by Ethan Gale
        if (xp >= 10){
            if (Greenfoot.getRandomNumber(5) == 1){ //mutation rate
                if (Greenfoot.getRandomNumber(5) == 1){
                    reproduce();
                    xp = 0;
                }
                else{
                    int chosenMutation = Greenfoot.getRandomNumber(5);
                    if (chosenMutation == 1){ //attack
                        maxHealth += 5;
                    }
                    else if (chosenMutation == 2){ //defense
                        speed += 1;
                    }
                    else if (chosenMutation == 3){ //speed
                        sight += 2;
                    }
                    else if (chosenMutation == 4){ //sight range
                        att += 10;
                    }
                    else if (chosenMutation == 5){ //max health
                        def +=2;
                    }
                    xp = 0;
                }
            }
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

        threatLevel = (maxHealth + att + def + speed); //updates threatLevel
    }

    //Creates two new organisms and kills the OG
    public void reproduce() {
        //Creates a temporary organism with the same traits as its parent.
        Organism tempOrg1 = new Organism(maxHealth, xp,speed, att, def, sight, myFamily, familyColor, name+1);
        Organism tempOrg2 = new Organism(maxHealth, xp,speed, att, def, sight, myFamily, familyColor, name+2);

        //Adds it to myWorld
        world.addObject(tempOrg1,(getX()+Greenfoot.getRandomNumber(30)-15),(getY()+Greenfoot.getRandomNumber(30)-15));
        world.addObject(tempOrg2,(getX()+Greenfoot.getRandomNumber(30)-15),(getY()+Greenfoot.getRandomNumber(30)-15));

        die();
    }

    //Kills the Organism
    public void die() {
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
        //Check if the organism is currently on top of something
        if (foodBeingEaten != null){
            //Remove the food
            removeTouching(Food.class);
            //Gets the mass of the food and adds it to xp.
            int foodConsumed = foodBeingEaten.foodMass;
            xp+=foodConsumed/10;
            health++;
        }
    }

    //Simple frame counter timer.
    public void startTimer(){
        //Frame counter
        age++;
    }

    //Returns the Age in seconds
    public int getAge() {
        //Calculates the age in time, by taking the frames
        //and calculating based on an anerage 60 fps
        return age/60;
    }

    public void flee(){
        turn(180);
    }

    //Dhori's Code
    public void kill(Organism prey, boolean share){ //Calculates the energy gained & kills enemy
        if (!prey.isAlive){ //if prey is alive
            if(share == true){ //sharing is true when they attack as a group
                int energyGain = (prey.maxHealth/(myFamily.familyList.size())); //int energy gain is the xp gained by each team member (prey max health divided by amount of team mates)
                for(int i = 0; i > (myFamily.familyList.size()); i++){ //distributes the energy gain between team members
                    Organism tempOrg; //creates a temp organism
                    tempOrg = (Organism)(myFamily.familyList.get(i)); //sets that temp organism as the "I" member of the family list
                    tempOrg.xp += energyGain/10; //adds xp to the member
                    tempOrg.health += prey.maxHealth/myFamily.familyList.size();
                }
            }
            else if (share == false){ //sharing is false when organism goes solo
                int energyGain = prey.maxHealth; //organism gets all their health
                xp+= energyGain; //xp increases
            }
            prey.die();
            chosenEnemy = null;
        }
    }

    public void hit(Organism prey, boolean attackOrDefend){ //hits enemy
        if (prey.isAlive) {//if prey is alive
            if (istouchingEnemy(prey)){ //if touching the X organism in totalEnemy list
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

    public boolean istouchingEnemy(Organism enemy){
        System.out.println("Running isTouchingEnemy");
        if (isTouching(Organism.class) == true){ //if touching any organism
            Organism touchingOrganism;
            System.out.println("Created temp Organism");
            
            //creates a temp organism
            if (isAlive){
                System.out.println("Setting temp Organism to intersecting");
                touchingOrganism = (Organism)(getOneIntersectingObject(Organism.class));
                System.out.println("Finsihed setting Organism");
                //sets that temp organism to the ogranism the this organism is touching
                System.out.println("I am touching: " + touchingOrganism.name);
                System.out.println("My chosen enemy is: " + chosenEnemy.name);
                System.out.println("I am atttacking: " + enemy.name);
                enemy.myColor = new Color(227,37,107);
                
                if (touchingOrganism == enemy){ //if the touching organism is the enemy
                    System.out.println("touching enemy");
                    return true;
                }
                else{
                    System.out.println("touching organism is NOT enemy");
                    return false;
                }
            }
            else{
                System.out.println("Imma KMS");
                die();
                return false;
            }
        }
        else{
            System.out.println("not touching");
            return false;
        }
    }
}
