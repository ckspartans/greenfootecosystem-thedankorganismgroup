import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * 
 * @author Cameron Dickie
 * @version 0.1
 */

public class Button extends Actor
{
    GreenfootImage img;
    MainWorld world;
    PauseWorld pause;
    PauseButton p;

    public Button(MainWorld w, int width, int height) {
        img = new GreenfootImage(width, height);
        img.setColor(new Color(200,200,200));
        img.fill();
        setImage(img); //assigning the color and size of the square
        world = w; // creates a reference to the mainworld
    }

    public void act() 
    {
        // Add your action code here.
        if(Greenfoot.mouseClicked(this)) {
            //onclick method
        }
    }    
}
