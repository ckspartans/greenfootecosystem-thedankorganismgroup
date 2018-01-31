import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * @author Cameron Dickie 
 * @version 0.0
 */
public class PauseWorld extends World
{

    /**
     * Constructor for objects of class PauseWorld.
     * 
     */
    GreenfootImage bg;
    Button r;
    MainWorld game;
    Button e;
    public PauseWorld(MainWorld g)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1920, 1080, 1); 
        bg = new GreenfootImage(1920,1080);
        bg.setColor(new Color(119, 221, 119));
        bg.fill();
        setBackground(bg);
        game = g;
        r = new Button(400,100, game);
        e = new Button(400,100, game);
        addObject(r, 1920/2, 1080/2 - 100);
        addObject(e, 1920/2, 1080/2 + 100);
    }
    
    public void  act() {
        if(e.getActive()) {
            System.exit(0);
            e.setActive(false);
        }
        if(r.getActive()) {
            Greenfoot.setWorld(game);
            r.setActive(false);
        }
        setBackground(bg);
        
    }
    
    

}