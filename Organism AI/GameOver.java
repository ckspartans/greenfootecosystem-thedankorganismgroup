import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * GameOver Class
 * 
 * @author Cameron Dickie 
 * @version 1.0
 */
public class GameOver extends World
{

    /**
     * Constructor for objects of class GameOver.
     * 
     */
    GreenfootImage img;
    public GameOver()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1920, 1080, 1); 
        img = new GreenfootImage("gameover.png");
        setBackground(img);
    }

}
