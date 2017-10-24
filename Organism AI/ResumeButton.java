import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ResumeButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ResumeButton extends Actor
{
    /**
     * Act - do whatever the ResumeButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    GreenfootImage img;
    PauseWorld world;
    MainWorld game;
    public ResumeButton(int width, int height, MainWorld w) {
        img = new GreenfootImage(width, height);
        img.setColor(new Color(200,200,200));
        img.fill();
        setImage(img); //assigning the color and size of the square
        game = w;
    }
    public void act() 
    {
        // Add your action code here.
        if(world == null) {
            world = (PauseWorld) getWorld();
        }
        if(Greenfoot.mouseClicked(this)) {
           Greenfoot.setWorld(game);
        }
    }    
}
