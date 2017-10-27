import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Intro Screen
 *
 * @author Class
 * @version 0.1
 */
public class IntroScreen extends World {

    MainWorld world;

  Button start;
  Button exit;

    public IntroScreen(MainWorld w)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1900, 1080, 1); 
        world = w;
        bg = new GreenfootImage(1920,1080);
        bg.setColor(new Color(119, 221, 119));
        bg.fill();
        start = new Button(400,100, w);
        exit = new Button(400,100, w);
        addObject(start, 1920/2, 1080/2 - 100);
        addObject(exit, 1920/2, 1080/2 + 100);
    }

  public void act() {
    if (start.getActive()) {
      Greenfoot.setWorld(world);
      start.setActive(false);
    }
    if(exit.getActive()) {
        System.exit(0);
    }
    draw();
}
  public void draw() {
      //draws a list of text options such as start, options, and exit
      setBackground(bg);
    }

    public void setWorld() {
        Greenfoot.setWorld(world);
    }

}
