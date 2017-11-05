import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Intro Screen
 *
 * @author Class
 * @version 1.0
 */
public class IntroScreen extends World {

    MainWorld world;
    GreenfootImage bg;

    Button start;
    Button exit;
    String sLoc = "start.png";
    String eLoc = "exit.png";

    public IntroScreen(MainWorld w)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1920, 1080, 1); 
        world = w;
        bg = new GreenfootImage("bg.png");
        start = new Button(w, sLoc);
        exit = new Button(w, eLoc);
        addObject(start, 1920/2, 1080/2 - 100);
        addObject(exit, 1920/2, 1080/2 + 100);
    }

    public void act() {
        if (start.getActive()) {
            Greenfoot.setWorld(world);
            start.setActive(false);
        }
        if(exit.getActive()) {
            System.exit(0);
        }
        draw();
    }

    public void draw() {
        //draws a list of text options such as start, options, and exit
        setBackground(bg);
    }

    public void setWorld() {
        Greenfoot.setWorld(world);
    }

}
