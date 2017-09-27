import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Main GameState
 * 
 * @author Uzair Ahmed 
 * @version 0.1
 */
public class MainWorld extends World
{

    IntroScreen intro;
    TestOrganism rocket;
    Algae al;
    int foodEaten;
    
    /**
     * Constructor for objects of class MyWorld.
     */
    
    public MainWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
        intro = new IntroScreen(this);
        setPaintOrder(TestOrganism.class);
        
        al = new Algae();
        addObject(al, 250,250);
        
        Greenfoot.setWorld(intro);
        for(int i = 0; i < 10; i ++){
            addObject(new Food(), Greenfoot.getRandomNumber(getWidth()), Greenfoot.getRandomNumber(getHeight()));
            
        }
    }
    public void act(){
        if (Greenfoot.isKeyDown("space")){
            removeObject(al);
        }
        showText("Food:" +foodEaten, 50, 20);
        
    }
    
    
}
