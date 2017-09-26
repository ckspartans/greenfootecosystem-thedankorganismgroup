import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{

    IntroScreen intro;
    TestOrganism rocket;
    int foodEaten;
    /**
     * Constructor for objects of class MyWorld.
     */
    
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
        intro = new IntroScreen(this);
        setPaintOrder(TestOrganism.class);
        rocket = new TestOrganism();
        addObject(rocket, 300, 300);
        //addObject(new TestOrganism(), 100, 200); 
        Greenfoot.setWorld(intro);
        for(int i = 0; i < 10; i ++){
            addObject(new Food(), Greenfoot.getRandomNumber(getWidth()), Greenfoot.getRandomNumber(getHeight()));
        }
    }
    public void act(){
        if (Greenfoot.isKeyDown("space")){
            removeObject(rocket);
        }
        showText("Food:" +foodEaten, 50, 20);
    }
}
