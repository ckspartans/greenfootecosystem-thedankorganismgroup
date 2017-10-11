import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Leaves for the organism to eat.
 *
 * CHANGLOG Oct 4,2017
 *  - Deleted old code
 * @author Uzair Ahmed
 * @version 0.1.2
 */
public class Food extends Actor {
    MainWorld world;
    static int foodMass = 10;
    GreenfootImage img = new GreenfootImage(foodMass, foodMass);

    public void act()
    {
        img.setColor(Color.BLACK);
        img.drawOval(0,0,foodMass,foodMass);
        img.fillOval(0,0,foodMass,foodMass);
        setImage(img);
    }
}
