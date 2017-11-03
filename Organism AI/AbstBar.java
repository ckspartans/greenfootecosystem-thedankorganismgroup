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
    
    protected GreenfootImage img;
    protected int height;
    protected int width;
    
    protected long curTime;
    protected long lastAdded;
    public void act() 
    {
        // Add your action code here.
        /*
        
        curTime = System.currentTimeMillis();
        if(curTime >= lastAdded + 2000) {
            removeObject(this);
            lastAdded = curTime;
        }
        */
    }    
    public abstract int updateValue(int w);
    public abstract void updateColor();
    public abstract void drawBar();
    
}
