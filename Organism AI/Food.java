import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Leaves for the organism to eat.
 * 
 * @author Uzair Ahmed 
 * @version 0.1
 */
public class Food extends Actor
{
    MainWorld world;
    /**
     * Act - do whatever the Food wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(world == null){
            world = (MainWorld)getWorld();
        }
        // Add your action code here.
        if (getOneIntersectingObject(TestOrganism.class)!= null){
           world.foodEaten ++;
           world.removeObject(this);
        }
    }    
}