import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class UIBack here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class UIBack extends Actor
{
    /**
     * Act - do whatever the UIBack wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    GreenfootImage UIBack;
    public int width = 840;
    public int height = 1080;

    public UIBack() {
        UIBack = new GreenfootImage(width, height);
        UIBack.setColor(new Color(0,255,0));
        UIBack.fill();
    }

    public void act() 
    {
        // Add your action code here.
        setImage(UIBack);
    }
}
