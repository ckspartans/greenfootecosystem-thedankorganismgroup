import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * 
 * @author Cameron Dickie
 * @version 0.1
 */

public class Button extends Actor
{
    public GreenfootImage img;
    public MainWorld game;
    public boolean active = false;

    public Button(int width, int height, MainWorld w) {
        img = new GreenfootImage(width, height);
        img.setColor(new Color(200,200,200));
        img.fill();
        setImage(img); //assigning the color and size of the square
        game = w; // setting the reference to the world

    }

    public void act() 
    {
        // Add your action code here.
        checkActive();
    }    

    public void checkActive() {
        if(Greenfoot.mouseClicked(this)) {
            active =  true;
        }
    }

    public boolean setActive(boolean n) {
        active = n;
        return active;
    }

    public boolean getActive() {
        return active;
    }

}
