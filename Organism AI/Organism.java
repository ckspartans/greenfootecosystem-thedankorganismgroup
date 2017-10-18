import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Extends off AbstOrganism to create a basic organism that reproduces, and moves around the screen.
 *
 * CHANGELOG October 14, 2017
 *      -Added aging code
 *
 * KNOWN BUGS TO ASK ABOUT:
 * - lines 111, 114. Organism does not stop at adding itself once.
 *
 *
 * @author Uzair Ahmed
 * @author Ethan Gale
 * @authot Josh Dhori
 * @version 1.4
 */

public class Organism extends AbstOrganism {
    //Dhori Variables
    public boolean attackMode = false; //if the organism is attacking
    public Organism chosenEnemy;

    public Organism(int smh, int smxp, int ss, int sa, int sd, int ssi, int t, Color c) {
        //XP Upgradeable Variables-------------------------------------------------
        maxHealth = smh; //Maximum Health
        maxXp = smxp; //Max XP Storage
        speed = ss; //Nuff Said
        att = sa; //Attack power
        def = sd; //Defensive power
        sight = ssi; //Sight

        //"Live" Variables. ----------------------------------------------------
        age = 0; //Time
        health = maxHealth-2; //Out of maxHealth
        xp = 0; //Out of maxXp
        radius = health*2;
        threatLevel = (att*health*def)/(1+age);

        //Team Variables
        family = new ArrayList<Organism>();
        enemies = new ArrayList<Organism>();
        myTeam = t;
        myColor = c;

        //Adds [this] to lifeForms team
        MainWorld.lifeForms.get(myTeam-1).add(this);

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

        //Draws the organism
        drawOrganism(myColor, radius);

        //Checks for friends or enemies.
        distinguishOrganisms();

        //Runs Mutation Method
        mutate();

        //Runs the AI Method

        AI.think(this, foodNearby, family, enemies, foodBeingEaten, family, enemies);
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

    //Removes the food it touches, and adds the mass to xp
    public void consumeFood(Food foodBeingEaten){
        //Check if the organism is currently on top of something
        if (foodBeingEaten != null){
            //Remove the food
            removeTouching(Food.class);
            //Gets the mass of the food and adds it to xp.
            int foodConsumed = foodBeingEaten.foodMass;
            xp+=foodConsumed/10;
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
                    int chosenMutation = Greenfoot.getRandomNumber(4);
                    if (chosenMutation == 1){ //attack
                        att += 1;
                    }
                    else if (chosenMutation == 2){ //defense
                        def += 1;
                    }
                    else if (chosenMutation == 3){ //speed
                        speed += 2;
                    }
                    else if (chosenMutation == 4){ //sight range
                        sight += 10;
                    }
                    xp=0;
                }
            }
        }
    }

    //Distinguishes all organisms and adds them to a respective List
    public void distinguishOrganisms(){
        //Go through each team in lifeforms
        for (int l = 0; l < world.lifeForms.size(); l++){
            //Go through each object in the team in question
            for (int o = 0; o < world.lifeForms.get(l).size();o++){
                //Creates a temporary organism for the object in question
                Organism org = (Organism) world.lifeForms.get(l).get(o);
                //Compares the myTeam variable of the org, and [this]
                //If it's the same add to the family list
                if (org.myTeam == myTeam){
                    family.add(org);
                }
                //Otherwise, if it's different, add to the enemies list
                else if (org.myTeam != myTeam){
                    enemies.add(org);
                }
            }
        }
    }

    public void startTimer(){
        //Frame counter
        age++;
    }

    public int getAge() {
        //Calculates the age in time, by taking the frames
        //and calculating based on an anerage 60 fps
        return age/60;
    }

    public void reproduce() {
        //Creates a temporary organism with the same traits as its parent.
        Organism tempOrg = new Organism(maxHealth, xp,speed, att, def, sight, myTeam, myColor);
        //Adds it to myWorld
        world.addObject(tempOrg,(getX()+Greenfoot.getRandomNumber(30)-15),(getY()+Greenfoot.getRandomNumber(30)-15));

        //Will have this run twice and kill the parent once die() method is created.
    }

    //I haven't thought about things this far yet :/

    public void die() {
        //Remove the object.
    }

    public void kill(Organism prey){
        if (prey != null){
            //Remove the prey
            int energyGain = (prey.maxHealth/family.size());
            //prey.die(); //runs uzair's die function on the prey
            for(int i = 0; i > family.size(); i++){ //distributes the energy gain between team members
                Organism tempOrg; 
                tempOrg = (Organism)family.get(i);
                tempOrg.xp += energyGain/10;
            }
        }
    }

    public void hit(Organism prey){ //hits enemy
        if (prey != null) {
            if (isTouching(Organism.class)){ //if touching the X organism in totalEnemy list
                if ((att - prey.def) > 0){
                    prey.health -= (att - prey.def); //hits selected enemy for your attack - enemy defense
                }
                prey.def -= (prey.def*(att/prey.def)); //reduce their defensive power by your attack by dividing their defense percentage eg att-->1 def -->2 new def = 1
                if(prey.health <=0){
                    kill(prey);
                }
            }
        }
    }

    public boolean touching(Organism enemy){
        return (isTouching(Organism.class));
    }

}
