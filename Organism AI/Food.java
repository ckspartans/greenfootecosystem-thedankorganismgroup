import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Leaves for the organism to eat.
 *
 * CHANGLOG Oct 4,2017
 *  - Deleted old code
 * @author Uzair Ahmed
 * @version 0.1.2
 */
public class Food extends Actor {
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
    }
}
