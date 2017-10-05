import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Main GameState
 *
 * @author Uzair Ahmed
 * @version 0.1
 */
public class MainWorld extends World {

  IntroScreen intro;
  MyOrganism player;
  int foodEaten;

  /**
   * Constructor for objects of class MyWorld.
   */

  public MainWorld() {

    super(500,500, 1);
    intro = new IntroScreen(this);
    Greenfoot.setWorld(intro);

    //setPaintOrder(MyOrganism.class);

    player = new MyOrganism();
    addObject(player, 250, 250);

    for (int i = 0; i < 10; i++) {
      addObject(new Food(), Greenfoot.getRandomNumber(getWidth()), Greenfoot.getRandomNumber(getHeight()));
    }
  }
}
