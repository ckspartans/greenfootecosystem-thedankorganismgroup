import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TestOrganism here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TestOrganism extends Actor
{
    protected int speed = 5;
    protected int spX = 0;
    protected int spY = 0;
    /**
     * Act - do whatever the TestOrganism wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.  
        checkKeys();
    }    
    protected void checkKeys(){
        if(Greenfoot.isKeyDown("w")){
            spY = - speed;
        }
        else if (Greenfoot.isKeyDown("s")){
            spY = speed;
        }
        else{
            spY = 0;
        }
        if (Greenfoot.isKeyDown("a")){
            spX = - speed;
        }
        else if (Greenfoot.isKeyDown("d")){
            spX = speed;
        }
        else{
            spX = 0;
        }
        setLocation(getX()+spX, getY() + spY);
    }
}