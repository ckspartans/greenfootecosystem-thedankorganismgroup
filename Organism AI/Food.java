import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Food for the organism to eat.
 *
 * CHANGLOG Oct 10, 2017
 *  - Added trippy movement to food
 *  - Added pseudocode

 * @author Uzair Ahmed
 * @version 0.1.4
 */

public class Food extends Actor {
    //The size of the food. Other classes *should* be able to see this.
    static int foodMass = 10;
    //GreenfootImage to store the picture to create.
    GreenfootImage img = new GreenfootImage(foodMass, foodMass);

    public void act()
    {
        //Draws food.
        drawFood(foodMass, img);
    }

    //Draws the food
    public void drawFood(int m, GreenfootImage i){
        //Gets a random angle between a range of 35-55
        int num = Greenfoot.getRandomNumber(10)+15;
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
    
}
