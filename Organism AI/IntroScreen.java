import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class IntroScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class IntroScreen extends World
{
    
    MainWorld world;
    
    /**
     * Constructor for objects of class IntroScreen.
     * 
     */
    public IntroScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
    }
    
    public IntroScreen(MainWorld w)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        world = w;
    }
    
    public void act(){
        if (Greenfoot.mouseClicked(this)){
            Greenfoot.setWorld(world);
        }
    }
}
