import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Leaves for the organism to eat.
 *
 * CHANGLOG Oct 10, 2017
 *  - Added trippy movement to food.
 * @author Uzair Ahmed
 * @version 0.1.3
 */
public class Food extends Actor {
    MainWorld world;
    static int foodMass = 10;
    GreenfootImage img = new GreenfootImage(foodMass, foodMass);

    public void act()
    {
        int num = Greenfoot.getRandomNumber(20)+35;
        turn(num);
        move((int)(num/20));
        img.setColor(Color.BLACK);
        img.drawOval(0,0,foodMass,foodMass);
        img.fillOval(0,0,foodMass,foodMass);
        setImage(img);
    }
}
