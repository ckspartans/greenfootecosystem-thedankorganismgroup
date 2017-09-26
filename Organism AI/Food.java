import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Food here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Food extends Actor
{
    MyWorld world;
    /**
     * Act - do whatever the Food wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(world == null){
            world = (MyWorld)getWorld();
        }
        // Add your action code here.
        if (getOneIntersectingObject(TestOrganism.class)!= null){
           world.foodEaten ++;
           world.removeObject(this);
        }
    }    
}