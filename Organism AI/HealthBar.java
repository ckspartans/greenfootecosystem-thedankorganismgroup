import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class HealthBar here.
 * 
 * @author Cameron Dickie
 * @version 1.9
 */
public class HealthBar extends AbstBar
{
    /**
     * Act - do whatever the HealthBar wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    Organism owner;
    MainWorld world;
    Color c;
    public HealthBar(Organism o, int w, int h, double max, double cur) {
        isActive = false;
        width = w;
        height = h;
        maxVal = max;
        curVal = cur;
        img = new GreenfootImage(width, height);
        owner = o;
    }
    public void act() 
    {
        // Add your action code here.
        if(world == null) {
            world = (MainWorld) getWorld();
        }
        /*if(owner.getWorld() == null) {
            removeObject(this);
        }
      */
        drawBar();
    }    
    public int updateValue(int w) {
        w = w*((int) (curVal/maxVal));
        return w;
    }
    public void updateColor() {
        c = new Color(0, 255, 0);   
        img.setColor(c);
    }
    public void drawBar() {
        updateColor();
        img.fill();
        setImage(img);
    }
    
    
}
