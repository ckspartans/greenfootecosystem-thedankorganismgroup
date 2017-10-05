import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Main GameState
 *
 * @author Uzair Ahmed
 * @version 0.1
 */
public class MainWorld extends World {

  IntroScreen intro;
  //TestOrganism rocket;
  MyOrganism player;
  int foodEaten;

  //EnemyOrganism enemy; //TEST CODE

  /**
   * Constructor for objects of class MyWorld.
   */

  public MainWorld() {
    // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
    super(1000, 1000, 1);
    intro = new IntroScreen(this);
    Greenfoot.setWorld(intro);

    setPaintOrder(MyOrganism.class);

    //rocket = new TestOrganism();
    //addObject(rocket,100,100);

    player = new MyOrganism();
    addObject(player, 250, 250);

    //enemy = new EnemyOrganism();
    //addObject(enemy, 250, 250);

    for (int i = 0; i < 10; i++) {
      addObject(new Food(), Greenfoot.getRandomNumber(getWidth()), Greenfoot.getRandomNumber(getHeight()));

    }
  }

  public void act() {
    showText("Food:" + foodEaten, 50, 20);
  }

}
