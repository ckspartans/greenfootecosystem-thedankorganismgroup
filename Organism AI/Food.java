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
        if (Greenfoot.getRandomNumber(500) == 1){
            reproduce();
        }
    }

    //Draws the food
    public void drawFood(int m, GreenfootImage i){
        //Uzair Ahmed

        //Gets a random angle between a range of 35-55
        int num = Greenfoot.getRandomNumber(50)+50;
        //turns and moves accordingly
        turn(num);
        move(1);

        //Set the color to black, draw an empty oval, fill the oval
        i.setColor(Color.BLACK);
        i.drawOval(0,0,m,m);
        i.fillOval(0,0,m,m);
        //Set the class image to the image created above.
        setImage(i);
    }

    public void reproduce(){
        //Uzair Ahmed
        for (int i = 0; i < reprodRate; i++){
            world.addObject(new Food(), getX(), getY());
        }
    }

    public void spawnShift(){
        //Uzair Ahmed

        setRotation(Greenfoot.getRandomNumber(360));
        move((Greenfoot.getRandomNumber(50)+10));
        spawning = false;
    }
}