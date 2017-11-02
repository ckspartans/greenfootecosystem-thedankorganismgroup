import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Food for the organism to eat.
 * @author Uzair Ahmed
 * @author Josh Dhori
 * @version 1.5
 */

public class Food extends Actor {
    //Declares world reference
    MainWorld world;
    //GreenfootImage to store the picture to create.
    GreenfootImage img = new GreenfootImage(foodMass, foodMass);
    //is it currently spawning
    boolean spawning = true;
    //is it infected
    public boolean infected;
    //The size of the food. Other classes *should* be able to see this.
    static int foodMass = 10;
    //reproduction rate
    int reprodRate = 2;

    public Food(boolean parentInfected){
        //Josh Dhori
        //creates a food object with the chance of it turning into a parasite

        int infectedChance = Greenfoot.getRandomNumber(1000);
        if(infectedChance == 666 || parentInfected){
            infected = true;
        }
        else{
            infected = false;
        }
    }

    public void act(){
        //Instantiates World
        if (world == null){
            world = (MainWorld) getWorld();
        }

        //Draws food.
        drawFood(foodMass, img);

        //Shifts slightly to spread out more evenly
        if (spawning){
            spawnShift();
        }

        //Wrap around the edges
        marcoPolo();

        //Reproduces at a low probability
        if (Greenfoot.getRandomNumber(750) == 1){
            reproduce();
        }
    }

    public void drawFood(int m, GreenfootImage i){
        //Uzair Ahmed
        //Draws the food

        //Gets a random angle between a range of 35-55
        int num = Greenfoot.getRandomNumber(10)+15;
        //turns and moves accordingly
        turn(num);
        move(1);

        //Set the color to black, draw an empty oval, fill the oval
        if (infected){
            i.setColor(Color.WHITE);
        }
        else if (!infected){
            i.setColor(Color.BLACK);
        }
        i.drawOval(0,0,m,m);
        i.fillOval(0,0,m,m);
        //Set the class image to the image created above.
        setImage(i);
    }

    public void reproduce(){
        //Uzair Ahmed
        for (int i = 0; i < reprodRate; i++){
            world.addObject(new Food(infected), getX(), getY());
        }
    }

    public void marcoPolo(){
        //Uzair Ahmed
        //It teleports to the other side of the map when hitting the side

        //Get current position values
        int posX = getX();
        int posY = getY();

        //If it is at the edge, move to the other edge
        if(posX <= 0){
            posX = 1079;
        }
        else if (posX >= 1080){
            posX = 1;
        }
        if(posY <= 0){
            posY = 1079;
        }
        else if (posY >= 1080){
            posY = 1;
        }

        //set the final location
        setLocation(posX, posY);
    }

    public void spawnShift(){
        //Uzair Ahmed
        //turns and move in a random direction at spawn

        setRotation(Greenfoot.getRandomNumber(360));
        move((Greenfoot.getRandomNumber(30)+50));
        spawning = false;
    }
}
