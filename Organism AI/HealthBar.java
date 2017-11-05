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
    Color fc;
    
    public HealthBar(Organism o, int h, double max, double cur) {
        isActive = false;
        height = h;
        maxVal = max;
        curVal = cur;
        fc = new Color(0,255,0);
        owner = o;
    }
    public void act() 
    {
        // Add your action code here.
        if(world == null) {
            world = (MainWorld) getWorld();
        }
        drawBar(fc);
    }    
    
    public void updateValue(double h, double mh) {
        curVal = h;
        maxVal = mh;
        percent = Math.abs((int)((curVal/maxVal)*100));
       }

    public void drawBar(Color c) {
        imgfront = new GreenfootImage((int)maxVal, height);
        imgfront.drawRect(0,0,(int)maxVal-1,height-1);
        imgfront.setColor(c);
        imgfront.fillRect(1,1,percent-2, height-2);
        setImage(imgfront);
    }
}
