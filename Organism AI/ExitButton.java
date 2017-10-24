import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ExitButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ExitButton extends Actor
{
    /**
     * Act - do whatever the ExitButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    GreenfootImage img;
    IntroScreen world;
    public ExitButton(int width, int height) {
           img = new GreenfootImage(width, height);
           img.setColor(new Color(200,200,200));
           img.fill();
           setImage(img); //assigning the color and size of the square

        }
    public void act() 
     {
        // Add your action code here.
        if(world == null) {
            world = (IntroScreen) getWorld();
        }
        if(Greenfoot.mouseClicked(this)) {
            System.exit(0);
        }
    }    
}
