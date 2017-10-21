import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Family Class
 * 
 * @author Uzair Ahmed
 * @version 0.1
 */
public class Family extends Actor
{
    public List familyList = new ArrayList();
    
    public Family() {}
  
    public void addOrganism(Organism o){
        familyList.add(o);
    }
    
    public void remOrganism(Organism o){ 
        familyList.remove(o); 
    }
    
    /*public void act() 
    {
        // Add your action code here.
    }*/    
}
