import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class AbstBar here.
 * 
 * @author Cameron Dickie 
 * @version 1.9
 */
public abstract class AbstBar extends Actor
{
    /**
     * Act - do whatever the AbstBar wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    public Boolean isActive;
    protected double maxVal; // maximum value of the bar
    protected double curVal; // current value of the bar

    protected GreenfootImage imgfront;
    protected int height;
    protected int percent;
    
    public abstract void updateValue(double h, double mh);
    public abstract void drawBar(Color c);
    
}
