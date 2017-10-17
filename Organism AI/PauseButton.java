import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PauseButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PauseButton extends Actor
{
    /**
     * Act - do whatever the PauseButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */

    GreenfootImage img;
    PauseWorld pause;
    MainWorld world;

    public PauseButton(MainWorld w, PauseWorld p, int width, int height) {
        img = new GreenfootImage(width, height);
        img.setColor(new Color(200,200,200));
        img.fill();
        setImage(img); //assigning the color and size of the square
        world = w; // creates a reference to the mainworld
        pause = p;
    }

    public void act() 
    {
        // Add your action code here.
        if(Greenfoot.mouseClicked(this)) {
            //world.setWorld(pause);
        }
    }    
}
