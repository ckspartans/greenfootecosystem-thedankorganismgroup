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
  public void act() {
    if (world == null) {
      world = (MainWorld) getWorld();
    }
  }
  public int getX(){
    return 1;
  }
}
