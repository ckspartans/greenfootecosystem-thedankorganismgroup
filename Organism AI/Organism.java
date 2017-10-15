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
 * @version 1.4
 */

public class Organism extends AbstOrganism {
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
        AI.think(this, foodNearby, family, enemies, foodBeingEaten);
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
        return timer/60;
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

    public void attack(int enemy){
        /*get the amount of organisms in the group
         *calculate the total group power
         *
         *To be done v2:
         *search for enemies
         *check if enemy is worth attacking (check enemy threat level, enemy size, enemy position & check group threat level, group number, group position)
         *if the enemy is worth attacking check to see how easy it is attacked, how many organisms the herd needs to kill the enemy, which organisms to use
         *choose a tatic and attack!!
         *
         *
         *fight until death
         *if defender has first strike they attack first
         *delete the loser
         *if win return how many energy they get
         */

        int eX;
        int eY;
        int radius;

        chosenEnemy(enemy); //choose which enemy to attack their chosenEnemy value, which enemy is it?
        attackMode(true); //set your own attackmode to true	
        calculateAttack(); //figure out how badass your squad is

        //move towards enemy
        hit(enemy);

        
        //surround enemy
        //attack enemy at once

    }

    public void defend(){
        /*search for the amount of enemies in sight range (this needs to be constantly run so the defender knows if more enemies are coming)
         *check the enemies coming at you (threat level and position)
         *determine the best strategy to use
         *Tatics:
         *A) Run like hell away
         *B) Attack closest enemy
         *C) Attack enemies based on position, threat level
         *
         *keep track of all the energy they gain by eating the bodies
         *eat the bodies (if they win at the end)
         */
        for (int i=0; i++; i > enemiesNearby.size){ //finds all the enemies nearby
            if (enemiesNearby.get(i.attackMode) == true){ //if any enemy is attacking
                //if (intelligence >= Xamount){ //if this organism is smart
                    //if (enemiesNearby.get(i.chosenEnemy) == ){ //only notice it is being attacked if it is acutally being attacked, not an organism near it
                            //go towards enemy
                            //hit enemy
                     //}
                //}
                //else {
                    //turn towards enemy (Uzair)
                    //move towards enemy (Uzair)
                    //hit enemy
                    hit(i);
                //}
            }
        }
    }

    public int calculateAttack(int groupAtt){
        //This gets all the attack power of the organisms in the group
        //needs uzair's code

        for (int i = 0; i++; i > family.size){
            groupAtt += family.get(i.attack);
        }

        return groupAtt;
    }

    public boolean attackMode(boolean attacking){ //if you choose to attack someone, set attackMode to true
        return mode;
    }

    public int chosenEnemy(int enemyNum){//if you are attacking someone, this is the enemy you are attacking (number is the number it is on the enemy list)
        return enemyNum;
    }

    public void checkGroupAttack(){ //checks if anyone in the group is attacking someone
        for (int i = 0; i++; i > family.size){ 
            if (family.get(i.attackMode) == true){ //if anyone in your family is attaccking someone
                chosenEnemy(family.get(i.chosenEnemy)); //using their chosenEnemy value, which enemy is it?
                attackMode(true); //set your own attackmode to true
                attack(i); //attack that enemy
            }
        }
    }
    
    public void hit(int enemy){ //hits enemy
        if (isTouching(enemies.enemy)){
         enemies.get(enemy.health) -= (att - enemies.get(enemy.def)); //hits selected enemy for your attack - enemy defense
         enemies.get(enemy.def) -= (enemies.get(enemy.def)*(att/enemies.get(enemy.def))); //reduce the defensive power by the your attack divided by their defense percentage eg att-->1 def -->2 new def = 1
        }
    }
}
