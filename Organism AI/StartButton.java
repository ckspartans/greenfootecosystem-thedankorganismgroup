import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class StartButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StartButton extends Actor
{
    /**
     * Act - do whatever the StartButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    GreenfootImage img;
    IntroScreen world;
    MainWorld game;
    public StartButton(int width, int height, MainWorld g) {
        img = new GreenfootImage(width, height);
        img.setColor(new Color(200,200,200));
        img.fill();
        setImage(img); //assigning the color and size of the square
        game = g;
    }
    public void act() 
    {
        // Add your action code here.
        if(world == null) {
            world = (IntroScreen) getWorld();
        }
        if(Greenfoot.mouseClicked(this)) {
            Greenfoot.setWorld(game);
        }
    }    
}
