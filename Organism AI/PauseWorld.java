import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PauseWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PauseWorld extends World
{

    /**
     * Constructor for objects of class PauseWorld.
     * 
     */
    GreenfootImage bg;
    ResumeButton r;
    MainWorld game;
    ExitButton e;
    public PauseWorld(MainWorld g)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1920, 1080, 1); 
        bg = new GreenfootImage(1920,1080);
        bg.setColor(new Color(119, 221, 119));
        bg.fill();
        setBackground(bg);
        game = g;
        r = new ResumeButton(400,100, game);
        e = new ExitButton(400,100);
        addObject(r, 1920/2, 1080/2 - 100);
        addObject(e, 1920/2, 1080/2 + 100);
    }
    
    public void  act() {
        setBackground(bg);
        
    }
    
    

}
