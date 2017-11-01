import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Food for the organism to eat.
 * @author Uzair Ahmed
 * @version 1.0
 */

public class Food extends Actor {
    MainWorld world;

    int reprodRate = 2;

    boolean spawning = true;

    public boolean infected;
    
    public Food(boolean parentInfected){
        int infectedChance = Greenfoot.getRandomNumber(1000);
        if(infectedChance == 666 || parentInfected){
            infected = true;
        }
        else{
            infected = false;
        }
    }
    //The size of the food. Other classes *should* be able to see this.
    static int foodMass = 10;
    //GreenfootImage to store the picture to create.
    GreenfootImage img = new GreenfootImage(foodMass, foodMass);

    public void act()
    {
        //Uzair Ahmed

        if (world == null){
            world = (MainWorld) getWorld();
        }

        //Draws food.
        drawFood(foodMass, img);
        if (spawning){
            spawnShift();
        }
        if (Greenfoot.getRandomNumber(750) == 1){
            reproduce();
        }
        marcoPolo();
    }

    //Draws the food
    public void drawFood(int m, GreenfootImage i){
        //Uzair Ahmed

        //Gets a random angle between a range of 35-55
        int num = Greenfoot.getRandomNumber(10)+15;
        //turns and moves accordingly
        turn(num);
        move(1);

        //Set the color to black, draw an empty oval, fill the oval
        if (infected){
            i.setColor(Color.GREEN);
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
        int posX = getX();
        int posY = getY();

        //If it is at the edge
        if(posX <= 0){
            posX = 999;
        }
        else if (posX >= 1000){
            posX = 1;
        }
        
        if(posY <= 0){
            posY = 999;
        }
        else if (posY >= 1000){
            posY = 1;
        }
        
        setLocation(posX, posY);
    }

    public void spawnShift(){
        //Uzair Ahmed

        setRotation(Greenfoot.getRandomNumber(360));
        move((Greenfoot.getRandomNumber(30)+50));
        spawning = false;
    }
}